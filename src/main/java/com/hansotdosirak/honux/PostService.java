package com.hansotdosirak.honux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PostRepository postRepository;
    private final PostLockRepository postLockRepository;

    public PostService(PostRepository postRepository, PostLockRepository postLockRepository) {
        this.postRepository = postRepository;
        this.postLockRepository = postLockRepository;
    }

    @Transactional
    public void updateLikeCountBaseLine(Long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional
    public void updateLikeCountV1LockOnly(Long postId) {
        Optional<Post> byId = postLockRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateLikeCountV2IsolationOnly(Long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional
    public void updateLikeCountV3Atomic(Long postId) {
        postRepository.updateAtomic(postId);
    }
}
