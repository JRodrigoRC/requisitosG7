import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CSVTable extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JButton logButton, importButton;
	static JTextField text;
	

	public CSVTable(String title, String source) {
		super(title);
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);

		JPanel buttonPanel = new JPanel();
		importButton = new JButton("Importar csv");
		buttonPanel.add(importButton);
		text = new JTextField();

		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
	}
	

    public static void generarTabla() {
    	boolean error = false;

        String filePath = "datos-estudiantes-pevau.csv";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath), "UTF-8"));
            
            String firstLine = br.readLine().trim();
            String[] columnsName = { "Nombre", "Apellido", "Materia", "Instituto" };
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setColumnIdentifiers(columnsName);
            
            BD miBD = new BD();
            Object[] tableLines = br.lines().toArray();
            for(int i = 0; i < tableLines.length; i++)
            {
            	boolean errorLinea = false;
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(";");
                for(String s : dataRow) {
                	if (s.isEmpty()) {
                		error = true;
                		errorLinea = true;
                	}
                }
                String[] row = {dataRow[1], dataRow[2], dataRow[5], dataRow[0]};
               
                if(!errorLinea) {
                    model.addRow(row);
                } else {
            		text.setText("Error en la importacion en la línea " + dataRow[1] + " " + dataRow[2] + " " + dataRow[0] +"\n");
                }
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        if(!error) {
        	text.setText("IMPORTACIÓN REALIZADA CON ÉXITO");
        }
        table.setAutoCreateRowSorter(true); 
    }

	
}
