package com.mideros.diceGameMongo.service;

import java.util.List;

import com.mideros.diceGameMongo.dto.Game;
import com.mideros.diceGameMongo.dto.Player;


public interface IGameService {
	
	public List<Game> listGames();

	public Game saveGame(Game game);

	public Game getGameById(String id_game);

	public Game updateGame(Game game);

	public void deleteGame(String id_game);
	
	public Game rollDice(String id_player, Player player);

	public List<Game> getGameByPlayer(Player player);

	public String deleteGameByPlayer(Player player);

}
