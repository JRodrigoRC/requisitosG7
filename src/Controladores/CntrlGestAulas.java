package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interfaces.InterfazAsignarResponsable;
import Interfaces.InterfazGestAulas;
import Interfaces.InterfazGestionSedes;
import Interfaces.InterfazImportarResp;

public class CntrlGestAulas implements ActionListener {
	InterfazGestAulas frame;

	public CntrlGestAulas(InterfazGestAulas aul) {
		frame = aul;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(InterfazGestAulas.INSERTAR_Aula)) {
			frame.insertarAula();
		} else if (e.getActionCommand().equals(InterfazGestAulas.MODIFICAR_Aula)) {
			
		} else if(e.getActionCommand().equals(InterfazGestAulas.BORRAR_Aula)) {
			InterfazGestAulas.borrarAula();
		}
	}
}