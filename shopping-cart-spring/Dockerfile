FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/app/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
# Download dependencies only (for caching)
RUN ./gradlew build -x test 2> /dev/null || return 0
COPY . .
RUN ./gradlew build

FROM openjdk:8-jre-alpine
WORKDIR /root/
RUN mv /usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64/jli/libjli.so /lib
COPY --from=BUILD_IMAGE /root/dev/app/build/libs/shopping-cart-*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]