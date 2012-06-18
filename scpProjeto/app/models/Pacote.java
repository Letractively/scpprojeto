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
	public boolean net;
	public boolean tv;
	public boolean telefone;

	public Pacote(String nome, boolean net, boolean tv, boolean telefone) {
		this.nome = nome;
		this.net = net;
		this.tv = tv;
		this.telefone = telefone;
	}

}
