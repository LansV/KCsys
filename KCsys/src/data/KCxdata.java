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

public class KCxdata {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	Date date=new Date();		
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//*******************************************读取****************************************************
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
	//------------------------------------------------获取供应商----------------------------------------
	public String[][] getgys(String cx){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from GYs where GYs_name like '%"+cx+"%' order by GYs_zl");
			while(res.next()){
				ls.add(res.getString("GYs_zl").trim());
				ls.add(res.getString("GYs_bh"));
				ls.add(res.getString("GYs_name").trim());
				if(res.getString("GYs_lxr")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_lxr").trim());
				}
				if(res.getString("GYs_tel")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_tel").trim());
				}
				
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
	//--------------------------------------------------------获取种类-----------------------------------
	public String[][] getzl(){
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
	//-------------------------------------------------获取缺货库存信息-------------------------------------------
	public String[][] qhKC(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
				res = sql.executeQuery("select KC_typeid,KC_type,KC_id,GYs_name,KC_name,KC_jhj,KC_fxj,KC_jxj,KC_dj,"
						+ "KC_sl,KC_dw,KC_date,KC_jgsl,KC_sbh,KC_gys "
						+ "from KC,GYs "
						+ "where KC_gys=GYs_bh and KC_sl<=KC_jgsl "
						+ "order by KC_date desc");
			while(res.next()){
				ls.add(res.getString("KC_sbh").trim());
				ls.add(res.getString("KC_type").trim());
				if(res.getString("GYs_name")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_name").trim());
				}
				ls.add(res.getString("KC_name").trim());
				if(res.getString("KC_jhj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_jhj")));
				}
				if(res.getString("KC_fxj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_fxj")));
				}
				if(res.getString("KC_jxj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_jxj")));
				}
				if(res.getString("KC_dj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_dj")));
				}
				if(res.getString("KC_sl")==null){
					ls.add("");
				}else{
					ls.add(res.getString("KC_sl").trim());
				}
				ls.add(res.getString("KC_dw").trim());
				ls.add(res.getString("KC_date").trim());
				ls.add(res.getString("KC_jgsl").trim());
				if(res.getString("KC_gys")==null){
					ls.add("");
				}else{
					ls.add(res.getString("KC_gys").trim());
				}
				
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
		int xl=13;
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
	//-------------------------------------------------获取查询库存信息-------------------------------------------
	public String[][] KCdata(String cx,String zl,String gys){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			if(zl.length()==0){
				res = sql.executeQuery("select KC_typeid,KC_type,KC_id,GYs_name,KC_name,KC_jhj,KC_fxj,KC_jxj,KC_dj,"
						+ "KC_sl,KC_dw,KC_date,KC_jgsl,KC_sbh,KC_gys "
						+ "from KC,GYs "
						+ "where KC_gys=GYs_bh and KC_name like '%"+cx+"%'"
						+ "order by KC_date desc");
			}else{
				res = sql.executeQuery("select KC_typeid,KC_type,KC_id,GYs_name,KC_name,KC_jhj,KC_fxj,KC_jxj,KC_dj,"
						+ "KC_sl,KC_dw,KC_date,KC_jgsl,KC_sbh,KC_gys "
						+ "from KC,GYs "
						+ "where KC_gys=GYs_bh and KC_name like '%"+cx+"%' and KC_typeid = '"+zl+"' "
						+ "order by KC_date desc");
			}
			while(res.next()){
				ls.add(res.getString("KC_sbh").trim());
				ls.add(res.getString("KC_type").trim());
				if(res.getString("GYs_name")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_name").trim());
				}
				ls.add(res.getString("KC_name").trim());
				if(res.getString("KC_jhj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_jhj")));
				}
				if(res.getString("KC_fxj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_fxj")));
				}
				if(res.getString("KC_jxj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_jxj")));
				}
				if(res.getString("KC_dj")==null){
					ls.add("");
				}else{
					ls.add(String.format("%.3f",res.getDouble("KC_dj")));
				}
				if(res.getString("KC_sl")==null){
					ls.add("");
				}else{
					ls.add(res.getString("KC_sl").trim());
				}
				ls.add(res.getString("KC_dw").trim());
				ls.add(res.getString("KC_date").trim());
				ls.add(res.getString("KC_jgsl").trim());
				if(res.getString("KC_gys")==null){
					ls.add("");
				}else{
					ls.add(res.getString("KC_gys").trim());
				}
				
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
		int xl=13;
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
	//-----------------------------------------------获取应付单号-------------------------------------
	public String getyfdh(){
		String s="";
		Date da=new Date();
		String y=String.format("%ty",da);
		String m=String.format("%tm",da);
		String st="D"+y+m;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from YF where dh like '"+st+"%'");
			while(res.next()){
				String sn=res.getString("dh");
				if(sn==null){
					s=st+"001";
				}else{
					sn=res.getString("dh").trim();
					String stl=sn.substring(sn.length()-3,sn.length());
					int i=Integer.parseInt(stl);
					i=i+1;
					String z=Integer.toString(i);
					if(z.length()==1){
						s=st+"00"+z;
					}else if(z.length()==2){
						s=st+"0"+z;
					}else{
						s=st+z;
					}
				}
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
		return s;
	}
	
	//******************************************写入***************************************************
	
	
	
	//******************************************************************************************************
	//-------------------------------------------添加应付----------------------------------------
	public void addyf(String gys,Double jhj,int sl,String sp,String bh){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try {
			sql = con.createStatement();
			sql.execute("insert into YF values('"+getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+bh+","+sl+","+jhj+")");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"写入数据错误！","错误",0);
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
	//---------------------------------------------写入库存------------------------------------------------
	public void waddkc(int zlid,String zl,String xh,String sp,Double jhj,Double dj,int sl,String dw,int sbh,int gys){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_xh,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values("+zlid+",'"+zl+"','"+xh+"','"+sp+"',"+jhj+","+dj+","+sl+",'"+dw+"','"+ckd+"','admin',0,0,"+sbh+","+gys+");"
					+ "insert into KCJL values(1,'"+sbh+"','"+sp+"',"+sl+",'入库','admin','"+ckd+"','"+time+"','添加商品');"
					+ "insert into YF values('"+getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+sbh+","+sl+","+jhj+")");
			JOptionPane.showMessageDialog(null,"添加成功");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"写入数据错误！","错误",0);
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
	//--------------------------------------------------------更新入库-----------------------------------
	public void wkcin(String sbh,String sckcp,int sn,String ly){
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
						+ "insert into KCJL values(1,'"+sbh+"','"+sckcp+"',"+sn+",'"+ly+"','admin','"+ckd+"','"+time+"','NULL')");
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
	public void wkcout(String sbh,String sckcp,int sn,String qx){
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
				sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = '"+sbh+"';"
						+ "insert into KCJL values(0,'"+sbh+"','"+sckcp+"',"+sn+",'"+st[0]+"','admin','"+ckd+"','"+time+"','"+st[1]+"')");
			}else{
				sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = '"+sbh+"';"
						+ "insert into KCJL values(0,'"+sbh+"','"+sckcp+"',"+sn+",'"+qx+"','admin','"+ckd+"','"+time+"','NULL')");
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"库存错误");
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
	//	------------------------------------------更新库存信息-------------------------------------------
	public void updateKC(String sbh,String c,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"错误");
			}else{
				sql.execute("update KC set "+lx+"='"+c+"' where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"修改数据错误");
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
	//----------------------修改库存价格-----------------------------------------
	public void updateKC(String sbh,Double j,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"错误");
			}else{
				sql.execute("update KC set "+lx+"="+j+" where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"修改数据错误");
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
	/*	
	---------------------------------------------------更新库存警告数量
*/
	public void updateKC(String sbh,int sl,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"错误");
			}else{
				sql.execute("update KC set "+lx+"="+sl+" where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"修改数据错误");
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
