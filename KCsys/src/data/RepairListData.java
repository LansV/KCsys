package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class RepairListData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	//-------------------------------------------------写入维修---------------------------------------------
	public boolean wx(String dh,String khid ,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int skfs,String user,String repairid,String repairman,String pumpNo) {
		Boolean b=false;
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into WXD values"
					+ "('"+dh+"',"+khid+",'"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ","+skfs+",0,'"+ckd+"',1,'"+user+"',"+repairid+",'"+repairman+"','"+pumpNo+"')");
			 b=true;
		}catch(Exception e){
			
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		b=false;
		     		 JOptionPane.showMessageDialog(null,"断开错误");
		     	 }
		}
		return b;
	}
	//---------------------------------------------------商品查询（单价）--------------------------
	public String[][] spcxdj(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_name like '%"+s+"%' order by KC_typeid");
			while(res.next()){
				if(res.getString("KC_sbh")==null){
					ls.add("");
				}else{
					ls.add(res.getString("KC_sbh").trim());
				}
				ls.add(res.getString("KC_name").trim());
				ls.add(res.getString("KC_dw").trim());
				if(res.getString("KC_dj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.2f",res.getDouble("KC_dj")));	
				}
				ls.add(res.getString("KC_sl").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		int xl=5;
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
	//-----------------------------------------客户查询---------------------------
	public String[][] khx(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from customerinfo where customerid like '%"+s+"%' or customername like '%"+s+"%' or "
					+ "contact like '%"+s+"%' or address like '%"+s+"%'");
			while(res.next()){
				ls.add(res.getString("customerid"));
				ls.add(res.getString("customername").trim());
				ls.add(res.getString("contact").trim());
				ls.add(res.getString("tel").trim());
				ls.add(res.getString("address").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		int xl=5;
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
	//-----------------------------------------------------获取客户编号-------------------------------------------
	public Integer getCustomerNo(){
		String st=null;
		int i = 0;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(customerid) as customerid from customerinfo");
				while(res.next()){
					st=res.getString("customerid");
					if(st==null){
						i=10001;
					}else{
						int j=res.getInt("customerid");
						i=j+1;
					}
				}
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"获取订单号错误");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return i;
	}
	//-----------------------------------------------------维修获取单号-------------------------------------------
	public String wxdh(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="X"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from WXD where dh like '"+s+"%'");
				while(res.next()){
					st=res.getString("dh");
					if(st==null){
						ls=s+"001";
					}else{
						st=res.getString("dh").trim();
						String stl=st.substring(st.length()-3,st.length());
						int i=Integer.parseInt(stl);
						i=i+1;
						String z=Integer.toString(i);
						if(z.length()==1){
							ls=s+"00"+z;
						}else if(z.length()==2){
							ls=s+"0"+z;
						}else{
							ls=s+z;
						}
					}
				}
 		} catch (Exception e) {
			// TODO Auto-generated catch block
 		   String sOut = "";
	         StackTraceElement[] trace = e.getStackTrace();
	         for (StackTraceElement s1 : trace) {
	             sOut += "\tat " + s1 + "\r\n";
	         }
			JOptionPane.showMessageDialog(null,"获取维修单错误\n"+sOut);
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return ls;
	}
	public String[][] getSaleman() {
		// TODO Auto-generated method stub
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from UserB where qx=1");
			while(res.next()){
				ls.add(res.getString("id").trim());
				ls.add(res.getString("username").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		int xl=2;
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
