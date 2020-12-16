package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.Controller.Controller;
import Game.Controller.multiplicationGame.DropCardsThread;
import Game.View.MenuGUI;
import Game.View.theFourArithmeticGUI;

public class TestStartTSTA {
	private Controller controller;
	
	@Before
	public void setup(){
		controller=new Controller();
	}
	
	/**
	 * JOptionPanes will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void startSinglePlayer() {
		MenuGUI menuGui=new MenuGUI(controller);
		theFourArithmeticGUI arithmeticGUI=menuGui.disposeAndStartTheFourArithmeticGUI();
		assertTrue(arithmeticGUI.startSinglePlayer() instanceof DropCardsThread);
		
		
	}
	
	/**
	 * JOptionPanes will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void startMultiPlayer() {
		MenuGUI menuGui=new MenuGUI(controller);
		controller.getPlayer1LoginGui().setTxtUsername("Bosse");
		controller.getPlayer1LoginGui().checkAndSetUserName();
		controller.getPlayer1LoginGui().dispose();
		menuGui.startMultiPlayerGame();
		controller.createUser();
		controller.getPlayer2LoginGui().setTxtUsername("Lisa");
		controller.getPlayer2LoginGui().checkAndSetUserName();
		controller.getBoardGui().dispose();
		assertNotNull(controller.getBoardGui());
		
		
		
		
		
	}

}
