package com.example.loggingconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@SpringBootApplication
public class LoggingConsumerApplication {

    private EmitterProcessor<String> processor = EmitterProcessor.create();

    public static void main(String[] args) {
        SpringApplication.run(LoggingConsumerApplication.class, args);
    }

    @RequestMapping("/{data}")
    public void accept(@PathVariable String data) {
        processor.onNext(data);
    }

    @Bean
    public Supplier<Flux<String>> stringSupplier() {
        return () -> processor;
    }

    @Bean
    public Function<String, Person> upperCase() {
        return it -> new Person(it.toUpperCase());
    }

    @Bean
    public Consumer<Person> log() {
        return person -> System.out.println("Received: " + person);
    }


    public static class Person {
        public String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
