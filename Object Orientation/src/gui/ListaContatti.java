package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.event.ObjectChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JScrollPane;

public class ListaContatti extends JFrame {
	
	private JFrame frame;
	private Controller controller;
	/**
	 * Create the frame.
	 */
	public ListaContatti(Controller c, JFrame frameChiamante) {
		
		frame = this;
        controller=c;
  
        // Display the window.
        frame.setTitle("Rubrica di "+controller.getRubricaSelezionata().getNome());
        frame.setBounds(500, 200, 650, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        // set flow layout for the frame
        frame.getContentPane().setLayout(new FlowLayout());
        
        JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList<Object> list = new JList<Object>(controller.getNomiContattiRubrica());
		scrollPane.setViewportView(list);;
        
	}
}
