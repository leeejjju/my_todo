
# 1단계: 빌드용 이미지
FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# 2단계: 실행용 경량 이미지
FROM openjdk:21

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["/wait-for-it.sh", "mariadb:3306", "--", "java", "-jar", "app.jar"]


