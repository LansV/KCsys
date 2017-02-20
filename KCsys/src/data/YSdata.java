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
public class YSdata {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	//====================================================get proceeds method===========================
	public int getproceedsmethod(String s){
		int i=0;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select skfs from XSD where dh='"+s+"'");
			while(res.next()){
				i=res.getInt("skfs");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ�տʽ����");
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
	//====================================================get customers info============================
	public List<String> getcustomerinfo(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KHx where KH_name ='"+s+"'");
			while(res.next()){
				ls.add(res.getString("KH_name").trim());
				ls.add(res.getString("KH_llx").trim());
				ls.add(res.getString("KH_tel").trim());
				ls.add(res.getString("KH_add").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ�ͻ���Ϣ����");
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
	//--------------------------------------------------------��ȡָ���ͻ�Ӧ��-----------------------------------
	public String[][] xys(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery(""
					+ "select dh,SUM(zj) as je,SUM(skje) as skje,Sum(zj)-SUM(skje) as s,MAX(skdate) as skdate from"
					+ "("
					+ "select dh,sum(je) as zj,sum(skje) as skje,max(skdate) as skdate from XSD where khmc = '"+s+"'group by dh "
					+ "union "
					+ "select dh,sum(je) as zj,sum(skje) as skje,max(skdate) as skdate from WXD where khmc = '"+s+"'group by dh "
					+ "union "
					+ "select dh,-sum(tje) as zj,0 as skje,max(tdate) as skdate from THD where khmc= '"+s+"' group by dh) "
					+ "temp group by dh");
			while(res.next()){
				if(res.getDouble("s")==0){
					
				}else{
					ls.add(res.getString("dh").trim());
					ls.add(s);
					ls.add(String.format("%.2f",res.getDouble("je")));
					ls.add(String.format("%.2f",res.getDouble("skje")));
					ls.add(String.format("%.2f",res.getDouble("s")));
					if(res.getString("skdate")==null){
						ls.add(res.getString("skdate"));
					}else{
						ls.add(res.getString("skdate").trim());
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
		int xl=6;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//----------------------------------------------------------------��ȡά�޵�--------------------------------
	public String[][] wxd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select  max(bh) as bh,xh,max(sp) as sp,max(dw) as dw,max(zk) as zk,"
					+ "max(dj) as dj,sum(sl) as sl,sum(je) as je,sum(skje) as skje,MAX(bz) as bz from "
					+ "("
					+ "select bh,xh,sp,dw,zk,dj,sl,je,skje,bz from WXD where dh='"+dh+"' "
					+ "union "
					+ "select max(bh),xh,max(sp),max(dw),max(zk),max(dj),-sum(tsl),-sum(tje),0 as skje,'' as bz "
					+ "from THD where dh='"+dh+"' group by xh"
					+ ") temp group by xh order by bh");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add("");
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
				ls.add(String.format("%.2f",res.getDouble("skje")));
				ls.add(res.getString("bz").trim());
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
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//----------------------------------------------------------------��ȡԭʼ���۵�--------------------------------
	public String[][] ywxd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select bh,xh,sp,dw,zk,dj,sl,je,skje,bz from WXD where dh='"+dh+"'");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add("");
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
				ls.add("0.00");
				ls.add(res.getString("bz").trim());
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
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//----------------------------------------------------------------��ȡԭʼ���۵�--------------------------------
	public String[][] yxsd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select bh,xh,sp,dw,zk,dj,sl,je,skje,bz from XSD where dh='"+dh+"'");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add("");
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
				ls.add("0.00");
				ls.add(res.getString("bz").trim());
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
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//----------------------------------------------------------------��ȡ���۵�--------------------------------
	public String[][] xsd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select  max(bh) as bh,xh,max(sp) as sp,max(dw) as dw,max(zk) as zk,"
					+ "max(dj) as dj,sum(sl) as sl,sum(je) as je,sum(skje) as skje,MAX(bz) as bz from "
					+ "("
					+ "select bh,xh,sp,dw,zk,dj,sl,je,skje,bz from XSD where dh='"+dh+"' "
					+ "union "
					+ "select max(bh),xh,max(sp),max(dw),max(zk),max(dj),-sum(tsl),-sum(tje),0 as skje,'' as bz "
					+ "from THD where dh='"+dh+"' group by xh"
					+ ") temp group by xh order by bh");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add("");
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
				ls.add(String.format("%.2f",res.getDouble("skje")));
				ls.add(res.getString("bz").trim());
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
		int xl=10;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//---------------------------------------------------��ȡ�ܼ�Ӧ��--------------------------------
	public String[][] ys(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from"
					+ "(select khmc,SUM(je) as zj,MAX(lastdate) as fdate from("
					+ "select khmc,SUM(je)-SUM(skje) as je ,MAX (date) as lastdate from XSD group by khmc "
					+ "union "
					+ "select khmc,SUM(je)-SUM(skje) as je ,MAX (date) as lastdate from WXD group by khmc "
					+ "union "
					+ "select khmc,-SUM(tje) as je, max(tdate) as lastdate from THD group by khmc "
					+ ") temp group by khmc"
					+ ") temp where zj >0");
			while(res.next()){
				if(res.getDouble("zj")==0){
					
				}else{
					ls.add(res.getString("khmc").trim());
					ls.add(String.format("%.2f",res.getDouble("zj")));
					if(res.getString("fdate")==null){
						ls.add(res.getString("fdate"));
					}else{
						ls.add(res.getString("fdate").trim());
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
		int xl=3;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//==============================================�����Ƿ�����˻�=============================================
	public List<Integer> pdj(String dh){
		List<Integer> ls=new ArrayList<Integer>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from THD where dh='"+dh+"'");
			while(res.next()){
				ls.add(res.getInt("bh"));
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
		return ls;
	}
	//==============================================�޸��տ�״̬=============================================
	public void alterSkstatus(String dh,int bh,int type){
		try {
			sql = con.createStatement();
			sql.execute("update XSD set skstatus="+type+" where dh='"+dh+"' and bh="+bh+"");
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
	}
	//------------------------------------------------------�����˻�----------------------------------------
	public void gth(String dh,String khmc,int bh,String xh,String sp,String dw,Double zk,Double dj,int sl,Double thje,String yy,int type,
			String user){
		Date date2=new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String time=timeFormat.format(date2);
		String ckd=String.format("%tF", date2);
		// 0Ϊ�տ����  1Ϊδ�տ�  2��Ʒ�˻�
		try{
			sql = con.createStatement();
			sql.execute("insert into THD values ('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","
					+ ""+dj+","+sl+","+thje+",'"+yy+"','"+ckd+"','"+time+"',"+type+",'"+user+"');"
					+ "update XSD set skstatus="+type+" where dh='"+dh+"' and bh="+bh+"");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"����˻����ݴ���");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
	}
	//------------------------------------------------------ȫ���˻�----------------------------------------
	public void dth(String dh,String kh,Double je,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0Ϊ�տ����  1Ϊδ�տ�  2Ϊ����  3Ϊȫ���˻� 4Ϊ��Ʒ�˻�
		try{
			sql = con.createStatement();
			sql.execute("update YSB set zt=3 where dh='"+dh+"';update YSB set date='"+ckd+"' where dh='"+dh+"';"
					+ "insert into THB(dh,kh,je,zl,yz,date)values('"+dh+"','"+kh+"',"+je+",3,'"+yy+"','"+ckd+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"����˻����ݴ���");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
	}
	//---------------------------------------------------����Ӧ��------------------------------------------------
	public void updateys(String dh,int bh,Double je,int zt){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
				sql.execute("update XSD set skje="+je+" where dh='"+dh+"' and bh="+bh+";update XSD set skdate='"+ckd+"' where dh='"+dh+"' and bh="+bh+";"
						+ "update XSD set skstatus="+zt+" where dh='"+dh+"' and bh="+bh+"");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�����տ����ݴ���");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
	}
	//---------------------------------------------д�뻵��--------------------------------------------------
	public void whz(String dh,String kh,Double je,String bz){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("update YSB set zt=2 where dh='"+dh+"';update YSB set date='"+ckd+"' where dh='"+dh+"';"
					+ "insert into HZ values('"+dh+"','"+kh+"',"+je+",'"+ckd+"','"+bz+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"���Ӧ�����ݴ���");
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
	}
}
