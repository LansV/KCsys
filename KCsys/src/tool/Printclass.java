package tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Printclass {
	//595×842
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
	static String receiptStatus="";
	static List<Object> kh=new ArrayList<Object>();
	static List<Object> sp=new ArrayList<Object>();
	static List<Object> hj=new ArrayList<Object>();
	public static void setkhls(List<Object> kh){
		Printclass.kh=kh;
		dh=kh.get(0).toString().trim();
		khm=kh.get(1).toString().trim();
		lxr=kh.get(2).toString().trim();
		tel=kh.get(3).toString().trim();
		add=kh.get(4).toString().trim();
		js=kh.get(5).toString().trim();
		if(kh.size()==7){
			receiptStatus=kh.get(6).toString().trim();
		}
		Date d=new Date();
		date=String.format("%tF",d);
	}
	public static void setJsr(String jsr){
		Printclass.jsr=jsr;
	}
	public static void setTitel(String title){
		Printclass.title=title;
	}
	public static void setUser(String user){
		Printclass.user=user;
	}
	public static void setsp(List<Object> sp){
		Printclass.sp=sp;
	}
	public static void sethj(List<Object> hj){
		Printclass.hj=hj;
	}
	public  Printclass(){
		JFrame jf=new JFrame();
		jf.setTitle("打印预览");
		jf.setResizable(false);
		jf.setSize(595,842);
		Container c=jf.getContentPane();
		c.setLayout(null);
		JPanel dr=new Draw();
		dr.setBounds(0, 0,595, 800);
		c.add(dr);
		JButton b=new JButton("打印");
		b.setBounds(250,750,60,25);
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					PrinterJob job = PrinterJob.getPrinterJob();
					if (!job.printDialog()){
						return;
					}else{
						job.setPrintable(new Printable() {
							public int print(Graphics graphics, PageFormat pageFormat,
									int pageIndex) throws PrinterException {
								if (pageIndex > 0){
									return Printable.NO_SUCH_PAGE;
								}else{
									Font font,font2,font3;
									font=new Font("宋体",Font.PLAIN,20);
									Graphics2D g2=(Graphics2D) graphics;
									g2.setFont(font);
									g2.drawString(title,150,35);
									font2=new Font("宋体",Font.PLAIN,10);
									g2.setFont(font2);
									g2.drawString("地址:江门市蓬江区潮连坦边方岳里1号   电话:0750-3327669  Fax:0750-3226508",100,50);
									font3=new Font("宋体",Font.PLAIN,10);
									g2.setFont(font3);
									g2.drawString("单号:",20,65);g2.drawString("日期:",240,65);g2.drawString(receiptStatus,330,65);
									g2.drawString("结算:",420,65);
									g2.drawString("客户:",20,85);g2.drawString("联系人:",240,85);g2.drawString("联系电话:",420,85);
									g2.drawString("地址:",20,105);
									g2.drawString(dh,50,65);g2.drawString(date,270,65);g2.drawString(js,445,65);
									g2.drawString(khm,50,85);g2.drawString(lxr,280,85);g2.drawString(tel,470,85);
									g2.drawString(add,50,105);
									List<String> ls=new ArrayList<String>();
									ls.add("序号");ls.add("商品编号");ls.add("商品名称");ls.add("单位");
									ls.add("折扣");ls.add("单价");ls.add("数量");ls.add("金额");ls.add("备注");
									int n[]=new int[]{0,2,4,12,2,2,4,2,4,5};
									int s=0;
									int x=20;
									int y=115;
									int row=0;
									int count=0;
									List<Object> lsx=new ArrayList<Object>();
						/*			for(int i=0;i<11;i++){
										lsx.add("");lsx.add("");lsx.add("");lsx.add("");lsx.add("");
										lsx.add("");lsx.add("");lsx.add("");lsx.add("");
									}*/
									lsx=sp;
									//System.out.println(lsx.size());
									for(int i=0;i<lsx.size()/9+3;i++){    //画横线
										y=115+row*18;
										if(i==1){
											for(int j=0;j<ls.size();j++){
												s=n[j]*14;
												x=x+s;
												switch(j){
												case 0:
													g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
													break;
												case 1:
													g2.drawString(ls.get(j),x+9,y-5); //写入表头数据
													break;
												case 2:
													g2.drawString(ls.get(j),x+65,y-5); //写入表头数据
													break;
												case 3:
													g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
													break;
												case 4:
													g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
													break;
												case 5:
													g2.drawString(ls.get(j),x+17,y-5); //写入表头数据
													break;
												case 6:
													g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
													break;
												case 7:
													g2.drawString(ls.get(j),x+18,y-5); //写入表头数据
													//System.out.println(x);
													break;
												case 8:
													g2.drawString(ls.get(j),x+24,y-5); //写入表头数据
													break;
												}
												
											}
										}
										s=0;
										x=20;
										if(i>1&&i<lsx.size()/9+2){
											for(int j=0;j<9;j++){
												s=n[j]*14;
												x=x+s;
												//System.out.println(j+"    "+x);
												switch(j){
												case 0:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
													break;
												case 1:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
													break;
												case 2:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
													break;
												case 3:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
													break;
												case 4:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
													break;
												case 5:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
													break;
												case 6:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
													break;
												case 7:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
													break;
												case 8:
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
													break;
												}
												
											}
											count++;
										}
										if(i==lsx.size()/9+2){
											g2.drawString(hj.get(0).toString().trim(),51,y-5);
											g2.drawString(hj.get(1).toString().trim(),387,y-5);
											g2.drawString(hj.get(2).toString().trim(),415,y-5);
										}
										g2.drawLine(20,y,538,y);//横线
										row++;
									}
									count=0;
									row=0;
									s=0;
									x=20;
									for(int i=0;i<ls.size()+1;i++){  //画竖线
										s=n[i]*14;
										x=x+s;
										if(i<2||i>5){
											g2.drawLine(x,115,x,y);//竖线
										}else{
											g2.drawLine(x,115,x,y-18);//竖线
										}
										if(i==0){
											g2.drawString("合计",x+4,y-5);
										}
									}
									//System.out.println(y);
									g2.drawString("开单人:"+user+"                                经手人:"+jsr+"                       收货人:",20,y+20);
									g2.drawString("白色:存根联                  红色:记账联                 蓝色:收款联                  黄色:收货联",20,y+40);
									return Printable.PAGE_EXISTS;
								}
							}
						});
					}
					job.setJobName("打印图形");
					job.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		c.add(b);
		jf.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_P){
					try {
						PrinterJob job = PrinterJob.getPrinterJob();
						if (!job.printDialog()){
							return;
						}else{
							job.setPrintable(new Printable() {
								public int print(Graphics graphics, PageFormat pageFormat,
										int pageIndex) throws PrinterException {
									if (pageIndex > 0){
										return Printable.NO_SUCH_PAGE;
									}else{
										Font font,font2,font3;
										font=new Font("宋体",Font.PLAIN,20);
										Graphics2D g2=(Graphics2D) graphics;
										g2.setFont(font);
										g2.drawString(title,150,35);
										font2=new Font("宋体",Font.PLAIN,10);
										g2.setFont(font2);
										g2.drawString("地址:江门市蓬江区潮连坦边方岳里1号   电话:0750-3327669  Fax:0750-3226508",100,50);
										font3=new Font("宋体",Font.PLAIN,10);
										g2.setFont(font3);
										g2.drawString("单号:",20,65);g2.drawString("日期:",240,65);g2.drawString(receiptStatus,330,65);
										g2.drawString("结算:",420,65);
										g2.drawString("客户:",20,85);g2.drawString("联系人:",240,85);g2.drawString("联系电话:",420,85);
										g2.drawString("地址:",20,105);
										//g2.drawString(dh,50,65);g2.drawString(date,270,65);g2.drawString(js,445,65);
										//g2.drawString(khm,50,85);g2.drawString(lxr,280,85);g2.drawString(tel,470,85);
										//g2.drawString(add,50,105);
										List<String> ls=new ArrayList<String>();
										ls.add("序号");ls.add("商品编号");ls.add("商品名称");ls.add("单位");
										ls.add("折扣");ls.add("单价");ls.add("数量");ls.add("金额");ls.add("备注");
										int n[]=new int[]{0,2,4,12,2,2,4,2,4,5};
										int s=0;
										int x=20;
										int y=115;
										int row=0;
										@SuppressWarnings("unused")
										int count=0;
										List<Object> lsx=new ArrayList<Object>();
										for(int i=0;i<10;i++){
											lsx.add("");lsx.add("");lsx.add("");lsx.add("");lsx.add("");
											lsx.add("");lsx.add("");lsx.add("");lsx.add("");
										}
										lsx=sp;
										//System.out.println(lsx.size());
										for(int i=0;i<lsx.size()/9+3;i++){    //画横线
											y=115+row*18;
											if(i==1){
												for(int j=0;j<ls.size();j++){
													s=n[j]*14;
													x=x+s;
													switch(j){
													case 0:
														g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
														break;
													case 1:
														g2.drawString(ls.get(j),x+9,y-5); //写入表头数据
														break;
													case 2:
														g2.drawString(ls.get(j),x+65,y-5); //写入表头数据
														break;
													case 3:
														g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
														break;
													case 4:
														g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
														break;
													case 5:
														g2.drawString(ls.get(j),x+17,y-5); //写入表头数据
														break;
													case 6:
														g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
														break;
													case 7:
														g2.drawString(ls.get(j),x+18,y-5); //写入表头数据
														//System.out.println(x);
														break;
													case 8:
														g2.drawString(ls.get(j),x+24,y-5); //写入表头数据
														break;
													}
													
												}
											}
											s=0;
											x=20;
											if(i>1&&i<lsx.size()/9+2){
												for(int j=0;j<9;j++){
													s=n[j]*14;
													x=x+s;
													//System.out.println(j+"    "+x);
													switch(j){
													case 0:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
														g2.drawString("1",x+10,y-5);
														break;
													case 1:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
														g2.drawString("103026",x+13,y-5);
														break;
													case 2:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
														g2.drawString("1",x+3,y-5);
														break;
													case 3:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
														g2.drawString("台",x+8,y-5);
														break;
													case 4:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
														g2.drawString("9.8",x+8,y-5);
														break;
													case 5:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
														g2.drawString("1",x+3,y-5);
														break;
													case 6:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
														g2.drawString("1",x+3,y-5);
														break;
													case 7:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
														g2.drawString("1",x+3,y-5);
														break;
													case 8:
														//g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
														g2.drawString("1",x+3,y-5);
														break;
													}
													
												}
												count++;
											}
											if(i==lsx.size()/9+2){
												//g2.drawString(hj.get(0).toString().trim(),51,y-5);
												//g2.drawString(hj.get(1).toString().trim(),387,y-5);
												//g2.drawString(hj.get(2).toString().trim(),415,y-5);
												g2.drawString("1",415,y-5);
											}
											g2.drawLine(20,y,538,y);//横线
											row++;
										}
										count=0;
										row=0;
										s=0;
										x=20;
										for(int i=0;i<ls.size()+1;i++){  //画竖线
											s=n[i]*14;
											x=x+s;
											if(i<2||i>5){
												g2.drawLine(x,115,x,y);//竖线
											}else{
												g2.drawLine(x,115,x,y-18);//竖线
											}
											if(i==0){
												g2.drawString("合计",x+4,y-5);
											}
										}
										//System.out.println(y);
										g2.drawString("开单人:"+user+"                                经手人:"+jsr+"                       收货人:",20,y+20);
										g2.drawString("白色:存根联         红色:记账联      蓝色:收款联         黄色:收货联",20,y+40);
										return Printable.PAGE_EXISTS;
									}
								}
							});
						}
						job.setJobName("打印图形");
						job.print();
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new Printclass();
	}
	class Draw extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g){
			super.paint(g);
			Font font,font2,font3;
			font=new Font("宋体",Font.PLAIN,20);
			Graphics2D g2=(Graphics2D) g;
			g2.setFont(font);
			g2.drawString(title,150,35);
			font2=new Font("宋体",Font.PLAIN,10);
			g2.setFont(font2);
			g2.drawString("地址:江门市蓬江区潮连坦边方岳里1号   电话:0750-3327669  Fax:0750-3226508",100,50);
			font3=new Font("宋体",Font.PLAIN,10);
			g2.setFont(font3);
			g2.drawString("单号:",20,65);g2.drawString("日期:",240,65);g2.drawString(receiptStatus,330,65);
			g2.drawString("结算:",420,65);
			g2.drawString("客户:",20,85);g2.drawString("联系人:",240,85);g2.drawString("联系电话:",420,85);
			g2.drawString("地址:",20,105);
			g2.drawString(dh,50,65);g2.drawString(date,270,65);g2.drawString(js,445,65);
			g2.drawString(khm,50,85);g2.drawString(lxr,280,85);g2.drawString(tel,470,85);
			g2.drawString(add,50,105);
			List<String> ls=new ArrayList<String>();
			ls.add("序号");ls.add("商品编号");ls.add("商品名称");ls.add("单位");
			ls.add("折扣");ls.add("单价");ls.add("数量");ls.add("金额");ls.add("备注");
			int n[]=new int[]{0,2,4,12,2,2,4,2,4,5};
			int s=0;
			int x=20;
			int y=115;
			int row=0;
			int count=0;
			List<Object> lsx=new ArrayList<Object>();
/*			for(int i=0;i<11;i++){
				lsx.add("");lsx.add("");lsx.add("");lsx.add("");lsx.add("");
				lsx.add("");lsx.add("");lsx.add("");lsx.add("");
			}*/
			lsx=sp;
			//System.out.println(lsx.size());
			for(int i=0;i<lsx.size()/9+3;i++){    //画横线
				y=115+row*18;
				if(i==1){
					for(int j=0;j<ls.size();j++){
						s=n[j]*14;
						x=x+s;
						switch(j){
						case 0:
							g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
							break;
						case 1:
							g2.drawString(ls.get(j),x+9,y-5); //写入表头数据
							break;
						case 2:
							g2.drawString(ls.get(j),x+65,y-5); //写入表头数据
							break;
						case 3:
							g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
							break;
						case 4:
							g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
							break;
						case 5:
							g2.drawString(ls.get(j),x+17,y-5); //写入表头数据
							break;
						case 6:
							g2.drawString(ls.get(j),x+4,y-5); //写入表头数据
							break;
						case 7:
							g2.drawString(ls.get(j),x+18,y-5); //写入表头数据
							//System.out.println(x);
							break;
						case 8:
							g2.drawString(ls.get(j),x+24,y-5); //写入表头数据
							break;
						}
						
					}
				}
				s=0;
				x=20;
				if(i>1&&i<lsx.size()/9+2){
					for(int j=0;j<9;j++){
						s=n[j]*14;
						x=x+s;
						//System.out.println(j+"    "+x);
						switch(j){
						case 0:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
							break;
						case 1:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+10,y-5);    // 写入当行数据
							break;
						case 2:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
							break;
						case 3:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
							break;
						case 4:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+8,y-5);    // 写入当行数据
							break;
						case 5:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
							break;
						case 6:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
							break;
						case 7:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
							break;
						case 8:
							g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-5);    // 写入当行数据
							break;
						}
						
					}
					count++;
				}
				if(i==lsx.size()/9+2){
					g2.drawString(hj.get(0).toString().trim(),51,y-5);
					g2.drawString(hj.get(1).toString().trim(),387,y-5);
					g2.drawString(hj.get(2).toString().trim(),415,y-5);
				}
				g2.drawLine(20,y,538,y);//横线
				row++;
			}
			count=0;
			row=0;
			s=0;
			x=20;
			for(int i=0;i<ls.size()+1;i++){  //画竖线
				s=n[i]*14;
				x=x+s;
				if(i<2||i>5){
					g2.drawLine(x,115,x,y);//竖线
				}else{
					g2.drawLine(x,115,x,y-18);//竖线
				}
				if(i==0){
					g2.drawString("合计",x+4,y-5);
				}
			}
			//System.out.println(y);
			g2.drawString("开单人:"+user+"                                经手人:"+jsr+"                       收货人:",20,y+20);
			g2.drawString("白色:存根联                  红色:记账联                 蓝色:收款联                  黄色:收货联",20,y+40);
		}
	}
}