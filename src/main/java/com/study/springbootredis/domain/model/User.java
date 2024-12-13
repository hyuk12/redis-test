package com.study.springbootredis.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  private Long id;
  private String username;
  private String email;
  @Setter
  private LocalDateTime createdAt;
}
