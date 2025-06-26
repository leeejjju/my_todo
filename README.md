# MY TODO LIST

클라우드 실습에 사용하기 위한 웹 애플리케이션입니다... <br><br>

## 환경

* Docker (version 28.2.2, build e6534b4)
* docker compose (version v2.36.2)

* -> [동일 환경 설정하기](https://leeejjju.tistory.com/8)
<br><br>


## 가능한 동작 
* TODO List 조회
* TODO 추가하기
* TODO 완료 여부 토글하기 
* TODO 삭제하기 
<br> <br>


## 시작하기

### 1. 환경 변수 설정

프로젝트 루트에 있는 `env.example` 파일의 이름을 `.env`로 변경하고 적절히 수정합니다.
```
mv .env.example .env
```

<br>

### 2. properties 설정

백엔드의 `application.properties.example` 파일의 이름을 `application.properties`로 변경해주세요.


```
 mv todo-backend-read/src/main/resources/application.properties.example todo-backend-read/src/main/resources/application.properties
 ```
 ```
  mv todo-backend-write/src/main/resources/application.properties.example todo-backend-write/src/main/resources/application.properties
  ```


<br>

### 3. make


혹시 make가 없다면...
```
sudo apt install make -y
```

빌드 및 실행하기
```
make up
```
중지하기
```
make stop
```
중지된 서비스 다시 시작하기
```
make
```
서비스 제거하기 (이미지는 제거되지 않음)
```
make down
```

---
<br><br>

<p align="center">
<img src="todo-frontend/public/images/general.PNG" width="80" height="80"/>
</p>
<br><br>

---

*문의: leeejjju@gmail.com*