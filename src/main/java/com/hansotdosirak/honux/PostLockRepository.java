package com.hansotdosirak.honux;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface PostLockRepository extends JpaRepository<Post,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Post> findById(Long postId);
}
