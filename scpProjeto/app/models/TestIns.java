package models;

import java.sql.SQLException;

public class TestIns {
	
	public static void main (String args[]){
		
		try {
			FuncionarioSQL.salvar_func("arthur", "mac", "lalal", "234235", "0396246", "ruadoblala", "arthurdhe@hotmail.com", "123", "afhsf", "25234");
			FuncionarioSQL.remover_func("234235");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
