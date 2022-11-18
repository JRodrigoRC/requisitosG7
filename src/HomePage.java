import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class HomePage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JButton generarCSVButton, gestionSedesButton;

	public HomePage(String title) {
		super(title);
		JPanel buttonPanel = new JPanel();

	    TitledBorder border = new TitledBorder("PEV-UMA");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		buttonPanel.setBorder(border);

		generarCSVButton = new JButton("Importar CSV Estudiantes");
		buttonPanel.add(generarCSVButton);
		gestionSedesButton = new JButton("Gestionar Sedes");
		buttonPanel.add(gestionSedesButton);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		pack();
	}
	
}
