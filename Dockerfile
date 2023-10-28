FROM gradle:jdk21-graal-jammy as builder
WORKDIR /opt/file-upload-server
COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon -x test --info

FROM sapmachine:21-jre-ubuntu-jammy
WORKDIR /opt/file-upload-server
VOLUME /var/file-upload-server/static
COPY --from=builder /opt/file-upload-server/build/libs/*.jar ./file-upload-server.jar
CMD java -jar file-upload-server.jar
