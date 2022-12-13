package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			//Aquí RF2.3
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.CRUD_AULAS)) {
			//Aquí RF3
			
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
