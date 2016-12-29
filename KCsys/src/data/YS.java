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
public class YS {
	getData d=new getData();
	wData w=new wData();
	Double ys;
	int tabler;
	int wzx;
	int wzy;
	public YS(){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);                      //全局居中
	    //=====================================详细面板================================================
	    
	    //--------------------------------------退货数量-------------------------------------------------------------
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
	    //-----------------------------------详细单面板--------------------------------------------------------------
		JPopupMenu thpm=new JPopupMenu();
		JMenuItem th=new JMenuItem("退货");
		thpm.add(th);
	    JFrame xxdf=new JFrame();
		xxdf.setTitle("详细列表");
		xxdf.setResizable(false);
		Container xxdc=xxdf.getContentPane();
		xxdf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		xxdc.setLayout(null);
		JTable xxdtable=new JTable();
		xxdtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=xxdtable.rowAtPoint(e.getPoint());
					if(xxdtable.getRowSelectionAllowed()==true){
						xxdtable.setRowSelectionInterval(r,r);
						thpm.show(xxdtable,e.getX(),e.getY());
					}
				}
			}
		});
		String[] xxdmc={"序号","商品型号","商品名称","单位","折扣","单价","数量","金额","备注"};
		DefaultTableModel xxddm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		th.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thtsl.setEnabled(true);
				xxdf.setEnabled(false);
				thf.setVisible(true);
			}
		});
		xxdtable.getTableHeader().setReorderingAllowed(false);
		xxdtable.setModel(xxddm);
	    xxdtable.setDefaultRenderer(Object.class, tcr);
		JScrollPane xxdjsp=new JScrollPane();
		xxdjsp.setViewportView(xxdtable);
		xxdjsp.setBounds(5,5,620,320);
		xxdc.add(xxdjsp);
		//--------------------------------------填写金额--------------------------------------------------------------
		JFrame xf=new JFrame("收款");
		xf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container xfc=xf.getContentPane();
		xfc.setLayout(null);
		xf.setBounds(800,380,200,100);
		JTextField xt=new JTextField();
		xt.setBounds(10,20,160,25);
		xfc.add(xt);
		xf.setResizable(false);
		//--------------------------------------------填写坏账记录----------------------------------------------------
		JFrame hzf=new JFrame("坏账");
		hzf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container hzc=hzf.getContentPane();
		hzc.setLayout(null);
		hzf.setBounds(800,380,200,100);
		JTextField hzt=new JTextField();
		hzt.setBounds(10,20,160,25);
		hzc.add(hzt);
		hzf.setResizable(false);
		//------------------------------------------------------------------------------------------------------------
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
		//-------------------------------------退货原因输入监听-----------------------------------------------------
		thtyz.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(thtsl.isEnabled()){
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"原因为空");
						}else{
							String sth=thtsl.getText().trim();
							if(sth.length()==0){
								JOptionPane.showMessageDialog(null,"数量为空");
							}else{
								try{
									int thsl=Integer.parseInt(sth);
									int r=xxdtable.getSelectedRow();
									int tr=table.getSelectedRow();
									int yysl=Integer.parseInt(xxdtable.getValueAt(r,6).toString().trim());
									if(thsl<=yysl){
										String yy=thtyz.getText().trim(); //退货原因
										String dh=table.getValueAt(tr,0).toString().trim();  //获取单号
										String kh=table.getValueAt(tr,1).toString().trim();    //获取客户名称
										Double yszj=Double.parseDouble(table.getValueAt(tr,2).toString().trim());//获取应收
										int bh=Integer.parseInt(xxdtable.getValueAt(r,0).toString().trim());
										String xh=xxdtable.getValueAt(r,1).toString().trim();
										String sp=xxdtable.getValueAt(r,2).toString().trim();   //获取商品名称
										String szk = null;
										Double dj=Double.parseDouble(xxdtable.getValueAt(r,5).toString().trim());//获取商品单价
										Double yzj=Double.parseDouble(xxdtable.getValueAt(r,7).toString().trim());//获取原价
										Double dje = null;
										try{
											szk=xxdtable.getValueAt(r,4).toString().trim();   //获取折扣
											Double zk=Double.parseDouble(szk.trim());
											dje=Double.parseDouble(String.format("%.2f",zk/10*thsl*dj));   //得到退货总价
										}catch(Exception e2){
											dje=Double.parseDouble(String.format("%.2f",thsl*dj));     //得到退货总价
										}
										//w.gth(dh,kh,sp,thsl,yysl,dj,je,dj,yy);
										Double ysje=Double.parseDouble(String.format("%.2f",yszj-dje));
										Double xsje=Double.parseDouble(String.format("%.2f",yzj-dje));
										w.gth(dh,bh,kh,sp,thsl,yysl,dj,dje,ysje,xsje,yy);
										w.wkcin(xh,sp,thsl,kh+"退货");
										System.out.println(yzj);
										System.out.println(dje);
										System.out.println(ysje);
										System.out.println(xsje);
										xxdf.setEnabled(true);
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
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"原因为空");
						}else{
							int r=table.getSelectedRow();
							String yy=thtyz.getText().trim();
							String dh=table.getValueAt(r,0).toString().trim();
							String kh=table.getValueAt(r,1).toString().trim();
							Double je=Double.parseDouble(table.getValueAt(r,2).toString().trim());
							w.dth(dh,kh,je,yy);
							int xrc=xxdtable.getRowCount();
							for(int i=0;i<xrc;i++){
								String xh=xxdtable.getValueAt(i,1).toString().trim();
								String sp=xxdtable.getValueAt(i,2).toString().trim();
								String str=xxdtable.getValueAt(i,6).toString().trim();
								int sl=Integer.parseInt(str);
								w.wkcin(xh,sp,sl,kh+"退货");
							}
							String[][] xarr=d.xys(kh);
							xdm.setDataVector(xarr,xcn);
							ff.setEnabled(true);
							thtyz.setText("");
							thf.dispose();
						}
					}
				}
			}
			});
		//-------------------------------------退货数量输入监听-----------------------------------------------------
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
								xxdf.setEnabled(true);
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
		//--------------------------------------退货窗口监听------------------------------------------------------
		thf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(xxdf.isVisible()){
					xxdf.setEnabled(true);
				}else{
					ff.setEnabled(true);
				}
				thtsl.setEnabled(false);
				thtsl.setText("");
				thtyz.setText("");
				thf.dispose();
			}
		});
		//--------------------------------------------右键菜单--------------------------------------------------------
		JPopupMenu pm=new JPopupMenu();
		JMenuItem mit=new JMenuItem("详细列表");
		JMenuItem hz=new JMenuItem("添加坏账");
		JMenuItem qth=new JMenuItem("退货");
		pm.add(mit);
		pm.add(hz);
		pm.add(qth);
		//------------------------------------------表格监听-------------------------------------------------
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
					xxddm.setDataVector(d.wxd(st),xxdmc);
				}else{
					xxddm.setDataVector(d.xsd(st),xxdmc);
				}
				thf.setVisible(true);
				ff.setEnabled(false);
			}	
		});
		//----------------------------------------------------------------------------------------------
		mit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=table.getSelectedRow();
				String st=table.getValueAt(r,0).toString().trim();
				if(st.substring(0,1).equals("X")){
					xxddm.setDataVector(d.wxd(st),xxdmc);
				}else{
					xxddm.setDataVector(d.xsd(st),xxdmc);
				}
		    	TableColumn xxdlk=xxdtable.getColumnModel().getColumn(2);   //设置列宽    
		    	xxdlk.setPreferredWidth(180);   
		    	xxdlk.setMinWidth(180);
		    	xxdlk.setMaxWidth(180);
		    	ff.setEnabled(false);
		    	xxdf.setBounds(200+wzx,250+wzy,650,350);
				xxdf.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------------
		xxdf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				xxdf.dispose();
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
					w.whz(dh,kh,je,bz);
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
		String[] cxcn={"客户名称","应收","待定"};
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
					ff.setVisible(true);
				}
			}
		});
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
							if(ys-sk<0){
								String dh=table.getValueAt(sr,0).toString().trim();    //获取单号
								String ssq=table.getValueAt(sr,3).toString().trim();  //获取上期金额
								Double sq1=Double.parseDouble(ssq);
								Double zj=ys+sq1;
								w.updateys(dh,zj,0);
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
												w.updateys(gdh,gzj,0);
												if(sk>0&&i==tabler-1){      //最后一行如果还有剩款 JP提示
													JOptionPane.showMessageDialog(null,"超出总应收"+String.format("%.2f",sk)+"元");
												}
											}else{  
												table.setValueAt(String.format("%.2f",sk+sq),i,3);  
												table.setValueAt(String.format("%.2f",zys-sk),i,4);
												Double gzj=sk+sq;
												w.updateys(gdh,gzj,1);
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
							}else if(ys-sk>0||ys-sk==0){
								String dh=table.getValueAt(sr,0).toString().trim();
								String ssq=table.getValueAt(sr,3).toString().trim();  //获取上期金额
								Double sq1=Double.parseDouble(ssq);
								table.setValueAt(sk+sq1,sr,3);
								table.setValueAt(String.format("%.2f",ys-sk),sr,4);
								Double gzj=sk+sq1;
								if(ys-sk==0){
									w.updateys(dh,gzj,0);
								}else{
									w.updateys(dh,gzj,1);
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
}
