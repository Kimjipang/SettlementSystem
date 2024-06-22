# 기반 이미지 선택
FROM openjdk:21

WORKDIR /app

ARG KEYSTORE_FILE=keystore.p12
COPY ${KEYSTORE_FILE} keystore.p12
COPY build/libs/*.jar app.jar

EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","app.jar"]
