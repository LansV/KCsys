package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class xsfData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//-----------------------------------------------------XS获取单号-------------------------------------------
	public String xsdh(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="K"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from XSD where dh like '"+s+"%'");
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
			JOptionPane.showMessageDialog(null,"错误");
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
	//-----------------------------------------客户查询---------------------------
	public String[][] khx(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KHx where KH_name like '%"+s+"%' or KH_add like '%"+s+"%' or "
					+ "KH_llx like '%"+s+"%'");
			while(res.next()){
				ls.add(res.getString("KH_name").trim());
				ls.add(res.getString("KH_llx").trim());
				ls.add(res.getString("KH_tel").trim());
				ls.add(res.getString("KH_add").trim());
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
		int xl=4;
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
	
	//----********************************************************
	//*****************************************************
	//*************************************************************
	//*************************************************************
	//--------------------------------------------------添加客户信息---------------------------------------------------
	public void wkh(String mc,String lxr,String lxtel,String add){
		int  sp=0;
		try {
			sql = con.createStatement();
			//System.out.println(id);
			res=sql.executeQuery("select max(KH_id) as id from KHx");
			while(res.next()){
				sp=res.getInt("id");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"获取客户数据错误！","错误",0);
		}
		int ids=sp+1;
		try{
			sql = con.createStatement();
			sql.execute("insert into KHx values("+ids+",'"+mc+"','"+lxr+"','"+lxtel+"','','"+add+"')");
			JOptionPane.showMessageDialog(null,"客户添加完成");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"客户添加错误");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"断开错误");
		     	 }
		}
	}
	//-------------------------------------------------写入销售单---------------------------------------------
	public Boolean wxs(String dh,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int skfs,String user) {
		Boolean b=true;
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into XSD values"
					+ "('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ","+skfs+",0,'"+ckd+"',1,'"+user+"')");
		}catch(Exception e){
			b=false;
			JOptionPane.showMessageDialog(null,"添加销售单错误");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"断开错误");
		     	 }
		}
		return b;
	}
	//--------------------------------------------------------更新出库-----------------------------------
	public void wkcout(String sbh,String sckcp,int sn,String qx,String user){
		int kcsl = 0;
		int jg = 0;
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		try{
			sql = con.createStatement();
			res=sql.executeQuery("select*from KC where KC_sbh = '"+sbh+"'");
			while(res.next()){
				kcsl=res.getInt("KC_sl");
			}
			jg=kcsl-sn;
		//System.out.println(kcsl);
		// 出 0               进 1
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null,"得到库存数量失败");
		}
		try{
			sql = con.createStatement();
			if(qx.length()>1){
				String[] st=qx.split(",");
				sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = "+sbh+";"
						+ "insert into KCJL values(0,'"+sbh+"','"+sckcp+"',"+sn+",'"+st[0]+"','"+user+"','"+ckd+"','"+time+"','"+st[1]+"')");
			}else{
				sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = '"+sbh+"';"
						+ "insert into KCJL values(0,'"+sbh+"','"+sckcp+"',"+sn+",'"+qx+"','"+user+"','"+ckd+"','"+time+"','NULL')");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"出库错误");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"断开错误");
		     	 }
		}
	}
}
