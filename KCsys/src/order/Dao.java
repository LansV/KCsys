package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Dao {
	 Connection con;
	public  Connection getcon(){
		  try{
	 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	   }catch(ClassNotFoundException e){
	 		   JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����ϵ������˾");
	 		   System.exit(0);
	 	   }
	 	   try{
	 		   con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CKsys3","sa","llfaicly1314@^");
	 	   }catch(SQLException e){
	 		   JOptionPane.showMessageDialog(null, "���ӷ�����ʧ�ܣ�\n����ϵ������˾");
	 		   System.exit(0);
	 	   }
	 	   return con;
	 	   //test
	}
	/*
	 * test
	public static void main(String[] args){
		new Dao().getcon();
	}
	*/
}

