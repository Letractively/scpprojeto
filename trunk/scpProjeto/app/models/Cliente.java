package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
public class Cliente extends Model {
		
	@Required
	public String pnome;
	
	@Required
	public String unome;
	
	@Required
	public String cpf;
	
	@Required
	public String telefone;
	
	@Required
	@MaxSize(1000)
	public String endereco;
	
	public Cliente (String pnome, String unome, String cpf, String telefone, String endereco) {
		this.pnome = pnome;
		this.unome = unome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public String toString() {
		return "" + pnome + " " + cpf;
	}
}