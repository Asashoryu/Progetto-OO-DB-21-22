package gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
		
		JPanel pannelloContatti = new JPanel();
		pannelloContatti.setBounds(46, 121, 249, 243);
		contentPane.add(pannelloContatti);
		pannelloContatti.setLayout(new BorderLayout(0, 0));
		
		JPanel pannelloContatti_1 = new JPanel();
		pannelloContatti_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pannelloContatti_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		pannelloContatti_1.setBackground(Color.ORANGE);
		
		JScrollPane scrollPaneContatti = new JScrollPane(pannelloContatti_1);
		pannelloContatti_1.setLayout(new BoxLayout(pannelloContatti_1, BoxLayout.PAGE_AXIS));
		scrollPaneContatti.setPreferredSize(pannelloContatti_1.getSize());
		pannelloContatti.add(scrollPaneContatti, BorderLayout.CENTER);
		
		initPannelloContatti(pannelloContatti_1);
		
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
		
	}
	
	private void initPannelloContatti(JPanel pannelloContatti)
	{
		JPanel panel;
		Checkbox checkbox;
		for (String nomeContatto : controller.getNomiContattiRubrica())
		{
			panel = new JPanel();
			checkbox = new Checkbox(nomeContatto);
			panel.add(checkbox);
			pannelloContatti.add(panel);
		}
	}
}

