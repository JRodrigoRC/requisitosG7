import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Asignar extends JFrame{
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JTextField text;
	static JButton asignar;

	
	public Asignar (String title){
		super(title);

		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);

		JPanel buttonPanel = new JPanel();
		asignar = new JButton("Asignar responsable");
		buttonPanel.add(asignar);
		text = new JTextField();

		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(text, BorderLayout.SOUTH);
		pack();
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
	
	public void asignar(String nombre){
		
			if(table.getSelectedRow()!=-1){
				 Vector v = (Vector) model.getDataVector().get(table.getSelectedRow());
				 BD miBD = new BD();
				 Object[] sede = miBD.Select("SELECT * from Sede where nombre ='" + nombre + "';").get(0);
				 Object[] resp = miBD.Select("SELECT * from RespSede where nombre ='" + (String)v.get(0) + "';").get(0);
				 try{
					 miBD.Update("UPDATE RespSede SET sede = " + (int)sede[0] + " WHERE nombre = '" + (String)resp[0]);
				 }catch(Exception e){
					 miBD.Update("UPDATE RespSede SET sede = " + null + " WHERE sede = " + (int)sede[0]);
					 miBD.Update("UPDATE RespSede SET sede = " + (int)sede[0] + " WHERE nombre = '" + (String)resp[0] + "'");
				 }
				 
				 JOptionPane.showMessageDialog(null, "El responsable " + (String)resp[0] + " ha sido asignado a la sede " + (String)sede[1]);
				 dispose();
			}
		
		}
		
	}

