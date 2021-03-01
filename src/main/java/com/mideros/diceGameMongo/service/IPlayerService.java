package com.mideros.diceGameMongo.service;

import java.util.List;

import com.mideros.diceGameMongo.dto.Player;

public interface IPlayerService {
	
	public List<Player> listPlayers();

	public Player savePlayer(Player player);

	public Player getPlayerById(String id_player);

	public Player updatePlayer(String id_player,Player player);

	public void deletePlayer(String id_player);

	public double averageRanking();

	public Player rankingLoser();

	public Player rankingWinner();
}
