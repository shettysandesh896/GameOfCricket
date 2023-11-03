package com.tekion.GameOfCricket.model;

import java.util.List;

import com.tekion.GameOfCricket.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TeamScoreboard {
	String teamId;
	String teamName;
	int runsScored;
	String overs;
	int wickets;
	List<Player> scoreCard;
}
