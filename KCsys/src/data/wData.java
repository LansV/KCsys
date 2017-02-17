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
	//---------------------------------------------添加供应商---------------------------------------------
	public void updategys(String name,String lxr,String tel,String fax,String add,int bh,String khh,String khm,String zh){
		try {
			sql = con.createStatement();
			sql.execute("update GYs set GYs_name='"+name+"',GYs_lxr='"+lxr+"',GYs_tel='"+tel+"',GYs_fax='"+fax+"'"
					+ ",GYs_adr='"+add+"',"
					+ "GYs_khh='"+khh+"',GYs_khm='"+khm+"',GYs_zh='"+zh+"' where GYs_bh="+bh);
			JOptionPane.showMessageDialog(null,"修改成功");
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
	//-------------------------------------------添加应付----------------------------------------
	public void addyf(String gys,Double jhj,int sl,String sp,String bh){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try {
			sql = con.createStatement();
			sql.execute("insert into YF values('"+gd.getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+bh+","+sl+","+jhj+")");
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
	//---------------------------------------------添加供应商---------------------------------------------
	public void waddgys(String name,String lxr,String tel,String fax,String add,String zl,int bh,String khh,String khm,String zh){
		try {
			sql = con.createStatement();
			sql.execute("insert into GYs values('"+name+"','"+lxr+"','"+tel+"','"+fax+"','"+add+"','"+zl+"',"+bh+",'"+khh+"','"+khm+"','"+zh+"')");
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
					+ "insert into YF values('"+gd.getyfdh()+"','"+gys+"',"+jhj*sl+",0,'"+ckd+"',0,'admin','"+sp+"',"+sbh+","+sl+","+jhj+")");
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
	//------------------------------------------------------订货全单退货----------------------------------------
	public void dhdth(String dh,String kh,Double je,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0为付款完成  1为未付款  2为坏账  3为全单退货 4为商品退货
		try{
			sql = con.createStatement();
			sql.execute("update YF set zt=3 where dh='"+dh+"';update YF set date='"+ckd+"' where dh='"+dh+"';"
					+ "insert into THB(dh,kh,je,zl,yz,date)values('"+dh+"','"+kh+"',"+je+",3,'"+yy+"','"+ckd+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加退货数据错误");
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
	//---------------------------------------------------更新应付------------------------------------------------
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
			JOptionPane.showMessageDialog(null,"更新应付数据错误");
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
	//--------------------------------------------采购退货-------------------------------------------------
	public void cgth(String dh){
		//-----------------0 不需要定 1 需要定 2 已经定
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
			JOptionPane.showMessageDialog(null,"获取商品数据错误！","错误",0);
		}
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update DH set zt=1 where sp = '"+sp+"';update DH set date=NULL where sp = '"+sp+"';"
					+ "update DH set c=NULL where sp = '"+sp+"'");
			JOptionPane.showMessageDialog(null,"已退货");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"更新订货数据错误！","错误",0);
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
	//--------------------------------------------收货-------------------------------------------------
	public void sh(String sp){
		//-----------------0 不需要定 1 需要定 2 已经定
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update KC set KC_state=0 where KC_name='"+sp+"'");
			JOptionPane.showMessageDialog(null,"已收货");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"更新订货数据错误！","错误",0);
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
	//--------------------------------------------更新订货-------------------------------------------------
	public void updatedh(String sp,int c){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//-----------------0 不需要定 1 需要定 2 已经定
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("update KC set KC_state=2 where KC_name='"+sp+"';update KC set KC_DHdate='"+ckd+"' where KC_name='"+sp+"';"
					+ "update KC set KC_DHsl="+c+" where KC_name='"+sp+"'");
			JOptionPane.showMessageDialog(null,"订货成功！");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"更新订货数据错误！","错误",0);
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
	//--------------------------------------------插入订货表-----------------------------------------------
	public void wdh(String sp,int sl){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		//-----------------0 不需要定 1 需要定 2 已经定
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
			JOptionPane.showMessageDialog(null,"订货获取单号错误");
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
			JOptionPane.showMessageDialog(null,"获取商品信息错误","错误",0);
		}
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into DHB values "
					  + "('"+dh+"','"+sp+"','"+gys+"',"+sl+","+jhj+","+sl*jhj+",'"+ckd+"','test')");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"更新订货数据错误！","错误",0);
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
	public void wkc(String xh,String sp,Double jhj,Double dj){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		String time=timef.format(date2);
		try {
			sql = con.createStatement();
			//System.out.println(id);
			sql.execute("insert into KC(KC_typeid,KC_type,KC_xh,KC_name,KC_jhj,KC_dj,KC_sl,KC_dw,KC_date,KC_jsr,KC_jgsl,KC_state,KC_sbh,KC_gys) "
					+ "values(1,'成品','"+xh+"','"+sp+"',"+jhj+","+dj+",1,'台','"+ckd+"','admin',0,0,"+gd.getspid(1)+",127);"
					+ "insert into KCJL values(1,'"+gd.getspid(1)+"','"+sp+"',1,'"+sp+"','admin','"+ckd+"','"+time+"','NULL')");
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
	//-------------------------------------------------写入组装-----------------------------------------
	public void wzz(String zm,int bh,String sbh,String sp,String dw,Double dj,int sl,Double je){
		try{
			sql = con.createStatement();
			sql.execute("insert into ZZB values('"+zm+"',"+bh+",'"+sbh+"','"+sp+"','"+dw+"',"+dj+","+sl+","+je+")");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加组装数据错误");
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
	//------------------------------------------------------退货----------------------------------------
	public void gth(String dh,int bh,String kh,String sp,int sl,int yysl,Double dj,Double dje,Double ysje,
			Double xsje,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0为收款完成  1为未收款  2为坏账  3为全单退货 4为商品退货
		try{
			sql = con.createStatement();
			int hsl=yysl-sl;
			if(ysje==0){
				sql.execute(  "update YSB set date='"+ckd+"' where dh='"+dh+"';"  //修改应收修改时间
						+ "update YSB set zj="+ysje+" where dh='"+dh+"';"     //修改应收金额
						+ "update YSB set zt=4 where dh='"+dh+"';"
						+ "update XSD set sl="+hsl+" where dh='"+dh+"' and bh='"+bh+"';"  //修改销售单数量
						+ "update XSD set je='"+xsje+"' where dh='"+dh+"' and bh='"+bh+"';"   //修改销售单金额
						+ "update XSD set date='"+ckd+"' where dh='"+dh+"' and bh='"+bh+"';"  //修改销售单修改时间
						+ "insert into THB(dh,kh,sp,sl,dj,je,zl,yz,date)values"      //插入THB
						+ "('"+dh+"','"+kh+"','"+sp+"',"+sl+","+dj+","+dje+",4,'"+yy+"','"+ckd+"')");
			}else{
				sql.execute(  "update YSB set date='"+ckd+"' where dh='"+dh+"';"  //修改应收修改时间
						+ "update YSB set zj="+ysje+" where dh='"+dh+"';"     //修改应收金额
						+ "update XSD set sl="+hsl+" where dh='"+dh+"' and bh='"+bh+"';"  //修改销售单数量
						+ "update XSD set je='"+xsje+"' where dh='"+dh+"' and bh='"+bh+"';"   //修改销售单金额
						+ "update XSD set date='"+ckd+"' where dh='"+dh+"' and bh='"+bh+"';"  //修改销售单修改时间
						+ "insert into THB(dh,kh,sp,sl,dj,je,zl,yz,date)values"      //插入THB
						+ "('"+dh+"','"+kh+"','"+sp+"',"+sl+","+dj+","+dje+",4,'"+yy+"','"+ckd+"')");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加退货数据错误");
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
	//------------------------------------------------------全单退货----------------------------------------
	public void dth(String dh,String kh,Double je,String yy){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		// 0为收款完成  1为未收款  2为坏账  3为全单退货 4为商品退货
		try{
			sql = con.createStatement();
			sql.execute("update YSB set zt=3 where dh='"+dh+"';update YSB set date='"+ckd+"' where dh='"+dh+"';"
					+ "insert into THB(dh,kh,je,zl,yz,date)values('"+dh+"','"+kh+"',"+je+",3,'"+yy+"','"+ckd+"')");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加退货数据错误");
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
	//---------------------------------------------------更新应收------------------------------------------------
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
			JOptionPane.showMessageDialog(null,"更新应收数据错误");
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
	//-------------------------------------------------------写入应收----------------------------------------------
	public void wys(String dh,String kh,Double hj){
		Date date2=new Date();
		String ckd=String.format("%tF", date2);
		try{
			sql = con.createStatement();
			sql.execute("insert into YSB(dh,kh,zj,ys,date,zt) values('"+dh+"','"+kh+"',"+hj+",0,'"+ckd+"',1)");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"添加应收数据错误");
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
	//--------------------------------------------------------更新入库-----------------------------------
	public void wkcin(String sbh,String sckcp,int sn,String ly,String user,String dh){
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
					  + "insert into KCJL values(1,'"+sbh+"','"+sckcp+"',"+sn+",'"+ly+"','"+user+"','"+ckd+"','"+time+"','"+dh+"')");
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
		// 出 0               进 1
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null,"得到库存数量失败");
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
			JOptionPane.showMessageDialog(null,"出库错误");
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
	//--------------------------------------------------添加客户信息---------------------------------------------------
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
			JOptionPane.showMessageDialog(null,"获取客户数据错误！","错误",0);
		}
		int ids=sp+1;
		try{
			sql = con.createStatement();
			sql.execute("insert into KHx values("+ids+",'"+mc+"','"+lxr+"','"+lxtel+"','','"+add+"')");
			JOptionPane.showMessageDialog(null,"客户添加完成");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"客户添加错误");
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
	//-------------------------------------------------写入维修---------------------------------------------
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
			JOptionPane.showMessageDialog(null,"错误");
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
	//-------------------------------------------------写入销售单---------------------------------------------
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
			JOptionPane.showMessageDialog(null,"添加销售单错误");
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
		return b;
	}
//-------------------------------------------------写入内部单---------------------------------------------
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
		JOptionPane.showMessageDialog(null,"错误");
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
	return b;
}
}
