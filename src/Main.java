import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		HomePage mp = new HomePage("Página principal");
		mp.setVisible(true);
		HomePage.generarCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVTable frame = new CSVTable("Importar CSV", "datos-estudiantes-pevau.csv");
				frame.setVisible(true);
				CSVTable.importButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CSVTable.generarTabla();
					}
				});
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
