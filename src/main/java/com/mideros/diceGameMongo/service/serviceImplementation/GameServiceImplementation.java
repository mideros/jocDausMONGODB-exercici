package com.mideros.diceGameMongo.service.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mideros.diceGameMongo.exception.PlayerExistsException;
import com.mideros.diceGameMongo.dto.Game;
import com.mideros.diceGameMongo.dto.Player;
import com.mideros.diceGameMongo.exception.ResourceNotFoundException;
import com.mideros.diceGameMongo.repository.IGameRepository;
import com.mideros.diceGameMongo.repository.IPlayerRepository;
import com.mideros.diceGameMongo.service.IGameService;

@Service
public class GameServiceImplementation implements IGameService {

	@Autowired
	IGameRepository gameRepository;

	@Autowired
	IPlayerRepository playerRepository;

	public GameServiceImplementation(IGameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public List<Game> listGames() {
		return gameRepository.findAll();
	}

	@Override
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Game getGameById(String id_game) {
		return gameRepository.findById(id_game).get();
	}

	@Override
	public Game updateGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public void deleteGame(String id_game) {
		gameRepository.deleteById(id_game);

	}

	@Override
	public Game rollDice(String id_player, Player player) {

		try {

			Player playerById = playerRepository.findById(id_player).get();

			List<Game> listGame = gameRepository.getGameByPlayer(playerById);

			Game gameP = new Game();

			gameP.setPlayer(playerById);

			listGame.add(gameP);

			playerById.updateSuccessRate(listGame);

			playerRepository.save(playerById);

			return gameRepository.save(gameP);

		} catch (Exception e) {

			throw new ResourceNotFoundException("It is not possible to roll the dice");
		}
	}

	@Override
	public List<Game> getGameByPlayer(Player player) {

		return gameRepository.getGameByPlayer(player);
	}

	@Override
	public String deleteGameByPlayer(Player player) {

		String idPlayer = player.getId();
		List<Game> listGameByPlayer = new ArrayList<Game>();

		try {

			if (gameRepository.findById(idPlayer) != null) {

				Player pById = playerRepository.findById(idPlayer).get();

				listGameByPlayer = gameRepository.getGameByPlayer(pById);

				gameRepository.deleteGameByPlayer(pById);

				pById.setSuccess_rate_player(0.0);

				playerRepository.save(pById);

				return (listGameByPlayer.size() + " records have been deleted");
			} else {
				throw new PlayerExistsException("Player " + idPlayer + " does not exist!!!");
			}
		} catch (Exception e) {
			throw new PlayerExistsException("Player " + idPlayer + " does not exist!!!");
		}
	}

}
