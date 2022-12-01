package Interfaces;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Datos.BD;

public class InterfazImportarResp extends JFrame{
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JTextField text;
	public InterfazImportarResp(String string) {
		super(string);
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		text = new JTextField();
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
	}

	public static void importarResp() {
		boolean error = false;
		boolean errorLinea = false;
		JFileChooser fc;
		fc = new JFileChooser();
		fc.setApproveButtonText("Selecciona asignar");
		fc.showSaveDialog(null);
		File archivo = new File(fc.getSelectedFile().toString());
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(archivo), "UTF-8"));
			br.readLine().trim();
			String[] columnsName = {"Nombre responsable de sede"};
			model = (DefaultTableModel)table.getModel();
	        model.setColumnIdentifiers(columnsName);
			Object[] tableLines = br.lines().toArray();
			String insert = "INSERT IGNORE INTO RespSede Values ";
			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString();
				if(line.isEmpty()){
					errorLinea= true;
					error = true;
				}
				if(!errorLinea){
					String [] row = {line};
					model.addRow(row);
					insert = insert.concat("('" + line + "'," + null + "),");
				}else{
					text.setText("Error linea " + i+1 + " :" + line);
				}
					errorLinea=false;
				}
				
			insert = insert.substring(0, insert.length()-1);
			insert.concat(";");
			BD miBD = new BD();
			miBD.Insert(insert);
			br.close();
			if (!error) {
				text.setText("IMPORTACION REALIZADA CON EXITO");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Archivo incorrecto");
			
		}
		
		
	}

	public static void cargarResp() {
		String[] columnsName = {"Nombre","Sede asignada"};
		model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnsName);
        BD miBD = new BD();
        for (Object[] tupla : miBD.Select("SELECT * FROM RespSede;")) {
        	String nombre = (String) tupla[0];
        	String resp;
        	try{
        		Object[] r = miBD.Select("SELECT NOMBRE FROM Sede WHERE id = " + (int) tupla[1] +";").get(0);
        		resp = (String) r[0];
        	}catch(Exception e){
        		resp = "No tiene responsable asignado";
        	}
        	String[] row = {nombre,resp};
        	model.addRow(row);
		}
		
	}

}
