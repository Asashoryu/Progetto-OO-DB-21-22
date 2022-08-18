package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.management.RuntimeErrorException;
import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import controller.Controller;
import dao.SistemaDAO;
import model.Rubrica;
import model.Sistema;

import javax.swing.JComboBox;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Classe che implementa il frame della home 
 *
 */
public class Home extends JFrame {
	private Controller controller;

	JFrame frame;
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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(500, 200, 618, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Benvenuto nella Rubrica Avanzata");
		lblNewLabel_1.setForeground(new Color(102, 102, 153));
		lblNewLabel_1.setBackground(Color.CYAN);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(10, 0, 584, 86);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("Utente");
		lblNewLabel.setForeground(new Color(102, 102, 153));
		lblNewLabel.setBounds(142, 98, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBoxModel = new DefaultComboBoxModel<Object>(controller.getNomiRubriche());
		comboBox = new JComboBox<Object>(comboBoxModel);
		comboBox.setForeground(new Color(102, 102, 153));
		comboBox.setBounds(194, 97, 90, 17);
		frame.getContentPane().add(comboBox);
		comboBox.setBackground(new Color(255, 255, 255));
		
		txtUtenteSelezionato = new JTextField("");
		txtUtenteSelezionato.setBounds(294, 97, 90, 16);
		frame.getContentPane().add(txtUtenteSelezionato);
		txtUtenteSelezionato.setBackground(new Color(102, 255, 255));
		txtUtenteSelezionato.setEditable(false);
		txtUtenteSelezionato.setColumns(10);
		
		btnEntra = new JButton("Entra");
		btnEntra.setFocusPainted(false);
		btnEntra.setForeground(new Color(102, 102, 153));
		btnEntra.setBounds(394, 96, 65, 18);
		frame.getContentPane().add(btnEntra);
		btnEntra.setBackground(new Color(255, 255, 255));
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setFocusPainted(false);
		btnAggiungi.setForeground(new Color(102, 102, 153));
		btnAggiungi.setBounds(247, 151, 89, 21);
		frame.getContentPane().add(btnAggiungi);
		btnAggiungi.setBackground(new Color(255, 255, 255));
		
		btnElimina = new JButton("Elimina");
		btnElimina.setFocusPainted(false);
		btnElimina.setForeground(new Color(102, 102, 153));
		btnElimina.setBounds(346, 151, 82, 21);
		frame.getContentPane().add(btnElimina);
		btnElimina.setBackground(new Color(255, 255, 255));
		
		btnModifica = new JButton("Modifica");
		btnModifica.setFocusPainted(false);
		btnModifica.setBounds(155, 151, 82, 21);
		frame.getContentPane().add(btnModifica);
		btnModifica.setForeground(new Color(102, 102, 153));
		btnModifica.setBackground(new Color(255, 255, 255));
		
		//Aggiunta dell'immagine
		JLabel lblImmagine = new JLabel("");
		lblImmagine.setHorizontalAlignment(SwingConstants.CENTER);
		// immagine di default
		Image img          = new ImageIcon(this.getClass().getResource("/IconaUNINA.jpg")).getImage();
		Image imgResized   = img.getScaledInstance(150, 154, Image.SCALE_SMOOTH);
		lblImmagine.setBackground(Color.LIGHT_GRAY);
		lblImmagine.setBounds(194, 199, 205, 204);
		lblImmagine.setIcon(new ImageIcon(imgResized));
		frame.getContentPane().add(lblImmagine);
		
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
		 * Quando � selezionato un campo della combobox il suo valore 
		 * � visualizzato nella label affianco
		 */
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
		 * Quando � premuto il button "entra" sono caricati i contatti
		 * dell'utente selezionato e il frame presente � posto a invisibile
		 */
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
						System.out.println("Debug: superato loadGruppi");
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
		
		/**
		 * Quando � premuto il button "aggiungi"
		 */
		btnAggiungi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String visualizzata      = "Inserisci il nome della Rubrica da aggiungere";
				String stringaDaInserire = JOptionPane.showInputDialog(visualizzata);
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
						System.out.println("Utente aggiunto � "+ comboBox.getSelectedItem().toString());
					} 
					catch (Exception e2) 
					{
						e2.printStackTrace();
						System.out.println("Entrato nel Catch.. valore aggiunto non valido\n");
						JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                             		   JOptionPane.ERROR_MESSAGE );
					}
					//debug manuale
					System.out.println("Superato il Catch.. In memoria: ");
					for(Rubrica r:controller.getRubriche()) 
					{
						System.out.print(r.getNome()+"   ");
					}
					//System.out.println(" La Rubrica Selezionata �: "+controller.getRubricaSelezionata().getNome());
				}
			}
		});
		
		/**
		 * Quando � premuto il button "elimina"
		 */
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
					int input = JOptionPane.showOptionDialog(rootPane, visualizzata, "Elimina", JOptionPane.YES_NO_OPTION,
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
							System.out.println("\nEntrato nel Catch.. valore non valido");
							JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                            		   	   JOptionPane.ERROR_MESSAGE );
						}
						//debug manuale
						System.out.println("Superato il Catch.. In memoria sono presenti le seguenti rubriche: ");
						for(Rubrica r:controller.getRubriche()) 
						{
							System.out.print(r.getNome()+"   ");
						}
						System.out.println(" La Rubrica Selezionata �: "+controller.getRubricaSelezionata().getNome());
					}
				}
			}
		});
			
		/**
		 * Quando � premuto il button "modifica"
		 */
		btnModifica.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtUtenteSelezionato.getText().isBlank()==false) 
				{
				//Fisso la rubrica selezionata dalla combobox attraverso il suo nome
				controller.setRubricaSelezionata(comboBox.getSelectedIndex());
					String visualizzata = "Ridenomina ".concat(controller.getRubricaSelezionata().getNome());
					String stringa = JOptionPane.showInputDialog(visualizzata);
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
							System.out.println("Entrato nel Catch..");
							JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
	                                                			JOptionPane.ERROR_MESSAGE );
						}
						System.out.println("Superato il Catch.. In memoria: ");
						for(Rubrica r:controller.getRubriche()) 
						{
							System.out.print(r.getNome()+"   ");
						}
						System.out.println(" La Rubrica Selezionata �: "+controller.getRubricaSelezionata().getNome());
					}
				}
			}
		});
		
	}
}