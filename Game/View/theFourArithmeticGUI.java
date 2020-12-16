package Game.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

import Game.Controller.Controller;
import Game.Controller.multiplicationGame.DropCardsThread;

public class theFourArithmeticGUI extends JFrame{

	private JFrame frame = new JFrame();

	private JPanel pnlMain = new JPanel();

	private JRadioButton rbMultiplication = new JRadioButton("Multiplication");
	private JRadioButton rbDivision = new JRadioButton("Division");
	private JRadioButton rbSubtraction = new JRadioButton("Subtraction");
	private JRadioButton rbAddition = new JRadioButton("Addition");
	private ButtonGroup group = new ButtonGroup();
	
	private ImageIcon iconSingle = new ImageIcon(new ImageIcon("images/single.JPG").getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH));
	private ImageIcon iconPi = new ImageIcon(new ImageIcon("images/pi.JPG").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	private ImageIcon iconMemoria = new ImageIcon(new ImageIcon("images/mem3.JPG").getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH));
	private ImageIcon iconBack = new ImageIcon("images/back.jpg");
	
	private JLabel lblMemoria = new JLabel(iconMemoria);
	private JLabel lblSingle = new JLabel(iconSingle);
	private JLabel lblPi = new JLabel(iconPi);

	private JButton btnBack = new JButton(iconBack);
	private JButton btnStart = new JButton("Start");
	
	private Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 26);
	private Font myFont2 = new Font("Serif", Font.ITALIC | Font.BOLD, 32);

	private boolean rbMchosen = false;
	private boolean rbDchosen = false;
	private boolean rbAchosen = false;
	private boolean rbSchosen = false;
	Controller controller;

	public theFourArithmeticGUI(Controller controller) {
		this.controller = controller;
		frame.setSize(600,500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(pnlMain);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		pnlMain.setLayout(null);
		pnlMain.setBackground(Color.white);
		pnlMain.add(rbMultiplication);
		pnlMain.add(rbDivision);
		pnlMain.add(rbSubtraction);
		pnlMain.add(rbAddition);
		pnlMain.add(lblMemoria);
		pnlMain.add(lblSingle);
		pnlMain.add(lblPi);
		pnlMain.add(btnBack);
		pnlMain.add(btnStart);

		group.add(rbAddition);
		group.add(rbDivision);
		group.add(rbMultiplication);
		group.add(rbSubtraction);

		btnBack.setBounds(40, 40, 40, 40);
		btnBack.setBackground(Color.white);
		btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnBack.setFocusPainted(false);
		
		btnStart.setBounds(420, 380, 80, 30);
		btnStart.setBackground(Color.white);
		btnStart.setBorder(BorderFactory.createLineBorder(Color.white));
		btnStart.setFocusPainted(false);
		btnStart.setFont(myFont2);
		btnStart.setVisible(false);
		
		lblMemoria.setBounds(100, 30, 400, 100); 
		lblSingle.setBounds(100,100,400,100);
		lblPi.setBounds(160,180,20,20);

		rbMultiplication.setBounds(200,180,300,50);
		rbDivision.setBounds(200,230,300,50);
		rbSubtraction.setBounds(200,280, 300,50);
		rbAddition.setBounds(200,330,300,30);

		rbMultiplication.setBackground(Color.white);
		rbDivision.setBackground(Color.white);
		rbSubtraction.setBackground(Color.white);
		rbAddition.setBackground(Color.white);
		
		rbMultiplication.setFocusPainted(false);
		rbDivision.setFocusPainted(false);
		rbSubtraction.setFocusPainted(false);
		rbAddition.setFocusPainted(false);

		rbMultiplication.setFont(myFont1);
		rbDivision.setFont(myFont1);
		rbSubtraction.setFont(myFont1);
		rbAddition.setFont(myFont1);


		lblPi.setVisible(false);
		addListeners();
		XButtonPressed();

	}

	private void addListeners() {
		  rbMultiplication.addMouseListener(new rbMouseListener());
		  rbDivision.addMouseListener(new rbMouseListener());
		  rbAddition.addMouseListener(new rbMouseListener());
		  rbSubtraction.addMouseListener(new rbMouseListener());

		rbMultiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbMultiplication.setIcon(iconPi);
				rbDivision.setIcon(null);
				rbAddition.setIcon(null);
				rbSubtraction.setIcon(null);
				rbMchosen = true;
				rbDchosen = false;
				rbAchosen = false;
				rbSchosen = false;
				btnStart.setVisible(true);
			}
		});
		rbDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbMultiplication.setIcon(null);
				rbDivision.setIcon(iconPi);
				rbAddition.setIcon(null);
				rbSubtraction.setIcon(null);
				rbMchosen = false;
				rbDchosen = true;
				rbAchosen = false;
				rbSchosen = false;
				btnStart.setVisible(true);

			}
		});
		rbAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbMultiplication.setIcon(null);
				rbDivision.setIcon(null);
				rbAddition.setIcon(iconPi);
				rbSubtraction.setIcon(null);
				rbMchosen = false;
				rbDchosen = false;
				rbAchosen = true;
				rbSchosen = false;
				btnStart.setVisible(true);

			}
		});
		rbSubtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbMultiplication.setIcon(null);
				rbDivision.setIcon(null);
				rbAddition.setIcon(null);
				rbSubtraction.setIcon(iconPi);
				rbMchosen = false;
				rbDchosen = false;
				rbAchosen = false;
				rbSchosen = true;
				btnStart.setVisible(true);

			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( arg0.getSource() == btnBack) {
					frame.dispose();
					controller.switchToMenu();
				}
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbMchosen == true) {
					startSinglePlayer();
				}
				if(rbDchosen == true) {
					JOptionPane.showMessageDialog(null, "Ej implementerat än, träna gärna på något av de andra räknesätten sålänge!");
				}
				if(rbAchosen == true) {
					JOptionPane.showMessageDialog(null, "Ej implementerat än, träna gärna på något av de andra räknesätten sålänge!");				}
				if(rbSchosen == true) {
					JOptionPane.showMessageDialog(null, "Ej implementerat än, träna gärna på något av de andra räknesätten sålänge!");				}
				
			}
		});
		
	}
	
	
	private class rbMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getComponent() == rbMultiplication) {
				rbMultiplication.setIcon(iconPi);
			}else if (arg0.getComponent() == rbDivision) {
				rbDivision.setIcon(iconPi);
			}else if (arg0.getComponent() == rbAddition) {
				rbAddition.setIcon(iconPi);
			} else if (arg0.getComponent() == rbSubtraction) {
				rbSubtraction.setIcon(iconPi);
			}


		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			if(rbMchosen == false)
				rbMultiplication.setIcon(null);
			if(rbDchosen == false)
				rbDivision.setIcon(null);
			if(rbAchosen == false)
				rbAddition.setIcon(null);
			if(rbSchosen == false)
				rbSubtraction.setIcon(null);
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
    public void XButtonPressed() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
        	
            @Override
            public void windowClosing(WindowEvent e) {
            	System.out.println("??");
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
    public DropCardsThread startSinglePlayer() {
		frame.setVisible(false);
        frame.dispose();
        return new DropCardsThread(controller, 29);
		}

}
