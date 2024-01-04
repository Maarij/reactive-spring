package com.reactivespring.demo.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
//                .concatWith(Flux.error(new RuntimeException("Exception")))
                .concatWith(Flux.just("After error - not emitted because flux errored"))
                .log();

        stringFlux.subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("Completed - only executed on complete event"));
    }
}
