package data;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class wZL extends JFrame{ //写入种类信息进入数据库	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   	Statement sql=null;
   	ResultSet res=null;
   	ResultSet res2=null;
   	Dao d=new Dao();
   	Connection con = d.getcon();
   	List<String> select;
	public wZL(){
		super();
		setTitle("种类添加");
		setBounds(500,200,240,400);
		setLayout(null);
		setResizable(false);
		JPanel mainPanel=new JPanel();
		JPanel topPanel=new JPanel();
		JPanel downPanel=new JPanel();
		JPanel jspJP=new JPanel();
		jspJP.setBounds(0,0,240, 220);
		jspJP.setLayout(null);
		JPanel buttonJP=new JPanel();
		buttonJP.setLayout(null);
		buttonJP.setBounds(0,220,240,60);
		JScrollPane jsp=new JScrollPane();
		jsp.setBounds(0,0,225,200);
		jsp.getViewport().setView(data());
		//jsp.add(Label1);
		jspJP.add(jsp);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,240,400);
		topPanel.setLayout(null);
		topPanel.setBounds(0,0,240,100);
		downPanel.setBounds(0,110,240,290);
		downPanel.setLayout(null);
		JLabel Label=new JLabel("填写种类:");
		Label.setBounds(30,20,55,25);
		JTextField Text=new JTextField();
		Text.setBounds(95,20,95,25);
		JButton Button=new JButton("添加");
		Button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id;
				String zl=Text.getText().trim();
				if(zl.length()==0){
					JOptionPane.showMessageDialog(null, "种类不能为空值！","错误",0);
				}else{
					try {
						sql = con.createStatement();
						res2=sql.executeQuery("select top 1 * from CPz order by CPz_typeid desc");
						/*if(res2.getInt("CPz_typeid")==0||res2.getString("CPz_typeid")=="NULL"){
							id=1;
						}else{
							id=res2.getInt("CPz_typeid")+1;
							res =sql.executeQuery("insert into CPz values("+id+",'"+zl+"')");
						}*/
						if(res2.next()==false){
							JOptionPane.showMessageDialog(null,"创建新数据！");
							id=1;
							sql.execute("insert into CPz values("+id+",'"+zl+"')");
						}else{
							id=res2.getInt("CPz_typeid")+1;
							sql.execute("insert into CPz values("+id+",'"+zl+"')");
						}
						jsp.getViewport().setView(data());
						JOptionPane.showMessageDialog(null,"添加成功！");
						Text.setText("");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"写入数据错误！","错误",0);
					}finally{
						if(res!=null){
							try {
								res.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"添加断开数据连接错误");
							}
						}
						if(res2!=null){
							try {
								res2.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"添加断开数据连接错误");
							}
						}
						if(sql!=null){
							try {
								sql.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"添加断开数据连接错误");
							}
						}
					}
					
				}
			}		
		});
		Text.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()=='\n'){
					Button.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton Button2=new JButton("修改");
		Button2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(select!=null){
					if(select.size()!=0){
						for(int i=0;i<select.size();i++){
						}
						select.removeAll(select);
						jsp.getViewport().setView(data());
					}else{
						JOptionPane.showMessageDialog(null,"未选中目标！\n不能修改！");
					}
				}else{
					JOptionPane.showMessageDialog(null,"未选中目标！\n不能修改！");
				}
				
			}
		});
		JButton Button3=new JButton("删除");
		Button3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String delzl=null;
				if(select!=null){
					if(select.size()!=0){
						for(int i=0;i<select.size();i++){
							try{
								sql=con.createStatement();
								res=sql.executeQuery("select*from CPx where CPx_type = '"+select.get(i)+"'");
								if(res.next()==true){
									delzl="Y";
								}else{
									delzl="N";
								}
							}catch(Exception e1){
								
							}
							if(delzl.equals("Y")){
								JOptionPane.showMessageDialog(null,"不能删除此项，请删除产品后重试！");
							}else{
								try {
									sql=con.createStatement();
									sql.execute("delete from CPz where CPz_type = '"+select.get(i)+"'");
									if(i==select.size()-1){
										jsp.getViewport().setView(data());
										select.removeAll(select);
										JOptionPane.showMessageDialog(null,"删除成功！");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,"删除错误！","错误",0);
								}finally{
									if(res!=null){
										try {
											res.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"查看断开数据失败");
										}
									}
									if(sql!=null){
										try {
											sql.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"查看断开数据失败");
										}
									}
								}
							}

						}
					}else{
						JOptionPane.showMessageDialog(null,"未选中目标！\n不能删除！");
					}
				}else{
					JOptionPane.showMessageDialog(null,"未选中目标！\n不能删除！");
				}
			}
			
		});
		Button3.setBounds(115,0,60,25);
		Button2.setBounds(45,0,60,25);
		Button.setBounds(81,60,60,25);
		buttonJP.add(Button2);
		buttonJP.add(Button3);
		topPanel.add(Button);
		topPanel.add(Text);
		topPanel.add(Label);
		downPanel.add(jspJP);
		downPanel.add(buttonJP);
		mainPanel.add(topPanel);
		mainPanel.add(downPanel);
		Container c=getContentPane();
		c.add(mainPanel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		setVisible(true);
	}
	public static void main(String[] args){
		new wZL();
	}
	public JPanel data(){
		List<String> ls=new ArrayList<String>();
		List<String> sel=new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from CPz order by CPz_typeid");
			while(res.next()){
				ls.add(res.getString("CPz_type"));
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
		JPanel data=new JPanel();
		int hight;
		int x,y;
		x=ls.size()/8;  //取整数
		y=ls.size()%8; //取余数
		
		if(y>0){
			x=x+1;
		}
		hight=ls.size()*45;
		//x=x*4;
		data.setLayout(new GridLayout(ls.size(),2,3,3));
		JLabel label[];
		label=new JLabel[ls.size()];
		//int s=label.length;
		for(int i=0;i<ls.size();i++){
			label[i]=new JLabel(ls.get(i),JLabel.CENTER);
			label[i].setName("Label"+i);
			label[i].setOpaque(true);
			label[i].setBackground(Color.GRAY);
			int sn=i;
			label[i].addMouseListener(new MouseListener(){
				int i=sn;
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(label[i].getBackground()==Color.blue){
						String qc=label[i].getText().trim();
						for(int i=0;i<sel.size();i++){
							String qca=sel.get(i);
							if(qc.equals(qca)==true){
								sel.remove(i);
							}
						}
						setls(sel);
						label[i].setOpaque(true);
						label[i].setBackground(Color.gray);
					}else{
						label[i].setOpaque(true);
						label[i].setBackground(Color.blue);	
						String lab=label[i].getText().trim();
						sel.add(lab);
						setls(sel);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					if(label[i].getBackground()==Color.blue){
						
					}else{
						label[i].setOpaque(true);
						label[i].setBackground(Color.red);	
					}
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					if(label[i].getBackground()==Color.blue){
						
					}else{
						label[i].setOpaque(true);
						label[i].setBackground(Color.GRAY);
					}
					
				}
				
			});
			data.add(label[i]);

		}

		data.setPreferredSize(new Dimension(205,hight));
		return data;
	}

	public  void setls(List<String> sel){
		select=new ArrayList<String>();
		for(int i=0;i<sel.size();i++){
			select.add(sel.get(i));
		}
	}
}
