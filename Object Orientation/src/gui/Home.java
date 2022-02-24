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
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;

public class Home extends JFrame {
	private Controller controller;

	JFrame frame;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JComboBox comboBox;
	private JButton btnModifica;
	private JTextField txtUtenteSelezionato;
	private JButton btnEntra;
	private JButton btnAggiungi;
	private JButton btnElimina;

	/**
	 * Create the application.
	 */
	public Home(Controller c) {
		controller=c;
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home");
		frame.setBounds(500, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Utente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<Object>(controller.getNomiRubriche());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUtenteSelezionato.setText(comboBox.getSelectedItem().toString());
			}
		});
				
		panel.add(comboBox);
		txtUtenteSelezionato = new JTextField();
		txtUtenteSelezionato.setEditable(false);
		panel.add(txtUtenteSelezionato);
		txtUtenteSelezionato.setColumns(10);
		
		controller.setRubricaSelezionata(0);
		txtUtenteSelezionato.setText(comboBox.getItemAt(0).toString());
		
		btnEntra = new JButton("Entra");
		panel.add(btnEntra);
		btnEntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setRubricaSelezionata(comboBox.getSelectedIndex());
				controller.loadContatti();
				JFrame contattiFrame = new ListaContatti(controller, frame);
				System.out.println("Frame Contatti caricato");
				frame.setVisible(false);
				contattiFrame.setVisible(true);
			}
		});
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Seleziono la stringa corrispondente alla stringa selezionata nella combobox
				//così da evitare la ricerca della stessa
				controller.setRubricaSelezionata(comboBox.getSelectedIndex());
					String visualizzata = "Ridenomina ".concat(controller.getRubricaSelezionata().getNome());
					String stringa = JOptionPane.showInputDialog(visualizzata);
					if(stringa!= null && stringa.isBlank()==false) {
						try {
						controller.updateRubrica(stringa);
						//In caso di Exception non entra in questo punto
						int indiceSelezionato = comboBox.getSelectedIndex();
						comboBox.removeItemAt(indiceSelezionato);
						comboBox.insertItemAt((Object) controller.getRubricaSelezionata().getNome(),indiceSelezionato);
						comboBox.setSelectedIndex(indiceSelezionato);
						txtUtenteSelezionato.setText(comboBox.getSelectedItem().toString());
						System.out.println("Utente modificato in "+ comboBox.getSelectedItem().toString());
						} catch (Exception e2) {
							System.out.println("Entrato nel Catch..");
							JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                                 			JOptionPane.ERROR_MESSAGE );
						}
						System.out.println("Superato il Catch.. In memoria: ");
						for(Rubrica r:controller.getRubriche()) {
							System.out.print(r.getNome()+"   ");
						}
						System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
					}
				}
		});
		panel_1.add(btnModifica);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String visualizzata = "Inserisci il nome della Rubrica da aggiungere";
				String stringa = JOptionPane.showInputDialog(visualizzata);
				if(stringa!= null && stringa.isBlank()==false) {
					try {
					//aggiunge la rubrica nel DB e in memoria
						
					controller.addRubrica(stringa);
					//Una volta effetuate le modifiche nel DB e in memoria, si aggiorna il frame
					int indiceSelezionato = comboBox.getSelectedIndex();
					comboBox.addItem((Object) stringa);
					comboBox.setSelectedIndex(indiceSelezionato);
					System.out.println("Utente modificato in "+ comboBox.getSelectedItem().toString());
					} catch (Exception e2) {
						System.out.println("Entrato nel Catch..");
						JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                             			JOptionPane.ERROR_MESSAGE );
					}
					//debug manuale
					System.out.println("Superato il Catch.. In memoria: ");
					for(Rubrica r:controller.getRubriche()) {
						System.out.print(r.getNome()+"   ");
					}
					System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
				}
			}
		});
		panel_1.add(btnAggiungi);
		
		btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringa=txtUtenteSelezionato.getText();
				String visualizzata = "Confermi di voler eliminare la Rubrica ".concat(stringa);
				// 0=Procedi, 1=Annulla
				Object[] opzioni = {"Procedi","Annulla"};
				// finestra di conferma per la cancellazione
				int input = JOptionPane.showOptionDialog(rootPane, visualizzata, "Elimina", JOptionPane.YES_NO_OPTION,
														 JOptionPane.WARNING_MESSAGE, null, opzioni, null);
				if(input==0 && stringa!= null && stringa.isBlank()==false) {
					try {
					//rimuove dalla rubrica nel DB e in memoria
					controller.setRubricaSelezionata(comboBox.getSelectedIndex());
					controller.deleteRubrica();
					//Una volta effetuate le modifiche nel DB e in memoria, si aggiorna il frame
					comboBox.removeItemAt(comboBox.getSelectedIndex());
					comboBox.setSelectedIndex(0);
					txtUtenteSelezionato.setText("");
					System.out.println("Utente modificato in "+ comboBox.getSelectedItem().toString());
					} catch (Exception e2) {
						System.out.println("Entrato nel Catch..");
						JOptionPane.showMessageDialog( null, "Valore non valido" , "Errore",
                                             		   JOptionPane.ERROR_MESSAGE );
					}
					
					//debug manuale
					System.out.println("Superato il Catch.. In memoria: ");
					for(Rubrica r:controller.getRubriche()) {
						System.out.print(r.getNome()+"   ");
					}
					System.out.println(" La Rubrica Selezionata è: "+controller.getRubricaSelezionata().getNome());
				}
			}
		});
		panel_1.add(btnElimina);
	}
}
