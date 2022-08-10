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
import javax.swing.DefaultListModel;

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
		setMinimumSize(new Dimension(850, 500));
		getContentPane().setBackground(new Color(224, 255, 255));
		
		frame = this;
        controller = c;
  
        // Display the window.
        frame.setTitle("Rubrica di "+controller.getRubricaSelezionata().getNome());
        frame.setBounds(500, 200,660,460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("RUBRICA AVANZATA");
		lblNewLabel_1.setBounds(139, 10, 368, 54);
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
		
		JScrollPane scrollPaneContatti = new JScrollPane();
		scrollPaneContatti.setBounds(139, 92, 368, 240);
		scrollPaneContatti.setBorder(null);
		scrollPaneContatti.setBackground(Color.CYAN);
		scrollPaneContatti.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		getContentPane().add(scrollPaneContatti);
		
		/** carica la Jlist con i nomi dei contatti dal DB */
		JList<Object> listaContatti = new JList<Object>(controller.getNomiContattiRubrica());
		listaContatti.setVisibleRowCount(-1);
		scrollPaneContatti.setViewportView(listaContatti);
		listaContatti.setSelectionForeground(new Color(0, 0, 0));
		listaContatti.setFont(new Font("Arial", Font.PLAIN, 15));
		listaContatti.setBorder(null);
		listaContatti.setBackground(new Color(240, 248, 255));
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(276, 362, 94, 21);
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().add(btnModifica);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(380, 362, 94, 21);
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().add(btnElimina);
				
		JLabel lblContatti = new JLabel("Contatti: ");
		lblContatti.setBounds(139, 68, 143, 21);
		lblContatti.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblContatti);
		
		JButton btnAggiungi = new JButton("Aggiungi ");
		btnAggiungi.setBounds(172, 362, 94, 21);
		btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().add(btnAggiungi);
		
		JLabel lblGruppi = new JLabel("Gruppi: ");
		lblGruppi.setBounds(540, 68, 143, 21);
		lblGruppi.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblGruppi);
		
		JScrollPane scrollPaneGruppi = new JScrollPane();
		scrollPaneGruppi.setBounds(540, 92, 246, 110);
		scrollPaneGruppi.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPaneGruppi.setBorder(null);
		scrollPaneGruppi.setBackground(Color.CYAN);
		getContentPane().add(scrollPaneGruppi);
		
		JList<Object> listaGruppi = new JList<Object>(controller.getNomiGruppiRubrica());
		scrollPaneGruppi.setViewportView(listaGruppi);
		
		JButton btnAggiungiGruppo = new JButton("+");
		btnAggiungiGruppo.setBounds(552, 361, 48, 23);
		getContentPane().add(btnAggiungiGruppo);
		
		JButton btnModificaGruppo = new JButton("Modifica Gruppo");
		btnModificaGruppo.setBounds(610, 361, 109, 23);
		getContentPane().add(btnModificaGruppo);
		
		JButton btnEliminaGruppo = new JButton("-");
		btnEliminaGruppo.setBounds(729, 361, 37, 23);
		btnEliminaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnEliminaGruppo);
		
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
		 * TODO: Quando è premuto il button "Aggiungi"
		 */
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame AddContatto = new AddContatto(controller, frame, listaContatti);
				frame.setVisible(false);
				AddContatto.setVisible(true);
			}
		});
		/**
		 * TODO: Quando è premuto il button "Modifica"
		 */
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setContattoSelezionato(listaContatti.getSelectedIndex());
				JFrame ChangeContatto = new ChangeContatto(controller, frame, listaContatti);
				frame.setVisible(false);
				ChangeContatto.setVisible(true);
			}
		});
		/**
		 * TODO: Quando è premuto il button "Elimina"
		 */
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indiceSelezionato = listaContatti.getSelectedIndex();
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
		/**
		 * TODO: Quando è premuto il button "GRUPPI"
		 */
		
		/**
		 * Quando è premuto il button "ricerca"
		 * TODO : implementare le varie forme di ricerca di un contatto, secondo
		 * 		  diversi criteri
		 */
	}
}

