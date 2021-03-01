package com.mideros.diceGameMongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mideros.diceGameMongo.dto.Player;

@Repository
public interface IPlayerRepository extends MongoRepository<Player, String> {

}
