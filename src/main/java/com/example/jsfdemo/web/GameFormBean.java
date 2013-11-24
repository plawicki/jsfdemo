package com.example.jsfdemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jsfdemo.domain.Game;

import com.example.jsfdemo.service.GameManager;


@SessionScoped
@Named("gameBean")
public class GameFormBean implements Serializable 
{

	private static final long serialVersionUID = 1L;

	private Game game = new Game();

	private ListDataModel<Game> games = new ListDataModel<Game>();

	@Inject
	private GameManager gm;


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ListDataModel<Game> getAllGames() {
		games.setWrappedData(gm.getAllGames());
		return games;
	}

	// Actions
	
	public String addGame() {
		gm.addGame(game);
		return "showGames";
		//return null;
	}

	public String deleteGame() {
		Game gameToDelete = games.getRowData();
		gm.deleteGame(gameToDelete);
		return null;
	}
	
	public String findGamePIN()
	{
		Game g = games.getRowData();
		gm.getGamePIN(g);
		return null;
	}
	
	public String findGamesAdult()
	{
		
		gm.getGamesByAdult();
		return null;
	}
	
	public String findGamesNotAdult()
	{
		gm.getGamesByNotAdult();
		return null;
	}
	
	public String getGamesYop()
	{
		Game g = games.getRowData();
		gm.getGamesByYop(g.getYop());
		return null;
	}
	
	public String getGamesPrice()
	{
		Game g = games.getRowData();
		gm.getGamesByPrice(g.getPrice());
		return null;
	}	
	

	public void uniquePin(FacesContext context, UIComponent component,
			Object value) {

		String PIN = (String) value;

		for (Game g : gm.getAllGames()) {
			if (g.getPIN().equalsIgnoreCase(PIN)) {
				FacesMessage message = new FacesMessage( "Game with this PIN already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	// xxxx-year
	public void validatePinYop(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput PIN = (UIInput) form.findComponent("PIN");
		UIInput yop = (UIInput) form.findComponent("yop");

		if (PIN.getValue() != null && yop.getValue() != null
		 && PIN.getValue().toString().length() >= 4) 
		
		{
			String fourDigitsOfPin = PIN.getValue().toString().substring(4, 4);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) yop.getValue()));

			String lastDigitsOfYop = ((Integer) cal.get(Calendar.YEAR))
					.toString().substring(4);

			if (!fourDigitsOfPin.equals(lastDigitsOfYop)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"PIN doesn't match date of publication"));
				context.renderResponse();
			}
		}
	}
}
