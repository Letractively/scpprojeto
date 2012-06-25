package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;


public class Pacote {

	@Required
	public String nome;
	public int net;
	public int tv;
	public int telefone;

	public Pacote(String nome, int net, int tv, int telefone) {
		this.nome = nome;
		this.net = net;
		this.tv = tv;
		this.telefone = telefone;
	}
	
	public static Pacote encontrar_Pacote(String nome) {
		return null;//TODO
	}
	
	public static List<Pacote> getAllPacote() {
		List<Pacote> retorno = new ArrayList<Pacote>();
		return retorno;//TODO
	}
	
	public static void dellPacote(String nome) {
		//TODO
	}
	
	public void savePacote() {
		// TODO
	}

}
