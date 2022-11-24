import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaSedes extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JButton removeButton, importarSedesButton;
	static JTable table;
	static DefaultTableModel model;


	public TablaSedes(String title) {
		super(title);		
		JPanel buttonPanel = new JPanel();
		importarSedesButton = new JButton("Importar Sedes");
		buttonPanel.add(importarSedesButton);
		removeButton = new JButton("Borrar Sede");
		buttonPanel.add(removeButton);
		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);

		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(scroll, BorderLayout.CENTER);
		pack();
	}

	public static void ImportarSedes() {
		JFileChooser fc;
		fc = new JFileChooser();
		fc.setApproveButtonText("Selecciona archivo");
		fc.showSaveDialog(null);
		File archivo = new File(fc.getSelectedFile().toString());
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));
			br.readLine().trim();
			String[] columnsName = { "Sede"};
			model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(columnsName);
			String insert = "Insert ignore into Sede Values ";
			Object[] tableLines = br.lines().toArray();
            for(int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("\n");
                model.addRow(dataRow);
                insert = insert.concat("(" + i + ",'"+line+"'),");
                br.close();
            }
            insert = insert.substring(0, insert.length()-1);
			insert.concat(";");
			BD miBD = new BD();
			miBD.Insert(insert);
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Archivo incorrecto");
		}
	}
	
	public static void borrarSeleccionado() {
		if(table.getSelectedRow() != -1) {
		   Vector v = (Vector) model.getDataVector().get(table.getSelectedRow()); 
		   BD miBD = new BD();
		   miBD.Delete("DELETE FROM Sede where nombre = '" + (String)v.get(0) +"';");
       	   model.removeRow(table.getSelectedRow());
           JOptionPane.showMessageDialog(null, "Sede eliminada");
     		} else {
	        JOptionPane.showMessageDialog(null, "No se puede eliminar");
		}
	}

}
