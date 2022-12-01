package Controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Interfaces.*;

public class CntrlHomePage implements ActionListener{
	
	InterfazHomePage interfaz;
	
	public CntrlHomePage (InterfazHomePage interfaz) {
		this.interfaz = interfaz;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(InterfazHomePage.IMPORTAR_CSV)) {
			InterfazCSVTable csv = new InterfazCSVTable("Importar CSV estudiantes");
			CntrlCSVTable ctrl = new CntrlCSVTable(csv);
			csv.controlador(ctrl);
			csv.setVisible(true);
		}else if(e.getActionCommand().equals(InterfazHomePage.IMPORTAR_SEDES)) {
			InterfazTablaSedes sed = new InterfazTablaSedes("Importar sedes");
			CntrlTablaSedes ctrl = new CntrlTablaSedes(sed);
			sed.controlador(ctrl);
			sed.setVisible(true);
		}else if(e.getActionCommand().equals(InterfazHomePage.ASIGNAR_RESP)){
			InterfazAsignarResponsable sed = new InterfazAsignarResponsable("Asignar Responsable");
			CntrlAsignarResponsable ctrl = new CntrlAsignarResponsable(sed);
			sed.controlador(ctrl);
			sed.setVisible(true);
			InterfazAsignarResponsable.mostrarSedes();
			InterfazAsignarResponsable.mostrarResp();
		}
		
	}

}
