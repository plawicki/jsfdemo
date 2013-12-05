package com.example.jsfdemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.jsfdemo.domain.Game;

@ApplicationScoped
public class GameManager {
	private List<Game> db = new ArrayList<Game>();

	public void addGame(Game game) {
		Game newGame = new Game();
		
		newGame.setPIN(game.getPIN());
		newGame.setTitle(game.getTitle());
		newGame.setPrice(game.getPrice());
		newGame.setYop(game.getYop());
		newGame.setRequireAdult(game.isRequireAdult());

		db.add(newGame);
	}

	// Removes the game with given Title
	public void deleteGame(Game game) {
		Game gameToRemove = null;
		for (Game g : db) {
			if (game.getTitle().equals(g.getTitle())) {
				gameToRemove = g;
				break;
			}
		}
		if (gameToRemove != null)
			db.remove(gameToRemove);
	}

	public List<Game> getAllGames() {
		return db;
	}
	
	// Games with adult requirments
	public List<Game> getGamesByAdult()
	{
		List<Game> gameByAdult = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.isRequireAdult())
				gameByAdult.add(g);
		}
		return gameByAdult;
	}
	
	// Games without adult requirments
	public List<Game> getGamesByNotAdult()
	{
		List<Game> gameByNotAdult = new ArrayList<Game>();
		
		for (Game g : db) {
			if (!g.isRequireAdult()) {
				gameByNotAdult.add(g);
			}
		}
		return gameByNotAdult;

	}
	
	// Find game with given title
	public Game getGameByTitle(Game game) {

		for (Game g : db) {
			if (game.getTitle().equals(g.getTitle())) {
				return g;
			}
		}

		return null;
	}
	
	// Find game with given PIN
		public Game getGamePIN(Game game) {

			for (Game g : db) {
				if (game.getPIN().equals(g.getPIN())) {
					return g;
				}
			}

			return null;
		}
	
	// Games by year of publication
	public List<Game> getGamesByYop(Date yop)
	{
		List<Game> gameByYop = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.getYop().equals(yop))
				gameByYop.add(g);
		}
		return gameByYop;
	}
	
	// Games by price
	public List<Game> getGamesByPrice(double price)
	{
		List<Game> gameByPrice = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.getPrice() == price)
				gameByPrice.add(g);
		}
		return gameByPrice;
	}
}
