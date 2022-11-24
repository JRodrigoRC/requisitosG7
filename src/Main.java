import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		HomePage mp = new HomePage("Pagina principal");
		mp.setVisible(true);
		HomePage.generarCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVTable frame = new CSVTable("Importar CSV", "datos-estudiantes-pevau.csv");
				frame.setVisible(true);
				CSVTable.importButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CSVTable.generarTabla();
					}
				});
			}
		});
		
		HomePage.importarSedesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaSedes frame = new TablaSedes("Importar Sedes");
				frame.setVisible(true);
				TablaSedes.importarSedesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TablaSedes.ImportarSedes();
					}
				});
				TablaSedes.removeButton.addActionListener(new ActionListener() {
			          public void actionPerformed(ActionEvent ae) {
			        	  TablaSedes.borrarSeleccionado();
			          }});
			}
		});
		
		HomePage.asignarRespButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			AsignacionResponsable frame = new AsignacionResponsable ("Asignar Responsable", "listado-de-posibles-responsables-de-sede");
			frame.setVisible(true);
			frame.CargarSedes();
			AsignacionResponsable.importButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ImportarUsuario usu = new ImportarUsuario("Importar Usuarios");
					usu.setVisible(true);
					usu.ImportarResp();
				}
			}
					);
			AsignacionResponsable.asignarResp.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(frame.table.getSelectedRow()!=-1){
						Vector v = (Vector) frame.model.getDataVector().get(frame.table.getSelectedRow());
						String nSede = (String)v.get(0);
						Asignar usu = new Asignar("Asignar Responsable");
						usu.setVisible(true);
						usu.mostrarUsuarios();
						Asignar.asignar.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								usu.asignar(nSede);
								frame.dispose();
								AsignacionResponsable frame = new AsignacionResponsable ("Asignar Responsable", "listado-de-posibles-responsables-de-sede");
								frame.setVisible(true);
								frame.CargarSedes();
								frame.importButton.setVisible(false);
								frame.asignarResp.setVisible(false);
							}
						});
						
					}
					
				
				}
			});
			
			}

			
		});
		
		mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
