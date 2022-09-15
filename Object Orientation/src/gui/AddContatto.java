package gui;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;

import java.sql.SQLException;

/**
 * Frame che gestisce l'inserimento di un nuovo contatto.
 */
@SuppressWarnings("serial")
public class AddContatto extends JFrame {

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
	
	/** Label num mobile. */
	private JLabel lblNumMobile;
	
	/** Label num fisso. */
	private JLabel lblNumFisso;
	
	/** Label via. */
	private JLabel lblVia;
	
	/** Campo citt‡. */
	private JTextField textFieldCitt‡;
	
	/** Campo nazione. */
	private JTextField textFieldNazione;
	
	/** Campo CAP. */
	private JTextField textFieldCap;
	
	/** Pannello indirizzo principale. */
	private JPanel pannelloIndPrincipale;
	
	/** Pannello numero telefono. */
	private JPanel pannelloNumTel;
	
	/** Pannello numeri telefono secondari. */
	private JPanel pannelloNumTelSec;
	
	/** Pannello email secondarie. */
	private JPanel pannelloEmailAddSec;
	
	/** Label numeri secondari. */
	private JLabel lblNumSecondari;
	
	/** Label email secondarie. */
	private JLabel lblEmailSecondarie;
	
	/** Pannello scroll numeri telefono secondari. */
	private JPanel pannelloScrolNumTel;
	
	/** Pannello scroll mail secondarie. */
	private JPanel pannelloScrollMail;
	
	/** Label immagine. */
	private JLabel lblImmagine;
	
	/** Percorso immagine. */
	private String percorsoImmagine = null;

