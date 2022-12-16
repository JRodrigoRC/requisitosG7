package Interfaces;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlGestAulas;
import Datos.BD;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class InterfazGestAulas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTable table;
	static DefaultTableModel model;
	static JButton addButton, removeButton, updateButton;
	static JTextField textAula, textAforo;
	public static String INSERTAR_Aula = "INSERTAR_Aula";
	public static String MODIFICAR_Aula = "MODIFICAR_Aula";
	public static String BORRAR_Aula = "BORRAR_Aula";
	private static JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private static int id;

	public InterfazGestAulas(String title, int ID) {
		super(title);
		id = ID;
		table = new JTable();
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);
		initializeModel();

		JPanel buttonPanel = new JPanel();
		addButton = new JButton("Añadir Aula");
		buttonPanel.add(addButton);
		removeButton = new JButton("Eliminar Aula");
		buttonPanel.add(removeButton);
		updateButton = new JButton("Actualizar Aula");
		buttonPanel.add(updateButton);

		textAula = new JTextField();
		Dimension d = new Dimension(100, 50);
		textAula.setPreferredSize(d);
		textAforo = new JTextField();
		Dimension d1 = new Dimension(100, 50);
		textAforo.setPreferredSize(d1);

		String[] franjas = { "Primera franja de Primer día", "Segunda franja de Primer día", "Tercera franja de Primer día",
				"Primera franja de Segundo día", "Segunda franja de Segundo día", "Tercera franja de Segundo día",
				"Primera franja de Tercer día", "Segunda franja de Tercer día", "Tercera franja de Tercer día"};
		comboBox = new JComboBox(franjas);
		lblNewLabel = new JLabel("ID");
		lblNewLabel_1 = new JLabel("Aforo");
		lblNewLabel_2 = new JLabel("Franja");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 652, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textAforo, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(textAula, GroupLayout.DEFAULT_SIZE, 122,
														Short.MAX_VALUE)))
								.addGap(18).addComponent(scroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(39)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel).addComponent(textAula,
														GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textAforo, GroupLayout.PREFERRED_SIZE, 28,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_2))
										.addGap(182)))));
		getContentPane().setLayout(groupLayout);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					Vector v = (Vector) model.getDataVector().get(table.getSelectedRow());
					textAula.setText((String) v.get(0));
					textAforo.setText((String) v.get(1));
				}
			}
		});
		pack();
	}

	public void controlador(CntrlGestAulas c) {
		addButton.addActionListener(c);
		addButton.setActionCommand(INSERTAR_Aula);

		updateButton.addActionListener(c);
		updateButton.setActionCommand(MODIFICAR_Aula);

		removeButton.addActionListener(c);
		removeButton.setActionCommand(BORRAR_Aula);
	}

	public static void initializeModel() {
		String[] columnsName = { "Aula", "Aforo", "Franja" };
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnsName);
		BD miBD = new BD();
		for (Object[] tupla : miBD.Select("SELECT * FROM Aula WHERE sede = " + id)) {
			String id = tupla[0].toString();
			String aforo = tupla[1].toString();
			String tramo = (String) tupla[2];
			String[] row = { id, aforo, tramo };
			model.addRow(row);
		}
	}

	public static void addFila() {
		if (textAula.getText().equals("") || textAforo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Hay campos vacios");
		} else {
			String[] data = { textAula.getText(), textAforo.getText(), (String)comboBox.getSelectedItem() };
			model.addRow(data);
			BD miBD = new BD();
			miBD.Insert("INSERT INTO Aula VALUES(" + Integer.parseInt(data[0]) + "," + Integer.parseInt(data[1])
					+ ",'" + (String)comboBox.getSelectedItem() + "'," + id + ");");
			JOptionPane.showMessageDialog(null, "Se ha añadido correctamente");
			textAula.setText("");
			textAforo.setText("");
		}
	}

	public static void borrarSeleccionado() {
		if (table.getSelectedRow() != -1) {
			Vector v = (Vector) model.getDataVector().get(table.getSelectedRow());
			model.removeRow(table.getSelectedRow());
			BD miBD = new BD();
			miBD.Delete("DELETE FROM Aula where id = " + v.get(0) + ";");
			JOptionPane.showMessageDialog(null, "Aula eliminada");
			textAula.setText("");
			textAforo.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "No se puede eliminar");
		}
	}

	public static void updateFila() {
		if (table.getSelectedRow() != -1) {
			Vector v = (Vector) model.getDataVector().get(table.getSelectedRow());
			String[] data = { textAula.getText(), textAforo.getText(), (String)comboBox.getSelectedItem() };
			BD miBD = new BD();
			if(!v.get(0).equals(data[0]))
				miBD.Update("UPDATE Aula SET id = " + Integer.parseInt(data[0]) + " where id = " + v.get(0) +";");
			if(!v.get(1).equals(data[1]))
				miBD.Update("UPDATE Aula SET aforo = " + Integer.parseInt(data[1]) + " where id = " + v.get(0) + ";");
			if(!v.get(2).equals(data[2]))
				miBD.Update("UPDATE Aula SET franja = '" + (String)comboBox.getSelectedItem() + "' where id = " + v.get(0) + ";");
			model.removeRow(table.getSelectedRow());
			model.addRow(data);
			JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
			textAula.setText("");
			textAforo.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "No se puede actualizar");
		}
	}

}

