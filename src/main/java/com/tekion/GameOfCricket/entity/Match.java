package com.tekion.GameOfCricket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Match {
	private String matchName;
	private String teamOne;
	private String teamTwo;
	private String MatchOutcome;
}
