package gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Gruppo;

public class AddGruppo extends JFrame{
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNome;
	private JButton btnNewButton;
	private Controller controller;
	private JButton btnNewButton_1;
	private JPanel panelMain;
	private JPanel pannelloElemScrollPane;
	private ListSelectionModel listSelectionModel;
	
	public AddGruppo(Controller c, JFrame frameChiamante, JList<Object> lista) {
		
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;

		frame.setTitle("Aggiunta Nuovo Contatto");
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 472);
		contentPane = new JPanel();
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(224, 255, 255));
		contentPane.setLayout(null);
		
		/**
		 * 
		 */
		
		JPanel pannelloContattiMain = new JPanel();
		pannelloContattiMain.setBounds(46, 121, 249, 243);
		contentPane.add(pannelloContattiMain);
		pannelloContattiMain.setLayout(new BorderLayout(0, 0));
		
		JPanel pannelloContatti = new JPanel();
		pannelloContatti.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloContatti.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloContatti.setBackground(Color.ORANGE);
		
		JScrollPane scrollPaneContatti = new JScrollPane(pannelloContatti);
		pannelloContatti.setLayout(new BoxLayout(pannelloContatti, BoxLayout.PAGE_AXIS));
		scrollPaneContatti.setPreferredSize(pannelloContatti.getSize());
		pannelloContattiMain.add(scrollPaneContatti, BorderLayout.CENTER);
		
		initPannelloContatti(pannelloContatti);
		
		JPanel pannelloNomeGruppo = new JPanel();
		pannelloNomeGruppo.setBounds(84, 28, 169, 58);
		contentPane.add(pannelloNomeGruppo);
		pannelloNomeGruppo.setLayout(null);
								
		textFieldNome = new JTextField();
		textFieldNome.setBounds(35, 27, 100, 19);
		pannelloNomeGruppo.add(textFieldNome);
		textFieldNome.setColumns(10);
		
										
		lblNome = new JLabel("Nome ");
		lblNome.setBounds(35, 11, 96, 13);
		pannelloNomeGruppo.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * Button "annulla"
		 */
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(84, 375, 75, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		
		/**
		 * Button "vai"
		 */
		JButton btnAzione = new JButton("Vai");
		btnAzione.setBounds(178, 375, 75, 21);
		contentPane.add(btnAzione);
		
		btnAzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Contatto> contatti = new ArrayList<>();
				int indice = 0;
				
				for (Component scrollComponent : pannelloContatti.getComponents())
				{
					// se non è un button allora è il pannello con gli indirizzi
					if(scrollComponent instanceof JPanel)
					{
						// estraggo le informazioni dal panel trovato
						JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
//						controller.addIndirizzoSec(nuovoContatto, viaSec, cittàSec, nazioneSec, capSec);
						if (checkbox.isSelected())
						{
							contatti.add(controller.getRubricaSelezionata().getContatti().get(indice));
							System.out.println(" Debug: "+checkbox.getText() + " e quello salvato è " + controller.getRubricaSelezionata().getContatti().get(indice).getNome());
						}
					}
					
					indice++;
				}
				if (!textFieldNome.getText().isBlank())
				{
					textFieldNome.setBackground(Color.WHITE);
					if (contatti.size() > 0)
					{
						Gruppo nuovoGruppo = new Gruppo(textFieldNome.getText(), contatti);
						try 
						{
							controller.addGruppo(nuovoGruppo);
							lista.removeAll();
							lista.setListData(controller.getNomiGruppiRubrica());
							JOptionPane.showConfirmDialog(null, 
					                "Gruppo inserito con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
							lista.revalidate();
							lista.repaint();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),
									"Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Un Gruppo deve contenere almeno un contatto",
								"Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					textFieldNome.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Inserire un nome valido",
							"Errore di inserimento nel Database", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	private void initPannelloContatti(JPanel pannelloContatti)
	{
		JPanel panel;
		JCheckBox checkbox;
		for (String nomeContatto : controller.getNomiContattiRubrica())
		{
			panel = new JPanel();
			checkbox = new JCheckBox(nomeContatto);
			
			// ItemListener
//			ItemListener itemListener = new ItemListener() {
//				@Override
//				public void itemStateChanged(ItemEvent itemEvent) {
//					int state = itemEvent.getStateChange();
//			        if (state == ItemEvent.SELECTED) {
//			        	nuovoGruppo;
//			        }
//			    }
//			};
				  
			panel.add(checkbox);
			pannelloContatti.add(panel);
		}
	}
}

