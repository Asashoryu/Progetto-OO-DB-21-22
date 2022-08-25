package gui;

import controller.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa il frame della home 
 *
 */
@SuppressWarnings("serial")
public class Home extends JFrame {
	
	private Controller controller;
	private JFrame frame;
	private JLabel lblNewLabel;
	private DefaultComboBoxModel<Object> comboBoxModel;
	private JComboBox<Object> comboBox;
	private JButton btnModifica;
	private JTextField txtUtenteSelezionato;
	private JButton btnEntra;
	private JButton btnAggiungi;
	private JButton btnElimina;

	/**
	 * Avvia l'interfaccia
	 */
	public Home(Controller c) {
		controller=c;
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Inizializza i contenuti del frame
	 */
	private void initialize() {
		
		/**
		 * Set dei Components
		 */
		frame = new JFrame("Home");
		frame.getContentPane().setBackground(new Color(102, 102, 153));
		frame.setBounds(500, 200, 567, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Benvenuto nella Rubrica Avanzata");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setForeground(new Color(204, 255, 255));
		lblNewLabel_1.setBackground(new Color(204, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(10, 0, 533, 86);
		frame.getContentPane().add(lblNewLabel_1);
		
		comboBoxModel = new DefaultComboBoxModel<Object>(controller.getNomiRubriche());
		// immagine di default
		Image img          = new ImageIcon(this.getClass().getResource("/IconaUNINA.jpg")).getImage();
		Image imgResized   = img.getScaledInstance(150, 154, Image.SCALE_SMOOTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 82, 553, 305);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Utente");
		lblNewLabel.setBounds(117, 21, 57, 18);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(102, 102, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		comboBox = new JComboBox<Object>(comboBoxModel);
		comboBox.setBounds(169, 21, 90, 18);
		panel.add(comboBox);
		comboBox.setForeground(new Color(102, 102, 153));
		comboBox.setBackground(new Color(255, 255, 255));
		
		txtUtenteSelezionato = new JTextField("");
		txtUtenteSelezionato.setBounds(269, 21, 90, 18);
		panel.add(txtUtenteSelezionato);
		txtUtenteSelezionato.setBackground(new Color(102, 255, 255));
		txtUtenteSelezionato.setEditable(false);
		txtUtenteSelezionato.setColumns(10);
		
		btnEntra = new JButton("Entra");
		btnEntra.setBounds(369, 21, 65, 18);
		panel.add(btnEntra);
		btnEntra.setFocusPainted(false);
		btnEntra.setForeground(new Color(102, 102, 153));
		btnEntra.setBackground(new Color(255, 255, 255));
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setToolTipText("Aggiungi un nuovo utente della rubrica");
		btnAggiungi.setBounds(231, 64, 89, 21);
		panel.add(btnAggiungi);
		btnAggiungi.setFocusPainted(false);
		btnAggiungi.setForeground(new Color(102, 102, 153));
		btnAggiungi.setBackground(new Color(255, 255, 255));
		
		btnElimina = new JButton("Elimina");
		btnElimina.setToolTipText("Elimina l'utente selezionato dalla rubrica assieme a tutti i suoi contatti");
		btnElimina.setBounds(330, 64, 82, 21);
		panel.add(btnElimina);
		btnElimina.setFocusPainted(false);
		btnElimina.setForeground(new Color(102, 102, 153));
		btnElimina.setBackground(new Color(255, 255, 255));
		
		btnModifica = new JButton("Modifica");
		btnModifica.setToolTipText("Modifica utente esistente della rubrica");
		btnModifica.setBounds(139, 64, 82, 21);
		panel.add(btnModifica);
		btnModifica.setFocusPainted(false);
		btnModifica.setForeground(new Color(102, 102, 153));
		btnModifica.setBackground(new Color(255, 255, 255));
		
		//Aggiunta dell'immagine
		JLabel lblImmagine = new JLabel("");
		lblImmagine.setBounds(174, 106, 205, 204);
		panel.add(lblImmagine);
		lblImmagine.setHorizontalAlignment(SwingConstants.CENTER);
		lblImmagine.setBackground(Color.LIGHT_GRAY);
		lblImmagine.setIcon(new ImageIcon(imgResized));
		btnModifica.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtUtenteSelezionato.getText().isBlank()==false) 
				{
				//Fisso la rubrica selezionata dalla combobox attraverso il suo nome
				controller.setRubricaSelezionata(comboBox.getSelectedIndex());
					String visualizzata = "Ridenomina ".concat(controller.getRubricaSelezionata().getNome());
					String stringa = JOptionPane.showInputDialog(null, visualizzata, "Modifica rubrica", JOptionPane.QUESTION_MESSAGE);
					if(stringa!= null && stringa.isBlank()==false) 
					{
						try
						{
							controller.updateRubrica(stringa);
							//In caso di Exception non entra in questo punto
							int indiceSelezionato = comboBox.getSelectedIndex();
							comboBoxModel.removeElementAt(indiceSelezionato);
							comboBoxModel.insertElementAt((Object) controller.getRubricaSelezionata().getNome(),indiceSelezionato);
							comboBox.setSelectedIndex(indiceSelezionato);
							System.out.println("Utente modificato in "+ comboBox.getSelectedItem().toString());
						} catch (Exception e2) 
						{
							JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
	                                                			JOptionPane.ERROR_MESSAGE );
						}
						for(String s : controller.getNomiRubriche()) 
						{
							System.out.print(s +"   ");
						}
						System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
					}
				}
			}
		});
		btnElimina.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtUtenteSelezionato.getText().isBlank() == false) 
				{
					String stringaUtenteDaEliminare = txtUtenteSelezionato.getText();
					String visualizzata = "Confermi di voler eliminare la Rubrica ".concat(stringaUtenteDaEliminare);
					// 0=Procedi, 1=Annulla
					Object[] opzioni = {"Procedi","Annulla"};
					// finestra di conferma per la cancellazione
					int input = JOptionPane.showOptionDialog(rootPane, visualizzata, "Elimina rubrica", JOptionPane.YES_NO_OPTION,
													 		 JOptionPane.WARNING_MESSAGE, null, opzioni, null);
					// Se scelta Yes
					if(input == 0)
					{
						try 
						{
							int indiceSelezionato = comboBox.getSelectedIndex();
							//rimuove dalla rubrica nel DB e in memoria
							controller.setRubricaSelezionata(indiceSelezionato);
							controller.deleteRubrica();
							//Una volta effettuate le modifiche nel DB e in memoria, si aggiorna il frame
							comboBoxModel.removeElementAt(indiceSelezionato);
						} 
						catch (Exception e2) 
						{
							e2.printStackTrace();
							JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                            		   	   JOptionPane.ERROR_MESSAGE );
						}
						//debug manuale
						for(String s : controller.getNomiRubriche()) 
						{
							System.out.print(s +"   ");
						}
						System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
					}
				}
			}
		});
		btnAggiungi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String visualizzata      = "Inserisci il nome della Rubrica da aggiungere";
				String stringaDaInserire = JOptionPane.showInputDialog(null, visualizzata, "Aggiungi rubrica", JOptionPane.QUESTION_MESSAGE);
				if(stringaDaInserire!= null && stringaDaInserire.isBlank() == false) 
				{
					try 
					{
						//aggiunge la rubrica nel DB e in memoria	
						controller.addRubrica(stringaDaInserire);
						//Una volta effetuate le modifiche nel DB e in memoria, si aggiorna il frame
						System.out.println("Valore aggiunto con successo!\n");
						comboBox.addItem((Object) stringaDaInserire);
						comboBox.setSelectedIndex(comboBoxModel.getIndexOf(stringaDaInserire));
						txtUtenteSelezionato.setText(stringaDaInserire);
						System.out.println("Utente aggiunto è "+ comboBox.getSelectedItem().toString());
					} 
					catch (Exception e2) 
					{
						e2.printStackTrace();
						JOptionPane.showMessageDialog( null, e2.getMessage(), "Errore",
                                             		   JOptionPane.ERROR_MESSAGE );
					}
					//debug manuale
					for(String s : controller.getNomiRubriche()) 
					{
						System.out.print(s +"   ");
					}
					//System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
				}
			}
		});
		btnEntra.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (txtUtenteSelezionato.getText().isBlank() == false) 
				{
					controller.setRubricaSelezionata(comboBox.getSelectedIndex());
					try {
						controller.loadContatti();
						controller.loadGruppi();
					} catch (Exception e1) {
						// TODO aggiungere un JOptionPane corrispondente
						e1.printStackTrace();
					}
					JFrame contattiFrame = new ListaContatti(controller, frame);
					System.out.println("Frame Contatti caricato");
					frame.setVisible(false);
					contattiFrame.setVisible(true);
				}
			}
		});
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(comboBoxModel.getSelectedItem()!=null) 
				{
					System.out.println("Entrato nell'action listener di combobox  " + e.getActionCommand());
					txtUtenteSelezionato.setText(comboBoxModel.getSelectedItem().toString());
				}
				else 
				{
					txtUtenteSelezionato.setText("");
				}
			}
		});
		
		/**
		 * All'avvio la combobox e il label visualizzano il
		 * primo utente caricato, se presente
		 */
		if(controller.getRubriche().size()!=0) 
		{
			txtUtenteSelezionato.setText(comboBox.getItemAt(0).toString());
			controller.setRubricaSelezionata(0);
		}
		
		/**
		 * Quando è selezionato un campo della combobox il suo valore 
		 * è visualizzato nella label affianco
		 */
		
		/**
		 * Quando è premuto il button "entra" sono caricati i contatti
		 * dell'utente selezionato e il frame presente è posto a invisibile
		 */
		
		/**
		 * Quando è premuto il button "aggiungi"
		 */
		
		/**
		 * Quando è premuto il button "elimina"
		 */
			
		/**
		 * Quando è premuto il button "modifica"
		 */
		
	}
}