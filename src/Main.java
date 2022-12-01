import javax.swing.JFrame;

import Controladores.*;
import Interfaces.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InterfazHomePage inicio = new InterfazHomePage("HomePage");
				CntrlHomePage cntrl = new CntrlHomePage(inicio);
				inicio.controlador(cntrl);
				inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				inicio.setVisible(true);
			}
		});

	}

}
