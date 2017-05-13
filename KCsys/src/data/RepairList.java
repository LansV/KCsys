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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import security.CheckDate;
import security.Lock;
import security.SQLFilter;
import tool.Printclass;

public class RepairList {
	String mc = ""; // ȫ�ֿͻ�����
	String lxr = ""; // ȫ����ϵ��
	String lxtel = ""; // ȫ����ϵ�绰
	String addr = ""; // ȫ�ֵ�ַ
	Double hj; // �ϼ�
	JLabel showhj = new JLabel(); // ��ʾ�ϼ�
	public RepairList(String user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// �������ʱ��
		Date date2 = new Date();
		String s1 = sdf.format(date2);
		CheckDate.ReturnCheckDateResult(s1);
		RepairListData gd = new RepairListData(); // ����������
		wData w = new wData();
		List<String> spcount = new ArrayList<String>(); // ��Ʒ����
		List<Integer> kccount = new ArrayList<Integer>(); // �������
		// -------------------------------------��ʾ���---------------------------------------------------
		JPanel mp = new JPanel();
		JScrollPane msp = new JScrollPane();
		JPopupMenu rightmenu = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("ɾ��");
		rightmenu.add(deleteItem);
		JTable mtable = new JTable() {
			private static final long serialVersionUID = 1L;

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (columnIndex == 4) {
					try {
						String st = aValue.toString();
						if (st.length() != 0) {
							Double price = Double.parseDouble(st);
							if (price < 0) {
								JOptionPane.showMessageDialog(null, "ֻ����������");
								return;
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ֻ����������!");
						return;
					}
				}
				if (columnIndex == 5) {
					try {
						String st = aValue.toString();
						Double price = Double.parseDouble(st);
						if (price < 0) {
							JOptionPane.showMessageDialog(null, "ֻ����������");
							return;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ֻ����������!");
						return;
					}
				}
				if (columnIndex == 6) {
					try {
						String st = (String) aValue;
						int num = Integer.parseInt(st);
						if (num > kccount.get(rowIndex)) {
							JOptionPane.showMessageDialog(null, "�������");
							return;
						}
						if (num <= 0) {
							JOptionPane.showMessageDialog(null, "����Ϊ0��С��0");
							return;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ֻ����������!");
						return;
					}
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}
		};
		mtable.setRowHeight(20);
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
		String[] mcn = { "���", "��Ʒ�ͺ�", "��Ʒ����", "��λ", "�ۿ�", "����", "����", "���", "��ע" };
		DefaultTableModel mdm = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				if (colunm > 3 && colunm < 9 && colunm != 7) {
					return true;
				}
				return false;
			}
		};
		mtable.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		mtable.setDefaultRenderer(Object.class, tcr);
		mdm.setColumnIdentifiers(mcn);
		mtable.setModel(mdm);
		TableColumn cktablecxh = mtable.getColumnModel().getColumn(0); // �����п�
		cktablecxh.setPreferredWidth(40);
		cktablecxh.setMinWidth(40);
		cktablecxh.setMaxWidth(40);
		TableColumn cktableczl = mtable.getColumnModel().getColumn(1); // �����п�
		cktableczl.setPreferredWidth(120);
		cktableczl.setMinWidth(120);
		cktableczl.setMaxWidth(120);
		TableColumn cktableccp = mtable.getColumnModel().getColumn(2); // �����п�
		cktableccp.setPreferredWidth(180);
		cktableccp.setMinWidth(180);
		cktableccp.setMaxWidth(180);
		TableColumn cktablecdw = mtable.getColumnModel().getColumn(3); // �����п�
		cktablecdw.setPreferredWidth(40);
		cktablecdw.setMinWidth(40);
		cktablecdw.setMaxWidth(40);
		// ============================================ɾ����============================
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int r = mtable.getSelectedRow();
				mdm.removeRow(r);
				spcount.remove(r);
				kccount.remove(r);
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
		msp.setBounds(0, 0, 700, 450);
		mp.setLayout(null);
		mp.add(msp);
		mp.setBounds(18, 100, 750, 450);
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
		// --------------------------------��Ʒ����------------------------------------------------------
		JFrame spsl = new JFrame("��д����");
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
		// ------------------------------------��Ʒѡ�����---------------------------------------------------
		JFrame sp = new JFrame("��Ʒ");
		sp.setResizable(false);
		Container spc = sp.getContentPane();
		spc.setLayout(null);
		JTextField spjt = new JTextField();
		spjt.setBounds(20, 10, 120, 25);
		String[] spcn = { "��Ʒ�ͺ�", "��Ʒ����", "��λ", "����", "����" };
		JTable sptable = new JTable();
		sptable.setRowHeight(20);
		sptable.setDefaultRenderer(Object.class, tcr);
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
						JOptionPane.showMessageDialog(null, "�������Ʒ");
					} else {
						String s = sptable.getValueAt(spr, 4).toString();
						int i = Integer.parseInt(s);
						if (i == 0) {
							JOptionPane.showMessageDialog(null, "���Ϊ��");
						} else {
							sptx.setText("");
							spsl.setVisible(true);
							splb.setText(sptable.getValueAt(spr, 1).toString().trim());
						}
					}
				}
			}
		});
		sptable.getTableHeader().setReorderingAllowed(false);
		String[][] sparr = gd.spcxdj(spjt.getText().trim()); // ��ȡ��Ʒ��Ϣ�����ۣ�
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
		TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // �����п�
		sptablecl1.setPreferredWidth(80);
		sptablecl1.setMinWidth(80);
		sptablecl1.setMaxWidth(80);
		TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // �����п�
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
					new SQLFilter(spjt, spjt.getText().trim(), user);
					String[][] sparr = gd.spcxdj(spjt.getText().trim()); // ��Ʒ��ѯ�����ۣ�
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
					TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // �����п�
					sptablecl1.setPreferredWidth(80);
					sptablecl1.setMinWidth(80);
					sptablecl1.setMaxWidth(80);
					TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // �����п�
					sptablecl.setPreferredWidth(180);
					sptablecl.setMinWidth(180);
					sptablecl.setMaxWidth(180);
				}
			}
		});
		spj.add(spjs);
		spc.add(spj);
		spc.add(spjt);
		sp.setBounds(760, 10, 500, 650);
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ------------------------------------����ģ�ͼ���-------------------------------------------------
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
							// System.out.println(d+" "+s);
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
								JOptionPane.showMessageDialog(null, "����Ƿ�");
							}
							Double zk = Double.parseDouble(z) / 10;
							// System.out.println(d+" "+s+" "+zk);
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
		// ------------------------------------��Ʒ��д����-------------------------------------------------
		sptx.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr = mtable.getRowCount();
				int spr = sptable.getSelectedRow();
				if (e.getKeyCode() == '\n') {
					new SQLFilter(sptx, sptx.getText().trim(), user);
					if (sptx.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "δ��д����");
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
							JOptionPane.showMessageDialog(null, "�ۿ�����Ƿ�");
							b = false;
						}
						try {
							String s = sptx.getText().trim();
							int pdnum = Integer.parseInt(s);
							String skc = sptable.getValueAt(spr, 4).toString().trim();
							int kcsl = Integer.parseInt(skc);
							if (pdnum > kcsl) {
								JOptionPane.showMessageDialog(null, "�������");
							} else {
								if (pdnum <= 0) {
									JOptionPane.showMessageDialog(null, "����Ϊ0��С��0");
								} else {
									if (b == true) {
										mdm.addRow(row);
										mtable.setValueAt(mr + 1, mr, 0);
										mtable.setValueAt(sptable.getValueAt(spr, 0), mr, 1);
										mtable.setValueAt(sptable.getValueAt(spr, 1), mr, 2);
										mtable.setValueAt(sptable.getValueAt(spr, 2), mr, 3);
										mtable.setValueAt(spzk.getText().trim(), mr, 4);
										mtable.setValueAt(sptable.getValueAt(spr, 3), mr, 5);
										kccount.add(kcsl);
										mtable.setValueAt(s, mr, 6);
										spsl.dispose();
										spcount.add(sptable.getValueAt(spr, 1).toString().trim());
									}
								}
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "����д����");
							sptx.setText("");
						}
					}
				}
			}
		});
		spsl.setBounds(700, 150, 250, 150);
		spsl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ------------------------------------ѡ��ͻ����-------------------------------------------------
		JFrame khf = new JFrame("ѡ��ͻ�");
		khf.setResizable(false);
		Container khfc = khf.getContentPane();
		khfc.setLayout(null);
		khf.setBounds(700, 10, 450, 650);
		khf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JTextField cxjt = new JTextField();
		cxjt.setBounds(10, 10, 120, 25);
		khfc.add(cxjt);
		String ss = "";
		String[][] arr = gd.khx(ss); // ��ȡ�ͻ���Ϣ
		String[] cxcn = { "���", "����", "��ϵ��", "�绰", "��ַ" };
		JScrollPane cxjsp = new JScrollPane();
		JTable jtab = new JTable();
		jtab.setRowHeight(20);
		jtab.setDefaultRenderer(Object.class, tcr);
		jtab.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel khcxdm = new DefaultTableModel(arr, cxcn) { // tableģ��
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // ���ز��ɱ༭
			}
		};
		jtab.setModel(khcxdm);
		TableColumn jtab0 = jtab.getColumnModel().getColumn(0);
		jtab0.setPreferredWidth(60);
		jtab0.setMaxWidth(60);
		jtab0.setMinWidth(60);
		TableColumn jtab1 = jtab.getColumnModel().getColumn(1);
		jtab1.setPreferredWidth(220);
		jtab1.setMaxWidth(220);
		jtab1.setMinWidth(220);
		TableColumn jtab2 = jtab.getColumnModel().getColumn(2);
		jtab2.setPreferredWidth(80);
		jtab2.setMaxWidth(80);
		jtab2.setMinWidth(80);
		TableColumn jtab3 = jtab.getColumnModel().getColumn(3);
		jtab3.setPreferredWidth(90);
		jtab3.setMaxWidth(90);
		jtab3.setMinWidth(90);
		TableColumn jtab4 = jtab.getColumnModel().getColumn(4);
		jtab4.setPreferredWidth(280);
		jtab4.setMaxWidth(280);
		jtab4.setMinWidth(280);
		cxjt.addKeyListener(new KeyAdapter() { // jtextfield����ı������
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == '\n') {
					new SQLFilter(cxjt, cxjt.getText().trim(), user);
					String[][] arr2 = gd.khx(cxjt.getText().trim());
					khcxdm.setDataVector(arr2, cxcn);
					TableColumn jtab0 = jtab.getColumnModel().getColumn(0);
					jtab0.setPreferredWidth(60);
					jtab0.setMaxWidth(60);
					jtab0.setMinWidth(60);
					TableColumn jtab1 = jtab.getColumnModel().getColumn(1);
					jtab1.setPreferredWidth(220);
					jtab1.setMaxWidth(220);
					jtab1.setMinWidth(220);
					TableColumn jtab2 = jtab.getColumnModel().getColumn(2);
					jtab2.setPreferredWidth(80);
					jtab2.setMaxWidth(80);
					jtab2.setMinWidth(80);
					TableColumn jtab3 = jtab.getColumnModel().getColumn(3);
					jtab3.setPreferredWidth(90);
					jtab3.setMaxWidth(90);
					jtab3.setMinWidth(90);
					TableColumn jtab4 = jtab.getColumnModel().getColumn(4);
					jtab4.setPreferredWidth(280);
					jtab4.setMaxWidth(280);
					jtab4.setMinWidth(280);
				}
			}
		});
		JPanel cxjp = new JPanel();
		jtab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		cxjp.setBounds(10, 50, 450, 550);
		cxjsp.setViewportView(jtab);
		cxjsp.setBounds(0, 0, 420, 550);
		// cxjsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		cxjp.setLayout(null);
		cxjp.add(cxjsp);
		khfc.add(cxjp);
		// -----------------------------------------�����----------------------------------------------------
		JFrame mf = new JFrame("ά��");
		mf.setResizable(false);
		Container mfc = mf.getContentPane();
		JComboBox<String> jc = new JComboBox<String>();
		jc.addItem("��ݴ���");
		jc.addItem("�ֽ�");
		jc.addItem("����");
		jc.addItem("����");
		jc.setBounds(620, 60, 80, 25);
		JComboBox<String> jcrepairman = new JComboBox<String>();
		String[][] repairmanarr = gd.getSaleman();
		int repairmanarrc = repairmanarr.length;
		jcrepairman.addItem("��ѡ��ҵ��Ա");
		for (int i = 0; i < repairmanarrc; i++) {
			jcrepairman.addItem(repairmanarr[i][1]);
		}
		jcrepairman.setBounds(480, 60, 110, 25);
		mfc.add(jcrepairman);
		JButton kh_b = new JButton("ѡ��ͻ�");
		kh_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				khf.setVisible(true);
			}
		});
		kh_b.setBounds(370, 60, 90, 25);
		JPanel jtp = new JPanel();
		jtp.setLayout(null);
		jtp.add(kh_b);
		jtp.add(jc);
		JLabel mcl = new JLabel("�ͻ�:");
		mcl.setBounds(10, 20, 40, 25);
		JTextField mct = new JTextField();
		mct.setBounds(50, 20, 180, 25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel showkhid = new JLabel();
		showkhid.setBounds(260, 20, 80, 25);
		jtp.add(showkhid);
		JLabel lxrl = new JLabel("��ϵ��:");
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
		JLabel addrl = new JLabel("��ַ:");
		addrl.setBounds(10, 60, 40, 25);
		jtp.add(addrl);
		JTextField addrt = new JTextField();
		addrt.setBounds(50, 60, 300, 25);
		jtp.add(addrt);
		JLabel ml = new JLabel();
		ml.setBounds(20, 560, 80, 25);
		jtp.setBounds(0, 0, 750, 90);
		JButton mbutton = new JButton("���");
		mbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sp.setVisible(true);
			}
		});
		mbutton.setBounds(670, 560, 60, 25);
		showhj.setBounds(600, 560, 60, 25);
		JTextField pumpNo = new JTextField();
		pumpNo.setBounds(120, 560, 120, 25);
		mfc.add(pumpNo);
		JButton mxsb = new JButton("����");
		mxsb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int cr = mtable.getRowCount();
				if (cr == 0) {
					JOptionPane.showMessageDialog(null, "������");
				} else {
					if (mtable.isEditing() == true) {
						mtable.getCellEditor().stopCellEditing();
					}
					String dh = ml.getText().trim();
					mc = mct.getText();
					if (mc.length() == 0) {
						JOptionPane.showMessageDialog(null, "�ͻ�δ��д");
					} else if (dh.length() != 0 && mc.length() != 0) {
						if (dh.equals(gd.wxdh())) {
							String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����
							try {
								Date date2 = new Date();
								URL url = new URL(webUrl4);// ȡ����Դ����
								URLConnection uc = url.openConnection();// �������Ӷ���
								uc.connect();// ��������
								long ld = uc.getDate();// ��ȡ��վ����ʱ��
								Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// �������ʱ��
								String s1 = sdf.format(date2);
								String s = sdf.format(date);
								if (s.equals(s1) == true) {
									// JOptionPane.showMessageDialog(null,"���ں˶Գɹ�");
								} else {
									JOptionPane.showMessageDialog(null, "ϵͳ��������������ڲ����\n��ͬ��ϵͳ���ں�����");
									System.exit(0);
								}
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "�������磬ȷ�����糩ͨ");
								System.exit(0);
							}
							int slhj = 0;
							List<Object> listkh = new ArrayList<Object>();
							List<Object> listsp = new ArrayList<Object>();
							List<Object> listhj = new ArrayList<Object>();
							lxr = lxrt.getText().trim();
							lxtel = lxrtelt.getText().trim();
							addr = addrt.getText().trim();
							String sPumpNo = pumpNo.getText().trim();
							listkh.add(dh);
							listkh.add(mc);
							listkh.add(lxr);
							listkh.add(lxtel);
							listkh.add(addr);
							listkh.add(jc.getSelectedItem());
							String khid = showkhid.getText().trim();
							String repairid;
							String repairman;
							int jcrepairs = jcrepairman.getSelectedIndex();
							if (jcrepairs == 0) {
								repairid = "0";
								repairman = "�ܹ�˾";
							} else {
								repairid = repairmanarr[jcrepairs - 1][0];
								repairman = jcrepairman.getSelectedItem().toString();
							}
							new SQLFilter(jtp, mc + lxr + lxtel + addr + sPumpNo, user);
							if (mct.isEditable() == true) {
								if (khid.equals(gd.getCustomerNo().toString())) {
									w.wkh(khid, mc, lxr, lxtel, addr, "0");
									mct.setEditable(false);
									lxrt.setEditable(false);
									lxrtelt.setEditable(false);
									addrt.setEditable(false);
								} else {
									JOptionPane.showMessageDialog(mct, "�ͻ���Ŵ��ڲ���");
								}
							}
							Boolean eq = false;
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
								listsp.add(xhs);
								listsp.add(xh);
								listsp.add(sp);
								listsp.add(dw);
								listsp.add(xhs4);
								listsp.add(xhs5);
								listsp.add(xhs6);
								listsp.add(xhs7);
								listsp.add(bz);
								eq = gd.wx(dh, khid, mc, bh, xh, sp, dw, zk, dj, sl, je, bz, jc.getSelectedIndex(),
										user, repairid, repairman, sPumpNo);
								if (eq) {
									if (sp.equals("�˹���") == false) {
										int error = w.wkcout(xh, sp, sl, "2", user, dh);
										if (error > 0) {
											switch (error) {
											case 1:
												JOptionPane.showMessageDialog(mf, "�õ��������ʧ��");
												break;
											case 2:
												JOptionPane.showMessageDialog(mf, "���С����");
												break;
											case 3:
												JOptionPane.showMessageDialog(mf, "�������");
												break;
											}
										}
									}
								} else {
									if (i == 0) {
										if (dh.equals(gd.wxdh()) == true) {
											JOptionPane.showMessageDialog(mf, "����δ֪����������");
										} else {
											JOptionPane.showMessageDialog(mf, "���������ظ����Զ���������");
											ml.setText(gd.wxdh());
											JOptionPane.showMessageDialog(mf, "�����ɹ��������µ������");
										}
									} else {
										JOptionPane.showMessageDialog(mf, "���ش���\n����������������ֵ�ǰ״̬������ϵ����Ա");
									}
									break;
								}
							}
							String[][] sparr = gd.spcxdj(spjt.getText().trim());
							DefaultTableModel spdm = new DefaultTableModel(sparr, spcn) {
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row, int colunm) {
									return false;
								}
							};
							spdm.setColumnIdentifiers(spcn);
							sptable.setModel(spdm);
							TableColumn sptablecl1 = sptable.getColumnModel().getColumn(0); // �����п�
							sptablecl1.setPreferredWidth(80);
							sptablecl1.setMinWidth(80);
							sptablecl1.setMaxWidth(80);
							TableColumn sptablecl = sptable.getColumnModel().getColumn(1); // �����п�
							sptablecl.setPreferredWidth(180);
							sptablecl.setMinWidth(180);
							sptablecl.setMaxWidth(180);
							// w.wys(dh, mc, hj);
							if (eq) {
								listhj.add(changenum(hj));
								listhj.add(slhj);
								listhj.add(String.format("%.2f", hj));
								mdm.setRowCount(0);
								spcount.clear(); // clear count
								kccount.clear(); // clear count
								ml.setText(gd.wxdh());
								Printclass.setTitel("������ϴ�豸���޹�˾ά�޵�");
								Printclass.setUser(user);
								Printclass.setJsr(repairman);
								Printclass.setkhls(listkh);
								Printclass.setsp(listsp);
								Printclass.sethj(listhj);
								new Printclass();
							}
							/*
							 * int khselect=JOptionPane.showConfirmDialog(null,
							 * "�Ƿ��������", "ѡ��",0); if(khselect==0){
							 * khf.setVisible(true); }else{ mf.dispose();
							 * sp.dispose(); }
							 */
						} else {
							JOptionPane.showMessageDialog(null, "���Ŵ��ڲ��죬�Զ���������");
							ml.setText(gd.wxdh());
							JOptionPane.showMessageDialog(null, "�����ɹ��������µ������");
						}

					}
				}
			}
		});
		mxsb.setBounds(300, 560, 60, 25);
		// ----------------------------------------------------------------------
		JFrame rL_LabourFrame = new JFrame("�˹���");
		rL_LabourFrame.setResizable(false);
		rL_LabourFrame.setBounds(700, 150, 250, 120);
		Container rL_LabourFrame_Content = rL_LabourFrame.getContentPane();
		rL_LabourFrame_Content.setLayout(null);
		JTextField rL_LabourFrame_TextF = new JTextField();
		rL_LabourFrame_TextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr = mtable.getRowCount();
				if (e.getKeyChar() == '\n') {
					new SQLFilter(rL_LabourFrame_TextF, rL_LabourFrame_TextF.getText().trim(), user);
					try {
						Double LF_labour = Double.parseDouble(rL_LabourFrame_TextF.getText().trim());
						if (LF_labour <= 0) {
							JOptionPane.showMessageDialog(null, "�˹��ѱ��������");
						} else {
							mdm.addRow(row);
							mtable.setValueAt(mr + 1, mr, 0);
							mtable.setValueAt("", mr, 1);
							mtable.setValueAt("�˹���", mr, 2);
							mtable.setValueAt("��", mr, 3);
							mtable.setValueAt("", mr, 4);
							mtable.setValueAt(String.format("%.2f", LF_labour), mr, 5);
							kccount.add(1);
							mtable.setValueAt("1", mr, 6);
							mf.setEnabled(true);
							rL_LabourFrame.dispose();
							spcount.add("�˹���");
							rL_LabourFrame_TextF.setText("");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "����������");
					}
				}
			}
		});
		rL_LabourFrame_TextF.setBounds(10, 27, 120, 25);
		rL_LabourFrame_Content.add(rL_LabourFrame_TextF);
		JButton rL_LabourFrame_LabourB = new JButton("�˹���");
		rL_LabourFrame_LabourB.setBounds(450, 560, 75, 25);
		rL_LabourFrame_LabourB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (spcount.size() == 0) {
					rL_LabourFrame.setVisible(true);
				} else {
					for (int i = 0; i < spcount.size(); i++) {
						if (spcount.get(i).equals("�˹���") == true) {
							JOptionPane.showMessageDialog(rL_LabourFrame, "�����ظ����");
						} else {
							rL_LabourFrame.setVisible(true);
						}
					}
				}
			}
		});
		mfc.add(rL_LabourFrame_LabourB);
		mfc.setLayout(null);
		mfc.add(jtp);
		mfc.add(mxsb);
		mfc.add(ml);
		mfc.add(mp);
		mfc.add(mbutton);
		mfc.add(showhj);
		mf.setBounds(20, 50, 750, 630);
		mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (Lock.SingleUnLock(mf, "lock/RepairList.txt")) {
					mf.dispose();
				}
				khf.dispose();
				sp.dispose();
			}
		});
		//
		khf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (!mf.isVisible()) {
					if (Lock.SingleUnLock(mf, "lock/RepairList.txt")) {
						khf.dispose();
					}
				} else {
					khf.dispose();
				}
			}
		});
		// --------------------------------------�ͻ�ѡ�����-------------------------------------------------
		jtab.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2 && e.getButton() == 1) {
					int r = jtab.getSelectedRow();
					showkhid.setText(jtab.getValueAt(r, 0).toString().trim());
					mc = jtab.getValueAt(r, 1).toString().trim();
					lxr = jtab.getValueAt(r, 2).toString().trim();
					lxtel = jtab.getValueAt(r, 3).toString().trim();
					addr = jtab.getValueAt(r, 4).toString().trim();
					mct.setText(mc);
					mct.setEditable(false);
					lxrt.setText(lxr);
					lxrt.setEditable(false);
					lxrtelt.setText(lxtel);
					lxrtelt.setEditable(false);
					addrt.setText(addr);
					addrt.setEditable(false);
					khf.dispose();
					String s = gd.wxdh();
					// System.out.println(s);
					ml.setText(s);
					mf.setVisible(true);
				}
			}
		});
		// -------------------------------------------------------------------------------------------
		int r = JOptionPane.showConfirmDialog(null, "�ͻ��Ƿ�Ϊ�¿ͻ�", "ѡ��", JOptionPane.YES_NO_OPTION);// ����ѡ��ֵ
		if (r == 0) {
			String s = gd.wxdh();
			showkhid.setText(gd.getCustomerNo().toString());
			// System.out.println(s);
			ml.setText(s);
			mf.setVisible(true);
		} else {
			khf.setVisible(true);
		}
		// -----------------------------------------------------------------------------------------
	}

	public static void main(String[] args) {
		new RepairList("test");
	}

	// -------------------------------------------------------------------------------------
	public String changenum(Double numb) {
		String num[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
		String d[] = { "Ԫ", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ" };
		String x[] = { "��", "��" };
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
			sb.append("��");
		} else {
			sb.append(num[xl]);
			sb.append(x[1]);
		}
		String regex1[] = { "��Ǫ", "���", "��ʰ" };
		String regex2[] = { "����", "����", "��Ԫ" };
		String regex3[] = { "��", "��", "Ԫ" };
		String send = new String(sb);
		for (int i = 0; i < 3; i++) {
			send = send.replaceAll(regex1[i], "��");
		}
		for (int i = 0; i < 3; i++) {
			send = send.replaceAll("������", "��");
			send = send.replaceAll("����", "��");
			send = send.replaceAll(regex2[i], regex3[i]);
		}
		send = send.replaceAll("���", "");
		send = send.replaceAll("���", "");
		return send;
	}
}
