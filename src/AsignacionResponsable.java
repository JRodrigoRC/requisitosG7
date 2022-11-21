import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class AsignacionResponsable extends JFrame {

	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JButton importButton;
	static JButton asignarResp;
	
	public AsignacionResponsable (String title, String source){
		super(title);
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);

		JPanel buttonPanel = new JPanel();
		importButton = new JButton("Importar usuarios");
		asignarResp = new JButton("Asignar responsable");
		buttonPanel.add(importButton);
		buttonPanel.add(asignarResp);

		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		pack();
	}
	
	public void CargarSedes(){
		String[] columnsName = {"Nombre","Responsable asignado"};
		model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnsName);
        BD miBD = new BD();
        for (Object[] tupla : miBD.Select("SELECT * FROM Sede;")) {
        	String nombre = (String) tupla[1];
        	String resp;
        	try{
        		Object[] r = miBD.Select("SELECT NOMBRE FROM RespSede WHERE sede = " + (int) tupla[0] +";").get(0);
        		resp = (String) r[0];
        	}catch(Exception e){
        		resp = "No tiene responsable asignado";
        	}
        	String[] row = {nombre,resp};
        	model.addRow(row);
		}
       
	}

	
	
}
