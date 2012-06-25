package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import models.*;
import play.data.validation.Required;
import play.libs.F;
import play.mvc.Controller;

public class Funcionarios extends Controller {

	public static void index() {

		BancoDados.conectar();
		Connection con = BancoDados.con;

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			BancoDados.conectar();
			Connection con1 = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Funcionario> retorno = new ArrayList<Funcionario>();
			
			String query = "select * from funcionario ORDER BY pnome";
			pstm = (PreparedStatement) con1.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Funcionario func = new Funcionario(rs.getString("username"),
						rs.getString("pnome"), rs.getString("unome"),
						rs.getString("cpf"), rs.getString("telefone"),
						rs.getString("endereco"), rs.getString("email"),
						rs.getString("password"), rs.getString("rg"));
			
				retorno.add(func);
			
			}
			funcionarios = retorno;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		render(funcionarios);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String cpf) {
		Funcionario funcionario = new Funcionario();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Funcionario funcionario1 = new Funcionario();
			
			String query = "Select * from funcionario where cpf = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, cpf);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				funcionario1.username = rs.getString("username");
				funcionario1.pnome = rs.getString("pnome");
				funcionario1.unome = rs.getString("unome");
				funcionario1.email = rs.getString("email");
				funcionario1.password = rs.getString("password");
				funcionario1.rg = rs.getString("rg");
				funcionario1.telefone = rs.getString("telefone");
				funcionario1.cpf = rs.getString("cpf");
				funcionario1.endereco = rs.getString("endereco");
			
			}
			funcionario = funcionario1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(funcionario);
	}

	public static void editar(String cpf) {
		Funcionario funcionario = new Funcionario();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Funcionario funcionario1 = new Funcionario();
			
			String query = "Select * from funcionario where cpf = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, cpf);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				funcionario1.username = rs.getString("username");
				funcionario1.pnome = rs.getString("pnome");
				funcionario1.unome = rs.getString("unome");
				funcionario1.email = rs.getString("email");
				funcionario1.password = rs.getString("password");
				funcionario1.rg = rs.getString("rg");
				funcionario1.telefone = rs.getString("telefone");
				funcionario1.cpf = rs.getString("cpf");
				funcionario1.endereco = rs.getString("endereco");
			
			}
			funcionario = funcionario1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(funcionario);
	}

	public static void excluir(String cpf) {
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "DELETE FROM funcionario where cpf = ?";
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, cpf);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void cadastrar_funcionario(@Required String username,
			@Required String pnome, @Required String unome,
			@Required String cpf, @Required String telefone,
			@Required String endereco, @Required String email,
			@Required String password, @Required String rg) {

		Funcionario funcionario = new Funcionario(username, pnome, unome, cpf,
				telefone, endereco, email, password, rg);// não salvamos o
															// passqwordhash,
															// ele eh gerado
															// internamente
		if (validation.hasErrors()) {
			render("Clientes/inserir.html", funcionario);
		}

		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into funcionario ( username, pnome, unome, cpf, telefone, endereco, email, password, passwordHash, rg) values (?,?,?,?,?,?,?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			pstm.setString(1, funcionario.username);
			pstm.setString(2, funcionario.pnome);
			pstm.setString(3, funcionario.unome);
			pstm.setString(4, funcionario.cpf);
			pstm.setString(5, funcionario.telefone);
			pstm.setString(6, funcionario.endereco);
			pstm.setString(7, funcionario.email);
			pstm.setString(8, funcionario.password);
			pstm.setString(9, funcionario.passwordHash);
			pstm.setString(10, funcionario.rg);
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void editar_funcionario(String cpf) {
		validation.required(request.params.get("pnome"));
		validation.required(request.params.get("cpf"));

		Funcionario funcionario = null;
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Funcionario funcionario1 = new Funcionario();
			
			String query = "Select * from funcionario where cpf = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, cpf);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				funcionario1.username = rs.getString("username");
				funcionario1.pnome = rs.getString("pnome");
				funcionario1.unome = rs.getString("unome");
				funcionario1.email = rs.getString("email");
				funcionario1.password = rs.getString("password");
				funcionario1.rg = rs.getString("rg");
				funcionario1.telefone = rs.getString("telefone");
				funcionario1.cpf = rs.getString("cpf");
				funcionario1.endereco = rs.getString("endereco");
			
			}
			funcionario = funcionario1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (validation.hasErrors()) {
			render("Funcionario/editar.html", funcionario);
		}
		if (funcionario != null) {
			funcionario.username = request.params.get("username");
			funcionario.pnome = request.params.get("pnome");
			funcionario.unome = request.params.get("unome");
			funcionario.email = request.params.get("email");
			funcionario.password = request.params.get("password");
			funcionario.rg = request.params.get("rg");
			funcionario.telefone = request.params.get("telefone");
			funcionario.cpf = request.params.get("cpf");
			funcionario.endereco = request.params.get("endereco");

			try {
				BancoDados.conectar();
				Connection con1 = BancoDados.con;
				PreparedStatement pstm1;
				
				String query1 = "DELETE FROM funcionario where cpf = ?";
				pstm1 = (PreparedStatement) con1.prepareStatement(query1);
				pstm1.setString(1, cpf);
				pstm1.execute();
				BancoDados.conectar();
				Connection con = BancoDados.con;
				PreparedStatement pstm;
				
				String query = "insert into funcionario ( username, pnome, unome, cpf, telefone, endereco, email, password, passwordHash, rg) values (?,?,?,?,?,?,?,?,?,?)";
				pstm = (PreparedStatement) con.prepareStatement(query);
				
				pstm.setString(1, funcionario.username);
				pstm.setString(2, funcionario.pnome);
				pstm.setString(3, funcionario.unome);
				pstm.setString(4, funcionario.cpf);
				pstm.setString(5, funcionario.telefone);
				pstm.setString(6, funcionario.endereco);
				pstm.setString(7, funcionario.email);
				pstm.setString(8, funcionario.password);
				pstm.setString(9, funcionario.passwordHash);
				pstm.setString(10, funcionario.rg);
				
				pstm.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		index();
	}

	public static boolean isValidLogin(String username, String password) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;
		ResultSet rs;

		Funcionario funcionario = new Funcionario();

		String query = "Select * from funcionario where username = ? and password = ?";

		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, username);
		pstm.setString(2, password);

		rs = pstm.executeQuery();

		while (rs.next()) {

			funcionario.username = rs.getString("username");
			funcionario.pnome = rs.getString("pnome");
			funcionario.unome = rs.getString("unome");
			funcionario.email = rs.getString("email");
			funcionario.password = rs.getString("password");
			funcionario.rg = rs.getString("rg");
			funcionario.telefone = rs.getString("telefone");
			funcionario.cpf = rs.getString("cpf");
			funcionario.endereco = rs.getString("endereco");

		}

		return (rs != null);
	}

}
