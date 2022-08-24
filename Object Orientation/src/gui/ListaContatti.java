package gui;
import controller.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class ListaContatti extends JFrame {
	
	private JFrame     frame;
	private Controller controller;
	
	/**
	 * Crea frame.
	 */
	public ListaContatti(Controller c, JFrame frameChiamante) {
		setMinimumSize(new Dimension(610, 420));
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		
		frame = this;
        controller = c;
  
        // Display the window.
        frame.setTitle("Rubrica di "+controller.getRubricaSelezionata().getNome());
        frame.setBounds(500, 200,660,460);
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
		
		JButton btnUscita = new JButton("Indietro");
		btnUscita.setForeground(new Color(255, 255, 255));
		btnUscita.setBounds(10, 10, 82, 23);
		btnUscita.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUscita.setBorder(UIManager.getBorder("Button.border"));
		btnUscita.setBackground(new Color(102, 102, 153));
		btnUscita.setFocusPainted(false);
		getContentPane().add(btnUscita);
		
		JScrollPane scrollPaneContatti = new JScrollPane();
		scrollPaneContatti.setBounds(10, 106, 258, 198);
		scrollPaneContatti.setBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPaneContatti.setBackground(new Color(102, 102, 153));
		
		
		scrollPaneContatti.setViewportBorder(null);
		getContentPane().add(scrollPaneContatti);
		JList<Object> listaContatti = new JList<Object>(controller.getNomiContattiRubrica());
		scrollPaneContatti.setViewportView(listaContatti);
		listaContatti.setVisibleRowCount(-1);
		listaContatti.setSelectionForeground(new Color(0, 0, 0));
		listaContatti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaContatti.setBorder(null);
		listaContatti.setBackground(new Color(255, 255, 255));
		
		/** carica la Jlist con i nomi dei contatti dal DB */
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setForeground(new Color(102, 102, 153));
		btnModifica.setBackground(new Color(204, 255, 255));
		btnModifica.setBounds(92, 347, 94, 21);
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModifica.setFocusPainted(false);
		getContentPane().add(btnModifica);
		
		JButton btnElimina = new JButton("-");
		btnElimina.setForeground(new Color(102, 102, 153));
		btnElimina.setBackground(new Color(204, 255, 255));
		btnElimina.setBounds(196, 347, 45, 21);
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 11));
		btnElimina.setFocusPainted(false);
		getContentPane().add(btnElimina);
				
		JLabel lblContatti = new JLabel("Contatti: ");
		lblContatti.setBounds(10, 75, 143, 21);
		lblContatti.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblContatti);
		
		JButton btnAggiungi = new JButton("+");
		btnAggiungi.setForeground(new Color(102, 102, 153));
		btnAggiungi.setBackground(new Color(204, 255, 255));
		btnAggiungi.setBounds(37, 347, 45, 21);
		btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAggiungi.setFocusPainted(false);
		getContentPane().add(btnAggiungi);
		
		JLabel lblGruppi = new JLabel("Gruppi: ");
		lblGruppi.setBounds(336, 75, 143, 21);
		lblGruppi.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblGruppi);
		
		JScrollPane scrollPaneGruppi = new JScrollPane();
		scrollPaneGruppi.setBounds(336, 106, 246, 198);
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
		btnAggiungiGruppo.setBounds(357, 343, 45, 23);
		btnAggiungiGruppo.setFocusPainted(false);
		getContentPane().add(btnAggiungiGruppo);
		
		JButton btnModificaGruppo = new JButton("Modifica");
		btnModificaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnModificaGruppo.setForeground(new Color(102, 102, 153));
		btnModificaGruppo.setBackground(new Color(204, 255, 255));
		btnModificaGruppo.setBounds(414, 343, 94, 23);
		btnModificaGruppo.setFocusPainted(false);
		getContentPane().add(btnModificaGruppo);
		
		JButton btnVisualizzaGruppo = new JButton("Visualizza gruppo");
		btnVisualizzaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnVisualizzaGruppo.setForeground(new Color(102, 102, 153));
		btnVisualizzaGruppo.setBackground(new Color(204, 255, 255));
		btnVisualizzaGruppo.setBounds(396, 314, 135, 21);
		btnVisualizzaGruppo.setFocusPainted(false);
		getContentPane().add(btnVisualizzaGruppo);
		
		JButton btnEliminaGruppo = new JButton("-");
		btnEliminaGruppo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnEliminaGruppo.setForeground(new Color(102, 102, 153));
		btnEliminaGruppo.setBackground(new Color(204, 255, 255));
		btnEliminaGruppo.setBounds(520, 343, 45, 23);
		btnEliminaGruppo.setFocusPainted(false);
		getContentPane().add(btnEliminaGruppo);
		
		
		JButton btnVisualizzaTuttiGruppi = new JButton("Tutti");
		btnVisualizzaTuttiGruppi.setForeground(new Color(102, 102, 153));
		btnVisualizzaTuttiGruppi.setToolTipText("Premi per mostrare l'elenco di tutti i contatti della rubrica");
		btnVisualizzaTuttiGruppi.setBackground(new Color(204, 255, 255));
		btnVisualizzaTuttiGruppi.setBounds(190, 72, 74, 20);
		btnVisualizzaTuttiGruppi.setFocusPainted(false);
		getContentPane().add(btnVisualizzaTuttiGruppi);
		
		//Bottone per visualizzare un contatto
		JButton btnInfo = new JButton("Visualizza Contatto");
		btnInfo.setForeground(new Color(102, 102, 153));
		btnInfo.setBackground(new Color(204, 255, 255));
		btnInfo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnInfo.setBounds(65, 314, 151, 21);
		btnInfo.setFocusPainted(false);
		getContentPane().add(btnInfo);
		
		/**
		 * Per il pannello di ricerca
		 */
		
		JTextField textFieldRicerca = new JTextField();
		textFieldRicerca.setToolTipText("Inserire chiave (testo, numero ecc.) da ricercare");
		textFieldRicerca.setBounds(168, 10, 158, 23);
		getContentPane().add(textFieldRicerca);
		textFieldRicerca.setColumns(10);
		
		
		String[] FiltroRicerca = {"Nome", "Email", "Account", "Numero di Telefono"};
		
		JComboBox comboBoxFiltroRicerca = new JComboBox(FiltroRicerca);
		comboBoxFiltroRicerca.setForeground(new Color(102, 102, 153));
		comboBoxFiltroRicerca.setBackground(new Color(255, 255, 255));
		comboBoxFiltroRicerca.setToolTipText("Elenco dei filtri utilizzabili per la ricerca dei contatti");
		comboBoxFiltroRicerca.setBounds(326, 10, 124, 23);
		getContentPane().add(comboBoxFiltroRicerca);
		comboBoxFiltroRicerca.setSelectedIndex(0);
		
		JButton btnInvioRicerca = new JButton("Vai");
		btnInvioRicerca.setToolTipText("Premi per avviare la ricerca basata su chiave e filtro");
		btnInvioRicerca.setForeground(new Color(102, 102, 153));
		btnInvioRicerca.setBackground(new Color(255, 255, 255));
		btnInvioRicerca.setBounds(450, 10, 58, 23);
		btnInvioRicerca.setFocusPainted(false);
		getContentPane().add(btnInvioRicerca);
		
		JLabel lblNewLabel = new JLabel("Ricerca");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(113, 10, 45, 23);
		getContentPane().add(lblNewLabel);
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
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame AddContatto = new AddContatto(controller, frame, listaContatti);
				frame.setVisible(false);
				AddContatto.setVisible(true);
			}
		});
		/**
		 * Quando è premuto il button "Visualizza contatto"
		 */
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					JFrame InfoContatto = new InfoContatto(controller, frame, listaContatti);
					frame.dispose();
					InfoContatto.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun contatto selezionato");
				}
			}
		});
		/**
		 * Quando è premuto il button "Modifica"
		 */
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					JFrame ChangeContatto = new ChangeContatto(controller, frame, listaContatti);
					frame.setVisible(false);
					ChangeContatto.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					System.out.println("Nessun contatto selezionato");
				}
			}
		});
		/**
		 * TODO: Quando è premuto il button "Elimina"
		 */
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Elimina dalla memoria e dal DB
					controller.setContattoSelezionato(listaContatti.getSelectedIndex());
					controller.deleteContattoSelezionato();
					//TODO: Cancella dalla lista;
