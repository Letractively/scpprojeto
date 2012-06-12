package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Status extends Model {

	@Required
	public String nome;

	public Status(String nome) {
		this.nome = nome;
	}

}
