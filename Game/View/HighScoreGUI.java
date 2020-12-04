package Game.View;

import java.awt.Dimension;

import javax.swing.*;

import Game.Model.User;

public class HighScoreGUI extends JFrame {
	private JLabel [] scoreList = new JLabel[10];
	private JLabel highscoreList = new JLabel();
	private JPanel highscorePanel = new JPanel();
	private ImageIcon iconHs = new ImageIcon("images/Highscore.jpg");
	private JLabel highscorePicter = new JLabel(iconHs);
	private JFrame frame = new JFrame();
	
	public HighScoreGUI() {
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(highscorePanel);
		
		
		highscorePanel.setLayout(new BoxLayout(highscorePanel, BoxLayout.PAGE_AXIS));
		highscorePanel.add(highscorePicter);
		highscorePanel.add(highscoreList);
		highscorePanel.setBounds(0, 30, 500, 500);
		highscoreList.setVerticalAlignment(JLabel.CENTER);
		highscoreList.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	
	public void updateHighscore(User[] highScoreList) {
		for(int i=0;i<scoreList.length;i++) {
			scoreList[i].setText(highScoreList[i].getUserName()+": " + highScoreList[i].getTotalPoints());	
		}
		repaint();
	}
	
	/**
	 * 
	 * Sets the highscorePanel. Should only be used on startup. 
	 * @param highScoreList
	 */
	public void setHighscorePanel(User[] highScoreList) {
		for(int i=0;i<scoreList.length;i++) {
			scoreList[i]=new JLabel(highScoreList[i].getUserName()+": " + highScoreList[i].getTotalPoints());
			scoreList[i].setVerticalAlignment(JLabel.CENTER);
			scoreList[i].setHorizontalAlignment(JLabel.CENTER);
			scoreList[i].setMinimumSize(new Dimension(300,100));
			scoreList[i].setMaximumSize(new Dimension(300,100));
			highscorePanel.add(scoreList[i]);
			
		}
		repaint();
	}

}
