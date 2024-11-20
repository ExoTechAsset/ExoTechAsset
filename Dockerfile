FROM openjdk:17-jdk-alpine

# 設置工作目錄
WORKDIR /app

RUN apk update

# 將本地專案複製到容器中
COPY . /app

EXPOSE 8080
RUN ls
RUN ./gradlew clean build
CMD ["./gradlew", "bootrun"]