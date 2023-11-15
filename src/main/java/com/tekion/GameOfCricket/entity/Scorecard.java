package com.tekion.GameOfCricket.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.tekion.GameOfCricket.response.MatchResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Scorecard")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Scorecard {
	@Id
	private String matchNumber;
	private MatchResult matchResult;

}
