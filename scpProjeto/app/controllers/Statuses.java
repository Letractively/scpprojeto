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
		List<Status> statuses = Status.getAllStatus();
		render(statuses);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Status status = Status.encontrar_Status(nome);
		render(status);
	}

	public static void editar(String nome) {
		Status statuses = Status.encontrar_Status(nome);
		render(statuses);
	}

	public static void excluir(String nome) {
		Status.dellStatus(nome);
		index();
	}

	public static void cadastrar_Status(@Required String nome) {
		Status status = new Status(nome);
		if (validation.hasErrors()) {
			render("Statuses/inserir.html", status);
		}

		status.saveStatus();
		index();
	}

	public static void editar_status(String nome) {
		validation.required(request.params.get("nome"));

		Status status = Status.encontrar_Status(nome);

		if (validation.hasErrors()) {
			render("Statuses/editar.html", status);
		}

		status.nome = request.params.get("nome");

		status.saveStatus();
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

	public static List<Status> getAllStatus() {
		List<Status> retorno = new ArrayList<Status>();
		return retorno;// TODO
	}

	public static void dellStatus(String nome) {
		// TODO
	}

	public void saveStatus() {
		// TODO
	}
}
