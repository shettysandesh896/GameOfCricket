package com.tekion.GameOfCricket.service;

import org.springframework.stereotype.Service;

import com.tekion.GameOfCricket.entity.Match;


@Service
public interface MatchService {

    public Match playCricket(String teamOneId, String teamTwoId, int Over);
    
}
