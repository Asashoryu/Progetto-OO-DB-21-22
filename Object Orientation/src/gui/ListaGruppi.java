package gui;
import javax.swing.event.*;
import model.Provatext;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class ListaGruppi extends JFrame {
	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaGruppi frame = new ListaGruppi("Francesco");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListaGruppi(String Utente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPane.setBackground(Color.CYAN);
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 69, 281, 302);
		contentPane.add(scrollPane);
		
		JList<Object> list = new JList<Object>();
		list.setBackground(new Color(240, 248, 255));
		scrollPane.setViewportView(list);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBackground(new Color(176, 224, 230));
		btnIndietro.setBounds(10, 10, 78, 19);
		contentPane.add(btnIndietro);
		
		JButton btnAddGruppo = new JButton("Aggiungi");
		btnAddGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddGruppo.setBounds(10, 393, 85, 21);
		contentPane.add(btnAddGruppo);
		
		JButton btnEliminaGruppo = new JButton("Elimina");
		btnEliminaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnEliminaGruppo.setBounds(206, 393, 85, 21);
		contentPane.add(btnEliminaGruppo);
		
		JButton btnModificaGruppo = new JButton("Modifica");
		btnModificaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModificaGruppo.setBounds(108, 393, 85, 21);
		contentPane.add(btnModificaGruppo);
		
		JLabel lblNewLabel = new JLabel("Gruppi di " + Utente);
		lblNewLabel.setBounds(10, 44, 169, 21);
		contentPane.add(lblNewLabel);
}
	}