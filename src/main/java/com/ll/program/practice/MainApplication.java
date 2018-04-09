package com.ll.program.practice;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ll.program.practice.utils.MapUtils;

@SpringBootApplication
@ComponentScan("com.ll")
@RestController
public class MainApplication {
	
	@RequestMapping("/hello.do")
	public Map<String, Object> hello() {
		return MapUtils.createSuccessMap("msg", "hello world!");
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MainApplication.class);
		app.run(args);
	}
}
