package com.postItApi.postIt;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postItApi.postIt.user.User;

@SpringBootApplication
// @RestController
public class PostItApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostItApplication.class, args);
	}

	// @GetMapping("/hello") //this now works
	// public List<User> hello(@RequestParam(name="name", defaultValue = "World") String name){
	// 	// return String.format("Hello %s!", name);

	// 	return List.of(
	// 	new User(1L,"TeddyP", "thisisapassword", "teddy@email.com")
	// 	);
		
	// }

}
