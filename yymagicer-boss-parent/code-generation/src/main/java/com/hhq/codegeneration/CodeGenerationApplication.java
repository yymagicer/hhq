package com.hhq.codegeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hhq.codegeneration.mapper")
public class CodeGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenerationApplication.class, args);
    }

}
