package Controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Interfaces.*;
public class CntrlTablaSedes implements ActionListener {

	InterfazTablaSedes sed;

	public CntrlTablaSedes(InterfazTablaSedes sed) {
		this.sed = sed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(InterfazTablaSedes.IMPORTAR_SEDES)) {
			InterfazTablaSedes.ImportarSedes();
		} else if (e.getActionCommand().equals(InterfazTablaSedes.BORRAR)) {
			InterfazTablaSedes.borrarSeleccionado();
		}

	}
}
