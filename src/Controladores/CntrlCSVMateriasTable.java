package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.InterfazCSVMateriasTable;

public class CntrlCSVMateriasTable implements ActionListener{
	
	InterfazCSVMateriasTable csv;
	public CntrlCSVMateriasTable(InterfazCSVMateriasTable csv) {
		// TODO Auto-generated constructor stub
		this.csv = csv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(InterfazCSVMateriasTable.IMPORTAR_CSV)) {
			InterfazCSVMateriasTable.generarTabla();
		}
		
	}

}
