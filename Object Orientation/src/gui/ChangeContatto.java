package gui;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;

import java.io.File;

import java.sql.SQLException;

/**
 * Frame che gestisce la modifica delle informazioni di un contatto.
 */
@SuppressWarnings("serial")
public class ChangeContatto extends JFrame {

	/** Controller. */
	private Controller controller;
	
	/** Questo frame. */
	private JFrame frame;
	
	/** Pannello dei contenuti. */
	private JPanel contentPane;
	
	/** Campo nome. */
	private JTextField textFieldNome;
	
	/** Campo secondo nome. */
	private JTextField textFieldSecondoNome;
	
	/** Campo cognome. */
	private JTextField textFieldCognome;
	
	/** Label titolo. */
	private JLabel lblTitolo;
	
	/** Label nome. */
	private JLabel lblLabelNome;
	
	/** Label secondo nome. */
	private JLabel lblSecondoNome;
	
	/** Label cognome. */
	private JLabel lblCognome;
	
	/** Campo numero fisso. */
	private JTextField textFieldNumFisso;
	
	/** Campo numero mobile. */
	private JTextField textFieldNumMobile;
	
	/** Campo via. */
	private JTextField textFieldVia;
	
	/** Label numero mobile. */
	private JLabel lblNumMobile;
	
	/** Label numero fisso. */
	private JLabel lblNumFisso;
	
	/** Label via. */
	private JLabel lblVia;
	
	/** Campo città. */
	private JTextField textFieldCittà;
	
	/** Campo nazione. */
	private JTextField textFieldNazione;
	
	/** Campo CAP. */
	private JTextField textFieldCap;
	
	/** Pannello numero telefono. */
	private JPanel pannelloNumTel;
	
	/** Pannello numeri telfono secondari. */
	private JPanel pannelloNumTelSec;
	
	/** Pannello email secondarie. */
	private JPanel pannelloEmailAddSec;
	
	/** Label numeri secondari. */
	private JLabel lblNumSecondari;
	
	/** Label email secondarie. */
	private JLabel lblEmailSecondarie;
	
	/** Pannello scrol numeri telefono secondari. */
	private JPanel pannelloScrolNumTel;
	
	/** Pannello scroll mail secondarie. */
	private JPanel pannelloScrollMail;
	
	/** Label immagine. */
	private JLabel lblImmagine;
	
	/** Percorso immagine. */
	private String percorsoImmagine = null;
	
	/** flag di controllo se sono state rinvenute modifiche da parte dell'utente. */
	private boolean modificato = false;
	
	/**
	 * Costruttore di un nuovo frame ChangeContatto.
	 *
	 * @param c controller
	 * @param frameChiamante frame chiamante
	 * @param lista lista di Contatti da aggiornare col nuovo contatto modificato
	 */
	public ChangeContatto(Controller c, JFrame frameChiamante, JList<Object> lista) {
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;
		if (controller.getContattoSelezionato().getSecondoNome() == null)
		{
			frame.setTitle("Modifica del Contatto " + controller.getContattoSelezionato().getNome() + " " + controller.getContattoSelezionato().getCognome());
		}
		else 
		{
			frame.setTitle("Modifica del Contatto " + controller.getContattoSelezionato().getNome() + " " +  controller.getContattoSelezionato().getSecondoNome() + " " + controller.getContattoSelezionato().getCognome());
		}
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);

		lblTitolo = new JLabel("Inserire informazioni del contatto da cambiare:");
		lblTitolo.setForeground(new Color(0, 0, 0));
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitolo.setBounds(10, 10, 386, 19);
		contentPane.add(lblTitolo);
		
		/**
		 * Inserimento
		 */
		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setForeground(new Color(102, 102, 153));
		lblNumeriTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeriTelefono.setBounds(297, 128, 187, 13);
		contentPane.add(lblNumeriTelefono);

