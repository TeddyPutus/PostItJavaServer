package com.postItApi.postIt.post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.postItApi.postIt.user.User;
import com.postItApi.postIt.user.UserRepository;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    //analogous to user router in the OG project
    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostService postService, UserRepository userRepository){
        this.postService = postService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public List<Post> getPosts(){
        return postService.getAllPublicPosts();
        
    }

    @GetMapping("/public/{userId}")
    public List<Post> getPublicPostsOfUser(@PathVariable (value = "userId") Long userId){
        return postService.getAllPublicPostsOfUser(userId);
        
    }

    @GetMapping("/private/{userId}")
    public List<Post> getPrivatePostsOfUser(@PathVariable (value = "userId") Long userId){
        return postService.getAllPrivatePostsOfUser(userId);
        
    }

    @PostMapping("/{userId}")
    public Boolean createUser(@RequestBody Map<String,String> post, @PathVariable (value = "userId") Long userId){
        
       Optional<User> foundUser = userRepository.findById(userId);

       //firstly. check user actually exists
       if(foundUser.isPresent() == false){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }

        Post newPost = new Post(post.get("title"), post.get("content"), post.get("isPrivate").equals("true") ? true : false, foundUser.get());

        postService.createPost(newPost);

        return true;
    }

    @PutMapping("/{postId}")
    public Boolean updatePost(@PathVariable (value = "postId") Long postId, @RequestBody Map<String, String> updateDetails){
        
        Optional<User> foundUser = userRepository.findById(Long.parseLong(updateDetails.get("userID")));

        //firstly. check user actually exists
       if(foundUser.isPresent() == false){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }

        if(foundUser.get().getPassword().equals(updateDetails.get("password"))){
            postService.updatePost(postId, updateDetails.get("title"), updateDetails.get("content"), Boolean.parseBoolean(updateDetails.get("isPrivate")));
            return true;
        }
        return false;
    }

    // @CrossOrigin
    @DeleteMapping("/{postId}")
    public Boolean deletePost(@PathVariable (value = "postId") Long postId, @RequestBody Map<String, String> userDetails){
        Optional<User> foundUser = userRepository.findById(Long.parseLong(userDetails.get("userID")));

        //firstly. check user actually exists
       if(foundUser.isPresent() == false){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found");
        }

        if(foundUser.get().getPassword().equals(userDetails.get("password"))){
            postService.deletePost(postId);
            return true;
        }
        
        return true;
    }
}