package com.mideros.diceGameMongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public class Game {

	@Id
	private String id;

	private int dice_one;
	private int dice_two;
	private boolean win_game;

	@DBRef
	private Player player;

	public Game() {

		this.dice_one = generateDiceValue();
		this.dice_two = generateDiceValue();
		this.win_game = checkDiceRoll(dice_one, dice_two);

	}

	public Game(String id, Player player) {

		this.id = id;
		this.dice_one = generateDiceValue();
		this.dice_two = generateDiceValue();
		this.win_game = checkDiceRoll(dice_one, dice_two);
		this.player = player;
	}

	public String getId_game() {
		return id;
	}

	public void setId_game(String id) {
		this.id = id;
	}

	public int getDice_one() {
		return dice_one;
	}

	public void setDice_one(int dice_one) {
		this.dice_one = dice_one;
	}

	public int getDice_two() {
		return dice_two;
	}

	public void setDice_two(int dice_two) {
		this.dice_two = dice_two;
	}

	public boolean isWin_game() {
		return win_game;
	}

	public void setWin_game(boolean win_game) {
		this.win_game = win_game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int generateDiceValue() {

		int diceValue = (int) Math.floor(Math.random() * 6 + 1);

		return diceValue;
	}

	public boolean checkDiceRoll(int dice_one, int dice_two) {

		boolean checkDice = (dice_one + dice_two) == 7 ? true : false;

		return checkDice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", dice_one=" + dice_one + ", dice_two=" + dice_two + ", win_game=" + win_game
				+ ", player=" + player + "]";
	}
}