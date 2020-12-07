package Game.Controller;

import java.util.Arrays;

import Game.Model.User;
import Game.View.HighScoreGUI;

public class HighScoreController {
	private HighScoreController hsController;
	private HighScoreGUI hsGUI;
	private User[] highscoreList;
	private String highscoreSave;
	private User currentUser;

	public HighScoreController() {
		highscoreList = new User[] {new User(),new User(),new User() };
		//hsGUI.setHighscorePanel(highscoreList);
	}
	
	public void showGUI(HighScoreGUI hsGUI) {
		this.hsGUI = hsGUI;
	}
	
	public void getUser(User[] players) {
		for(int i = 0; i< players.length-1; i++) {
			currentUser = players[i];
			checkHighScore();
		}
	}
	
	private void checkHighScore() {
		if(currentUser.getTotalPoints() >= highscoreList[highscoreList.length-1].getTotalPoints()) {
			highscoreList[9]=currentUser;
			Arrays.sort(highscoreList);
			hsGUI.updateHighscore(highscoreList);
		}
	}
	
	public HighScoreController getController() {
		return this.hsController;
	}

}
