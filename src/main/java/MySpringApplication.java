import componentScan.ComponentScanManager;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class MySpringApplication {

    public static void main(String[] args) {

//        ComponentScanManager.run(MySpringApplication.class);
        DisposableServer server =
                HttpServer.create()
                        .host("localhost") // (1)
                        .port(8080)        // (2)
                        .route(routes ->
                                routes.get("/hello",        // (1)
                                        (request, response) -> response.sendString(Mono.just("Hello World!")))
                                        .post("/echo",        // (2)
                                                        (request, response) -> response.send(request.receive().retain())))
                        .bindNow();


        server.onDispose()
                .block();
    }
}
