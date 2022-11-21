import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Interfaz_Usuario extends JFrame {
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JTextField text;


	public Interfaz_Usuario(String title) {
		super(title);
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		text = new JTextField();
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
	}

	public void ImportarResp() {
		boolean error = false;

		String filePath = "listado-de-posibles-responsables-de-sede.txt";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "UTF-8"));
			br.readLine().trim();
			BD miBD = new BD();
			Object[] tableLines = br.lines().toArray();
			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString();
					try {
						miBD.Insert("Insert into RespSede Values ('" + line + "'," + null + ");");
					} catch (Exception e) {
						RespSede s = new RespSede(line);
						if(s==null){
							text.setText("Error en la importacion en la línea " + line + "\n");
							error = true;
						}
						
					
				}

			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (!error) {
			text.setText("IMPORTACIÓN REALIZADA CON ÉXITO");
		}
	}
	
	public void mostrarUsuarios(){
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
