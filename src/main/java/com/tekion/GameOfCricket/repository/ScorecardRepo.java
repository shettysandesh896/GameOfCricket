package com.tekion.GameOfCricket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tekion.GameOfCricket.entity.Scorecard;

@Repository
public interface ScorecardRepo extends MongoRepository<Scorecard, String> {

}
