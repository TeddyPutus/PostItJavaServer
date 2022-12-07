package com.postItApi.postIt.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    //analogous to user router in the OG project
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }


    @GetMapping()
    public List<Post> getPosts(){
        // return "Hello!";
        return postService.getAllPosts();
        
    }

    @PostMapping()
    public List<Post> createUser(@RequestBody Post post){
        postService.createPost(post);
        return postService.getAllPosts();
    }
}