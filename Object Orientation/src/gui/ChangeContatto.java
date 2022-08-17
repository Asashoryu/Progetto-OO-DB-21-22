package gui;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.event.ObjectChangeListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Contatto;
import model.Email;
import model.Indirizzo;
import model.Indirizzo.tipoIndirizzo;
import model.Rubrica;
import model.Telefono;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
import javax.swing.JScrollBar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class ChangeContatto extends JFrame {

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
	private JTextField textFieldCitt‡;
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
	private JPanel pannelloScrollMail;
	private JLabel lblImmagine;
	
	private boolean modificato = false;
	private String percorsoImmagine = null;


	public ChangeContatto(Controller c, JFrame frameChiamante, JList<Object> lista) {
		
		
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
		setBounds(100, 100, 793, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(224, 255, 255));
		contentPane.setLayout(null);

		lblTitolo = new JLabel("Inserire informazioni del contatto della rubrica di " + controller.getRubricaSelezionata().getNome());
		lblTitolo.setBounds(10, 10, 362, 19);
		contentPane.add(lblTitolo);
		
		/**
		 * Inserimento
		 */

		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setBounds(366, 128, 112, 13);
		contentPane.add(lblNumeriTelefono);

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
		
		
		JPanel pannelloScrollIndirizziSec = new JPanel();
		pannelloScrollIndirizziSec.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloScrollIndirizziSec.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloScrollIndirizziSec.setBackground(Color.GREEN);
		
		JScrollPane scrollPane = new JScrollPane(pannelloScrollIndirizziSec);
		pannelloScrollIndirizziSec.setLayout(new BoxLayout(pannelloScrollIndirizziSec, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(pannelloScrollIndirizziSec.getSize());
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
				JPanel elemento;
				int lastElemIndex;
				JButton btnCancella     = new JButton();
				JTextField fieldVia     = new JTextField();
				JTextField fieldCitt‡   = new JTextField();
				JTextField fieldNazione = new JTextField();
				JTextField fieldCap     = new JTextField();
				Object[] message = {
				    "Inserisci la via     :", fieldVia,
				    "Inserisci la citt‡   :", fieldCitt‡,
				    "Inserisci la nazione :", fieldNazione,
				    "Inserisci il Cap     :", fieldCap,
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Riempire i campi", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					// Si registra l'aggiunta di un nuovo indirizzo secodnario
					modificato = true;
					
					//TODO : controlli sulla validit‡ dell'inserimento
					elemento = creaElemScrollBar(fieldVia.getText(), fieldCitt‡.getText(), fieldNazione.getText(), fieldCap.getText());
					pannelloScrollIndirizziSec.add(btnCancella);
					pannelloScrollIndirizziSec.add(elemento);
					/* button per cancellare un indirizzo secondario */
					btnCancella.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pannelloScrollIndirizziSec.remove(btnCancella);
							pannelloScrollIndirizziSec.remove(elemento);
							revalidate();
							repaint();
						}
					});
					lastElemIndex  = pannelloScrollIndirizziSec.getComponentCount() - 1;
					System.out.println("L'ultimo elemento Ë: " + lastElemIndex);
					revalidate();
					repaint();
				}
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
									
		JLabel lblCitt‡ = new JLabel("Citt\u00E0");
		panel.add(lblCitt‡);
		
		textFieldCitt‡ = new JTextField();
		panel.add(textFieldCitt‡);
		textFieldCitt‡.setColumns(10);

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
		pannelloNumTel.setBounds(322, 144, 187, 67);
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
		 * pannello numeri secondari
		 */
		
		lblNumSecondari = new JLabel("Numeri Secondari\r\n");
		lblNumSecondari.setBounds(366, 245, 112, 14);
		contentPane.add(lblNumSecondari);
		
		pannelloNumTelSec = new JPanel();
		pannelloNumTelSec.setBackground(Color.GREEN);
		pannelloNumTelSec.setBounds(322, 270, 187, 119);
		contentPane.add(pannelloNumTelSec);
		pannelloNumTelSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNumTel = new JScrollPane();
		pannelloNumTelSec.add(scrollPaneNumTel, BorderLayout.CENTER);
		scrollPaneNumTel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneNumTel.setForeground(Color.GREEN);
		scrollPaneNumTel.setBackground(Color.GREEN);
		
		pannelloScrolNumTel = new JPanel();
		pannelloScrolNumTel.setBackground(Color.GREEN);
		scrollPaneNumTel.setViewportView(pannelloScrolNumTel);
		pannelloScrolNumTel.setLayout(new BoxLayout(pannelloScrolNumTel, BoxLayout.PAGE_AXIS));
		
		/**
		 * Button "+" aggiungi numero secondario
		 */
		JButton btnAddSecNum = new JButton("+");
		btnAddSecNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Debug: cliccato '+'");
				JPanel numero;
				int lastElemIndex;
				JButton btnCancellaNumSec  = new JButton();
				JTextField fieldTipo       = new JTextField();
				JTextField fieldNum      = new JTextField();
				Object[] message = {
				    "Inserisci il tipo     :", fieldTipo,
				    "Inserisci il numero   :", fieldNum,
				};
				
				
				int option = JOptionPane.showConfirmDialog(null, message, "Riempire i campi", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					// Si registra l'aggiunta di un nuovo numero secondario
					modificato = true;
					
					//TODO : controlli sulla validit‡ dell'inserimento
					numero = creaSecNumb(fieldTipo.getText(), fieldNum.getText());
					pannelloScrolNumTel.add(btnCancellaNumSec);
					pannelloScrolNumTel.add(numero);
					
					btnCancellaNumSec.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pannelloScrolNumTel.remove(btnCancellaNumSec);
							pannelloScrolNumTel.remove(numero);
							revalidate();
							repaint();
						}
					});
					lastElemIndex  = pannelloScrolNumTel.getComponentCount() - 1;
					System.out.println("L'ultimo elemento Ë: " + lastElemIndex);
					revalidate();
					repaint();
				}
			}
		});
		btnAddSecNum.setBounds(322, 392, 47, 23);
		contentPane.add(btnAddSecNum);
		
		/**
		 *  Aggiungi mail secondarie
		 */
		
		lblEmailSecondarie = new JLabel("Indirizzi Mail Secondari\r\n");
		lblEmailSecondarie.setBounds(587, 245, 134, 14);
		contentPane.add(lblEmailSecondarie);
		
		pannelloEmailAddSec = new JPanel();
		pannelloEmailAddSec.setBackground(Color.LIGHT_GRAY);
		pannelloEmailAddSec.setBounds(545, 270, 205, 119);
		contentPane.add(pannelloEmailAddSec);
		pannelloEmailAddSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEmail = new JScrollPane();
		pannelloEmailAddSec.add(scrollPaneEmail, BorderLayout.CENTER);
		
		pannelloScrollMail = new JPanel();
		scrollPaneEmail.setViewportView(pannelloScrollMail);
		pannelloScrollMail.setBackground(Color.GREEN);
		pannelloScrollMail.setLayout(new BoxLayout(pannelloScrollMail, BoxLayout.PAGE_AXIS));
		
		inizializzaContatto(textFieldNome,textFieldSecondoNome, textFieldCognome, textFieldVia, textFieldCitt‡, textFieldNazione, textFieldCap,
	            textFieldNumMobile, textFieldNumFisso, pannelloScrollIndirizziSec, pannelloScrolNumTel, pannelloScrollMail);
		
		/**
		 * Button "+" aggiungi email secondaria
		 */
		JButton btnAddSecMail = new JButton("+");
		btnAddSecMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Debug: cliccato '+'");
				
				JPanel mail;
				int lastMailIndex;;
				JButton btnCancellaMailSec = new JButton();
				JTextField fieldTipo     = new JTextField();
				JTextField fieldEmail      = new JTextField();
				Object[] message = {
				    "Inserisci il tipo     :", fieldTipo,
				    "Inserisci l'email     :", fieldEmail,
				};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					// Si registra l'aggiunta di una nuova email
					modificato = true;
					
					//TODO : controlli sulla validit‡ dell'inserimento
					mail = creaSecMail(fieldTipo.getText(), fieldEmail.getText());
					mail.setMaximumSize(new Dimension( 500, 20));
					pannelloScrollMail.add(btnCancellaMailSec);
					pannelloScrollMail.add(mail);
					
					btnCancellaMailSec.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {							
							pannelloScrollMail.remove(btnCancellaMailSec);
							pannelloScrollMail.remove(mail);
							revalidate();
							repaint();
						}
					});
					lastMailIndex  = pannelloScrollMail.getComponentCount() - 1;
					System.out.println("L'ultima mail Ë: " + lastMailIndex);
					revalidate();
					repaint();
				}
			}
		});
		btnAddSecMail.setBounds(547, 392, 45, 23);
		contentPane.add(btnAddSecMail);
		
		/**
		 * Button "annulla"
		 */
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(587, 432, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		
		/**
		 * Button "vai"
		 */
		JButton btnAzione = new JButton("Vai");
		btnAzione.setBounds(682, 432, 85, 21);
		contentPane.add(btnAzione);
		
		
		
		btnAzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Inserimento dei dati obbligatori presi dai textfield in contatti di Utente
				// passato come parametro
				// Creazione frame per informazioni secondarie
				
				int valido = 1;
				
				if (textFieldNome.getText().isBlank())
				{
					textFieldNome.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldNome.setBackground(Color.WHITE);
				}
				if (textFieldCognome.getText().isBlank())
				{
					textFieldCognome.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldCognome.setBackground(Color.WHITE);
				}
				if (textFieldVia.getText().isBlank())
				{
					textFieldVia.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldVia.setBackground(Color.WHITE);
				}
				if (textFieldCitt‡.getText().isBlank())
				{
					textFieldCitt‡.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldCitt‡.setBackground(Color.WHITE);
				}
				if (textFieldNazione.getText().isBlank())
				{
					textFieldNazione.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldNazione.setBackground(Color.WHITE);
				}
				if (textFieldCap.getText().isBlank())
				{
					textFieldCap.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldCap.setBackground(Color.WHITE);
				}
				if (textFieldNumMobile.getText().isBlank())
				{
					textFieldNumMobile.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldNumMobile.setBackground(Color.WHITE);
				}
				if (textFieldNumFisso.getText().isBlank())
				{
					textFieldNumFisso.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldNumFisso.setBackground(Color.WHITE);
				}
				if (valido == 1) {
					// verifico se sono stati inseriti dei dati nuovi
					if (modificato)
					{
					
						System.out.println("Sono state trovate delle modifiche, i dati verranno aggiornati ora!");
						int id = -1;
						// TODO: inserimento in memoria e nel DB
						// inserimenti principali
						try {
							// contatto creato
							Contatto nuovoContatto;
							// inserimento in database
							controller.inizializzaModifica();
							// ricreo il contatto con le nuove informazioni
							nuovoContatto = controller.changeContatto(textFieldNome.getText(),      textFieldSecondoNome.getText(), textFieldCognome.getText(),
												   				   textFieldNumMobile.getText(), textFieldNumFisso.getText(),    textFieldVia.getText(),
												   		           textFieldCitt‡.getText(),     textFieldNazione.getText(),     textFieldCap.getText());
							// INSERIMENTI SECONDARI
							// Inserimento indirizzi secondari
							for (Component compIndirizzoSec : pannelloScrollIndirizziSec.getComponents())
							{
								System.out.println("Primo ciclo for");
								// se non Ë un button allora Ë il pannello con gli indirizzi
								if(compIndirizzoSec instanceof JPanel)
								{
									// estraggo le informazioni dal panel trovato
									System.out.println("\tEntrato nell'if del for");
									String viaSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[1]).getText();
									String citt‡Sec   = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[3]).getText();
									String nazioneSec = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[5]).getText();
									String capSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[7]).getText();
									controller.addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
								}
							}
							// Inserimento numeri secondari
							for (Component compNumeroSec : pannelloScrolNumTel.getComponents())
							{
								System.out.println("Primo ciclo for");
								// se non Ë un button allora Ë il pannello con gli indirizzi
								if(compNumeroSec instanceof JPanel)
								{
									// estraggo le informazioni dal panel trovato
									System.out.println("\tEntrato nell'if del for");
									String descrizioneSec = ((JTextField)((JPanel) compNumeroSec).getComponents()[0]).getText();
									String numeroSec      = ((JTextField)((JPanel) compNumeroSec).getComponents()[1]).getText();
									controller.addTelefonoSec(nuovoContatto, numeroSec, descrizioneSec);
								}
							}
							// Inserimento email secondarie
							for (Component compEmailSec : pannelloScrollMail.getComponents())
							{
								System.out.println("Primo ciclo for");
								// se non Ë un button allora Ë il pannello con gli indirizzi
								if(compEmailSec instanceof JPanel)
								{
									// estraggo le informazioni dal panel trovato
									System.out.println("\tEntrato nell'if del for");
									String descrizioneSec = ((JTextField)((JPanel) compEmailSec).getComponents()[0]).getText();
									String emailSec       = ((JTextField)((JPanel) compEmailSec).getComponents()[1]).getText();
									controller.addEmailSec(nuovoContatto, emailSec, descrizioneSec);
								}
							}
							
							// vengono associati gli account alle email
							controller.loadAccountContatto(nuovoContatto);
							//commit delle informazioni in DB e inserimento del contatto i memoria
							controller.finalizzaModifica(nuovoContatto);
							
							// aggiornamento della combobox di listaContatti
							lista.removeAll();
							lista.setListData(controller.getNomiContattiRubrica());
							JOptionPane.showConfirmDialog(null, 
					                "Informazioni aggiornate con successo!", "Modifica avvenuta", JOptionPane.DEFAULT_OPTION);
							lista.revalidate();
							lista.repaint();
						} catch (SQLException es) {
							es.printStackTrace();
							JOptionPane.showMessageDialog(null, es.getMessage(),
								      "Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
							System.out.println("Non Ë stato possibile inserire il contatto in memoria");
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Non sono state rinvenute modifiche dei campi",
							      "Attenzione", JOptionPane.WARNING_MESSAGE);
						System.out.println("Non sono state rinvenute modifiche dei campi");
					}
				}
				else {
					System.out.println("I dati non sono stati inseriti correttamente");
				}
				
			}
		});
		
		// Si vuole rilevare ogni modifica effettuata sul JFrame
		textFieldNome.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldSecondoNome.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldCognome.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldVia.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldCitt‡.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldNazione.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldCap.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldNumMobile.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		textFieldNumFisso.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				modificato = true;
			}
		});
		
		JButton btnRimuoviImmagine = new JButton("Rimuovi");
		btnRimuoviImmagine.setBounds(649, 195, 85, 23);
		contentPane.add(btnRimuoviImmagine);
		btnRimuoviImmagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image img        = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
				Image imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
				lblImmagine.setIcon(new ImageIcon(imgResized));
				percorsoImmagine = null;
				if (controller.getContattoSelezionato().getPathImmagine() != null)
				{
					modificato = true;
				}
			}
		});
		
		JButton btnScegliImmagine = new JButton("Scegli");
		btnScegliImmagine.setBounds(573, 195, 73, 23);
		contentPane.add(btnScegliImmagine);
		// Quando cliccato button "scegli"
		btnScegliImmagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & PNG Images", "jpg", "png");
						fileChooser.setFileFilter(filter);
				int risposta = fileChooser.showOpenDialog(null);
				if (risposta == JFileChooser.APPROVE_OPTION)
				{
					percorsoImmagine    = fileChooser.getSelectedFile().getAbsolutePath();
					Image imgNew        = new ImageIcon(percorsoImmagine).getImage();
					Image imgNewResized = imgNew.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
					lblImmagine.setIcon(new ImageIcon(imgNewResized));
					// se l'immagine selezionata Ë diversa da quella di partenza, allora si registra la modifica
					if (percorsoImmagine != controller.getContattoSelezionato().getPathImmagine())
					{
						modificato = true;
					}
				}
			}
		});

	}

	private void inizializzaContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
						     JTextField textFieldCitt‡, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
						     JTextField textFieldNumFisso, JPanel pannelloScrollIndirizziSec, JPanel pannelloScrolNumTel, JPanel pannelloScrollMail)
	{
		Contatto contatto = controller.getContattoSelezionato();
		// inserimento nome contatto
		textFieldNome.setText(contatto.getNome());
		textFieldSecondoNome.setText(contatto.getSecondoNome());
		textFieldCognome.setText(contatto.getCognome());
		
		// inserimento dati contatto
		lblImmagine = new JLabel("");
		if (contatto.getPathImmagine() == null)
		{
			// immagine di default
			Image img          = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
			Image imgResized   = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBounds(573, 57, 150, 127);
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		else {
			// immagine caricata
			Image img          = new ImageIcon(contatto.getPathImmagine()).getImage();
			Image imgResized   = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBounds(573, 57, 150, 127);
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		
		// inserimento indirizzi contatto
		for (Indirizzo indirizzo : contatto.getIndirizzi())
		{
			if (indirizzo.getTipo() == tipoIndirizzo.Principale)
			{
				textFieldVia.setText(indirizzo.getVia());
				textFieldCitt‡.setText(indirizzo.getCitta());
				textFieldNazione.setText(indirizzo.getNazione());
				textFieldCap.setText(indirizzo.getCap());
			}
			else 
			{
				JPanel elemento;
				JButton btnCancella     = new JButton();
				
				elemento = creaElemScrollBar(indirizzo.getVia(), indirizzo.getCitta(), indirizzo.getNazione(), indirizzo.getCap());
				pannelloScrollIndirizziSec.add(btnCancella);
				pannelloScrollIndirizziSec.add(elemento);
				/* button per cancellare un indirizzo secondario */
				btnCancella.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Si registra la cancellazione di un indirizzo secondario aggiunto alla creazione del frame
						modificato = true;
						
						pannelloScrollIndirizziSec.remove(btnCancella);
						pannelloScrollIndirizziSec.remove(elemento);
						revalidate();
						repaint();
					}
				});
			}
		}
		
		// inserimento numeri di telefono del contatto
		boolean flagMobile = false;
		boolean flagFisso  = false;
		
		for (Telefono telefono : contatto.getTelefoni())
		{
			if      (flagMobile == false && telefono.getTipo().equals("Mobile") )
			{
				textFieldNumMobile.setText(telefono.getNumero());
				flagMobile = true;
			}
			else if (flagFisso == false && telefono.getTipo().equals("Fisso") )
			{
				textFieldNumFisso.setText(telefono.getNumero());
				flagFisso = true;
			}
			else 
			{
				JPanel numero;
				JButton btnCancellaNumSec = new JButton();
				
				numero = creaSecNumb(telefono.getTipo(), telefono.getNumero());
				pannelloScrolNumTel.add(btnCancellaNumSec);
				pannelloScrolNumTel.add(numero);
				
				btnCancellaNumSec.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Si registra la cancellazione di un numero aggiunto alla creazione del frame
						modificato = true;
						
						pannelloScrolNumTel.remove(btnCancellaNumSec);
						pannelloScrolNumTel.remove(numero);
						revalidate();
						repaint();
					}
				});
			}
		}
		
		// inserimento email del contatto
		for (Email email : contatto.getEmail())
		{
			JPanel mail;
			int lastMailIndex;;
			JButton btnCancellaMailSec = new JButton();
			
			mail = creaSecMail(email.getTipo(), email.getStringaEmail());
			pannelloScrollMail.add(btnCancellaMailSec);
			pannelloScrollMail.add(mail);
			
			btnCancellaMailSec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Si registra la cancellazione di una email aggiunta alla creazione del frame
					modificato = true;
					
					pannelloScrollMail.remove(btnCancellaMailSec);
					pannelloScrollMail.remove(mail);
					revalidate();
					repaint();
				}
			});
		}
		revalidate();
		repaint();
	}
	
	private JPanel creaElemScrollBar(String fieldVia, String fieldCitt‡, String fieldNazione, String fieldCap)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.setSize(new Dimension(300,300));
		
		JTextField textFieldViaSB;
		JTextField textFieldCitt‡SB;
		JTextField textFieldNazioneSB;
		JTextField textFieldCapSB;

		JLabel lblViaSB = new JLabel("Via");
		panel.add(lblViaSB);

		textFieldViaSB = new JTextField();
		textFieldViaSB.setText(fieldVia);
		panel.add(textFieldViaSB);
		textFieldViaSB.setColumns(10);
									
		JLabel lblCitt‡SB = new JLabel("Citt\u00E0");
		panel.add(lblCitt‡SB);
		
		textFieldCitt‡SB = new JTextField();
		textFieldCitt‡SB.setText(fieldCitt‡);
		panel.add(textFieldCitt‡SB);
		textFieldCitt‡SB.setColumns(10);

		JLabel lblNazioneSB = new JLabel("Nazione");
		panel.add(lblNazioneSB);

		textFieldNazioneSB = new JTextField();
		textFieldNazioneSB.setText(fieldNazione);
		panel.add(textFieldNazioneSB);
		textFieldNazioneSB.setColumns(10);
		
		JLabel lblCapSB = new JLabel("CAP");
		panel.add(lblCapSB);
		
		textFieldCapSB = new JTextField();
		textFieldCapSB.setText(fieldCap);
		panel.add(textFieldCapSB, BorderLayout.WEST);
		textFieldCapSB.setColumns(10);
		
		return panel;
	}
	
	private JPanel creaSecNumb(String fieldTipo, String fieldNum)
	{
		JTextField textFieldDescSB;
		JTextField textFieldNumSB;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescSB = new JTextField();
		textFieldDescSB.setText(fieldTipo);
		panel.add(textFieldDescSB);
		textFieldDescSB.setColumns(5);
																		
		textFieldNumSB = new JTextField();
		textFieldNumSB.setText(fieldNum);
		panel.add(textFieldNumSB);
		textFieldNumSB.setColumns(5);
		
		return  panel;
	}
	
	private JPanel creaSecMail(String fieldTipo, String fieldEmail)
	{
		JTextField textFieldDescMailSB;
		JTextField textFieldMailSB;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescMailSB = new JTextField();
		textFieldDescMailSB.setText(fieldTipo);
		textFieldDescMailSB.setColumns(5);
		panel.add(textFieldDescMailSB);
																		
		textFieldMailSB = new JTextField();
		textFieldMailSB.setText(fieldEmail);
		textFieldMailSB.setColumns(5);
		panel.add(textFieldMailSB);
		
		return  panel;
	}
}