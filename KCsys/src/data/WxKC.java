package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class WxKC {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	Date date=new Date();		
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	/*	
		���¿��۸���Ϣ
	*/
	public void updateKC(String sbh,String c,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"����");
			}else{
				sql.execute("update KC set "+lx+"='"+c+"' where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�޸����ݴ���");
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
	public void updateKC(String sbh,Double j,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"����");
			}else{
				sql.execute("update KC set "+lx+"="+j+" where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�޸����ݴ���");
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
	/*	
	���¿�澯������
*/
	public void updateKC(String sbh,int sl,String lx){
		//String ckd=String.format("%tF",date);
		try{
			sql = con.createStatement();
			if(sbh.length()==0){
				JOptionPane.showMessageDialog(null,"����");
			}else{
				sql.execute("update KC set "+lx+"="+sl+" where KC_sbh="+sbh);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�޸����ݴ���");
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
