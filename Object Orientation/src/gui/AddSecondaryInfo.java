package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;

public class AddSecondaryInfo extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;

	
	public AddSecondaryInfo(Controller c, String Utente) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 221);
		setLocation(500,100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTermina = new JButton("Termina");
		btnTermina.setBounds(133, 153, 85, 21);
		contentPane.add(btnTermina);
		
		JButton btnAddFisico = new JButton("Inserisci Indirizzo Fisico");
		btnAddFisico.setBackground(new Color(0, 255, 255));
		btnAddFisico.setBorder(null);
		btnAddFisico.setBounds(10, 68, 208, 21);
		contentPane.add(btnAddFisico);
		btnAddFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddIndirizzoSec Address = new AddIndirizzoSec();
				Address.setVisible(true);
			}
		});
		
		JButton btnAddNumero = new JButton("Inserisci Numero Telefonico");
		btnAddNumero.setBackground(new Color(0, 255, 255));
		btnAddNumero.setBorder(null);
		btnAddNumero.setBounds(10, 24, 208, 21);
		contentPane.add(btnAddNumero);
		btnAddNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNumero Number = new AddNumero();
				Number.setVisible(true);
			}
		});
		
		JButton btnAddEmail = new JButton("Inserisci Email");
		btnAddEmail.setBackground(new Color(0, 255, 255));
		btnAddEmail.setBorder(null);
		btnAddEmail.setBounds(10, 112, 208, 21);
		contentPane.add(btnAddEmail);
		btnAddEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmail Email = new AddEmail();
				Email.setVisible(true);
			}
		});
		
	}

}
