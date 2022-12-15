package Interfaces;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladores.CntrlAsignarInst;

import Datos.BD;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.JButton;

public class InterfazAsignarInst extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tableSedes;
	private static JTable tableInst;
	private JButton asignarButton;

	public static String ASIGNAR_INST = "ASIGNAR_INST";

	/**
	 * Create the frame.
	 */
	public InterfazAsignarInst() {
		setTitle("Asignar Instituto");

		setBounds(100, 100, 702, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 253, 270);
		contentPane.add(scrollPane);

		tableSedes = new JTable();
		scrollPane.setViewportView(tableSedes);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(310, 86, 368, 271);
		contentPane.add(scrollPane_1);

		tableInst = new JTable();
		scrollPane_1.setViewportView(tableInst);

		asignarButton = new JButton("Asignar");
		asignarButton.setBounds(239, 36, 85, 21);
		contentPane.add(asignarButton);
	}

	public void controlador(CntrlAsignarInst c) {
		asignarButton.addActionListener(c);
		asignarButton.setActionCommand(ASIGNAR_INST);
	}

	public static void mostrarSedes() {
		String[] columnsName = { "SEDE", "AFORO DISPONIBLE" };
		DefaultTableModel model = (DefaultTableModel) tableSedes.getModel();
		model.setColumnIdentifiers(columnsName);
		BD miBD = new BD();
		for (Object[] tupla : miBD.Select("select * from Sede")) {
			String nombre = (String) tupla[1];
			Object[] aux = miBD.Select("select SUM(aforo) from Aula where sede = " + (int) tupla[0] + "; ").get(0);
			int aSedes;
			if (aux[0] == null) {
				aSedes = 0;
			} else {
				BigDecimal x = (BigDecimal) aux[0];
				aSedes = x.intValue();
			}
			aux = miBD.Select("select SUM(aforo) from Instituto where sede = " + (int) tupla[0] + ";").get(0);
			int aInst;
			if (aux[0] == null) {
				aInst = 0;
			} else {
				BigDecimal x = (BigDecimal) aux[0];
				aInst = x.intValue();

			}
			String aforo = Integer.toString(aSedes - aInst);
			String[] row = { nombre, aforo };
			model.addRow(row);
		}
	}

	public static void mostrarInstitutos() {
		String[] columnsName = { "INSTITUTO", "AFORO", "SEDE" };
		DefaultTableModel model = (DefaultTableModel) tableInst.getModel();
		model.setColumnIdentifiers(columnsName);
		BD miBD = new BD();
		for (Object[] tupla : miBD.Select(
				"select i.nombre, i.aforo, ifnull(s.nombre,'Sede no asignada') from Instituto i left join Sede s on (i.sede=s.id)")) {
			String nombre = (String) tupla[0];
			String aforo = tupla[1].toString();
			String sede = (String) tupla[2];
			String[] row = { nombre, aforo, sede };
			model.addRow(row);
		}

	}

	public static void asignar() {
		// TODO Auto-generated method stub
		if (tableSedes.getSelectedRow() != -1 && tableInst.getSelectedRow() != -1) {
			DefaultTableModel modelSede = (DefaultTableModel) tableSedes.getModel();
			DefaultTableModel modelInst = (DefaultTableModel) tableInst.getModel();
			Vector i = (Vector) modelInst.getDataVector().get(tableInst.getSelectedRow());
			String nInst = (String) i.get(0);
			Vector s = (Vector) modelSede.getDataVector().get(tableSedes.getSelectedRow());
			String nSede = (String) s.get(0);
			if(Integer.parseInt((String)s.get(1))>=Integer.parseInt((String)i.get(1))) {
				BD miBD = new BD();
				Object[] aux = miBD.Select("select sede from Instituto where nombre ='" + nInst + "';").get(0);
				if(aux[0]!=null) {
					JOptionPane.showMessageDialog(null,"El instituto " + nInst + " ya tiene sede asignada");
				}else {
					miBD.Update("update Instituto set sede = (select id from Sede where nombre = '" + nSede
							+ "') where nombre = '" + nInst + "';");
					JOptionPane.showMessageDialog(null, "El instituto " + nInst + " ha sido asignado a la sede " + nSede);
				}
			}else {
				JOptionPane.showMessageDialog(null,"La sede no tiene suficiente aforo");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione la sede y el instituto");
		}

	}

	public static void clearInst() {
		DefaultTableModel modelInst = (DefaultTableModel) tableInst.getModel();
		modelInst.setRowCount(0);
	}

	public static void clearSedes() {
		DefaultTableModel modelSed = (DefaultTableModel) tableSedes.getModel();
		modelSed.setRowCount(0);

	}
}
