package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import controller.Controller;
import dao.SistemaDAO;
import model.Rubrica;
import model.Sistema;

import javax.swing.JComboBox;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Utente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<Object>(controller.getNomiRubriche());
		panel.add(comboBox);
		
		txtUtenteSelezionato = new JTextField();
		txtUtenteSelezionato.setEditable(false);
		panel.add(txtUtenteSelezionato);
		txtUtenteSelezionato.setColumns(10);
		
		btnEntra = new JButton("Entra");
		panel.add(btnEntra);
		btnEntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//il controller prende l'arraylist delle rubriche, tra cui selezioniamo
				//quella che ha lo stesso nome dell'utente selezionato dalla combobox
				//(gli indici delle rubriche e dei loro nomi coincidono)
				System.out.println(controller.getRubriche().get(comboBox.getSelectedIndex()).getNome());
				txtUtenteSelezionato.setText(comboBox.getSelectedItem().toString());
			}
		});
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setRubricaSelezionata(comboBox.getSelectedIndex());
				ModificaRubrica modificaRubrica = new ModificaRubrica(controller,frame);
			}
		});
		panel_1.add(btnModifica);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Rubrica r:controller.getRubriche()) {
					System.out.print(r.getNome()+"   ");
				}
				System.out.println(" "+controller.getRubricaSelezionata().getNome());
			}
		});
		panel_1.add(btnAggiungi);
		
		btnElimina = new JButton("Elimina");
		panel_1.add(btnElimina);
	}
	// metodo introdotto per mantenere la combobox aggiornata senza dover richiamare il DB
	// per dopo ogni modifica
	public void refreshComboBox() {
		comboBox.revalidate();
	}
}
