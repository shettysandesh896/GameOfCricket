package com.tekion.GameOfCricket.input;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {

	private String teamId;
	private String teamName;
	private ArrayList<String> batsmen;
	private ArrayList<String> bowlers;

}
