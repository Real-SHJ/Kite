FROM java:8

VOLUME [ "/var/lib/img" ]

EXPOSE 8999

ARG JAR_FILE=build/libs/image-0.0.2.jar

ADD ${JAR_FILE} kite-image.jar

ENTRYPOINT [ "java","-jar","/kite-image.jar" ]