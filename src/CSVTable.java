import java.awt.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CSVTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JButton importButton;
	static JTextField text;
	
	

	public CSVTable(String title, String source) {
		super(title);
		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);

		JPanel buttonPanel = new JPanel();
		importButton = new JButton("Importar csv");
		buttonPanel.add(importButton);
		text = new JTextField();
		Dimension d = new Dimension(500,50);
		text.setPreferredSize(d);
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
	}

	public static void generarTabla() {
		JFileChooser fc;
		fc = new JFileChooser();
		fc.setApproveButtonText("Selecciona csv");
		fc.showSaveDialog(null);
		boolean error = false;
		File archivo = new File(fc.getSelectedFile().toString());
		
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(archivo), "UTF-8"));

				br.readLine().trim();
				String[] columnsName = { "Nombre", "Apellido", "Materia",
						"Instituto" };

				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(columnsName);

				Object[] tableLines = br.lines().toArray();
				String insert = "INSERT IGNORE INTO Alumno Values ";
				text.setText("Error en la importación en la: \n");
				for (int i = 0; i < tableLines.length-1; i++) {
					boolean errorLinea = false;
					String line = tableLines[i].toString().trim();
					String[] dataRow = line.split(";");
					for (String s : dataRow) {
						if (s.isEmpty() || s.contains("'")) {
							error = true;
							errorLinea = true;
						}
					}
					String[] row = { dataRow[1], dataRow[2], dataRow[5], dataRow[0] };
					if (!errorLinea) {
						model.addRow(row);
						insert = insert.concat("('" + dataRow[4] + "','" + dataRow[1]
								+ "','" + dataRow[2] + "','" + dataRow[0] + "'),");

					} else {
						text.setText(text.getText() + " linea " + i+1 + " :" + dataRow[1] + ", " + dataRow[2] + ", " + dataRow[0]
								+ "\n");
						
					}
					errorLinea = false;
				}
				insert = insert.substring(0, insert.length()-1);
				insert.concat(";");
				BD miBD = new BD();
				miBD.Insert(insert);
				if (!error) {
					text.setText("IMPORTACION REALIZADA CON EXITO");
				}
				table.setAutoCreateRowSorter(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Archivo incorrecto");
				text.setText("");
			}
			
		}
	}


