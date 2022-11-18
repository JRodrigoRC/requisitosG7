
import java.sql.*;
import java.util.*;

public class BD 
{
	
	private Connection con ;	
	
	public BD()
	{
		try
		{
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String DB_URL = "jdbc:mysql://database-pevau.cobadwnzalab.eu-central-1.rds.amazonaws.com";
			String DB_SCHEMA = "grupo07DB";
			String USER = "grupo07";
			String PASS = "FjLWM6DNk6TJDzfV";
			con = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA,USER,PASS);
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error al Conectar con la base de datos." + ex.getMessage());
		}
	}
	
	
	protected void finalize () 
	{
		try
		{
			if (con!=null)  con.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error al Cerrar la Conexión." + ex.getMessage());
		}
    }
	
	public Object SelectEscalar(String sel)
	{
		ResultSet rset;
		Object res = null;
		try
		{
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(sel);
			rset.next();
			res = rset.getObject(1);
			rset.close();
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error en el SELECT: " + sel + ". " + ex.getMessage());
		}		
		
		return res;
	}
	
	public List<Object[]> Select(String sel)
	{
		ResultSet rset;
		List<Object[]> lista = new ArrayList<Object[]>();
		try
		{
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(sel);
			ResultSetMetaData meta = rset.getMetaData();
			int numCol = meta.getColumnCount();
			while (rset.next())
			{
				Object[] tupla = new Object[numCol];
				for(int i=0; i<numCol;++i)
				{
					tupla[i] = rset.getObject(i+1);
				}
				lista.add(tupla);
			}
			rset.close();
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error en el SELECT: " + sel+ ". " + ex.getMessage());
		}		
		
		return lista;
	}
	
	public void Insert(String ins)
	{
		try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(ins);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error en el INSERT: " + ins+ ". " + ex.getMessage());
		}
	}

	public void Delete(String del)
	{
		try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(del);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error en el DELETE: " + del+ ". " + ex.getMessage());
		}
	}

	public void Update(String up)
	{
		try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(up);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new BD_Error("Error en el UPDATE: " + up+ ". " + ex.getMessage());
		}
	}

}
