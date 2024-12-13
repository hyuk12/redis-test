package com.study.springbootredis.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeaderboardServiceTest {
  @Autowired
  private LeaderboardService leaderboardService;

  @Test
  void leaderboardOperationTest() {
    // given
    String username = "test";
    double score = 100.0;

    // when
    leaderboardService.addScore(username, score);
    Long rank = leaderboardService.getUserRank(username);
    Double userScore = leaderboardService.getUserScore(username);
    List<String> topPlayers = leaderboardService.getTopPlayers(1);

    // then
    assertFalse(topPlayers.isEmpty());
    assertEquals(username, topPlayers.getFirst());
    assertEquals(0, rank);
  }
}