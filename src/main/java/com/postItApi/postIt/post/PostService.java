package com.postItApi.postIt.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> getAllPublicPosts(){
        return postRepository.findByIsPrivate(false);
    }

    public List<Post> createPost(Post post){
        
        postRepository.save(post);
        return postRepository.findAll(); 
    }


    public List<Post> getAllPublicPostsOfUser(Long id){
        return postRepository.findAllByUserIdAndIsPrivate(id, false);
    }

    public List<Post> getAllPrivatePostsOfUser(Long id){
        return postRepository.findAllByUserIdAndIsPrivate(id, true);
    }

    public void updatePost(Long postId, String title, String content, Boolean isPrivate){
        Optional<Post> foundPost = postRepository.findById(postId);

        Post postToUpdate = foundPost.get();
        postToUpdate.setTitle(title);
        postToUpdate.setContent(content);
        postToUpdate.setIsPrivate(isPrivate);
        postRepository.save(postToUpdate);
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}