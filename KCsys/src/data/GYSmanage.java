package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import security.CheckDate;
public class GYSmanage {
	getData gd=new getData();
	wData w=new wData();
	GysManageData gmd=new GysManageData();
	public GYSmanage(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 输出北京时间
	   	Date date2=new Date();
	   	String s1=sdf.format(date2);
	   	CheckDate.ReturnCheckDateResult(s1);
		//------------------------------------------添加面板----------------------------------------
		JFrame tjf=new JFrame("添加供应商");
		tjf.setBounds(500,50,380,580);
		Container tjc=tjf.getContentPane();
		tjc.setLayout(null);
		JLabel zlid=new JLabel("");
		zlid.setBounds(50,30,80,25);
		tjc.add(zlid);
		JTextField jtbh=new JTextField();
		jtbh.setEditable(false);
		jtbh.setBounds(240,30,90,25);
		tjc.add(jtbh);
		JComboBox<String> zljc=new JComboBox<String>();
		zljc.addItem("选择种类");
		String[][] zlarr=gd.getzl();
		for(int i=0;i<zlarr.length;i++){
			zljc.addItem(zlarr[i][1]);
		}
		zljc.setBounds(100,30,100,25);
		zljc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				// TODO Auto-generated method stub
				if(zljc.getSelectedIndex()!=0){
					zlid.setText(zlarr[zljc.getSelectedIndex()-1][0]);
					jtbh.setText(gd.getgysid(zlarr[zljc.getSelectedIndex()-1][0]));
				}else{
					zlid.setText("");
					jtbh.setText("");
				}
			}
		});
		tjc.add(zljc);
		JLabel bqmc=new JLabel("名称");
		bqmc.setBounds(50,60,120,25);
		tjc.add(bqmc);
		JTextField jtmc=new JTextField();
		jtmc.setBounds(50,90,280,25);
		tjc.add(jtmc);
		JLabel bqlxr=new JLabel("联系人");
		bqlxr.setBounds(50,120,120,25);
		tjc.add(bqlxr);
		JTextField jtlxr=new JTextField();
		jtlxr.setBounds(50,150,120,25);
		tjc.add(jtlxr);
		JLabel bqlxrtel=new JLabel("联系电话");
		bqlxrtel.setBounds(210,120,120,25);
		tjc.add(bqlxrtel);
		JTextField jtlxrtel=new JTextField();
		jtlxrtel.setBounds(210,150,120,25);
		tjc.add(jtlxrtel);
		JLabel bqfax=new JLabel("座机");
		bqfax.setBounds(50,180,120,25);
		tjc.add(bqfax);
		JTextField jtfax=new JTextField();
		jtfax.setBounds(50,210,120,25);
		tjc.add(jtfax);
		JLabel bqemail=new JLabel("邮箱");
		bqemail.setBounds(210,180,120,25);
		tjc.add(bqemail);
		JTextField jtemail=new JTextField();
		jtemail.setBounds(210,210,120,25);
		jtemail.setEnabled(false);
		tjc.add(jtemail);
		JLabel bqadd=new JLabel("地址");
		bqadd.setBounds(50,240,120,25);
		tjc.add(bqadd);
		JTextField jtadd=new JTextField();
		jtadd.setBounds(50,270,280,25);
		tjc.add(jtadd);
		JLabel bqkhh=new JLabel("开户行");
		bqkhh.setBounds(50,300,120,25);
		tjc.add(bqkhh);
		JTextField jtkhh=new JTextField();
		jtkhh.setBounds(50,330,280,25);
		tjc.add(jtkhh);
		JLabel bqkhm=new JLabel("开户名");
		bqkhm.setBounds(50,360,120,25);
		tjc.add(bqkhm);
		JTextField jtkhm=new JTextField();
		jtkhm.setBounds(50,390,120,25);
		tjc.add(jtkhm);
		JLabel bqzh=new JLabel("账号");
		bqzh.setBounds(50,420,120,25);
		tjc.add(bqzh);
		JTextField jtzh=new JTextField();
		jtzh.setBounds(50,450,280,25);
		tjc.add(jtzh);
		JButton addb=new JButton("添加");
		addb.setBounds(160,500,60,25);
		tjc.add(addb);
		tjf.setResizable(false);
		tjf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				zljc.setSelectedIndex(0);
				jtbh.setText("");
				jtmc.setText("");
				jtlxr.setText("");
				jtlxrtel.setText("");
				jtfax.setText("");
				jtadd.setText("");
				jtkhh.setText("");
				jtkhm.setText("");
				jtzh.setText("");
				tjf.dispose();
			}
		});
		//------------------------------------修改面板---------------------------------------
		JFrame xgf=new JFrame("修改信息");
		xgf.setBounds(500,50,380,580);
		Container xgc=xgf.getContentPane();
		xgc.setLayout(null);
		JLabel xgid=new JLabel("");
		xgid.setBounds(50,30,80,25);
		xgc.add(xgid);
		JLabel xgtype=new JLabel("");
		xgtype.setBounds(120,30,100,25);
		xgc.add(xgtype);
		JTextField xgbh=new JTextField();
		xgbh.setEditable(false);
		xgbh.setBounds(240,30,90,25);
		xgc.add(xgbh);
		JLabel xgmc=new JLabel("名称");
		xgmc.setBounds(50,60,120,25);
		xgc.add(xgmc);
		JTextField xjtmc=new JTextField();
		xjtmc.setBounds(50,90,280,25);
		xgc.add(xjtmc);
		JLabel xglxr=new JLabel("联系人");
		xglxr.setBounds(50,120,120,25);
		xgc.add(xglxr);
		JTextField xjtlxr=new JTextField();
		xjtlxr.setBounds(50,150,120,25);
		xgc.add(xjtlxr);
		JLabel xglxrtel=new JLabel("联系电话");
		xglxrtel.setBounds(210,120,120,25);
		xgc.add(xglxrtel);
		JTextField xjtlxrtel=new JTextField();
		xjtlxrtel.setBounds(210,150,120,25);
		xgc.add(xjtlxrtel);
		JLabel xgfax=new JLabel("座机");
		xgfax.setBounds(50,180,120,25);
		xgc.add(xgfax);
		JTextField xjtfax=new JTextField();
		xjtfax.setBounds(50,210,120,25);
		xgc.add(xjtfax);
		JLabel xgadd=new JLabel("地址");
		xgadd.setBounds(50,240,120,25);
		xgc.add(xgadd);
		JTextField xjtadd=new JTextField();
		xjtadd.setBounds(50,270,280,25);
		xgc.add(xjtadd);
		JLabel xjkhh=new JLabel("开户行");
		xjkhh.setBounds(50,300,120,25);
		xgc.add(xjkhh);
		JTextField xjtkhh=new JTextField();
		xjtkhh.setBounds(50,330,280,25);
		xgc.add(xjtkhh);
		JLabel xjkhm=new JLabel("开户名");
		xjkhm.setBounds(50,360,120,25);
		xgc.add(xjkhm);
		JTextField xjtkhm=new JTextField();
		xjtkhm.setBounds(50,390,120,25);
		xgc.add(xjtkhm);
		JLabel xjzh=new JLabel("账号");
		xjzh.setBounds(50,420,120,25);
		xgc.add(xjzh);
		JTextField xjtzh=new JTextField();
		xjtzh.setBounds(50,450,280,25);
		xgc.add(xjtzh);
		JButton xgb=new JButton("修改");
		xgb.setBounds(160,500,60,25);
		xgc.add(xgb);
		xgf.setResizable(false);
		xgf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				xgbh.setText("");
				xjtmc.setText("");
				xjtlxr.setText("");
				xjtlxrtel.setText("");
				xjtfax.setText("");
				xjtadd.setText("");
				xjtkhh.setText("");
				xjtkhm.setText("");
				xjtzh.setText("");
				xgf.dispose();
			}
		});
		//------------------------------------详细列表----------------------------------------
		JFrame showf=new JFrame("供应商列表");
		showf.setBounds(900,80,500,700);
		showf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showf.setResizable(false);
		Container showc=showf.getContentPane();
		showc.setLayout(null);
		JPanel showp=new JPanel();
		showp.setLayout(null);
		showp.setBounds(0,40,500,670);
		JTextField gycxt=new JTextField();
		gycxt.setBounds(10,10,120,25);
		JButton tjb=new JButton("添加供应商");
		tjb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tjf.setVisible(true);
			}
		});
		tjb.setBounds(378,10,100,25);
		showc.add(tjb);
		showc.add(gycxt);
		JScrollPane showjsp=new JScrollPane();
		JTable mt=new JTable();
		String[] cn={"种类","编号","名称","联系人","联系电话"};
		DefaultTableModel dm=new DefaultTableModel(gd.getgys(gycxt.getText()),cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		mt.getTableHeader().setReorderingAllowed(false);
		mt.setModel(dm);
		JPopupMenu rigthJPo=new JPopupMenu();
		JMenuItem delete=new JMenuItem("删除");
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=mt.getSelectedRow();
				String gysid=mt.getValueAt(r, 1).toString();
				gmd.deleteGys(gysid);
				dm.setDataVector(gd.getgys(gycxt.getText()),cn);
				TableColumn zlc=mt.getColumnModel().getColumn(0);
				zlc.setPreferredWidth(30);
				TableColumn mcc=mt.getColumnModel().getColumn(2);
				mcc.setPreferredWidth(180);
				TableColumn telc=mt.getColumnModel().getColumn(4);
				telc.setPreferredWidth(90);
			}
			
		});
		rigthJPo.add(delete);
		mt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=mt.rowAtPoint(e.getPoint());
					mt.getSelectionModel().setSelectionInterval(r, r);
					rigthJPo.show(mt, e.getX(), e.getY());
				}
			}
		});
		TableColumn zlc=mt.getColumnModel().getColumn(0);
		zlc.setPreferredWidth(30);
		TableColumn mcc=mt.getColumnModel().getColumn(2);
		mcc.setPreferredWidth(180);
		TableColumn telc=mt.getColumnModel().getColumn(4);
		telc.setPreferredWidth(90);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    mt.setDefaultRenderer(Object.class, tcr);
	    mt.setRowHeight(20);
		showjsp.setViewportView(mt);
		showjsp.setBounds(10,0,470,620);
		showp.add(showjsp);
		showc.add(showp);
		showf.setVisible(true);
		gycxt.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					dm.setDataVector(gd.getgys(gycxt.getText()),cn);
					TableColumn zlc=mt.getColumnModel().getColumn(0);
					zlc.setPreferredWidth(30);
					TableColumn mcc=mt.getColumnModel().getColumn(2);
					mcc.setPreferredWidth(180);
					TableColumn telc=mt.getColumnModel().getColumn(4);
					telc.setPreferredWidth(90);
				}
			}
		});
		mt.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						int r=mt.getSelectedRow();
						int cxid=Integer.parseInt(mt.getValueAt(r,1).toString());
						List<String> ls=gd.getcxgys(cxid);
						xgid.setText(ls.get(0));
						xgtype.setText(ls.get(1));
						xgbh.setText(ls.get(2));
						xjtmc.setText(ls.get(3));
						xjtlxr.setText(ls.get(4));
						xjtlxrtel.setText(ls.get(5));
						xjtfax.setText(ls.get(6));
						xjtadd.setText(ls.get(7));
						xjtkhh.setText(ls.get(8));
						xjtkhm.setText(ls.get(9));
						xjtzh.setText(ls.get(10));
						xgf.setVisible(true);
					}
				}
			}
		});
		//=====================================添加==============================================
		addb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtbh.getText().length()==0){
					JOptionPane.showMessageDialog(null,"必要数据为空");
				}else{
					if(jtmc.getText().length()==0){
						JOptionPane.showMessageDialog(null,"必要数据为空");
					}else{
						if(jtlxr.getText().length()==0){
							JOptionPane.showMessageDialog(null,"必要数据为空");
						}else{
							if(jtlxrtel.getText().length()==0){
								JOptionPane.showMessageDialog(null,"必要数据为空");
							}else{
								int bh=Integer.parseInt(jtbh.getText());
								String zl=zlid.getText();
								String mc=jtmc.getText().trim();
								String lxr=jtlxr.getText().trim();
								String tel=jtlxrtel.getText().trim();
								String fax="";
								if(jtfax.getText().length()!=0){
									fax=jtfax.getText();
								}
								String add="";
								if(jtadd.getText().length()!=0){
									add=jtadd.getText();
								}
								String khh="";
								if(jtkhh.getText().length()!=0){
									khh=jtkhh.getText().trim();
								}
								String khm="";
								if(jtkhm.getText().length()!=0){
									khm=jtkhm.getText().trim();
								}
								String zh="";
								if(jtzh.getText().length()!=0){
									zh=jtzh.getText().trim();
								}
								w.waddgys(mc,lxr,tel,fax,add,zl,bh,khh,khm,zh);
								zljc.setSelectedIndex(0);
								jtbh.setText("");
								jtmc.setText("");
								jtlxr.setText("");
								jtlxrtel.setText("");
								jtfax.setText("");
								jtemail.setText("");
								jtadd.setText("");
								jtkhh.setText("");
								jtkhm.setText("");
								jtzh.setText("");
								dm.setDataVector(gd.getgys(gycxt.getText()),cn);
								TableColumn zlc=mt.getColumnModel().getColumn(0);
								zlc.setPreferredWidth(30);
								TableColumn mcc=mt.getColumnModel().getColumn(2);
								mcc.setPreferredWidth(180);
								TableColumn telc=mt.getColumnModel().getColumn(4);
								telc.setPreferredWidth(90);
							}
						}
					}
				}
			}
		});
		//---------------------------------------修改------------------------------------------
		xgb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(xjtmc.getText().length()==0){
						JOptionPane.showMessageDialog(null,"名称不能为空");
					}else{
						if(xjtlxr.getText().length()==0){
							JOptionPane.showMessageDialog(null,"联系人不能为为空");
						}else{
							if(xjtlxrtel.getText().length()==0){
								JOptionPane.showMessageDialog(null,"联系电话不能为空");
							}else{
								String sbh=xgbh.getText().trim();
								int bh=Integer.parseInt(sbh);
								String mc=xjtmc.getText().trim();
								String lxr=xjtlxr.getText().trim();
								String tel=xjtlxrtel.getText().trim();
								String fax=xjtfax.getText().trim();
								String add=xjtadd.getText().trim();
								String khh=xjtkhh.getText().trim();
								String khm=xjtkhm.getText().trim();
								String zh=xjtzh.getText().trim();
								w.updategys(mc,lxr,tel,fax,add,bh,khh,khm,zh);
								dm.setDataVector(gd.getgys(gycxt.getText()),cn);
								TableColumn zlc=mt.getColumnModel().getColumn(0);
								zlc.setPreferredWidth(30);
								TableColumn mcc=mt.getColumnModel().getColumn(2);
								mcc.setPreferredWidth(180);
								TableColumn telc=mt.getColumnModel().getColumn(4);
								telc.setPreferredWidth(90);
							}
						}
					}
			}
		});
	}    
	public static void main(String[] args){
		new GYSmanage();
	}
}
