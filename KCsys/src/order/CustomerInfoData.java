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

public class CustomerInfoData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();	
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//---------------------------------------------------------------��ȡ��ַ-------------------------
	public String getAddr(String id){
		String s = null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from CustomerInfo where id="+id+"");
			while(res.next()){
				s=res.getString("Address").trim();
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
		/*int xl=4;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;*/
		return s;
	}
	//---------------------------------------------------------------��ȡ�ͻ�-------------------------
	public String[][] getCustomerInfo(int id,String Contact,String name){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from CustomerInfo where Belong = "+id+" and (Contact like '%"+Contact+"%' "
					+ "and CName like '%"+name+"%')  order by id");
			while(res.next()){
				ls.add(res.getString("id").trim());
				ls.add(res.getString("CName").trim());
				ls.add(res.getString("Contact"));
				ls.add(res.getString("Tel").trim());
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//-----------------------------------------�޸Ŀͻ�-------------------------------------
	public void alterCustomer(String name,String contact,String tel,String addr,String id){
		//Date cd=new Date();
		/*String time=timef.format(cd);
		String ckd=String.format("%tF",cd);*/
		try {
			sql = con.createStatement();
			sql.execute("update CustomerInfo set CName = '"+name+"' where id = "+id+";"
					+ "update CustomerInfo set Contact = '"+contact+"' where id = "+id+";"
					+ "update CustomerInfo set Tel = '"+tel+"' where id = "+id+";"
					+ "update CustomerInfo set Address = '"+addr+"' where id = "+id+";");
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(f,"�޸ĳɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
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
	//-----------------------------------------��ӿͻ�-------------------------------------
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
			JOptionPane.showMessageDialog(f,"��ӳɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JFrame f=new JFrame();
			f.setAlwaysOnTop(true);
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
}
