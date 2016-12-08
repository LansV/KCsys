package data;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class wCP extends JFrame{

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
   	String ypicadd,picn;
   	String picadd;
   	Image img;
	public wCP(){
		super();
		setTitle("物品添加");
		setBounds(150,10,300,510);
		setLayout(null);
		setResizable(false);
		Container c=getContentPane();
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0,300,720);
		JPanel topPanel=new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0,300,450);
			JComboBox<String> jc=new JComboBox<String>();
			try {
				sql=con.createStatement();
				res=sql.executeQuery("select*from CPz");
				while(res.next()){
					jc.addItem(res.getString("CPz_type").trim());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"读取种类错误！\n软件自动退出！","错误",0);
			}finally{
				if(res!=null){
					try {
						res.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
				if(sql!=null){
					try {
						sql.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
			}
			JComboBox<String> jcdw=new JComboBox<String>();
			try {
				sql=con.createStatement();
				res=sql.executeQuery("select*from Dw");
				while(res.next()){
					jcdw.addItem(res.getString("Dw_dw").trim());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"读取种类错误！\n软件自动退出！","错误",0);
			}finally{
				if(res!=null){
					try {
						res.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
				if(sql!=null){
					try {
						sql.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
			}
			JComboBox<String> jcgys=new JComboBox<String>();
			try {
				sql=con.createStatement();
				res=sql.executeQuery("select*from GYs");
				while(res.next()){
					jcgys.addItem(res.getString("GYs_name").trim());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"读取种类错误！\n软件自动退出！","错误",0);
			}finally{
				if(res!=null){
					try {
						res.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
				if(sql!=null){
					try {
						sql.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"断开数据连接错误");
					}
				}
			}
			JLabel zl=new JLabel("选择种类");
			JLabel cp=new JLabel("物品名称");
			JLabel dw=new JLabel("选择单位");
			JLabel dj=new JLabel("物品单价");
			JLabel gys=new JLabel("选择供应商");
			JLabel gg=new JLabel("填写规格");
			JLabel weight=new JLabel("填写重量");
			JLabel Ljg=new JLabel("警告数量");
			JLabel Ljhj=new JLabel("进货价格");
			gg.setBounds(55,222,65,25);
			weight.setBounds(55,260,60,25);
			gys.setBounds(50,100,65,25);
			jcgys.setBounds(125,100,100,25);
			dj.setBounds(55,180,60,25);
			Ljg.setBounds(55,300,60,25);
			Ljhj.setBounds(55,340,60,25);
			JTextField jt=new JTextField();
			JTextField jtdj=new JTextField();
			JTextField jtgg=new JTextField();
			JTextField jtweight=new JTextField();
			JTextField jgsl=new JTextField();
			JTextField jhj=new JTextField();
			jgsl.setBounds(125,300,100,25);
			jhj.setBounds(125,340,100,25);
			jtweight.setBounds(125,260,100,25);
			jtgg.setBounds(125,220,100,25);
			jtdj.setBounds(125,180,100,25);
			zl.setBounds(55,20,60,25);
			jc.setBounds(125,20,100,25);
			cp.setBounds(55,140,60,25);
			jt.setBounds(125,140,100,25);
			dw.setBounds(55,60,60,25);
			jcdw.setBounds(125,60,100,25);
			topPanel.add(jgsl);
			topPanel.add(jhj);
			topPanel.add(Ljg);
			topPanel.add(Ljhj);
			topPanel.add(jtgg);
			topPanel.add(jtweight);
			topPanel.add(gg);
			topPanel.add(weight);
			topPanel.add(gys);
			topPanel.add(jcgys);
			topPanel.add(zl);
			topPanel.add(jc);
			topPanel.add(cp);
			topPanel.add(jt);
			topPanel.add(dw);
			topPanel.add(jcdw);
			topPanel.add(dj);
			topPanel.add(jtdj);	
			JButton addpic=new JButton("添加图片");
			addpic.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                    "图片(*.png;*.jpg;*.jpeg)",new String[]{"PNG","JPG","JPEG"});// 
					// TODO Auto-generated method stub
					JFileChooser jfc=new JFileChooser();
					jfc.setFileFilter(filter);
					jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
					jfc.showDialog(new JLabel(), "选择");
					File file=jfc.getSelectedFile();
					if(file.isDirectory()){
						JOptionPane.showMessageDialog(null,"未选中文件");
					}else if(file.isFile()){
						String files=jfc.getSelectedFile().getName().trim();
						if(files.substring(files.length()-5).trim().equals(".jpeg")){
							picn=files.substring(files.length()-5);
							ypicadd=file.getAbsolutePath();
							addpic.setText("已添加");
							addpic.setEnabled(false);
							topPanel.updateUI();
						}else{
							if(files.substring(files.length()-4).trim().equals(".png")||files.substring(files.length()-4).trim().equals(".jpg")){
								picn=files.substring(files.length()-4);
								ypicadd=file.getAbsolutePath();
								addpic.setText("已添加");
								addpic.setEnabled(false);
								topPanel.updateUI();
							}else{
								JOptionPane.showMessageDialog(null,"选择文件类型错误");
							}
						}
					}
				}	
			});
			addpic.setBounds(102,385,100,25);
			topPanel.add(addpic);
			JPanel downPanel=new JPanel();
			downPanel.setLayout(null);
			downPanel.setBounds(0,350,300,370);
			JPanel jspPane=new JPanel();
			jspPane.setLayout(null);
			jspPane.setBounds(0,0,300,300);
			JScrollPane jsp=new JScrollPane();
			jsp.setBounds(0,0,294,300);
			JButton delete=new JButton("删除");
			delete.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String delcp=null;
					if(select!=null){
						if(select.size()!=0){
							for(int i=0;i<select.size();i++){
								try{
									sql=con.createStatement();
									res=sql.executeQuery("select*from KC where KC_name = '"+select.get(i)+"'");
									if(res.next()==true){
										delcp="Y";	
									}else{
										delcp="N";
									}
								}catch(Exception e2){
									
								}
								if(delcp.equals("N")){
									try{
										sql=con.createStatement();
										res=sql.executeQuery("select*from CPx where CPx_name = '"+select.get(i)+"'");
										if(res.next()==false){
											
										}else{
											while(res.next()){
												String a=res.getString("CPx_picadd").trim();
												File file = new File(a);  
												if(file.exists()&&file.isFile()){
													file.delete();
												}
										}
										}
									}catch(Exception e2){
										JOptionPane.showMessageDialog(null,"图片删除失败");
									}finally{
										if(res!=null){
											try {
												res.close();
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												JOptionPane.showMessageDialog(null,"断开数据连接错误");
											}
										}
										if(sql!=null){
											try {
												sql.close();
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												JOptionPane.showMessageDialog(null,"断开数据库失败！");
											}
										}
									}
									try {
										sql=con.createStatement();
										sql.execute("delete from CPx where CPx_name = '"+select.get(i)+"'");
										if(i==select.size()-1){
											jsp.getViewport().setView(data());
											select.removeAll(select);
											JOptionPane.showMessageDialog(null,"删除成功！");
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null,"删除错误！\n请联系技术支持！","错误",0);
										return;
									}finally{
										if(sql!=null){
											try {
												sql.close();
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												JOptionPane.showMessageDialog(null,"断开数据库失败！");
											}
										}
									}
								}else{
									if(i==select.size()-1){
										JOptionPane.showMessageDialog(null,"不能删除此项，请清空库存后重试！");
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
			//delete.setBounds(120,310,60,25);
			//jsp.getViewport().setView(data());
			//jspPane.add(jsp);
			//downPanel.add(jspPane);
			//downPanel.add(delete);
			JButton button=new JButton("添加");
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String getzl=jc.getSelectedItem().toString().trim();
					String getdw=jcdw.getSelectedItem().toString().trim();
					String getgys=jcgys.getSelectedItem().toString().trim();
					String getcp=jt.getText().trim();
					String getdj=jtdj.getText().trim();
					String getgg=jtgg.getText().trim();
					String getweight=jtweight.getText().trim();
					Double dj,Djhj;
					String getjg=jgsl.getText().trim();
					String getjhj=jhj.getText().trim();
					String cphasbeen = null;
					int jgs;
					int zlid = 0;
					if(getdj.length()==0||getcp.length()==0||getjg.length()==0||getjhj.length()==0){
						JOptionPane.showMessageDialog(null,"名称或单价不能留空！");	
					}else{
						dj=new Double(getdj);
						Djhj=new Double(getjhj);
						jgs=new Integer(getjg);
						if(ypicadd==null){
							int option=JOptionPane.showConfirmDialog(null,"未选择图片是否继续","",JOptionPane.YES_NO_CANCEL_OPTION);
							if(option==0){
								picadd="NULL";
								try{
									sql=con.createStatement();
									res=sql.executeQuery("select*from CPx where CPx_name = '"+getcp+"'");
									if(res.next()==true){
										cphasbeen="Y";
									}else{
										cphasbeen="N";
									}
								}catch(Exception e1){
									JOptionPane.showMessageDialog(null,"判断存在错误");
								}finally{
									if(res!=null){
										try {
											res.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"断开数据连接错误");
										}
									}
									if(sql!=null){
										try {
											sql.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"断开数据库失败！");
										}
									}
								}
								if(cphasbeen.equals("Y")){
									JOptionPane.showMessageDialog(null,"产品已存在");
								}else{
									try {
										sql=con.createStatement();
										res=sql.executeQuery("select CPz_typeid from CPz where CPz_type = '"+getzl+"'");
										while(res.next()){
											zlid=res.getInt("CPz_typeid");
										}
										res2=sql.executeQuery("select top 1 *from CPx order by CPx_id desc");
										int cpid;
										if(res2.next()==false){
											cpid=1;
										}else{
											cpid=res2.getInt("CPx_id")+1;
										}
										sql.execute("insert into CPx values ("+zlid+",'"+getzl+"',"+cpid+",'"+getcp+"',"+dj+",'"+getdw+"','"+getgys+"','"+getgg+"',"
												+ "'"+getweight+"',NULL,+"+jgs+","+Djhj+")");
										jsp.getViewport().setView(data());
										addpic.setText("添加图片");
										addpic.setEnabled(true);
										topPanel.updateUI();
										JOptionPane.showMessageDialog(null, "添加成功！");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null,"添加数据错误");
									}finally{
										if(res!=null){
											try {
												res.close();
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												JOptionPane.showMessageDialog(null,"断开数据连接错误");
											}
										}
										if(sql!=null){
											try {
												sql.close();
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												JOptionPane.showMessageDialog(null,"断开数据库失败！");
											}
										}
									}
								}

							}
						}else{
							picadd="e://pic//"+getcp+picn;
							try{
								sql=con.createStatement();
								res=sql.executeQuery("select*from CPx where CPx_name = '"+getcp+"'");
								if(res.next()==true){
									cphasbeen="Y";
								}else{
									cphasbeen="N";
								}
							}catch(Exception e1){
								JOptionPane.showMessageDialog(null,"判断存在错误");
							}finally{
								if(res!=null){
									try {
										res.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null,"断开数据连接错误");
									}
								}
								if(sql!=null){
									try {
										sql.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null,"断开数据库失败！");
									}
								}
							}
							if(cphasbeen.equals("Y")){
								JOptionPane.showMessageDialog(null,"产品已存在");
								addpic.setText("添加图片");
								addpic.setEnabled(true);
								topPanel.updateUI();
							}else{
								try {
									sql=con.createStatement();
									res=sql.executeQuery("select CPz_typeid from CPz where CPz_type = '"+getzl+"'");
									while(res.next()){
										zlid=res.getInt("CPz_typeid");
									}
									res2=sql.executeQuery("select top 1 *from CPx order by CPx_id desc");
									int cpid;
									if(res2.next()==false){
										cpid=1;
									}else{
										cpid=res2.getInt("CPx_id")+1;
									}
									sql.execute("insert into CPx values ("+zlid+",'"+getzl+"',"+cpid+",'"+getcp+"',"+dj+",'"+getdw+"','"+getgys+"','"+getgg+"',"
											+ "'"+getweight+"','"+picadd+"',"+jgs+","+Djhj+")");
									jsp.getViewport().setView(data());
										try{
											FileInputStream input=new FileInputStream(ypicadd);//可替换为任何路径何和文件名 
											FileOutputStream output=new FileOutputStream(picadd);//可替换为任何路径何和文件名 
											int in=input.read();
											while(in!=-1){
											output.write(in);
											in=input.read();
											}
											input.close();
											output.close();
										}catch(Exception e1){
											JOptionPane.showMessageDialog(null, "添加图片失败");
										}
										addpic.setText("添加图片");
										addpic.setEnabled(true);
										topPanel.updateUI();
										JOptionPane.showMessageDialog(null, "添加成功！");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,"添加数据错误");
								}finally{
									if(res!=null){
										try {
											res.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"断开数据连接错误");
										}
									}
									if(sql!=null){
										try {
											sql.close();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(null,"断开数据库失败！");
										}
									}
								}
							}

						}						
					}
				}
			});
			button.setBounds(120,425,60,25);
			topPanel.add(button);
			mainPanel.add(topPanel);
			//mainPanel.add(downPanel);
			c.add(mainPanel);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
	}
	public static void main(String[] args){
		new wCP();
	}
	
	public JPanel data(){
		List<String> ls=new ArrayList<String>();
		List<String> sel=new ArrayList<String>();
		List<String> lspic=new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from CPx order by CPx_typeid");
			while(res.next()){
				String cp = res.getString("CPx_name").trim();
				ls.add(cp);
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
		x=ls.size()/9;  //取整数
		y=ls.size()%9; //取余数
		if(y>0){
			x=x+1;
		}
		hight=ls.size()*45;
		//x=x*9;
		data.setLayout(new GridLayout(ls.size(),1,3,3));
		JLabel label[];
		label=new JLabel[ls.size()];
		//int s=label.length;
		JPopupMenu Rmenu=new JPopupMenu();
		JMenuItem pic=new JMenuItem("查看图片");
		pic.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showpics(lspic.get(0))!=null){
					showpics(lspic.get(0)).setVisible(true);;
				}else{
				}

			}
			
		});
		Rmenu.add(pic);
		for(int i=0;i<ls.size();i++){
			label[i]=new JLabel(ls.get(i),JLabel.CENTER);
			label[i].setName("Label"+i);
			//label[i].setOpaque(true);
			//label[i].setBackground(Color.GRAY);
			int sn=i;
			label[i].addMouseListener(new MouseListener(){
				int i=sn;
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getButton()==MouseEvent.BUTTON3){
						if(lspic.size()>1){
							lspic.removeAll(lspic);
							String getpicname=label[i].getText().trim();
							lspic.add(getpicname);
							Rmenu.show(e.getComponent(),e.getX(),e.getY());
						}else{
							lspic.removeAll(lspic);
							String getpicname=label[i].getText().trim();
							lspic.add(getpicname);
							Rmenu.show(e.getComponent(),e.getX(),e.getY());
						}
					}else{
						if(label[i].getBackground()==Color.blue){
							String qc=label[i].getText().trim();
							for(int i=0;i<sel.size();i++){
								String qca=sel.get(i);
								if(qc.equals(qca)==true){
									sel.remove(i);
								}
							}
							setls(sel);
							label[i].setOpaque(false);
							label[i].setBackground(Color.gray);
						}else{
							label[i].setOpaque(true);
							label[i].setBackground(Color.blue);	
							String lab=label[i].getText().trim();
							sel.add(lab);
							setls(sel);
						}
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
						label[i].setOpaque(false);
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
	public JFrame showpics(String cp){
		JFrame spic=new JFrame("产品图片");
		Container c=spic.getContentPane();
		showpic sp=new showpic(cp);
		if(sp.getw()==0){
			spic.dispose();
		}else{
			c.add(sp);
			spic.setSize(sp.getw(),sp.geth());
			spic.setAlwaysOnTop(true);
			spic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			spic.addWindowListener(new WindowListener(){

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					spic.dispose();
					//spic.setVisible(false);
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
			return spic;
		}
		return null;
		
	}
}
