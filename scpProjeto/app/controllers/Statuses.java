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

public class Statuses extends Controller {

	public static void index() {
		List<Status> statuses = new ArrayList<Status>();
		try {
			statuses = getAllStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(statuses);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Status status = new Status();
		try {
			status = encontrar_Status(nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(status);
	}

	public static void editar(String nome) {
		Status statuses = new Status();
		try {
			statuses = encontrar_Status(nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(statuses);
	}

	public static void excluir(String nome) {
		try {
			dellStatus(nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void cadastrar_Status(@Required String nome) {
		Status status = new Status(nome);
		if (validation.hasErrors()) {
			render("Statuses/inserir.html", status);
		}

		try {
			saveStatus(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void editar_status(String nome) {
		validation.required(request.params.get("nome"));

		Status status = new Status();
		try {
			status = encontrar_Status(nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (validation.hasErrors()) {
			render("Statuses/editar.html", status);
		}

		status.nome = request.params.get("nome");

		try {
			saveStatus(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static Status encontrar_Status(String nome) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;
		ResultSet rs;

		Status status = new Status();

		String query = "Select * from status where nome = ?";

		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, nome);

		rs = pstm.executeQuery();

		while (rs.next()) {

			status.nome = rs.getString("nome");

		}

		return status;
	}

	public static List<Status> getAllStatus() throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;

		PreparedStatement pstm;
		ResultSet rs;
		List<Status> statuses = new ArrayList<Status>();

		String query = "select * from status ORDER BY (nome)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			Status status = new Status(rs.getString("nome"));

			statuses.add(status);

		}
		return statuses;

	}

	public static void dellStatus(String nome) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "DELETE FROM status where nome = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, nome);
		pstm.execute();
	}

	public static void saveStatus(Status status) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "insert into status (status) values (?)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		pstm.setString(1, status.nome);

		pstm.execute();

	}
}
