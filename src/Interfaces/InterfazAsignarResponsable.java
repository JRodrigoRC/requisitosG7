package Interfaces;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlAsignarResponsable;
import Datos.BD;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InterfazAsignarResponsable extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tableSedes;
	private static JTable tableResp;
	private JButton importButton, asigButton;
	static DefaultTableModel model;
	
	public static String IMPORTAR_Resp = "IMPORTAR_Resp";
	public static String ASIGNAR_RESP = "ASIGNAR_RESP";

	public InterfazAsignarResponsable(String string) {
		super(string);
		setBounds(100, 100, 969, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		importButton = new JButton("Importar responsables de sede");
		importButton.setBounds(39, 10, 216, 21);
		contentPane.add(importButton);
		
		asigButton = new JButton("Asignar");
		asigButton.setBounds(295, 10, 85, 21);
		contentPane.add(asigButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 99, 428, 303);
		contentPane.add(scrollPane);
		
		tableSedes = new JTable();
		scrollPane.setViewportView(tableSedes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(504, 99, 441, 303);
		contentPane.add(scrollPane_1);
		
		tableResp = new JTable();
		scrollPane_1.setViewportView(tableResp);
	}
	
	public void controlador (CntrlAsignarResponsable c) {
		importButton.addActionListener(c);
		importButton.setActionCommand(IMPORTAR_Resp);
		
		asigButton.addActionListener(c);
		asigButton.setActionCommand(ASIGNAR_RESP);
	}

	public static void mostrarSedes() {
		String[] columnsName = {"Nombre","Responsable asignado"};
		DefaultTableModel model = (DefaultTableModel)tableSedes.getModel();
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

	public static void mostrarResp() {
		String[] columnsName = {"Nombre","Sede asignada"};
		DefaultTableModel model = (DefaultTableModel)tableResp.getModel();
        model.setColumnIdentifiers(columnsName);
        BD miBD = new BD();
        for (Object[] tupla : miBD.Select("SELECT * FROM RespSede;")) {
        	String nombre = (String) tupla[0];
        	String resp;
        	try{
        		Object[] r = miBD.Select("SELECT NOMBRE FROM Sede WHERE id = " + (int) tupla[1] +";").get(0);
        		resp = (String) r[0];
        	}catch(Exception e){
        		resp = "No tiene sede asignada";
        	}
        	String[] row = {nombre,resp};
        	model.addRow(row);
		}
		
	}

	public static void asignar() {
		if(tableSedes.getSelectedRow()!=-1 && tableResp.getSelectedRow()!=-1) {
			 DefaultTableModel modelSed = (DefaultTableModel)tableSedes.getModel();
			 DefaultTableModel modelResp = (DefaultTableModel)tableResp.getModel();
			 Vector v = (Vector) modelSed.getDataVector().get(tableSedes.getSelectedRow());
			 BD miBD = new BD();
			 Object[] sede = miBD.Select("SELECT * from Sede where nombre ='" + (String)v.get(0) + "';").get(0);
			 v = (Vector) modelResp.getDataVector().get(tableResp.getSelectedRow());
			 Object[] resp = miBD.Select("SELECT * from RespSede where nombre ='" + (String)v.get(0) + "';").get(0);
			 try{
				 miBD.Update("UPDATE RespSede SET sede = " + (int)sede[0] + " WHERE nombre = '" + (String)resp[0]);
			 }catch(Exception e){
				 miBD.Update("UPDATE RespSede SET sede = " + null + " WHERE sede = " + (int)sede[0]);
				 miBD.Update("UPDATE RespSede SET sede = " + (int)sede[0] + " WHERE nombre = '" + (String)resp[0] + "'");
			 }
			 JOptionPane.showMessageDialog(null, "El responsable " + (String)resp[0] + " ha sido asignado a la sede " + (String)sede[1]);
		}
		
	}

	public static void clearResp() {
		for (int i = 0; i < tableResp.getRowCount(); i++) {
			DefaultTableModel modelResp = (DefaultTableModel)tableResp.getModel();
			modelResp.removeRow(i);
			i-=1;
		}
			
	}

	public static void clearSedes() {
		for (int i = 0; i < tableSedes.getRowCount(); i++) {
			 DefaultTableModel modelSed = (DefaultTableModel)tableSedes.getModel();
			 modelSed.removeRow(i);
			i-=1;
		}
		
	}
}
