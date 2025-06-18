# MY TODO LIST (backend)


웹으로 접속하여 사용 가능한 간단한 TODO LIST 프로그램입니다.

준비물: `jdk 21`, `MariaDB`, `MongoDB`, `Kafka`

## 시작하기

---

### 1. 프로젝트 클론

```
git clone https://github.com/leeejjju/my_todo.git`
cd todo-backend
```

<br>

### 2. 설정 파일 수정
`src/main/resources/application.properties.example`을 적절히 수정하여 `application.properties`로 만들기


<br>

### 3. DataBase 및 Kafka 설정 


   * MariaDB
     * DB: `todo_maria`
     * table: `todos`
     * PORT: 3306
     

   * MongoDB
     * DB: `todo_mongo`
     * collection: `todos`
     * PORT: 27017
     

   * Kafka
     * topic: `todo-events`
     * PORT: 9092


<br>

### 4. 빌드하기 


gradle 설치 (필요한 경우)
```
sudo apt update
sudo apt install gradle -y
chmod +x ./gradlew
```

java(jdk21) 설치 (필요한 경우)
```
sudo apt install openjdk-17-jdk -y
```

* Linux/macOS
```
./gradlew clean build
```
* Windows
```
./gradlew.bat clean build
```



<br>

### 5. 실행하기 
* Linux/macOS
```
./gradlew bootRun
```
* Windows
```
gradlew.bat bootRun
```

<br>

프론트 실행해서 나온 주소로 사용하면 됨!

---

*문의: leeejjju@gmail.com*
