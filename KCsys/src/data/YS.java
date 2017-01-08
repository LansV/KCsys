package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
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
import test.Printclass;

public class YS {
	YSdata d=new YSdata();
	wData w=new wData();
	Double ys;
	Double hj;
	int tabler;
	int wzx;
	int wzy;
	public YS(){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);                      //全局居中
	    String[] mcn={"序号","商品型号","商品名称","单位","折扣","单价","数量","金额","备注","状态"};
	    //================product return popup menu=================
	    JPopupMenu thpm=new JPopupMenu();
		JMenuItem th=new JMenuItem("退货");
		thpm.add(th);
	    //=================================================退货面板=======================================
		JFrame thf=new JFrame("退货");
		thf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container thc=thf.getContentPane();
		thc.setLayout(null);
		thf.setBounds(800,380,200,120);
		JLabel thyz=new JLabel("原因:");
		thyz.setBounds(10,10,60,25);
		JTextField thtyz=new JTextField();
		thtyz.setBounds(45,10,140,25);
		JLabel thsl=new JLabel("数量:");
		thsl.setBounds(10,50,60,25);
		JTextField thtsl=new JTextField();
		thtsl.setBounds(45,50,140,25);
		thtsl.setEnabled(false);
		thc.add(thyz);
		thc.add(thsl);
		thc.add(thtyz);
		thc.add(thtsl);
		thf.setResizable(false);
	    //======================================detailed panel=================================
		JLabel showhj=new JLabel("",JLabel.CENTER);
		JFrame xxf=new JFrame("销售单据");
		xxf.setResizable(false);
		Container xxfc=xxf.getContentPane();
		//=====================================================table===========================
		JTable xxtable=new JTable();
		xxtable.getTableHeader().setResizingAllowed(false);
		DefaultTableModel xxmdm=new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		xxtable.setModel(xxmdm);
		xxtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=xxtable.rowAtPoint(e.getPoint());
					if(xxtable.getRowSelectionAllowed()==true){
						xxtable.setRowSelectionInterval(r,r);
						thpm.show(xxtable,e.getX(),e.getY());
					}
				}
			}
		});
		JPanel mp=new JPanel();
		JScrollPane msp=new JScrollPane();
		msp.setViewportView(xxtable);
		xxtable.setRowHeight(20);
	    xxtable.setDefaultRenderer(Object.class, tcr);
    	//==================================================table==========================================
		JComboBox<String> jc=new JComboBox<String>();
		jc.addItem("快递代收");
		jc.addItem("现金");
		jc.addItem("银行");
		jc.addItem("其他");
		jc.setEnabled(false);
		jc.setBounds(600,60,80,25);
		JPanel jtp=new JPanel();
		jtp.setLayout(null);
		jtp.add(jc);
		JLabel mcl=new JLabel("客户:");
		mcl.setBounds(10,20,40,25);
		JTextField mct=new JTextField();
		mct.setEditable(false);
		mct.setBounds(50,20,180,25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel lxrl=new JLabel("联系人:");
		lxrl.setBounds(320,20,60,25);
		JTextField lxrt=new JTextField();
		lxrt.setEditable(false);
		lxrt.setBounds(370,20,70,25);
		jtp.add(lxrt);
		jtp.add(lxrl);
		JLabel lxrtell=new JLabel("TEL:");
		lxrtell.setBounds(550,20,60,25);
		JTextField lxrtelt=new JTextField();
		lxrtelt.setEditable(false);
		lxrtelt.setBounds(580,20,90,25);
		jtp.add(lxrtell);
		jtp.add(lxrtelt);
		JLabel addrl=new JLabel("地址:");
		addrl.setBounds(10,60,40,25);
		jtp.add(addrl);
		JTextField addrt=new JTextField();
		addrt.setEditable(false);
		addrt.setBounds(50,60,300,25);
		jtp.add(addrt);
		JLabel ml=new JLabel("");
		ml.setBounds(20,560,80,25);
		jtp.setBounds(0,0,750,90);
		msp.setBounds(0,0,700,450);
		mp.setLayout(null);
		JButton print_b=new JButton("打印");
		print_b.setBounds(330,560,60,25);
		print_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				// TODO Auto-generated method stub
				List<Object> listkh=new ArrayList<Object>();
				List<Object> listsp=new ArrayList<Object>();
				List<Object> listhj=new ArrayList<Object>();
				listkh.add(ml.getText().trim());
				listkh.add(mct.getText().trim());
				listkh.add(lxrt.getText().trim());
				listkh.add(lxrtelt.getText().trim());
				listkh.add(addrt.getText().trim());
				listkh.add(jc.getSelectedItem());
				int cr=xxtable.getRowCount();
				int slhj=0;
				for(int i=0;i<cr;i++){
					String xhs=xxtable.getValueAt(i,0).toString().trim();
					String xh=xxtable.getValueAt(i,1).toString().trim();
					String sp=xxtable.getValueAt(i,2).toString().trim();
					String dw=xxtable.getValueAt(i,3).toString().trim();
					String xhs4=xxtable.getValueAt(i,4).toString().trim();
					String xhs5=xxtable.getValueAt(i,5).toString().trim();
					String xhs6=xxtable.getValueAt(i,6).toString().trim();
					int sl=Integer.parseInt(xhs6);
					slhj=slhj+sl;
					String xhs7=xxtable.getValueAt(i,7).toString().trim();
					String xstatus=xxtable.getValueAt(i,9).toString().trim();
					String bz="";
					if(xstatus.substring(0,1).equals("退")){
						bz="退货";
					}
					listsp.add(xhs);listsp.add(xh);listsp.add(sp);listsp.add(dw);listsp.add(xhs4);
					listsp.add(xhs5);listsp.add(xhs6);listsp.add(xhs7);listsp.add(bz);
				}
				listhj.add(changenum(hj));
				listhj.add(slhj);
				listhj.add(String.format("%.2f",hj));
				Printclass.setkhls(listkh);
				Printclass.setsp(listsp);
				Printclass.sethj(listhj);
				new Printclass();
			}
		});
		xxfc.add(print_b);
		mp.add(msp);
		mp.setBounds(18,100,750,450);
		showhj.setBounds(593,560,60,25);
		xxfc.setLayout(null);
		xxfc.add(jtp);
		xxfc.add(ml);
		xxfc.add(mp);
		xxfc.add(showhj);
		xxf.setBounds(20,50,750,630);
		xxf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//=============================================above is detailed panel=========================
		//======================================returns add listener========================================
		th.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				xxf.setEnabled(false);
				thtsl.setEnabled(true);
				thf.setVisible(true);
			}
		});
		//--------------------------------------填写金额------------------------------------
		JFrame xf=new JFrame("收款");
		xf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container xfc=xf.getContentPane();
		xfc.setLayout(null);
		xf.setBounds(800,380,200,100);
		JTextField xt=new JTextField();
		xt.setBounds(10,20,160,25);
		xfc.add(xt);
		xf.setResizable(false);
		//--------------------------------------------填写坏账记录--------------------------
		JFrame hzf=new JFrame("坏账");
		hzf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container hzc=hzf.getContentPane();
		hzc.setLayout(null);
		hzf.setBounds(800,380,200,100);
		JTextField hzt=new JTextField();
		hzt.setBounds(10,20,160,25);
		hzc.add(hzt);
		hzf.setResizable(false);
		//-------------------------------------overview panel-------------------------------------------
		JFrame ff=new JFrame("总览");
		ff.setResizable(false);
		ff.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container ffc=ff.getContentPane();
		ffc.setLayout(null);
		ff.setBounds(350,180,650,350);
		JTable table=new JTable();
		table.getTableHeader().setReorderingAllowed(false);
	    table.setDefaultRenderer(Object.class, tcr);
		String[] xcn={"单号","名称","总计","已收","应收","最后更新"};
		DefaultTableModel xdm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		table.setModel(xdm);
		//-------------------------------------退货原因输入监听------------------------------------------
		thtyz.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(thtsl.isEnabled()){   // some product return    
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"原因为空");
						}else{
							String sth=thtsl.getText().trim();
							if(sth.length()==0){
								JOptionPane.showMessageDialog(null,"数量为空");
							}else{
								try{
									int thsl=Integer.parseInt(sth);
									int r=xxtable.getSelectedRow();
									int yysl=Integer.parseInt(xxtable.getValueAt(r,6).toString().trim());
									if(thsl<=yysl){
										String yy=thtyz.getText().trim(); //退货原因
										String dh=ml.getText().trim(); //获取单号
										String kh=mct.getText().trim();   //获取客户名称
										int bh=Integer.parseInt(xxtable.getValueAt(r,0).toString().trim());
										String xh=xxtable.getValueAt(r,1).toString().trim();   //get product model
										String sp=xxtable.getValueAt(r,2).toString().trim();   //获取商品名称
										String dw=xxtable.getValueAt(r,3).toString().trim();
										String szk=xxtable.getValueAt(r,4).toString().trim();
										Double dj=Double.parseDouble(xxtable.getValueAt(r,5).toString().trim());//获取商品单价
										Double zk = null;
										Double thje = null;
										if(szk.length()==0){
											thje=dj*thsl;
										}else{
											zk=Double.parseDouble(szk);
											thje=dj*thsl*zk/10;
										}
										d.gth(dh,kh,bh,xh,sp,dw,zk,dj,thsl,thje,yy);
										//d.gth();
										w.wkcin(xh,sp,thsl,kh+"退货");
										/*System.out.println(yzj);
										System.out.println(dje);
										System.out.println(ysje);
										System.out.println(xsje);*/
										if(dh.substring(0,1).equals("X")){
											xxmdm.setDataVector(d.wxd(dh),mcn);
										}else{
											xxmdm.setDataVector(d.xsd(dh),mcn);
										}
										int row=xxtable.getRowCount();
										Double hj=0.0;
										for(int i=0;i<row;i++){
											hj=Double.parseDouble(xxtable.getValueAt(i,7).toString());
										}
										showhj.setText(String.format("%.2f",hj));
										TableColumn cktablecxh=xxtable.getColumnModel().getColumn(0);   //设置列宽    
								    	cktablecxh.setPreferredWidth(40);   
								    	cktablecxh.setMinWidth(40);
								    	cktablecxh.setMaxWidth(40);
								    	TableColumn cktableczl=xxtable.getColumnModel().getColumn(1);   //设置列宽    
								    	cktableczl.setPreferredWidth(70);   
								    	cktableczl.setMinWidth(70);
								    	cktableczl.setMaxWidth(70);
								    	TableColumn cktableccp=xxtable.getColumnModel().getColumn(2);   //设置列宽    
								    	cktableccp.setPreferredWidth(180);   
								    	cktableccp.setMinWidth(180);
								    	cktableccp.setMaxWidth(180);
								    	TableColumn cktablecdw=xxtable.getColumnModel().getColumn(3);   //设置列宽    
								    	cktablecdw.setPreferredWidth(40);   
								    	cktablecdw.setMinWidth(40);
								    	cktablecdw.setMaxWidth(40);
								    	TableColumn cktablezk=xxtable.getColumnModel().getColumn(4);   //设置列宽    
								    	cktablezk.setPreferredWidth(40);   
								    	cktablezk.setMinWidth(40);
								    	cktablezk.setMaxWidth(40);
								    	TableColumn cktablesl=xxtable.getColumnModel().getColumn(6);   //设置列宽    
								    	cktablesl.setPreferredWidth(40);   
								    	cktablesl.setMinWidth(40);
								    	cktablesl.setMaxWidth(40);
								    	TableColumn xxtablestatus=xxtable.getColumnModel().getColumn(9);
										xxtablestatus.setPreferredWidth(80);
										xxtablestatus.setMaxWidth(80);
										xxtablestatus.setMinWidth(80);
										xxf.setEnabled(true);
										thtsl.setEnabled(false);
										thtsl.setText("");
										thtyz.setText("");
										thf.dispose();
									}else{
										JOptionPane.showMessageDialog(null,"超出销售数量");
									}
								}catch(Exception e1){
									JOptionPane.showMessageDialog(null,"输入非法");
								}
							}
						}
					}else{
						//   ===============all product return===============
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"原因为空");
						}else{
							int r=table.getSelectedRow();
							String yy=thtyz.getText().trim();
							String dh=table.getValueAt(r,0).toString().trim();
							String kh=table.getValueAt(r,1).toString().trim();
							Double je=Double.parseDouble(table.getValueAt(r,2).toString().trim());
							d.dth(dh,kh,je,yy);
							int xrc=xxtable.getRowCount();
							for(int i=0;i<xrc;i++){
								String xh=xxtable.getValueAt(i,1).toString().trim();
								String sp=xxtable.getValueAt(i,2).toString().trim();
								String str=xxtable.getValueAt(i,6).toString().trim();
								int sl=Integer.parseInt(str);
								w.wkcin(xh,sp,sl,kh+"退货");
							}
							xdm.setDataVector(d.xys(kh),xcn);
							TableColumn cktablecxh=table.getColumnModel().getColumn(1);   //设置列宽    
					    	cktablecxh.setPreferredWidth(180);   
					    	cktablecxh.setMinWidth(180);
					    	cktablecxh.setMaxWidth(180);
							ff.setEnabled(true);
							thtyz.setText("");
							thf.dispose();
						}
					}
				}
			}
			});
		//-------------------------------------退货数量输入监听------------------------------------------
		thtsl.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(thtyz.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,"原因为空");
					}else{
						String sth=thtsl.getText().trim();
						if(sth.length()==0){
							JOptionPane.showMessageDialog(null,"数量为空");
						}else{
							try{
								int ths=Integer.parseInt(sth);
								System.out.println(ths);
								xxf.setEnabled(true);
								thtsl.setEnabled(false);
								thtsl.setText("");
								thtyz.setText("");
								thf.dispose();
							}catch(Exception e1){
								JOptionPane.showMessageDialog(null,"输入非法");
							}
						}
					}
				}
			}
		});
		//--------------------------------------退货窗口监听-------------------------------------
		thf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(xxf.isVisible()){
					xxf.setEnabled(true);
				}else{
					ff.setEnabled(true);
				}
				thtsl.setEnabled(false);
				thtsl.setText("");
				thtyz.setText("");
				thf.dispose();
			}
		});
		//--------------------------------------------右键菜单-------------------------------------
		JPopupMenu pm=new JPopupMenu();
		JMenuItem mit=new JMenuItem("详细列表");
		JMenuItem hz=new JMenuItem("添加坏账");
		JMenuItem qth=new JMenuItem("退货");
		pm.add(mit);
		pm.add(hz);
		pm.add(qth);
		//------------------------------------------表格监听---------------------------------
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub	
				if(e.getButton()==3){
					int r=table.rowAtPoint(e.getPoint());
					if(table.getRowSelectionAllowed()==true){
						table.setRowSelectionInterval(r,r);
						pm.show(table,e.getX(),e.getY());
						wzx=e.getX();
						wzy=e.getY();
					}
				}
				if(e.getButton()==1&&e.getClickCount()==2){
					ff.setEnabled(false);
					int r=table.getSelectedRow();
					String s=table.getValueAt(r,4).toString().trim();
					try{
						ys=Double.parseDouble(s);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"数据错误");
					}
					tabler=table.getRowCount();
					xf.setVisible(true);
				}
			}
		});
		//----------------------------------------------------------------------------------------------
		qth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=table.getSelectedRow();
				String st=table.getValueAt(r,0).toString().trim();
				if(st.substring(0,1).equals("X")){
					xxmdm.setDataVector(d.wxd(st),mcn);
				}else{
					xxmdm.setDataVector(d.xsd(st),mcn);
				}
				thf.setVisible(true);
				ff.setEnabled(false);
			}	
		});
		//-----------------------------------Listener of show detailed panel-------------------------------------------
		mit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=table.getSelectedRow();
				String st=table.getValueAt(r,0).toString().trim();
				String sn=table.getValueAt(r,1).toString().trim();
				ml.setText(st);
				ff.setEnabled(false);
				List<String> ls=d.getcustomerinfo(sn);
				mct.setText(ls.get(0));lxrt.setText(ls.get(1));lxrtelt.setText(ls.get(2));addrt.setText(ls.get(3));
				jc.setSelectedIndex(d.getproceedsmethod(st));
				if(st.substring(0,1).equals("X")){
					xxmdm.setDataVector(d.wxd(st),mcn);
				}else{
					xxmdm.setDataVector(d.xsd(st),mcn);
				}
				int row=xxtable.getRowCount();
				hj=0.0;
				for(int i=0;i<row;i++){
					hj=Double.parseDouble(xxtable.getValueAt(i, 7).toString())+hj;
				}
				showhj.setText(String.format("%.2f",hj));
		    	TableColumn cktablecxh=xxtable.getColumnModel().getColumn(0);   //设置列宽    
		    	cktablecxh.setPreferredWidth(40);   
		    	cktablecxh.setMinWidth(40);
		    	cktablecxh.setMaxWidth(40);
		    	TableColumn cktableczl=xxtable.getColumnModel().getColumn(1);   //设置列宽    
		    	cktableczl.setPreferredWidth(70);   
		    	cktableczl.setMinWidth(70);
		    	cktableczl.setMaxWidth(70);
		    	TableColumn cktableccp=xxtable.getColumnModel().getColumn(2);   //设置列宽    
		    	cktableccp.setPreferredWidth(180);   
		    	cktableccp.setMinWidth(180);
		    	cktableccp.setMaxWidth(180);
		    	TableColumn cktablecdw=xxtable.getColumnModel().getColumn(3);   //设置列宽    
		    	cktablecdw.setPreferredWidth(40);   
		    	cktablecdw.setMinWidth(40);
		    	cktablecdw.setMaxWidth(40);
		    	TableColumn cktablezk=xxtable.getColumnModel().getColumn(4);   //设置列宽    
		    	cktablezk.setPreferredWidth(40);   
		    	cktablezk.setMinWidth(40);
		    	cktablezk.setMaxWidth(40);
		    	TableColumn cktablesl=xxtable.getColumnModel().getColumn(6);   //设置列宽    
		    	cktablesl.setPreferredWidth(40);   
		    	cktablesl.setMinWidth(40);
		    	cktablesl.setMaxWidth(40);
		    	TableColumn xxtablestatus=xxtable.getColumnModel().getColumn(9);
				xxtablestatus.setPreferredWidth(80);
				xxtablestatus.setMaxWidth(80);
				xxtablestatus.setMinWidth(80);
				xxf.setVisible(true);
			}
		});
		//--------------------------------输入监听写入坏账---------------------------------------------
		hzt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()=='\n'){
					int r=table.getSelectedRow();
					String dh=table.getValueAt(r,0).toString().trim();
					String kh=table.getValueAt(r,1).toString().trim();
					String st=table.getValueAt(r,4).toString().trim();
					Double je=Double.parseDouble(st);
					String bz=hzt.getText().trim();
					d.whz(dh,kh,je,bz);
					String[][] xarr=d.xys(kh);
					xdm.setDataVector(xarr, xcn);
					hzt.setText("");
					ff.setEnabled(true);
					hzf.dispose();
				}
			}
		});
		//-----------------------------------------------------------------------------------------------
		hz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(false);
				hzf.setVisible(true);
			}
		});
		//-----------------------------------------------------------------------------------------------
		JScrollPane xjsp=new JScrollPane();
		xjsp.setViewportView(table);
		xjsp.setBounds(10,5,625,310);
		ffc.add(xjsp);
		//---------------------------------------------------应收总面板-------------------------------------------
		JFrame f=new JFrame("应收");
		f.setResizable(false);
		Container fc=f.getContentPane();
		fc.setLayout(null);
		f.setBounds(1000,50,320,650);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String[][] arr=d.ys();
		String[] cxcn={"客户名称","应收","最后日期"};
		JScrollPane jsp=new JScrollPane();
		JTable jtab=new JTable();
		jtab.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel dm=new DefaultTableModel(arr,cxcn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		jtab.setModel(dm);
		TableColumn cj=jtab.getColumnModel().getColumn(0);   //设置列宽    
    	cj.setPreferredWidth(140);   
    	cj.setMinWidth(140);
    	cj.setMaxWidth(40);
		jtab.setDefaultRenderer(Object.class, tcr);
		jtab.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				// TODO Auto-generated method stub
				if(e.getClickCount()==2&&e.getButton()==1){
					f.setEnabled(false);
					int r=jtab.getSelectedRow();
					String s=jtab.getValueAt(r,0).toString().trim();
					String[][] xarr=d.xys(s);
					xdm.setDataVector(xarr, xcn);
					TableColumn cktablecxh=table.getColumnModel().getColumn(1);   //设置列宽    
			    	cktablecxh.setPreferredWidth(180);   
			    	cktablecxh.setMinWidth(180);
			    	cktablecxh.setMaxWidth(180);
					ff.setVisible(true);
				}
			}
		});
		//-----------------------------------------detail panel close listener------------------------------------------
		xxf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int i=jtab.getSelectedRow();
				xdm.setDataVector(d.xys(jtab.getValueAt(i,0).toString()),xcn);
				TableColumn cktablecxh=table.getColumnModel().getColumn(1);   //设置列宽    
		    	cktablecxh.setPreferredWidth(180);   
		    	cktablecxh.setMinWidth(180);
		    	cktablecxh.setMaxWidth(180);
				ff.setEnabled(true);
				xxf.dispose();
			}
		});
		//==========================================proceeds  JTextField=====================
		xt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					int sr=table.getSelectedRow();
					String s=xt.getText().trim();
					if(s.length()==0){
						JOptionPane.showMessageDialog(null,"金额为空值");
					}else{
						try{
							Double sk=Double.parseDouble(s);
							if(ys-sk<0){       //proceeds greater than receivables
								String dh=table.getValueAt(sr,0).toString().trim();    //获取单号
								String ssq=table.getValueAt(sr,3).toString().trim();  //获取上期金额
								Double sq1=Double.parseDouble(ssq);
								Double zj=ys+sq1;
								d.updateys(dh,zj,0);
								table.setValueAt(String.format("%.2f",ys+sq1),sr,3);
								table.setValueAt(0,sr,4);
								sk=Double.parseDouble(String.format("%.2f",sk-ys));
								if(tabler>1){ //如果为多单号
									JOptionPane.showMessageDialog(null,"超本单金额,将自动分配余下金额");
									for(int i=0;i<tabler;i++){
										if(sr==i){
											//跳过选择行
										}else{
											String gdh=table.getValueAt(i,0).toString().trim();    //获取单号
											String st=table.getValueAt(i,4).toString().trim();  //获取应收金额
											String st2=table.getValueAt(i,3).toString().trim();  //获取上期金额
											Double zys=Double.parseDouble(st);
											Double sq=Double.parseDouble(st2);
											if(sk-zys>0||sk-zys==0){        //判断是否有剩款
												sk=sk-zys;
												Double gzj=zys+sq;
												table.setValueAt(String.format("%.2f",zys+sq),i,3);
												table.setValueAt(0,i,4);
												d.updateys(gdh,gzj,0);
												if(sk>0&&i==tabler-1){      //最后一行如果还有剩款 JP提示
													JOptionPane.showMessageDialog(null,"超出总应收"+String.format("%.2f",sk)+"元");
												}
											}else{  
												table.setValueAt(String.format("%.2f",sk+sq),i,3);  
												table.setValueAt(String.format("%.2f",zys-sk),i,4);
												Double gzj=sk+sq;
												d.updateys(gdh,gzj,1);
												break;                     //跳出循环
											}
										}
									}
								}else{
									if(sk>0){
										JOptionPane.showMessageDialog(null,"超出总应收"+String.format("%.2f",sk)+"元");
									}
								}
								ff.setEnabled(true);
								xf.dispose();
							}else if(ys-sk>0||ys-sk==0){  //proceeds less than receivable or proceeds equality receivable
								String dh=table.getValueAt(sr,0).toString().trim();
								String ssq=table.getValueAt(sr,3).toString().trim();  //获取上期金额
								Double sq1=Double.parseDouble(ssq);
								table.setValueAt(sk+sq1,sr,3);
								table.setValueAt(String.format("%.2f",ys-sk),sr,4);
								Double gzj=sk+sq1;
								if(ys-sk==0){
									d.updateys(dh,gzj,0);
								}else{
									d.updateys(dh,gzj,1);
								}
								ff.setEnabled(true);
								xf.dispose();
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"非法输入");
						}
						String kh=table.getValueAt(sr,1).toString().trim();
						String[][] xarr=d.xys(kh);
						xdm.setDataVector(xarr, xcn);
						xt.setText("");
					}
				}
			}
		});
		//--------------------------------------------------------------------------------------------------------
		ff.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				String[][] arr2=d.ys();
				dm.setDataVector(arr2,cxcn);
				TableColumn cj=jtab.getColumnModel().getColumn(0);   //设置列宽    
		    	cj.setPreferredWidth(140);   
		    	cj.setMinWidth(140);
		    	cj.setMaxWidth(40);
				f.setEnabled(true);
				ff.dispose();
			}
		});
		//---------------------------------------------------------------------------------------------------------
		hzf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				hzf.dispose();
			}
		});
		//--------------------------------------------------------------------------------------------------------
		xf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				xf.dispose();
			}
		});
		//-------------------------------------------------------------------------------------------------------
		jsp.setViewportView(jtab);
		jsp.setBounds(0,0,295,600);
		JPanel jp=new JPanel();
		jp.setBounds(8,10,295,600);
		jp.setLayout(null);
		jp.add(jsp);
		fc.add(jp);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new YS();
	}
	public String changenum(Double numb){
		String num[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String d[]={"元","拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾","佰", "仟" };
		String x[]={"角","分"};
		String sd=String.format("%.2f",numb);
		String[] cf=sd.split("\\.");
		String s=cf[0];
		int n=new Integer(s);
		String xs=cf[1];
		int xl=new Integer(xs);
		StringBuilder sb=new StringBuilder();
		List<Integer> ls=new ArrayList<Integer>();
		int cl = 0;
		for(int i=0;i<s.length();i++){
			cl=(int) Math.pow(10,s.length()-(i+1));
			int st=n/cl;
			ls.add(st);
			n=n-st*cl;
		}
		for(int i=0;i<ls.size();i++){
			sb.append(num[ls.get(i)]);
			sb.append(d[ls.size()-1-i]);
		}
		if(xl>=10){
			int st=xl/10;
			sb.append(num[st]);
			sb.append(x[0]);
			int f=xl-st*10;
			sb.append(num[f]);
			sb.append(x[1]);
		}else if(xl==0){
			sb.append("整");
		}else{
			sb.append(num[xl]);
			sb.append(x[1]);
		}
	    String regex1[] = {"零仟", "零佰", "零拾"};  
	    String regex2[] = {"零亿", "零万", "零元"};  
	    String regex3[] = {"亿", "万", "元"};  
	    String send=new String(sb);
	    for(int i=0;i<3;i++){
	    	send=send.replaceAll(regex1[i],"零");
	    }
	    for(int i=0;i<3;i++){
            send=send.replaceAll("零零零", "零");  
            send=send.replaceAll("零零", "零");  
            send=send.replaceAll(regex2[i], regex3[i]);
	    }
	    send=send.replaceAll("零角","");  
	    send=send.replaceAll("零分","");  
		return send;
	}
}
