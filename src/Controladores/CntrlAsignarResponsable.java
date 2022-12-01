package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.*;

public class CntrlAsignarResponsable implements ActionListener {
	InterfazAsignarResponsable sed;
	
	public CntrlAsignarResponsable(InterfazAsignarResponsable sed) {
		this.sed = sed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(InterfazAsignarResponsable.IMPORTAR_Resp)) {
			InterfazImportarResp csv = new InterfazImportarResp("Importar responsables");
			csv.setVisible(true);
			InterfazImportarResp.importarResp();
			InterfazImportarResp.cargarResp();
			sed.clearResp();
			sed.mostrarResp();
		}else if(e.getActionCommand().equals(InterfazAsignarResponsable.ASIGNAR_RESP)) {
			InterfazAsignarResponsable.asignar();
			InterfazAsignarResponsable.clearResp();
			InterfazAsignarResponsable.clearSedes();
			InterfazAsignarResponsable.mostrarResp();
			InterfazAsignarResponsable.mostrarSedes();
		}
		
	}

}
