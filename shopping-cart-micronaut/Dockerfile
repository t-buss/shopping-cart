FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/shopping-cart-micronaut
WORKDIR /home/gradle/shopping-cart-micronaut
RUN ./gradlew build

FROM oracle/graalvm-ce:1.0.0-rc11 as graalvm
COPY --from=builder /home/gradle/shopping-cart-micronaut/ /home/gradle/shopping-cart-micronaut/
WORKDIR /home/gradle/shopping-cart-micronaut
RUN java -cp build/libs/*-all.jar \
            io.micronaut.graal.reflect.GraalClassLoadingAnalyzer \
            reflect.json
RUN native-image --no-server \
                 --class-path /home/gradle/shopping-cart-micronaut/build/libs/*-all.jar \
                 -H:ReflectionConfigurationFiles=/home/gradle/shopping-cart-micronaut/reflect.json \
                 -H:EnableURLProtocols=http \
                 -H:IncludeResources='logback.xml|application.yml|META-INF/services/*.*' \
                 -H:+ReportUnsupportedElementsAtRuntime \
                 -H:+AllowVMInspection \
                 --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder',javax.net.ssl.SSLContext \
                 --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom \
                 -H:-UseServiceLoaderFeature \
                 --allow-incomplete-classpath \
                 -H:Name=shopping-cart-micronaut \
                 -H:Class=com.example.myshop.shoppingcart.Application


FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/gradle/shopping-cart-micronaut/shopping-cart-micronaut .
ENTRYPOINT ["./shopping-cart-micronaut"]
