package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;

public class Status {

	@Required
	public String nome;
	
	public Status() {
		super();
	}

	public Status(String nome) {
		this.nome = nome;
	}
	
	public static Status encontrar_Status(String nome) {
		return null;//TODO
	}
	
	public static List<Status> getAllStatus() {
		List<Status> retorno = new ArrayList<Status>();
		return retorno;//TODO
	}
	
	public static void dellStatus(String nome) {
		//TODO
	}
	
	public void saveStatus() {
		// TODO
	}

}
