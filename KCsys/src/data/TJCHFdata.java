package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class TJCHFdata {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public String[][] getdate(String qx,String qdate,String edate,String sp){
		List<String> ls=new ArrayList<String>();
		Date da=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(qdate.length()==0){
			qdate="2016-1-1";
		}
		if(edate.length()==0){
			edate=sdf.format(da);
		}
		try{
			sql = con.createStatement();
			if(qx.length()==0){
				res = sql.executeQuery("select dh,xh,sp,dw,sl,KC_jhj,dj,zk,date from XSD,KC where xh=KC_sbh "
						+ "and date between '"+qdate+"' and '"+edate+"' and sp like '%"+sp+"%' "
						+ "union all "
						+ "select dh,xh,sp,dw,sl,KC_jhj,dj,zk,date from WXB,KC where xh=KC_sbh "
						+ "and date between '"+qdate+"' and '"+edate+"' and sp like '%"+sp+"%' "
						+ "union all "
						+ "select qx,bh,sp,KC_dw,sl,KC_jhj,NULL,NULL,jldate from KCJL,KC where Stata=0 and qx!=1 "
						+ "and qx!=2 and bh=KC_sbh and jldate between '"+qdate+"' and '"+edate+"' "
						+ "and sp like '%"+sp+"%' "
						+ "order by dh");
			}else if(qx.equals("销售")){
				res = sql.executeQuery("select dh,xh,sp,dw,sl,KC_jhj,dj,zk,date from XSD,KC where xh=KC_sbh "
						+ "and date between '"+qdate+"' and '"+edate+"' and sp like '%"+sp+"%' order by dh");
	
			}else if(qx.equals("维修")){
				res = sql.executeQuery("select dh,xh,sp,dw,sl,KC_jhj,dj,zk,date from WXB,KC where xh=KC_sbh "
						+ "and date between '"+qdate+"' and '"+edate+"' and sp like '%"+sp+"%' order by dh");
			}else if(qx.equals("组装")){
				res = sql.executeQuery("select qx as dh,bh as xh,sp,KC_dw as dw,sl,KC_jhj,NULL as dj,NULL as zk,jldate as date from KCJL,KC where Stata=0 and qx!=1 "
						+ "and qx!=2 and bh=KC_sbh and jldate between '"+qdate+"' and '"+edate+"' "
						+ "and sp like '%"+sp+"%' and qx=3"
						+ "order by dh");
			}else if(qx.equals("测试")){
				res = sql.executeQuery("select qx as dh,bh as xh,sp,KC_dw as dw,sl,KC_jhj,NULL as dj,NULL as zk,jldate as date from KCJL,KC where Stata=0 and qx!=1 "
						+ "and qx!=2 and bh=KC_sbh and jldate between '"+qdate+"' and '"+edate+"' "
						+ "and sp like '%"+sp+"%' and qx=4"
						+ "order by dh");
			}
			while(res.next()){
				String dh=res.getString("dh").trim();
				ls.add(dh);
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.3f",res.getDouble("KC_jhj")));
				ls.add(String.format("%.3f",res.getDouble("dj")));
				if(res.getString("zk")!=null){
					int sl=res.getInt("sl");
					Double jhj=res.getDouble("KC_jhj");
					Double dj=res.getDouble("dj");
					Double zk=res.getDouble("zk");
					Double jhzj=sl*jhj;
					Double xszj=sl*dj*zk;
					ls.add(res.getString("zk").trim());
					ls.add(String.format("%.3f",jhzj));
					ls.add(String.format("%.3f",xszj));
				}else{
					int sl=res.getInt("sl");
					Double jhj=res.getDouble("KC_jhj");
					Double dj=res.getDouble("dj");
					Double jhzj=sl*jhj;
					Double xszj=sl*dj;
					ls.add(res.getString("zk"));
					ls.add(String.format("%.3f",jhzj));
					ls.add(String.format("%.3f",xszj));
				}
				ls.add(res.getString("date").trim());
				if(dh.length()==1){
					int d=Integer.parseInt(dh);
					if(d==3){
						ls.add("组装");
					}else if(d==4){
						ls.add("测试");
					}else{
						ls.add("error");
					}
				}else{
					String s=dh.substring(0,1);
					if(s.equals("K")){
						ls.add("销售");
					}else if(s.equals("X")){
						ls.add("维修");
					}else{
						ls.add("error");
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"error");
		}
		int xl=12;
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
}
