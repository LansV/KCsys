package data;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class wKC {

   	Statement sql=null;
   	ResultSet res=null;
   	ResultSet res2=null;
   	Dao d=new Dao();
   	Connection con = d.getcon();
	JLabel JLzl,JLgys,JLcp,JLsl,JLdw;
	JLabel Dw;
	JButton button;
	String szl,scp,jsr;
	Date date=new Date(); 
	String y=String.format("%tY", date);
	String m=String.format("%tm", date);
	String day=String.format("%td", date);
	String Sdate=y+"-"+m+"-"+day;       
	public void wKCc(){
		jsr="test";
		JFrame kcf=new JFrame();
		kcf.setTitle("库存添加");
		kcf.setLayout(null);
		kcf.setBounds(200,100,600,200);
		Container c=kcf.getContentPane();
		kcf.setResizable(false);
		JLzl=new JLabel("种类");
		JLzl.setBounds(75,10,60,25);
		JLcp=new JLabel("产品");
		JLcp.setBounds(230,10,60,25);
		JLsl=new JLabel("数量");
		JLsl.setBounds(385,10,60,25);
		JLdw=new JLabel("单位");
		JLdw.setBounds(530,10,60,25);
		JTextField jt=new JTextField();
		jt.setBounds(350,45,100,25);
		Dw=new JLabel("");
		Dw.setBounds(535,45,30,25);
		button=new JButton("添加库存");
		JComboBox<String> jczl=new JComboBox<String>();
		for(int i=0;i<getzl().size();i++){
			jczl.addItem(getzl().get(i));
		}
		JComboBox<String> jccp=new JComboBox<String>();
		for(int i=0;i<getcp().size();i++){
			jccp.addItem(getcp().get(i));
		}
		jccp.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					scp=(String) jccp.getSelectedItem();
					for(int i=0;i<getdw().size();i++){
						Dw.setText(getdw().get(i));
						System.out.println(getdw().get(i));
					}
					c.repaint();
				}
			}
			
		});
		jccp.setBounds(195,45,100,25);
		jczl.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					szl=(String) jczl.getSelectedItem();
					System.out.println(szl);
					jccp.removeAllItems();
					getcp().removeAll(getcp());
					for(int i=0;i<getcp().size();i++){
						jccp.addItem(getcp().get(i));
						System.out.println(getcp().get(i));
					}
					c.repaint();
				}
			}
			
		});
		jczl.setBounds(40,45,100,25);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int zlid=0;
				int id=0;
				Double dj=(double) 0;
				String zl=jczl.getSelectedItem().toString().trim();
				String cp=jccp.getSelectedItem().toString().trim();
				String sl=jt.getText().trim();
				String dw=Dw.getText().trim();
				String iu = null;
				int s=new Integer(sl);
				int kcsl=0;
				try{
					sql=con.createStatement();
					res=sql.executeQuery("select*from CPx where CPx_type = '"+zl+"' and CPx_name = '"+cp+"' ");
					while(res.next()){
						zlid=res.getInt("CPx_typeid");
						dj=res.getDouble("CPx_dj");
					}
					System.out.println(dj);
				}catch(Exception e1){
					
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
				if(zlid==0){
					JOptionPane.showMessageDialog(null,"信息不完整，请检查");
				}else{
					if(sl.length()==0){
						JOptionPane.showMessageDialog(null,"未填写入库数量");
					}else{
						try{
							sql = con.createStatement();
							res=sql.executeQuery("select*from KC where KC_name = '"+cp+"'");
							if(res.next()==false){
								iu="insert";
							}else{
								iu="updata";
								kcsl=res.getInt("KC_sl");
								System.out.println(kcsl);
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"得到库存数量失败");
						}
						try {
							sql = con.createStatement();
							res2=sql.executeQuery("select top 1 * from KC order by KC_id desc");
							if(res2.next()==false){
								//System.out.println("NO data");
								JOptionPane.showMessageDialog(null,"创建新数据！");
								id=1;
								sql.execute("insert into KC values("+zlid+","+id+",'"+cp+"',"+dj+","+s+",'"+dw+"','"+Sdate+"','"+jsr+"')");
							}else{
								if(iu.equals("insert")){
									id=res2.getInt("KC_id")+1;
									sql.execute("insert into KC values("+zlid+","+id+",'"+cp+"',"+dj+","+s+",'"+dw+"','"+Sdate+"','"+jsr+"')");
								}else{
									int sn=s+kcsl;
									sql.execute("UPDATE KC SET KC_sl="+sn+" where KC_name ='"+cp+"';UPDATE KC SET KC_date='"+Sdate+"' where KC_name ='"+cp+"'");
								}
							}
							jt.setText("");
							JOptionPane.showMessageDialog(null,"添加成功！");
							
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
							if(res2!=null){
								try {
									res2.close();
									res2=null;
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
			}
		});
		button.setBounds(480,120,100,25);
		c.add(setjsr());
		c.add(jt);
		c.add(jccp);
		c.add(jczl);
		c.add(JLdw);
		c.add(Dw);
		c.add(JLsl);
		c.add(JLcp);
		c.add(JLzl);
		c.add(button);
		kcf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		kcf.setVisible(true);
	}
	public static void main(String[] args){
		new wKC();
	}
	public List<String> getzl(){
		List<String> ls=new ArrayList<String>();
		String top="请选择种类";
		ls.add(top);
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from CPz order by CPz_typeid");
			while(res.next()){
				String zl= res.getString("CPz_type").trim();
				ls.add(zl);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"读取种类错误！\n请联系技术支持！\n软件将自动关闭！","错误",0);
			System.exit(0);
		}finally{
			if(res!=null){
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
			if(sql!=null){
				try {
					sql.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
		}
		return ls;
	}
	public List<String> getcp(){
		List<String> ls=new ArrayList<String>();
		String top="请选择种类";
		ls.add(top);
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from CPx where CPx_type = '"+szl+"'");
			while(res.next()){
				String cp= res.getString("CPx_name").trim();
				ls.add(cp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"读取产品错误！\n请联系技术支持！\n软件将自动关闭！","错误",0);
			System.exit(0);
		}finally{
			if(res!=null){
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
			if(sql!=null){
				try {
					sql.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
		}
		return ls;
	}
	public List<String> getdw(){
		List<String> ls=new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from CPx where CPx_name = '"+scp+"'");
			while(res.next()){
				String cp= res.getString("CPx_dw").trim();
				ls.add(cp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"读取产品错误！\n请联系技术支持！\n软件将自动关闭！","错误",0);
			System.exit(0);
		}finally{
			if(res!=null){
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
			if(sql!=null){
				try {
					sql.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查看断开数据失败");
				}
			}
		}
		return ls;
	}
	public JTextField setjsr(){
		JTextField jt=new JTextField(jsr);
		jt.setEditable(false);
		jt.setBounds(300,120,60,25);
		return jt;
	}
}
