package order;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
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

import security.Lock;
import security.ReturnDate;
import tool.CreateExcel;

public class CreateOrder {
	String mc = ""; // 全局客户名称
	String lxr = ""; // 全局联系人
	String lxtel = ""; // 全局联系电话
	String addr = ""; // 全局地址
	Double hj; // 合计
	JLabel showhj = new JLabel(""); // 显示合计
	String tempEq=null;
	Image img = null;
	public static void main(String[] args) {
		new CreateOrder(1);
	}
	public CreateOrder(int userid) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		CreateOrderData d = new CreateOrderData();
		List<String> spcount = new ArrayList<String>(); // 商品名称
		
		// -------------------------------------显示表格---------------------------------------------------
		JPanel mp = new JPanel();
		JScrollPane msp = new JScrollPane();
		JPopupMenu rightmenu = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("删除");
		rightmenu.add(deleteItem);
		JTable mtable = new JTable() {
			private static final long serialVersionUID = 1L;

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (columnIndex == 5) {
					try {
						String st = aValue.toString();
						@SuppressWarnings("unused")
						Double price = Double.parseDouble(st);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "只能输入数字!");
						return;
					}
				}
				if (columnIndex == 6) {
					try {
						String st = (String) aValue;
						int num = Integer.parseInt(st);
						/*
						 * if(num>kccount.get(rowIndex)){
						 * JOptionPane.showMessageDialog(null,"超出库存"); return; }
						 */
						if (num == 0) {
							JOptionPane.showMessageDialog(null, "不能为0");
							return;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "只能输入数字!");
						return;
					}
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}
		};
		// =======================================add table menu
		// listener====================
		mtable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 3) {
					int r = mtable.rowAtPoint(e.getPoint());
					if (mtable.getRowSelectionAllowed() == true) {
						mtable.setRowSelectionInterval(r, r);
						rightmenu.show(mtable, e.getX(), e.getY());
					}
				}
			}
		});
		// =================================tablemodel========================================
		String[] mcn = { "序号", "商品编号", "商品名称", "单位", "折扣", "单价", "数量", "金额", "备注" };
		DefaultTableModel mdm = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				if (colunm > 3 && colunm < 7) {
					return true;
				}
				if (colunm == 8) {
					return true;
				}
				return false;
			}
		};
		mtable.getTableHeader().setReorderingAllowed(false);
		tcr.setHorizontalAlignment(JLabel.CENTER);
		mtable.setDefaultRenderer(Object.class, tcr);
		mdm.setColumnIdentifiers(mcn);
		mtable.setModel(mdm);
		mtable.setRowHeight(22);
		TableColumn cktablecxh = mtable.getColumnModel().getColumn(0); // 设置列宽
		cktablecxh.setPreferredWidth(40);
		cktablecxh.setMinWidth(40);
		cktablecxh.setMaxWidth(40);
		TableColumn cktableczl = mtable.getColumnModel().getColumn(1); // 设置列宽
		cktableczl.setPreferredWidth(120);
		cktableczl.setMinWidth(120);
		cktableczl.setMaxWidth(120);
		TableColumn cktableccp = mtable.getColumnModel().getColumn(2); // 设置列宽
		cktableccp.setPreferredWidth(180);
		cktableccp.setMinWidth(180);
		cktableccp.setMaxWidth(180);
		TableColumn cktablecdw = mtable.getColumnModel().getColumn(3); // 设置列宽
		cktablecdw.setPreferredWidth(40);
		cktablecdw.setMinWidth(40);
		cktablecdw.setMaxWidth(40);
		TableColumn cktablecbz = mtable.getColumnModel().getColumn(8); // 设置列宽
		cktablecbz.setPreferredWidth(350);
		cktablecbz.setMinWidth(350);
		cktablecbz.setMaxWidth(350);
		// ============================================删除行============================
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int r = mtable.getSelectedRow();
				mdm.removeRow(r);
				spcount.remove(r);
				// kccount.remove(r);
				int rowcount = mtable.getRowCount();
				for (int i = 0; i < rowcount; i++) {
					mtable.setValueAt(i + 1, i, 0);
				}
				Double chj = 0.0;
				for (int i = 0; i < mtable.getRowCount(); i++) {
					String s = mtable.getValueAt(i, 7).toString().trim();
					Double d = Double.parseDouble(s);
					chj = chj + d;
				}
				hj = chj;
				showhj.setText(String.format("%.2f", hj));
				// System.out.println(rowcount);
			}
		});
		// =============================================================================
		msp.setViewportView(mtable);
		msp.setBounds(0, 0, 960, 450);
		mp.setLayout(null);
		mp.add(msp);
		mp.setBounds(18, 100, 1000, 450);
		Object[] row = new Object[mcn.length];
		row[0] = "";
		row[1] = "";
		row[2] = "";
		row[3] = "";
		row[4] = "";
		row[5] = "";
		row[6] = "";
		row[7] = "";
		row[8] = "";
		// --------------------------------商品数量------------------------------------------------------
		JFrame spsl = new JFrame("填写数量");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    spsl.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		spsl.setAlwaysOnTop(true);
		spsl.setResizable(false);
		Container spslc = spsl.getContentPane();
		spslc.setLayout(null);
		JLabel splb = new JLabel();
		splb.setBounds(10, 5, 120, 25);
		JTextField sptx = new JTextField();
		sptx.setBounds(10, 35, 120, 25);
		JTextField spzk = new JTextField();
		spzk.setBounds(10, 65, 120, 25);
		spslc.add(spzk);
		spslc.add(sptx);
		spslc.add(splb);
		// ------------------------------------商品选择面板---------------------------------------------------
		JFrame spFrame = new JFrame("商品");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    spFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		spFrame.setAlwaysOnTop(true);
		spFrame.setResizable(false);
		Container spc = spFrame.getContentPane();
		spc.setLayout(null);
		JTextField spjt = new JTextField();
		spjt.setBounds(20, 10, 120, 25);
		String[] spcn = { "商品型号", "商品名称", "单位", "单价", "数量" };
		JTable sptable = new JTable();
		sptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int spr = sptable.getSelectedRow();
				if (e.getClickCount() == 2 && e.getButton() == 1) {
					Boolean sp = true;
					for (int i = 0; i < spcount.size(); i++) {
						if (spcount.get(i).equals(sptable.getValueAt(spr, 1).toString().trim())) {
							sp = false;
						}
					}
					if (sp == false) {
						JOptionPane.showMessageDialog(spFrame, "已添加商品");
					} else {
						sptx.setText("");
						spFrame.setEnabled(false);
						spsl.setVisible(true);
						splb.setText(sptable.getValueAt(spr, 1).toString().trim());
					}
				}
			}
		});
		sptable.getTableHeader().setReorderingAllowed(false);
		String[][] sparr = d.spcxdj(spjt.getText().trim());
		DefaultTableModel spdm = new DefaultTableModel(sparr, spcn) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				return false;
			}
		};
		spdm.setColumnIdentifiers(spcn);
		sptable.setModel(spdm);
		TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // 设置列宽
		sptablecl1.setPreferredWidth(80);
		sptablecl1.setMinWidth(80);
		sptablecl1.setMaxWidth(80);
		TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // 设置列宽
		sptablecl.setPreferredWidth(180);
		sptablecl.setMinWidth(180);
		sptablecl.setMaxWidth(180);
		JPanel spj = new JPanel();
		spj.setLayout(null);
		spj.setBounds(10, 50, 500, 620);
		JScrollPane spjs = new JScrollPane();
		spjs.setViewportView(sptable);
		spjs.setBounds(0, 0, 465, 555);
		spjt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == '\n') {
					String[][] sparr = d.spcxdj(spjt.getText().trim());
					DefaultTableModel spdm = new DefaultTableModel(sparr, spcn) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int colunm) {
							return false;
						}
					};
					spdm.setColumnIdentifiers(spcn);
					sptable.setModel(spdm);
					TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // 设置列宽
					sptablecl1.setPreferredWidth(80);
					sptablecl1.setMinWidth(80);
					sptablecl1.setMaxWidth(80);
					TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // 设置列宽
					sptablecl.setPreferredWidth(180);
					sptablecl.setMinWidth(180);
					sptablecl.setMaxWidth(180);
				}
			}
		});
		spj.add(spjs);
		spc.add(spj);
		spc.add(spjt);
		spFrame.setBounds(900, 100, 500, 650);
		spFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ------------------------------------主表模型监听-------------------------------------------------
		mdm.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int r = e.getFirstRow();
				int c = e.getColumn();
				if (3 < c && c < 7) {
					String c4 = mtable.getValueAt(r, 4).toString().trim();
					String c6 = mtable.getValueAt(r, 6).toString().trim();
					if (c6.length() == 0) {

					} else {
						String s = mtable.getValueAt(r, 6).toString().trim();
						int sl = Integer.parseInt(s);
						if (c4.length() == 0) {
							String d = mtable.getValueAt(r, 5).toString().trim();
							Double dj = null;
							dj = Double.parseDouble(d);
							String zj = String.format("%.2f", dj * sl);
							mtable.setValueAt(zj, r, 7);
						} else {
							String z = mtable.getValueAt(r, 4).toString().trim();
							String d = mtable.getValueAt(r, 5).toString().trim();
							Double dj = null;
							try {
								dj = Double.parseDouble(d);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "输入非法");
							}
							Double zk = Double.parseDouble(z) / 10;
							String zj = String.format("%.2f", dj * sl * zk);
							mtable.setValueAt(zj, r, 7);
						}
					}
				}
				if (c == 7) {
					Double chj = 0.0;
					for (int i = 0; i < mtable.getRowCount(); i++) {
						String s = mtable.getValueAt(i, 7).toString().trim();
						Double d = Double.parseDouble(s);
						chj = chj + d;
					}
					hj = chj;
					showhj.setText(String.format("%.2f", hj));
				}
			}
		});
		// ------------------------------------商品填写数量-------------------------------------------------
		sptx.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr = mtable.getRowCount();
				int spr = sptable.getSelectedRow();
				if (e.getKeyCode() == '\n') {
					if (sptx.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "未填写数量");
					} else {
						Boolean b = true;
						try {
							String zk = spzk.getText().trim();
							if (zk.length() == 0) {

							} else {
								@SuppressWarnings("unused")
								Double s = Double.parseDouble(zk);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "折扣输入非法");
							b = false;
						}
						try {
							String s = sptx.getText().trim();
							@SuppressWarnings("unused")
							int pdnum = Integer.parseInt(s);
							if (b == true) {
								mdm.addRow(row);
								mtable.setValueAt(mr + 1, mr, 0);
								mtable.setValueAt(sptable.getValueAt(spr, 0), mr, 1);
								mtable.setValueAt(sptable.getValueAt(spr, 1), mr, 2);
								mtable.setValueAt(sptable.getValueAt(spr, 2), mr, 3);
								mtable.setValueAt(spzk.getText().trim(), mr, 4);
								mtable.setValueAt(sptable.getValueAt(spr, 3), mr, 5);
								// kccount.add(kcsl);
								mtable.setValueAt(s, mr, 6);
								spFrame.setEnabled(true);
								spsl.dispose();
								spcount.add(sptable.getValueAt(spr, 1).toString().trim());
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(spsl, "请填写数字");
							sptx.setText("");
						}
					}
				}
			}
		});
		spsl.setBounds(700, 150, 250, 150);
		spsl.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				spFrame.setEnabled(true);
				spsl.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				spFrame.setEnabled(true);
				spsl.dispose();
			}
		});
		// ------------------------------------选择客户面板-------------------------------------------------
		JFrame khf = new JFrame("选择客户");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    khf.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		khf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		khf.setAlwaysOnTop(true);
		khf.setResizable(false);
		Container khfc = khf.getContentPane();
		khfc.setLayout(null);
		khf.setBounds(700, 10, 380, 650);
		JTextField cxjt = new JTextField();
		cxjt.setBounds(10, 10, 120, 25);
		khfc.add(cxjt);
		String[][] arr = d.khx(userid, "", ""); // 数据类里取二维数组
		String[] cxcn = { "编号","名称", "联系人", "电话", "地址" };
		JScrollPane cxjsp = new JScrollPane();
		JTable jtab = new JTable();
		jtab.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel khcxdm = new DefaultTableModel(arr, cxcn) { // table模型
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // 返回不可编辑
			}
		};
		jtab.setModel(khcxdm);
		cxjt.addKeyListener(new KeyAdapter() { // jtextfield添加文本框监听
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == '\n') {
					String[][] arr2 = d.khx(userid, cxjt.getText().trim(), cxjt.getText().trim());
					DefaultTableModel dm2 = new DefaultTableModel(arr2, cxcn) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jtab.setModel(dm2); // table 设置模型
				}
			}
		});
		JPanel cxjp = new JPanel();
		cxjp.setBounds(10, 50, 380, 550);
		cxjsp.setViewportView(jtab);
		cxjsp.setBounds(0, 0, 350, 550);
		cxjp.setLayout(null);
		cxjp.add(cxjsp);
		khfc.add(cxjp);
		
		// -----------------------------------------主面板----------------------------------------------------
		JFrame mf = new JFrame("订单提交");
		Image img = null;
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    mf.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		mf.setAlwaysOnTop(true);
		mf.setResizable(false);
		//-------------------------------------------临时单据-------------------------------------------------
		JFrame tempList=new JFrame("临时数据");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    tempList.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		tempList.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tempList.setAlwaysOnTop(true);
		tempList.setResizable(false);
		tempList.setBounds(420,280,450,400);
		Container tempListC=tempList.getContentPane();
		tempListC.setLayout(null);
		tempList.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				mf.setEnabled(true);
				tempList.dispose();
			}
		});
		JTable tempListTable=new JTable();
		tempListTable.getTableHeader().setReorderingAllowed(false);
		String[] tempListTableC={"单号","客户编号","名称","日期"};
		DefaultTableModel tempListTableModel=new DefaultTableModel(d.getTempListName(mf,userid),tempListTableC){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tempListTable.setModel(tempListTableModel);
		tempListTable.setRowHeight(22);
		tempListTable.setDefaultRenderer(Object.class,tcr);
		TableColumn tempListTC1=tempListTable.getColumnModel().getColumn(2);
		tempListTC1.setMaxWidth(180);
		tempListTC1.setMinWidth(180);
		JScrollPane tempListJSP=new JScrollPane();
		tempListJSP.setBounds(10,10,420,350);
		tempListJSP.setViewportView(tempListTable);
		tempListC.add(tempListJSP);
		//--------------------------------------------------------------------------------------------------
		Container mfc = mf.getContentPane();
		JButton kh_b = new JButton("选择客户");
		kh_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mf.setEnabled(false);
				khf.setVisible(true);
			}
		});
		kh_b.setBounds(450, 60, 90, 25);
		JPanel jtp = new JPanel();
		jtp.setLayout(null);
		jtp.add(kh_b);
		// jtp.add(jc);
		JLabel customerid=new JLabel("");
		customerid.setBounds(600, 60, 90, 25);
		jtp.add(customerid);
		JLabel mcl = new JLabel("客户:");
		mcl.setBounds(10, 20, 40, 25);
		JTextField mct = new JTextField();
		mct.setBounds(50, 20, 180, 25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel lxrl = new JLabel("联系人:");
		lxrl.setBounds(320, 20, 60, 25);
		JTextField lxrt = new JTextField();
		lxrt.setBounds(370, 20, 70, 25);
		jtp.add(lxrt);
		jtp.add(lxrl);
		JLabel lxrtell = new JLabel("TEL:");
		lxrtell.setBounds(550, 20, 60, 25);
		JTextField lxrtelt = new JTextField();
		lxrtelt.setBounds(580, 20, 90, 25);
		jtp.add(lxrtell);
		jtp.add(lxrtelt);
		JLabel addrl = new JLabel("地址:");
		addrl.setBounds(10, 60, 40, 25);
		jtp.add(addrl);
		JTextField addrt = new JTextField();
		addrt.setBounds(50, 60, 300, 25);
		jtp.add(addrt);
		JLabel ml = new JLabel();
		ml.setBounds(20, 560, 80, 25);
		jtp.setBounds(0, 0, 750, 90);
		JButton mbutton = new JButton("商品");
		mbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				spFrame.setVisible(true);
			}
		});
		mbutton.setBounds(910, 560, 60, 25);
		//------------------------------------------<临时表单监听>--------------------------------------
		tempListTable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						int row=tempListTable.getSelectedRow();
						String dh=tempListTable.getValueAt(row, 0).toString().trim();
						String khid=tempListTable.getValueAt(row, 1).toString().trim();
						List<String> ls=d.getCustomerInfo(userid, khid);
						mct.setText(ls.get(1));
						mct.setEditable(false);
						lxrt.setText(ls.get(2));
						lxrt.setEditable(false);
						lxrtelt.setText(ls.get(3));
						lxrtelt.setEditable(false);
						addrt.setText(ls.get(4));
						addrt.setEditable(false);
						customerid.setText(ls.get(0));
						//System.out.println();
						mdm.setRowCount(0);
						mdm.setDataVector(d.getTempListDate(dh, userid), mcn);
						tempEq=dh;
						TableColumn cktablecxh = mtable.getColumnModel().getColumn(0); // 设置列宽
						cktablecxh.setPreferredWidth(40);
						cktablecxh.setMinWidth(40);
						cktablecxh.setMaxWidth(40);
						TableColumn cktableczl = mtable.getColumnModel().getColumn(1); // 设置列宽
						cktableczl.setPreferredWidth(120);
						cktableczl.setMinWidth(120);
						cktableczl.setMaxWidth(120);
						TableColumn cktableccp = mtable.getColumnModel().getColumn(2); // 设置列宽
						cktableccp.setPreferredWidth(180);
						cktableccp.setMinWidth(180);
						cktableccp.setMaxWidth(180);
						TableColumn cktablecdw = mtable.getColumnModel().getColumn(3); // 设置列宽
						cktablecdw.setPreferredWidth(40);
						cktablecdw.setMinWidth(40);
						cktablecdw.setMaxWidth(40);
						TableColumn cktablecbz = mtable.getColumnModel().getColumn(8); // 设置列宽
						cktablecbz.setPreferredWidth(350);
						cktablecbz.setMinWidth(350);
						cktablecbz.setMaxWidth(350);
						Double chj = 0.0;
						for (int i = 0; i < mtable.getRowCount(); i++) {
							String s = mtable.getValueAt(i, 7).toString().trim();
							Double d = Double.parseDouble(s);
							chj = chj + d;
						}
						hj = chj;
						showhj.setText(String.format("%.2f", hj));
						tempList.dispose();
						mf.setEnabled(true);
					}
				}
			}
		});
		//------------------------------------------</临时表单监听>-------------------------------------
		JButton templist = new JButton("临时单据");
		templist.setBounds(120, 560, 100, 25);
		templist.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tempListTableModel.setDataVector(d.getTempListName(tempList,userid),tempListTableC);
				TableColumn tempListTC1=tempListTable.getColumnModel().getColumn(2);
				tempListTC1.setMaxWidth(180);
				tempListTC1.setMinWidth(180);
				tempList.setVisible(true);
				mf.setEnabled(false);
			}
			
		});
		mfc.add(templist);
		JButton addtemp = new JButton("添加临单");
		addtemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int cr = mtable.getRowCount();
				if (cr == 0) {
					JOptionPane.showMessageDialog(mf, "无数据");
				} else {

					if (mtable.isEditing() == true) {
						mtable.getCellEditor().stopCellEditing();
					}
					String dh = ml.getText().trim();
					mc = mct.getText();
					if (mc.length() == 0) {
						JOptionPane.showMessageDialog(null, "客户未填写");
					} else if (dh.length() != 0 && mc.length() != 0) {

						int slhj = 0;
						List<Object> listkh = new ArrayList<Object>();
						//List<Object> listsp = new ArrayList<Object>();
						List<Object> listhj = new ArrayList<Object>();
						lxr = lxrt.getText().trim();
						lxtel = lxrtelt.getText().trim();
						addr = addrt.getText().trim();
						listkh.add(dh);
						listkh.add(mc);
						listkh.add(lxr);
						listkh.add(lxtel);
						listkh.add(addr);
						listkh.add("");
						if (mct.isEditable() == true) {
							d.addCustomer(mf,customerid.getText().trim(),mc, lxr, lxtel, addr, userid);
						}
						mct.setEditable(false);
						lxrt.setEditable(false);
						lxrtelt.setEditable(false);
						addrt.setEditable(false);
						String mcid=customerid.getText().trim();
						String date=ReturnDate.netDate();
						if(dh.equals(d.tempNo())){
							String[][] data=new String[cr][9];
							int count=0;
							for (int i = 0; i < cr; i++) {

								String xhs = mtable.getValueAt(i, 0).toString().trim();
								int bh = Integer.parseInt(xhs);
								String xh = mtable.getValueAt(i, 1).toString().trim();
								String sp = mtable.getValueAt(i, 2).toString().trim();
								String dw = mtable.getValueAt(i, 3).toString().trim();
								String xhs4 = mtable.getValueAt(i, 4).toString().trim();
								Double zk = null;
								if (xhs4.length() == 0) {

								} else {
									zk = Double.parseDouble(xhs4);
								}
								String xhs5 = mtable.getValueAt(i, 5).toString().trim();
								Double dj = Double.parseDouble(xhs5);
								String xhs6 = mtable.getValueAt(i, 6).toString().trim();
								int sl = Integer.parseInt(xhs6);
								slhj = slhj + sl;
								String xhs7 = mtable.getValueAt(i, 7).toString().trim();
								Double je = Double.parseDouble(xhs7);
								String bz = mtable.getValueAt(i, 8).toString().trim();
								data[count][0]=xhs;
								data[count][1]=xh;
								data[count][2]=sp;
								data[count][3]=dw;
								data[count][4]=xhs4;
								data[count][5]=xhs5;
								data[count][6]=xhs6;
								data[count][7]=xhs7;
								data[count][8]=bz;
						/*		listsp.add(xhs);
								listsp.add(xh);
								listsp.add(sp);
								listsp.add(dw);
								listsp.add(xhs4);
								listsp.add(xhs5);
								listsp.add(xhs6);
								listsp.add(xhs7);
								listsp.add(bz);*/
								d.wxs(mf,dh,mcid, mc, bh, xh, sp, dw, zk, dj, sl, je, bz, userid,"temporder",date);
								count++;
							}
							count=0;
							int r = JOptionPane.showConfirmDialog(mf, "是否导出excel", "导出表格", JOptionPane.YES_NO_OPTION);// 返回选择值
							if (r == 0) {
								CreateExcel.exportExcel(mf, mc+"报价单.xls", data);
							};
							String[][] sparr = d.spcxdj(spjt.getText().trim());
							DefaultTableModel spdm = new DefaultTableModel(sparr, spcn) {
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row, int colunm) {
									return false;
								}
							};
							spdm.setColumnIdentifiers(spcn);
							sptable.setModel(spdm);
							TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // 设置列宽
							sptablecl1.setPreferredWidth(80);
							sptablecl1.setMinWidth(80);
							sptablecl1.setMaxWidth(80);
							TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // 设置列宽
							sptablecl.setPreferredWidth(180);
							sptablecl.setMinWidth(180);
							sptablecl.setMaxWidth(180);
						}else{
							JOptionPane.showMessageDialog(mf, "单号修正，重新提交");
						}
						
						listhj.add(changenum(hj));
						listhj.add(slhj);
						listhj.add(String.format("%.2f", hj));
						mdm.setRowCount(0);
						spcount.clear(); // clear count
						ml.setText(d.tempNo());
						showhj.setText("0.0");
					}
				}
			}
		});
		addtemp.setBounds(420, 560, 100, 25);
		mfc.add(addtemp);
		showhj.setBounds(600, 560, 60, 25);
		JButton mxsb = new JButton("提交");
		mxsb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int cr = mtable.getRowCount();
				if (cr == 0) {
					JOptionPane.showMessageDialog(mf, "无数据");
				} else {

					if (mtable.isEditing() == true) {
						mtable.getCellEditor().stopCellEditing();
					}
					String dh = d.getNo();
					mc = mct.getText();
					if (mc.length() == 0) {
						JOptionPane.showMessageDialog(mf, "客户未填写");
					} else if (dh.length() != 0 && mc.length() != 0) {
						int r = JOptionPane.showConfirmDialog(mf, "是否提交确认订单\n注意事项\n*订单确认后不可修改\n*订单确认后发货\n*发货周期为  [ 15 ] 个工作日", "订单确认", JOptionPane.YES_NO_OPTION);// 返回选择值
						if (r == 0) {
							int slhj = 0;
							List<Object> listkh = new ArrayList<Object>();
							//List<Object> listsp = new ArrayList<Object>();
							List<Object> listhj = new ArrayList<Object>();
							lxr = lxrt.getText().trim();
							lxtel = lxrtelt.getText().trim();
							addr = addrt.getText().trim();
							listkh.add(dh);
							listkh.add(mc);
							listkh.add(lxr);
							listkh.add(lxtel);
							listkh.add(addr);
							listkh.add("");
							if (mct.isEditable() == true) {
								d.addCustomer(mf,customerid.getText().trim(),mc, lxr, lxtel, addr, userid);
							}
							String mcid=customerid.getText().trim();
							mct.setEditable(false);
							lxrt.setEditable(false);
							lxrtelt.setEditable(false);
							addrt.setEditable(false);
							String date=ReturnDate.netDate();
							if(tempEq!=null){
								d.alertTempListStatus(tempEq, userid);
								tempEq=null;
							}
							String[][] data=new String[cr][9];
							int count=0;
							for (int i = 0; i < cr; i++) {
								String xhs = mtable.getValueAt(i, 0).toString().trim();
								int bh = Integer.parseInt(xhs);
								String xh = mtable.getValueAt(i, 1).toString().trim();
								String sp = mtable.getValueAt(i, 2).toString().trim();
								String dw = mtable.getValueAt(i, 3).toString().trim();
								String xhs4 = mtable.getValueAt(i, 4).toString();
								Double zk = null;
								if (xhs4.length() == 0) {

								} else {
									zk = Double.parseDouble(xhs4);
								}
								String xhs5 = mtable.getValueAt(i, 5).toString().trim();
								Double dj = Double.parseDouble(xhs5);
								String xhs6 = mtable.getValueAt(i, 6).toString().trim();
								int sl = Integer.parseInt(xhs6);
								slhj = slhj + sl;
								String xhs7 = mtable.getValueAt(i, 7).toString().trim();
								Double je = Double.parseDouble(xhs7);
								String bz = mtable.getValueAt(i, 8).toString().trim();
								data[count][0]=xhs;
								data[count][1]=xh;
								data[count][2]=sp;
								data[count][3]=dw;
								data[count][4]=xhs4;
								data[count][5]=xhs5;
								data[count][6]=xhs6;
								data[count][7]=xhs7;
								data[count][8]=bz;
						/*		listsp.add(xhs);
								listsp.add(xh);
								listsp.add(sp);
								listsp.add(dw);
								listsp.add(xhs4);
								listsp.add(xhs5);
								listsp.add(xhs6);
								listsp.add(xhs7);
								listsp.add(bz);*/

								d.wxs(mf,dh,mcid, mc, bh, xh, sp, dw, zk, dj, sl, je, bz, userid,"comfirmorder",date);
								count++;
							}
							count=0;
							int rr = JOptionPane.showConfirmDialog(mf, "是否导出excel", "导出表格", JOptionPane.YES_NO_OPTION);// 返回选择值
							if (rr == 0) {
								CreateExcel.exportExcel(mf, mc+"报价单.xls", data);
								
							};
							String[][] sparr = d.spcxdj(spjt.getText().trim());
							DefaultTableModel spdm = new DefaultTableModel(sparr, spcn) {
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row, int colunm) {
									return false;
								}
							};
							spdm.setColumnIdentifiers(spcn);
							sptable.setModel(spdm);
							TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // 设置列宽
							sptablecl1.setPreferredWidth(80);
							sptablecl1.setMinWidth(80);
							sptablecl1.setMaxWidth(80);
							TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // 设置列宽
							sptablecl.setPreferredWidth(180);
							sptablecl.setMinWidth(180);
							sptablecl.setMaxWidth(180);
							listhj.add(changenum(hj));
							listhj.add(slhj);
							listhj.add(String.format("%.2f", hj));
							mdm.setRowCount(0);
							spcount.clear(); // clear count
							ml.setText(d.tempNo());
							showhj.setText("0.0");
						}
					}
				}
			}
		});
		mxsb.setBounds(750, 560, 60, 25);
		mfc.setLayout(null);
		mfc.add(jtp);
		mfc.add(mxsb);
		mfc.add(ml);
		mfc.add(mp);
		mfc.add(mbutton);
		mfc.add(showhj);
		mf.setBounds(400, 100, 1000, 630);
		mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if (Lock.SingleUnLock(mf, "order/lock/CreateOrder.txt") == true) {
					mf.dispose();
					spFrame.dispose();
					khf.dispose();
				}
			}
		});
		// --------------------------------------客户选择监听-------------------------------------------------
		jtab.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2 && e.getButton() == 1) {
					int r = jtab.getSelectedRow();
					mc = jtab.getValueAt(r, 1).toString().trim();
					lxr = jtab.getValueAt(r, 2).toString().trim();
					lxtel = jtab.getValueAt(r, 3).toString().trim();
					addr = jtab.getValueAt(r, 4).toString().trim();
					customerid.setText(jtab.getValueAt(r, 0).toString().trim());
					mct.setText(mc);
					mct.setEditable(false);
					lxrt.setText(lxr);
					lxrt.setEditable(false);
					lxrtelt.setText(lxtel);
					lxrtelt.setEditable(false);
					addrt.setText(addr);
					addrt.setEditable(false);
					khf.dispose();
					String s = d.tempNo();
					// System.out.println(s);
					ml.setText(s);
					mf.setEnabled(true);
					mf.setVisible(true);
				}
			}
		});
		khf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if (!mf.isVisible()) {
					if (Lock.SingleUnLock(khf, "order/lock/CreateOrder.txt") == true) {
						mf.setEnabled(true);
						khf.dispose();
					}
				} else {
					mf.setEnabled(true);
					khf.dispose();
				}
			}
		});
		// -------------------------------------------------------------------------------------------
		int r = JOptionPane.showConfirmDialog(khf, "客户是否为新客户", "选择", JOptionPane.YES_NO_OPTION);// 返回选择值
		if (r == 0) {
			String s = d.tempNo();
			// System.out.println(s);
			customerid.setText(Integer.toString(d.getCustomerNo()));
			ml.setText(s);
			mf.setVisible(true);
		} else {
			khf.setVisible(true);
		}
		// -----------------------------------------------------------------------------------------
	}

	// -------------------------------------------------------------------------------------
	public String changenum(Double numb) {
		String num[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String d[] = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };
		String x[] = { "角", "分" };
		String sd = String.format("%.2f", numb);
		String[] cf = sd.split("\\.");
		String s = cf[0];
		int n = new Integer(s);
		String xs = cf[1];
		int xl = new Integer(xs);
		StringBuilder sb = new StringBuilder();
		List<Integer> ls = new ArrayList<Integer>();
		int cl = 0;
		for (int i = 0; i < s.length(); i++) {
			cl = (int) Math.pow(10, s.length() - (i + 1));
			int st = n / cl;
			ls.add(st);
			n = n - st * cl;
		}
		for (int i = 0; i < ls.size(); i++) {
			sb.append(num[ls.get(i)]);
			sb.append(d[ls.size() - 1 - i]);
		}
		if (xl >= 10) {
			int st = xl / 10;
			sb.append(num[st]);
			sb.append(x[0]);
			int f = xl - st * 10;
			sb.append(num[f]);
			sb.append(x[1]);
		} else if (xl == 0) {
			sb.append("整");
		} else {
			sb.append(num[xl]);
			sb.append(x[1]);
		}
		String regex1[] = { "零仟", "零佰", "零拾" };
		String regex2[] = { "零亿", "零万", "零元" };
		String regex3[] = { "亿", "万", "元" };
		String send = new String(sb);
		for (int i = 0; i < 3; i++) {
			send = send.replaceAll(regex1[i], "零");
		}
		for (int i = 0; i < 3; i++) {
			send = send.replaceAll("零零零", "零");
			send = send.replaceAll("零零", "零");
			send = send.replaceAll(regex2[i], regex3[i]);
		}
		send = send.replaceAll("零角", "");
		send = send.replaceAll("零分", "");
		return send;
	}
}