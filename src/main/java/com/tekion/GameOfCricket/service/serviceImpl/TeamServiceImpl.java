package com.tekion.GameOfCricket.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.exception.TeamExistsAlreadyException;
import com.tekion.GameOfCricket.exception.TeamNotFoundException;
import com.tekion.GameOfCricket.input.Team;
import com.tekion.GameOfCricket.input.UserTeamInput;
import com.tekion.GameOfCricket.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	private List<Team> teams = new ArrayList<Team>();

	@Override
	public List<Team> addTeam(UserTeamInput userTeamInput) {

		Team teamOne = userTeamInput.getTeamOne();
		Team teamTwo = userTeamInput.getTeamTwo();
		for (int i = 0; i < teams.size(); i++) {
			if (teams.get(i).getTeamId().equals(teamOne.getTeamId())
					|| teams.get(i).getTeamId().equals(teamTwo.getTeamId())) {
				throw new TeamExistsAlreadyException("Enter a different team! entered team already exists.");
			}
		}
		teams.add(teamOne);
		teams.add(teamTwo);
		return teams;
	}

	@Override
	public List<Team> getAllTeams() {
		if (!teams.isEmpty()) {
			return teams;
		} else {
			throw new TeamNotFoundException("No Teams Found! Please add a team.");
		}
	}

	@Override
	public Team getTeamById(String id) {
		return teams.stream().filter(t -> t.getTeamId().equals(id)).findFirst()
				.orElseThrow(() -> new TeamNotFoundException("Oh No! Your team is not added."));
	}

	@Override
	public Team deleteTeamById(String id) {
		// teams.removeIf(t -> t.getTeamId().equals(id));
		Team team = teams.stream().filter(t -> t.getTeamId().equals(id)).findFirst().orElseThrow(
				() -> new TeamNotFoundException("Could not find the team with given id! Please check added teams"));
		boolean teamRemovedFlag = teams.remove(team);
		System.out.println(teamRemovedFlag);
		return team;
	}

}
