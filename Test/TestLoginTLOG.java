package Test;
import Game.Controller.*;
import Game.Model.User;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoginTLOG {
	private Controller controller;
	
	@Before
	public void setup(){
		controller=new Controller();
	}
	
	/**
	 * JOptionPane will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void UserNameUnder3CharsShouldBeNullUser() { 
		controller.getPlayer1LoginGui().setTxtUsername("Bo");
		controller.getPlayer1LoginGui().checkAndSetUserName();
		controller.getPlayer1LoginGui().dispose();
		
		assertEquals(null,controller.getMultiplayer()[0]);
		
	}
	
	/**
	 * JOptionPane will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void UserNameShouldBeSameAsInputString() { 
		controller.getPlayer1LoginGui().setTxtUsername("Nisse-Hult");
		controller.getPlayer1LoginGui().checkAndSetUserName();
		controller.getPlayer1LoginGui().dispose();
		assertEquals("Nisse-Hult",controller.getMultiplayer()[0].getUserName());
	}
	
	/**
	 * JOptionPane will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void UserNameTooLongShouldBeNullUser() { 
		controller.getPlayer1LoginGui().setTxtUsername("Kajsa Frans");
		controller.getPlayer1LoginGui().checkAndSetUserName();
		controller.getPlayer1LoginGui().dispose();
		assertEquals(null,controller.getMultiplayer()[0]);	
	}
	
	/**
	 * JOptionPane will need to be closed manually before the test can finish
	 * This is a side effect of the static method invocation showmessagedialog
	 * It is not possible to close from outside the pane itself
	 * 
	 */
	@Test
	public void NoUserNameShouldBeNullUser() { 
		controller.getPlayer1LoginGui().setTxtUsername("");
		controller.getPlayer1LoginGui().checkAndSetUserName();
		controller.getPlayer1LoginGui().dispose();
		assertEquals(null,controller.getMultiplayer()[0]);	
	}

}
