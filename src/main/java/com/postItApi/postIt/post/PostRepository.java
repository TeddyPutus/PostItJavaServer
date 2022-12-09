package com.postItApi.postIt.post;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByIsPrivate(Boolean isPrivate);
    public Optional<Post> findById(Long id);

    public List<Post> findAllByUserIdAndIsPrivate(Long id, Boolean isPrivate);
}
