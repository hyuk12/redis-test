package com.study.springbootredis.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.study.springbootredis.domain.model.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheServiceTest {
  @Autowired
  private CacheService cacheService;

  @Test
  void cacheDataTest() {
    // given
    User user = new User(1L, "test", "test@gmail.com", LocalDateTime.now());

    // when
    cacheService.cacheData("user:1", user, 60);
    Optional<User> cachedUser = cacheService.getCachedData("user:1", User.class);

    // then
    assertTrue(cachedUser.isPresent());
    assertEquals(user.getUsername(), cachedUser.get().getUsername());
  }

}