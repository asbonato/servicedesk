package util;

import java.util.TreeSet;

import model.Cerveja;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSonFacade {

	public static String listToJSon(TreeSet<Cerveja> lista) {

		JSONArray vetor = new JSONArray();
		for (Cerveja cerveja : lista) {
			JSONObject object = new JSONObject();
			try {
				object.put("nome", cerveja.getNome());
				object.put("imagem", cerveja.getImagem());
				object.put("preco", cerveja.getPreco());
				object.put("estilo", cerveja.getEstilo());
				object.put("cor", cerveja.getCor());
				object.put("pais", cerveja.getPais());
				vetor.put(object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return vetor.toString();
	}

}
