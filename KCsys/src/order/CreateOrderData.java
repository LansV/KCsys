package order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreateOrderData {
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
		String s="D"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from temporder where dh like '"+s+"%'");
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
	//-------------------------------------------------写入定单---------------------------------------------
	public Boolean wxs(String dh,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int belong) {
		Boolean b=true;
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into temporder values"
					+ "('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ",0,"+belong+")");
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
		}catch(Exception e){
			b=false;
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"添加订单错误");
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
	//------------------------------------------------------添加客户-----------------------------
	public void addCustomer(String name,String contact,String tel,String addr,int id){
		Date cd=new Date();
		String time=timef.format(cd);
		String ckd=String.format("%tF",cd);
		try {
			sql = con.createStatement();
			sql.execute("insert into CustomerInfo values('"+name+"','"+contact+"','"+tel+"','"+addr+"',"
					+ ""+id+",'"+ckd+"','"+time+"')");
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"添加成功");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"\t添加失败\n请重新录入信息");
		}finally{
			if(res!=null){
				try {
					res.close();
					res=null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"断开数据连接错误");
				}
			}
			if(sql!=null){
				try {
					sql.close();
					sql=null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"断开数据连接错误");
				}
			}
		}
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
	public String[][] khx(int id,String Contact,String name){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from CustomerInfo where Belong = "+id+" and (Contact like '%"+Contact+"%' "
					+ "or CName like '%"+name+"%')  order by id");
			while(res.next()){
				ls.add(res.getString("CName").trim());
				ls.add(res.getString("Contact").trim());
				ls.add(res.getString("Tel").trim());
				ls.add(res.getString("Address").trim());
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
}
