package com.tekion.GameOfCricket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
	String playerName;
	String playerType;
	int runsScored;
}
