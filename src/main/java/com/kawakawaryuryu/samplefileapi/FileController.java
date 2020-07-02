package com.kawakawaryuryu.samplefileapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class FileController {

  @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void upload(@RequestParam("file") MultipartFile file) throws IOException {
    log.info("content-type: {}", file.getContentType());
    log.info("name: {}", file.getName());
    log.info("originalFileName: {}", file.getOriginalFilename());
    log.info("file: {}", new String(file.getBytes()));
    // InputStreamをBufferedReaderに変換して利用
    // これによりファイルの内容がメモリ上に展開される
    // BufferedReaderでラップしたことで行読み込みやStream取得など様々な便利メソッドを使える
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),
        StandardCharsets.UTF_8))) {
      br.lines().forEach(line -> log.info("file: {}", line));
    }
  }
}
