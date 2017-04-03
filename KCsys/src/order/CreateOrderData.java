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
	//-----------------------------------------------------XS��ȡ����-------------------------------------------
	public String getNo(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="O"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from comfirmorder");
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
			JOptionPane.showMessageDialog(null,"��ȡ�����Ŵ���");
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
	//-----------------------------------------------------XS��ȡ��ʱ����-------------------------------------------
	public String tempNo(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="L"+y+m;
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
			JOptionPane.showMessageDialog(null,"��ȡ��ʱ���Ŵ���");
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
	//-------------------------------------------------�޸���ʱ��״̬---------------------------------------------
	public void alertTempListStatus(String dh,int id) {
		try{
			sql = con.createStatement();
			sql.execute("update temporder set bstatus = 1 where dh='"+dh+"' and belong = "+id+" ");
		}catch(Exception e){
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"�����ʱ��������");
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
	//-------------------------------------------------д�붨��---------------------------------------------
	public void wxs(JFrame f,String dh,String mcid,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int belong,String table,String ckd) {
		try{
			sql = con.createStatement();
			sql.execute("insert into "+table+"(dh,customerid,customer,bh,xh,sp,dw,zk,dj,sl,je,bz,bdate,bstatus,belong) values"
					+ "('"+dh+"',"+mcid+",'"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ",0,"+belong+")");
		}catch(Exception e){
			JOptionPane.showMessageDialog(f,"��Ӷ�������");
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
	//------------------------------------------------------��ӿͻ�-----------------------------
	public void addCustomer(JFrame f,String cid,String name,String contact,String tel,String addr,int id){
		Date cd=new Date();
		String time=timef.format(cd);
		String ckd=String.format("%tF",cd);
		try {
			sql = con.createStatement();
			sql.execute("insert into customerinfo values("+cid+",'"+name+"','"+contact+"','"+tel+"','"+addr+"',"
					+ ""+id+",'"+ckd+"','"+time+"')");
			JOptionPane.showMessageDialog(f,"��ӿͻ��ɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(f,"\t���ʧ��\n������¼����Ϣ");
		}finally{
			if(res!=null){
				try {
					res.close();
					res=null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"�Ͽ��������Ӵ���");
				}
			}
			if(sql!=null){
				try {
					sql.close();
					sql=null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"�Ͽ��������Ӵ���");
				}
			}
		}
	}
	//---------------------------------------------------��Ʒ��ѯ�����ۣ�--------------------------
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//-----------------------------------------��ѯ��ʱ��---------------------------
	public String[][] getTempListDate(String dh,int id){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from temporder where dh='"+dh+"' and belong = "+id+" and bstatus = 0 order by bh");
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
				ls.add(String.format("%.2f", res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f", res.getDouble("je")));
				ls.add(res.getString("bz").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"��ȡ��ʱ������");
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
		int xl=9;
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
	//-----------------------------------------��ѯ��ʱ��---------------------------
	public String[][] getTempListName(JFrame f,int id){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select dh,max(customerid) as id, max(customer) as khmc,max(bdate) as bdate from temporder where belong = "+id+" and bstatus = 0 group by dh");
			while(res.next()){
				ls.add(res.getString("dh").trim());
				ls.add(res.getString("id").trim());
				ls.add(res.getString("khmc").trim());
				ls.add(res.getString("bdate").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(f,"��ȡ��ʱ������");
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//-----------------------------------------�ͻ���ѯ---------------------------
	public String[][] khx(int id,String Contact,String name){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from customerinfo where belong = "+id+" and (contact like '%"+Contact+"%' "
					+ "or customername like '%"+name+"%')  order by id");
			while(res.next()){
				ls.add(res.getString("customerid").trim());
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//-----------------------------------------------------��ȡ�ͻ�����-------------------------------------------
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
			JOptionPane.showMessageDialog(null,"��ȡ�����Ŵ���");
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
	//-----------------------------------------ָ���ͻ���ѯ---------------------------
	public List<String> getCustomerInfo(int id,String name){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from customerinfo where belong = "+id+" and customerid = '"+name+"'");
			while(res.next()){
				ls.add(res.getString("customerid").trim());
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
		return ls;
	}
}
