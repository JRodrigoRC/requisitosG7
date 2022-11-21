import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class HomePage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JButton generarCSVButton, importarSedesButton, asignarRespButton;

	public HomePage(String title) {
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
		
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		pack();
	}
	
}
