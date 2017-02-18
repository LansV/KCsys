package data;

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

public class AssembleData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//---------------------------------------------写入库存------------------------------------------------
	public boolean wkc(String xh,String sp,Double jhj,Double dj,String sbh,String user){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//String time=timef.format(date2);
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		boolean b=true;
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_xh,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values(1,'成品','"+xh+"','"+sp+"',"+jhj+","+dj+",0,'台','"+ckd+"','"+user+"',0,0,"+sbh+",127)");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(f,"写入数据错误！","错误",0);
			return b=false;
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
		return b;
	}
	//-------------------------------------------------写入组装-----------------------------------------
	public boolean wzz(String sbh,String zm,int bh,String psbh,String sp,String dw,Double dj,int sl,Double je,String user){
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		boolean b=true;
		try{
			sql = con.createStatement();
			sql.execute("insert into assembleB values('"+sbh+"','"+zm+"',"+bh+",'"+psbh+"','"+sp+"','"+dw+"',"+dj+","+sl+","+je+",'"+user+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(f,"添加组装数据错误");
			return b=false;
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
	//------------------------------------------------------------组装记录-----------------------------------
	public boolean zzjl(String cx){
		boolean b=false;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from assembleB where zname='"+cx+"'");
			if(res.next()){
				b=true;
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
		return b;
	}
	//-----------------------------------------------获取商品编号---------------------------------------
	public String getspid(int cx){
		String s="";
		int i=0;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(KC_sbh) as bh from KC where KC_typeid = "+cx);
			while(res.next()){
				i=res.getInt("bh");
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
		if(i==0){
			int sl=(cx+100)*1000+1;
			s=Integer.toString(sl);
		}else{
			s=Integer.toString(i+1);
		}
		return s;
	}
	//---------------------------------------------------商品查询进货价--------------------------
	public String[][] spcxjhj(String s){
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
				if(res.getString("KC_jhj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.2f",res.getDouble("KC_jhj")));	
				}
				ls.add(res.getString("KC_sl"));
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
	//------------------------------------------------------------获取组名-----------------------------------
	public String[][] zm(String QueryString){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(sbh) as sbh,zname from assembleB where zname like '%"+QueryString+"%' or"
					+ " sbh like '%"+QueryString+"%' group by zname order by sbh");
			while(res.next()){
				ls.add(res.getString("sbh").trim());
				ls.add(res.getString("zname").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"获取组名出错");
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
