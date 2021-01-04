package Game.View;

import Game.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contains the gui for the login menu
 *
 * @author Joakim Tell, Yasir Kakar, Adel Sabanovic
 * @version 4.0
 */
public class LogInGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private String url = "jdbc:mysql://localhost:3306/sys2"; 
    private String user = "Stefan";
    private String password = "Ivana70a";
    private Controller controller;
    private ImageIcon welcomeLogo = new ImageIcon("images/MemoriaWelcome.PNG");
    private ImageIcon imgbtn = new ImageIcon("images/ok.PNG");

    private JPanel pnlMain = new JPanel();

    ImageIcon piLogo = new ImageIcon("images/pi.jpg");

    private JLabel lblGame = new JLabel(welcomeLogo);

    private JLabel lblUsername = new JLabel("AnvÃ¤ndarnamn");
    private JLabel lblPassword = new JLabel("Lösenord");
    private JLabel lblCreateUser = new JLabel("Create new user");


    private JLabel lblPi = new JLabel(piLogo);

    private JTextField txtUsername = new JTextField("Användarnamn (3-10 tecken)");
    private JTextField txtPassword = new JTextField();


    private Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 20);

    private JButton btnLogin = new JButton(imgbtn);
    private JButton btnCreate = new JButton();

    /**
     * Constructor that initializes the different components in the class
     */
    public LogInGUI(Controller controller, String name) {
        this.controller = controller;
        setSize(380, 255);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        add(pnlMain);
        pnlMain.setBackground(Color.WHITE);

        lblGame.setFont(myFont);

        pnlMain.setLayout(null);

        lblGame.setBounds(40, 20, 300, 50);
        lblUsername.setBounds(50, 60, 100, 100);
        lblPassword.setBounds(50, 80, 100, 100);
        txtUsername.setBounds(120, 100, 165, 25);
        txtPassword.setBounds(120, 120, 165, 25);
        btnLogin.setBounds(168, 150, 59, 38);
        
        lblCreateUser.setBounds(50, 150, 50, 50);
        btnCreate.setBounds(50, 150, 50, 50);
        
        lblPi.setBounds(128, 145, 33, 40);

        lblPi.setVisible(false);
        
        lblCreateUser.setText("Create new user");
        lblUsername.setText("Username");
        lblPassword.setText("Password");

        txtUsername.setForeground(Color.GRAY);

        pnlMain.add(lblGame);
        pnlMain.add(lblUsername);
        pnlMain.add(lblPassword);
        pnlMain.add(txtUsername);
        pnlMain.add(txtPassword);
        pnlMain.add(btnLogin);
        pnlMain.add(btnCreate);
        pnlMain.add(lblPi);

        btnLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        listeners();
    }

    /**
     * Adds the listeners to the components
     */
    private void listeners() {
        txtUsername.addKeyListener(new LimitUsername());
        btnLogin.addActionListener(new Listener());
        btnCreate.addActionListener(new createListener());
        txtUsername.addFocusListener(new Focus());
    	
    	
        btnLogin.addMouseListener(new MouseSubmit());

    }

    /**
     * An inner class that makes the username hint disappear
     *
     * @author Yasir Kakar
     */
    private class Focus implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            txtUsername.setText(null);
            txtUsername.setForeground(Color.BLACK);
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

    /**
     * An inner class which limits how long a username can be
     */
    private class LimitUsername implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if ((txtUsername.getText().length() > 10) || (txtUsername.getText().length() < 3)) {
                txtUsername.setForeground(Color.RED);
                btnLogin.setEnabled(false);
            } else {
                txtUsername.setForeground(Color.GREEN);
                btnLogin.setEnabled(true);
            }
        }
    }

    /**
     * An inner class that shows a pi logo if the mouse hovers over a certain button
     */
    private class MouseSubmit implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPi.setVisible(true);

        }

        public void mouseExited(MouseEvent e) {
            lblPi.setVisible(false);

        }
    }

    /**
     * Listener that controls the length of the username.
     * Creates a MenuGUI if valid username is entered.
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String txtuserName = txtUsername.getText();
        	String txtpassWord = txtPassword.getText();
            if ((txtUsername.getText().trim().length() <= 10) && (txtUsername.getText().trim().length() >= 3)) {
            	try {
            		Connection connection = (Connection) DriverManager.getConnection(url, user, password);
            		
            		PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("Select name, password from user where name=? and password=?");
            		
            		st.setString(1, txtuserName);
                    st.setString(2, txtpassWord);
                    ResultSet rs = st.executeQuery();
                    
                    if(rs.next()) {
                    	controller.createUser();
                        setVisible(false);
                    	dispose();
                        JOptionPane.showMessageDialog(btnLogin, "You have successfully logged in");
                    }else {
                        JOptionPane.showMessageDialog(btnLogin, "Wrong Username & Password");
                    }
            	}catch ( SQLException sqlException) {
            		sqlException.printStackTrace();
            	}
            	
            } 
        }
    }
    
    private class createListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String txtuserName = txtUsername.getText();
        	String txtpassWord = txtPassword.getText();
			try {
        		Connection connection = (Connection) DriverManager.getConnection(url, user, password);
        		
        		String query = " insert into user (name , password)"
        				+ " values (?,?)";
        		
        	      PreparedStatement st = connection.prepareStatement(query);
        	      st.setString(1, txtuserName);
        	      st.setString(2, txtpassWord);
        	      
        	      st.execute();
        	      connection.close();

        		
			}catch ( SQLException sqlException) {
        		sqlException.printStackTrace();
        	}
		}
    }
    

    public JTextField getTxtUsername() {
        return txtUsername;
    }

}
