package com.tekion.GameOfCricket.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTeamInput {

	private Team teamOne;
	private Team teamTwo;

}
