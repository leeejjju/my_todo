
# 1단계: 빌드용 이미지
FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# 2단계: 실행용 경량 이미지
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


