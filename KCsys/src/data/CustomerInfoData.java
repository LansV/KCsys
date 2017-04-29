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

public class CustomerInfoData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();	
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//---------------------------------------------------------------获取地址-------------------------
	public String getAddr(String id){
		String s = null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from customerinfo where customerid="+id+"");
			while(res.next()){
				s=res.getString("address").trim();
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
	   	for(int i=0;i<ls.size()/xl;i++){  //行
	   		for(int j=0;j<xl;j++){  //列
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;*/
		return s;
	}
	//---------------------------------------------------------------获取客户-------------------------
	public String[][] getCustomerInfo(int id,String Contact,String name){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from customerinfo where contact like '%"+Contact+"%' "
					+ "or customername like '%"+name+"%'  order by id");
			while(res.next()){
				ls.add(res.getString("customerid").trim());
				ls.add(res.getString("customername").trim());
				ls.add(res.getString("contact"));
				ls.add(res.getString("tel").trim());
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
	//-----------------------------------------修改客户-------------------------------------
	public void alterCustomer(JFrame f,String name,String contact,String tel,String addr,String id){
		//Date cd=new Date();
		/*String time=timef.format(cd);
		String ckd=String.format("%tF",cd);*/
		try {
			sql = con.createStatement();
			sql.execute("update customerinfo set customername = '"+name+"' where customerid = "+id+";"
					+ "update customerinfo set contact = '"+contact+"' where customerid = "+id+";"
					+ "update customerinfo set tel = '"+tel+"' where customerid = "+id+";"
					+ "update customerinfo set address = '"+addr+"' where customerid = "+id+";");
			JOptionPane.showMessageDialog(f,"修改成功");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
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
	//-----------------------------------------------------获取客户单号-------------------------------------------
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
	//-----------------------------------------添加客户-------------------------------------
	public void addCustomer(JFrame f,String name,String contact,String tel,String addr,int id){
		Date cd=new Date();
		String time=timef.format(cd);
		String ckd=String.format("%tF",cd);
		try {
			sql = con.createStatement();
			sql.execute("insert into customerinfo values("+getCustomerNo()+",'"+name+"','"+contact+"','"+tel+"','"+addr+"',"
					+ ""+id+",'"+ckd+"','"+time+"')");
			JOptionPane.showMessageDialog(f,"添加成功");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
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
}