		JLabel lblIndirizzoFisico = new JLabel("Indirizzo Principale\r\n");
		lblIndirizzoFisico.setForeground(new Color(102, 102, 153));
		lblIndirizzoFisico.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndirizzoFisico.setBounds(38, 128, 234, 13);
		contentPane.add(lblIndirizzoFisico);

		
		/**
		 * Indirizzi fisici secondari
		 */
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setBounds(38, 270, 228, 119);
		contentPane.add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		JPanel pannelloScrollIndirizziSec = new JPanel();
		pannelloScrollIndirizziSec.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloScrollIndirizziSec.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloScrollIndirizziSec.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPane = new JScrollPane(pannelloScrollIndirizziSec);
		pannelloScrollIndirizziSec.setLayout(new BoxLayout(pannelloScrollIndirizziSec, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(pannelloScrollIndirizziSec.getSize());
		panelMain.add(scrollPane, BorderLayout.CENTER);
		
		/**
		 * Crea button "+" per aggiungere indirizzo secondario
		 */
		JButton btnAggiungiIndirizzo = new JButton("+");
		btnAggiungiIndirizzo.setForeground(new Color(102, 102, 153));
		btnAggiungiIndirizzo.setBackground(new Color(204, 255, 255));
		btnAggiungiIndirizzo.setBounds(38, 392, 45, 23);
		btnAggiungiIndirizzo.setFocusPainted(false);
		contentPane.add(btnAggiungiIndirizzo);
		
		btnAggiungiIndirizzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel elemento;
				JButton btnCancella     = new JButton();
				btnCancella.setBackground(Color.GREEN);
				btnCancella.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
				JTextField fieldVia     = new JTextField();
				JTextField fieldCittà   = new JTextField();
				JTextField fieldNazione = new JTextField();
				JTextField fieldCap     = new JTextField();
				Object[] message = {
				    "Inserisci la via     :", fieldVia,
				    "Inserisci la città   :", fieldCittà,
				    "Inserisci la nazione :", fieldNazione,
				    "Inserisci il Cap     :", fieldCap,
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Riempire i campi", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					// Si registra l'aggiunta di un nuovo indirizzo secodnario
					modificato = true;
					
					elemento = creaSecIndirizzo(fieldVia.getText(), fieldCittà.getText(), fieldNazione.getText(), fieldCap.getText());
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
					revalidate();
					repaint();
				}
			}
		});
		
		/**
		 * Indirizzo principale
		 */
		JPanel pannelloIndPrincipale = new JPanel();
		pannelloIndPrincipale.setBackground(new Color(255, 255, 255));
		pannelloIndPrincipale.setBounds(38, 144, 234, 90);
		contentPane.add(pannelloIndPrincipale);
		pannelloIndPrincipale.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 5, 192, 76);
		pannelloIndPrincipale.add(panel);
		panel.setLayout(new GridLayout(4,2));

		lblVia = new JLabel("Via");
		lblVia.setOpaque(true);
		lblVia.setBackground(new Color(255, 255, 255));
		panel.add(lblVia);
		
		textFieldVia = new JTextField();
		panel.add(textFieldVia);
		textFieldVia.setColumns(10);
									
		JLabel lblCittà = new JLabel("Citt\u00E0");
		lblCittà.setOpaque(true);
		lblCittà.setBackground(new Color(255, 255, 255));
		panel.add(lblCittà);
		
		textFieldCittà = new JTextField();
		panel.add(textFieldCittà);
		textFieldCittà.setColumns(10);

		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setOpaque(true);
		lblNazione.setBackground(new Color(255, 255, 255));
		panel.add(lblNazione);

		textFieldNazione = new JTextField();
		panel.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setOpaque(true);
		lblCap.setBackground(new Color(255, 255, 255));
		panel.add(lblCap);
		
		textFieldCap = new JTextField();
		panel.add(textFieldCap, BorderLayout.WEST);
		textFieldCap.setColumns(10);
		
		JPanel pannelloCredUtente = new JPanel();
		pannelloCredUtente.setBackground(new Color(255, 255, 255));
		pannelloCredUtente.setBounds(10, 49, 471, 58);
		contentPane.add(pannelloCredUtente);
		pannelloCredUtente.setLayout(null);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(318, 27, 96, 20);
		pannelloCredUtente.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		lblCognome = new JLabel("Cognome ");
		lblCognome.setForeground(new Color(102, 102, 153));
		lblCognome.setBounds(308, 11, 96, 13);
		pannelloCredUtente.add(lblCognome);
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
				
		textFieldSecondoNome = new JTextField();
		textFieldSecondoNome.setBounds(172, 27, 96, 19);
		pannelloCredUtente.add(textFieldSecondoNome);
		textFieldSecondoNome.setColumns(10);
						
		lblSecondoNome = new JLabel("Secondo Nome");
		lblSecondoNome.setForeground(new Color(102, 102, 153));
		lblSecondoNome.setBounds(172, 11, 96, 13);
		pannelloCredUtente.add(lblSecondoNome);
		lblSecondoNome.setHorizontalAlignment(SwingConstants.CENTER);
								
		textFieldNome = new JTextField();
		textFieldNome.setBounds(45, 27, 90, 19);
		pannelloCredUtente.add(textFieldNome);
		textFieldNome.setColumns(10);
										
		lblLabelNome = new JLabel("Nome ");
		lblLabelNome.setForeground(new Color(102, 102, 153));
		lblLabelNome.setBackground(new Color(240, 240, 240));
		lblLabelNome.setBounds(35, 11, 96, 13);
		pannelloCredUtente.add(lblLabelNome);
		lblLabelNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * Indirizzi Secondari
		 */
		JLabel lblNewLabel = new JLabel("Indirizzi Secondari");
		lblNewLabel.setForeground(new Color(102, 102, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(38, 245, 249, 14);
		contentPane.add(lblNewLabel);
												
		pannelloNumTel = new JPanel();
		pannelloNumTel.setBackground(new Color(255, 255, 255));
		pannelloNumTel.setBounds(297, 144, 187, 67);
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
		lblNumSecondari.setForeground(new Color(102, 102, 153));
		lblNumSecondari.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumSecondari.setBounds(297, 245, 187, 14);
		contentPane.add(lblNumSecondari);
		
		pannelloNumTelSec = new JPanel();
		pannelloNumTelSec.setBackground(new Color(255, 255, 255));
		pannelloNumTelSec.setBounds(297, 270, 187, 119);
		contentPane.add(pannelloNumTelSec);
		pannelloNumTelSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNumTel = new JScrollPane();
		pannelloNumTelSec.add(scrollPaneNumTel, BorderLayout.CENTER);
		scrollPaneNumTel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneNumTel.setForeground(Color.GREEN);
		scrollPaneNumTel.setBackground(new Color(255, 255, 255));
		
		pannelloScrolNumTel = new JPanel();
		pannelloScrolNumTel.setBackground(new Color(255, 255, 255));
		scrollPaneNumTel.setViewportView(pannelloScrolNumTel);
		pannelloScrolNumTel.setLayout(new BoxLayout(pannelloScrolNumTel, BoxLayout.PAGE_AXIS));
		
		JLabel lblImg = new JLabel("Immagine del contatto");
		lblImg.setForeground(new Color(102, 102, 153));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBounds(563, 16, 171, 13);
		contentPane.add(lblImg);
		
		/**
		 * Button "+" aggiungi numero secondario
		 */
		
		JButton btnAddSecNum = new JButton("+");
		btnAddSecNum.setFocusPainted(false);
		btnAddSecNum.setForeground(new Color(102, 102, 153));
		btnAddSecNum.setBackground(new Color(204, 255, 255));
		btnAddSecNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel numero;
				JButton btnCancellaNumSec  = new JButton();
				btnCancellaNumSec.setBackground(Color.GREEN);
				btnCancellaNumSec.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
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
					
					// controlli sulla validità dell'inserimento
					numero = creaSecNumb(fieldTipo.getText(), fieldNum.getText());
					numero.setMaximumSize(new Dimension(500, 20));

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
					revalidate();
					repaint();
				}
			}
		});
		btnAddSecNum.setBounds(297, 392, 45, 23);
		contentPane.add(btnAddSecNum);
		
		/**
		 *  Aggiungi mail secondarie
		 */
		
		lblEmailSecondarie = new JLabel("Indirizzi Mail \r\n");
		lblEmailSecondarie.setForeground(new Color(102, 102, 153));
		lblEmailSecondarie.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailSecondarie.setBounds(524, 245, 205, 14);
		contentPane.add(lblEmailSecondarie);
		
		pannelloEmailAddSec = new JPanel();
		pannelloEmailAddSec.setBackground(new Color(255, 255, 255));
		pannelloEmailAddSec.setBounds(524, 270, 243, 119);
		contentPane.add(pannelloEmailAddSec);
		pannelloEmailAddSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEmail = new JScrollPane();
		pannelloEmailAddSec.add(scrollPaneEmail, BorderLayout.CENTER);
		
		pannelloScrollMail = new JPanel();
		scrollPaneEmail.setViewportView(pannelloScrollMail);
		pannelloScrollMail.setBackground(new Color(255, 255, 255));
		pannelloScrollMail.setLayout(new BoxLayout(pannelloScrollMail, BoxLayout.PAGE_AXIS));
		
		inizializzaContatto(textFieldNome,textFieldSecondoNome, textFieldCognome, textFieldVia, textFieldCittà, textFieldNazione, textFieldCap,
							textFieldNumMobile, textFieldNumFisso, pannelloScrollIndirizziSec, pannelloScrolNumTel, pannelloScrollMail);
		
		/**
		 * Button "+" aggiungi email secondaria
		 */
		
		JButton btnAddSecMail = new JButton("+");
		btnAddSecMail.setFocusPainted(false);
		btnAddSecMail.setForeground(new Color(102, 102, 153));
		btnAddSecMail.setBackground(new Color(204, 255, 255));
		btnAddSecMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel mail;
				JButton btnCancellaMailSec = new JButton();
				btnCancellaMailSec.setBackground(Color.GREEN);
				btnCancellaMailSec.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
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
					
					mail = creaSecMail(fieldTipo.getText(), fieldEmail.getText());
					mail.setMaximumSize(new Dimension(500, 20));
					pannelloScrollMail.add(btnCancellaMailSec);
					pannelloScrollMail.add(mail);
					
					btnCancellaMailSec.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {							
							pannelloScrollMail.remove(btnCancellaMailSec);
							pannelloScrollMail.remove(mail);
							pannelloScrollMail.revalidate();
							pannelloScrollMail.repaint();
						}
					});
					revalidate();
					repaint();
				}
			}
		});
		btnAddSecMail.setBounds(526, 392, 45, 23);
		contentPane.add(btnAddSecMail);
		
		/**
		 * Button "annulla"
		 */
		JButton btnIndietro = new JButton("Annulla");
		btnIndietro.setFocusPainted(false);
		btnIndietro.setFont(new Font("Arial", Font.PLAIN, 11));
		btnIndietro.setForeground(new Color(204, 255, 255));
		btnIndietro.setBackground(new Color(102, 102, 153));
		btnIndietro.setBounds(10, 432, 85, 21);
		contentPane.add(btnIndietro);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		
		/**
		 * Button "vai"
		 */
		JButton btnModifica = new JButton("Vai");
		btnModifica.setToolTipText("Modifica il contatto esistente con le informazioni cambiate.\r\n");
		btnModifica.setFocusPainted(false);
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModifica.setForeground(new Color(102, 102, 153));
		btnModifica.setBackground(new Color(204, 255, 255));
		btnModifica.setBounds(682, 432, 85, 21);
		contentPane.add(btnModifica);
		
		
		/**
		 * Quando viene cliccato il button "vai"
		 */
		btnModifica.addActionListener(new ActionListener() {
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
				if (textFieldCittà.getText().isBlank())
				{
					textFieldCittà.setBackground(Color.RED);
					valido = 0;
				}
				else {
					textFieldCittà.setBackground(Color.WHITE);
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
					
						try {
							// modifica contatto
							controller.changeContatto(textFieldNome,textFieldSecondoNome, textFieldCognome, textFieldVia, textFieldCittà, textFieldNazione,
													  textFieldCap, textFieldNumMobile, textFieldNumFisso, percorsoImmagine,
													  pannelloScrollIndirizziSec, pannelloScrolNumTel, pannelloScrollMail);
							// aggiornamento della combobox di listaContatti
							lista.removeAll();
							if (controller.getGruppoSelezionato() != null)
							{
								lista.setListData(controller.getNomiContattiGruppoSelezionato());
							}
							else {
								lista.setListData(controller.getNomiContattiRubrica());
							}
							JOptionPane.showConfirmDialog(null, 
					                "Informazioni aggiornate con successo!", "Modifica avvenuta", JOptionPane.DEFAULT_OPTION);
							lista.revalidate();
							lista.repaint();
							frameChiamante.setVisible(true);
							frame.dispose();
						} catch (SQLException es) {
							es.printStackTrace();
							JOptionPane.showMessageDialog(null, es.getMessage(),
								      "Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
							System.out.println("Non è stato possibile inserire il contatto in memoria");
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(),
								      "Errore di inserimento", JOptionPane.ERROR_MESSAGE);
							System.out.println("Non è stato possibile inserire il contatto in memoria");
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
		
		textFieldCittà.addKeyListener(new KeyAdapter() {
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
		btnRimuoviImmagine.setToolTipText("Rimuove la precedente immagine del contatto e ne associa una di default");
		btnRimuoviImmagine.setBackground(new Color(204, 255, 255));
		btnRimuoviImmagine.setForeground(new Color(102, 102, 153));
		btnRimuoviImmagine.setFocusPainted(false);
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
		btnScegliImmagine.setToolTipText("Permette di scegliere l'immagine da associare al contatto da qualsiasi directory");
		btnScegliImmagine.setBackground(new Color(204, 255, 255));
		btnScegliImmagine.setForeground(new Color(102, 102, 153));
		btnScegliImmagine.setFocusPainted(false);
		btnScegliImmagine.setBounds(563, 195, 85, 23);
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
					// se l'immagine selezionata è diversa da quella di partenza, allora si registra la modifica
					if (percorsoImmagine != controller.getContattoSelezionato().getPathImmagine())
					{
						modificato = true;
					}
				}
			}
		});

	}

	/**
	 * Riempie i campi del frame alla sua costruzione.
	 *
	 * @param textFieldNome campo nome
	 * @param textFieldSecondoNome campo secondo nome
	 * @param textFieldCognome campo cognome
	 * @param textFieldVia campo via
	 * @param textFieldCittà campo città
	 * @param textFieldNazione campo nazione
	 * @param textFieldCap campo CAP
	 * @param textFieldNumMobile campo numero mobile
	 * @param textFieldNumFisso campo numero fisso
	 * @param pannelloScrollIndirizziSec pannello indirizzi secsecondari
	 * @param pannelloScrolNumTel pannello numeri telefono secondari
	 * @param pannelloScrollMail pannello mail secondarie
	 */
	public void inizializzaContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
									 JTextField textFieldCittà, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
									 JTextField textFieldNumFisso, JPanel pannelloScrollIndirizziSec, JPanel pannelloScrolNumTel, JPanel pannelloScrollMail)
	{
		boolean flagMobile;
		boolean flagFisso;
		Image img;
		Image imgResized;
		
		// inserimento nome contatto
		textFieldNome.setText(controller.getContattoSelezionato().getNome());
		textFieldSecondoNome.setText(controller.getContattoSelezionato().getSecondoNome());
		textFieldCognome.setText(controller.getContattoSelezionato().getCognome());
		
		// inserimento dati contatto
		lblImmagine = new JLabel("");
		if (controller.getContattoSelezionato().getPathImmagine() == null || !new File(controller.getContattoSelezionato().getPathImmagine()).exists())
		{
			// immagine di default
			img        = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
			imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBounds(573, 57, 150, 127);
			lblImmagine.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		else {
			// immagine caricata
			img        = new ImageIcon(controller.getContattoSelezionato().getPathImmagine()).getImage();
			imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBounds(573, 57, 150, 127);
			lblImmagine.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		
		// inserimento indirizzi contatto
		for (int i = 0; i < controller.getContattoSelezionato().getIndirizzi().size(); i++)
		{
			if (controller.checkSeTipoIndirizzoPrincipale(controller.getContattoSelezionato().getIndirizzi().get(i).getTipo()))
			{
				textFieldVia.setText(controller.getContattoSelezionato().getIndirizzi().get(i).getVia());
				textFieldCittà.setText(controller.getContattoSelezionato().getIndirizzi().get(i).getCitta());
				textFieldNazione.setText(controller.getContattoSelezionato().getIndirizzi().get(i).getNazione());
				textFieldCap.setText(controller.getContattoSelezionato().getIndirizzi().get(i).getCap());
			}
			else 
			{
				JPanel elemento;
				JButton btnCancella     = new JButton();
				btnCancella.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
				btnCancella.setBackground(new Color(204,255,255));
				
				elemento = creaSecIndirizzo(controller.getContattoSelezionato().getIndirizzi().get(i).getVia(),
											controller.getContattoSelezionato().getIndirizzi().get(i).getCitta(),
											controller.getContattoSelezionato().getIndirizzi().get(i).getNazione(),
											controller.getContattoSelezionato().getIndirizzi().get(i).getCap());
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
		flagMobile = false;
		flagFisso  = false;
		
		for (int i = 0; i < controller.getContattoSelezionato().getTelefoni().size(); i++)
		{
			if      (flagMobile == false && controller.getContattoSelezionato().getTelefoni().get(i).getTipo().equals("Mobile") )
			{
				textFieldNumMobile.setText(controller.getContattoSelezionato().getTelefoni().get(i).getNumero());
				flagMobile = true;
			}
			else if (flagFisso == false && controller.getContattoSelezionato().getTelefoni().get(i).getTipo().equals("Fisso") )
			{
				textFieldNumFisso.setText(controller.getContattoSelezionato().getTelefoni().get(i).getNumero());
				flagFisso = true;
			}
			else 
			{
				JPanel numero;
				JButton btnCancellaNumSec = new JButton();
				btnCancellaNumSec.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
				btnCancellaNumSec.setBackground(new Color(204,255,255));
				
				numero = creaSecNumb(controller.getContattoSelezionato().getTelefoni().get(i).getTipo(),
						             controller.getContattoSelezionato().getTelefoni().get(i).getNumero());
				numero.setMaximumSize(new Dimension(500, 20));
				
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
		for (int i = 0; i < controller.getContattoSelezionato().getEmail().size(); i++)
		{
			JPanel mail;
			JButton btnCancellaMailSec = new JButton();
			btnCancellaMailSec.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
			btnCancellaMailSec.setBackground(new Color(204,255,255));
			
			mail = creaSecMail(controller.getContattoSelezionato().getEmail().get(i).getTipo(),
							   controller.getContattoSelezionato().getEmail().get(i).getStringaEmail());
			mail.setMaximumSize(new Dimension(500, 20));
			
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
	}
	
	/**
	 * Crea un pannello di indirizzi secondari.
	 *
	 * @param fieldVia campo via
	 * @param fieldCittà campo città
	 * @param fieldNazione campo nazione
	 * @param fieldCap campo CAP
	 * @return il JPanel di indirizzi secondari
	 */
	private JPanel creaSecIndirizzo(String fieldVia, String fieldCittà, String fieldNazione, String fieldCap)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.setSize(new Dimension(300,300));
		
		JTextField textFieldViaSB;
		JTextField textFieldCittàSB;
		JTextField textFieldNazioneSB;
		JTextField textFieldCapSB;

		JLabel lblViaSB = new JLabel("Via");
		lblViaSB.setOpaque(true);
		lblViaSB.setBackground(new Color(255, 255, 255));
		panel.add(lblViaSB);

		textFieldViaSB = new JTextField();
		textFieldViaSB.setText(fieldVia);
		textFieldViaSB.setEditable(false);
		textFieldViaSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldViaSB);
		textFieldViaSB.setCaretPosition(0);
		textFieldViaSB.setColumns(10);
									
		JLabel lblCittàSB = new JLabel("Citt\u00E0");
		lblCittàSB.setOpaque(true);
		lblCittàSB.setBackground(new Color(255, 255, 255));
		panel.add(lblCittàSB);
		
		textFieldCittàSB = new JTextField();
		textFieldCittàSB.setText(fieldCittà);
		textFieldCittàSB.setEditable(false);
		textFieldCittàSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldCittàSB);
		textFieldCittàSB.setCaretPosition(0);
		textFieldCittàSB.setColumns(10);

		JLabel lblNazioneSB = new JLabel("Nazione");
		lblNazioneSB.setOpaque(true);
		lblNazioneSB.setBackground(new Color(255, 255, 255));
		panel.add(lblNazioneSB);

		textFieldNazioneSB = new JTextField();
		textFieldNazioneSB.setText(fieldNazione);
		textFieldNazioneSB.setEditable(false);
		textFieldNazioneSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldNazioneSB);
		textFieldNazioneSB.setCaretPosition(0);
		textFieldNazioneSB.setColumns(10);
		
		JLabel lblCapSB = new JLabel("CAP");
		lblCapSB.setOpaque(true);
		lblCapSB.setBackground(new Color(255, 255, 255));
		panel.add(lblCapSB);
		
		textFieldCapSB = new JTextField();
		textFieldCapSB.setText(fieldCap);
		textFieldCapSB.setEditable(false);
		textFieldCapSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldCapSB, BorderLayout.WEST);
		textFieldCapSB.setCaretPosition(0);
		textFieldCapSB.setColumns(10);
		
		return panel;
	}
	
	/**
	 * Crea un pannello di numeri secondari.
	 *
	 * @param fieldTipo campo tipo
	 * @param fieldNum campo numero
	 * @return il JPanel di numeri secondari
	 */
	private JPanel creaSecNumb(String fieldTipo, String fieldNum)
	{
		JTextField textFieldDescSB;
		JTextField textFieldNumSB;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescSB = new JTextField();
		textFieldDescSB.setText(fieldTipo);
		textFieldDescSB.setEditable(false);
		textFieldDescSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldDescSB);
		textFieldDescSB.setColumns(5);
																		
		textFieldNumSB = new JTextField();
		textFieldNumSB.setText(fieldNum);
		textFieldNumSB.setEditable(false);
		textFieldNumSB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldNumSB);
		textFieldNumSB.setColumns(5);
		
		return  panel;
	}
	
	/**
	 * Crea un pannello di mail secondarie.
	 *
	 * @param campo tipo
	 * @param campo email
	 * @return il JPanel di mail secondarie
	 */
	private JPanel creaSecMail(String fieldTipo, String fieldEmail)
	{
		JTextField textFieldDescMailSB;
		JTextField textFieldMailSB;
		JPanel panel;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		textFieldDescMailSB = new JTextField();
		textFieldDescMailSB.setText(fieldTipo);
		textFieldDescMailSB.setEditable(false);
		textFieldDescMailSB.setBackground(new Color(255, 255, 255));
		textFieldDescMailSB.setColumns(5);
		textFieldDescMailSB.setCaretPosition(0);
		panel.add(textFieldDescMailSB);
																		
		textFieldMailSB = new JTextField();
		textFieldMailSB.setText(fieldEmail);
		textFieldMailSB.setEditable(false);
		textFieldMailSB.setBackground(new Color(255, 255, 255));
		textFieldMailSB.setColumns(5);
		textFieldMailSB.setCaretPosition(0);
		panel.add(textFieldMailSB);
		
		return  panel;
	}
}