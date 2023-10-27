package com.tekion.GameOfCricket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.Team;

@Service
public interface TeamService {

	public Team addTeam(Team team);

	public List<Team> getAllTeams();
	
	public Team getTeamById(String id);

}
