package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import data.Dao;

public class SQLTest {
	
   	
	public static void main(String[] args){
		Thread t1 =new MyThread("T1");
		Thread t2 =new MyThread("T2");
		t1.start();t2.start();
	}
}
class MyThread extends Thread {
	static Statement sql=null;
   	ResultSet res=null;
   	static Dao d=new Dao();
	static Connection con = d.getcon();
	private String ThreadName;
	public MyThread(String ThreadName){
		this.ThreadName=ThreadName;
	}
	public void run() {
//		for(int i=0;i<100;i++){
//			System.out.println(ThreadName+":"+i);
//		}
		try{
			sql = con.createStatement();
			sql.execute("update KC set KC_date='2017-2-3',kc_version=kc_version+1 where KC_sbh=102009 and kc_version=5");
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}


