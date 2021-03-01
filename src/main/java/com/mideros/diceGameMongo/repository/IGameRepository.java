package com.mideros.diceGameMongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mideros.diceGameMongo.dto.Game;
import com.mideros.diceGameMongo.dto.Player;

@Repository
public interface IGameRepository extends MongoRepository<Game, String> {

	public List<Game> getGameByPlayer(Player player);

	public void deleteGameByPlayer(Player player);

}
