package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
public class wData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
   	getData gd=new getData();
	Connection con = d.getcon();	
	SimpleDateFormat timef= new SimpleDateFormat("HH:mm:ss");
	//---------------------------------------------��ӹ�Ӧ��---------------------------------------------
	public void updategys(String name,String lxr,String tel,String fax,String add,int bh,String khh,String khm,String zh){
		try {
			sql = con.createStatement();
			sql.execute("update GYs set GYs_name='"+name+"',GYs_lxr='"+lxr+"',GYs_tel='"+tel+"',GYs_fax='"+fax+"'"
					+ ",GYs_adr='"+add+"',"
					+ "GYs_khh='"+khh+"',GYs_khm='"+khm+"',GYs_zh='"+zh+"' where GYs_bh="+bh);
			JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"д�����ݴ���","����",0);
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
	//-------------------------------------------���Ӧ��----------------------------------------
	public void addyf(String gys,Double jhj,int sl,String sp,String bh){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try {
			sql = con.createStatement();
			sql.execute("insert into YF values('"+gd.getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+bh+","+sl+","+jhj+")");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"д�����ݴ���","����",0);
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
	//---------------------------------------------��ӹ�Ӧ��---------------------------------------------
	public void waddgys(String name,String lxr,String tel,String fax,String add,String zl,int bh,String khh,String khm,String zh){
		try {
			sql = con.createStatement();
			sql.execute("insert into GYs values('"+name+"','"+lxr+"','"+tel+"','"+fax+"','"+add+"','"+zl+"',"+bh+",'"+khh+"','"+khm+"','"+zh+"')");
			JOptionPane.showMessageDialog(null,"��ӳɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"д�����ݴ���","����",0);
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
	//---------------------------------------------д����------------------------------------------------
	public void waddkc(int zlid,String zl,String xh,String sp,Double jhj,Double dj,int sl,String dw,int sbh,int gys){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_xh,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values("+zlid+",'"+zl+"','"+xh+"','"+sp+"',"+jhj+","+dj+","+sl+",'"+dw+"','"+ckd+"','admin',0,0,"+sbh+","+gys+");"
					+ "insert into KCJL values(1,'"+sbh+"','"+sp+"',"+sl+",'���','admin','"+ckd+"','"+time+"','�����Ʒ');"
					+ "insert into YF values('"+gd.getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+sbh+","+sl+","+jhj+")");
			JOptionPane.showMessageDialog(null,"��ӳɹ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"д�����ݴ���","����",0);
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
	//------------------------------------------------------����ȫ���˻�----------------------------------------
	public void dhdth(String dh,String kh,Double je,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0Ϊ�������  1Ϊδ����  2Ϊ����  3Ϊȫ���˻� 4Ϊ��Ʒ�˻�
		try{
			sql = con.createStatement();
			sql.execute("update YF set zt=3 where dh='"+dh+"';update YF set date='"+ckd+"' where dh='"+dh+"';"
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
	public void updateyf(String dh,Double ys,int zt){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			if(zt==1){
				sql.execute("update YF set ys="+ys+" where dh= '"+dh+"';update YF set date='"+ckd+"' where dh ='"+dh+"'"
						+ "update YF set zt="+zt+" where dh ='"+dh+"'");
			}else{
				sql.execute("update YF set ys="+ys+" where dh ='"+dh+"';update YF set date='"+ckd+"' where dh ='"+dh+"'");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"����Ӧ�����ݴ���");
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
	//--------------------------------------------�ɹ��˻�-------------------------------------------------
	public void cgth(String dh){
		//-----------------0 ����Ҫ�� 1 ��Ҫ�� 2 �Ѿ���
		String sp="";
		try {
			sql = con.createStatement();
			//System.out.println(id);
			res=sql.executeQuery("select sp from DHB where dh = '"+dh+"'");
			while(res.next()){
				sp=res.getString("sp").trim();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ��Ʒ���ݴ���","����",0);
		}
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update DH set zt=1 where sp = '"+sp+"';update DH set date=NULL where sp = '"+sp+"';"
					+ "update DH set c=NULL where sp = '"+sp+"'");
			JOptionPane.showMessageDialog(null,"���˻�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"���¶������ݴ���","����",0);
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
	//--------------------------------------------�ջ�-------------------------------------------------
	public void sh(String sp){
		//-----------------0 ����Ҫ�� 1 ��Ҫ�� 2 �Ѿ���
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update KC set KC_state=0 where KC_name='"+sp+"'");
			JOptionPane.showMessageDialog(null,"���ջ�");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"���¶������ݴ���","����",0);
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
	//--------------------------------------------���¶���-------------------------------------------------
	public void updatedh(String sp,int c){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//-----------------0 ����Ҫ�� 1 ��Ҫ�� 2 �Ѿ���
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update KC set KC_state=2 where KC_name='"+sp+"';update KC set KC_DHdate='"+ckd+"' where KC_name='"+sp+"';"
					+ "update KC set KC_DHsl="+c+" where KC_name='"+sp+"'");
			JOptionPane.showMessageDialog(null,"�����ɹ���");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"���¶������ݴ���","����",0);
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
	//--------------------------------------------���붩����-----------------------------------------------
	public void wdh(String sp,int sl){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//-----------------0 ����Ҫ�� 1 ��Ҫ�� 2 �Ѿ���
		String dh="";
		String y=String.format("%ty",date2);
		String m=String.format("%tm",date2);
		String s="D"+y+m;
		String st=null;
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max(dh) as dh from DHB where dh like '"+s+"%'");
				while(res.next()){
					st=res.getString("dh");
					if(st==null){
						dh=s+"001";
					}else{
						st=res.getString("dh").trim();
						String stl=st.substring(st.length()-3,st.length());
						int i=Integer.parseInt(stl);
						i=i+1;
						String z=Integer.toString(i);
						if(z.length()==1){
							dh=s+"00"+z;
						}else if(z.length()==2){
							dh=s+"0"+z;
						}else{
							dh=s+z;
						}
					}
				}
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"������ȡ���Ŵ���");
		}
		String gys = "";
		Double jhj = 0.0;
		try {
			sql = con.createStatement();
			//System.out.println(id);
			res=sql.executeQuery("select CPx_gys,CPx_jhj from CPx where CPx_name = '"+sp+"'");
			while(res.next()){
				gys=res.getString("CPx_gys").trim();
				jhj=res.getDouble("CPx_jhj");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ��Ʒ��Ϣ����","����",0);
		}
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into DHB values "
					  + "('"+dh+"','"+sp+"','"+gys+"',"+sl+","+jhj+","+sl*jhj+",'"+ckd+"','test')");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"���¶������ݴ���","����",0);
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
	//---------------------------------------------д����------------------------------------------------
	public void wkc(String xh,String sp,Double jhj,Double dj){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_xh,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values(1,'��Ʒ','"+xh+"','"+sp+"',"+jhj+","+dj+",1,'̨','"+ckd+"','admin',0,0,"+gd.getspid(1)+",127);"
					+ "insert into KCJL values(1,'"+gd.getspid(1)+"','"+sp+"',1,'"+sp+"','admin','"+ckd+"','"+time+"','NULL')");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"д�����ݴ���","����",0);
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
	//-------------------------------------------------д����װ-----------------------------------------
	public void wzz(String zm,int bh,String sbh,String sp,String dw,Double dj,int sl,Double je){
		try{
			sql = con.createStatement();
			sql.execute("insert into ZZB values('"+zm+"',"+bh+",'"+sbh+"','"+sp+"','"+dw+"',"+dj+","+sl+","+je+")");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�����װ���ݴ���");
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
	//------------------------------------------------------�˻�----------------------------------------
	public void gth(String dh,int bh,String kh,String sp,int sl,int yysl,Double dj,Double dje,Double ysje,
			Double xsje,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0Ϊ�տ����  1Ϊδ�տ�  2Ϊ����  3Ϊȫ���˻� 4Ϊ��Ʒ�˻�
		try{
			sql = con.createStatement();
			int hsl=yysl-sl;
			if(ysje==0){
				sql.execute(  "update YSB set date='"+ckd+"' where dh='"+dh+"';"  //�޸�Ӧ���޸�ʱ��
						+ "update YSB set zj="+ysje+" where dh='"+dh+"';"     //�޸�Ӧ�ս��
						+ "update YSB set zt=4 where dh='"+dh+"';"
						+ "update XSD set sl="+hsl+" where dh='"+dh+"' and bh='"+bh+"';"  //�޸����۵�����
						+ "update XSD set je='"+xsje+"' where dh='"+dh+"' and bh='"+bh+"';"   //�޸����۵����
						+ "update XSD set date='"+ckd+"' where dh='"+dh+"' and bh='"+bh+"';"  //�޸����۵��޸�ʱ��
						+ "insert into THB(dh,kh,sp,sl,dj,je,zl,yz,date)values"      //����THB
						+ "('"+dh+"','"+kh+"','"+sp+"',"+sl+","+dj+","+dje+",4,'"+yy+"','"+ckd+"')");
			}else{
				sql.execute(  "update YSB set date='"+ckd+"' where dh='"+dh+"';"  //�޸�Ӧ���޸�ʱ��
						+ "update YSB set zj="+ysje+" where dh='"+dh+"';"     //�޸�Ӧ�ս��
						+ "update XSD set sl="+hsl+" where dh='"+dh+"' and bh='"+bh+"';"  //�޸����۵�����
						+ "update XSD set je='"+xsje+"' where dh='"+dh+"' and bh='"+bh+"';"   //�޸����۵����
						+ "update XSD set date='"+ckd+"' where dh='"+dh+"' and bh='"+bh+"';"  //�޸����۵��޸�ʱ��
						+ "insert into THB(dh,kh,sp,sl,dj,je,zl,yz,date)values"      //����THB
						+ "('"+dh+"','"+kh+"','"+sp+"',"+sl+","+dj+","+dje+",4,'"+yy+"','"+ckd+"')");
			}
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
	public void updateys(String dh,Double ys,int zt){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			if(zt==0){
				sql.execute("update YSB set ys="+ys+" where dh like '"+dh+"';update YSB set date='"+ckd+"' where dh like '"+dh+"'"
						+ "update YSB set zt="+zt+" where dh like '"+dh+"'");
			}else{
				sql.execute("update YSB set ys="+ys+" where dh like '"+dh+"';update YSB set date='"+ckd+"' where dh like '"+dh+"'");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"����Ӧ�����ݴ���");
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
	//-------------------------------------------------------д��Ӧ��----------------------------------------------
	public void wys(String dh,String kh,Double hj){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into YSB(dh,kh,zj,ys,date,zt) values('"+dh+"','"+kh+"',"+hj+",0,'"+ckd+"',1)");
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
	//--------------------------------------------------------�������-----------------------------------
	public void wkcin(String sbh,String sckcp,int sn,String ly,String user,String dh){
		Date date2=new Date();
		int kcsl = 0;
		int jg = 0;
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		//��ȡ�޸�֮ǰ�Ŀ������
		try{
			sql = con.createStatement();
			res=sql.executeQuery("select*from KC where KC_sbh = "+sbh+"");
			while(res.next()){
				kcsl=res.getInt("KC_sl");
			}
			jg=kcsl+sn;
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null,"�õ��������ʧ��");
		}
		try{
			sql = con.createStatement();
			sql.execute("UPDATE KC SET KC_sl="+jg+" where KC_sbh ="+sbh+";"
					  + "UPDATE KC SET KC_date = '"+ckd+"' where KC_sbh = "+sbh+";"
					  + "insert into KCJL values(1,'"+sbh+"','"+sckcp+"',"+sn+",'"+ly+"','"+user+"','"+ckd+"','"+time+"','"+dh+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"д�������");
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
	//--------------------------------------------------��ӿͻ���Ϣ---------------------------------------------------
	public void wkh(String mc,String lxr,String lxtel,String add){
		int  sp=0;
		try {
			sql = con.createStatement();
			//System.out.println(id);
			res=sql.executeQuery("select max(KH_id) as id from KHx");
			while(res.next()){
				sp=res.getInt("id");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"��ȡ�ͻ����ݴ���","����",0);
		}
		int ids=sp+1;
		try{
			sql = con.createStatement();
			sql.execute("insert into KHx values("+ids+",'"+mc+"','"+lxr+"','"+lxtel+"','','"+add+"')");
			JOptionPane.showMessageDialog(null,"�ͻ�������");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�ͻ���Ӵ���");
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
	//-------------------------------------------------д��ά��---------------------------------------------
	public void wx(String dh,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int skfs,String user) {
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into WXD values"
					+ "('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ","+skfs+",0,'"+ckd+"',1,'"+user+"')");
		}catch(Exception e){
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
		     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
		     	 }
		}
	}
	//-------------------------------------------------д�����۵�---------------------------------------------
	public Boolean wxs(String dh,String khmc,int bh, String xh, String sp, String dw, Double zk,
			Double dj, int sl, Double je, String bz,int skfs,String user) {
		Boolean b=true;
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into XSD values"
					+ "('"+dh+"','"+khmc+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+zk+","+dj+","+sl+","+je+",'"+bz+"','"+ckd+"'"
					+ ","+skfs+",0,'"+ckd+"',1,'"+user+"')");
		}catch(Exception e){
			b=false;
			JOptionPane.showMessageDialog(null,"������۵�����");
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
		return b;
	}
//-------------------------------------------------д���ڲ���---------------------------------------------
public Boolean wnbc(String dh,int bh, String xh, String sp, String dw,int sl,String bz) {
	Boolean b=true;
	Date date2=new Date();
	String ckd=String.format("%tF", date2);
	try{
		sql = con.createStatement();
		sql.execute("insert into NBC values"
				+ "('"+dh+"',"+bh+",'"+xh+"','"+sp+"','"+dw+"',"+sl+",'"+ckd+"','admin','"+bz+"')");
	}catch(Exception e){
		b=false;
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
	     		 JOptionPane.showMessageDialog(null,"�Ͽ�����");
	     	 }
	}
	return b;
}
}
