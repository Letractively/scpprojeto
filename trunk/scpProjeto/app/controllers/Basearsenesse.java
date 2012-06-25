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

public class Basearsenesse extends Controller {

	public static void index() {

		BancoDados.conectar();
		Connection con = BancoDados.con;

		List<Funcionario> funcionarios = null;
		try {
			funcionarios = getAllFunc();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		render(funcionarios);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String cpf) {
		Funcionario funcionario = null;
		try {
			funcionario = obterFunc(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(funcionario);
	}

	public static void editar(String cpf) {
		Funcionario funcionario = null;
		try {
			funcionario = obterFunc(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(funcionario);
	}

	public static void excluir(String cpf) {
		try {
			apagarFunc(cpf);
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
			salvarFunc(funcionario);
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
			funcionario = obterFunc(cpf);
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
				apagarFunc(cpf);
				salvarFunc(funcionario);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		index();
	}

	public static List<Funcionario> getAllFunc() throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;

		PreparedStatement pstm;
		ResultSet rs;
		List<Funcionario> retorno = new ArrayList<Funcionario>();

		String query = "select * from funcionario ORDER BY (name)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			Funcionario func = new Funcionario(rs.getString("username"),
					rs.getString("pnome"), rs.getString("unome"),
					rs.getString("cpf"), rs.getString("telefone"),
					rs.getString("endereco"), rs.getString("email"),
					rs.getString("password"), rs.getString("rg"));

			retorno.add(func);

		}
		return retorno;
	}

	public static Funcionario obterFunc(String cpf) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;
		ResultSet rs;

		Funcionario funcionario = new Funcionario();

		String query = "Select * from produtoitem where id = (select max(id) from produtoitem)";

		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			funcionario.username = request.params.get("username");
			funcionario.pnome = request.params.get("pnome");
			funcionario.unome = request.params.get("unome");
			funcionario.email = request.params.get("email");
			funcionario.password = request.params.get("password");
			funcionario.rg = request.params.get("rg");
			funcionario.telefone = request.params.get("telefone");
			funcionario.cpf = request.params.get("cpf");
			funcionario.endereco = request.params.get("endereco");

		}

		return funcionario;

	}

	public static void apagarFunc(String cpf) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "DELETE FROM funcionario where cpf = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, cpf);
		pstm.execute();

	}

	public static void salvarFunc(Funcionario func) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "insert into funcionario ( username, pnome, unome, cpf, telefone, endereco, email, password, passwordHash, rg) values (?,?,?,?,?,?,?,?,?,?)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		pstm.setString(1, func.username);
		pstm.setString(2, func.pnome);
		pstm.setString(3, func.unome);
		pstm.setString(4, func.cpf);
		pstm.setString(5, func.telefone);
		pstm.setString(6, func.endereco);
		pstm.setString(7, func.email);
		pstm.setString(8, func.password);
		pstm.setString(9, func.passwordHash);
		pstm.setString(10, func.rg);

		pstm.execute();

	}

}
