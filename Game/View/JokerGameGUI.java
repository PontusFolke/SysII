package Game.View;

import Game.Controller.multiplicationGame.CardDropTask;
import Game.View.BoardGUI.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Contains the main parts of the joker bonus round and/or the single player game.
 * The GUI paints up a multiplication game where card drops are falling down the screen.
 *
 * @author Robert Rosencrantz
 * @version 4.0
 */
public class JokerGameGUI extends JFrame {

    private JPanel pnlMain;
    private JPanel pnlTyping;
    private JPanel pnlGame;
    private JPanel pnlRight;
    private JLabel lblHelp;
    private JTextField textFieldPoints;
    private JLabel labelTyping;
    private JLabel lblTwoPoints;
    private JLabel lblFinalPoints;
    private String answerTyped = "";
    private String test = "";
    private boolean isHelpBtnPressed=false;
    private ImageIcon iconHelp = new ImageIcon("images/questionMark1.jpg");
    private JButton btnInstructions = new JButton(iconHelp);
    private HelpGUI helpGUI;

    /**
     * Construct and initialize the GUI.
     */
    public JokerGameGUI() {
        setupGamePanel();
        
        setupTypePanel();
        setupRightPanel();
        
        setupMainPanel();
        
        setupTwoPointsLabel();
        setupFinalPointsLabel();
        setupFrame();
        xButtonPressed();
    }
    
