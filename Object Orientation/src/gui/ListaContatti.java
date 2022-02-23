package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.FlowLayout;

public class ListaContatti extends JFrame {

	private JPanel contentPane;
	
	private JFrame frame;
	private Controller controller;
	/**
	 * Create the frame.
	 */
	public ListaContatti(Controller c, JFrame frameChiamante) {
		frame = this;
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JList<Object> list = new JList<Object>(controller.getNomiContattiRubrica());
		tabbedPane.addTab("tblListaContatti", null, list, null);
	}

}
