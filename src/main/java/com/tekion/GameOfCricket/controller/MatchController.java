package com.tekion.GameOfCricket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.GameOfCricket.entity.Match;
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
	public ResponseEntity<Match> playMatch(@RequestParam String teamOneId, @RequestParam String teamTwoId,
			@RequestParam int Over) {

		return new ResponseEntity<>(matchService.playCricket(teamOneId, teamTwoId, Over), HttpStatus.OK);

	}

}
