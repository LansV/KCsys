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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import security.CheckDate;
import tool.Printclass;

public class QuerySaleAndRepair {
	QuerySaleAndRepairData d = new QuerySaleAndRepairData();
	wData w = new wData();
	Double hj;
	int tabler;
	int wzx;
	int wzy;

	public QuerySaleAndRepair(String user) {
	   	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); // 创建渲染器
		tcr.setHorizontalAlignment(JLabel.CENTER); // 全局居中
		String[] mcn = { "序号", "商品型号", "商品名称", "单位", "折扣", "单价", "数量", "金额", "收款", "备注" };
		// --------------------------------------填写金额------------------------------------
		JFrame xf = new JFrame("收款");
		xf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container xfc = xf.getContentPane();
		xfc.setLayout(null);
		xf.setBounds(800, 380, 200, 100);
		JTextField xt = new JTextField();
		xt.setBounds(10, 20, 160, 25);
		xfc.add(xt);
		xf.setResizable(false);
		// ================product return popup menu=================
		JPopupMenu thpm = new JPopupMenu();
		JMenuItem sk = new JMenuItem("收款");
		JMenuItem th = new JMenuItem("退货");
		// 禁止操作表单
		thpm.setEnabled(false);
		thpm.add(sk);
		thpm.add(th);
		// =================================================退货面板=======================================
		JFrame thf = new JFrame("退货");
		thf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container thc = thf.getContentPane();
		thc.setLayout(null);
		thf.setBounds(800, 380, 200, 120);
		JLabel thyz = new JLabel("原因:");
		thyz.setBounds(10, 10, 60, 25);
		JTextField thtyz = new JTextField();
		thtyz.setBounds(45, 10, 140, 25);
		JLabel thsl = new JLabel("数量:");
		thsl.setBounds(10, 50, 60, 25);
		JTextField thtsl = new JTextField();
		thtsl.setBounds(45, 50, 140, 25);
		thtsl.setEnabled(false);
		thc.add(thyz);
		thc.add(thsl);
		thc.add(thtyz);
		thc.add(thtsl);
		thf.setResizable(false);
		// ======================================detailed
		// panel=================================
		JLabel xxf_ShowTotal = new JLabel("", JLabel.CENTER);
		JFrame xxf = new JFrame("销售单据");
		xxf.setResizable(false);
		Container xxfc = xxf.getContentPane();
		// =====================================================table===========================
		JTable xxtable = new JTable();
		xxtable.getTableHeader().setResizingAllowed(false);
		DefaultTableModel xxmdm = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				return false;
			}
		};
		xxtable.setModel(xxmdm);
		xxtable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					CheckDate.ReturnCheckDateResult(xxtable);
					xxf.setEnabled(false);
					int r = xxtable.getSelectedRow();
					int sl = Integer.parseInt(xxtable.getValueAt(r, 6).toString());
					if (sl == 0) {
						xxf.setEnabled(true);
						JOptionPane.showMessageDialog(null, "此商品已全部退货");
					} else {
						Double zj = Double.parseDouble(xxtable.getValueAt(r, 7).toString());
						Double sk = Double.parseDouble(xxtable.getValueAt(r, 8).toString());
						if (zj.equals(sk)) {
							xxf.setEnabled(true);
							JOptionPane.showMessageDialog(null, "此商品已付清货款");
						} else {
							xf.setVisible(true);
						}
					}
				}
				if (e.getButton() == 3) {
					CheckDate.ReturnCheckDateResult(xxtable);
					int r = xxtable.rowAtPoint(e.getPoint());
					if (xxtable.getRowSelectionAllowed() == true) {
						xxtable.setRowSelectionInterval(r, r);
						thpm.show(xxtable, e.getX(), e.getY());
					}
				}
			}
		});
		JPanel mp = new JPanel();
		JScrollPane msp = new JScrollPane();
		msp.setViewportView(xxtable);
		xxtable.setRowHeight(20);
		xxtable.setDefaultRenderer(Object.class, tcr);
		// ==================================================table==========================================
		JComboBox<String> jc = new JComboBox<String>();
		jc.addItem("快递代收");
		jc.addItem("现金");
		jc.addItem("银行");
		jc.addItem("其他");
		jc.setEnabled(false);
		jc.setBounds(600, 60, 80, 25);
		JPanel jtp = new JPanel();
		jtp.setLayout(null);
		jtp.add(jc);
		JLabel mcl = new JLabel("客户:");
		mcl.setBounds(10, 20, 40, 25);
		JTextField mct = new JTextField();
		mct.setEditable(false);
		mct.setBounds(50, 20, 180, 25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel lxrl = new JLabel("联系人:");
		lxrl.setBounds(320, 20, 60, 25);
		JTextField lxrt = new JTextField();
		lxrt.setEditable(false);
		lxrt.setBounds(370, 20, 70, 25);
		jtp.add(lxrt);
		jtp.add(lxrl);
		JLabel lxrtell = new JLabel("TEL:");
		lxrtell.setBounds(550, 20, 60, 25);
		JTextField lxrtelt = new JTextField();
		lxrtelt.setEditable(false);
		lxrtelt.setBounds(580, 20, 90, 25);
		jtp.add(lxrtell);
		jtp.add(lxrtelt);
		JLabel addrl = new JLabel("地址:");
		addrl.setBounds(10, 60, 40, 25);
		jtp.add(addrl);
		JTextField addrt = new JTextField();
		addrt.setEditable(false);
		addrt.setBounds(50, 60, 300, 25);
		jtp.add(addrt);
		JLabel xxf_ShowNo = new JLabel("");
		xxf_ShowNo.setBounds(20, 560, 80, 25);
		jtp.setBounds(0, 0, 750, 90);
		msp.setBounds(0, 0, 700, 450);
		mp.setLayout(null);
		JButton xxf_OldSaleReceipt_b = new JButton("查看原单");
		xxf_OldSaleReceipt_b.setVisible(false);
		xxf_OldSaleReceipt_b.setBounds(380, 60, 90, 25);
		xxf_OldSaleReceipt_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (xxf_OldSaleReceipt_b.getText().equals("查看原单")) {
					sk.setEnabled(false);
					th.setEnabled(false);
					if (xxf_ShowNo.getText().trim().substring(0, 1).equals("X")) {
						xxmdm.setDataVector(d.ywxd(xxf_ShowNo.getText().trim()), mcn);
					} else {
						xxmdm.setDataVector(d.yxsd(xxf_ShowNo.getText().trim()), mcn);
					}
					TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
					cktablecxh.setPreferredWidth(40);
					cktablecxh.setMinWidth(40);
					cktablecxh.setMaxWidth(40);
					TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
					cktableczl.setPreferredWidth(70);
					cktableczl.setMinWidth(70);
					cktableczl.setMaxWidth(70);
					TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
					cktableccp.setPreferredWidth(180);
					cktableccp.setMinWidth(180);
					cktableccp.setMaxWidth(180);
					TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
					cktablecdw.setPreferredWidth(40);
					cktablecdw.setMinWidth(40);
					cktablecdw.setMaxWidth(40);
					TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
					cktablezk.setPreferredWidth(40);
					cktablezk.setMinWidth(40);
					cktablezk.setMaxWidth(40);
					TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
					cktablesl.setPreferredWidth(40);
					cktablesl.setMinWidth(40);
					cktablesl.setMaxWidth(40);
					int row = xxtable.getRowCount();
					Double hj = 0.0;
					for (int i = 0; i < row; i++) {
						Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
						hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
					}
					xxf_ShowTotal.setText(String.format("%.2f", hj));
					xxf_OldSaleReceipt_b.setText("查看现单");
				} else {
					sk.setEnabled(true);
					th.setEnabled(true);
					if (xxf_ShowNo.getText().trim().substring(0, 1).equals("X")) {
						xxmdm.setDataVector(d.wxd(xxf_ShowNo.getText().trim()), mcn);
					} else {
						xxmdm.setDataVector(d.xsd(xxf_ShowNo.getText().trim()), mcn);
					}
					TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
					cktablecxh.setPreferredWidth(40);
					cktablecxh.setMinWidth(40);
					cktablecxh.setMaxWidth(40);
					TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
					cktableczl.setPreferredWidth(70);
					cktableczl.setMinWidth(70);
					cktableczl.setMaxWidth(70);
					TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
					cktableccp.setPreferredWidth(180);
					cktableccp.setMinWidth(180);
					cktableccp.setMaxWidth(180);
					TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
					cktablecdw.setPreferredWidth(40);
					cktablecdw.setMinWidth(40);
					cktablecdw.setMaxWidth(40);
					TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
					cktablezk.setPreferredWidth(40);
					cktablezk.setMinWidth(40);
					cktablezk.setMaxWidth(40);
					TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
					cktablesl.setPreferredWidth(40);
					cktablesl.setMinWidth(40);
					cktablesl.setMaxWidth(40);
					int row = xxtable.getRowCount();
					Double hj = 0.0;
					for (int i = 0; i < row; i++) {
						Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
						hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
					}
					xxf_ShowTotal.setText(String.format("%.2f", hj));
					xxf_OldSaleReceipt_b.setText("查看原单");
				}
			}
		});
		jtp.add(xxf_OldSaleReceipt_b);
		JButton print_b = new JButton("打印");
		print_b.setBounds(330, 560, 60, 25);
		print_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<Object> listkh = new ArrayList<Object>();
				List<Object> listsp = new ArrayList<Object>();
				List<Object> listhj = new ArrayList<Object>();
				listkh.add(xxf_ShowNo.getText().trim());
				listkh.add(mct.getText().trim());
				listkh.add(lxrt.getText().trim());
				listkh.add(lxrtelt.getText().trim());
				listkh.add(addrt.getText().trim());
				listkh.add(jc.getSelectedItem());
				listkh.add("补单");
				int cr = xxtable.getRowCount();
				int slhj = 0;
				Double hj = 0.0;
				for (int i = 0; i < cr; i++) {
					String xhs = xxtable.getValueAt(i, 0).toString().trim();
					String xh = xxtable.getValueAt(i, 1).toString().trim();
					String sp = xxtable.getValueAt(i, 2).toString().trim();
					String dw = xxtable.getValueAt(i, 3).toString().trim();
					String xhs4 = xxtable.getValueAt(i, 4).toString().trim();
					String xhs5 = xxtable.getValueAt(i, 5).toString().trim();
					String xhs6 = xxtable.getValueAt(i, 6).toString().trim();
					int sl = Integer.parseInt(xhs6);
					slhj = slhj + sl;
					String xhs7 = xxtable.getValueAt(i, 7).toString().trim();
					Double jg = Double.parseDouble(xhs7);
					hj = jg + hj;
					String bz = "";
					listsp.add(xhs);
					listsp.add(xh);
					listsp.add(sp);
					listsp.add(dw);
					listsp.add(xhs4);
					listsp.add(xhs5);
					listsp.add(xhs6);
					listsp.add(xhs7);
					listsp.add(bz);
				}
				listhj.add(changenum(hj));
				listhj.add(slhj);
				listhj.add(String.format("%.2f", hj));
				if (xxf_ShowNo.getText().trim().substring(0, 1).equals("X")) {
					Printclass.setTitel("天澜清洗设备有限公司维修单");
				} else {
					Printclass.setTitel("天澜清洗设备有限公司销售单");
				}
				Printclass.setUser(user);
				Printclass.setkhls(listkh);
				Printclass.setsp(listsp);
				Printclass.sethj(listhj);
				new Printclass();
			}
		});
		xxfc.add(print_b);
		mp.add(msp);
		mp.setBounds(18, 100, 750, 450);
		xxf_ShowTotal.setBounds(593, 560, 60, 25);
		xxfc.setLayout(null);
		xxfc.add(jtp);
		xxfc.add(xxf_ShowNo);
		xxfc.add(mp);
		xxfc.add(xxf_ShowTotal);
		xxf.setBounds(20, 50, 750, 630);
		xxf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// =============================================above is detailed
		// panel=========================
		// ======================================returns add
		// listener========================================
		th.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CheckDate.ReturnCheckDateResult(th);
				xxf.setEnabled(false);
				int r = xxtable.getSelectedRow();
				int sl = Integer.parseInt(xxtable.getValueAt(r, 6).toString());
				if (sl == 0) {
					xxf.setEnabled(true);
					JOptionPane.showMessageDialog(null, "此商品已全部退货");
				} else {
					thtsl.setEnabled(true);
					thf.setVisible(true);
				}
			}
		});

		// --------------------------------------------填写坏账记录--------------------------
		JFrame hzf = new JFrame("坏账");
		hzf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container hzc = hzf.getContentPane();
		hzc.setLayout(null);
		hzf.setBounds(800, 380, 200, 100);
		JTextField hzt = new JTextField();
		hzt.setBounds(10, 20, 160, 25);
		hzc.add(hzt);
		hzf.setResizable(false);
		// =====================================销售单内收款监听=========================
		sk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckDate.ReturnCheckDateResult(sk);
				// TODO Auto-generated method stub
				xxf.setEnabled(false);
				int r = xxtable.getSelectedRow();
				int sl = Integer.parseInt(xxtable.getValueAt(r, 6).toString());
				if (sl == 0) {
					xxf.setEnabled(true);
					JOptionPane.showMessageDialog(null, "此商品已全部退货");
				} else {
					Double zj = Double.parseDouble(xxtable.getValueAt(r, 7).toString());
					Double sk = Double.parseDouble(xxtable.getValueAt(r, 8).toString());
					if (zj.equals(sk)) {
						xxf.setEnabled(true);
						JOptionPane.showMessageDialog(null, "此商品已付清货款");
					} else {
						xf.setVisible(true);
					}
				}
			}
		});
		// -------------------------------------overview
		// panel-------------------------------------------
		JFrame qSAR_MainFrame = new JFrame("单据查询");
		qSAR_MainFrame.setResizable(false);
		qSAR_MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container qSAR_MainFrame_Content = qSAR_MainFrame.getContentPane();
		qSAR_MainFrame_Content.setLayout(null);
		qSAR_MainFrame.setBounds(350, 180, 650, 500);
		JButton qSAR_MainFrame_QueryB = new JButton("查询");
		qSAR_MainFrame_QueryB.setBounds(570, 10, 60, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryB);
		JTextField qSAR_MainFrame_QueryNo = new JTextField();
		qSAR_MainFrame_QueryNo.setBounds(200, 10, 70, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryNo);
		JTextField qSAR_MainFrame_QueryName = new JTextField();
		qSAR_MainFrame_QueryName.setBounds(10, 10, 180, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryName);
		JTextField qSAR_MainFrame_QueryDate1 = new JTextField();
		qSAR_MainFrame_QueryDate1.setBounds(280, 10, 80, 22);
		JTextField qSAR_MainFrame_QueryDate2 = new JTextField();
		qSAR_MainFrame_QueryDate2.setBounds(373, 10, 80, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryDate2);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryDate1);
		JLabel qSAR_MainFrame_QueryLabel = new JLabel("—");
		qSAR_MainFrame_QueryLabel.setBounds(360, 10, 30, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryLabel);
		JTable qSAR_MainTable = new JTable();
		qSAR_MainTable.getTableHeader().setReorderingAllowed(false);
		qSAR_MainTable.setDefaultRenderer(Object.class, tcr);
		String[] qSAR_MainTable_ColumnName = { "单号", "客户编号","名称", "总计", "已收", "应收", "最后更新" };
		DefaultTableModel xdm = new DefaultTableModel(d.xys("", "", "", ""), qSAR_MainTable_ColumnName) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // 返回不可编辑
			}
		};
		qSAR_MainTable.setModel(xdm);
		TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(1); // 设置列宽
		cktablecxh.setPreferredWidth(180);
		cktablecxh.setMinWidth(180);
		cktablecxh.setMaxWidth(180);
		qSAR_MainTable.setRowHeight(20);
		// ===========================================单据查询=========================================
		qSAR_MainFrame_QueryB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String QueryName = qSAR_MainFrame_QueryName.getText().trim();
				String QueryNo = qSAR_MainFrame_QueryNo.getText().trim();
				String QueryDate1 = qSAR_MainFrame_QueryDate1.getText().trim();
				String QueryDate2 = qSAR_MainFrame_QueryDate2.getText().trim();
				if (QueryDate1.length() == 0) {
					QueryDate1 = "2000-1-1";
				}
				if (QueryDate2.length() == 0) {
					Date cDate = new Date();
					QueryDate2 = String.format("%tF", cDate);
				}
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date cDate = sdf.parse(QueryDate1);
					Date xDate = sdf.parse(QueryDate2);
					xdm.setDataVector(
							d.xys(QueryName, QueryNo, String.format("%tF", cDate), String.format("%tF", xDate)),
							qSAR_MainTable_ColumnName);
					TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(1); // 设置列宽
					cktablecxh.setPreferredWidth(180);
					cktablecxh.setMinWidth(180);
					cktablecxh.setMaxWidth(180);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "日期格式错误");
				}
			}
		});
		qSAR_MainFrame_QueryName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == '\n') {
					qSAR_MainFrame_QueryB.doClick();
				}
			}
		});
		qSAR_MainFrame_QueryNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == '\n') {
					qSAR_MainFrame_QueryB.doClick();
				}
			}
		});
		qSAR_MainFrame_QueryDate1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == '\n') {
					qSAR_MainFrame_QueryB.doClick();
				}
			}
		});
		qSAR_MainFrame_QueryDate2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == '\n') {
					qSAR_MainFrame_QueryB.doClick();
				}
			}
		});
		// -------------------------------------退货原因输入监听------------------------------------------
		thtyz.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == '\n') {
					if (thtsl.isEnabled()) { // some product return
						if (thtyz.getText().trim().length() == 0) {
							JOptionPane.showMessageDialog(null, "原因为空");
						} else {
							String sth = thtsl.getText().trim();
							if (sth.length() == 0) {
								JOptionPane.showMessageDialog(null, "数量为空");
							} else {
								try {
									int thsl = Integer.parseInt(sth);
									int r = xxtable.getSelectedRow();
									int yysl = Integer.parseInt(xxtable.getValueAt(r, 6).toString().trim());
									if (thsl <= yysl) {
										String yy = thtyz.getText().trim(); // 退货原因
										String dh = xxf_ShowNo.getText().trim(); // 获取单号
										String kh = mct.getText().trim(); // 获取客户名称
										int bh = Integer.parseInt(xxtable.getValueAt(r, 0).toString().trim());
										String xh = xxtable.getValueAt(r, 1).toString().trim(); // get
																								// product
																								// model
										String sp = xxtable.getValueAt(r, 2).toString().trim(); // 获取商品名称
										String dw = xxtable.getValueAt(r, 3).toString().trim();
										String szk = xxtable.getValueAt(r, 4).toString().trim();
										Double dj = Double.parseDouble(xxtable.getValueAt(r, 5).toString().trim());// 获取商品单价
										Double zk = null;
										Double thje = null;
										if (szk.length() == 0) {
											thje = dj * thsl;
										} else {
											zk = Double.parseDouble(szk);
											thje = dj * thsl * zk / 10;
										}
										d.gth(dh, kh, bh, xh, sp, dw, zk, dj, thsl, thje, yy, 2, user);
										w.wkcin(xh, sp, thsl, kh + "退货", user, dh);
										if (dh.substring(0, 1).equals("X")) {
											xxmdm.setDataVector(d.wxd(dh), mcn);
										} else {
											xxmdm.setDataVector(d.xsd(dh), mcn);
										}
										int row = xxtable.getRowCount();
										Double hj = 0.0;
										for (int i = 0; i < row; i++) {
											Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
											hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
										}
										xxf_ShowTotal.setText(String.format("%.2f", hj));
										TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
										cktablecxh.setPreferredWidth(40);
										cktablecxh.setMinWidth(40);
										cktablecxh.setMaxWidth(40);
										TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
										cktableczl.setPreferredWidth(70);
										cktableczl.setMinWidth(70);
										cktableczl.setMaxWidth(70);
										TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
										cktableccp.setPreferredWidth(180);
										cktableccp.setMinWidth(180);
										cktableccp.setMaxWidth(180);
										TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
										cktablecdw.setPreferredWidth(40);
										cktablecdw.setMinWidth(40);
										cktablecdw.setMaxWidth(40);
										TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
										cktablezk.setPreferredWidth(40);
										cktablezk.setMinWidth(40);
										cktablezk.setMaxWidth(40);
										TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
										cktablesl.setPreferredWidth(40);
										cktablesl.setMinWidth(40);
										cktablesl.setMaxWidth(40);
										xxf.setEnabled(true);
										thtsl.setEnabled(false);
										thtsl.setText("");
										thtyz.setText("");
										thf.dispose();
									} else {
										JOptionPane.showMessageDialog(null, "超出销售数量");
									}
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "输入非法");
								}
							}
						}
					} else {
						// ===============all product return===============
						if (thtyz.getText().trim().length() == 0) {
							JOptionPane.showMessageDialog(null, "原因为空");
						} else {
							int r = qSAR_MainTable.getSelectedRow();
							String yy = thtyz.getText().trim();
							String dh = qSAR_MainTable.getValueAt(r, 0).toString().trim();
							String kh = qSAR_MainTable.getValueAt(r, 1).toString().trim();
							int xrc = xxtable.getRowCount();
							for (int i = 0; i < xrc; i++) {
								String xh = xxtable.getValueAt(i, 1).toString().trim();
								String sp = xxtable.getValueAt(i, 2).toString().trim();
								String str = xxtable.getValueAt(i, 6).toString().trim();
								String dw = xxtable.getValueAt(i, 3).toString().trim();
								String szk = xxtable.getValueAt(i, 4).toString().trim();
								String tdj = xxtable.getValueAt(i, 5).toString().trim();
								Double dj = Double.parseDouble(tdj);
								int sl = Integer.parseInt(str);
								if (sl > 0) {
									Double zk = null;
									Double thje = null;
									if (szk.length() == 0) {
										thje = dj * sl;
									} else {
										zk = Double.parseDouble(szk);
										thje = dj * sl * zk / 10;
									}
									d.gth(dh, kh, i + 1, xh, sp, dw, zk, dj, sl, thje, yy, 3, user);
									w.wkcin(xh, sp, sl, kh + "退货", user, dh);
								} else {
									d.alterSkstatus(dh, i + 1, 3);
								}
							}
							xdm.setDataVector(d.xys(kh, "", "", ""), qSAR_MainTable_ColumnName);
							TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(1); // 设置列宽
							cktablecxh.setPreferredWidth(180);
							cktablecxh.setMinWidth(180);
							cktablecxh.setMaxWidth(180);
							qSAR_MainFrame.setEnabled(true);
							thtyz.setText("");
							thf.dispose();
						}
					}
				}
			}
		});
		// -------------------------------------退货数量输入监听------------------------------------------
		thtsl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == '\n') {
					if (thtyz.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "原因为空");
					} else {
						String sth = thtsl.getText().trim();
						if (sth.length() == 0) {
							JOptionPane.showMessageDialog(null, "数量为空");
						} else {
							try {
								int thsl = Integer.parseInt(sth);
								int r = xxtable.getSelectedRow();
								int yysl = Integer.parseInt(xxtable.getValueAt(r, 6).toString().trim());
								if (thsl <= yysl) {
									String yy = thtyz.getText().trim(); // 退货原因
									String dh = xxf_ShowNo.getText().trim(); // 获取单号
									String kh = mct.getText().trim(); // 获取客户名称
									int bh = Integer.parseInt(xxtable.getValueAt(r, 0).toString().trim());
									String xh = xxtable.getValueAt(r, 1).toString().trim(); // get
																							// product
																							// model
									String sp = xxtable.getValueAt(r, 2).toString().trim(); // 获取商品名称
									String dw = xxtable.getValueAt(r, 3).toString().trim();
									String szk = xxtable.getValueAt(r, 4).toString().trim();
									Double dj = Double.parseDouble(xxtable.getValueAt(r, 5).toString().trim());// 获取商品单价
									Double zk = null;
									Double thje = null;
									if (szk.length() == 0) {
										thje = dj * thsl;
									} else {
										zk = Double.parseDouble(szk);
										thje = dj * thsl * zk / 10;
									}
									d.gth(dh, kh, bh, xh, sp, dw, zk, dj, thsl, thje, yy, 2, user);
									w.wkcin(xh, sp, thsl, kh + "退货", user, dh);
									if (dh.substring(0, 1).equals("X")) {
										xxmdm.setDataVector(d.wxd(dh), mcn);
									} else {
										xxmdm.setDataVector(d.xsd(dh), mcn);
									}
									int row = xxtable.getRowCount();
									Double hj = 0.0;
									for (int i = 0; i < row; i++) {
										Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
										hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
									}
									xxf_ShowTotal.setText(String.format("%.2f", hj));
									TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
									cktablecxh.setPreferredWidth(40);
									cktablecxh.setMinWidth(40);
									cktablecxh.setMaxWidth(40);
									TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
									cktableczl.setPreferredWidth(70);
									cktableczl.setMinWidth(70);
									cktableczl.setMaxWidth(70);
									TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
									cktableccp.setPreferredWidth(180);
									cktableccp.setMinWidth(180);
									cktableccp.setMaxWidth(180);
									TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
									cktablecdw.setPreferredWidth(40);
									cktablecdw.setMinWidth(40);
									cktablecdw.setMaxWidth(40);
									TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
									cktablezk.setPreferredWidth(40);
									cktablezk.setMinWidth(40);
									cktablezk.setMaxWidth(40);
									TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
									cktablesl.setPreferredWidth(40);
									cktablesl.setMinWidth(40);
									cktablesl.setMaxWidth(40);
									xxf.setEnabled(true);
									thtsl.setEnabled(false);
									thtsl.setText("");
									thtyz.setText("");
									thf.dispose();
								} else {
									JOptionPane.showMessageDialog(null, "超出销售数量");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "输入非法");
							}
						}
					}
				}
			}
		});
		// --------------------------------------退货窗口监听-------------------------------------
		thf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (xxf.isVisible()) {
					xxf.setEnabled(true);
				} else {
					qSAR_MainFrame.setEnabled(true);
				}
				thtsl.setEnabled(false);
				thtsl.setText("");
				thtyz.setText("");
				thf.dispose();
			}
		});
		// --------------------------------------------右键菜单-------------------------------------
		JPopupMenu pm = new JPopupMenu();
		JMenuItem mit = new JMenuItem("详细列表");
		JMenuItem hz = new JMenuItem("添加坏账");
		JMenuItem qth = new JMenuItem("退货");
		qth.setEnabled(false);
		pm.add(mit);
		pm.add(hz);
		pm.add(qth);
		// ------------------------------------------表格监听---------------------------------
		qSAR_MainTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == 3) {
					int r = qSAR_MainTable.rowAtPoint(e.getPoint());
					if (qSAR_MainTable.getRowSelectionAllowed() == true) {
						qSAR_MainTable.setRowSelectionInterval(r, r);
						pm.show(qSAR_MainTable, e.getX(), e.getY());
						wzx = e.getX();
						wzy = e.getY();
					}
				}
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					qSAR_MainFrame.setEnabled(false);
					int r = qSAR_MainTable.getSelectedRow();
					String st = qSAR_MainTable.getValueAt(r, 0).toString().trim();
					if (st.substring(0, 1).equals("X")) {
						xxmdm.setDataVector(d.wxd(st), mcn);
					} else {
						xxmdm.setDataVector(d.xsd(st), mcn);
					}
					mit.doClick();
					// xf.setVisible(true);
				}
			}
		});
		// ----------------------------------------------------------------------------------------------
		qth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = qSAR_MainTable.getSelectedRow();
				String st = qSAR_MainTable.getValueAt(r, 0).toString().trim();
				if (st.substring(0, 1).equals("X")) {
					xxmdm.setDataVector(d.wxd(st), mcn);
				} else {
					xxmdm.setDataVector(d.xsd(st), mcn);
				}
				thf.setVisible(true);
				qSAR_MainFrame.setEnabled(false);
			}
		});
		// -----------------------------------Listener of show detailed
		// panel-------------------------------------------
		mit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = qSAR_MainTable.getSelectedRow();
				String st = qSAR_MainTable.getValueAt(r, 0).toString().trim();
				String sn = qSAR_MainTable.getValueAt(r, 1).toString().trim();
				xxf_ShowNo.setText(st);
				qSAR_MainFrame.setEnabled(false);
				List<String> ls = d.getcustomerinfo(sn);
				mct.setText(ls.get(0));
				lxrt.setText(ls.get(1));
				lxrtelt.setText(ls.get(2));
				addrt.setText(ls.get(3));
				jc.setSelectedIndex(d.getproceedsmethod(st));
				if (st.substring(0, 1).equals("X")) {
					xxmdm.setDataVector(d.wxd(st), mcn);
				} else {
					xxmdm.setDataVector(d.xsd(st), mcn);
				}
				int row = xxtable.getRowCount();
				hj = 0.0;
				for (int i = 0; i < row; i++) {
					Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
					hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
				}
				xxf_ShowTotal.setText(String.format("%.2f", hj));
				TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
				cktablecxh.setPreferredWidth(40);
				cktablecxh.setMinWidth(40);
				cktablecxh.setMaxWidth(40);
				TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
				cktableczl.setPreferredWidth(70);
				cktableczl.setMinWidth(70);
				cktableczl.setMaxWidth(70);
				TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
				cktableccp.setPreferredWidth(180);
				cktableccp.setMinWidth(180);
				cktableccp.setMaxWidth(180);
				TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
				cktablecdw.setPreferredWidth(40);
				cktablecdw.setMinWidth(40);
				cktablecdw.setMaxWidth(40);
				TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
				cktablezk.setPreferredWidth(40);
				cktablezk.setMinWidth(40);
				cktablezk.setMaxWidth(40);
				TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
				cktablesl.setPreferredWidth(40);
				cktablesl.setMinWidth(40);
				cktablesl.setMaxWidth(40);
				if (d.pdj(st).isEmpty() == false) {
					xxf_OldSaleReceipt_b.setVisible(true);
				}
				xxf.setVisible(true);
			}
		});
		// --------------------------------输入监听写入坏账---------------------------------------------
		hzt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == '\n') {
					int r = qSAR_MainTable.getSelectedRow();
					String dh = qSAR_MainTable.getValueAt(r, 0).toString().trim();
					String kh = qSAR_MainTable.getValueAt(r, 1).toString().trim();
					String st = qSAR_MainTable.getValueAt(r, 4).toString().trim();
					Double je = Double.parseDouble(st);
					String bz = hzt.getText().trim();
					d.whz(dh, kh, je, bz);
					String[][] xarr = d.xys(kh, "", "", "");
					xdm.setDataVector(xarr, qSAR_MainTable_ColumnName);
					hzt.setText("");
					qSAR_MainFrame.setEnabled(true);
					hzf.dispose();
				}
			}
		});
		// -----------------------------------------------------------------------------------------------
		hz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qSAR_MainFrame.setEnabled(false);
				hzf.setVisible(true);
			}
		});
		// -----------------------------------------------------------------------------------------------
		JScrollPane qSAR_MainFrame_Js = new JScrollPane();
		qSAR_MainFrame_Js.setViewportView(qSAR_MainTable);
		qSAR_MainFrame_Js.setBounds(10, 40, 625, 420);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_Js);
		// ---------------------------------------------------应收总面板-------------------------------------------
		// -----------------------------------------detail panel close
		// listener------------------------------------------
		xxf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				// int i=jtab.getSelectedRow();
				// xdm.setDataVector(d.xys(jtab.getValueAt(i,0).toString()),xcn);
				TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(1); // 设置列宽
				cktablecxh.setPreferredWidth(180);
				cktablecxh.setMinWidth(180);
				cktablecxh.setMaxWidth(180);
				xxf_OldSaleReceipt_b.setText("查看原单");
				xxf_OldSaleReceipt_b.setVisible(false);
				qSAR_MainFrame.setEnabled(true);
				xxf.dispose();
			}
		});
		// ==========================================proceeds
		// JTextField=====================
		xt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == '\n') {
					if (xxf.isVisible() == true) {
						int sr = xxtable.getSelectedRow();
						String s = xt.getText().trim();
						if (s.length() == 0) {
							JOptionPane.showMessageDialog(null, "金额为空值");
						} else {
							try {
								Double sk = Double.parseDouble(s);
								if (sk > 0) {
									Double zj = Double.parseDouble(xxtable.getValueAt(sr, 7).toString());
									Double ys = Double.parseDouble(xxtable.getValueAt(sr, 8).toString());
									if (sk > zj - ys) { // proceeds more than
														// receivables
										d.updateys(xxf_ShowNo.getText(), sr + 1, zj, 0);
									} else if (sk < zj - ys) {
										d.updateys(xxf_ShowNo.getText(), sr + 1, sk + ys, 1);
									} else if (sk.equals(zj - ys)) {
										d.updateys(xxf_ShowNo.getText(), sr + 1, zj, 0);
									}
								} else {
									JOptionPane.showMessageDialog(null, "输入为负");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "非法输入");
							}
							String st = xxf_ShowNo.getText();
							if (st.substring(0, 1).equals("X")) {
								xxmdm.setDataVector(d.wxd(st), mcn);
							} else {
								xxmdm.setDataVector(d.xsd(st), mcn);
							}
							hj = 0.0;
							for (int i = 0; i < xxtable.getRowCount(); i++) {
								Double sk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
								hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj - sk;
							}
							xxf_ShowTotal.setText(String.format("%.2f", hj));
							TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // 设置列宽
							cktablecxh.setPreferredWidth(40);
							cktablecxh.setMinWidth(40);
							cktablecxh.setMaxWidth(40);
							TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // 设置列宽
							cktableczl.setPreferredWidth(70);
							cktableczl.setMinWidth(70);
							cktableczl.setMaxWidth(70);
							TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // 设置列宽
							cktableccp.setPreferredWidth(180);
							cktableccp.setMinWidth(180);
							cktableccp.setMaxWidth(180);
							TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // 设置列宽
							cktablecdw.setPreferredWidth(40);
							cktablecdw.setMinWidth(40);
							cktablecdw.setMaxWidth(40);
							TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // 设置列宽
							cktablezk.setPreferredWidth(40);
							cktablezk.setMinWidth(40);
							cktablezk.setMaxWidth(40);
							TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // 设置列宽
							cktablesl.setPreferredWidth(40);
							cktablesl.setMinWidth(40);
							cktablesl.setMaxWidth(40);
							xxf.setEnabled(true);
							xt.setText("");
							xf.dispose();
						}
					} else {
						int sr = qSAR_MainTable.getSelectedRow();
						String s = xt.getText().trim();
						if (s.length() == 0) {
							JOptionPane.showMessageDialog(null, "金额为空值");
						} else {
							try {
								Double sk = Double.parseDouble(s);
								if (sk > 0) {
									Double ys = Double.parseDouble(qSAR_MainTable.getValueAt(sr, 4).toString());
									String dh = qSAR_MainTable.getValueAt(sr, 0).toString();
									if (sk > ys) { // proceeds more than
													// receivables
										int j = xxtable.getRowCount();
										for (int i = 0; i < j; i++) {
											Double je = Double.parseDouble(xxtable.getValueAt(i, 7).toString());
											d.updateys(dh, i + 1, je, 0);
										}
									} else if (sk < ys) {
										// System.out.println("<");
										int j = xxtable.getRowCount();
										for (int i = 0; i < j; i++) {
											Double zje = Double.parseDouble(xxtable.getValueAt(i, 7).toString());
											Double ysk = Double.parseDouble(xxtable.getValueAt(i, 8).toString());
											if (zje > ysk) {
												// System.out.println(i+1);
												if (sk - (zje - ysk) >= 0) {
													d.updateys(dh, i + 1, zje, 0);
													sk = sk - (zje - ysk);
												} else {
													d.updateys(dh, i + 1, sk + ysk, 1);
													break;
												}
											}
										}
									} else {
										// System.out.println("=");
										int j = xxtable.getRowCount();
										for (int i = 0; i < j; i++) {
											Double je = Double.parseDouble(xxtable.getValueAt(i, 7).toString());
											d.updateys(dh, i + 1, je, 0);
										}
									}
								} else {
									JOptionPane.showMessageDialog(null, "输入为负或为零");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "非法输入");
							}
							String kh = qSAR_MainTable.getValueAt(sr, 1).toString().trim();
							String[][] xarr = d.xys(kh, "", "", "");
							xdm.setDataVector(xarr, qSAR_MainTable_ColumnName);
							TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(1); // 设置列宽
							cktablecxh.setPreferredWidth(180);
							cktablecxh.setMinWidth(180);
							cktablecxh.setMaxWidth(180);
							qSAR_MainFrame.setEnabled(true);
							xt.setText("");
							xf.dispose();
						}
					}
				}
			}
		});
		// --------------------------------------------------------------------------------------------------------
		qSAR_MainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				qSAR_MainFrame.dispose();
			}
		});
		// ---------------------------------------------------------------------------------------------------------
		hzf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				qSAR_MainFrame.setEnabled(true);
				hzf.dispose();
			}
		});
		// --------------------------------------------------------------------------------------------------------
		xf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (xxf.isVisible()) {
					xxf.setEnabled(true);
					xf.dispose();
				} else {
					qSAR_MainFrame.setEnabled(true);
					xf.dispose();
				}

			}
		});
		// -------------------------------------------------------------------------------------------------------
		qSAR_MainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new QuerySaleAndRepair("test");
	}

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
