package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlAsignarVigilante;
import Datos.BD;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class InterfazAsignarVigilante extends JFrame{

	private InterfazGestionSedes intGestion;
	private JTable tAulas;
	private JTable tVigilantes;
	private JButton btnAsignar;
	
	/**
	 * Create the application.
	 * @param frame 
	 */
	public InterfazAsignarVigilante(String title, InterfazGestionSedes frame) {
		intGestion = frame;
		
		setTitle(title);
		initialize();
		mostrarAulas();
		mostrarVig();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 1200, 300);

		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 914, 194);
		getContentPane().add(scrollPane);
		
		tAulas = new JTable();
		scrollPane.setViewportView(tAulas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(934, 56, 240, 194);
		getContentPane().add(scrollPane_1);
		
		tVigilantes = new JTable();
		scrollPane_1.setViewportView(tVigilantes);
		
		btnAsignar = new JButton("Asignar vigilante");
		btnAsignar.setBounds(288, 11, 117, 34);
		getContentPane().add(btnAsignar);
	}

	public void controlador(CntrlAsignarVigilante cntrl) {
		// TODO Auto-generated method stub
		btnAsignar.addActionListener(cntrl);
		btnAsignar.setActionCommand("ASIGNAR_VIGILANTE");
	}

	private void mostrarAulas() {
		int IDSede = intGestion.getID();
		String[] colName = {"AULA" , "FRANJA", "VIGILANTES"};
		DefaultTableModel model = (DefaultTableModel)tAulas.getModel();
		model.setColumnIdentifiers(colName);
		BD miBD = new BD();
		int i = 0;
		
		for (Object[] tupla : miBD.Select("SELECT id, franja FROM Aula WHERE sede = " + IDSede + ";")) {
			String id =  tupla[0].toString();
			String franja = tupla[1].toString();
			String vigilantes = "";
			List<Object[]> l = miBD.Select("SELECT vigilante FROM Vigilan WHERE aula = " + Integer.parseInt(id) + " AND franja = '" + franja + "';");
			for(Object[] v : l)
			{
				vigilantes += v[0].toString();
				i++;
				if(i < l.size())
					vigilantes += ", ";
			}
			if(i == 0)
				vigilantes = "Sin vigilantes";
			String[] row = {id, franja, vigilantes};
			model.addRow(row);
		}
		tAulas.setModel(model);
	}
	
	private void mostrarVig()
	{
		String[] colName = {"NOMBRE"};
		DefaultTableModel model = (DefaultTableModel)tVigilantes.getModel();
		model.setColumnIdentifiers(colName);
		BD bd = new BD();
		
		for(Object[] o : bd.Select("SELECT nombre FROM Vigilante"))
		{
			String nombre = o[0].toString();
			String[] row = {nombre};
			model.addRow(row);
		}
		tVigilantes.setModel(model);
	}
	
	public void recargar()
	{
		DefaultTableModel modelAula = (DefaultTableModel)tAulas.getModel();
		tVigilantes.clearSelection();
		modelAula.setRowCount(0);
		mostrarAulas();
	}
	
	public String[] aulaSelec()
	{
		String[] keys = new String[2];
		
		if(tAulas.getSelectedRow() < 0)
		{
			keys[0] = "-1";
			keys[1] = "null";
			return keys;
		}
		else
		{
			DefaultTableModel model = (DefaultTableModel)tAulas.getModel();
			Vector v = (Vector) model.getDataVector().get(tAulas.getSelectedRow());
			keys[0] = v.get(0).toString();
			keys[1] = v.get(1).toString();
			return keys;
		}
	}
	
	public String vigSelec()
	{
		if(tVigilantes.getSelectedRow() < 0)
			return null;
		else
		{
			DefaultTableModel model = (DefaultTableModel)tVigilantes.getModel();
			Vector v = (Vector) model.getDataVector().get(tVigilantes.getSelectedRow());
			String nombre = v.get(0).toString();
			return nombre;
		}	
	}
}
