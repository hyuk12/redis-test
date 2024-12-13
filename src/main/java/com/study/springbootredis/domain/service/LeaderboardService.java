package com.study.springbootredis.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeaderboardService {
  private final RedisTemplate<String, String> redisTemplate;
  private static final String LEADERBOARD_KEY = "game:leaderboard";

  @Autowired
  public LeaderboardService(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void addScore(String username, double score) {
    try {
      redisTemplate.opsForZSet().add(LEADERBOARD_KEY, username, score);
      log.info("Score added: username={}, score={}", username, score);
    } catch (Exception e) {
      log.error("Failed to add score: username={}, score={}", username, score, e);
    }
  }

  public List<String> getTopPlayers(int count) {
    try {
      Set<String> topScore = redisTemplate.opsForZSet().reverseRange(LEADERBOARD_KEY, 0, count - 1);
      return new ArrayList<>(topScore != null ? topScore : Collections.emptySet());
    } catch (Exception e) {
      log.error("Failed to get top players: count={}", count, e);
      return List.of();
    }
  }

  public Long getUserRank(String username) {
    try {
      return redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, username);
    } catch (Exception e) {
      log.error("Failed to get user rank: username={}", username, e);
      return null;
    }
  }

  public Double getUserScore(String username) {
    try {
      return redisTemplate.opsForZSet().score(LEADERBOARD_KEY, username);
    } catch (Exception e) {
      log.error("Failed to get user score: username={}", username, e);
      return null;
    }
  }
}
