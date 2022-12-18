package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Datos.BD;
import Interfaces.InterfazAsignarVigilante;

public class CntrlAsignarVigilante implements ActionListener {

	private InterfazAsignarVigilante i;
	
	public CntrlAsignarVigilante(InterfazAsignarVigilante intf) {
		// TODO Auto-generated constructor stub
		i = intf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("ASIGNAR_VIGILANTE"))
		{
			String vigilante = i.vigSelec();
			String[] claves = i.aulaSelec();
			int aula = Integer.parseInt(claves[0]);
			String franja = claves[1];
			
			if(aula < 0 || vigilante == null)
				JOptionPane.showMessageDialog(null,"Seleccione aula y responsable");
			else {
				if(!checkAsignadoFranja(vigilante, franja))
				{
					BD bd = new BD();
					
					bd.Insert("INSERT INTO Vigilan VALUES (" + aula + ", '" + vigilante +"', '" + franja + "');");
					i.recargar();
					JOptionPane.showMessageDialog(null,"Se ha asignado exitosamente a " + vigilante + " al aula " + aula + " en la " + franja);
					//System.out.println("Recargado");				
				} else
				{
					JOptionPane.showMessageDialog(null,"El individuo seleccionado ya tiene un aula asignada en esa franja");
				}
			}
		}
	}
	
	private boolean checkAsignadoFranja(String vigilante, String f) {
		// TODO Auto-generated method stub
		BD bd = new BD();
		for(Object[] franja : bd.Select("SELECT franja FROM Vigilan WHERE vigilante = '" + vigilante + "';"))
		{
			if(franja[0] != null && franja[0].toString().equals(f))
				return true;
			//System.out.println(franja[0].toString() + " != " + objetivo);
		}
		
		return false;
	}

}
