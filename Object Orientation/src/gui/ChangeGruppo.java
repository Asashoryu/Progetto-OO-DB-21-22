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
import java.awt.Font;

@SuppressWarnings("serial")
public class ChangeGruppo extends JFrame{
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNome;
	private Controller controller;
	
	public ChangeGruppo(Controller c, JFrame frameChiamante, JList<Object> listaContattiChiamante, JList<Object> listaGruppiChiamante) {
		
		setResizable(false);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximumSize(new Dimension(550, 580));
		getContentPane().setBackground(new Color(224, 255, 255));

		frame = this;
		controller = c;

		frame.setTitle("Modifica gruppo");
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
		pannelloContattiMain.setBackground(new Color(255, 255, 255));
		pannelloContattiMain.setBounds(45, 90, 249, 243);
		contentPane.add(pannelloContattiMain);
		pannelloContattiMain.setLayout(new BorderLayout(0, 0));
		
		JPanel pannelloContatti = new JPanel();
		pannelloContatti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pannelloContatti.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloContatti.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloContatti.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPaneContatti = new JScrollPane(pannelloContatti);
		scrollPaneContatti.setBackground(new Color(255, 255, 255));
		pannelloContatti.setLayout(new BoxLayout(pannelloContatti, BoxLayout.PAGE_AXIS));
		scrollPaneContatti.setPreferredSize(pannelloContatti.getSize());
		pannelloContattiMain.add(scrollPaneContatti, BorderLayout.CENTER);
		
		
		
		JPanel pannelloNomeGruppo = new JPanel();
		pannelloNomeGruppo.setBackground(new Color(255, 255, 255));
		pannelloNomeGruppo.setBounds(84, 10, 169, 58);
		contentPane.add(pannelloNomeGruppo);
		pannelloNomeGruppo.setLayout(null);
								
		textFieldNome = new JTextField();
		textFieldNome.setBounds(23, 27, 120, 19);
		pannelloNomeGruppo.add(textFieldNome);
		textFieldNome.setColumns(10);
		inizializzaFrame(pannelloContatti);
										
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
		JButton btnModifica = new JButton("Vai");
		btnModifica.setForeground(new Color(102, 102, 153));
		btnModifica.setBackground(new Color(204, 255, 255));
		btnModifica.setBounds(177, 353, 84, 21);
		contentPane.add(btnModifica);
		
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica preventiva se sono state rilevate delle modifiche
				if (checkModificato(pannelloContatti))
				{
					Boolean selezionatoAlmenoUno = false;
					// aggiunta dei contatti selezionati all'ArrayList 'contatti'
					for (Component scrollComponent : pannelloContatti.getComponents())
					{
						// se non Ë un button allora Ë il pannello con gli indirizzi
						if(scrollComponent instanceof JPanel)
						{
							// estraggo le informazioni dal panel trovato
							JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
//						controller.addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
							if (checkbox.isSelected())
							{
								selezionatoAlmenoUno = true;
								break;
							}
						}
						
					}
					// controlli e inserimento
					if (!textFieldNome.getText().isBlank())
					{
						textFieldNome.setBackground(Color.WHITE);
						if (selezionatoAlmenoUno == true)
						{
							try
							{
								controller.changeGruppo(textFieldNome, pannelloContatti);
								listaGruppiChiamante.removeAll();
								listaGruppiChiamante.setListData(controller.getNomiGruppiRubrica());
								listaContattiChiamante.removeAll();
								listaContattiChiamante.setListData(controller.getNomiContattiGruppoSelezionato());
								JOptionPane.showConfirmDialog(null, 
										"Gruppo inserito con successo!", "Inserimento completato", JOptionPane.DEFAULT_OPTION);
								listaGruppiChiamante.revalidate();
								listaGruppiChiamante.repaint();
								listaContattiChiamante.revalidate();
								listaContattiChiamante.repaint();
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
				else {
					JOptionPane.showMessageDialog(null, "Non sono state rinvenute modifiche dei campi",
						      "Attenzione", JOptionPane.WARNING_MESSAGE);
					System.out.println("Non sono state rinvenute modifiche dei campi");
				}
			}
		});
		
	}
	
	private void inizializzaFrame(JPanel pannelloContatti)
	{
		JPanel panel;
		JCheckBox checkbox;
		int i = 0;
		
		textFieldNome.setText(controller.getGruppoSelezionato().getNome());
		
		for (String nomeContatto : controller.getNomiContattiRubrica())
		{
			panel = new JPanel();
			checkbox = new JCheckBox(nomeContatto);
			checkbox.setBackground(new Color(204, 255, 255));
			for (int j = 0; j < controller.getGruppoSelezionato().getContatti().size(); j++)
			{
				if (controller.getGruppoSelezionato().getContatti().get(j) == controller.getRubricaSelezionata().getContatti().get(i))
				{
					checkbox.setSelected(true);
				}
			}
			
//			 ItemListener
			panel.add(checkbox);
			panel.setBackground(new Color(204, 255, 255));
			pannelloContatti.add(panel);
			i++;
		}
	}
	
	private boolean checkModificato(JPanel pannelloContatti)
	{
		Boolean modificato = false;
		Boolean trovato    = false;
		
		int i = 0;
		// Verifico se il nome del gruppo Ë stato modificato
		if (!textFieldNome.getText().equals(controller.getGruppoSelezionato().getNome()))
		{
			modificato = true;
		}
		// Verifico se i contatti del gruppo sono stati modificati
		for (Component component : pannelloContatti.getComponents())
		{
			trovato = false;
			for (int j = 0; j < controller.getGruppoSelezionato().getContatti().size(); j++)
			{
				if (controller.getGruppoSelezionato().getContatti().get(j) == controller.getRubricaSelezionata().getContatti().get(i))
				{
					trovato = true;
				}
			}
			if ( (((JCheckBox)((JPanel) component).getComponents()[0]).isSelected() == true  &&  trovato == false) ||
				 (((JCheckBox)((JPanel) component).getComponents()[0]).isSelected() == false &&  trovato == true ))
			{
				modificato = true;
			}
			i++;
		}
		
		return modificato;
	}
}
