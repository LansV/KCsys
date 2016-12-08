package data;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class KCx {
	KCxdate wx=new KCxdate();
	JTable mtt;
	public JTable mt(String[][] arr){
		JTable maintable=new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public void setValueAt(Object aValue, int rowIndex, int columnIndex){
				Double num;
				if(columnIndex>3&&columnIndex<8){
	                try{
	                	String st=(String) aValue;
	                	
						num=Double.parseDouble(st);
						super.setValueAt(num,rowIndex,columnIndex);
						//System.out.println(st);
						//System.out.println(kccount.get(rowIndex));
	                }catch(Exception ex){
	                    JOptionPane.showMessageDialog(null, "只能输入数字!");
	                    return;
	                }
				}else{
					if(columnIndex==11){
		                try{
		                	String st=(String) aValue;
		                	int jgs=Integer.parseInt(st);
							super.setValueAt(jgs,rowIndex,columnIndex);
							//System.out.println(kccount.get(rowIndex));
		                }catch(Exception ex){
		                    JOptionPane.showMessageDialog(null, "只能输入数字!");
		                    return;
		                }
					}else{
						super.setValueAt(aValue,rowIndex,columnIndex);
					}
				}	
			}
		};
/*		maintable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				// TODO Auto-generated method stub
				if(e.getButton()==3){
					int r=maintable.rowAtPoint(e.getPoint());
					if(maintable.getRowSelectionAllowed()==true){
						maintable.setRowSelectionInterval(r,r);
						pm.show(maintable,e.getX()+10,e.getY()+10);
					}
				}
			}
		});*/
		maintable.getTableHeader().setReorderingAllowed(false);
		String[] cn={"种类编号","种类","供应商名称","名称","进货价","分销价","经销价","单价","数量","单位","修改日期","警告数量","供应商编号"};
		DefaultTableModel maindm=new DefaultTableModel(arr,cn){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				if(colunm>2&&colunm!=10&&colunm!=8){
					return true;
				}
				return false;
			}
		};
		maindm.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int column=e.getColumn();
				int row=e.getFirstRow();
				String xh=maintable.getValueAt(row,0).toString();
				switch (column){
				case 3:
					String spm=maintable.getValueAt(row, column).toString().trim();
					wx.updateKC(xh,spm,"KC_name");
					break;
				case 4:
					Double jhj=Double.parseDouble(maintable.getValueAt(row, column).toString());
					wx.updateKC(xh,jhj,"KC_jhj");
					break;
				case 5:
					Double fxj=Double.parseDouble(maintable.getValueAt(row, column).toString());
					wx.updateKC(xh,fxj,"KC_fxj");
					break;
				case 6:
					Double jxj=Double.parseDouble(maintable.getValueAt(row, column).toString());
					wx.updateKC(xh,jxj,"KC_jxj");
					break;
				case 7:
					Double dj=Double.parseDouble(maintable.getValueAt(row, column).toString());
					wx.updateKC(xh,dj,"KC_dj");
					break;
				case 9:
					String sdw=maintable.getValueAt(row, column).toString().trim();
					wx.updateKC(xh,sdw,"KC_dw");
					break;
				case 11:
					int sl=Integer.parseInt(maintable.getValueAt(row, column).toString());
					wx.updateKC(xh,sl,"KC_jgsl");
					break;
				case 12:
					String sgys=maintable.getValueAt(row, column).toString().trim();
					wx.updateKC(xh,sgys,"KC_gys");
					break;
				}
			}
		});
		maintable.setModel(maindm);
    	TableColumn mtablecl2=maintable.getColumnModel().getColumn(2);   //设置列宽    
    	mtablecl2.setPreferredWidth(160);   
    	mtablecl2.setMinWidth(160);
    	mtablecl2.setMaxWidth(160);
    	TableColumn mtablecl=maintable.getColumnModel().getColumn(3);   //设置列宽    
    	mtablecl.setPreferredWidth(200);   
    	mtablecl.setMinWidth(200);
    	mtablecl.setMaxWidth(200);
		maintable.setDefaultRenderer(Object.class,new colorc());
		maintable.setRowHeight(24);
		return maintable;
	}
	public KCx(){
		//---------------------------------------------入库数量------------------------------------------------------
		JFrame slf=new JFrame("入库数量");
		slf.setResizable(false);
		slf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container slc=slf.getContentPane();
		JLabel sll=new JLabel();
		JLabel xhl=new JLabel();
		slf.setBounds(700,150,250,150);
		sll.setBounds(30,10,200,25);
		xhl.setBounds(30,40,200,25);
		JTextField slt=new JTextField();
		slt.setBounds(30,75,180,25);
		slf.setLayout(null);
		slc.add(sll);
		slc.add(xhl);
		slc.add(slt);
		//---------------------------------------------修改数量------------------------------------------------------
		JFrame xgf=new JFrame("修改数量");
		xgf.setResizable(false);
		xgf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container xgc=xgf.getContentPane();
		JLabel xgl=new JLabel();
		JLabel xgxh=new JLabel();
		xgf.setBounds(700,150,250,150);
		xgl.setBounds(30,10,200,25);
		xgxh.setBounds(30,40,200,25);
		JTextField xgslt=new JTextField();
		xgslt.setBounds(30,75,180,25);
		xgf.setLayout(null);
		xgc.add(xgl);
		xgc.add(xgxh);
		xgc.add(xgslt);
		//----------------------------------------------主面板-------------------------------------------------------
		String[][] jczl=wx.getzl();
		JFrame mainf=new JFrame("库存详情");
		Container mainfc=mainf.getContentPane();
		mainf.setBounds(100,50,1250,750);
		mainf.setResizable(false);
		mainf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel mainp=new JPanel();
		mainp.setLayout(null);
		JTextField cxt=new JTextField();
		cxt.setBounds(375,20,120,25);
		mainp.add(cxt);
		JComboBox<String> cxzl=new JComboBox<String>();
		cxzl.addItem("所有种类");
		for(int i=0;i<jczl.length;i++){
			cxzl.addItem(jczl[i][1]);
		}
		cxzl.setBounds(20,20,100,25);
		mainp.add(cxzl);
		JLabel showjhj=new JLabel();
		showjhj.setBounds(535,20,80,25);
		JButton cxqh=new JButton("查缺");
		cxqh.setBounds(835,20,60,25);
		mainp.add(cxqh);
		mtt=mt(wx.KCdata("","",""));
		//-----------------------------------------------------------------
		JPopupMenu pm=new JPopupMenu();
		JMenuItem mit=new JMenuItem("入库");
		JMenuItem xit=new JMenuItem("出库");
		xit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mtt.setEnabled(false);
				int r=mtt.getSelectedRow();
				xgxh.setText(mtt.getValueAt(r,2).toString());
				xgl.setText(mtt.getValueAt(r,3).toString());
				xgf.setAlwaysOnTop(true);
				xgf.setVisible(true);
			}
		});
		mit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mtt.setEnabled(false);
				int r=mtt.getSelectedRow();
				xhl.setText(mtt.getValueAt(r,2).toString());
				sll.setText(mtt.getValueAt(r,3).toString());
				slf.setAlwaysOnTop(true);
				slf.setVisible(true);
			}
		});
		pm.add(mit);
		pm.add(xit);
		mtt.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				// TODO Auto-generated method stub
				if(e.getButton()==3){
					int r=mtt.rowAtPoint(e.getPoint());
					if(mtt.getRowSelectionAllowed()==true){
						mtt.setRowSelectionInterval(r,r);
						pm.show(mtt,e.getX()+10,e.getY()+10);
					}
				}
			}
		});
		xgf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				mtt.setEnabled(true);
				xgf.dispose();
			}
		});
		slf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				mtt.setEnabled(true);
				slf.dispose();
			}
		});
		//--------------------------------------------------------------------
		int crow=mtt.getRowCount();
		Double hjjhj=0.0;
		for(int i=0;i<crow;i++){
			String sjhj=mtt.getValueAt(i,4).toString();
			String ssl=mtt.getValueAt(i,8).toString();
			Double jhj=Double.parseDouble(sjhj);
			int sl=Integer.parseInt(ssl);
			hjjhj=sl*jhj+hjjhj;
		}
		showjhj.setText(String.format("%.3f",hjjhj));
		mainp.add(showjhj);
		JScrollPane mainjsp=new JScrollPane();
		mainjsp.setBounds(20,60,1200,650);
		mainjsp.setViewportView(mtt);
		//---------cxqh
		cxqh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mtt=mt(wx.qhKC());
				int crow=mtt.getRowCount();
				mtt.addMouseListener(new MouseAdapter(){
					@Override
					public void mousePressed(MouseEvent e){
						// TODO Auto-generated method stub
						if(e.getButton()==3){
							int r=mtt.rowAtPoint(e.getPoint());
							if(mtt.getRowSelectionAllowed()==true){
								mtt.setRowSelectionInterval(r,r);
								pm.show(mtt,e.getX()+10,e.getY()+10);
							}
						}
					}
				});
				mainjsp.setViewportView(mtt);
				Double hjjhj=0.0;
				for(int i=0;i<crow;i++){
					String sjhj=mtt.getValueAt(i,4).toString();
					String ssl=mtt.getValueAt(i,8).toString();
					Double jhj=Double.parseDouble(sjhj);
					int sl=Integer.parseInt(ssl);
					hjjhj=sl*jhj+hjjhj;
				}
				showjhj.setText(String.format("%.3f",hjjhj));
			}
		});
		//-------button
		cxt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					int zl=0;
					if(cxzl.getSelectedIndex()==0){
						mtt=mt(wx.KCdata(cxt.getText(),"",""));
					}else{
						zl=cxzl.getSelectedIndex();
						mtt=mt(wx.KCdata(cxt.getText(),jczl[zl-1][0],""));
					}
					
					int crow=mtt.getRowCount();
					mtt.addMouseListener(new MouseAdapter(){
						@Override
						public void mousePressed(MouseEvent e){
							// TODO Auto-generated method stub
							if(e.getButton()==3){
								int r=mtt.rowAtPoint(e.getPoint());
								if(mtt.getRowSelectionAllowed()==true){
									mtt.setRowSelectionInterval(r,r);
									pm.show(mtt,e.getX()+10,e.getY()+10);
								}
							}
						}
					});
					mainjsp.setViewportView(mtt);
					Double hjjhj=0.0;
					for(int i=0;i<crow;i++){
						String sjhj=mtt.getValueAt(i,4).toString();
						String ssl=mtt.getValueAt(i,8).toString();
						Double jhj=Double.parseDouble(sjhj);
						int sl=Integer.parseInt(ssl);
						hjjhj=sl*jhj+hjjhj;
					}
					showjhj.setText(String.format("%.3f",hjjhj));
				}
			}
		});
		//---------------------------种类监听-------
		cxzl.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					int zl=0;
					if(cxzl.getSelectedIndex()==0){
						mtt=mt(wx.KCdata(cxt.getText(),"",""));
					}else{
						zl=cxzl.getSelectedIndex();
						mtt=mt(wx.KCdata(cxt.getText(),jczl[zl-1][0],""));
					}
					int crow=mtt.getRowCount();
					mtt.addMouseListener(new MouseAdapter(){
						@Override
						public void mousePressed(MouseEvent e){
							// TODO Auto-generated method stub
							if(e.getButton()==3){
								int r=mtt.rowAtPoint(e.getPoint());
								if(mtt.getRowSelectionAllowed()==true){
									mtt.setRowSelectionInterval(r,r);
									pm.show(mtt,e.getX()+10,e.getY()+10);
								}
							}
						}
					});
					mainjsp.setViewportView(mtt);
					Double hjjhj=0.0;
					for(int i=0;i<crow;i++){
						String sjhj=mtt.getValueAt(i,4).toString();
						String ssl=mtt.getValueAt(i,8).toString();
						Double jhj=Double.parseDouble(sjhj);
						int sl=Integer.parseInt(ssl);
						hjjhj=sl*jhj+hjjhj;
					}
					showjhj.setText(String.format("%.3f",hjjhj));
				}
			}
		});
		//------------------------------------------------------------------------------------------------
		JFrame tjf=new JFrame("物品添加");
		tjf.setResizable(false);
		Container tjc=tjf.getContentPane();
		tjc.setLayout(null);
		JLabel lid=new JLabel("");
		lid.setBounds(20,30,30,25);
		tjc.add(lid);
		JComboBox<String> jc=new JComboBox<String>();
		jc.addItem("选择种类");
		for(int i=0;i<jczl.length;i++){
			jc.addItem(jczl[i][1]);
		}
		jc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					int i=jc.getSelectedIndex();
					if(i>0){
						lid.setText(jczl[i-1][0]);
					}else{
						lid.setText("");
					}
				}
			}
		});
		JLabel jcl=new JLabel("种类");
		jcl.setBounds(90,5,120,25);
		tjc.add(jcl);
		jc.setBounds(50,30,120,25);
		tjc.add(jc);
		JLabel bhl=new JLabel("编号");
		bhl.setBounds(215,5,120,25);
		tjc.add(bhl);
		JTextField tbh=new JTextField("");
		tbh.setBounds(180,30,100,25);
		tjc.add(tbh);
		JLabel mcl=new JLabel("名称");
		mcl.setBounds(365,5,120,25);
		tjc.add(mcl);
		JTextField tmc=new JTextField();
		tmc.setBounds(290,30,180,25);
		tjc.add(tmc);
		JLabel jhjl=new JLabel("进货价");
		jhjl.setBounds(500,5,120,25);
		tjc.add(jhjl);
		JTextField tjhj=new JTextField();
		tjhj.setBounds(480,30,80,25);
		tjc.add(tjhj);
		JLabel djl=new JLabel("单价");
		djl.setBounds(595,5,120,25);
		tjc.add(djl);
		JTextField tdj=new JTextField();
		tdj.setBounds(570,30,80,25);
		tjc.add(tdj);
		JLabel slla=new JLabel("数量");
		slla.setBounds(685,5,120,25);
		tjc.add(slla);
		JTextField tsl=new JTextField();
		tsl.setBounds(660,30,80,25);
		tjc.add(tsl);
		JLabel dwl=new JLabel("单位");
		dwl.setBounds(775,5,120,25);
		tjc.add(dwl);
		JTextField tdw=new JTextField();
		tdw.setBounds(750,30,80,25);
		tjc.add(tdw);
		JLabel sbhl=new JLabel("识别号");
		sbhl.setBounds(860,5,120,25);
		tjc.add(sbhl);
		JTextField tsbh=new JTextField();
		tsbh.setBounds(840,30,80,25);
		tsbh.setEditable(false);
		tjc.add(tsbh);
		JLabel sgys=new JLabel("");
		sgys.setBounds(50,80,120,25);
		tjc.add(sgys);
		JLabel sgysn=new JLabel("");
		sgysn.setBounds(100,80,120,25);
		tjc.add(sgysn);
		//--------------------------------------------供应商-----------------------------------
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
		showc.add(gycxt);
		JScrollPane showjsp=new JScrollPane();
		JTable mt=new JTable();
		String[] cn={"种类","编号","名称","联系人","联系电话"};
		DefaultTableModel dm=new DefaultTableModel(wx.getgys(gycxt.getText()),cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		mt.getTableHeader().setReorderingAllowed(false);
		mt.setModel(dm);
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
		gycxt.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					dm.setDataVector(wx.getgys(gycxt.getText()),cn);
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
						sgys.setText(mt.getValueAt(r,1).toString());
						sgysn.setText(mt.getValueAt(r,2).toString());
						showf.dispose();
					}
				}
			}
		});
		//------------------------------------------------------------------------------------
		JButton sbgys=new JButton();
		sbgys.setBounds(250,80,20,20);
		sbgys.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showf.setVisible(true);
			}
		});
		tjc.add(sbgys);
		jc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					int i=jc.getSelectedIndex();
					if(i>0){
						lid.setText(jczl[i-1][0]);
						tsbh.setText(wx.getspid(Integer.parseInt(jczl[i-1][0])));
					}else{
						lid.setText("");
						tsbh.setText("");
					}
				}
			}
		});
		JButton tj=new JButton("添加");
		tj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(lid.getText().length()==0){
					JOptionPane.showMessageDialog(null,"请选择种类");
				}else{
					if(tmc.getText().length()==0){
						JOptionPane.showMessageDialog(null,"请填写名称");
					}else{
						if(tjhj.getText().length()==0){
							JOptionPane.showMessageDialog(null,"请填写进货价");
						}else{
							if(tdj.getText().length()==0){
								JOptionPane.showMessageDialog(null,"请填写单价");
							}else{
								if(tsl.getText().length()==0){
									JOptionPane.showMessageDialog(null,"请填写数量");
								}else{
									if(tdw.getText().length()==0){
										JOptionPane.showMessageDialog(null,"请填写单位");
									}else{
										if(tsbh.getText().length()==0){
											JOptionPane.showMessageDialog(null,"请填写识别号");
										}else{
											if(sgys.getText().length()==0){
												JOptionPane.showMessageDialog(null,"选择供应商");
											}else{
												try{
													int id=Integer.parseInt(lid.getText().trim());
													String zl=jc.getSelectedItem().toString();
													String bh=tbh.getText().trim();
													String mc=tmc.getText().trim();
													Double jhj=Double.parseDouble(tjhj.getText().trim());
													Double dj=Double.parseDouble(tdj.getText().trim());
													int sl=Integer.parseInt(tsl.getText().trim());
													String dw=tdw.getText().trim();
													int sbh=Integer.parseInt(tsbh.getText().trim());
													int gys=Integer.parseInt(sgys.getText());
													wx.waddkc(id,zl,bh,mc,jhj,dj,sl,dw,sbh,gys);
													jc.setSelectedIndex(0);tbh.setText("");tmc.setText("");tjhj.setText("");
													tdj.setText("");tsl.setText("");tdw.setText("");tsbh.setText("");
												}catch(Exception e1){
													JOptionPane.showMessageDialog(null,"格式错误");
												} 
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		tj.setBounds(850,80,80,25);
		tjc.add(tj);
		tjf.setBounds(100,150,1000,160);
		tjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//-----------------------------------------------------------------------------------------------
		//-----------------------------------------减少数量监听-----------------------------
		xgslt.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()=='\n'){
					try{
						int tr=mtt.getSelectedRow();
						int sl=Integer.parseInt(xgslt.getText().trim());
						String sbh=mtt.getValueAt(tr,0).toString();
						int dbs=Integer.parseInt(mtt.getValueAt(tr,8).toString());
						if(sl>dbs){
							JOptionPane.showMessageDialog(null,"库存不足");
						}else{
							wx.wkcout(sbh,xgl.getText(),sl,"4");
							xgslt.setText("");
							if(cxzl.getSelectedIndex()==0){
								mtt=mt(wx.KCdata(cxt.getText(),"",""));
							}else{
								int zl=cxzl.getSelectedIndex();
								mtt=mt(wx.KCdata(cxt.getText(),jczl[zl-1][0],""));
							}
					    	mtt.setEnabled(true);
							mtt.addMouseListener(new MouseAdapter(){
								@Override
								public void mousePressed(MouseEvent e){
									// TODO Auto-generated method stub
									if(e.getButton()==3){
										int r=mtt.rowAtPoint(e.getPoint());
										if(mtt.getRowSelectionAllowed()==true){
											mtt.setRowSelectionInterval(r,r);
											pm.show(mtt,e.getX()+10,e.getY()+10);
										}
									}
								}
							});
					    	mainjsp.setViewportView(mtt);
							xgf.dispose();
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"数据非法");
					}
				}
			}
		});
		//------------------------------------------增加库存------------------------------------------------
		slt.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()=='\n'){
					try{
						int sl=Integer.parseInt(slt.getText().trim());
						int tr=mtt.getSelectedRow();
						Double jhj=Double.parseDouble(mtt.getValueAt(tr,4).toString());
						String gys=mtt.getValueAt(tr,12).toString();
						String sbh=mtt.getValueAt(tr,0).toString();
						if(sl<=0){
							JOptionPane.showMessageDialog(null,"不能小于等于零");
						}else{
							wx.wkcin(sbh,sll.getText(),sl,"采购进货");
							wx.addyf(gys,jhj,sl,sll.getText(),sbh);
							slt.setText("");
							if(cxzl.getSelectedIndex()==0){
								mtt=mt(wx.KCdata(cxt.getText(),"",""));
							}else{
								int zl=cxzl.getSelectedIndex();
								mtt=mt(wx.KCdata(cxt.getText(),jczl[zl-1][0],""));
							}
					    	mtt.setEnabled(true);
							mtt.addMouseListener(new MouseAdapter(){
								@Override
								public void mousePressed(MouseEvent e){
									// TODO Auto-generated method stub
									if(e.getButton()==3){
										int r=mtt.rowAtPoint(e.getPoint());
										if(mtt.getRowSelectionAllowed()==true){
											mtt.setRowSelectionInterval(r,r);
											pm.show(mtt,e.getX()+10,e.getY()+10);
										}
									}
								}
							});
					    	mainjsp.setViewportView(mtt);
							slf.dispose();
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"数据非法");
					}
				}
			}
		});
		//-------------------------------------------------------------------------------------------------
		JButton tjb=new JButton("添加商品");
		tjb.setBounds(1130,20,90,25);
		mainp.add(tjb);
		tjb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				tjf.setVisible(true);
			}
		});
		mainp.add(mainjsp);
		mainfc.add(mainp);
		mainf.setVisible(true);
		mainf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				// TODO Auto-generated method stub
				tjf.dispose();
			}
		});
	}
	public static void main(String[] args){
		new KCx();
	}
}
class colorc extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent
        (JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent (table,value,isSelected,hasFocus,row,column);
        setHorizontalAlignment(JLabel.CENTER);
        Color foreground, background;   
        if (isSelected) {
        	int r=table.getSelectedRow();
        	int c=table.getSelectedColumn();
        	if(r==row&&c==column){
        		foreground = Color.black;   
                background = Color.WHITE;  
        	}else{
        		foreground = Color.black;   
                background = Color.LIGHT_GRAY; 
        	}
            
        }  else {     
            foreground = Color.black;   
            background = Color.WHITE;    
        }
        cell.setForeground(foreground);   
        cell.setBackground(background);  
        if(column==8){
        	String n=table.getValueAt(row,11).toString().trim();
        	String n1=table.getValueAt(row,8).toString().trim();
        	int sl=0;
        	if(n1==""){
        		JOptionPane.showMessageDialog(null,"数据混乱");
        		System.out.println(row);
        	}else{
        		sl=Integer.parseInt(n1);
        	}
        	int jg=Integer.parseInt(n);
        	
        	if(jg>=sl){
        		cell.setBackground(Color.RED);
        	}
        }
        return cell;
    }
}
