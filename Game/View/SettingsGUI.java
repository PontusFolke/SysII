package Game.View;

import Game.Controller.MusicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class contains all the code for the Setting interface
 *
 * @author Yasir Kakar, Adel Sabanovic
 * @version 4.0
 */

public class SettingsGUI extends JFrame {
    private MusicController musicController = new MusicController();
    private JFrame frame = new JFrame();

    private JPanel pnlMain = new JPanel();

    private ImageIcon inställningarLogo = new ImageIcon("images/settings.PNG");
    private ImageIcon iconMusic = new ImageIcon("images/music.PNG");
    private ImageIcon iconOn = new ImageIcon("images/on.PNG");
    private ImageIcon iconOff = new ImageIcon("images/Off.PNG");
    private ImageIcon iconOk = new ImageIcon("images/ok.PNG");
    private ImageIcon iconPi = new ImageIcon("images/pi.jpg");


    private JButton btnOn = new JButton(iconOn);
    private JButton btnOff = new JButton(iconOff);
    private JButton btnOk = new JButton(iconOk);

    private JLabel lblSettings = new JLabel(inställningarLogo);
    private JLabel lblMusic = new JLabel(iconMusic);
    private JLabel lblPiOn = new JLabel(iconPi);
    private JLabel lblPiOff = new JLabel(iconPi);
    private JLabel lblPi3Ok = new JLabel(iconPi);

    private Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 20);
    
    private boolean isDisposed=false;

    /**
     * This constructor contains the functions for the settingsGUI
     */
    public SettingsGUI() {
    	if(musicController.isPlaying()) {
    		btnOn.setEnabled(false);
    	}else {
    		btnOff.setEnabled(false);
    	}
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(pnlMain);

        pnlMain.setBackground(Color.WHITE);

        pnlMain.setLayout(null);

        lblSettings.setFont(myFont);
        lblMusic.setFont(myFont);
        lblPiOn.setVisible(false);
        lblPiOff.setVisible(false);
        lblPi3Ok.setVisible(false);

        btnOk.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnOff.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnOn.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        addContents();
        addListeners();
        setBoundsForContents();
        XButtonPressed();
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method sets size and location for the buttons and labels in the SettingsGUI
     */
    private void setBoundsForContents() {
        lblSettings.setBounds(40, 10, 300, 60);
        lblMusic.setBounds(10, 97, 130, 40);
        btnOn.setBounds(240, 100, 60, 25);
        lblPiOn.setBounds(200, 90, 33, 40);
        btnOff.setBounds(240, 150, 71, 24);
        lblPiOff.setBounds(200, 140, 33, 40);
        btnOk.setBounds(150, 200, 59, 38);
        lblPi3Ok.setBounds(110, 200, 33, 40);

    }

    /**
     * This method adds the buttons and labels in the SettingsGUI
     */
    private void addContents() {

        pnlMain.add(lblSettings);
        pnlMain.add(lblMusic);
        pnlMain.add(btnOn);
        pnlMain.add(lblPiOn);
        pnlMain.add(btnOff);
        pnlMain.add(lblPiOff);
        pnlMain.add(btnOk);
        pnlMain.add(lblPi3Ok);
    }
    public boolean isDisposed() {
    	return isDisposed;
    }
    /**
     * This method adds listeners to the buttons in the SettingsGUI
     */
    private void addListeners() {

        btnOk.addActionListener(new OkListener());
        btnOn.addActionListener(new PlayMusic());
        btnOff.addActionListener(new StopMusic());
        btnOn.addMouseListener(new MouseOn());
        btnOff.addMouseListener(new MouseOff());
        btnOk.addMouseListener(new MouseOk());
    }

    /**
     * Inner class shows a Pi logo while the mouse hovers over the "on" button
     */
    private class MouseOn implements MouseListener {
        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPiOn.setVisible(true);
        }

        public void mouseExited(MouseEvent e) {
            lblPiOn.setVisible(false);
        }
    }

    /**
     * Inner class shows a Pi logo while the mouse hovers over the "off" button
     */
    private class MouseOff implements MouseListener {
        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPiOff.setVisible(true);
        }

        public void mouseExited(MouseEvent e) {
            lblPiOff.setVisible(false);
        }
    }

    /**
     * Inner class shows a Pi logo while the mouse hovers over the "ok" button
     */
    private class MouseOk implements MouseListener {
        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPi3Ok.setVisible(true);
        }

        public void mouseExited(MouseEvent e) {
            lblPi3Ok.setVisible(false);
        }
    }

    /**
     * This class contains the functions for the "OK" button in the SettingsGUI
     *
     * @author Yasir Kakar
     */
    private class OkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	isDisposed=true;
        	System.out.println("GUI is disposed?" + isDisposed);
            frame.dispose();
            
        }
    }

    private class PlayMusic implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            musicController.playMusicSelectedClip();
            btnOn.setEnabled(false);
            btnOff.setEnabled(true);
        }
    }

    private class StopMusic implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            musicController.stopMusic();
            btnOn.setEnabled(true);
            btnOff.setEnabled(false);
        }
    }
    public void XButtonPressed() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isDisposed=true;
                frame.dispose();
            }
        });
    }
}
