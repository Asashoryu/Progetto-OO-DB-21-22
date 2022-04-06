package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class AddIndirizzoSec extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldVia;
	private JTextField textFieldCittà;
	private JTextField textFieldNazione;
	private JTextField textCap;

	
	public AddIndirizzoSec() {
		frame=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 221);
		setLocation(750,100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldVia = new JTextField();
		textFieldVia.setBounds(78, 27, 140, 19);
		contentPane.add(textFieldVia);
		textFieldVia.setColumns(10);
		
		textFieldCittà = new JTextField();
		textFieldCittà.setBounds(78, 56, 140, 19);
		contentPane.add(textFieldCittà);
		textFieldCittà.setColumns(10);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(78, 85, 140, 19);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textCap = new JTextField();
		textCap.setBounds(78, 114, 140, 19);
		contentPane.add(textCap);
		textCap.setColumns(10);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setBounds(10, 30, 45, 13);
		contentPane.add(lblVia);
		
		JLabel lblCittà = new JLabel("Citt\u00E0");
		lblCittà.setBounds(10, 59, 45, 13);
		contentPane.add(lblCittà);
		
		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setBounds(10, 88, 45, 13);
		contentPane.add(lblNazione);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(10, 117, 45, 13);
		contentPane.add(lblCap);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(133, 159, 85, 21);
		contentPane.add(btnInserisci);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(10, 159, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}
}
