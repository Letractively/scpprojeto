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

	public Pacote(String nome) {
		this.nome = nome;
	}

}
