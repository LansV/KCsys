package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Dao {
	 Connection con;
	public  Connection getcon(){
		  try{
	 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	   }catch(ClassNotFoundException e){
	 		   JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j,"未找到到驱动文件");
	 		   System.exit(0);
	 	   }
	 	   try{
	 		   con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CKsys3","sa","llfaicly1314@^");
	 	   }catch(SQLException e){
	 		  JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j, "请检查数据库路径及数据名称");
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

