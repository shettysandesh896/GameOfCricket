package com.tekion.GameOfCricket.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.PlayerStatistics;
import com.tekion.GameOfCricket.entity.Scorecard;
import com.tekion.GameOfCricket.input.Team;
import com.tekion.GameOfCricket.model.Player;
import com.tekion.GameOfCricket.model.TeamScoreboard;
import com.tekion.GameOfCricket.model.TeamScoreboardOne;
import com.tekion.GameOfCricket.model.TeamScoreboardTwo;
import com.tekion.GameOfCricket.repository.PlayerRepo;
import com.tekion.GameOfCricket.repository.ScorecardRepo;
import com.tekion.GameOfCricket.response.MatchResult;
import com.tekion.GameOfCricket.service.MatchService;
import com.tekion.GameOfCricket.service.TeamService;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	TeamService teamService;
	@Autowired
	TeamScoreboardOne teamOneResponse;
	@Autowired
	TeamScoreboardTwo teamTwoResponse;
	@Autowired
	MatchResult matchResult;
	@Autowired
	Scorecard scoreCard;
	@Autowired
	PlayerStatistics playerStatistics;
	@Autowired
	ScorecardRepo scorecardRepo;
	@Autowired
	PlayerRepo playerRepo;

	@Override
	public MatchResult playCricket(String teamOneId, String teamTwoId, int over) {

		Team teamOne = teamService.getTeamById(teamOneId);
		Team teamTwo = teamService.getTeamById(teamTwoId);

//		Match match = new Match();
//		match.setMatchName(teamOne.getTeamName() + " vs " + teamTwo.getTeamName());
//		match.setTeamOne(teamOne.getTeamName());
//		match.setTeamTwo(teamTwo.getTeamName());

		// Defining each player as Batsman or Bowler...
		Map<String, String> definedRoleTeamOne = teamSquad(teamOne.getBatsmen(), teamOne.getBowlers());
		Map<String, String> definedRoleTeamTwo = teamSquad(teamTwo.getBatsmen(), teamTwo.getBowlers());
		// Batting eleven list of each team
		List<String> batterListTeamOne = playingElevenList(teamOne.getBatsmen(), teamOne.getBowlers());
		List<String> batterListTeamTwo = playingElevenList(teamTwo.getBatsmen(), teamTwo.getBowlers());

		// int ballsPerInnings = over * 6;

		// Assigning team one to bat first and team two to bowl first...
		String firstBatting = teamOne.getTeamName();
		String secondBatting = teamTwo.getTeamName();

		// Result of teamOneBatting
		TeamScoreboardOne teamOneResult = (TeamScoreboardOne) letsPlay(teamOneId, definedRoleTeamOne, batterListTeamOne,
				teamOneResponse, firstBatting, over);
		// Result of teamTwoBatting
		TeamScoreboardTwo teamTwoResult = (TeamScoreboardTwo) letsPlay(teamTwoId, definedRoleTeamTwo, batterListTeamTwo,
				teamTwoResponse, secondBatting, over);

		matchResult.setTeamOne(teamOneResult);
		matchResult.setTeamTwo(teamTwoResult);
		matchResult.setWinner(matchResult.winner());

		// Setting scorecard of the match to save
		scoreCard.setMatchResult(matchResult);

		// Statistics of all players
		List<Player> temp = new LinkedList<>();
		temp.addAll(matchResult.getTeamOne().getScoreCard());
		temp.addAll(matchResult.getTeamTwo().getScoreCard());

		playerStatistics.setAllPlayerList(temp);

		// Saving player statistics and Scorecard in MongoDB
		playerRepo.save(playerStatistics);
		scorecardRepo.save(scoreCard);

		return matchResult;

	}

	private TeamScoreboard letsPlay(String teamId, Map<String, String> definedRole, List<String> batterList,
			TeamScoreboard teamScoreboard, String teamName, int over) {

		int totalScore = 0;
		int wicketCount = 0;
		int maxWickets = 10;
		int overs = 0;
		int balls = 0;
		int playerBattingOrder = 0;

		// Storing each player data...
		List<Player> playerDataList = new LinkedList<>();

		// To check whether the team is all out...
		boolean gotAllOut = false;

		// getting details of first player to Bat..
		String playerName = batterList.get(playerBattingOrder);
		String playerRole = definedRole.get(playerName);
		int playerRuns = 0;

		// Outer loop for number of overs..
		// Inner loop for six balls for each over...
		Outerloop: for (int i = 1; i <= over; i++) {
			for (int j = 1; j <= 6; j++) {
				balls++;
				// break if team is all out...
				if (wicketCount == maxWickets) {
					gotAllOut = true;
					break Outerloop;
				}
				int perBallScore = (int) (Math.random() * 8);

				// when result of ball is 7(i.e Wicket)...
				if (perBallScore == 7) {
					playerDataList.add(new Player(playerName, playerRole, playerRuns, teamName));
					wicketCount++;
					playerBattingOrder++;
					if (playerBattingOrder <= 9) {
						playerName = batterList.get(playerBattingOrder);
						playerRole = definedRole.get(playerName);
						playerRuns = 0;
					}
				} else {
					playerRuns += perBallScore;
					totalScore += perBallScore;
				}
			}
			// increase overs after 6 ball
			balls = 0;
			overs++;
		}
		// increase overs if ball bowled is 6 in case of team all out...
		if (balls == 6) {
			overs++;
			balls = 0;
		}

		// for adding the runs of last playing batsmen if the team is not all out
		if (!gotAllOut) {
			playerDataList.add(new Player(playerName, playerRole, playerRuns, teamName));
		}

		String total_overs = overs + "." + balls;

		// Setting the team ScoreBoard
		teamScoreboard.setTeamId(teamId);
		teamScoreboard.setTeamName(teamName);
		teamScoreboard.setRunsScored(totalScore);
		teamScoreboard.setOvers(total_overs);
		teamScoreboard.setWickets(wicketCount);
		teamScoreboard.setScoreCard(playerDataList);

		return teamScoreboard;
	}

	// Method to define each player as batsmen or bowler
	public Map<String, String> teamSquad(ArrayList<String> batsmen, ArrayList<String> bowlers) {
		Map<String, String> teamLineUp = new HashMap<>();
		for (String player : batsmen)
			teamLineUp.put(player, "BatsMen");
		for (String player : bowlers)
			teamLineUp.put(player, "Bowler");
		return teamLineUp;
	}

	// Playing xi list to bat
	public List<String> playingElevenList(ArrayList<String> batsmen, ArrayList<String> bowlers) {
		List<String> team = new ArrayList<>();
		team.addAll(batsmen);
		team.addAll(bowlers);
		return team;
	}
}
