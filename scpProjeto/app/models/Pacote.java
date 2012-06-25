package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;


public class Pacote {

	@Required
	public String nome;
	public int internet;
	public int tv;
	public int telefone;
	
	public Pacote() {
		super();
	}

	public Pacote(String nome, int internet, int tv, int telefone) {
		this.nome = nome;
		this.internet = internet;
		this.tv = tv;
		this.telefone = telefone;
	}
	
	

}
