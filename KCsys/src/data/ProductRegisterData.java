package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class ProductRegisterData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public void insertProductRegister(String sbh,String name,String pumpNo,String motorNo,String tlNo,
			String assemble,String qc,String user){
		Date da=new Date();
		String ckd=String.format("%tF", da);
		try{
			sql = con.createStatement();
			sql.execute("insert into productregister values('"+sbh+"','"+name+"','"+pumpNo+"','"+motorNo+"','"+tlNo+"','"+assemble+"',"
					+ "'"+qc+"','"+ckd+"','"+user+"',0,NULL,NULL)");
			JOptionPane.showMessageDialog(null,"添加成功");
			//0 未注册 1注册
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加错误");
		}
	}
	//-------------------------------------------------------获取序列号---------------------------------
	public String getSN(){
		String sn = null;
		Calendar now = Calendar.getInstance();  
		int yeari=now.get(Calendar.YEAR)-2000;
		int weeki=now.get(Calendar.WEEK_OF_YEAR);
		String year=Integer.toString(yeari);
		String sweek=Integer.toString(weeki);
		String week;
		if(sweek.length()==1){
			week="0"+sweek;
		}else{
			week=sweek;
		}
		String qsn="MF"+year+week;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select count(1)as ccount from productregister where tl_no like '"+qsn+"%'");
			int count = 0;
			while(res.next()){
				count=res.getInt("ccount");
			}
			if(count==0){
				sn=qsn+"001B";
			}else{
				String s=Integer.toString(count+1);
				if(s.length()==1){
					sn=qsn+"00"+s+"B";
				}else if(s.length()==2){
					sn=qsn+"0"+s+"B";
				}else if(s.length()==3){
					sn=qsn+s+"B";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        String sOut = "";
	         StackTraceElement[] trace = e.getStackTrace();
	         for (StackTraceElement s : trace) {
	             sOut += "\tat " + s + "\r\n";
	         }
			JOptionPane.showMessageDialog(null,"获取商品信息错误\n"+sOut);
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
		return sn;
	}
	//--------------------------------------------------------获取商品-----------------------------------
	public String[][] getSpName(){
		List<String> ls=new ArrayList<String>();
		ls.add("");ls.add("请选择商品");
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_gys='127'");
			while(res.next()){
				ls.add(res.getString("KC_sbh").trim());
				ls.add(res.getString("KC_name").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        String sOut = "";
	         StackTraceElement[] trace = e.getStackTrace();
	         for (StackTraceElement s : trace) {
	             sOut += "\tat " + s + "\r\n";
	         }
			JOptionPane.showMessageDialog(null,"获取商品信息错误\n"+sOut);
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
