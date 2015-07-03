package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Cerveja;

public class CervejaTO implements Serializable {
	private ArrayList<Cerveja> lista;
	
	public CervejaTO(){
		lista = new ArrayList<Cerveja>();
	}
	
	public void add(Cerveja cerveja){
		lista.add(cerveja);
	}
	
	public boolean remove(Cerveja cerveja){
		return(lista.remove(cerveja));
	}
	
	public ArrayList<Cerveja> getLista(){
		return lista;
	}
}
