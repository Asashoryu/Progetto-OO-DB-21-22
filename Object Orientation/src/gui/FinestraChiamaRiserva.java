package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

@SuppressWarnings("serial")
public class FinestraChiamaRiserva extends JFrame {

	private JPanel contentPane;

	public FinestraChiamaRiserva(String NumeroDiRiserva) {
		setResizable(false);
		JFrame frame = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 204);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChiamandoR = new JLabel("Chiamando il numero di riserva " + NumeroDiRiserva + "...");
		lblChiamandoR.setForeground(new Color(204, 255, 255));
		lblChiamandoR.setFont(new Font("Arial", Font.PLAIN, 16));
		lblChiamandoR.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiamandoR.setBounds(0, 10, 426, 42);
		contentPane.add(lblChiamandoR);
		
		//Medesimo timer di FinestraChiama
		new Timer(3_000, (e) -> {
			contentPane.repaint();
			lblChiamandoR.setVisible(false);
			JLabel ChiamataFinita = new JLabel("La chiamata non ha avuto risposta.");
			ChiamataFinita.setForeground(new Color(204,255,255));
			ChiamataFinita.setFont(new Font("Arial", Font.PLAIN, 16));
			ChiamataFinita.setHorizontalAlignment(SwingConstants.CENTER);
			ChiamataFinita.setBounds(0, 10, 426, 42);
			contentPane.add(ChiamataFinita);
			
			JButton btnChiudi = new JButton("Chiudi"); 
			btnChiudi.setFont(new Font("Arial", Font.PLAIN, 11));
			btnChiudi.setForeground(new Color(102,102,153));
			btnChiudi.setBackground(new Color(204,255,255));
			btnChiudi.setFocusPainted(false);
			btnChiudi.setBounds(95, 72, 225, 39);
			contentPane.add(btnChiudi);
			btnChiudi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				frame.dispose();
				}
			});
		}).start();
	}
}
