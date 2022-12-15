package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.InterfazAsignarInst;


public class CntrlAsignarInst implements ActionListener {

	private InterfazAsignarInst frame; 
	public CntrlAsignarInst(InterfazAsignarInst inst) {
		// TODO Auto-generated constructor stub
		frame = inst;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(InterfazAsignarInst.ASIGNAR_INST)) {
			InterfazAsignarInst.asignar();
			InterfazAsignarInst.clearInst();
			InterfazAsignarInst.clearSedes();
			InterfazAsignarInst.mostrarInstitutos();
			InterfazAsignarInst.mostrarSedes();
		}
		
	}

}
