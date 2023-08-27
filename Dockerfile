FROM openjdk:17-jdk-alpine3.14
WORKDIR /opt/image-upload-server
VOLUME /var/file-upload-server/static
COPY . .
RUN ./gradlew build --no-daemon --info
CMD java -jar build/libs/*.jar