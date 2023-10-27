package com.tekion.GameOfCricket.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.Match;
import com.tekion.GameOfCricket.entity.Team;
import com.tekion.GameOfCricket.service.MatchService;
import com.tekion.GameOfCricket.service.TeamService;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	TeamService teamService;

	@Override
	public Match playCricket(String teamOneId, String teamTwoId, int over) {

		Team teamOne = teamService.getTeamById(teamOneId);
		Team teamTwo = teamService.getTeamById(teamTwoId);

		Match match = new Match();
		match.setMatchName(teamOne.getTeamName() + " vs " + teamTwo.getTeamName());
		match.setTeamOne(teamOne.getTeamName());
		match.setTeamTwo(teamTwo.getTeamName());

		int ballsPerInnings = over * 6;

		// calling letsPlay with teamOne as Batting team.
		int[] teamOneScore = letsPlay(match, teamOne, teamTwo, ballsPerInnings);
		// calling letsPlay with teamTwo as Batting team.
		int[] teamTwoScore = letsPlay(match, teamTwo, teamOne, ballsPerInnings);

		int teamOneRuns = teamOneScore[0];
		int teamOneWickets = teamOneScore[1];
		int teamTwoRuns = teamTwoScore[0];
		int teamTwoWickets = teamTwoScore[1];

		match.setTeamOneScoredRuns(teamOneRuns);
		match.setTeamOneWickets(teamOneWickets);
		match.setTeamTwoScoredRuns(teamTwoRuns);
		match.setTeamTwoWickets(teamTwoWickets);

		String outcome = null;

		if (teamOneRuns > teamTwoRuns) {
			outcome = teamOne.getTeamName();
			match.setMatchOutcome(outcome + " won");
		} else if (teamTwoRuns > teamOneRuns) {
			outcome = teamTwo.getTeamName();
			match.setMatchOutcome(outcome + " won");

		} else {
			outcome = "It's a draw!";
			match.setMatchOutcome(outcome);

		}
		return match;
	}

	private int[] letsPlay(Match match, Team teamBatting, Team teamBowling, int ballsPerInnings) {

		int totalScore = 0;
		int wicketCount = 0;
		int maxWickets = 11;

		int[] teamScore = new int[10];

		for (int ball = 1; ball <= ballsPerInnings; ball++) {
			if (wicketCount == maxWickets) {
				break;
			}

			int perBallScore = (int) (Math.random() * 8);
			if (perBallScore == 7) {
				wicketCount++;

			} else {
				totalScore += perBallScore;
			}
		}
		teamScore[0] = totalScore;
		teamScore[1] = wicketCount;
		return teamScore;
	}

}
