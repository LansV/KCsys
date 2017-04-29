package data;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import security.Lock;
import tool.Printclass;

public class QueryAllOrder {
	QueryAllOrderData d = new QueryAllOrderData();
	wData w = new wData();
	Double hj;
	Image img = null;
	int tabler;
	int wzx;
	int wzy;

	public static void main(String[] args) {
		new QueryAllOrder("test");
	}

	public QueryAllOrder(String user) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); // ������Ⱦ��
		tcr.setHorizontalAlignment(JLabel.CENTER); // ȫ�־���
		String[] mcn = { "���", "��Ʒ�ͺ�", "��Ʒ����", "��λ", "�ۿ�", "����", "����", "���", "��ע", "״̬", "����" };
		// ======================================detailed
		// panel=================================
		JLabel xxf_ShowTotal = new JLabel("", JLabel.CENTER);
		JFrame xxf = new JFrame("��������");
		try {
			img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
			xxf.setIconImage(img);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ȡϵͳͼ�����");
		}
		xxf.setResizable(false);
		Container xxfc = xxf.getContentPane();
		// =====================================================table===========================
		JTable xxtable = new JTable();
		xxtable.getTableHeader().setReorderingAllowed(false);
		xxtable.getTableHeader().setResizingAllowed(false);
		DefaultTableModel xxmdm = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				if(colunm==8){
					return true;
				}
				return false;
			}
		};
		xxtable.setModel(xxmdm);
		JPanel mp = new JPanel();
		JScrollPane msp = new JScrollPane();
		msp.setViewportView(xxtable);
		xxtable.setRowHeight(20);
		xxtable.setDefaultRenderer(Object.class, tcr);
		// ==================================================table==========================================
		JPanel jtp = new JPanel();
		jtp.setLayout(null);
		// jtp.add(jc);
		JLabel mcl = new JLabel("�ͻ�:");
		mcl.setBounds(10, 20, 40, 25);
		JTextField mct = new JTextField();
		mct.setEditable(false);
		mct.setBounds(50, 20, 180, 25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel lxrl = new JLabel("��ϵ��:");
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
		JLabel addrl = new JLabel("��ַ:");
		addrl.setBounds(10, 60, 40, 25);
		jtp.add(addrl);
		JTextField addrt = new JTextField();
		addrt.setEditable(false);
		addrt.setBounds(50, 60, 300, 25);
		jtp.add(addrt);
		JLabel custid = new JLabel();
		custid.setBounds(400, 60, 90, 25);
		jtp.add(custid);
		JLabel salemanidL = new JLabel("");
		salemanidL.setBounds(500, 60, 60, 25);
		jtp.add(salemanidL);
		JLabel salemanL = new JLabel("");
		salemanL.setBounds(530, 60, 90, 25);
		jtp.add(salemanL);
		JLabel xxf_ShowNo = new JLabel("");
		xxf_ShowNo.setBounds(20, 560, 80, 25);
		jtp.setBounds(0, 0, 850, 90);
		msp.setBounds(0, 0, 810, 450);
		mp.setLayout(null);
		// -------------------------
		mp.add(msp);
		mp.setBounds(18, 100, 850, 450);
		JButton addsalelist_button = new JButton("����");
		addsalelist_button.setBounds(350, 560, 60, 25);
		xxfc.add(addsalelist_button);
		xxf_ShowTotal.setBounds(593, 560, 60, 25);
		xxfc.setLayout(null);
		xxfc.add(jtp);
		xxfc.add(xxf_ShowNo);
		xxfc.add(mp);
		xxfc.add(xxf_ShowTotal);
		xxf.setBounds(410, 120, 850, 630);
		xxf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// -------------------------------------overview
		// panel-------------------------------------------
		JFrame qSAR_MainFrame = new JFrame("���ݲ�ѯ");
		try {
			img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
			qSAR_MainFrame.setIconImage(img);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ȡϵͳͼ�����");
		}
		qSAR_MainFrame.setResizable(false);
		qSAR_MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		qSAR_MainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if (Lock.SingleUnLock(qSAR_MainFrame, "order/lock/QuerySaleAndRepair.txt")) {
					qSAR_MainFrame.dispose();
				}
			}
		});
		Container qSAR_MainFrame_Content = qSAR_MainFrame.getContentPane();
		qSAR_MainFrame_Content.setLayout(null);
		qSAR_MainFrame.setBounds(400, 100, 650, 500);
		JButton qSAR_MainFrame_QueryB = new JButton("��ѯ");
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
		JLabel qSAR_MainFrame_QueryLabel = new JLabel("��");
		qSAR_MainFrame_QueryLabel.setBounds(360, 10, 30, 22);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_QueryLabel);
		JTable qSAR_MainTable = new JTable();
		qSAR_MainTable.getTableHeader().setReorderingAllowed(false);
		qSAR_MainTable.setDefaultRenderer(Object.class, tcr);
		String[] qSAR_MainTable_ColumnName = { "����", "���", "����", "��������", "��B", "���", "�µ���" };
		DefaultTableModel xdm = new DefaultTableModel(d.xys("", "", "", ""), qSAR_MainTable_ColumnName) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // ���ز��ɱ༭
			}
		};
		qSAR_MainTable.setModel(xdm);
		TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(2); // �����п�
		cktablecxh.setPreferredWidth(180);
		cktablecxh.setMinWidth(180);
		cktablecxh.setMaxWidth(180);
		qSAR_MainTable.setRowHeight(20);
		// ===========================================���ݲ�ѯ=========================================
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
					TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(2); // �����п�
					cktablecxh.setPreferredWidth(180);
					cktablecxh.setMinWidth(180);
					cktablecxh.setMaxWidth(180);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(qSAR_MainFrame, "���ڸ�ʽ����");
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
		// --------------------------------------------�Ҽ��˵�-------------------------------------
		JPopupMenu pm = new JPopupMenu();
		JMenuItem mit = new JMenuItem("��ϸ�б�");
		pm.add(mit);
		// ----------------------------------------------��������-------------------------------------
		addsalelist_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int src = xxtable.getSelectedRowCount();
				if (src <= 0) {
					JOptionPane.showMessageDialog(xxf, "��ѡ�������\nȫ��������ȫѡ");
				} else {
					int[] sr = xxtable.getSelectedRows();
					boolean checkb = false;
					String[][] insertdata = new String[sr.length][9];
					List<Object> splist = new ArrayList<Object>();
					int slhj = 0;
					Double hj = 0.0;
					for (int i = 0; i < sr.length; i++) {
						String kcstatus = xxtable.getValueAt(sr[i], 9).toString();
						if (kcstatus.equals("�ɳ���")) {
							insertdata[i][0] = Integer.toString(i + 1);
							splist.add(Integer.toString(i + 1));
							insertdata[i][1] = xxtable.getValueAt(sr[i], 1).toString();
							splist.add(xxtable.getValueAt(sr[i], 1).toString());
							insertdata[i][2] = xxtable.getValueAt(sr[i], 2).toString();
							splist.add(xxtable.getValueAt(sr[i], 2).toString());
							insertdata[i][3] = xxtable.getValueAt(sr[i], 3).toString();
							splist.add(xxtable.getValueAt(sr[i], 3).toString());
							if (xxtable.getValueAt(sr[i], 4).toString().length() == 0) {
								insertdata[i][4] = "NULL";
							} else {
								insertdata[i][4] = xxtable.getValueAt(sr[i], 4).toString();
							}
							splist.add(xxtable.getValueAt(sr[i], 4).toString());
							insertdata[i][5] = xxtable.getValueAt(sr[i], 5).toString();
							splist.add(xxtable.getValueAt(sr[i], 5).toString());
							insertdata[i][6] = xxtable.getValueAt(sr[i], 6).toString();
							splist.add(xxtable.getValueAt(sr[i], 6).toString());
							slhj = slhj + Integer.parseInt(xxtable.getValueAt(sr[i], 6).toString());
							insertdata[i][7] = xxtable.getValueAt(sr[i], 7).toString();
							splist.add(xxtable.getValueAt(sr[i], 7).toString());
							hj = hj + Double.parseDouble(xxtable.getValueAt(sr[i], 7).toString());
							insertdata[i][8] = xxtable.getValueAt(sr[i], 8).toString();
							splist.add(xxtable.getValueAt(sr[i], 8).toString());
							checkb = true;
						} else {
							JOptionPane.showMessageDialog(xxf, "ѡ���к��в�֧�ֳ�����Ʒ\n������ѡ��");
							checkb = false;
							break;
						}

					}
					if (checkb) {
						String custn = mct.getText().trim(); // ��ȡ�ͻ�����
						String cusid = custid.getText(); // ��ȡ�ͻ����
						String dh = d.xsdh();
						String odh = xxf_ShowNo.getText();
						String saleid = salemanidL.getText().trim();
						String saleman = salemanL.getText().trim();
						for (int i = 0; i < insertdata.length; i++) {
							System.out.println(insertdata[i][0] + "," + insertdata[i][1] + "," + insertdata[i][2] + ","
									+ insertdata[i][3] + "," + insertdata[i][4] + "," + insertdata[i][5] + ","
									+ insertdata[i][6] + "," + insertdata[i][7] + "," + insertdata[i][8]);
						}
						d.insertSaleList(xxf, odh, dh, custn, cusid, insertdata, user, saleid, saleman);
						xxmdm.setDataVector(d.xsd(odh), mcn);
						TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // �����п�
						cktablecxh.setPreferredWidth(40);
						cktablecxh.setMinWidth(40);
						cktablecxh.setMaxWidth(40);
						TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // �����п�
						cktableczl.setPreferredWidth(70);
						cktableczl.setMinWidth(70);
						cktableczl.setMaxWidth(70);
						TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // �����п�
						cktableccp.setPreferredWidth(180);
						cktableccp.setMinWidth(180);
						cktableccp.setMaxWidth(180);
						TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // �����п�
						cktablecdw.setPreferredWidth(40);
						cktablecdw.setMinWidth(40);
						cktablecdw.setMaxWidth(40);
						TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // �����п�
						cktablezk.setPreferredWidth(40);
						cktablezk.setMinWidth(40);
						cktablezk.setMaxWidth(40);
						TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // �����п�
						cktablesl.setPreferredWidth(40);
						cktablesl.setMinWidth(40);
						cktablesl.setMaxWidth(40);
						List<Object> listkh = new ArrayList<Object>();
						listkh.add(dh);
						listkh.add(mct.getText());
						listkh.add(lxrt.getText());
						listkh.add(lxrtelt.getText());
						listkh.add(addrt.getText());
						listkh.add("");
						Printclass.setTitel("������ϴ�豸���޹�˾���۵�");
						Printclass.setUser(user);
						Printclass.setkhls(listkh);
						Printclass.setsp(splist);
						List<Object> listhj = new ArrayList<Object>();
						listhj.add(changenum(hj));
						listhj.add(slhj);
						listhj.add(String.format("%.2f", hj));
						Printclass.sethj(listhj);
						new Printclass();
						System.out.println(odh + "," + dh + "," + custn + "," + cusid);
					}
				}
			}
		});
		// ------------------------------------------������---------------------------------
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
					mit.doClick();
				}
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
				custid.setText(ls.get(4));
				salemanidL.setText(qSAR_MainTable.getValueAt(r, 5).toString().trim());
				salemanL.setText(qSAR_MainTable.getValueAt(r, 6).toString().trim());
				xxmdm.setDataVector(d.xsd(st), mcn);
				int row = xxtable.getRowCount();
				hj = 0.0;
				for (int i = 0; i < row; i++) {
					hj = Double.parseDouble(xxtable.getValueAt(i, 7).toString()) + hj;
				}
				xxf_ShowTotal.setText(String.format("%.2f", hj));
				TableColumn cktablecxh = xxtable.getColumnModel().getColumn(0); // �����п�
				cktablecxh.setPreferredWidth(40);
				cktablecxh.setMinWidth(40);
				cktablecxh.setMaxWidth(40);
				TableColumn cktableczl = xxtable.getColumnModel().getColumn(1); // �����п�
				cktableczl.setPreferredWidth(70);
				cktableczl.setMinWidth(70);
				cktableczl.setMaxWidth(70);
				TableColumn cktableccp = xxtable.getColumnModel().getColumn(2); // �����п�
				cktableccp.setPreferredWidth(180);
				cktableccp.setMinWidth(180);
				cktableccp.setMaxWidth(180);
				TableColumn cktablecdw = xxtable.getColumnModel().getColumn(3); // �����п�
				cktablecdw.setPreferredWidth(40);
				cktablecdw.setMinWidth(40);
				cktablecdw.setMaxWidth(40);
				TableColumn cktablezk = xxtable.getColumnModel().getColumn(4); // �����п�
				cktablezk.setPreferredWidth(40);
				cktablezk.setMinWidth(40);
				cktablezk.setMaxWidth(40);
				TableColumn cktablesl = xxtable.getColumnModel().getColumn(6); // �����п�
				cktablesl.setPreferredWidth(40);
				cktablesl.setMinWidth(40);
				cktablesl.setMaxWidth(40);
				if (d.pdj(st).isEmpty() == false) {
					// xxf_OldSaleReceipt_b.setVisible(true);
				}
				xxf.setVisible(true);
			}
		});
		// -----------------------------------------------------------------------------------------------
		JScrollPane qSAR_MainFrame_Js = new JScrollPane();
		qSAR_MainFrame_Js.setViewportView(qSAR_MainTable);
		qSAR_MainFrame_Js.setBounds(10, 40, 625, 420);
		qSAR_MainFrame_Content.add(qSAR_MainFrame_Js);
		// ---------------------------------------------------Ӧ�������-------------------------------------------
		// -----------------------------------------detail panel close
		// -------------------------------------------------------listener------------------------------------------
		xxf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				// int i=jtab.getSelectedRow();
				// xdm.setDataVector(d.xys(jtab.getValueAt(i,0).toString()),xcn);
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
					TableColumn cktablecxh = qSAR_MainTable.getColumnModel().getColumn(2); // �����п�
					cktablecxh.setPreferredWidth(180);
					cktablecxh.setMinWidth(180);
					cktablecxh.setMaxWidth(180);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(qSAR_MainFrame, "���ڸ�ʽ����");
				}
				qSAR_MainFrame.setEnabled(true);
				xxf.dispose();
			}
		});
		// -------------------------------------------------------------------------------------------------------
		qSAR_MainFrame.setVisible(true);
	}

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
