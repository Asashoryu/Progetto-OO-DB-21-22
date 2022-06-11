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
import javax.swing.Spring;
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
import java.awt.GridBagLayout;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.TextField;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import gui.AddSecondaryInfo;
import javax.swing.JScrollBar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout.Alignment;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

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
	private JPanel panelMain;
	private JPanel pannelloElemScrollPane;
	private JPanel pannelloIndPrincipale;
	private JPanel pannelloNumTel;
	private JPanel pannelloIndMail;
	private JPanel pannelloNumTelSec;
	private JPanel pannelloEmailAddSec;
	private JLabel lblNumSecondari;
	private JLabel lblEmailSecondarie;
	private JPanel pannelloScrolNumTel;
	private JPanel panelloScrollMail;

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
		setBounds(100, 100, 793, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(224, 255, 255));
		contentPane.setLayout(null);

		lblTitolo = new JLabel("Inserire informazioni del contatto della rubrica di " + Utente);
		lblTitolo.setBounds(10, 10, 362, 19);
		contentPane.add(lblTitolo);

		/**
		 * Button "annulla"
		 */
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(492, 393, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		/**
		 * Button "vai"
		 */
		JButton btnAzione = new JButton("Vai");
		btnAzione.setBounds(593, 393, 85, 21);
		contentPane.add(btnAzione);
		btnAzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Inserimento dei dati obbligatori presi dai textfield in contatti di Utente
				// passato come parametro
				// Creazione frame per informazioni secondarie
			}
		});
		
		/**
		 * Inserimentoi 
		 */

		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setBounds(366, 128, 112, 13);
		contentPane.add(lblNumeriTelefono);

		JLabel lblIndirizzoMail = new JLabel("Indirizzo Mail");
		lblIndirizzoMail.setBounds(582, 128, 96, 13);
		contentPane.add(lblIndirizzoMail);

		JLabel lblIndirizzoFisico = new JLabel("Indirizzo Principale\r\n");
		lblIndirizzoFisico.setBounds(62, 128, 111, 13);
		contentPane.add(lblIndirizzoFisico);

		
		/**
		 * Indirizzi fisici secondari
		 */
		JPanel panelMain = new JPanel();
		panelMain.setBounds(38, 270, 249, 119);
		contentPane.add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pannelloElemScrollPane_1 = new JPanel();
		pannelloElemScrollPane_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloElemScrollPane_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloElemScrollPane_1.setBackground(Color.GREEN);
		
		JScrollPane scrollPane = new JScrollPane(pannelloElemScrollPane_1);
		pannelloElemScrollPane_1.setLayout(new BoxLayout(pannelloElemScrollPane_1, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(pannelloElemScrollPane_1.getSize());
		panelMain.add(scrollPane, BorderLayout.CENTER);
		
		/**
		 * Crea button "+" per aggiungere indirizzo secondario
		 */
		JButton btnAggiungiIndirizzo = new JButton("+");
		btnAggiungiIndirizzo.setBounds(38, 392, 45, 23);
		contentPane.add(btnAggiungiIndirizzo);
		
		btnAggiungiIndirizzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Debug: cliccato '+'");
				JPanel elemento     = creaElemScrollBar();
				JButton btnCancella = new JButton();
				int lastElemIndex;
				
				pannelloElemScrollPane_1.add(btnCancella);
				pannelloElemScrollPane_1.add(elemento);
				
				btnCancella.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pannelloElemScrollPane_1.remove(btnCancella);
						pannelloElemScrollPane_1.remove(elemento);
						revalidate();
						repaint();
					}
				});
				
				lastElemIndex  = pannelloElemScrollPane_1.getComponentCount() - 1;
				System.out.println("L'ultimo elemento è: " + lastElemIndex);
				revalidate();
				repaint();
			}
		});
		
		/**
		 * Indirizzo principale
		 */
		pannelloIndPrincipale = new JPanel();
		pannelloIndPrincipale.setBounds(38, 144, 234, 90);
		contentPane.add(pannelloIndPrincipale);
		
		JPanel panel = new JPanel();
		pannelloIndPrincipale.add(panel);
		panel.setLayout(new GridLayout(4,2));

		lblVia = new JLabel("Via");
		panel.add(lblVia);

		textFieldVia = new JTextField();
		panel.add(textFieldVia);
		textFieldVia.setColumns(10);
									
		JLabel lblCittà = new JLabel("Citt\u00E0");
		panel.add(lblCittà);
		
		textFieldCittà = new JTextField();
		panel.add(textFieldCittà);
		textFieldCittà.setColumns(10);

		JLabel lblNazione = new JLabel("Nazione");
		panel.add(lblNazione);

		textFieldNazione = new JTextField();
		panel.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		JLabel lblCap = new JLabel("CAP");
		panel.add(lblCap);
		
		textFieldCap = new JTextField();
		panel.add(textFieldCap, BorderLayout.WEST);
		textFieldCap.setColumns(10);
		
		/**
		 * 
		 */
		
		JPanel pannelloCredUtente = new JPanel();
		pannelloCredUtente.setBounds(38, 57, 471, 58);
		contentPane.add(pannelloCredUtente);
		pannelloCredUtente.setLayout(null);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(318, 27, 86, 20);
		pannelloCredUtente.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		lblCognome = new JLabel("Cognome ");
		lblCognome.setBounds(308, 11, 96, 13);
		pannelloCredUtente.add(lblCognome);
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
				
		textFieldSecondoNome = new JTextField();
		textFieldSecondoNome.setBounds(172, 27, 96, 19);
		pannelloCredUtente.add(textFieldSecondoNome);
		textFieldSecondoNome.setColumns(10);
						
		lblSecondoNome = new JLabel("Secondo Nome");
		lblSecondoNome.setBounds(172, 11, 96, 13);
		pannelloCredUtente.add(lblSecondoNome);
		lblSecondoNome.setHorizontalAlignment(SwingConstants.CENTER);
								
		textFieldNome = new JTextField();
		textFieldNome.setBounds(45, 27, 90, 19);
		pannelloCredUtente.add(textFieldNome);
		textFieldNome.setColumns(10);
										
		lblLabelNome = new JLabel("Nome ");
		lblLabelNome.setBounds(35, 11, 96, 13);
		pannelloCredUtente.add(lblLabelNome);
		lblLabelNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		/**
		 * Indirizzi Secondari
		 */
		JLabel lblNewLabel = new JLabel("Indirizzi Secondari");
		lblNewLabel.setBounds(62, 245, 112, 14);
		contentPane.add(lblNewLabel);
												
		pannelloNumTel = new JPanel();
		pannelloNumTel.setBounds(322, 152, 187, 66);
		contentPane.add(pannelloNumTel);
		pannelloNumTel.setLayout(null);
												
		/**
		 * numeri di telefono												
		 */
		lblNumMobile = new JLabel("Mobile ");
		lblNumMobile.setBounds(10, 15, 45, 13);
		pannelloNumTel.add(lblNumMobile);
																
		lblNumFisso = new JLabel("Fisso ");
		lblNumFisso.setBounds(10, 39, 45, 13);
		pannelloNumTel.add(lblNumFisso);
		
		textFieldNumMobile = new JTextField();
		textFieldNumMobile.setBounds(65, 11, 98, 20);
		pannelloNumTel.add(textFieldNumMobile);
		textFieldNumMobile.setColumns(10);
																		
		textFieldNumFisso = new JTextField();
		textFieldNumFisso.setBounds(65, 36, 98, 19);
		pannelloNumTel.add(textFieldNumFisso);
		textFieldNumFisso.setColumns(10);
		
		/**
		 * pannello mail secondaria
		 */
		
		pannelloIndMail = new JPanel();
		pannelloIndMail.setBounds(532, 152, 205, 66);
		contentPane.add(pannelloIndMail);
		pannelloIndMail.setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 11, 42, 14);
		pannelloIndMail.add(lblEmail);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblDescrizioneEmail = new JLabel("Descrizione");
		lblDescrizioneEmail.setBounds(10, 36, 68, 13);
		pannelloIndMail.add(lblDescrizioneEmail);
		lblDescrizioneEmail.setHorizontalAlignment(SwingConstants.LEFT);
				
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(99, 8, 96, 19);
		pannelloIndMail.add(textFieldEmail);
		textFieldEmail.setColumns(10);
																										
		textFieldDescrizioneEmail = new JTextField();
		textFieldDescrizioneEmail.setBounds(99, 32, 96, 19);
		pannelloIndMail.add(textFieldDescrizioneEmail);
		textFieldDescrizioneEmail.setColumns(10);
		
		/**
		 * pannello numeri secondari
		 */
		
		lblNumSecondari = new JLabel("Numeri Secondari\r\n");
		lblNumSecondari.setBounds(366, 245, 112, 14);
		contentPane.add(lblNumSecondari);
		
		pannelloNumTelSec = new JPanel();
		pannelloNumTelSec.setBackground(Color.GREEN);
		pannelloNumTelSec.setBounds(322, 270, 187, 58);
		contentPane.add(pannelloNumTelSec);
		pannelloNumTelSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNumTel = new JScrollPane();
		scrollPaneNumTel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneNumTel.setForeground(Color.GREEN);
		scrollPaneNumTel.setBackground(Color.GREEN);
		pannelloNumTelSec.add(scrollPaneNumTel, BorderLayout.CENTER);
		
		pannelloScrolNumTel = new JPanel();
		scrollPaneNumTel.setViewportView(pannelloScrolNumTel);
		pannelloScrolNumTel.setLayout(new BoxLayout(pannelloScrolNumTel, BoxLayout.PAGE_AXIS));
		
		/**
		 * Button "+" aggiungi numero secondario
		 */
		JButton btnAddSecNum = new JButton("+");
		btnAddSecNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Debug: cliccato '+'");
				JButton btnCancellaNumSec = new JButton();
				JPanel numero    = creaSecNumb();
				int lastNumIndex;
				
				pannelloScrolNumTel.add(btnCancellaNumSec);
				pannelloScrolNumTel.add(numero);
				lastNumIndex  = pannelloScrolNumTel.getComponentCount() - 1;
				System.out.println("L'ultimo numero è: " + lastNumIndex);
				
				btnCancellaNumSec.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pannelloScrolNumTel.remove(btnCancellaNumSec);
						pannelloScrolNumTel.remove(numero);
						revalidate();
						repaint();
					}
				});
				
				revalidate();
				repaint();
			}
		});
		btnAddSecNum.setBounds(322, 333, 47, 23);
		contentPane.add(btnAddSecNum);
		
		/**
		 *  Aggiungi mail secondarie
		 */
		
		lblEmailSecondarie = new JLabel("Indirizzi Mail Secondari\r\n");
		lblEmailSecondarie.setBounds(565, 245, 134, 14);
		contentPane.add(lblEmailSecondarie);
		
		pannelloEmailAddSec = new JPanel();
		pannelloEmailAddSec.setBackground(Color.GREEN);
		pannelloEmailAddSec.setBounds(532, 270, 205, 58);
		contentPane.add(pannelloEmailAddSec);
		pannelloEmailAddSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEmail = new JScrollPane();
		pannelloEmailAddSec.add(scrollPaneEmail, BorderLayout.CENTER);
		
		panelloScrollMail = new JPanel();
		scrollPaneEmail.setViewportView(panelloScrollMail);
		panelloScrollMail.setLayout(new BoxLayout(panelloScrollMail, BoxLayout.PAGE_AXIS));
		
		/**
		 * Button "+" aggiungi mail secondaria
		 */
		JButton btnAddSecMail = new JButton("+");
		btnAddSecMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Debug: cliccato '+'");
				JButton btnCancellaMailSec = new JButton();
				JPanel mail                = creaSecMail();
				int lastMailIndex;
				
				panelloScrollMail.add(btnCancellaMailSec);
				panelloScrollMail.add(mail);
				lastMailIndex  = panelloScrollMail.getComponentCount() - 1;
				System.out.println("L'ultima mail è: " + lastMailIndex);
				
				btnCancellaMailSec.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelloScrollMail.remove(btnCancellaMailSec);
						panelloScrollMail.remove(mail);
						revalidate();
						repaint();
					}
				});
				
				revalidate();
				repaint();
			}
		});
		btnAddSecMail.setBounds(532, 333, 45, 23);
		contentPane.add(btnAddSecMail);
	}
	
	private JPanel creaElemScrollBar()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.setSize(new Dimension(300,300));

		lblVia = new JLabel("Via");
		panel.add(lblVia);

		textFieldVia = new JTextField();
		panel.add(textFieldVia);
		textFieldVia.setColumns(10);
									
		JLabel lblCittà = new JLabel("Citt\u00E0");
		panel.add(lblCittà);
		
		textFieldCittà = new JTextField();
		panel.add(textFieldCittà);
		textFieldCittà.setColumns(10);

		JLabel lblNazione = new JLabel("Nazione");
		panel.add(lblNazione);

		textFieldNazione = new JTextField();
		panel.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		JLabel lblCap = new JLabel("CAP");
		panel.add(lblCap);
		
		textFieldCap = new JTextField();
		panel.add(textFieldCap, BorderLayout.WEST);
		textFieldCap.setColumns(10);
		
		return panel;
	}
	
	private JPanel creaSecNumb()
	{
		JTextField textFieldDescFun;
		JTextField textFieldNumFun;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescFun = new JTextField();
		panel.add(textFieldDescFun);
		textFieldDescFun.setColumns(5);
																		
		textFieldNumFun = new JTextField();
		panel.add(textFieldNumFun);
		textFieldNumFun.setColumns(5);
		
		return  panel;
	}
	
	private JPanel creaSecMail()
	{
		JTextField textFieldDescFun;
		JTextField textFieldMailFun;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescFun = new JTextField();
		textFieldDescFun.setColumns(5);
		panel.add(textFieldDescFun);
																		
		textFieldMailFun = new JTextField();
		textFieldMailFun.setColumns(5);
		panel.add(textFieldMailFun);
		
		return  panel;
	}
}
