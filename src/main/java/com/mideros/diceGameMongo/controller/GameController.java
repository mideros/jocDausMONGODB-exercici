package com.mideros.diceGameMongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mideros.diceGameMongo.dto.Game;
import com.mideros.diceGameMongo.dto.Player;
import com.mideros.diceGameMongo.exception.PlayerExistsException;
import com.mideros.diceGameMongo.exception.ResourceNotFoundException;
import com.mideros.diceGameMongo.service.IGameService;
import com.mideros.diceGameMongo.service.IPlayerService;

@RestController
@RequestMapping("/v1")
public class GameController {

	@Autowired
	private IGameService gameService;

	@Autowired
	private IPlayerService playerService;

	// roll the dice
	@PostMapping(value = { "/players/{id}/games" }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> rollDice(@PathVariable(value = "id") String idPlayer, @RequestBody Player player) {

		try {
			if (playerService.getPlayerById(idPlayer) != null) {
				return ResponseEntity.ok().body(gameService.rollDice(idPlayer, player));
			} else {
				throw new PlayerExistsException("Player " + idPlayer + " does not exist!!!");
			}
		} catch (PlayerExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Get games by player
	@GetMapping(value = { "/players/{id}/games" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Game> getGameByPlayerById(@PathVariable(value = "id") String idPlayer)
			throws ResourceNotFoundException {

		Player pById = playerService.getPlayerById(idPlayer);
		return gameService.getGameByPlayer(pById);
	}

	// Delete games by player
	@DeleteMapping("/players/{id}/games")
	public ResponseEntity<Object> deleteGames(@PathVariable(value = "id") String idPlayer) {

		if (playerService.getPlayerById(idPlayer) != null) {
			Player pById = playerService.getPlayerById(idPlayer);
			return ResponseEntity.ok().body(gameService.deleteGameByPlayer(pById));
		} else {
			throw new PlayerExistsException("Player " + idPlayer + " does not exist!!!");
		}
	}
}
