package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interfaces.InterfazAsignarInst;
import Interfaces.InterfazAsignarResponsable;
import Interfaces.InterfazAsignarResponsableAula;
import Interfaces.InterfazAsignarVigilante;
import Interfaces.InterfazCSVMateriasTable;
import Interfaces.InterfazCSVTable;
import Interfaces.InterfazGestAulas;
import Interfaces.InterfazGestionSedes;
import Interfaces.InterfazImportarResp;
import Interfaces.InterfazImportarRespAulaVigilante;


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
			InterfazCSVMateriasTable csv = new InterfazCSVMateriasTable("Importar CSV materias");
			CntrlCSVMateriasTable ctrl = new CntrlCSVMateriasTable(csv);
			csv.controlador(ctrl);
			csv.setVisible(true);
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.CARGA_USUARIOS)) {
			InterfazImportarRespAulaVigilante csv = new InterfazImportarRespAulaVigilante("Importar responsables de Aula o Vigilantes");
				csv.setVisible(true);
				InterfazImportarRespAulaVigilante.importarRespAula();
				InterfazImportarRespAulaVigilante.cargarRespAula();
			
		}else if(e.getActionCommand().equals(InterfazGestionSedes.ASIGNAR_RESP)) {
			if(frame.seleccionado()) {
				InterfazAsignarResponsableAula intf = new InterfazAsignarResponsableAula("Asignar responsable de aula", frame);
				CntrlAsignarResponsableAula cntrl = new CntrlAsignarResponsableAula(intf);
				intf.controlador(cntrl);
				intf.setVisible(true);				
			} else {
				JOptionPane.showMessageDialog(null,"Seleccione una sede");
			}
		}else if(e.getActionCommand().equals(InterfazGestionSedes.ASIGNAR_VIGILANTE)) {
			if(frame.seleccionado()) {
				InterfazAsignarVigilante intf = new InterfazAsignarVigilante("Asignar vigilante", frame);
				CntrlAsignarVigilante cntrl = new CntrlAsignarVigilante(intf);
				intf.controlador(cntrl);
				intf.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null,"Seleccione una sede");
			}
		}
		
	}

}
