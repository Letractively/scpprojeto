package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
	public class Pedido extends Model {
		
		public String cliente;
		public String pacote;
		public String status;
		public String formaPag;
		public String funcionario;
		
		public Pedido(String cliente, String pacote, String status, String formaPag, String funcionario) {
			this.cliente = cliente;
			this.pacote = pacote;
			this.status = status;
			this.formaPag = formaPag;
			this.funcionario = funcionario;
		} 

}

