package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
	public class Pedido extends Model {
		
		@Required
		public String cliente;
		@Required
		public String pacote;
		@Required
		public String status;
		@Required
		public String formaPag;
		@Required
		public String funcionario;
		@Required
		public String numPedido;
		@Required
		public String dataInscricao;
		@Required
		public String dataCadastro;
		
		public Pedido(String cliente, String pacote, String status, String formaPag, String funcionario, String numPedido, String dataInscricao, String dataCadastro) {
			this.cliente = cliente;
			this.pacote = pacote;
			this.status = status;
			this.formaPag = formaPag;
			this.funcionario = funcionario;
			this.numPedido = numPedido;
			this.dataInscricao = dataInscricao;
			this.dataCadastro = dataCadastro;
		} 

}

