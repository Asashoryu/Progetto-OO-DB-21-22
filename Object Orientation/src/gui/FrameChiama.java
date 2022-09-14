package gui;

import controller.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Frame che gestisce la simulazione di una chiamata al numero selezionato.
 */
@SuppressWarnings("serial")
public class FrameChiama extends JFrame {

	/** Pannello dei contenuti. */
	private JPanel contentPane;
	
	/** Frame per chiamare il numero di riserva. */
	private FrameChiamaRiserva FinestraCR;
	
	/** Button chiama riserva. */
	private JButton btnChiamaRiserva;
	
	/** Button annulla. */
	private JButton btnAnnulla;
	
	/** numero di riserva. */
	private String numeroRiserva;

	/**
	 * Costruttore di un nuovo frame FrameChiama.
	 *
	 * @param c controller
     * @param frameInfoContatti frame InfoContatti a cui ritornare
	 * @param numeroChiamato numero chiamato
	 */
	public FrameChiama(Controller c, JFrame frameInfoContatti, String numeroChiamato) {
		setResizable(false);
		JFrame frame = this;

		Controller controller = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChiamando = new JLabel("Sto chiamando " + controller.getContattoSelezionato().getNome() + " al numero " + numeroChiamato + "...");
		lblChiamando.setForeground(new Color(204, 255, 255));
		lblChiamando.setFont(new Font("Arial", Font.PLAIN, 16));
		lblChiamando.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiamando.setBounds(10, 10, 416, 47);
		contentPane.add(lblChiamando);
		
		//Timer che dopo 3 secondi elimina il frame presente e ne crea uno nuovo che simula il reindirizzamento della chiamata a un nuovo numero.
		new Timer(3_000, (e) -> {
			lblChiamando.setVisible(false);
			contentPane.repaint();
			JLabel lblReindirizzamento = new JLabel("Nessuna risposta.. desideri chiamare il numero di riserva?");
			lblReindirizzamento.setHorizontalAlignment(SwingConstants.CENTER);
			lblReindirizzamento.setFont(new Font("Arial", Font.PLAIN, 16));
			lblReindirizzamento.setForeground(new Color(204, 255, 255));
			lblReindirizzamento.setBounds(10, 10, 416, 27);
			contentPane.add(lblReindirizzamento);
			
			btnChiamaRiserva = new JButton("Chiama");
			btnChiamaRiserva.setFont(new Font("Arial", Font.PLAIN, 11));
			btnChiamaRiserva.setBackground(new Color(204,255,255));
			btnChiamaRiserva.setForeground(new Color(102,102,153));
			btnChiamaRiserva.setFocusPainted(false);
			btnChiamaRiserva.setBounds(95, 72, 225, 39);
			contentPane.add(btnChiamaRiserva);
			
			/** Quando si sceglie di chiamare il numero di riserva*/
			btnChiamaRiserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					numeroRiserva = controller.reindirizza(numeroChiamato);
					JLabel lblReindirizzato = new JLabel("Chiamando il numero di riserva " + numeroRiserva + "...");
					lblReindirizzato.setHorizontalAlignment(SwingConstants.CENTER);
					lblReindirizzato.setFont(new Font("Arial", Font.PLAIN, 16));
					lblReindirizzato.setBounds(10, 10, 416, 27);
					FinestraCR = new FrameChiamaRiserva(frameInfoContatti, numeroRiserva);
					FinestraCR.setLocationRelativeTo(null);
					FinestraCR.setVisible(true);
					frame.dispose();
				}
			});
			
			btnAnnulla = new JButton("Annulla");
			btnAnnulla.setFont(new Font("Arial", Font.PLAIN, 11));
			btnAnnulla.setForeground(new Color(102,102,153));
			btnAnnulla.setBackground(new Color(204,255,255));
			btnAnnulla.setFocusPainted(false);
			btnAnnulla.setBounds(95, 120, 225, 39);
			contentPane.add(btnAnnulla);
			
			/** Quando è cliccato il button "Annulla"*/
			btnAnnulla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frameInfoContatti.setVisible(true);
					frame.dispose();
				}
			});
		}).start();
	}
}