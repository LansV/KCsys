package tool;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Printclass {
	// 595��842
	static String user;
	static String title;
	static String dh;
	static String lxr;
	static String tel;
	static String date;
	static String khm;
	static String add;
	static String js;
	static String jsr;
	static String receiptStatus;
	static List<Object> kh = new ArrayList<Object>();
	static List<Object> sp = new ArrayList<Object>();
	static List<Object> hj = new ArrayList<Object>();
	int totalrow = 0;
	int markrow = 0;
	int data = 0;
	int len = 0;
	int page = 0;
	int h=0;
	public static void setkhls(List<Object> kh) {
		Printclass.kh = kh;
		dh = kh.get(0).toString().trim();
		khm = kh.get(1).toString().trim();
		lxr = kh.get(2).toString().trim();
		tel = kh.get(3).toString().trim();
		add = kh.get(4).toString().trim();
		js = kh.get(5).toString().trim();
		if (kh.size() == 7) {
			receiptStatus = kh.get(6).toString().trim();
		} else {
			receiptStatus = "";
		}
		Date d = new Date();
		date = String.format("%tF", d);
	}

	public static void setJsr(String jsr) {
		Printclass.jsr = jsr;
	}

	public static void setTitel(String title) {
		Printclass.title = title;
	}

	public static void setUser(String user) {
		Printclass.user = user;
	}

	public static void setsp(List<Object> sp) {
		Printclass.sp = sp;
	}

	public static void sethj(List<Object> hj) {
		Printclass.hj = hj;
	}

	public Printclass() {
		JFrame jf = new JFrame();
		jf.setTitle("��ӡԤ��");
		jf.setResizable(false);
		jf.setSize(595, 842);
		Container c = jf.getContentPane();
		//c.setLayout(new GridLayout(1,1));
		c.setLayout(null);
		JPanel dr = new Draw();
		dr.setPreferredSize(new Dimension(550,2000));
		JScrollPane jsp=new JScrollPane(dr);
		jsp.setBounds(2,25,585,800);
		c.add(jsp);
		JButton b = new JButton("��ӡ");
		b.setBounds(250, 0, 60, 25);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Book book = new Book();
				// ���ó�����
				PageFormat pf = new PageFormat();
				pf.setOrientation(PageFormat.PORTRAIT);
				// ͨ��Paper����ҳ��Ŀհױ߾�Ϳɴ�ӡ���򡣱�����ʵ�ʴ�ӡֽ�Ŵ�С�����
				Paper p = new Paper();
				p.setSize(590, 840);// ֽ�Ŵ�С
				p.setImageableArea(10, 10, 590, 840);// A4(595 X
														// 842)���ô�ӡ������ʵ0��0Ӧ����72��72����ΪA4ֽ��Ĭ��X,Y�߾���72
				pf.setPaper(p);
				// �� PageFormat �� Printable ��ӵ����У����һ��ҳ��
				int sps = sp.size();
				int row = 0;
				// int count=0;
				System.out.println(sps);
				for (int i = 0; i < sps / 9; i++) {
					ArrayList<String> remark = CutRemark.getRemark(sp.get(8 + i * 9).toString());
					int rm = remark.size();
					//System.out.println(rm);
					if (rm > 1) {
						row = row + rm - 1;
						markrow = markrow + rm - 1;
					}
					// count++;
					// data++;
					row++;
				}
				totalrow = row;
				page = row / 36;
				System.out.println("ҳ����" + (page + 1) + "   ������" + totalrow + "  ���ݣ�" + data);
				book.append(new PrintTest(), pf);
				book.append(new PrintTest(), pf, page);
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPageable(book);
				try {
					if (!job.printDialog()) {
						return;
					}
					job.setJobName("��ӡͼ��");
					job.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		c.add(b);
		jf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_P) {
					b.doClick();
				}
			}
		});
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
	/*	List<Object> kh = new ArrayList<Object>();
		for (int i = 0; i < 6; i++) {
			kh.add("����" + i);

		}
		List<Object> sp = new ArrayList<Object>();
		// int count=0;
		for (int i = 0; i < 325; i++) {
			if (i == 8 || i == 323) {
				 sp.add("����");
			} else {
				sp.add("����" + i);
			}

		}
		List<Object> hj = new ArrayList<Object>();
		for (int i = 0; i < 3; i++) {
			hj.add("����" + i);
		}
		setUser("user");
		setJsr("jsp");
		setTitel("titel");
		setkhls(kh);
		setsp(sp);
		sethj(hj);
		new Printclass();*/
	}

	public class PrintTest implements Printable {

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			// TODO �Զ����ɵķ������
			System.out.println("pageIndex=" + pageIndex);
			Font font, font2, font3;

			font = new Font("����", Font.PLAIN, 20);
			Graphics2D g2 = (Graphics2D) graphics;
			if (pageIndex == 0) {
				g2.setFont(font);
				g2.drawString(title, 150, 35);
				font2 = new Font("����", Font.PLAIN, 10);
				g2.setFont(font2);
				g2.drawString("��ַ:�������������̹�߷�����1��   �绰:0750-3327669  Fax:0750-3226508", 100, 50);
			}
			font3 = new Font("����", Font.PLAIN, 10);
			g2.setFont(font3);
			if (pageIndex == 0) {
				g2.drawString("����:", 20, 65);
				g2.drawString("����:" + pageIndex, 240, 65);
				g2.drawString(receiptStatus, 330, 65);
				g2.drawString("����:", 420, 65);
				g2.drawString("�ͻ�:", 20, 85);
				g2.drawString("��ϵ��:", 240, 85);
				g2.drawString("��ϵ�绰:", 420, 85);
				g2.drawString("��ַ:", 20, 105);
				g2.drawString(dh, 50, 65);
				g2.drawString(date, 270, 65);
				g2.drawString(js, 445, 65);
				g2.drawString(khm, 50, 85);
				g2.drawString(lxr, 280, 85);
				g2.drawString(tel, 470, 85);
				g2.drawString(add, 50, 105);
			} else {
				g2.drawString("����:", 20, 35);
				g2.drawString(dh, 50, 35);
			}
			List<String> ls = new ArrayList<String>();
			ls.add("���");
			ls.add("��Ʒ���");
			ls.add("��Ʒ����");
			ls.add("��λ");
			ls.add("�ۿ�");
			ls.add("����");
			ls.add("����");
			ls.add("���");
			ls.add("��ע");
			int n[] = new int[] { 0, 2, 4, 12, 2, 2, 4, 2, 4, 5 };
			int s = 0;
			int x = 20;
			int y = 0;
			int row = 0;
			int count = 0;
			List<Object> lsx = new ArrayList<Object>();
			lsx = sp;
			row = 0;
			count = 0;
			if (pageIndex == 0) {
				if (totalrow > 35) {
					len = 35 - markrow;
				} else {
					len = totalrow - markrow;
				}
				data = len;
			} else {
				len = totalrow - 35;
			}
			for (int i = 0; i < len + 3; i++) { // ������
				if (pageIndex == 0) {
					y = 115 + row * 18;
				} else {
					y = 50 + row * 18;
				}
				if (i == 1) {
					for (int j = 0; j < ls.size(); j++) {
						s = n[j] * 14;
						x = x + s;
						switch (j) {
						case 0:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 1:
							g2.drawString(ls.get(j), x + 9, y - 5); // д���ͷ����
							break;
						case 2:
							g2.drawString(ls.get(j), x + 65, y - 5); // д���ͷ����
							break;
						case 3:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 4:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 5:
							g2.drawString(ls.get(j), x + 17, y - 5); // д���ͷ����
							break;
						case 6:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 7:
							g2.drawString(ls.get(j), x + 18, y - 5); // д���ͷ����
							break;
						case 8:
							g2.drawString(ls.get(j), x + 24, y - 5); // д���ͷ����
							break;
						}
					}
				}
				s = 0;
				x = 20;
				if (i > 1 && i < len + 2) {
					for (int j = 0; j < 9; j++) {
						s = n[j] * 14;
						x = x + s;
						// System.out.println(j+" "+x);
						switch (j) {
						case 0:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 10,
									y - 5); // д�뵱������
							break;
						case 1:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 10,
									y - 5); // д�뵱������
							break;
						case 2:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 3,
									y - 5); // д�뵱������
							break;
						case 3:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 8,
									y - 5); // д�뵱������
							break;
						case 4:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 8,
									y - 5); // д�뵱������
							break;
						case 5:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 3,
									y - 5); // д�뵱������
							break;
						case 6:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 3,
									y - 5); // д�뵱������
							break;
						case 7:
							g2.drawString(lsx.get(j + count * 9 + data * 9 * pageIndex).toString().trim(), x + 3,
									y - 5); // д�뵱������
							break;
						case 8:
							ArrayList<String> remark = CutRemark
									.getRemark(lsx.get(j + count * 9 + data * 9 * pageIndex).toString());
							int rm = remark.size();
							for (int r = 0; r < rm; r++) {
								g2.drawString(remark.get(r), x + 3, y - 5 + r * 18); // д�뵱������
							}
							row = row + rm - 1;
							break;
						}

					}
					count++;
					// System.out.println(count);
				}
				if (page != pageIndex) {
					if (i == len + 1) {
						if (pageIndex == 0) {
							g2.drawLine(20, 115 + row * 18, 538, 115 + row * 18);// ����
						} else {
							g2.drawLine(20, 50 + row * 18, 538, 50 + row * 18);// ����
						}
						break;
					}

				} else {
					if (i == len + 2) {
						g2.drawString(hj.get(0).toString().trim(), 51, y - 5);
						g2.drawString(hj.get(1).toString().trim(), 387, y - 5);
						g2.drawString(hj.get(2).toString().trim(), 415, y - 5);
					}
				}
				if (pageIndex == 0) {
					g2.drawLine(20, 115 + row * 18, 538, 115 + row * 18);// ����
				} else {
					g2.drawLine(20, 50 + row * 18, 538, 50 + row * 18);// ����
				}
				row++;
				// System.out.println(row);
			}
			count = 0;
			row = 0;
			s = 0;
			x = 20;
			for (int i = 0; i < ls.size() + 1; i++) { // ������
				s = n[i] * 14;
				x = x + s;
				if (i < 2 || i > 5) {
					if (pageIndex == 0) {
						g2.drawLine(x, 115, x, y);// ����
					} else {
						g2.drawLine(x, 50, x, y);// ����
					}
				} else {
					if (page != pageIndex) {
						g2.drawLine(x, 115, x, y);// ����
					} else {
						if (pageIndex == 0) {
							g2.drawLine(x, 115, x, y - 18);// ����
						} else {
							g2.drawLine(x, 50, x, y - 18);// ����
						}
					}

				}
				if (i == 0) {
					if (page == pageIndex) {
						g2.drawString("�ϼ�", x + 4, y - 5);
					}
				}
			}
			// System.out.println(y);
			if (page == pageIndex) {
				g2.drawString(
						"������:" + user + "              ҵ��Ա:" + jsr + "                  ������:                  �ջ���:", 20,
						y + 20);
				g2.drawString("��ɫ:�����                  ��ɫ:������                 ��ɫ:�տ���                  ��ɫ:�ջ���", 20,
						y + 40);
			}
			if(pageIndex==0&&page!=0){
				g2.drawString("�� " + (pageIndex + 1) + " ҳ", 20, y +15);
			}else{
				g2.drawString("�� " + (pageIndex + 1) + " ҳ", 20, y + 60);
			}
			
			return Printable.PAGE_EXISTS;

		}

	}

	class Draw extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			super.paint(g);
			Font font, font2, font3;
			font = new Font("����", Font.PLAIN, 20);
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(font);
			g2.drawString(title, 150, 35);
			font2 = new Font("����", Font.PLAIN, 10);
			g2.setFont(font2);
			g2.drawString("��ַ:�������������̹�߷�����1��   �绰:0750-3327669  Fax:0750-3226508", 100, 50);
			font3 = new Font("����", Font.PLAIN, 10);
			g2.setFont(font3);
			g2.drawString("����:", 20, 65);
			g2.drawString("����:", 240, 65);
			g2.drawString(receiptStatus, 330, 65);
			g2.drawString("����:", 420, 65);
			g2.drawString("�ͻ�:", 20, 85);
			g2.drawString("��ϵ��:", 240, 85);
			g2.drawString("��ϵ�绰:", 420, 85);
			g2.drawString("��ַ:", 20, 105);
			g2.drawString(dh, 50, 65);
			g2.drawString(date, 270, 65);
			g2.drawString(js, 445, 65);
			g2.drawString(khm, 50, 85);
			g2.drawString(lxr, 280, 85);
			g2.drawString(tel, 470, 85);
			g2.drawString(add, 50, 105);
			List<String> ls = new ArrayList<String>();
			ls.add("���");
			ls.add("��Ʒ���");
			ls.add("��Ʒ����");
			ls.add("��λ");
			ls.add("�ۿ�");
			ls.add("����");
			ls.add("����");
			ls.add("���");
			ls.add("��ע");
			int n[] = new int[] { 0, 2, 4, 12, 2, 2, 4, 2, 4, 5 };
			int s = 0;
			int x = 20;
			int y = 115;
			int row = 0;
			int count = 0;
			List<Object> lsx = new ArrayList<Object>();
			/*
			 * for(int i=0;i<11;i++){
			 * lsx.add("");lsx.add("");lsx.add("");lsx.add("");lsx.add("");
			 * lsx.add("");lsx.add("");lsx.add("");lsx.add(""); }
			 */
			lsx = sp;
			// System.out.println(lsx.size());
			for (int i = 0; i < lsx.size() / 9 + 3; i++) { // ������
				y = 115 + row * 18;
				if (i == 1) {
					for (int j = 0; j < ls.size(); j++) {
						s = n[j] * 14;
						x = x + s;
						switch (j) {
						case 0:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 1:
							g2.drawString(ls.get(j), x + 9, y - 5); // д���ͷ����
							break;
						case 2:
							g2.drawString(ls.get(j), x + 65, y - 5); // д���ͷ����
							break;
						case 3:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 4:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 5:
							g2.drawString(ls.get(j), x + 17, y - 5); // д���ͷ����
							break;
						case 6:
							g2.drawString(ls.get(j), x + 4, y - 5); // д���ͷ����
							break;
						case 7:
							g2.drawString(ls.get(j), x + 18, y - 5); // д���ͷ����
							// System.out.println(x);
							break;
						case 8:
							g2.drawString(ls.get(j), x + 24, y - 5); // д���ͷ����
							break;
						}

					}
				}
				s = 0;
				x = 20;
				if (i > 1 && i < lsx.size() / 9 + 2) {
					for (int j = 0; j < 9; j++) {
						s = n[j] * 14;
						x = x + s;
						// System.out.println(j+" "+x);
						switch (j) {
						case 0:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 10, y - 5); // д�뵱������
							break;
						case 1:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 10, y - 5); // д�뵱������
							break;
						case 2:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 3, y - 5); // д�뵱������
							break;
						case 3:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 8, y - 5); // д�뵱������
							break;
						case 4:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 8, y - 5); // д�뵱������
							break;
						case 5:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 3, y - 5); // д�뵱������
							break;
						case 6:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 3, y - 5); // д�뵱������
							break;
						case 7:
							g2.drawString(lsx.get(j + count * 9).toString().trim(), x + 3, y - 5); // д�뵱������
							break;
						case 8:
							ArrayList<String> remark = CutRemark.getRemark(lsx.get(j + count * 9).toString());
							int rm = remark.size();
							for (int r = 0; r < rm; r++) {
								g2.drawString(remark.get(r), x + 3, y - 5 + r * 18); // д�뵱������
							}
							row = row + rm - 1;
							break;
						}

					}
					count++;
				}
				if (i == lsx.size() / 9 + 2) {
					g2.drawString(hj.get(0).toString().trim(), 51, y - 5);
					g2.drawString(hj.get(1).toString().trim(), 387, y - 5);
					g2.drawString(hj.get(2).toString().trim(), 415, y - 5);
				}
				g2.drawLine(20, 115 + row * 18, 538, 115 + row * 18);// ����
				row++;
			}
			count = 0;
			row = 0;
			s = 0;
			x = 20;
			for (int i = 0; i < ls.size() + 1; i++) { // ������
				s = n[i] * 14;
				x = x + s;
				if (i < 2 || i > 5) {
					g2.drawLine(x, 115, x, y);// ����
				} else {
					g2.drawLine(x, 115, x, y - 18);// ����
				}
				if (i == 0) {
					g2.drawString("�ϼ�", x + 4, y - 5);
				}
			}
			// System.out.println(y);
			g2.drawString("������:" + user + "              ҵ��Ա:" + jsr + "                  ������:                  �ջ���:",
					20, y + 20);
			g2.drawString("��ɫ:�����                  ��ɫ:������                 ��ɫ:�տ���                  ��ɫ:�ջ���", 20, y + 40);
			//this.setPreferredSize(new Dimension(600, 2000));
			//h=y+40;
		}
	}
}