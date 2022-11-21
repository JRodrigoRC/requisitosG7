import java.util.ArrayList;
import java.util.List;

public class Sede {
	private int id;
	private String nombre;

	public static List<Sede> listaSedes() {
		List<Sede> lista = new ArrayList<>();
		BD miBD = new BD();

		for (Object[] tupla : miBD.Select("SELECT id FROM Sede;")) {
			lista.add(new Sede((Integer) tupla[0]));
		}
		return lista;
	}

	public RespSede respSede() {
		try{
			BD miBD = new BD();
			Object[] tupla = miBD.Select(
					"SELECT nombre FROM RespSede where sede = " + this.id + ";")
					.get(0);
			return new RespSede((String) tupla[0]);
		}catch (Exception e ){
			//No tiene asignado
			return null;
		}
		
	}

	public Sede(int id) {
		try {
			BD miBD = new BD();
			Object[] tupla = miBD.Select(
					"SELECT * FROM Sede where id = " + id + ";").get(0);
			this.id = id;
			this.nombre = (String) tupla[1];
		} catch (Exception e) {
			// No existe
		}
	}
	
	public Sede(String nombre) {
		try {
			BD miBD = new BD();
			Object[] tupla = miBD.Select(
					"SELECT * FROM Sede where nombre = '" + nombre + "';").get(0);
			this.id = (int) tupla[0];
			this.nombre = (String) tupla[1];
		} catch (Exception e) {
			// No existe
		}
	}

	public Sede(int id, String nombre) {
		try{
			BD miBD = new BD();
			miBD.Insert("INSERT INTO Sede VALUES (" + id + ",'" + nombre
					+ "');");
			this.id = id;
			this.nombre = nombre;
		
		}catch (Exception e){
			
		}
			

	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(int value) {
		BD miBD = new BD();
    	miBD.Update("UPDATE Sede SET id = " + value 
    			+ " WHERE id ="+ this.id + ";");
    	
    	this.id = value;  
	}

	public void setNombre(String value) {
		BD miBD = new BD();
    	miBD.Update("UPDATE Sede SET nombre = '" + value 
    			+ "' WHERE id ="+ this.id + ";");
    	
    	this.nombre = value;  
	}
	

	public void BorrarSede()
	    {
	    	BD miBD = new BD();
	    	miBD.Delete("DELETE FROM Sede WHERE id ="+ this.id + ";");
			this.id = -1;
			this.nombre = null;
	    }

}
