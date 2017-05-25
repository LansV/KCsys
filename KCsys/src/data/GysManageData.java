package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GysManageData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
   	getData gd=new getData();
	Connection con = d.getcon();
	public int deleteGys(String gysid){
		int i=0;
		try{
			sql=con.createStatement();
			sql.execute("delete GYs where GYs_bh = "+gysid);
		}catch(Exception e){
			
		}
		return i;
	}
}
