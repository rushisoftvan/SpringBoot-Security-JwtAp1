package com.learn1.jwt1.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class MyFileRead implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
       ClassPathResource classPathResource = new ClassPathResource("rushi.txt");
        //Path path = Paths.get("src/main/resources/rushi.txt");
        //List<String> lines = Files.readAllLines(path);
        //for(String line : lines ){
        //  System.out.println(line);
       // }
        InputStream inputStream = classPathResource.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String data = new String(bytes);
        System.out.println(data);

    }
}
