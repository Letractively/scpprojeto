package models;

import java.util.ArrayList;
import java.util.List;

import play.db.jpa.*;
import play.data.validation.*;
import play.libs.Codec;

import javax.persistence.*;


public class Funcionario {
	
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
	
	public Funcionario () {
		super();
	}
	
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

	public static boolean isValidLogin(String username, String password) {//NAO VAI EXISTIR DEPOIS
		//true se bateu com uma combinação username/senha
		
		return false;
		//return (count("username=? AND PasswordHash=?", username,Codec.hexMD5(password)) == 1);
	}

	public boolean isvalid() {
		return (username != null && pnome != null && unome != null && cpf != null && telefone != null && endereco != null && email != null && rg != null);
	}
	
	public static Funcionario encontrar_Funcionario(String cpf) {
		return null;//TODO
	}
	
	public static List<Funcionario> getAllFuncionario() {
		List<Funcionario> retorno = new ArrayList<Funcionario>();
		return retorno;//TODO
	}
	
	public static void dellFuncionario(String cpf) {
		//TODO
	}
	
	public void saveFuncionario() {
		// TODO
	}
}