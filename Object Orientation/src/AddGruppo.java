package gui;

import controller.Controller;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class AddGruppo.
 */
@SuppressWarnings("serial")
public class AddGruppo extends JFrame{
	
	/** The frame. */
	private JFrame frame;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field nome. */
	private JTextField textFieldNome;
	
	/** The lbl nome. */
	private JLabel lblNome;
	
	/** The controller. */
	private Controller controller;
	
	/**
	 * Instantiates a new adds the gruppo.
	 *
	 * @param c the c
	 * @param frameChiamante the frame chiamante
	 * @param listaGruppiChiamante the lista gruppi chiamante
	 */
	public AddGruppo(Controller c, JFrame frameChiamante, JList<Object> listaGruppiChiamante) {
		
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;

		frame.setTitle("Aggiunta nuovo gruppo");
		frame.setBounds(500, 200, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 422);
		contentPane = new JPanel();
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		
		/**
		 * 
		 */
		
		JPanel pannelloContattiMain = new JPanel();
		pannelloContattiMain.setBounds(45, 90, 249, 243);
		contentPane.add(pannelloContattiMain);
		pannelloContattiMain.setLayout(new BorderLayout(0, 0));
		
		JPanel pannelloContatti = new JPanel();
		pannelloContatti.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloContatti.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloContatti.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPaneContatti = new JScrollPane(pannelloContatti);
		pannelloContatti.setLayout(new BoxLayout(pannelloContatti, BoxLayout.PAGE_AXIS));
		scrollPaneContatti.setPreferredSize(pannelloContatti.getSize());
		
		pannelloContattiMain.add(scrollPaneContatti, BorderLayout.CENTER);
		
		initPannelloContatti(pannelloContatti);
		
		JPanel pannelloNomeGruppo = new JPanel();
		pannelloNomeGruppo.setBackground(new Color(255, 255, 255));
		pannelloNomeGruppo.setBounds(84, 10, 169, 58);
		contentPane.add(pannelloNomeGruppo);
		pannelloNomeGruppo.setLayout(null);
								
		textFieldNome = new JTextField();
		textFieldNome.setBounds(23, 27, 120, 19);
		pannelloNomeGruppo.add(textFieldNome);
		textFieldNome.setColumns(10);
		
										
		lblNome = new JLabel("Nome ");
		lblNome.setBounds(23, 9, 120, 13);
		pannelloNomeGruppo.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * Button "annulla"
		 */
		JButton btnIndietro = new JButton("Annulla");
		btnIndietro.setForeground(new Color(204, 255, 255));
		btnIndietro.setBackground(new Color(102, 102, 153));
		btnIndietro.setBounds(83, 353, 84, 21);
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
		btnInserisci.setForeground(new Color(102, 102, 153));
		btnInserisci.setBackground(new Color(204, 255, 255));
		btnInserisci.setBounds(177, 353, 84, 21);
		contentPane.add(btnInserisci);
		
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!textFieldNome.getText().isBlank())
				{
					textFieldNome.setBackground(Color.WHITE);
					
					Boolean selezionatoAlmenoUno = false;
					for (Component scrollComponent : pannelloContatti.getComponents())
					{
						// se non Ë un button allora Ë il pannello con gli indirizzi
						if(scrollComponent instanceof JPanel)
						{
							// estraggo le informazioni dal panel trovato
							JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
							// controller.addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
							if (checkbox.isSelected())
							{
								selezionatoAlmenoUno = true;
								break;
							}
						}
					}
					if (selezionatoAlmenoUno == true)
					{
						try 
						{
							controller.addGruppo(textFieldNome, pannelloContatti);
							listaGruppiChiamante.removeAll();
							listaGruppiChiamante.setListData(controller.getNomiGruppiRubrica());
							JOptionPane.showConfirmDialog(null, 
					                "Gruppo inserito con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
							listaGruppiChiamante.revalidate();
							listaGruppiChiamante.repaint();
							frameChiamante.setVisible(true);
							frame.dispose();
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
	
	/**
	 * Inits the pannello contatti.
	 *
	 * @param pannelloContatti the pannello contatti
	 */
	private void initPannelloContatti(JPanel pannelloContatti)
	{
		JPanel panel;
		JCheckBox checkbox;
		for (String nomeContatto : controller.getNomiContattiRubrica())
		{
			panel = new JPanel();
			checkbox = new JCheckBox(nomeContatto);
			checkbox.setBackground(new Color(204, 255, 255));
			
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
			panel.setBackground(new Color(204, 255, 255));
			pannelloContatti.add(panel);
		}
	}
}

