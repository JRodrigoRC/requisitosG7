package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlCSVMateriasTable;
import Controladores.CntrlCSVTable;
import Datos.BD;

public class InterfazCSVMateriasTable extends JFrame{
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JButton importButton;
	static JTextField text;
	
	public static String IMPORTAR_CSV = "IMPORTAR_CSV";
	
	public InterfazCSVMateriasTable(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);
		JPanel buttonPanel = new JPanel();
		importButton = new JButton("Importar csv Materias");
		buttonPanel.add(importButton);
		text = new JTextField();
		Dimension d = new Dimension(500,50);
		text.setPreferredSize(d);
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
	}
	
	public void controlador (CntrlCSVMateriasTable c) {
		importButton.addActionListener(c);
		importButton.setActionCommand(IMPORTAR_CSV);
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
				String[] columnsName = { "Nombre", "Tramo" };

				model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(columnsName);

				Object[] tableLines = br.lines().toArray();
				String insert = "INSERT IGNORE INTO Examen Values ";
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
					String[] row = { dataRow[0], dataRow[1] };
					if (!errorLinea) {
						model.addRow(row);
						insert = insert.concat("('" + dataRow[0] + "','" + dataRow[1] + "'),");

					} else {
						text.setText(text.getText() + " linea " + i+1 + " :" + dataRow[0] + ", " + dataRow[1] + "\n");
						
					}
					errorLinea = false;
				}
				insert = insert.substring(0, insert.length()-1);
				insert.concat(";");
				BD miBD = new BD();
				miBD.Insert(insert);
				br.close();
				if (!error) {
					text.setText("IMPORTACION REALIZADA CON EXITO");
				}
				table.setAutoCreateRowSorter(true);
			} catch (Exception e) {
				text.setText("");
				JOptionPane.showMessageDialog(null, "Archivo incorrecto");
			}
			
		}
	
}
