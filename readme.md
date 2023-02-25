# 결론
- lock 을 걸자
<img width="312" alt="image" src="https://user-images.githubusercontent.com/31065684/221095686-81a8709c-62ce-4a9a-8598-8f57217f2e8a.png">
- 설명 : 2개의 프로세스에서 10000번 요청 날릴때
  - 베이스라인, 리피터블 리드는 정합성 꺠짐

## 테스트돌리기

- 프로젝트 루트에서 

```shell
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 3 &&
ab -n 10000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4'
```


```shell
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/5' 
```

### with 웜업

```shell

ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/4' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/5' && sleep 10 &&

ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/6' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/7' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/8' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v3/9' && sleep 10 &&
ab -n 5000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v4/10' 
```



- 성능에 관하여
```
1번 : Requests per second:    4411.54 [#/sec] (mean)

2번 : Requests per second:    4757.34 [#/sec] (mean)

3번 : Requests per second:    3883.90 [#/sec] (mean)

4번 : Requests per second:    242.77 [#/sec] (mean)

```


## 베이스라인 코드 
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/1'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/1'
```

### 결과
```
```



## 1트 - 락만걸기

- 테스트
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v1/2'
```

```

```



## 2트 - 리피터블 리드

- 
```shell
# ab -n 15000 -c 1 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3'
ab -n 15000 -c 2 -p data.json -T 'application/json' 'http://localhost:8080/post/v2/3'
```

```
```


