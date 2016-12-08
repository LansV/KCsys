package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dorderdate {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	Date da=new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String[][] getdate(){
		List<String> ls=new ArrayList<String>();
		try{
			sql = con.createStatement();
			res = sql.executeQuery("");	
			while(res.next()){
				
			}
		}catch(Exception e){
			
		}
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //行
	   		for(int j=0;j<xl;j++){  //列
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//===数据模型标准
/*	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	Date da=new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String[][] getdate(){
		List<String> ls=new ArrayList<String>();
		try{
			sql = con.createStatement();
			res = sql.executeQuery("");	
			while(res.next()){
				
			}
		}catch(Exception e){
			
		}
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //行
	   		for(int j=0;j<xl;j++){  //列
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}*/
}