//					DefaultListModel model = (DefaultListModel) listaContatti.getModel();
//					int selectedIndex = listaContatti.getSelectedIndex();
//					if (selectedIndex != -1) {
//					    model.remove(selectedIndex);
//					}
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiRubrica());
					JOptionPane.showConfirmDialog(null, 
			                "Contatto cancellato con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
					listaContatti.revalidate();
					listaContatti.repaint();
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
		
		btnVisualizzaTuttiGruppi.addActionListener(new ActionListener() {
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
					JFrame changeGruppo = new ChangeGruppo(controller, frame, listaGruppi);
					frame.setVisible(false);
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
					controller.setNullGruppoSelezionato();
				}
				// se ricerca per email
				if (modo == 1)
				{
					controller.cercaPerEmail(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
					controller.setNullGruppoSelezionato();
				}
				// se ricerca per account
				if (modo == 2)
				{
					controller.cercaPerAccount(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
					controller.setNullGruppoSelezionato();
				}
				// se ricerca per numero di telefono
				if (modo == 3)
				{
					controller.cercaPerNumero(textFieldRicerca.getText());
					listaContatti.removeAll();
					listaContatti.setListData(controller.getNomiContattiGruppoSelezionato());
					listaContatti.revalidate();
					listaContatti.repaint();
					controller.setNullGruppoSelezionato();
				}
			}
		});
	}
}

