package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;

import models.*;

public class Clientes extends Controller {

	public static void index() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = getAllCliente();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		render(clientes);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String cpf) {
		Cliente cliente = new Cliente();
		try {
			cliente = encontrar_Cliente(cpf);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		render(cliente);
	}

	public static void editar(String cpf) {
		Cliente cliente = new Cliente();
		try {
			cliente = encontrar_Cliente(cpf);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		render(cliente);
	}

	public static void excluir(String cpf) {

		try {
			dellCliente(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void cadastrar_cliente(@Required String pnome,
			@Required String unome, @Required String cpf,
			@Required String telefone, @Required String endereco) {
		Cliente cliente = new Cliente(pnome, unome, cpf, telefone, endereco);
		if (validation.hasErrors()) {
			render("Clientes/inserir.html", cliente);
		}

		try {
			saveCliente(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void editar_cliente(String cpf) {
		validation.required(request.params.get("pnome"));
		validation.required(request.params.get("cpf"));

		Cliente cliente = new Cliente();
		try {
			cliente = encontrar_Cliente(cpf);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (validation.hasErrors()) {
			render("Clientes/editar.html", cliente);
		}

		cliente.pnome = request.params.get("pnome");
		cliente.unome = request.params.get("unome");
		cliente.telefone = request.params.get("telefone");
		cliente.cpf = request.params.get("cpf");
		cliente.endereco = request.params.get("endereco");

		try {
			dellCliente(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			saveCliente(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static Cliente encontrar_Cliente(String cpf) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;
		ResultSet rs;

		Cliente cliente = new Cliente();

		String query = "SELECT * FROM cliente where cpf = ?";

		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, cpf);

		rs = pstm.executeQuery();

		while (rs.next()) {
			cliente.pnome = rs.getString("pnome");
			cliente.unome = rs.getString("unome");
			cliente.cpf = rs.getString("cpf");
			cliente.telefone = rs.getString("telefone");
			cliente.endereco = rs.getString("endereco");
		}

		return cliente;

	}

	public static List<Cliente> getAllCliente() throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;

		PreparedStatement pstm;
		ResultSet rs;
		List<Cliente> retorno = new ArrayList<Cliente>();

		String query = "select * from cliente ORDER BY (pnome)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			Cliente func = new Cliente(rs.getString("pnome"),
					rs.getString("unome"), rs.getString("cpf"),
					rs.getString("telefone"), rs.getString("endereco"));

			retorno.add(func);

		}
		return retorno;

	}

	public static void dellCliente(String cpf) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "DELETE FROM cliente where cpf = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, cpf);
		pstm.execute();
	}

	public static void saveCliente(Cliente cliente) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "insert into cliente ( pnome, unome, cpf, telefone, endereco) values (?,?,?,?,?)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		pstm.setString(1, cliente.pnome);
		pstm.setString(2, cliente.unome);
		pstm.setString(3, cliente.cpf);
		pstm.setString(4, cliente.telefone);
		pstm.setString(4, cliente.endereco);

		pstm.execute();

	}

}