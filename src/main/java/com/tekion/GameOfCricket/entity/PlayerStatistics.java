package com.tekion.GameOfCricket.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.tekion.GameOfCricket.model.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "PlayerStatistics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PlayerStatistics {
	@Id
	private String id;
	private List<Player> allPlayerList;
}
