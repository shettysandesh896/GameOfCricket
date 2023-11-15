package com.tekion.GameOfCricket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.GameOfCricket.entity.Scorecard;
import com.tekion.GameOfCricket.model.Player;
import com.tekion.GameOfCricket.response.MatchResult;
import com.tekion.GameOfCricket.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {

	private final MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@PostMapping(value = "/play")
	public ResponseEntity<MatchResult> playMatch(@RequestParam String matchNumber, @RequestParam String teamOneId,
			@RequestParam String teamTwoId, @RequestParam int Over) {

		return new ResponseEntity<>(matchService.playCricket(matchNumber, teamOneId, teamTwoId, Over), HttpStatus.OK);

	}

	@GetMapping(value = "/get/player/stats/{playerName}")
	public ResponseEntity<Player> getPlayerStats(@PathVariable String playerName, @RequestParam String matchNumber) {

		return new ResponseEntity<>(matchService.getPlayerStats(playerName, matchNumber), HttpStatus.OK);
	}

	@GetMapping(value = "/result/{matchNumber}")
	public ResponseEntity<Scorecard> getmatchResult(@PathVariable String matchNumber) {

		return new ResponseEntity<>(matchService.getMatchResult(matchNumber), HttpStatus.OK);
	}

}
