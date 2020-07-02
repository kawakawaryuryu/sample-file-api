# sample-file-api
## ファイルアップロード
```bash
# リクエスト例
% curl http://localhost:8080/upload -X POST -F "file=@test.csv; filename=testfile.csv; type=text/csv" --trace-ascii /dev/stdout
```
