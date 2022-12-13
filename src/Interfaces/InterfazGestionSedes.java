package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlGestionSedes;
import Datos.BD;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class InterfazGestionSedes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton bAsignarInst,bCRUD,bImpMaterias,bCargaUsuarios,bAsignarResponsableAula,bAsignarVigilante;

	public static String ASIGNAR_INST = "ASIGNAR_INST";
	public static String CRUD_AULAS = "CRUD_AULAS";
	public static String IMPORTAR_MATERIAS = "IMPORTAR_MATERIAS";
	public static String CARGA_USUARIOS = "CARGA_USUARIOS";
	public static String ASIGNAR_RESP = "ASIGNAR_RESP";
	public static String ASIGNAR_VIGILANTE = "ASIGNAR_VIGILANTE";
	private static JTable table;

	/**
	 * Create the frame.
	 */
	public InterfazGestionSedes() {
		setTitle("Gestionar Sedes");
		setBounds(100, 100, 590, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bAsignarInst = new JButton("Asignar Institutos");
		bAsignarInst.setBounds(45, 20, 141, 32);
		contentPane.add(bAsignarInst);
		
		bCRUD = new JButton("CRUD Aulas");
		bCRUD.setBounds(45, 72, 114, 32);
		contentPane.add(bCRUD);
		
		bImpMaterias = new JButton("Importar materias/examen");
		bImpMaterias.setBounds(322, 20, 191, 32);
		contentPane.add(bImpMaterias);
		
		bCargaUsuarios = new JButton("Carga de usuarios");
		bCargaUsuarios.setBounds(360, 72, 153, 32);
		contentPane.add(bCargaUsuarios);
		
		bAsignarResponsableAula = new JButton("Asignar Responsable de aula");
		bAsignarResponsableAula.setBounds(285, 126, 228, 32);
		contentPane.add(bAsignarResponsableAula);
		
		bAsignarVigilante = new JButton("Asignar Vigilante");
		bAsignarVigilante.setBounds(45, 126, 168, 32);
		contentPane.add(bAsignarVigilante);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 226, 556, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}



	public void controlador(CntrlGestionSedes ctrl) {
		bAsignarInst.addActionListener(ctrl);
		bAsignarInst.setActionCommand(ASIGNAR_INST);
		
		bCRUD.addActionListener(ctrl);
		bCRUD.setActionCommand(CRUD_AULAS);
		
		bImpMaterias.addActionListener(ctrl);
		bImpMaterias.setActionCommand(IMPORTAR_MATERIAS);
		
		bCargaUsuarios.addActionListener(ctrl);
		bCargaUsuarios.setActionCommand(CARGA_USUARIOS);
		
		bAsignarResponsableAula.addActionListener(ctrl);
		bAsignarResponsableAula.setActionCommand(ASIGNAR_RESP);
		
		bAsignarVigilante.addActionListener(ctrl);
		bAsignarVigilante.setActionCommand(ASIGNAR_VIGILANTE);
	}



	public static void mostrarSedes() {
		String[] columnsName = {"Sedes"};
		DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnsName);
        BD miBD = new BD();
        for (Object[] tupla : miBD.Select("SELECT * FROM Sede;")) {
        	String nombre = (String) tupla[1];
        	String[] row = {nombre};
        	model.addRow(row);
		}
		
	}
}
