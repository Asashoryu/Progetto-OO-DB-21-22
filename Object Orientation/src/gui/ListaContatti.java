package gui;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.sql.SQLException;

/**
 * Frame che visualizza e gestisce ciascun contatto della rubrica tramite una lista.
 */
@SuppressWarnings("serial")
public class ListaContatti extends JFrame {
	
	/** Questo frame. */
	private JFrame     frame;
	
	/** Controller. */
	private Controller controller;
	
	/**
	 * Costruttore di un nuovo frame ListaContatti.
	 *
	 * @param c controller
	 * @param frameChiamante frame chiamante
	 */
	public ListaContatti(Controller c, JFrame frameChiamante) {
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		
		frame = this;
        controller = c;
  
        frame.setTitle("Rubrica di "+controller.getRubricaSelezionata().getNome());
        frame.setBounds(500, 200,590,422);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(-10007, -10030, 718, 448);
		getContentPane().add(label_5);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setForeground(new Color(255, 255, 255));
		btnIndietro.setBounds(10, 10, 82, 23);
		btnIndietro.setFont(new Font("Arial", Font.PLAIN, 12));
		btnIndietro.setBorder(UIManager.getBorder("Button.border"));
		btnIndietro.setBackground(new Color(102, 102, 153));
		btnIndietro.setFocusPainted(false);
		getContentPane().add(btnIndietro);
		
		JScrollPane scrollPaneContatti = new JScrollPane();
		scrollPaneContatti.setBounds(10, 100, 258, 198);
		scrollPaneContatti.setBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPaneContatti.setBackground(new Color(102, 102, 153));
		
		
		scrollPaneContatti.setViewportBorder(null);
		getContentPane().add(scrollPaneContatti);
		
		/** carica la Jlist con i nomi dei contatti dal DB */
		JList<Object> listaContatti = new JList<Object>(controller.getNomiContattiRubrica());
		scrollPaneContatti.setViewportView(listaContatti);
		listaContatti.setVisibleRowCount(-1);
		listaContatti.setSelectionForeground(new Color(0, 0, 0));
		listaContatti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaContatti.setBorder(null);
		listaContatti.setBackground(new Color(255, 255, 255));
		
		JButton btnModificaContatto = new JButton("Modifica");
		btnModificaContatto.setForeground(new Color(102, 102, 153));
		btnModificaContatto.setBackground(new Color(204, 255, 255));
		btnModificaContatto.setBounds(92, 339, 94, 21);
		btnModificaContatto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModificaContatto.setFocusPainted(false);
		getContentPane().add(btnModificaContatto);
		
		JButton btnEliminaContatto = new JButton("-");
		btnEliminaContatto.setForeground(new Color(102, 102, 153));
		btnEliminaContatto.setBackground(new Color(204, 255, 255));
		btnEliminaContatto.setBounds(196, 339, 45, 21);
		btnEliminaContatto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnEliminaContatto.setFocusPainted(false);
		getContentPane().add(btnEliminaContatto);
				
		JLabel lblContatti = new JLabel("Contatti: ");
		lblContatti.setBounds(10, 69, 143, 21);
		lblContatti.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblContatti);
		
		JButton btnAggiungiContatto = new JButton("+");
		btnAggiungiContatto.setForeground(new Color(102, 102, 153));
		btnAggiungiContatto.setBackground(new Color(204, 255, 255));
		btnAggiungiContatto.setBounds(37, 339, 45, 21);
		btnAggiungiContatto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAggiungiContatto.setFocusPainted(false);
		getContentPane().add(btnAggiungiContatto);
		