	/**
	 * Costruttore di un nuovo frame AddContatto.
	 *
	 * @param c controller
	 * @param frameChiamante frame chiamante
	 * @param lista lista di Contatti da aggiornare col nuovo contatto aggiunto
	 */
	public AddContatto(Controller c, JFrame frameChiamante, JList<Object> lista) {
		
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;

		frame.setTitle("Aggiunta di un nuovo contatto");
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 488);
		contentPane = new JPanel();
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);

		/**
		 * Inserimento
		 */
		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setForeground(new Color(102, 102, 153));
		lblNumeriTelefono.setBounds(298, 107, 112, 13);
		contentPane.add(lblNumeriTelefono);

		JLabel lblIndirizzoFisico = new JLabel("Indirizzo Principale\r\n");
		lblIndirizzoFisico.setForeground(new Color(102, 102, 153));
		lblIndirizzoFisico.setBounds(34, 107, 111, 13);
		contentPane.add(lblIndirizzoFisico);
		
		/**
		 * Indirizzi fisici secondari
		 */
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setBounds(10, 253, 249, 119);
		contentPane.add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pannelloScrollIndirizziSec = new JPanel();
		pannelloScrollIndirizziSec.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloScrollIndirizziSec.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloScrollIndirizziSec.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPane = new JScrollPane(pannelloScrollIndirizziSec);
		scrollPane.setBackground(new Color(255, 255, 255));
		pannelloScrollIndirizziSec.setLayout(new BoxLayout(pannelloScrollIndirizziSec, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(pannelloScrollIndirizziSec.getSize());
		panelMain.add(scrollPane, BorderLayout.CENTER);
		
		/**
		 * Crea button "+" per aggiungere indirizzo secondario
		 */
		JButton btnAggiungiIndirizzo = new JButton("+");
		btnAggiungiIndirizzo.setFocusPainted(false);
		btnAggiungiIndirizzo.setForeground(new Color(102, 102, 153));
		btnAggiungiIndirizzo.setBackground(new Color(204, 255, 255));
		btnAggiungiIndirizzo.setBounds(10, 375, 45, 23);
		contentPane.add(btnAggiungiIndirizzo);
		
		btnAggiungiIndirizzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel elemento;
				JButton btnCancella     = new JButton();
				btnCancella.setBackground(new Color(204,255,255));
				btnCancella.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
				btnCancella.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
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
					elemento = creaSecIndirizzo(fieldVia.getText(), fieldCitt‡.getText(), fieldNazione.getText(), fieldCap.getText());
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
		pannelloIndPrincipale = new JPanel();
		pannelloIndPrincipale.setBackground(new Color(255, 255, 255));
		pannelloIndPrincipale.setBounds(10, 123, 234, 90);
		contentPane.add(pannelloIndPrincipale);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
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
		
		JPanel pannelloCredUtente = new JPanel();
		pannelloCredUtente.setBackground(new Color(255, 255, 255));
		pannelloCredUtente.setBounds(10, 39, 471, 58);
		contentPane.add(pannelloCredUtente);
		pannelloCredUtente.setLayout(null);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(318, 27, 86, 20);
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
		lblLabelNome.setBounds(35, 11, 96, 13);
		pannelloCredUtente.add(lblLabelNome);
		lblLabelNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * Indirizzi Secondari
		 */
		JLabel lblNewLabel = new JLabel("Indirizzi Secondari");
		lblNewLabel.setForeground(new Color(102, 102, 153));
		lblNewLabel.setBounds(34, 228, 112, 14);
		contentPane.add(lblNewLabel);
												
		pannelloNumTel = new JPanel();
		pannelloNumTel.setBackground(new Color(255, 255, 255));
		pannelloNumTel.setBounds(254, 123, 187, 67);
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
		lblNumSecondari.setBounds(338, 228, 112, 14);
		contentPane.add(lblNumSecondari);
		
		pannelloNumTelSec = new JPanel();
		pannelloNumTelSec.setBackground(new Color(255, 255, 255));
		pannelloNumTelSec.setBounds(294, 253, 187, 119);
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
				btnCancellaNumSec.setBackground(new Color(204,255,255));
				btnCancellaNumSec.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
				btnCancellaNumSec.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
				JTextField fieldTipo       = new JTextField();
				JTextField fieldNum      = new JTextField();
				Object[] message = {
				    "Inserisci il tipo     :", fieldTipo,
				    "Inserisci il numero   :", fieldNum,
				};
				
				
				int option = JOptionPane.showConfirmDialog(null, message, "Riempire i campi", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					// controlli sulla validit‡ dell'inserimento
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
		btnAddSecNum.setBounds(294, 375, 45, 23);
		contentPane.add(btnAddSecNum);
		
		/**
		 *  Aggiungi mail secondarie
		 */
		lblEmailSecondarie = new JLabel("Indirizzi Mail \r\n");
		lblEmailSecondarie.setForeground(new Color(102, 102, 153));
		lblEmailSecondarie.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailSecondarie.setBounds(559, 228, 134, 14);
		contentPane.add(lblEmailSecondarie);
		
		pannelloEmailAddSec = new JPanel();
		pannelloEmailAddSec.setBackground(new Color(255, 255, 255));
		pannelloEmailAddSec.setBounds(519, 253, 205, 119);
		contentPane.add(pannelloEmailAddSec);
		pannelloEmailAddSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEmail = new JScrollPane();
		scrollPaneEmail.setBackground(new Color(255, 255, 255));
		pannelloEmailAddSec.add(scrollPaneEmail, BorderLayout.CENTER);
		
		pannelloScrollMail = new JPanel();
		scrollPaneEmail.setViewportView(pannelloScrollMail);
		pannelloScrollMail.setBackground(new Color(255, 255, 255));
		pannelloScrollMail.setLayout(new BoxLayout(pannelloScrollMail, BoxLayout.PAGE_AXIS));
		
		/**
		 * Button "+" aggiungi mail secondaria
		 */
		JButton btnAddSecMail = new JButton("+");
		btnAddSecMail.setFocusPainted(false);
		btnAddSecMail.setForeground(new Color(102, 102, 153));
		btnAddSecMail.setBackground(new Color(204, 255, 255));
		btnAddSecMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel mail;
				JButton btnCancellaMailSec = new JButton();
				btnCancellaMailSec.setBackground(new Color(204,255,255));
				btnCancellaMailSec.setToolTipText("Premere per eliminare interamente l'elemento sottostante appena inserito e non ancora aggiunto in memoria.");
				btnCancellaMailSec.setToolTipText("Permette di eliminare interamente l'elemento sottostante");
				JTextField fieldTipo     = new JTextField();
				JTextField fieldEmail      = new JTextField();
				Object[] message = {
				    "Inserisci il tipo     :", fieldTipo,
				    "Inserisci l'email     :", fieldEmail,
				};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Riempire i campi", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
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
					revalidate();
					repaint();
				}
			}
		});
		btnAddSecMail.setBounds(519, 375, 45, 23);
		contentPane.add(btnAddSecMail);
		
		/**
		 * Button "annulla"
		 */
		JButton btnIndietro = new JButton("Annulla");
		btnIndietro.setFocusPainted(false);
		btnIndietro.setForeground(new Color(204, 255, 255));
		btnIndietro.setBackground(new Color(102, 102, 153));
		btnIndietro.setBounds(10, 420, 85, 21);
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
		JButton btnInserisci = new JButton("Vai");
		btnInserisci.setToolTipText("Aggiunge il nuovo contatto con tutte le informazioni inserite nella rubrica.");
		btnInserisci.setFocusPainted(false);
		btnInserisci.setForeground(new Color(102, 102, 153));
		btnInserisci.setBackground(new Color(204, 255, 255));
		btnInserisci.setBounds(639, 420, 85, 21);
		contentPane.add(btnInserisci);
		btnInserisci.addActionListener(new ActionListener() {
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
					try {
						// inserimento contatto
						controller.addContatto(textFieldNome,textFieldSecondoNome, textFieldCognome, textFieldVia, textFieldCitt‡, textFieldNazione, textFieldCap,
												textFieldNumMobile, textFieldNumFisso, percorsoImmagine,
												pannelloScrollIndirizziSec, pannelloScrolNumTel, pannelloScrollMail);
						
						// aggiornamento della combobox di listaContatti
						lista.removeAll();
						if (controller.getGruppoSelezionato() != null)
						{
							lista.setListData(controller.getNomiContattiGruppoSelezionato());
						}
						else {
							lista.setListData(controller.getNomiContattiRubrica());
						}						JOptionPane.showConfirmDialog(null, 
				                "Contatto inserito con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
						lista.revalidate();
						lista.repaint();
						frameChiamante.setVisible(true);
						frame.dispose();
					} catch (SQLException es) {
						es.printStackTrace();
						JOptionPane.showMessageDialog(null, es.getMessage(),
							      "Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
						System.out.println("Non Ë stato possibile inserire il contatto in memoria");
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(),
							      "Errore di inserimento", JOptionPane.ERROR_MESSAGE);
						System.out.println("Non Ë stato possibile inserire il contatto in memoria");
					}
				}
				else {
					System.out.println("I dati non sono stati inseriti correttamente");
				}
				
			}
		});
		
		// Per l'immagine
		lblImmagine      = new JLabel("");
		lblImmagine.setBorder(new LineBorder(new Color(0, 0, 0)));
		Image img        = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
		Image imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
		lblImmagine.setBackground(Color.LIGHT_GRAY);
		lblImmagine.setBounds(538, 50, 150, 127);
		lblImmagine.setIcon(new ImageIcon(imgResized));
		contentPane.add(lblImmagine);
		
		JButton btnRimuoviImmagine = new JButton("Rimuovi");
		btnRimuoviImmagine.setToolTipText("Rimuove la precedente immagine del contatto e ne associa una di default");
		btnRimuoviImmagine.setForeground(new Color(102, 102, 153));
		btnRimuoviImmagine.setBackground(new Color(204, 255, 255));
		btnRimuoviImmagine.setFocusPainted(false);
		btnRimuoviImmagine.setBounds(614, 188, 85, 23);
		contentPane.add(btnRimuoviImmagine);
		btnRimuoviImmagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image img        = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
				Image imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
				lblImmagine.setIcon(new ImageIcon(imgResized));
				percorsoImmagine = null;
			}
		});
		
		JButton btnScegliImmagine = new JButton("Scegli");
		btnScegliImmagine.setToolTipText("Permette di scegliere l'immagine da associare al contatto da qualsiasi directory");
		btnScegliImmagine.setForeground(new Color(102, 102, 153));
		btnScegliImmagine.setBackground(new Color(204, 255, 255));
		btnScegliImmagine.setFocusPainted(false);
		btnScegliImmagine.setBounds(528, 188, 85, 23);
		contentPane.add(btnScegliImmagine);
		
		JLabel lblImg = new JLabel("Immagine del contatto");
		lblImg.setForeground(new Color(102, 102, 153));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBounds(539, 20, 150, 13);
		contentPane.add(lblImg);
		
		/**
		 *  Quando cliccato button "Scegli"
		 */
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
				}
			}
		});
	}
	
	/**
	 * Crea un pannello di indirizzi secondari.
	 *
	 * @param fieldVia campo via
	 * @param fieldCitt‡ campo citt‡
	 * @param fieldNazione campo nazione
	 * @param fieldCap campo CAP
	 * @return il JPanel di indirizzi secondari
	 */
	private JPanel creaSecIndirizzo(String fieldVia, String fieldCitt‡, String fieldNazione, String fieldCap)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.setSize(new Dimension(300,300));
		
		JTextField textFieldViaSB;
		JTextField textFieldCitt‡SB;
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
									
		JLabel lblCitt‡SB = new JLabel("Citt\u00E0");
		lblCitt‡SB.setOpaque(true);
		lblCitt‡SB.setBackground(new Color(255, 255, 255));
		panel.add(lblCitt‡SB);
		
		textFieldCitt‡SB = new JTextField();
		textFieldCitt‡SB.setText(fieldCitt‡);
		textFieldCitt‡SB.setEditable(false);
		textFieldCitt‡SB.setBackground(new Color(255, 255, 255));
		panel.add(textFieldCitt‡SB);
		textFieldCitt‡SB.setCaretPosition(0);
		textFieldCitt‡SB.setColumns(10);

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
