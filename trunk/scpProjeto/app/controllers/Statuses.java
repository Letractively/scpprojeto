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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Status> statuses1 = new ArrayList<Status>();
			
			String query = "select * from status ORDER BY (nome)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Status status = new Status(rs.getString("nome"));
			
				statuses1.add(status);
			
			}
			statuses = statuses1;
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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Status status1 = new Status();
			
			String query = "Select * from status where nome = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				status1.nome = rs.getString("nome");
			
			}
			status = status1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(status);
	}

	public static void editar(String nome) {
		Status statuses = new Status();
		try {
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
			statuses = status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(statuses);
	}

	public static void excluir(String nome) {
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "DELETE FROM status where nome = ?";
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.execute();
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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into status (status) values (?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			pstm.setString(1, status.nome);
			
			pstm.execute();
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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Status status1 = new Status();
			
			String query = "Select * from status where nome = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				status1.nome = rs.getString("nome");
			
			}
			status = status1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (validation.hasErrors()) {
			render("Statuses/editar.html", status);
		}

		status.nome = request.params.get("nome");

		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into status (status) values (?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			pstm.setString(1, status.nome);
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}
}
