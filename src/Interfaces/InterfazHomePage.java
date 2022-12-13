package Interfaces;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controladores.CntrlHomePage;

public class InterfazHomePage extends JFrame{
	private static final long serialVersionUID = 1L;
	static JButton generarCSVButton, importarSedesButton, asignarRespButton, gestionarSedes;
	
	public static String IMPORTAR_CSV = "IMPORTAR_CSV";
	public static String IMPORTAR_SEDES = "IMPORTAR_SEDES";
	public static String ASIGNAR_RESP = "ASIGNAR_RESP";
	public static String GESTIONAR_SEDES = "GESTIONAR_SEDES";
	
	public InterfazHomePage(String title) {
		super(title);
		JPanel titlePanel = new JPanel();
	    this.setPreferredSize(new Dimension(400, 300));

	    TitledBorder border = new TitledBorder("PEV-UMA");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		titlePanel.setBorder(border);
		
		
		JPanel buttonPanel = new JPanel();
		generarCSVButton = new JButton("Importar CSV Estudiantes");
		buttonPanel.add(generarCSVButton);
		importarSedesButton = new JButton("Importar Sedes");
		buttonPanel.add(importarSedesButton);
		asignarRespButton = new JButton("Asignar Responsable");
		buttonPanel.add(asignarRespButton);
		gestionarSedes = new JButton("Gestionar Sedes");
		buttonPanel.add(gestionarSedes);
		
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		pack();
	}
	
	public void controlador (CntrlHomePage c) {
		generarCSVButton.addActionListener(c);
		generarCSVButton.setActionCommand(IMPORTAR_CSV);
		
		importarSedesButton.addActionListener(c);
		importarSedesButton.setActionCommand(IMPORTAR_SEDES);
		
		asignarRespButton.addActionListener(c);
		asignarRespButton.setActionCommand(ASIGNAR_RESP);
		
		gestionarSedes.addActionListener(c);
		gestionarSedes.setActionCommand(GESTIONAR_SEDES);
		
	}
	
	
	
	
}
