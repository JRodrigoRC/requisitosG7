
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BD miBD = new BD();
		int a = 4;
		int b = 5;
		Object [] tupla = miBD.Select("Select * from prueba").get(0);
		System.out.println((int)tupla[0]);
	}

}
