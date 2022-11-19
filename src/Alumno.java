import java.util.ArrayList;
import java.util.List;

public class Alumno {
	private String DNI;
	private String nombre;
	private String apellido;
	private String instituto;

	public static List<Alumno> listaAlumnos() {
		List<Alumno> lista = new ArrayList<>();
		BD miBD = new BD();

		for (Object[] tupla : miBD.Select("SELECT DNI FROM Alumno;")) {
			lista.add(new Alumno((String) tupla[0]));
		}
		return lista;
	}

	public Alumno(String DNI) {

		try {
			BD miBD = new BD();
			Object[] tupla = miBD.Select(
					"SELECT * FROM Alumno where DNI = '" + DNI + "';").get(0);
			this.DNI = DNI;
			this.nombre = (String) tupla[1];
			this.apellido = (String) tupla[2];
			this.instituto = (String) tupla[4];
		} catch (Exception e) {
			// No existe
		}

	}

	public Alumno(String DNI, String nombre, String apellido, String instituto) {
		try {
			BD miBD = new BD();
			miBD.Insert("INSERT INTO Alumno VALUES ('"+ DNI + "','" + nombre + "','" +apellido + "','" + instituto + "');"); 
			this.DNI = DNI;
			this.nombre = nombre;
			this.apellido = apellido;
			this.instituto = instituto;
		} catch (Exception e) {
			// Ya estaba insertada
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String value) {
		BD miBD = new BD();
		miBD.Update("UPDATE Alumno SET nombre = '" + value + "' WHERE DNI ='"
				+ this.DNI + "';");

		this.nombre = value;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String value) {
		BD miBD = new BD();
		miBD.Update("UPDATE Alumno SET apellido = '" + value + "' WHERE DNI ='"
				+ this.DNI + "';");

		this.apellido = value;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String value) {
		BD miBD = new BD();
		miBD.Update("UPDATE Alumno SET instituto = '" + value + "' WHERE DNI ='"
				+ this.DNI + "';");

		this.instituto = value;
	}

	public String getDNI() {
		return DNI;
	}

	public void BorrarAlumno() {
		BD miBD = new BD();
		miBD.Delete("DELETE FROM Alumno WHERE DNI = '" + this.DNI + "';");
		this.DNI = null;
		this.nombre = null;
		this.apellido = null;
		this.instituto = null;
	}

}
