package com.tekion.GameOfCricket.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.Team;
import com.tekion.GameOfCricket.exception.TeamExistsAlreadyException;
import com.tekion.GameOfCricket.exception.TeamNotFoundException;
import com.tekion.GameOfCricket.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	private List<Team> teams = new ArrayList<Team>();

	@Override
	public Team addTeam(Team team) {

		if (teams.contains(team)) {
			throw new TeamExistsAlreadyException("Enter a different team! entered team already exists.");
		}
		teams.add(team);
		return team;
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

//		List<Team> team = teams.stream().filter(t -> t.getTeamId().equals(id)).collect(Collectors.toList());
//
//        if(!team.isEmpty())
//        {
//        	return team.get(0);
//        }
//        else {
//        	throw new TeamNotFoundException("Oh No! Your team is not added.");
//        }

		return teams.stream().filter(t -> t.getTeamId().equals(id)).findFirst()
				.orElseThrow(() -> new TeamNotFoundException("Oh No! Your team is not added."));
	}

}
