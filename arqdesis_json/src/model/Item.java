package model;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable{
	private Cerveja cerveja;
	private int quantidade;
	
	public Item(Cerveja cerveja, int quantidade) {
		super();
		this.cerveja = cerveja;
		this.quantidade = quantidade;
	}

	public Cerveja getCerveja() {
		return cerveja;
	}

	public void setCerveja(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (cerveja == null) {
			if (other.cerveja != null)
				return false;
		} else if (!cerveja.equals(other.cerveja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [cerveja=" + cerveja + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int compareTo(Item outro) {
		if(this.equals(outro)){
			return 0;
		} else {
			return this.getCerveja().getNome().compareTo(outro.getCerveja().getNome());
		}
	}
}
