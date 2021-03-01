package com.mideros.diceGameMongo.dto;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player")
public class Player {

	@Id
	private String id;

	private String name_player;

	private Date date_register_player = new Date(System.currentTimeMillis());

	private double success_rate_player;

	public Player() {

	}

	public Player(String id, String name_player, Date date_register_player, double success_rate_player) {
		this.id = id;
		this.name_player = name_player;
		this.date_register_player = date_register_player;
		this.success_rate_player = success_rate_player;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_player() {
		return name_player;
	}

	public void setName_player(String name_player) {
		this.name_player = name_player;
	}

	public Date getDate_register_player() {
		return date_register_player;
	}

	public void setDate_register_player(Date date_register_player) {
		this.date_register_player = date_register_player;
	}

	public double getSuccess_rate_player() {
		return success_rate_player;
	}

	public void setSuccess_rate_player(double success_rate_player) {
		this.success_rate_player = success_rate_player;
	}

	public void updateSuccessRate(List<Game> gameList) {

		double sR = 0;

		for (Game pl : gameList) {
			if (pl.isWin_game()) {
				sR++;
			}
		}
		this.setSuccess_rate_player((sR / gameList.size()) * 100);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name_player == null) ? 0 : name_player.hashCode());
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
		Player other = (Player) obj;
		if (name_player == null) {
			if (other.name_player != null)
				return false;
		} else if (!name_player.equals(other.name_player))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name_player=" + name_player + ", date_register_player=" + date_register_player
				+ ", success_rate_player=" + success_rate_player + "]";
	}

}
