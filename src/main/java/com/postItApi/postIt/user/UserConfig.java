package com.postItApi.postIt.user;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.postItApi.postIt.post.Post;
import com.postItApi.postIt.post.PostRepository;

@Configuration
public class UserConfig {
    //database seeding goes here
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, PostRepository postRepository){
        return args -> {
            User user1 = new User("TeddyP", "thisisapassword", "teddy@email.com");

            Post post1 = new Post("myfirstpost", "hello all", false, user1);

            repository.save(user1);
            postRepository.save(post1);
        };    
    }
}
