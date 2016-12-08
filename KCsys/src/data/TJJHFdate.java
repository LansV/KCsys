package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TJJHFdate {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public String[][] getdate(String xgys,String qdate,String edate,String xsp){
		Date da=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> ls=new ArrayList<String>();
		if(qdate.length()==0){
			qdate="2016-1-1";
		}
		if(edate.length()==0){
			edate=sdf.format(da);
		}
		try{
			sql = con.createStatement();
			res = sql.executeQuery("select dh,gys,zj,sp,bh,sl,GYs_name,KC_jhj,date,KC_dw from YF,GYs,KC where YF.gys=GYs.GYs_bh and YF.bh=KC.KC_sbh"
					+ " and gys like '%"+xgys+"%' and '"+qdate+"'<=date and date<='"+edate+"' and sp like '%"+xsp+"%'");	
			while(res.next()){
				ls.add(res.getString("gys").trim());
				ls.add(res.getString("GYs_name").trim());
				ls.add(res.getString("dh").trim());
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("sl").trim());
				ls.add(res.getString("KC_dw").trim());
				ls.add(res.getString("KC_jhj").trim());
				ls.add(res.getString("zj").trim());
				ls.add(res.getString("date").trim());
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
	//--------------------------------标准二维数组模型
/*	public String[][] getdate(){
		List<String> ls=new ArrayList<String>();
		try{
			sql = con.createStatement();
			res = sql.executeQuery("");
			while(res.next()){
				
			}
		}catch(Exception e){
			
		}
		int xl=9;
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
