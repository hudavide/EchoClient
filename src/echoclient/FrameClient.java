package echoclient;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class FrameClient extends JFrame implements WindowListener, ActionListener{
	
	private JTextField testo = new JTextField("Inserisci testo");
	private JButton invio = new JButton("Invio");
	private JButton chiudi = new JButton("Chiudi");
	private JLabel risposta = new JLabel("Risposta");
	private JCheckBox on = new JCheckBox("Maiuscolo");
	
	public FrameClient(){
		this.setSize(500,200);
		this.setVisible(true);
		this.addWindowListener(this);
		this.setLayout(new java.awt.GridLayout(2,3,5,5));
		this.add(testo);
		this.add(on);
		this.add(invio);
		this.add(risposta);
		this.add(new JPanel());
		this.add(chiudi);
		this.chiudi.addActionListener(this);
		this.invio.addActionListener(this);
	}

	public void windowOpened(WindowEvent we) {
	}
	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}
	public void windowClosed(WindowEvent we) {
		System.exit(0);
	}
	public void windowIconified(WindowEvent we) {
	}
	public void windowDeiconified(WindowEvent we) {
	}
	public void windowActivated(WindowEvent we) {
	}
	public void windowDeactivated(WindowEvent we) {
	}

	public void actionPerformed(ActionEvent ae){
		try{
			Socket s = new Socket("127.0.0.1", 5555);
			BufferedReader sock_in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter sock_out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
			if(ae.getActionCommand().equals("Chiudi")){
				sock_out.println("fine");
			}else if(ae.getActionCommand().equals("Invio")){
				if(on.isSelected())
					sock_out.println("Maiuscole: ON");
				else
					sock_out.println("Maiuscole: OFF");
				sock_out.println(testo.getText());
				risposta.setText(sock_in.readLine());
			}
		}catch(Exception exc){
				
		}
	}
}
