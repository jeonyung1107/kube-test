FROM openjdk:11

EXPOSE 8080

ARG JAR_FILE=build/libs/kube-test-0.0.1.jar

ADD ${JAR_FILE} kube-test.jar

ENTRYPOINT ["java", "-jar", "kube-test.jar"]