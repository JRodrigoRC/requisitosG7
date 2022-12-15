package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlAsignarResponsable;
import Controladores.CntrlGestAulas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class InterfazGestAulas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField aulaID;
	private static JTextField aforo;
	private static JTable table;
	private JButton insertButton, updateButton, borrarButton;
	static DefaultTableModel model;


	public static String INSERTAR_Aula = "INSERTAR_Aula";
	public static String MODIFICAR_Aula = "MODIFICAR_Aula";
	public static String BORRAR_Aula = "BORRAR_Aula";


	/**
	 * Create the frame.
	 */
	public InterfazGestAulas(String string) {
		setTitle(string);
		setBounds(100, 100, 608, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		insertButton = new JButton("Insertar");
		insertButton.setBounds(109, 279, 85, 21);
		contentPane.add(insertButton);
		
		updateButton = new JButton("Modificar");
		updateButton.setBounds(239, 279, 85, 21);
		contentPane.add(updateButton);
		
		borrarButton = new JButton("Borrar");
		borrarButton.setBounds(379, 279, 85, 21);
		contentPane.add(borrarButton);
		
		aulaID = new JTextField();
		aulaID.setBounds(79, 111, 96, 19);
		contentPane.add(aulaID);
		aulaID.setColumns(10);
		
		aforo = new JTextField();
		aforo.setBounds(79, 159, 96, 19);
		contentPane.add(aforo);
		aforo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 10, 405, 247);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Aula", "Aforo"
			}
		));

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 114, 41, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aforo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 162, 41, 13);
		contentPane.add(lblNewLabel_1);
	}
	
	public void controlador (CntrlGestAulas c) {
		insertButton.addActionListener(c);
		insertButton.setActionCommand(INSERTAR_Aula);
		
		updateButton.addActionListener(c);
		updateButton.setActionCommand(MODIFICAR_Aula);
		
		borrarButton.addActionListener(c);
		borrarButton.setActionCommand(BORRAR_Aula);

	}

	public static void insertarAula() {
		/*if (aulaID.getText().equals("") || aforo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Hay campos vacíos");
		} else {
			String[] data = { aulaID.getText(), aforo.getText() };
			model = (DefaultTableModel) table.getModel();
			model.addRow(data);
			JOptionPane.showMessageDialog(null, "Se ha añadido correctamente");
			aulaID.setText("");
			aforo.setText("");
		}*/
		
		JOptionPane.showMessageDialog(null, "Hay campos vacíos");

	}

	public static void borrarAula() {
		if (table.getSelectedRow() != -1) {
			Vector v = (Vector) model.getDataVector().get(table.getSelectedRow());
			model.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, "Aula eliminada");
		} else {
			JOptionPane.showMessageDialog(null, "No se puede eliminar");
		}
	}
}
