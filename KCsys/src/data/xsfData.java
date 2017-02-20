package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class xsfData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
}
