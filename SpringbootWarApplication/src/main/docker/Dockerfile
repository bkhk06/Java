FROM flywithu/oraclejdk8
VOLUME /tmp
ADD docker-0.0.1-SNAPSHOT.jar dockerapp.jar
RUN sh -c 'touch /dockerapp.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /dockerapp.jar" ]