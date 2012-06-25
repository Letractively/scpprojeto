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

public class Application extends Controller {

    public static void index() {
    	render ();
    }
    
    public static void busca (String numPedido) {
    	List<Pedido> pedidos = new ArrayList<Pedido>();
    	try {
    		BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Pedido> retorno = new ArrayList<Pedido>();
			
			String query = "select * from pedido where numPedido = ?";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Pedido func = new Pedido(rs.getString("cliente"),
						rs.getString("pacote"), rs.getString("status"),
						rs.getString("formaPag"), rs.getString("funcionario"), rs.getString("numPedido"),
						rs.getString("dataInscricao"), rs.getString("dataCadastro"));
			
				retorno.add(func);
			
			}
			pedidos = retorno;
    	} catch(SQLException ex) {
    		ex.printStackTrace();
		}
		render(pedidos);

    }

}