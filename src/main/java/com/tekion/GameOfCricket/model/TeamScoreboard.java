package com.tekion.GameOfCricket.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TeamScoreboard {
	private String teamId;
	private String teamName;
	private int runsScored;
	private String overs;
	private int wickets;
	private List<Player> scoreCard;
}
