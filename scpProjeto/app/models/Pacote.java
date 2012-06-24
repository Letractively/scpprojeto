package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;

@Entity
public class Pacote extends Model {

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

}
