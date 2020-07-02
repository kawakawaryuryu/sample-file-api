# sample-file-api
## ファイルアップロード
- curlでFオプションを使うとmultipart/form-dataで送ってくれる
- ファイルの指定はfile(@を使ってファイルパスを指定)
- ファイル名の指定はfilename
  - 省略するとfileで指定したパスのファイル名になる
- Content-Typeの指定はtype
  - 省略するとfileで指定したファイルから適切に選択される
    - xmlの場合はapplication/xmlでうまくいったが、csvはapplication/octet-streamになってしまった
```bash
# リクエスト例
% curl http://localhost:8080/upload -X POST -F "file=@test.csv; filename=testfile.csv; type=text/csv" --trace-ascii /dev/stdout
Note: Unnecessary use of -X or --request, POST is already inferred.
== Info:   Trying ::1...
== Info: TCP_NODELAY set
== Info: Connected to localhost (::1) port 8080 (#0)
=> Send header, 192 bytes (0xc0)
0000: POST /upload HTTP/1.1
0017: Host: localhost:8080
002d: User-Agent: curl/7.64.1
0046: Accept: */*
0053: Content-Length: 200
0068: Content-Type: multipart/form-data; boundary=--------------------
00a8: ----1489b55083ed2483
00be:
=> Send data, 200 bytes (0xc8)
0000: --------------------------1489b55083ed2483
002c: Content-Disposition: form-data; name="file"; filename="testfile.
006c: csv"
0072: Content-Type: text/csv
008a:
008c: 1,2,3.4,5,6.
009a: --------------------------1489b55083ed2483--
== Info: We are completely uploaded and fine
<= Recv header, 15 bytes (0xf)
0000: HTTP/1.1 200
<= Recv header, 19 bytes (0x13)
0000: Content-Length: 0
<= Recv header, 37 bytes (0x25)
0000: Date: Thu, 02 Jul 2020 17:30:25 GMT
<= Recv header, 2 bytes (0x2)
0000:
== Info: Connection #0 to host localhost left intact
* Closing connection 0
```

## ファイルダウンロード
http://localhost:8080/download をクリックしてブラウザでダウンロード  
```bash
# リクエスト例
% curl -i http://localhost:8080/download
HTTP/1.1 200
Content-Disposition: filename=test.csv
Content-Type: text/csv
Content-Length: 11
Date: Thu, 02 Jul 2020 17:28:27 GMT

1,2,3
4,5,6
```
