package com.tekion.GameOfCricket.service;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.Scorecard;
import com.tekion.GameOfCricket.model.Player;
import com.tekion.GameOfCricket.response.MatchResult;

@Service
public interface MatchService {

	public MatchResult playCricket(String matchNumber, String teamOneId, String teamTwoId, int Over);

	public Player getPlayerStats(String playerName, String matchNumber);

	public Scorecard getMatchResult(String matchNumber);

}
