package com.yonyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@SpringBootApplication
@EnableSwagger2
@EnableScheduling 
public class BootMain {

	public static void main(String[] args) {
		SpringApplication.run(BootMain.class, args);
	}
	@RequestMapping("/help")
	public String help() {
		return "redirect:swagger-ui.html";
	}
}
