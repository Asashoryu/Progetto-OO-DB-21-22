package gui;
import controller.Controller;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.event.ObjectChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Rubrica;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.TextField;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.BoxLayout;

public class ListaContatti extends JFrame {
	
	private JFrame     frame;
	private Controller controller;
	private String 	   Nome;
	private String     SecondoNome;
	private String     Cognome;
	
	/**
	 * Crea frame.
	 */
	public ListaContatti(Controller c, JFrame frameChiamante) {
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));
		
		frame = this;
        controller=c;
  
        // Display the window.
        frame.setTitle("Rubrica di "+controller.getRubricaSelezionata().getNome());
        frame.setBounds(500, 200,660,460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("RUBRICA AVANZATA");
		lblNewLabel_1.setBounds(139, 10, 368, 72);
		lblNewLabel_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Ebrima", Font.BOLD, 20));
		getContentPane().add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_3);
		
		JButton btnNewButton = new JButton("GRUPPI");
		btnNewButton.setBounds(536, 61, 82, 21);
		btnNewButton.setBackground(new Color(176, 224, 230));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(btnNewButton);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_5);
		
		JButton btnUscita = new JButton("Indietro");
		btnUscita.setBounds(10, 10, 82, 23);
		btnUscita.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUscita.setBorder(UIManager.getBorder("Button.border"));
		btnUscita.setBackground(new Color(176, 224, 230));
		getContentPane().add(btnUscita);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 92, 368, 240);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.CYAN);
		scrollPane.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		getContentPane().add(scrollPane);
		
		JList<Object> list = new JList<Object>(controller.getNomiContattiRubrica());
		list.setVisibleRowCount(-1);
		scrollPane.setViewportView(list);
		list.setSelectionForeground(new Color(0, 0, 0));
		list.setFont(new Font("Arial", Font.PLAIN, 15));
		list.setBorder(null);
		list.setBackground(new Color(240, 248, 255));
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(276, 362, 94, 21);
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().add(btnModifica);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(413, 362, 94, 21);
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().add(btnElimina);
				
		JLabel lblNewLabel = new JLabel("Contatti: ");
		lblNewLabel.setBounds(139, 68, 143, 21);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel);
		
		JButton btnAggiungi = new JButton("Aggiungi ");
		btnAggiungi.setBounds(139, 362, 94, 21);
		btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 11));
		
		/**
		 * Quando è premuto il button "Indietro"
		 */
		btnUscita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frameChiamante.setVisible(true);
				 frame.dispose();
			}
		});
		
		/**
		 * Quando è premuto il button "Aggiungi"
		 */
		getContentPane().add(btnAggiungi);
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String utenteRubrica = controller.getRubricaSelezionata().getNome();
				JFrame Contatto      = new AddContatto(controller, utenteRubrica);
				frame.setVisible(false);
				Contatto.setVisible(true);
			}
		});
		
		/**
		 * Quando è premuto il button "Modifica"
		 */
		
		/**
		 * Quando è premuto il button "Elimina"
		 */
		
		/**
		 * Quando è premuto il button "GRUPPI"
		 */
		
		/**
		 * Quando è premuto il button "ricerca"
		 * TODO : implementare le varie forme di ricerca di un contatto, secondo
		 * 		  diversi criteri
		 */
	}
}

