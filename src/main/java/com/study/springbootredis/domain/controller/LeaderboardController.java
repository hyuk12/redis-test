package com.study.springbootredis.domain.controller;

import com.study.springbootredis.domain.service.LeaderboardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
  private final LeaderboardService leaderboardService;

  @Autowired
  public LeaderboardController(LeaderboardService leaderboardService) {
    this.leaderboardService = leaderboardService;
  }

  @PostMapping("/scores")
  public ResponseEntity<Void> addScore(
      @RequestParam String username,
      @RequestParam double score) {
      leaderboardService.addScore(username, score);
      return ResponseEntity.ok().build();
  }

  @GetMapping("/top/{count}")
  public ResponseEntity<List<String>> getTopPlayers(@PathVariable int count) {
    return ResponseEntity.ok(leaderboardService.getTopPlayers(count));
  }

  @GetMapping("/rank/{username}")
  public ResponseEntity<Long> getUserRank(@PathVariable String username) {
    return ResponseEntity.ok(leaderboardService.getUserRank(username));
  }

}
