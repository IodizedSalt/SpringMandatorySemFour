package com.mandatory.semfour.repository;

import com.mandatory.semfour.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    public Post findPostById(int id);
}
