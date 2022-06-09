package gui;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.event.ObjectChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import controller.Controller;
import model.Rubrica;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
import gui.AddSecondaryInfo;

public class AddContatto extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldSecondoNome;
	private JTextField textFieldCognome;
	private JLabel lblTitolo;
	private JLabel lblLabelNome;
	private JLabel lblSecondoNome;
	private JLabel lblCognome;
	private JButton btnNewButton;
	private Controller controller;
	private JButton btnNewButton_1;
	private JTextField textFieldNumFisso;
	private JTextField textFieldNumMobile;
	private JTextField textFieldEmail;
	private JTextField textFieldVia;
	private JLabel lblNumMobile;
	private JLabel lblNumFisso;
	private JLabel lblVia;
	private JTextField textFieldCittà;
	private JTextField textFieldNazione;
	private JTextField textFieldCap;
	private JTextField textFieldDescrizioneEmail;

	public AddContatto(Controller c, String Utente) {
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;

		frame.setTitle("Aggiunta Nuovo Contatto");
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(224, 255, 255));
		contentPane.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(20, 62, 90, 19);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		textFieldNome.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {

			}

			public void removeUpdate(DocumentEvent e) {

			}

			public void changedUpdate(DocumentEvent e) {

			}
		});

		textFieldSecondoNome = new JTextField();
		textFieldSecondoNome.setBounds(149, 62, 96, 19);
		contentPane.add(textFieldSecondoNome);
		textFieldSecondoNome.setColumns(10);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(278, 62, 90, 19);
		contentPane.add(textFieldCognome);
		textFieldCognome.setColumns(10);

		lblTitolo = new JLabel("Inserire informazioni del contatto della rubrica di " + Utente);
		lblTitolo.setBounds(10, 10, 362, 19);
		contentPane.add(lblTitolo);

		lblLabelNome = new JLabel("Nome ");
		lblLabelNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelNome.setBounds(14, 39, 96, 13);
		contentPane.add(lblLabelNome);

		lblSecondoNome = new JLabel("Secondo Nome");
		lblSecondoNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondoNome.setBounds(149, 39, 96, 13);
		contentPane.add(lblSecondoNome);

		lblCognome = new JLabel("Cognome ");
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
		lblCognome.setBounds(274, 39, 96, 13);
		contentPane.add(lblCognome);

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(10, 355, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});

		textFieldNumFisso = new JTextField();
		textFieldNumFisso.setBounds(65, 203, 96, 19);
		contentPane.add(textFieldNumFisso);
		textFieldNumFisso.setColumns(10);
		textFieldNumFisso.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {

			}

			public void removeUpdate(DocumentEvent e) {

			}

			public void changedUpdate(DocumentEvent e) {

			}
		});

		textFieldNumMobile = new JTextField();
		textFieldNumMobile.setBounds(65, 174, 96, 19);
		contentPane.add(textFieldNumMobile);
		textFieldNumMobile.setColumns(10);
		textFieldNumMobile.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {

			}

			public void removeUpdate(DocumentEvent e) {

			}

			public void changedUpdate(DocumentEvent e) {

			}
		});

		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setBounds(21, 152, 112, 13);
		contentPane.add(lblNumeriTelefono);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(65, 263, 96, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblIndirizzoMail = new JLabel("Indirizzo Mail");
		lblIndirizzoMail.setBounds(20, 240, 96, 13);
		contentPane.add(lblIndirizzoMail);

		textFieldVia = new JTextField();
		textFieldVia.setBounds(272, 177, 96, 19);
		contentPane.add(textFieldVia);
		textFieldVia.setColumns(10);
		textFieldVia.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {

			}

			public void removeUpdate(DocumentEvent e) {

			}

			public void changedUpdate(DocumentEvent e) {

			}
		});

		JLabel lblIndirizzoFisico = new JLabel("Indirizzo Fisico");
		lblIndirizzoFisico.setBounds(228, 152, 97, 13);
		contentPane.add(lblIndirizzoFisico);

		lblNumMobile = new JLabel("Mobile ");
		lblNumMobile.setBounds(10, 177, 45, 13);
		contentPane.add(lblNumMobile);

		lblNumFisso = new JLabel("Fisso ");
		lblNumFisso.setBounds(10, 206, 45, 13);
		contentPane.add(lblNumFisso);

		lblVia = new JLabel("Via");
		lblVia.setHorizontalAlignment(SwingConstants.LEFT);
		lblVia.setBounds(217, 180, 45, 13);
		contentPane.add(lblVia);

		JButton btnAzione = new JButton("Vai");
		btnAzione.setBounds(283, 355, 85, 21);
		contentPane.add(btnAzione);
		btnAzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Inserimento dei dati obbligatori presi dai textfield in contatti di Utente
				// passato come parametro
				// Creazione frame per informazioni secondarie
				AddSecondaryInfo MoreInfo = new AddSecondaryInfo(Utente);
				MoreInfo.setVisible(true);
			}
		});

		textFieldCittà = new JTextField();
		textFieldCittà.setBounds(272, 203, 96, 19);
		contentPane.add(textFieldCittà);
		textFieldCittà.setColumns(10);

		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(272, 232, 96, 19);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);

		textFieldCap = new JTextField();
		textFieldCap.setBounds(272, 261, 96, 19);
		contentPane.add(textFieldCap);
		textFieldCap.setColumns(10);

		JLabel lblCittà = new JLabel("Citt\u00E0");
		lblCittà.setBounds(217, 206, 45, 13);
		contentPane.add(lblCittà);

		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setBounds(217, 235, 45, 13);
		contentPane.add(lblNazione);

		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(217, 264, 45, 13);
		contentPane.add(lblCap);

		JLabel lblDescrizioneFisico = new JLabel("La descrizione puo essere solo principale\r\n");
		lblDescrizioneFisico.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblDescrizioneFisico.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrizioneFisico.setBounds(217, 296, 151, 13);
		contentPane.add(lblDescrizioneFisico);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(10, 265, 45, 13);
		contentPane.add(lblEmail);

		textFieldDescrizioneEmail = new JTextField();
		textFieldDescrizioneEmail.setBounds(65, 293, 96, 19);
		contentPane.add(textFieldDescrizioneEmail);
		textFieldDescrizioneEmail.setColumns(10);

		JLabel lblDescrizioneEmail = new JLabel("Descrizione");
		lblDescrizioneEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrizioneEmail.setBounds(10, 296, 52, 13);
		contentPane.add(lblDescrizioneEmail);
	}
}
