package com.tekion.GameOfCricket.response;

import org.springframework.stereotype.Component;

import com.tekion.GameOfCricket.model.TeamScoreboardOne;
import com.tekion.GameOfCricket.model.TeamScoreboardTwo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MatchResult {
	TeamScoreboardOne teamOne;
	TeamScoreboardTwo teamTwo;
	String winner;

	public String winner() {
		if (teamOne.getRunsScored() > teamTwo.getRunsScored()) {
			return teamOne.getTeamName() + " won the match!!";
		} else if (teamOne.getRunsScored() < teamTwo.getRunsScored()) {
			return teamTwo.getTeamName() + " won the match!!";
		} else {
			return "its a draw";
		}
	}

}