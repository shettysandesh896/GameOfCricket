package com.tekion.GameOfCricket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.input.Team;
import com.tekion.GameOfCricket.input.UserTeamInput;

@Service
public interface TeamService {

	public List<Team> addTeam(UserTeamInput userTeamInput);

	public List<Team> getAllTeams();

	public Team getTeamById(String id);

}
