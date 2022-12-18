package Interfaces;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controladores.CntrlAsignarResponsableAula;
import Datos.BD;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazAsignarResponsableAula extends JFrame{

	private static InterfazGestionSedes intGestion;
	private JButton btnAsignar;
	private static JTable tablaAulas;
	private JScrollPane scrollPane_1;
	private static JTable tablaResponsables;

	/**
	 * Create the application.
	 * @param frame2 
	 */
	public InterfazAsignarResponsableAula(String s, InterfazGestionSedes frame2) {
		intGestion = frame2;
		setTitle(s);
		initialize();
	}	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 800, 300);

		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 501, 173);
		getContentPane().add(scrollPane);
		
		tablaAulas = new JTable();
		scrollPane.setViewportView(tablaAulas);
		
		btnAsignar = new JButton("Asignar responsable");
		btnAsignar.setBounds(345, 26, 134, 40);
		getContentPane().add(btnAsignar);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(521, 77, 253, 173);
		getContentPane().add(scrollPane_1);
		
		tablaResponsables = new JTable();
		scrollPane_1.setViewportView(tablaResponsables);
		
		mostrarAulas();
		mostrarResp();
	}

	public void controlador(CntrlAsignarResponsableAula cntrl) {
		// TODO Auto-generated method stub		
		btnAsignar.addActionListener(cntrl);
		btnAsignar.setActionCommand("ASIGNAR RESPONSABLE");
	}
	
	private void mostrarAulas() {
		int IDSede = intGestion.getID();
		String[] colName = {"AULA" , "FRANJA", "RESPONSABLE"};
		DefaultTableModel model = (DefaultTableModel)tablaAulas.getModel();
		model.setColumnIdentifiers(colName);
		BD miBD = new BD();
		
		for (Object[] tupla : miBD.Select("SELECT id, franja, ifnull(responsable_aula, 'Responsable no asignado') FROM Aula WHERE sede = " + IDSede + ";")) {
			String id =  tupla[0].toString();
			String franja = tupla[1].toString();
			String responsable = tupla[2].toString();
			String[] row = {id, franja, responsable};
			model.addRow(row);
		}
		tablaAulas.setModel(model);
	}
	
	private void mostrarResp()
	{
		String[] colName = {"NOMBRE"};
		DefaultTableModel model = (DefaultTableModel)tablaResponsables.getModel();
		model.setColumnIdentifiers(colName);
		BD bd = new BD();
		
		for(Object[] o : bd.Select("SELECT nombre FROM RespAula"))
		{
			String nombre = o[0].toString();
			String[] row = {nombre};
			model.addRow(row);
		}
		tablaResponsables.setModel(model);
	}
	
	public void recargar()
	{
		DefaultTableModel modelAula = (DefaultTableModel)tablaAulas.getModel();
		tablaResponsables.clearSelection();
		modelAula.setRowCount(0);
		mostrarAulas();
	}
	
	public String[] aulaSelec()
	{
		String[] keys = new String[2];
		
		if(tablaAulas.getSelectedRow() < 0)
		{
			keys[0] = "-1";
			keys[1] = "null";
			return keys;
		}
		else
		{
			DefaultTableModel model = (DefaultTableModel)tablaAulas.getModel();
			Vector v = (Vector) model.getDataVector().get(tablaAulas.getSelectedRow());
			keys[0] = v.get(0).toString();
			keys[1] = v.get(1).toString();
			return keys;
		}
	}
	
	public String respSelec()
	{
		if(tablaResponsables.getSelectedRow() < 0)
			return null;
		else
		{
			DefaultTableModel model = (DefaultTableModel)tablaResponsables.getModel();
			Vector v = (Vector) model.getDataVector().get(tablaResponsables.getSelectedRow());
			String nombre = v.get(0).toString();
			return nombre;
		}	
	}
}
