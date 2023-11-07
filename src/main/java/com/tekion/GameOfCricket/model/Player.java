package com.tekion.GameOfCricket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
	private String playerName;
	private String playerRole;
	private int runsScored;
	private String teamName;
}
