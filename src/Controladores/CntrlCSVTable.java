package Controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.*;

public class CntrlCSVTable implements ActionListener{

	InterfazCSVTable csv;
	public CntrlCSVTable(InterfazCSVTable csv) {
		// TODO Auto-generated constructor stub
		this.csv = csv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(InterfazCSVTable.IMPORTAR_CSV)) {
			InterfazCSVTable.generarTabla();
		}
		
	}

}
