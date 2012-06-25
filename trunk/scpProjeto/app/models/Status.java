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
	
	
	
}
