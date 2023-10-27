package com.tekion.GameOfCricket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.GameOfCricket.entity.Team;
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
	public Team insertTeam(@RequestBody Team team) {
		return teamService.addTeam(team);
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
