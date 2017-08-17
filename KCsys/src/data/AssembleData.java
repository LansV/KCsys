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
	//--------------------------------------------------------更新入库-----------------------------------
	public void wkcin(String sbh,String sckcp,int sn,String ly,String user,String dh){
		Date date2=new Date();
		int kcsl = 0;
		int jg = 0;
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		//获取修改之前的库存数量
		try{
			sql = con.createStatement();
			res=sql.executeQuery("select*from KC where KC_sbh = "+sbh+"");
			while(res.next()){
				kcsl=res.getInt("KC_sl");
			}
			jg=kcsl+sn;
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null,"得到库存数量失败");
		}
		try{
			sql = con.createStatement();
			sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";"
					  + "UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = "+sbh+";"
					  + "insert into KCJL values(1,'"+sbh+"','"+sckcp+"',"+sn+",'"+ly+"','"+user+"','"+ckd+"','"+time+"','"+dh+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"写入库存错误");
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
	//-------------------------------------------------删除组装-----------------------------------------
	public boolean wDeleteAssemble(String sbh,String user){
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		boolean b=true;
		try{
			// cstatus 0 exist 1 delete
			sql = con.createStatement();
			sql.execute("update assembleB set cstatus = 1 where sbh='"+sbh+"';"
					+ "update assembleB set operator = '"+user+"' where sbh='"+sbh+"'");
		}catch(Exception e){
			JOptionPane.showMessageDialog(f,"删除组装数据错误");
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
	//---------------------------------------------写入库存------------------------------------------------
	public boolean wkc(String typeid,String type,String sp,Double jhj,Double dj,String sbh,String user){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//String time=timef.format(date2);
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		boolean b=true;
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values("+typeid+",'"+type+"','"+sp+"',"+jhj+","+dj+",0,'台','"+ckd+"','"+user+"',0,0,"+sbh+",1001)");
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
			// cstatus 0 exist 1 delete
			sql = con.createStatement();
			sql.execute("insert into assembleB values('"+sbh+"','"+zm+"',"+bh+",'"+psbh+"','"+sp+"','"+dw+"',"+dj+","+sl+","+je+",'"+user+"',0)");
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
			res = sql.executeQuery("select*from assembleB where zname='"+cx+"' and cstatus != 1");
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
	//------------------------------------------获取种类及编号--------------------------------------------
		public String[][] getType(String cx){
			List<String> ls=new ArrayList<String>();
			try {
				sql = con.createStatement();
				res = sql.executeQuery("select*from CPz order by CPz_typeid");
				while(res.next()){
					ls.add(res.getString("CPz_typeid"));
					ls.add(res.getString("CPz_type"));
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
	//-----------------------------------------------获取商品编号---------------------------------------
	public String getspid(String cx){
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
			int sl=(Integer.parseInt(cx)+100)*1000+1;
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
	//------------------------------------------------------------获取打印-----------------------------------
	public String[][] zPrint(String QueryString){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from assembleB where sbh='"+QueryString+"' and cstatus = 0 order by psbh");
			int i=1;
			while(res.next()){
				ls.add(""+i);
				ls.add(res.getString("psbh").trim());
				ls.add(res.getString("pname").trim());
				ls.add(res.getString("dw").trim());
				ls.add(res.getString("sl").trim());
				ls.add("");
				i++;
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
		int xl=6;
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
	//------------------------------------------------------------获取详情-----------------------------------
	public String[][] zdetail(String QueryString){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from assembleB where sbh='"+QueryString+"' and cstatus = 0 order by psbh");
			int i=1;
			while(res.next()){
				ls.add(""+i);
				ls.add(res.getString("psbh").trim());
				ls.add(res.getString("pname").trim());
				ls.add(res.getString("dw").trim());
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
				i++;
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
		int xl=7;
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
	//------------------------------------------------------------判断库存是否足够-----------------------------------
	public String[][] pdKC(String sbh,int sl){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select pname,ccount from"
					+ "("
					+ "select psbh,pname,dw,assembleB.sl,KC.KC_sl,KC.KC_sl-assembleB.sl*"+sl+" as ccount from assembleB,KC where assembleB.sbh='"+sbh+"' and assembleB.psbh=KC.KC_sbh"
					+ ")"
					+ "temp where ccount < 0");
			while(res.next()){
				ls.add(res.getString("pname").trim());
				int i=-res.getInt("ccount");
				ls.add(Integer.toString(i));
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
	//------------------------------------------------------------获取组名-----------------------------------
	public String[][] zm(String QueryString){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(sbh) as sbh,zname from assembleB where cstatus = 0 and (zname like '%"+QueryString+"%' or"
					+ " sbh like '%"+QueryString+"%') group by zname order by sbh");
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
