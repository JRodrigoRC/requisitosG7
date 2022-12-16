package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interfaces.InterfazAsignarInst;
import Interfaces.InterfazGestAulas;
import Interfaces.InterfazGestionSedes;


public class CntrlGestionSedes implements ActionListener{
	
	InterfazGestionSedes frame;

	public CntrlGestionSedes(InterfazGestionSedes sed) {
		// TODO Auto-generated constructor stub
		frame = sed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(InterfazGestionSedes.ASIGNAR_INST)) {
			InterfazAsignarInst inst = new InterfazAsignarInst();
			CntrlAsignarInst ctrl = new CntrlAsignarInst(inst);
			inst.controlador(ctrl);
			inst.setVisible(true);
			InterfazAsignarInst.mostrarSedes();
			InterfazAsignarInst.mostrarInstitutos();
		}else if(e.getActionCommand().equals(InterfazGestionSedes.CRUD_AULAS)) {
			if(frame.seleccionado()) {
				InterfazGestAulas aulas = new InterfazGestAulas("Gestión de aulas", frame.getID());
				CntrlGestAulas ctrl = new CntrlGestAulas(aulas);
				aulas.controlador(ctrl);
				aulas.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null,"Seleccione una sede");
			}
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.IMPORTAR_MATERIAS)){
			//Aquí RF4
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.CARGA_USUARIOS)) {
			//Aquí RF6
			

		}else if(e.getActionCommand().equals(InterfazGestionSedes.ASIGNAR_RESP)) {
			//Aquí RF7
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.ASIGNAR_VIGILANTE)) {
			//Aquí RF8
			
		}
		
	}

}
