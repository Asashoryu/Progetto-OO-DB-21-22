package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.Controller;

public class ContattoFrame extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	

	
	public ContattoFrame() {
		frame=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(21, 53, 68, 20);
		contentPane.add(lblNome);
		
		JTextField textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(21, 83, 129, 19);
		contentPane.add(textFieldNome);
		
		JTextField textFieldSecondoNome = new JTextField();
		textFieldSecondoNome.setColumns(10);
		textFieldSecondoNome.setBounds(186, 83, 129, 19);
		contentPane.add(textFieldSecondoNome);
		
		JTextField textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(364, 83, 129, 19);
		contentPane.add(textFieldCognome);
		
		JLabel lblSecondoNome = new JLabel("Secondo Nome");
		lblSecondoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSecondoNome.setBounds(186, 53, 96, 20);
		contentPane.add(lblSecondoNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCognome.setBounds(364, 53, 96, 20);
		contentPane.add(lblCognome);
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		scrollPaneNumeri.setBounds(21, 183, 190, 92);
		contentPane.add(scrollPaneNumeri);
		
		JList listNumeri = new JList();
		scrollPaneNumeri.setViewportView(listNumeri);
		
		JLabel lblNumeri = new JLabel("Numeri ");
		lblNumeri.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumeri.setBounds(21, 154, 62, 20);
		contentPane.add(lblNumeri);
		
		JScrollPane scrollIndirizziEmail = new JScrollPane();
		scrollIndirizziEmail.setBounds(329, 183, 188, 92);
		contentPane.add(scrollIndirizziEmail);
		
		
		JList listIndirizziEmail = new JList();
		scrollIndirizziEmail.setViewportView(listIndirizziEmail);
		
		JLabel lblIndirizziEmail = new JLabel("Indirizzi Email");
		lblIndirizziEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIndirizziEmail.setBounds(329, 154, 80, 20);
		contentPane.add(lblIndirizziEmail);
		
		JScrollPane scrollPaneIndirizziFisici = new JScrollPane();
		scrollPaneIndirizziFisici.setBounds(329, 316, 190, 92);
		contentPane.add(scrollPaneIndirizziFisici);
		
		JList listIndirizziFisici = new JList();
		scrollPaneIndirizziFisici.setViewportView(listIndirizziFisici);
		
		
		JLabel lblIndirizziFisici = new JLabel("Indirizzi Fisici");
		lblIndirizziFisici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIndirizziFisici.setBounds(329, 292, 90, 20);
		contentPane.add(lblIndirizziFisici);
		
		JLabel lblTitolo = new JLabel("INSERIRE NOME CONTATTO DINAMICAMENTE");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitolo.setBounds(10, 15, 483, 28);
		contentPane.add(lblTitolo);
		
		JScrollPane scrollPaneGruppi = new JScrollPane();
		scrollPaneGruppi.setBounds(21, 316, 190, 92);
		contentPane.add(scrollPaneGruppi);
		
		JList listGruppi = new JList();
		scrollPaneGruppi.setViewportView(listGruppi);
		
		JLabel lblGruppi = new JLabel("Gruppi");
		lblGruppi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGruppi.setBounds(21, 292, 45, 20);
		contentPane.add(lblGruppi);
		
		JButton btnAggiungiInfo = new JButton("Aggiungi");
		btnAggiungiInfo.setBackground(new Color(176, 224, 230));
		btnAggiungiInfo.setBounds(10, 426, 85, 21);
		contentPane.add(btnAggiungiInfo);
		
		JButton btnModificaInfo = new JButton("Modifica");
		btnModificaInfo.setBackground(new Color(176, 224, 230));
		btnModificaInfo.setBounds(126, 426, 85, 21);
		contentPane.add(btnModificaInfo);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBackground(new Color(176, 224, 230));
		btnElimina.setBounds(248, 426, 85, 21);
		contentPane.add(btnElimina);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(445, 426, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}
}
