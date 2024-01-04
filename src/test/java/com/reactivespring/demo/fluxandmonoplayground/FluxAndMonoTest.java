package com.reactivespring.demo.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
//                .concatWith(Flux.error(new RuntimeException("Exception")))
                .concatWith(Flux.just("After error")) // Not emitted because flux errored
                .log();

        stringFlux.subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("Completed")); //  Only executed on complete event
    }

    @Test
    public void fluxTestElements_WithoutError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .expectError(RuntimeException.class)
//                .expectErrorMessage("After error")
                .verify();
    }

    @Test
    public void fluxTestElementsCount_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectError(RuntimeException.class)
                .verify();
    }
}
