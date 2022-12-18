package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Datos.BD;
import Interfaces.InterfazAsignarResponsableAula;

public class CntrlAsignarResponsableAula implements ActionListener{

	private InterfazAsignarResponsableAula i;
	
	public CntrlAsignarResponsableAula(InterfazAsignarResponsableAula intf) {
		i = intf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("ASIGNAR RESPONSABLE"))
		{
			String responsable = i.respSelec();
			String[] claves = i.aulaSelec();
			int aula = Integer.parseInt(claves[0]);
			String franja = claves[1];
			
			if(aula < 0 || responsable == null)
				JOptionPane.showMessageDialog(null,"Seleccione aula y responsable");
			else {
				if(!checkAsignadoFranja(responsable, franja))
				{
					BD bd = new BD();
					
					bd.Update("UPDATE Aula SET responsable_aula = '" + responsable + "' WHERE id = " + aula + " AND franja = '" + franja + "';");
					i.recargar();
					JOptionPane.showMessageDialog(null,"Se ha asignado exitosamente a " + responsable + " al aula " + aula + " en la " + franja);
					//System.out.println("Recargado");				
				} else
				{
					JOptionPane.showMessageDialog(null,"El individuo seleccionado ya tiene un aula asignada en esa franja");
				}
			}
			
		}
	}

	private boolean checkAsignadoFranja(String responsable, String f) {
		// TODO Auto-generated method stub
		BD bd = new BD();
		boolean asignado = false;
		
		for(Object[] franja : bd.Select("SELECT franja FROM Vigilan WHERE vigilante = '" + responsable + "';"))
		{
			if(franja[0] != null && franja[0].toString().equals(f))
				asignado = true;
			//System.out.println(franja[0].toString() + " != " + objetivo);
		}
		for(Object[] franja : bd.Select("SELECT franja FROM Aula WHERE responsable_aula = '" + responsable + "';"))
		{
			if(franja[0] != null && franja[0].toString().equals(f))
				asignado = true;
			//System.out.println(franja[0].toString() + " != " + objetivo);
		}
		return asignado;
	}
}
