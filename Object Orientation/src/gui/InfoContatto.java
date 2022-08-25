package gui;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;

import java.io.File;

@SuppressWarnings("serial")
public class InfoContatto extends JFrame {

	private Controller controller;
	private JFrame frame;
	private JPanel contentPane;
	private JPanel pannelloNumTelSec;
	private JPanel pannelloScrolNumTel;
	private JPanel pannelloScrollMail;
	private JTextField textFieldNome;
	private JTextField textFieldSecondoNome;
	private JTextField textFieldCognome;
	private JTextField textFieldNumFisso;
	private JTextField textFieldNumMobile;
	private JTextField textFieldVia;
	private JTextField textFieldCittà;
	private JTextField textFieldNazione;
	private JTextField textFieldCap;

	public InfoContatto(Controller c, JFrame frameChiamante, JList<Object> lista) {
		
		
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));
		

		frame = this;
		controller = c;
		frame.setTitle("Informazioni del contatto "+controller.getContattoSelezionato().getNome() + " " + controller.getContattoSelezionato().getSecondoNome() + " " + controller.getContattoSelezionato().getCognome());
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 604);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		
		JLabel lblNumeriTelefono = new JLabel("Numeri di telefono");
		lblNumeriTelefono.setForeground(new Color(102, 102, 153));
		lblNumeriTelefono.setBounds(352, 121, 112, 13);
		contentPane.add(lblNumeriTelefono);
		
		JLabel lblIndirizzoFisico = new JLabel("Indirizzo Principale\r\n");
		lblIndirizzoFisico.setForeground(new Color(102, 102, 153));
		lblIndirizzoFisico.setBounds(48, 121, 111, 13);
		contentPane.add(lblIndirizzoFisico);
		
		JPanel pannelloIndPrincipale_1 = new JPanel();
		pannelloIndPrincipale_1.setForeground(new Color(0, 0, 0));
		pannelloIndPrincipale_1.setBackground(new Color(255, 255, 255));
		pannelloIndPrincipale_1.setBounds(24, 137, 249, 90);
		contentPane.add(pannelloIndPrincipale_1);
		
		
		
		
		JPanel pannelloCredUtente = new JPanel();
		pannelloCredUtente.setLayout(null);
		pannelloCredUtente.setBorder(null);
		pannelloCredUtente.setBackground(new Color(255, 255, 255));
		pannelloCredUtente.setBounds(24, 41, 471, 58);
		contentPane.add(pannelloCredUtente);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setOpaque(false);
		textFieldCognome.setEditable(false);
		textFieldCognome.setText((String) null);
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(318, 27, 86, 20);
		pannelloCredUtente.add(textFieldCognome);
		
		JLabel lblCognome_1 = new JLabel("Cognome ");
		lblCognome_1.setForeground(new Color(102, 102, 153));
		lblCognome_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCognome_1.setBounds(308, 11, 96, 13);
		pannelloCredUtente.add(lblCognome_1);
		
		textFieldSecondoNome = new JTextField();
		textFieldSecondoNome.setOpaque(false);
		textFieldSecondoNome.setEditable(false);
		textFieldSecondoNome.setText((String) null);
		textFieldSecondoNome.setColumns(10);
		textFieldSecondoNome.setBounds(172, 27, 96, 19);
		pannelloCredUtente.add(textFieldSecondoNome);
		
		JLabel lblSecondoNome_1 = new JLabel("Secondo Nome");
		lblSecondoNome_1.setForeground(new Color(102, 102, 153));
		lblSecondoNome_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondoNome_1.setBounds(172, 11, 96, 13);
		pannelloCredUtente.add(lblSecondoNome_1);
		
		textFieldNome = new JTextField();
		textFieldNome.setOpaque(false);
		textFieldNome.setEditable(false);
		textFieldNome.setText((String) null);
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(45, 27, 90, 19);
		pannelloCredUtente.add(textFieldNome);
		
		JLabel lblLabelNome_1 = new JLabel("Nome ");
		lblLabelNome_1.setForeground(new Color(102, 102, 153));
		lblLabelNome_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelNome_1.setBounds(35, 11, 96, 13);
		pannelloCredUtente.add(lblLabelNome_1);
		
		JPanel pannelloNumTel_1 = new JPanel();
		pannelloNumTel_1.setLayout(null);
		pannelloNumTel_1.setBackground(new Color(255, 255, 255));
		pannelloNumTel_1.setBounds(308, 137, 189, 58);
		contentPane.add(pannelloNumTel_1);
		
		JLabel lblNumMobile_1 = new JLabel("Mobile ");
		lblNumMobile_1.setBackground(new Color(255, 240, 245));
		lblNumMobile_1.setBounds(10, 15, 45, 13);
		pannelloNumTel_1.add(lblNumMobile_1);
		
		JLabel lblNumFisso_1 = new JLabel("Fisso ");
		lblNumFisso_1.setBounds(10, 39, 45, 13);
		pannelloNumTel_1.add(lblNumFisso_1);
		
		textFieldNumMobile = new JTextField();
		textFieldNumMobile.setOpaque(false);
		textFieldNumMobile.setEditable(false);
		textFieldNumMobile.setColumns(10);
		textFieldNumMobile.setBounds(65, 11, 118, 20);
		pannelloNumTel_1.add(textFieldNumMobile);
		
		textFieldNumFisso = new JTextField();
		textFieldNumFisso.setOpaque(false);
		textFieldNumFisso.setEditable(false);
		textFieldNumFisso.setColumns(10);
		textFieldNumFisso.setBounds(65, 36, 118, 20);
		pannelloNumTel_1.add(textFieldNumFisso);

		textFieldNome.setText(controller.getContattoSelezionato().getNome());
		textFieldSecondoNome.setText(controller.getContattoSelezionato().getSecondoNome());
		textFieldCognome.setText(controller.getContattoSelezionato().getCognome());
		
		// TODO : punto di riferimento
		JPanel pannelloScrollIndirizziSec = new JPanel();
		pannelloScrollIndirizziSec.setBorder(null);
		pannelloScrollIndirizziSec.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloScrollIndirizziSec.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloScrollIndirizziSec.setBackground(new Color(255, 255, 255));
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelMain.setBackground(new Color(255, 240, 245));
		panelMain.setBounds(24, 282, 231, 119);
		contentPane.add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(pannelloScrollIndirizziSec);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 240, 245));
		pannelloScrollIndirizziSec.setLayout(new BoxLayout(pannelloScrollIndirizziSec, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(pannelloScrollIndirizziSec.getSize());
		panelMain.add(scrollPane, BorderLayout.CENTER);
		
		
		JLabel lblNewLabel = new JLabel("Indirizzi Secondari");
		lblNewLabel.setForeground(new Color(102, 102, 153));
		lblNewLabel.setBounds(48, 257, 112, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumSecondari_1 = new JLabel("Numeri Secondari\r\n");
		lblNumSecondari_1.setForeground(new Color(102, 102, 153));
		lblNumSecondari_1.setBounds(68, 413, 112, 14);
		contentPane.add(lblNumSecondari_1);
		
		pannelloNumTelSec = new JPanel();
		pannelloNumTelSec.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pannelloNumTelSec.setBackground(new Color(255, 240, 245));
		pannelloNumTelSec.setBounds(24, 438, 231, 119);
		contentPane.add(pannelloNumTelSec);
		pannelloNumTelSec.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNumTel = new JScrollPane();
		scrollPaneNumTel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneNumTel.setForeground(new Color(255, 240, 245));
		scrollPaneNumTel.setBackground(new Color(255, 240, 245));
		pannelloNumTelSec.add(scrollPaneNumTel, BorderLayout.CENTER);
		
		pannelloScrolNumTel = new JPanel();
		pannelloScrolNumTel.setBackground(new Color(255, 255, 255));
		scrollPaneNumTel.setViewportView(pannelloScrolNumTel);
		pannelloScrolNumTel.setLayout(new BoxLayout(pannelloScrolNumTel, BoxLayout.PAGE_AXIS));
		
		//Pannello per mail
		JLabel lblEmail = new JLabel("Indirizzi Mail\r\n");
		lblEmail.setForeground(new Color(102, 102, 153));
		lblEmail.setBounds(332, 257, 134, 14);
		contentPane.add(lblEmail);
		
		JPanel pannelloEmail = new JPanel();
		pannelloEmail.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pannelloEmail.setBackground(new Color(255, 255, 255));
		pannelloEmail.setBounds(308, 283, 426, 119);
		
		contentPane.add(pannelloEmail);
		pannelloEmail.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEmail = new JScrollPane();
		scrollPaneEmail.setBackground(new Color(255, 255, 255));
		pannelloEmail.add(scrollPaneEmail, BorderLayout.CENTER);
		
		pannelloScrollMail = new JPanel();
		pannelloScrollMail.setLayout(new BoxLayout(pannelloScrollMail, BoxLayout.PAGE_AXIS));
		scrollPaneEmail.setViewportView(pannelloScrollMail);
		pannelloScrollMail.setBackground(new Color(255, 255, 255));
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFocusPainted(false);
		btnChiudi.setBackground(new Color(102, 102, 153));
		btnChiudi.setForeground(new Color(204, 255, 255));
		btnChiudi.setFont(new Font("Arial", Font.PLAIN, 11));
		btnChiudi.setBounds(10, 10, 85, 21);
		contentPane.add(btnChiudi);
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		pannelloIndPrincipale_1.add(panel);
		panel.setLayout(new GridLayout(4, 2));
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setOpaque(true);
		lblVia.setBackground(new Color(255, 255, 255));
		panel.add(lblVia);
		
		textFieldVia = new JTextField();
		textFieldVia.setSelectionColor(new Color(255, 255, 255));
		textFieldVia.setOpaque(false);
		textFieldVia.setEditable(false);
		textFieldVia.setColumns(10);
		panel.add(textFieldVia);
		
		JLabel lblCittà = new JLabel("Citt\u00E0");
		lblCittà.setOpaque(true);
		lblCittà.setBackground(new Color(255, 255, 255));
		panel.add(lblCittà);
		
		textFieldCittà = new JTextField();
		textFieldCittà.setOpaque(false);
		textFieldCittà.setEditable(false);
		textFieldCittà.setColumns(10);
		panel.add(textFieldCittà);
		
		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setOpaque(true);
		lblNazione.setBackground(new Color(255, 255, 255));
		panel.add(lblNazione);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setOpaque(false);
		textFieldNazione.setEditable(false);
		textFieldNazione.setColumns(10);
		panel.add(textFieldNazione);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setOpaque(true);
		lblCap.setBackground(new Color(255, 255, 255));
		panel.add(lblCap);
		
		textFieldCap = new JTextField();
		textFieldCap.setOpaque(false);
		textFieldCap.setEditable(false);
		textFieldCap.setColumns(10);
		panel.add(textFieldCap);
		
		//Pannello degli account
		JLabel lblAccount = new JLabel("Account collegati al contatto");
		lblAccount.setForeground(new Color(102, 102, 153));
		lblAccount.setBounds(332, 414, 200, 13);
		contentPane.add(lblAccount);
		// TODO : qui
		JPanel pannelloAccount = new JPanel();
		pannelloAccount.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pannelloAccount.setBackground(new Color(255, 240, 245));
		pannelloAccount.setBounds(308, 438, 426, 119);
		contentPane.add(pannelloAccount);
		pannelloAccount.setLayout(new BorderLayout(0, 0));
						
		JScrollPane scrollPaneAccount = new JScrollPane();
		pannelloAccount.add(scrollPaneAccount, BorderLayout.CENTER);
						
		JPanel pannelloScrollAccount = new JPanel();
		scrollPaneAccount.setViewportView(pannelloScrollAccount);
		pannelloScrollAccount.setLayout(new BoxLayout(pannelloScrollAccount, BoxLayout.PAGE_AXIS));
		pannelloScrollAccount.setBackground(new Color(255, 255, 255));
		pannelloScrollAccount.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloScrollAccount.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblImg = new JLabel("Immagine del contatto");
		lblImg.setForeground(new Color(102, 102, 153));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBounds(638, 22, 150, 13);
		contentPane.add(lblImg);
		
		inizializzaContatto(textFieldNome,textFieldSecondoNome, textFieldCognome, textFieldVia, textFieldCittà, textFieldNazione, textFieldCap,
	            textFieldNumMobile, textFieldNumFisso, pannelloScrollIndirizziSec, pannelloScrolNumTel, pannelloScrollMail, pannelloScrollAccount);
		
		
		
		
		//Bottoni di chiamata dei numeri
		JButton btnChiama = new JButton("Chiama");
		btnChiama.setForeground(new Color(102, 102, 153));
		btnChiama.setBackground(new Color(255, 255, 255));
		btnChiama.setFont(new Font("Arial", Font.PLAIN, 11));
		btnChiama.setBounds(498, 147, 85, 21);
		btnChiama.setFocusPainted(false);
		contentPane.add(btnChiama);
		
		JButton btnChiama_1 = new JButton("Chiama");
		btnChiama_1.setForeground(new Color(102, 102, 153));
		btnChiama_1.setBackground(new Color(255, 255, 255));
		btnChiama_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btnChiama_1.setBounds(498, 174, 85, 21);
		btnChiama_1.setFocusPainted(false);
		contentPane.add(btnChiama_1);

		
		btnChiama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinestraChiama Chiama = new FinestraChiama(c, textFieldNumMobile.getText().toString(), textFieldNumFisso.getText().toString());
				Chiama.setVisible(true);
				//frame.dispose();
				
			}
		});
			
		btnChiama_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinestraChiama Chiama = new FinestraChiama(c, textFieldNumFisso.getText().toString(), textFieldNumMobile.getText().toString());
				Chiama.setVisible(true);
				//frame.dispose();
			}
		});
	}

	private void inizializzaContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
								     JTextField textFieldCittà, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
								     JTextField textFieldNumFisso, JPanel pannelloScrollIndirizziSec, JPanel pannelloScrolNumTel, JPanel pannelloScrollMail,
								     JPanel pannelloScrollAccount)
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
		JLabel lblImmagine = new JLabel("");
		if (controller.getContattoSelezionato().getPathImmagine() == null || !new File(controller.getContattoSelezionato().getPathImmagine()).exists())
		{
			// immagine di default
			img        = new ImageIcon(this.getClass().getResource("/default.jpg")).getImage();
			imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBounds(633, 57, 170, 150);
			lblImmagine.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		else {
			// immagine caricata
			img        = new ImageIcon(controller.getContattoSelezionato().getPathImmagine()).getImage();
			imgResized = img.getScaledInstance(150, 154, Image.SCALE_DEFAULT);
			lblImmagine.setBackground(Color.LIGHT_GRAY);
			lblImmagine.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblImmagine.setBounds(633, 57, 170, 150);
			lblImmagine.setIcon(new ImageIcon(imgResized));
			contentPane.add(lblImmagine);
		}
		//inserimento indirizzi contatto
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
				JLabel lblSpace     = new JLabel(" ");
				lblSpace.setBackground(new Color(255,255,255));
				
				elemento = creaElemScrollBar(controller.getContattoSelezionato().getIndirizzi().get(i).getVia(),
						 					 controller.getContattoSelezionato().getIndirizzi().get(i).getCitta(),
						 					 controller.getContattoSelezionato().getIndirizzi().get(i).getNazione(),
						 					 controller.getContattoSelezionato().getIndirizzi().get(i).getCap());
				pannelloScrollIndirizziSec.add(elemento);
				pannelloScrollIndirizziSec.add(lblSpace);
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
				
				numero = creaSecNumb(controller.getContattoSelezionato().getTelefoni().get(i).getTipo(),
									 controller.getContattoSelezionato().getTelefoni().get(i).getNumero());
				pannelloScrolNumTel.add(numero);
			}
		}
		// inserimento email del contatto
		for (int i = 0; i < controller.getContattoSelezionato().getEmail().size(); i++)
		{
			JPanel mail;
			
			mail = creaSecMail(controller.getContattoSelezionato().getEmail().get(i).getTipo(),
							   controller.getContattoSelezionato().getEmail().get(i).getStringaEmail());
			pannelloScrollMail.add(mail);
			for (int j = 0; j < controller.getContattoSelezionato().getEmail().get(i).getAccount().size(); j++)
			{
				JPanel accountJP;
				
				accountJP = creaAccountScrollBar(controller.getContattoSelezionato().getEmail().get(i).getAccount().get(j).getFornitore(),
												 controller.getContattoSelezionato().getEmail().get(i).getAccount().get(j).getFraseStato(),
												 controller.getContattoSelezionato().getEmail().get(i).getAccount().get(j).getNickname());
				pannelloScrollAccount.add(accountJP);
				
				JLabel lblSpace     = new JLabel(" ");
				pannelloScrollAccount.add(lblSpace);
			}
		}
		
		revalidate();
		repaint();
	}
		
	private JPanel creaElemScrollBar(String fieldVia, String fieldCittà, String fieldNazione, String fieldCap)
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
		textFieldViaSB.setOpaque(true);
		textFieldViaSB.setBackground(new Color(255,255,255));
		panel.add(textFieldViaSB);
		textFieldViaSB.setColumns(10);
									
		JLabel lblCittàSB = new JLabel("Citt\u00E0");
		lblCittàSB.setOpaque(true);
		lblCittàSB.setBackground(new Color(255, 255, 255));
		panel.add(lblCittàSB);
		
		textFieldCittàSB = new JTextField();
		textFieldCittàSB.setText(fieldCittà);
		textFieldCittàSB.setEditable(false);
		textFieldCittàSB.setOpaque(true);
		textFieldCittàSB.setBackground(new Color(255,255,255));
		panel.add(textFieldCittàSB);
		textFieldCittàSB.setColumns(10);

		JLabel lblNazioneSB = new JLabel("Nazione");
		lblNazioneSB.setOpaque(true);
		lblNazioneSB.setBackground(new Color(255, 255, 255));
		panel.add(lblNazioneSB);

		textFieldNazioneSB = new JTextField();
		textFieldNazioneSB.setText(fieldNazione);
		textFieldNazioneSB.setEditable(false);
		textFieldNazioneSB.setOpaque(true);
		textFieldNazioneSB.setBackground(new Color(255,255,255));
		panel.add(textFieldNazioneSB);
		textFieldNazioneSB.setColumns(10);
		
		JLabel lblCapSB = new JLabel("CAP");
		lblCapSB.setOpaque(true);
		lblCapSB.setBackground(new Color(255, 255, 255));
		panel.add(lblCapSB);
		
		textFieldCapSB = new JTextField();
		textFieldCapSB.setText(fieldCap);
		textFieldCapSB.setEditable(false);
		textFieldCapSB.setOpaque(true);
		textFieldCapSB.setBackground(new Color(255,255,255));
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
		textFieldDescSB.setEditable(false);
		textFieldDescSB.setOpaque(true);
		textFieldDescSB.setBackground(new Color(255,255,255));
		panel.add(textFieldDescSB);
		textFieldDescSB.setColumns(5);
																		
		textFieldNumSB = new JTextField();
		textFieldNumSB.setEditable(false);
		textFieldNumSB.setOpaque(true);
		textFieldNumSB.setBackground(new Color(255,255,255));
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
		panel.setMaximumSize(new Dimension(450,70));
		panel.setPreferredSize(new Dimension(300,50));
		
		
		textFieldDescMailSB = new JTextField();
		
		textFieldDescMailSB.setText(fieldTipo);
		textFieldDescMailSB.setEditable(false);
		textFieldDescMailSB.setOpaque(true);
		textFieldDescMailSB.setBackground(new Color(255,255,255));
		textFieldDescMailSB.setColumns(5);
		panel.add(textFieldDescMailSB);
																		
		textFieldMailSB = new JTextField();
		textFieldMailSB.setText(fieldEmail);
		textFieldMailSB.setEditable(false);
		textFieldMailSB.setOpaque(true);
		textFieldMailSB.setBackground(new Color(255,255,255));
		textFieldMailSB.setColumns(5);
		panel.add(textFieldMailSB);
		
		return  panel;
	}
	
	private JPanel creaAccountScrollBar(String fornitore, String fraseStato, String nickname)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		panel.setSize(new Dimension(300,300));
		
		JTextField textFieldFornitore;
		JTextField textFieldFraseStato;
		JTextField textFieldNickname;
		
		JLabel lblFornitoreSB = new JLabel("Fornitore");
		lblFornitoreSB.setOpaque(true);
		lblFornitoreSB.setBackground(new Color(255, 255, 255));
		panel.add(lblFornitoreSB);

		textFieldFornitore = new JTextField();
		textFieldFornitore.setText(fornitore);
		textFieldFornitore.setEditable(false);
		textFieldFornitore.setOpaque(true);
		textFieldFornitore.setBackground(new Color(255,255,255));
		panel.add(textFieldFornitore);
		textFieldFornitore.setColumns(10);
									
		JLabel lblFraseStatoSB = new JLabel("Frase Stato");
		lblFraseStatoSB.setOpaque(true);
		lblFraseStatoSB.setBackground(new Color(255, 255, 255));
		panel.add(lblFraseStatoSB);
		
		textFieldFraseStato = new JTextField();
		textFieldFraseStato.setText(fraseStato);
		textFieldFraseStato.setEditable(false);
		textFieldFraseStato.setOpaque(true);
		textFieldFraseStato.setBackground(new Color(255,255,255));
		panel.add(textFieldFraseStato);
		textFieldFraseStato.setColumns(10);

		JLabel lblNicknameSB = new JLabel("Nickname");
		lblNicknameSB.setOpaque(true);
		lblNicknameSB.setBackground(new Color(255, 255, 255));
		panel.add(lblNicknameSB);

		textFieldNickname = new JTextField();
		textFieldNickname.setText(nickname);
		textFieldNickname.setEditable(false);
		textFieldNickname.setOpaque(true);
		textFieldNickname.setBackground(new Color(255,255,255));
		panel.add(textFieldNickname);
		textFieldNickname.setColumns(10);
	
		return panel;
	}
}
	
