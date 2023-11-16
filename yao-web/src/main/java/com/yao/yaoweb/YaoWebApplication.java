package com.yao.yaoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class YaoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YaoWebApplication.class, args);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("hello"));
        executorService.execute(() -> System.out.println("hello"));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                0,
                0,
                20,
                TimeUnit.SECONDS,
                null,
                null,
                null
        );
        threadPoolExecutor.execute(() -> System.out.println("hello"));
    }

}