    public boolean isHelBtnPressed() {
    	
    	return isHelpBtnPressed;
    }
    public void helpGuiClosed() {
    	labelTyping.getTopLevelAncestor().requestFocus();
    	isHelpBtnPressed=false;
    }
    /**
     * Updates the GUI by adding a new card drop on the game panel.
     *
     * @param drop The card drop to add on GUI.
     */
    public void addDropToGamePanel(CardDropTask drop) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pnlGame.add(drop);
            }
        });
    }

    /**
     * Update the view with current score.
     *
     * @param points the current score
     */
    public void setTextFieldPoints(int points) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textFieldPoints.setText(points + "p");
                addTwoPointsThread();
            }
        });
    }

    /**
     * Show final points in large numbers after game ends.
     */
    public void addPointsText() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lblFinalPoints.setText("GRATTIS: " + textFieldPoints.getText());
                lblFinalPoints.setVisible(true);
            }
        });
    }

    /**
     * Called to compare user input to problems that are visible in the GUI.
     *
     * @return string representation of a numeric value.
     */
    public String getAnswerTyped() {
        return answerTyped;
    }

    /**
     * Called when the input would create more than two number characters.
     */
    public void resetLabelTyping() {
        answerTyped = "";
    }

    /**
     * Listens to keyboard input.
     * Configured to only listen to numeric values.
     * Adjustments to only add to answer if less than or equal to two numbers.
     */
    private class TypeListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
            char c = e.getKeyChar();
            if ("1234567890".contains(String.valueOf(c))) { // Only react to numeric values.
                
            	if (test.length() == 1) {
                    test += String.valueOf(c);
                } else {
                    test = String.valueOf(c);
                }
            }
            labelTyping.setText(test);
            if (key == KeyEvent.VK_ENTER) {
            	answerTyped = labelTyping.getText();
            	test = "";
                labelTyping.setText(test);

            }
            
            if(key == KeyEvent.VK_BACK_SPACE) {
            	if(test.length() > 0) {
            	test = test.substring(0, test.length()-1);
            	System.out.println("hejk");
            	labelTyping.setText(test);
            	}		
            }
            
            
          
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Initialize the frame.
     */
    private void setupFrame() {
        setSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setTitle("JOKER ROUND");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(pnlMain);
        addKeyListener(new TypeListener());     // Listens to numeric keyboard input.
        setFocusable(true);
        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Initialize the main panel to use in the frame.
     */
    private void setupMainPanel() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(1000, 600));
        pnlMain.add(pnlRight,BorderLayout.EAST);
        pnlMain.add(pnlTyping, BorderLayout.NORTH);
        
        pnlMain.add(pnlGame, BorderLayout.CENTER);
        
    }
    private void setupRightPanel() {
    	pnlRight=new JPanel();
    	pnlRight.setLayout(new BorderLayout());
    	pnlRight.setPreferredSize(new Dimension(50,600));
    	pnlRight.setBackground(Color.WHITE);
    	btnInstructions.setBackground(Color.WHITE);
    	btnInstructions.setMaximumSize(new Dimension(50, 68));
        pnlRight.add(btnInstructions, BorderLayout.SOUTH);
        btnInstructions.addActionListener(new buttonListener());
    }
   
    /**
     * Initialize the panel where the user input is visualized.
     */
    private void setupTypePanel() {
        pnlTyping = new JPanel(new BorderLayout());
        pnlTyping.setBackground(Color.WHITE);
        ImageIcon imageShowing = new ImageIcon("images/svar_memoria.png");
        JLabel lblLogo = new JLabel(imageShowing);
        lblLogo.setBackground(Color.WHITE);
        setupTextFieldPoints();
        pnlTyping.add(lblLogo, BorderLayout.WEST);
        JPanel pnlCenterTyping = new JPanel(new BorderLayout());
        pnlCenterTyping.setBackground(Color.WHITE);
        labelTyping = new JLabel("") {
            @Override
            public boolean isValidateRoot() {
                return true;
            }
        };
        labelTyping.setFont(new Font("monospaced", Font.BOLD, 90));
        labelTyping.setBackground(Color.WHITE);
        pnlCenterTyping.add(labelTyping, BorderLayout.CENTER);
        pnlTyping.add(pnlCenterTyping, BorderLayout.CENTER);
        pnlTyping.add(textFieldPoints, BorderLayout.EAST);
       
    }

    /**
     * Initialize the game panel.
     */
    private void setupGamePanel() {
        pnlGame = new JPanel();
        pnlGame.setBackground(Color.WHITE);
        lblHelp=new JLabel("HELP");
        lblHelp.setPreferredSize(new Dimension(500,500));
    	lblHelp.setBackground(Color.WHITE);
    	lblHelp.setOpaque(true);
    	lblHelp.setVisible(false);
    	pnlGame.add(lblHelp);
    }

    /**
     * Initialize a text field to display current points.
     */
    private void setupTextFieldPoints() {
        textFieldPoints = new JTextField("0p  ");
        textFieldPoints.setFont(new Font("monospaced", Font.BOLD, 80));
        textFieldPoints.setBackground(Color.WHITE);
        textFieldPoints.setForeground(Color.BLACK);
        textFieldPoints.setEditable(false);
        textFieldPoints.setFocusable(false);
        textFieldPoints.setOpaque(true);
        textFieldPoints.setBorder(null);
    }

    private void setupFinalPointsLabel() {
        lblFinalPoints = new JLabel("GRATTIS: " + textFieldPoints.getText()) {
            @Override
            public boolean isValidateRoot() {
                return true;
            }
        };
        ;
        lblFinalPoints.setHorizontalAlignment(SwingConstants.CENTER);
        lblFinalPoints.setVerticalAlignment(SwingConstants.CENTER);
        lblFinalPoints.setSize(1000, 500);
        lblFinalPoints.setFont(new Font("monospaced", Font.BOLD, 100));           // Enlarge font size
        lblFinalPoints.setForeground(Color.BLACK);
        lblFinalPoints.setVisible(false);
        pnlGame.add(lblFinalPoints);
    }

    /**
     * Initialize a label to display point gains for matching answers.
     */
    private void setupTwoPointsLabel() {
        lblTwoPoints = new JLabel(new ImageIcon("images/plus_two_points.png")) {
            @Override
            public boolean isValidateRoot() {
                return true;
            }
        };
        lblTwoPoints.setHorizontalAlignment(SwingConstants.CENTER);
        lblTwoPoints.setVerticalAlignment(SwingConstants.CENTER);
        lblTwoPoints.setSize(1000, 500);
        lblTwoPoints.setVisible(false);
        pnlGame.add(lblTwoPoints);
    }

    /**
     * When called, starts running a task to display, pause and hide a point label.
     */
    private void addTwoPointsThread() {
        new Thread() {
            @Override
            public void run() {
                lblTwoPoints.setVisible(true);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lblTwoPoints.setVisible(false);
            }
        }.start();
    }

    /**
     * If X on frame i pressed asks the user if the want to close the program.
     */
    private void xButtonPressed() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(null,
                        "Do you really want to close Memoria?");
                if (x == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.exit(0);
                } else {
                    System.out.println("We are happy you are back :)");
                }
            }
        });
    }
    private class buttonListener implements  java.awt.event.ActionListener {
    	
    
    	 public void actionPerformed(ActionEvent e) {
             if (e.getSource() == btnInstructions) {
                 isHelpBtnPressed=true;
            	 if (helpGUI==null){
              		  helpGUI= new HelpGUI(JokerGameGUI.this);
                 }else {
                 	if (helpGUI.isDisposed()) {
                 		helpGUI=new HelpGUI(JokerGameGUI.this);
                 	}
                 }
                 
                 
             }
    	 }
    
    }
}