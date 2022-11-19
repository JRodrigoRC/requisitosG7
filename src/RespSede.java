import java.util.ArrayList;
import java.util.List;


public class RespSede {
	private String nombre;
	private Sede sede;
	
	public static List<RespSede> listaRespSedes() {
		List<RespSede> lista = new ArrayList<>();
		BD miBD = new BD();

		for (Object[] tupla : miBD.Select("SELECT nombre FROM RespSede;")) {
			lista.add(new RespSede((String) tupla[0]));
		}
		return lista;
	}

	public RespSede(String nombre) {
		try{
			BD miBD = new BD();
			Object[] tupla = miBD.Select(
					"SELECT nombre FROM RespSede where nombre = '" + nombre + "';")
					.get(0);
			this.nombre = nombre;
			this.sede = new Sede((int)tupla[1]);
		}catch (Exception e){
			//No existe
		}
		
	}
	
	

	public RespSede(String nombre, Sede sede) {
		try {
			BD miBD = new BD();
			if (sede == null) {
				miBD.Insert("INSERT INTO RespSede VALUES ('" + nombre + "'," + null
						+ ");");
			}else {
				miBD.Insert("INSERT INTO RespSede VALUES ('" + nombre + "'," + sede.getId()
						+ ");");
			}
			
			this.nombre = nombre;
			this.sede = sede;
		} catch (Exception ex){
			// Ya estaba insertada
		}

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String value) {
		BD miBD = new BD();
    	miBD.Update("UPDATE RespSede SET nombre = '" + value 
    			+ "' WHERE nombre ='"+ this.nombre + "';");
    	this.nombre = value;  
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede value) {
		BD miBD = new BD();
		if(value!=null){
			miBD.Update("Update RespSede SET sede =  " + value.getId() 
	    			+ " WHERE nombre = '"+ this.nombre + "';");
	    	this.sede = value; 
		}else{
			miBD.Update("Update RespSede SET sede =  " + null
	    			+ " WHERE nombre = '"+ this.nombre + "';");
	    	this.sede = value; 
		}
    	 
	}
	
	public void BorrarRespSede()
    {
    	BD miBD = new BD();
    	miBD.Delete("DELETE FROM RespSede WHERE nombre='"+ this.nombre + "';");    	
		this.nombre = null;
		this.sede = null;
    }


}
