package models;

import play.db.jpa.*;
import play.data.validation.*;
import play.libs.Codec;

import javax.persistence.*;

@Entity
public class Funcionario extends Model {
	
	@Required
	@MinSize(6)
	public String username;
		
	@Required
	public String pnome;
	
	@Required
	public String unome;
	
	@Required
	public String cpf;
	
	@Required
	public String telefone;
	
	@Required
	public String endereco;
	
	@Required
	@Email
	public String email;
	
	@Required
	@Password
	public String password;
	
	public String passwordHash;
	
	@Required
	public String rg;
	

	
	public Funcionario (String username, String pnome, String unome, String cpf, String telefone, String endereco, String email, String password, String rg) {
		this.username = username;
		this.pnome = pnome;
		this.unome = unome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		setPassword(password);
		this.rg = rg;
		
	}
	
	public String toString() {
		return "" + pnome + " " + cpf;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordHash = Codec.hexMD5(password);
	}

	public static boolean isValidLogin(String username, String password) {
		//true se bateu com uma combinação username/senha
		
		return (count("username=? AND PasswordHash=?", username,Codec.hexMD5(password)) == 1);
	}
}