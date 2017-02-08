package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
public class getData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	//-----------------------------------------------��ȡӦ������-------------------------------------
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
	//-----------------------------------------------��ȡ��Ʒ���---------------------------------------
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
	//------------------------------------------------��ȡ��ѯ��Ӧ��------------------------------------
	public List<String> getcxgys(int cx){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from GYs,CPz where GYs.GYs_bh="+cx+" and GYs.GYs_zl=CPz.CPz_typeid");
			while(res.next()){
				ls.add(res.getString("CPz_typeid").trim());
				ls.add(res.getString("CPz_type").trim());
				ls.add(res.getString("GYs_bh").trim());
				ls.add(res.getString("GYs_name").trim());
				ls.add(res.getString("GYs_lxr").trim());
				ls.add(res.getString("GYs_tel").trim());
				if(res.getString("GYs_fax")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_fax").trim());
				}
				if(res.getString("GYs_adr")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_adr").trim());
				}
				if(res.getString("GYs_khh")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_khh").trim());
				}
				if(res.getString("GYs_khm")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_khm").trim());
				}
				if(res.getString("GYs_zh")==null){
					ls.add("");
				}else{
					ls.add(res.getString("GYs_zh").trim());
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
		return ls;
	}
	//------------------------------------------------��ȡ��Ӧ�̱��------------------------------------
	public String getgysid(String cx){
		String s="";
		int i=0;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(GYs_bh) as bh from GYs where GYs_zl = '"+cx+"'");
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
			s=cx+"001";
		}else{
			s=Integer.toString(i+1);
		}
		return s;
	}
	//------------------------------------------------��ȡ��Ӧ��----------------------------------------
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//--------------------------------------------------------��ȡ����-----------------------------------
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//--------------------------------------------------------��ȡָ���ͻ�Ӧ��-----------------------------------
	public String[][] xyf(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select dh,sp,zj,ys,zj-ys as s,date,sl,bh,jhj from YF where gys ='"+s+"'");
			while(res.next()){
				if(res.getDouble("s")==0){
					
				}else{
					ls.add(res.getString("dh").trim());
					ls.add(res.getString("bh").trim());
					ls.add(res.getString("sp").trim());
					ls.add(res.getString("sl").trim());
					ls.add(String.format("%.3f",res.getDouble("jhj")));
					ls.add(String.format("%.3f",res.getDouble("zj")));
					ls.add(String.format("%.3f",res.getDouble("ys")));
					ls.add(String.format("%.3f",res.getDouble("s")));
					if(res.getString("date")==null){
						ls.add(res.getString("date"));
					}else{
						ls.add(res.getString("date").trim());
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
	//---------------------------------------------------��ȡ�ܼ�Ӧ��--------------------------------
	public String[][] yf(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select bh,zj,date,GYs_name as gys from (select gys as bh,sum(zj-ys) as zj,MAX(date)"
					+ " as date from YF where YF.zt=0 group by gys ) a left join GYs on GYs.GYs_bh=a.bh");
			while(res.next()){
				if(res.getDouble("zj")==0){
					
				}else{
					ls.add(res.getString("bh").trim());
					ls.add(res.getString("gys").trim());
					ls.add(String.format("%.3f",res.getDouble("zj")));
					if(res.getString("date")==null){
						ls.add(res.getString("date"));
					}else{
						ls.add(res.getString("date").trim());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡӦ������");
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
	//--------------------------------------------------------�ж��Ƿ񶩻�-------------------------------------
	public String[][] getdhsp(){
		//-----------------0 ����Ҫ�� 1 ��Ҫ�� 2 �Ѿ���
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_state>0");
			while(res.next()){
				ls.add(res.getString("KC_name").trim());
				if(res.getInt("KC_state")==1){
					ls.add("�趩");
					ls.add("");
					ls.add("");
				}else{
					ls.add("�Ѷ�");
					String date=res.getString("KC_DHdate");
					if(date==null){
						ls.add("����");
					}else{
						ls.add(date);
					}
					String sl=res.getString("KC_DHsl");
					if(sl==null){
						ls.add("����");
					}else{
						ls.add(sl);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ����״̬����");
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
	//------------------------------------------------------------�ж���װ���-----------------------------------
	public String[][] zpdkc(String zm,int c){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select sp,sl,xh,zt=case "
					+ "when KC.KC_sl>=ZZB.sl*"+c+" then 0 "
					+ "when KC.KC_sl<ZZB.sl*"+c+" then ABS(KC.KC_sl-ZZB.sl*"+c+") "
					+ "end from KC,ZZB "
					+ "where KC.KC_name=ZZB.sp and zm='"+zm+"' order by bh");
			while(res.next()){
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("sl").trim());
				ls.add(res.getString("zt").trim());
				ls.add(res.getString("xh").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ�������");
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
	//------------------------------------------------------------��ȡ����-----------------------------------
	public String[][] zm(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select DISTINCT(KC_name),KC_sbh from KC,ZZB  where KC.KC_name=ZZB.zm ");
			while(res.next()){
				ls.add(res.getString("KC_name").trim());
				ls.add(res.getString("KC_sbh").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ��������");
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//------------------------------------------------------------�жϿ���Ƿ����-----------------------------------
	public int pdkc(String sp){
		//1 update 0 insert
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select KC_name from KC where KC_name = '"+sp+"'");
			while(res.next()){
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"���Աȳ���");
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
		return 0;
	}
	//------------------------------------------------------------��װ��¼-----------------------------------
	public List<String> zzjl(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct zm from ZZB");
			while(res.next()){
				ls.add(res.getString("zm").trim());
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
	//----------------------------------------------------------------��ȡά�޵�--------------------------------
	public String[][] wxd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select bh,xh,sp,dw,zk,dj,sl,je,bz from WXB where dh = '"+dh+"' and sl !=0 order by bh");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add(res.getString("zk"));
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
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
	//----------------------------------------------------------------��ȡ���۵�--------------------------------
	public String[][] xsd(String dh){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select bh,xh,sp,dw,zk,dj,sl,je,bz from XSD where dh = '"+dh+"' and sl !=0 order by bh");
			while(res.next()){
				ls.add(res.getString("bh").trim());
				ls.add(res.getString("xh").trim());
				ls.add(res.getString("sp").trim());
				ls.add(res.getString("dw").trim());
				if(res.getString("zk")==null){
					ls.add(res.getString("zk"));
				}else{
					ls.add(res.getString("zk").trim());	
				}
				ls.add(String.format("%.2f",res.getDouble("dj")));
				ls.add(res.getString("sl").trim());
				ls.add(String.format("%.2f",res.getDouble("je")));
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
	//--------------------------------------------------------��ȡָ���ͻ�Ӧ��-----------------------------------
	public String[][] xys(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select dh,kh,zj,ys,zj-ys as s,date from YSB where kh like '%"+s+"%' and zt=1 order by dh");
			while(res.next()){
				if(res.getDouble("s")==0){
					
				}else{
					ls.add(res.getString("dh").trim());
					ls.add(res.getString("kh").trim());
					ls.add(String.format("%.2f",res.getDouble("zj")));
					ls.add(String.format("%.2f",res.getDouble("ys")));
					ls.add(String.format("%.2f",res.getDouble("s")));
					if(res.getString("date")==null){
						ls.add(res.getString("date"));
					}else{
						ls.add(res.getString("date").trim());
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
	//---------------------------------------------------��ȡ�ܼ�Ӧ��--------------------------------
	public String[][] ys(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select kh,SUM(zj-ys) as zj,MAX(date) as date from YSB where zt=1 group by kh");
			while(res.next()){
				if(res.getDouble("zj")==0){
					
				}else{
					ls.add(res.getString("kh").trim());
					ls.add(String.format("%.2f",res.getDouble("zj")));
					if(res.getString("date")==null){
						ls.add(res.getString("date"));
					}else{
						ls.add(res.getString("date").trim());
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
	//-----------------------------------------------------ά�޻�ȡ����-------------------------------------------
	public String wxdh(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="X"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from WXB where dh like '"+s+"%'");
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
			JOptionPane.showMessageDialog(null,"����");
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
	
	//-----------------------------------------------------NBC��ȡ����-------------------------------------------
	public String nbcdh(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="N"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from NBC where dh like '"+s+"%'");
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
			JOptionPane.showMessageDialog(null,"����");
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
	
	//-----------------------------------------------------XS��ȡ����-------------------------------------------
	public String xsdh(){
		String ls="";
		Date d=new Date();
		String y=String.format("%ty",d);
		String m=String.format("%tm",d);
		String s="K"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from XSD where dh like '"+s+"%'");
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
			JOptionPane.showMessageDialog(null,"����");
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
	
	//-----------------------------------------�ͻ���ѯ---------------------------
	public String[][] khx(String s){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KHx where KH_name like '%"+s+"%' or KH_add like '%"+s+"%' or "
					+ "KH_llx like '%"+s+"%'");
			while(res.next()){
				ls.add(res.getString("KH_name").trim());
				ls.add(res.getString("KH_llx").trim());
				ls.add(res.getString("KH_tel").trim());
				ls.add(res.getString("KH_add").trim());
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
	//---------------------------------------------------��Ʒ��ѯ������--------------------------
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
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
	//-------------------------------------------------��ȡ���п����Ϣ-------------------------------------------
	public String[][] KCdata(String cx){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select KC_typeid,KC_type,KC_id,GYs_name,KC_name,KC_jhj,KC_fxj,KC_jxj,KC_dj,KC_sl,KC_dw,KC_date,KC_jgsl,KC_sbh,KC_gys from KC,GYs where KC_gys=GYs_bh and KC_name like '%"+cx+"%' order by KC_typeid");
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
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
	//-----------------------------------------------��������ѯ--------------------------------------------
	public String[][] sxKCdata(String sx){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_type = '"+sx+"' order by KC_typeid");
			while(res.next()){
				ls.add(res.getString("KC_name").trim());
				ls.add(res.getString("KC_sl").trim());
				ls.add(res.getString("KC_dw").trim());
				ls.add(res.getString("KC_date").trim());
				ls.add(res.getString("KC_jsr").trim());
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
	//select*from KC where KC_sl<=KC_jgsl
	//---------------------------------------�ж϶�����-----------------------------------------------
	public List<String> ls(){
		List<String> ls=new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_sl<=KC_jgsl");
			while(res.next()){
				ls.add(res.getString("KC_name").trim());
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
	public Integer jgs(String n){
		int i = 0;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from KC where KC_name = '"+n+"'");
			while(res.next()){
				i=res.getInt("KC_jgsl");
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
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
		return i;
	}
	
}
