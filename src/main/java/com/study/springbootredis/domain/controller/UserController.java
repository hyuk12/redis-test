package com.study.springbootredis.domain.controller;

import com.study.springbootredis.domain.model.User;
import com.study.springbootredis.domain.service.CacheService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
  private final CacheService cacheService;

  @Autowired
  public UserController(CacheService cacheService) {
    this.cacheService = cacheService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    return cacheService.getCachedData("user:" + id, User.class)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    user.setCreatedAt(LocalDateTime.now());
    cacheService.cacheData("user:" + user.getId(), user, 3600);
    return ResponseEntity.ok(user);
  }

}
