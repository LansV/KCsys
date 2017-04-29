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
public class QueryAllOrderData{
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
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
			res = sql.executeQuery("select*from customerinfo where customerid ="+s);
			while(res.next()){
				ls.add(res.getString("customername").trim());
				ls.add(res.getString("contact").trim());
				ls.add(res.getString("tel").trim());
				ls.add(res.getString("address").trim());
				ls.add(res.getString("customerid").trim());
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
	public String[][] xys(String QueryName,String QueryNo,String QueryDate1,String QueryDate2){
		List<String> ls=new ArrayList<String>();
		if(QueryDate1.length()==0){
			QueryDate1="2000-1-1";
		}
		if(QueryDate2.length()==0){
			Date cDate=new Date();
			QueryDate2=String.format("%tF",cDate);
		}
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select dh,max(customerid) as khid,max(customer) as khmc, MAX(bdate) as bdate ,min(bstatus) as bs ,max(id) as id,max(username) as n from comfirmorder,UserB "
					+ "where  customer like '%"+QueryName+"%' "
					+ "and dh like '%"+QueryNo+"%' and bdate between '"+QueryDate1+"' and '"+QueryDate2+"' "
					+ "and comfirmorder.belong=UserB.id "
					+ "group by dh");
			while(res.next()){
				ls.add(res.getString("dh"));
				ls.add(res.getString("khid"));
				ls.add(res.getString("khmc"));
				ls.add(res.getString("bdate"));
				if(res.getInt("bs")==0){
					ls.add("������");
				}else{
					ls.add("�ѳ���");
				}
				ls.add(res.getString("id").trim());
				ls.add(res.getString("n").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        String sOut = "";
	         StackTraceElement[] trace = e.getStackTrace();
	         for (StackTraceElement s : trace) {
	             sOut += "\tat " + s + "\r\n";
	         }
			JOptionPane.showMessageDialog(null,"��ȡ���ݴ���\n"+sOut);
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
			res = sql.executeQuery("select*from"
					+ "(select dh,customerid,customer,bh,xh,sp,dw,zk,dj,sl,je,bz,bdate,bstatus,belong,xsdh,sl-KC_sl as kcstatus "
					+ "from comfirmorder left join KC on comfirmorder.xh=KC.KC_sbh) temp where dh='"+dh+"'");
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
				ls.add(res.getString("bz").trim());
				if(res.getInt("bstatus")==0){
					if(res.getInt("kcstatus")<=0){
						ls.add("�ɳ���");
					}else{
						ls.add("ȱ��:"+res.getString("kcstatus"));
					}
					ls.add("");
				}else{
					ls.add("�ѳ���");
					ls.add(res.getString("xsdh"));
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
		int xl=11;
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
					+ "("
					+ "select khmc,SUM(je) as zj,MAX(lastdate) as fdate from (select khmc,SUM(je)-SUM(skje) as je ,"
					+ "MAX (date) as lastdate from XSD group by khmc union select khmc,-SUM(tje) as je, "
					+ "Max(tdate) as lastdate from THD group by khmc ) temp group by khmc"
					+ ") temp where zj>0");
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
	//==============================================�������۵�=============================================
	public void insertSaleList(JFrame f,String odh,String dh,String custn,String custid,String[][] data,String user,String saleid,String saleman){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try {
			sql = con.createStatement();
			int co=data.length;
			for(int i=0;i<co;i++){
				sql.execute("insert into XSD values ('"+dh+"',"+custid+","
						+ "'"+custn+"',"+data[i][0]+",'"+data[i][1]+"','"+data[i][2]+"','"+data[i][3]+"',"
						+ ""+data[i][4]+","+data[i][5]+","+data[i][6]+","
						+ ""+data[i][7]+",'"+data[i][8]+"','"+ckd+"',0,0,'"+ckd+"',1,'"+user+"',"+saleid+",'"+saleman+"');"
						+ "update comfirmorder set bstatus = 1 where dh='"+odh+"' and xh="+data[i][1]+";"
						+ "update comfirmorder set xsdh = '"+dh+"' where dh='"+odh+"' and xh="+data[i][1]+";");
				wkcout(data[i][1],data[i][2],Integer.parseInt(data[i][6]),"1,"+dh,user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(f, "������۵�����");
		/*	StackTraceElement[] st=e.getStackTrace();
			String s="";
			for(StackTraceElement i:st){
				s=s+i.toString()+'\n';
			}
			System.out.println(s);
			JTextArea ta=new JTextArea();
			ta.setEditable(false);
			ta.setText(s);
			JScrollPane errorjsp=new JScrollPane(ta);
			errorjsp.setBounds(0,0,200,300);
			MyJOptionPane.showMessageDialog(f, errorjsp);*/
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		JOptionPane.showMessageDialog(f, "�Ͽ�����"); 
		     	 }
		}
	}
	//--------------------------------------------------------���³���-----------------------------------
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
		// �� 0               �� 1
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null,"�õ��������ʧ��");
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
			JOptionPane.showMessageDialog(null,"�������");
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
	//------------------------------------------------------�����˻�----------------------------------------
	public void gth(String dh,String khmc,int bh,String xh,String sp,String dw,Double zk,Double dj,int sl,Double thje,String yy,int type ){
		Date date2=new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String time=timeFormat.format(date2);
		String ckd=String.format("%tF", date2);
		// 0Ϊ�տ����  1Ϊδ�տ�  2��Ʒ�˻�
		try{
			sql = con.createStatement();
			sql.execute("insert into THD values ('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","
					+ ""+dj+","+sl+","+thje+",'"+yy+"','"+ckd+"','"+time+"',"+type+");"
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
