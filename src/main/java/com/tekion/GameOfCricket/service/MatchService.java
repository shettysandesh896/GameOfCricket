package com.tekion.GameOfCricket.service;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.response.MatchResult;

@Service
public interface MatchService {

	public MatchResult playCricket(String teamOneId, String teamTwoId, int Over);

}
