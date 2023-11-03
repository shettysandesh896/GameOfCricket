package com.tekion.GameOfCricket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.GameOfCricket.input.Team;
import com.tekion.GameOfCricket.input.UserTeamInput;
import com.tekion.GameOfCricket.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@PostMapping("/add/Team")
	public List<Team> insertTeam(@RequestBody UserTeamInput userTeamInput) {
		return teamService.addTeam(userTeamInput);
	}

	@GetMapping("/get/AllTeams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping("/get/Team/{id}")
	public Team getTeamById(@PathVariable String id) {
		return teamService.getTeamById(id);
	}

}