		JLabel lblGruppi = new JLabel("Gruppi: ");
		lblGruppi.setBounds(317, 69, 143, 21);
		lblGruppi.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblGruppi);
		
		JScrollPane scrollPaneGruppi = new JScrollPane();
		scrollPaneGruppi.setBounds(317, 100, 246, 198);
		scrollPaneGruppi.setViewportBorder(null);
		scrollPaneGruppi.setBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPaneGruppi.setBackground(new Color(102, 102, 153));
		getContentPane().add(scrollPaneGruppi);
		
		JList<Object> listaGruppi = new JList<Object>(controller.getNomiGruppiRubrica());
		listaGruppi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaGruppi.setBorder(null);
		scrollPaneGruppi.setViewportView(listaGruppi);
		
		JButton btnAggiungiGruppo = new JButton("+");
		btnAggiungiGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAggiungiGruppo.setForeground(new Color(102, 102, 153));
		btnAggiungiGruppo.setBackground(new Color(204, 255, 255));
		btnAggiungiGruppo.setBounds(338, 339, 45, 21);
		btnAggiungiGruppo.setFocusPainted(false);
		getContentPane().add(btnAggiungiGruppo);
		
		JButton btnModificaGruppo = new JButton("Modifica");
		btnModificaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModificaGruppo.setForeground(new Color(102, 102, 153));
		btnModificaGruppo.setBackground(new Color(204, 255, 255));
		btnModificaGruppo.setBounds(395, 339, 94, 21);
		btnModificaGruppo.setFocusPainted(false);
		getContentPane().add(btnModificaGruppo);
		
		JButton btnVisualizzaGruppo = new JButton("Visualizza Gruppo");
		btnVisualizzaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnVisualizzaGruppo.setForeground(new Color(102, 102, 153));
		btnVisualizzaGruppo.setBackground(new Color(204, 255, 255));
		btnVisualizzaGruppo.setBounds(377, 310, 135, 21);
		btnVisualizzaGruppo.setFocusPainted(false);
		getContentPane().add(btnVisualizzaGruppo);
		
		JButton btnEliminaGruppo = new JButton("-");
		btnEliminaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnEliminaGruppo.setForeground(new Color(102, 102, 153));
		btnEliminaGruppo.setBackground(new Color(204, 255, 255));
		btnEliminaGruppo.setBounds(501, 339, 45, 21);
		btnEliminaGruppo.setFocusPainted(false);
		getContentPane().add(btnEliminaGruppo);
		
		JButton btnVisualizzaTuttiContatti = new JButton("Tutti");
		btnVisualizzaTuttiContatti.setForeground(new Color(102, 102, 153));
		btnVisualizzaTuttiContatti.setToolTipText("Premi per mostrare l'elenco di tutti i contatti della rubrica");
		btnVisualizzaTuttiContatti.setBackground(new Color(204, 255, 255));
		btnVisualizzaTuttiContatti.setBounds(190, 66, 74, 20);
		btnVisualizzaTuttiContatti.setFocusPainted(false);
		getContentPane().add(btnVisualizzaTuttiContatti);
		
		//Bottone per visualizzare un contatto
		JButton btnInfoContatto = new JButton("Visualizza Contatto");
		btnInfoContatto.setForeground(new Color(102, 102, 153));
		btnInfoContatto.setBackground(new Color(204, 255, 255));
		btnInfoContatto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnInfoContatto.setBounds(65, 310, 151, 21);
		btnInfoContatto.setFocusPainted(false);
		getContentPane().add(btnInfoContatto);
		
		/**
		 * Per il pannello di ricerca
		 */
		JTextField textFieldRicerca = new JTextField();
		textFieldRicerca.setToolTipText("Inserire chiave (testo, numero ecc.) da ricercare");
		textFieldRicerca.setBounds(223, 10, 158, 23);
		getContentPane().add(textFieldRicerca);
		textFieldRicerca.setColumns(10);
		
		String[] FiltroRicerca = {"Nome", "Email", "Account", "Numero di Telefono"};
		
		JComboBox<Object> comboBoxFiltroRicerca = new JComboBox<Object>(FiltroRicerca);
		comboBoxFiltroRicerca.setForeground(new Color(102, 102, 153));
		comboBoxFiltroRicerca.setBackground(new Color(255, 255, 255));
		comboBoxFiltroRicerca.setToolTipText("Elenco dei filtri utilizzabili per la ricerca dei contatti");
		comboBoxFiltroRicerca.setBounds(381, 10, 124, 23);
		getContentPane().add(comboBoxFiltroRicerca);
		comboBoxFiltroRicerca.setSelectedIndex(0);
		
		JButton btnInvioRicerca = new JButton("Vai");
		btnInvioRicerca.setToolTipText("Premi per avviare la ricerca basata su chiave e filtro");
		btnInvioRicerca.setForeground(new Color(102, 102, 153));
		btnInvioRicerca.setBackground(new Color(255, 255, 255));
		btnInvioRicerca.setBounds(505, 10, 58, 23);
		btnInvioRicerca.setFocusPainted(false);
		getContentPane().add(btnInvioRicerca);
		
		JLabel lblNewLabel = new JLabel("Ricerca");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(168, 10, 45, 23);
		getContentPane().add(lblNewLabel);
		
		/**
		 * Quando è premuto il button "Indietro"
		 */
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frameChiamante.setVisible(true);
				 frame.dispose();
			}
		});
		
		/**
		 * Quando è premuto il button "Aggiungi"
		 */
		btnAggiungiContatto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame AddContatto = new AddContatto(controller, frame, listaContatti);
				frame.setVisible(false);
				AddContatto.setLocationRelativeTo(null);
				AddContatto.setVisible(true);
			}
		});
		
		/**
		 * Quando è premuto il button "Visualizza contatto"
		 */
		btnInfoContatto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					JFrame InfoContatto = new InfoContatto(controller, frame);
					frame.dispose();
					InfoContatto.setLocationRelativeTo(null);
					InfoContatto.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun contatto selezionato");
				}
			}
		});
		
		/**
		 * Quando è premuto il button "Modifica"
		 */
		btnModificaContatto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					JFrame ChangeContatto = new ChangeContatto(controller, frame, listaContatti);
					frame.setVisible(false);
					ChangeContatto.setLocationRelativeTo(null);
					ChangeContatto.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun contatto selezionato");
				}
			}
		});
		
		/**
		 * Quando è premuto il button "Elimina"
		 */
		btnEliminaContatto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Elimina dalla memoria e dal DB
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					controller.deleteContattoSelezionato();
					if (controller.getGruppoSelezionato() != null)
					{
						listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					}
					else 
					{
						listaContatti.setListData(controller.getNomiContattiRubrica());
					}
					listaGruppi.setListData(controller.getNomiGruppiRubrica());
					JOptionPane.showConfirmDialog(null, 
			                "Contatto cancellato con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
					revalidate();
					repaint();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.getMessage(),
						      "Operazione di cancellazione fallita", JOptionPane.ERROR_MESSAGE);
					System.out.println("Non è stato possibile cancellare il contatto dalla memoria : " + listaContatti.getComponentCount());
				}
			}
		});
		
		btnVisualizzaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setGruppoSelezionato(listaGruppi.getSelectedIndex());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun gruppo selezionato");
				}
			}
		});
		
		btnVisualizzaTuttiContatti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setNullGruppoSelezionato();
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiRubrica());
					listaContatti.revalidate();
					listaContatti.repaint();
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun gruppo selezionato");
				}
			}
		});
		
		btnAggiungiGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame AddGruppo = new AddGruppo(controller, frame, listaGruppi);
				frame.setVisible(false);
				AddGruppo.setLocationRelativeTo(null);
				AddGruppo.setVisible(true);
			}
		});
		
		btnEliminaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setGruppoSelezionato(listaGruppi.getSelectedIndex());
					controller.deleteGruppoSelezionato();
					// Cancella dalla lista;
					listaGruppi.removeAll();
					listaGruppi.setListData(controller.getNomiGruppiRubrica());
					JOptionPane.showConfirmDialog(null, 
							"Gruppo cancellato con successo!", "Cancellazione riuscita", JOptionPane.DEFAULT_OPTION);
					listaGruppi.revalidate();
					listaGruppi.repaint();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnModificaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setGruppoSelezionato(listaGruppi.getSelectedIndex());
					JFrame changeGruppo = new ChangeGruppo(controller, frame, listaContatti, listaGruppi);
					frame.setVisible(false);
					changeGruppo.setLocationRelativeTo(null);
					changeGruppo.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun gruppo selezionato");
				}
			}
		});
		
		btnInvioRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int modo = comboBoxFiltroRicerca.getSelectedIndex();
				// se ricerca per nome
				if (modo == 0)
				{
					controller.cercaPerNome(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
				}
				// se ricerca per email
				if (modo == 1)
				{
					controller.cercaPerEmail(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
				}
				// se ricerca per account
				if (modo == 2)
				{
					controller.cercaPerAccount(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
				}
				// se ricerca per numero di telefono
				if (modo == 3)
				{
					controller.cercaPerNumero(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
				}
			}
		});
	}
}

