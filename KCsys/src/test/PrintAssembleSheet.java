package test;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PrintAssembleSheet {
	//595×842
	static String user;
	static String lxr;
	static String tel;
	static String date;
	static String khm;
	static String add;
	static String js="现金";
	static String[][] data;
	static String spname;
	static List<Object> kh=new ArrayList<Object>();
	static List<Object> sp=new ArrayList<Object>();
	static List<Object> hj=new ArrayList<Object>();
	public static void setdata(String spname,String user,String[][] data){
		PrintAssembleSheet.spname=spname;
		PrintAssembleSheet.user=user;
		PrintAssembleSheet.data=data;
	}
	public PrintAssembleSheet(){
		JFrame jf=new JFrame("打印预览");
		//jf.setAlwaysOnTop(true);
		jf.setResizable(false);
		jf.setSize(595,842);
		Container c=jf.getContentPane();
		c.add(new Draw());
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
										Graphics2D g2 = (Graphics2D) graphics;
										Date d=new Date();
										date=String.format("%tF",d);
										Font font,font3;
										font=new Font("黑体",Font.PLAIN,20);
										g2.setFont(font);
										g2.drawString("天澜清洗设备组装单",200,35);
										font3=new Font("宋体",Font.PLAIN,10);
										g2.setFont(font3);
										g2.drawString(spname,20,65);
										//g2.drawString("单号:",130,65);
										g2.drawString("日期:",250,65);
										//g2.drawString(dh,160,65);
										g2.drawString(date,280,65);
										List<String> ls=new ArrayList<String>();
										ls.add("序号");ls.add("商品种类");ls.add("商品名称");ls.add("单位");ls.add("数量");ls.add("备注");
										int row=0;
										int y=0;
										int x=20;int s=0;
										int n[]=new int[]{0,4,6,12,3,4,8}; //总计37
										int t[]=new int[]{0,4,6,12,3,4};
										int count=0;
										for(int i=0;i<data.length+3;i++){
											y=row*18+90;
											if(i==1){
												for(int j=0;j<ls.size();j++){
													s=t[j]*14;
													x=x+s;
													g2.drawString(ls.get(j),x+5,y-4);
												}
											}
											x=20;s=0;
											if(i>1&&i<data.length+2){
												for(int j=0;j<data[i-2].length;j++){
													s=t[j]*14;
													x=x+s;
													g2.drawString(data[i-2][j],x+5,y-4);
													if(j==4){
														count=count+Integer.parseInt(data[i-2][4]);
													}
												}
											}
											x=20;s=0;
											if(i==data.length+2){
												for(int j=0;j<ls.size();j++){
													s=n[j]*14;
													x=x+s;
													if(j==0){
														g2.drawString("合计",x+5,y-4);
													}
													if(j==4){
														g2.drawString(Integer.toString(count),x+5,y-4);
													}
												}
											}
											g2.drawLine(20,y,538,y);//横线
											row++;
										}
										x=20;s=0;
										for(int i=0;i<7;i++){
											s=n[i]*14;
											x=x+s;
											g2.drawLine(x,90,x,y);//竖线
										}
										g2.drawString("开单人:"+user+"",20,y+20);
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
		new PrintNBC();
	}
	class Draw extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g){
			super.paint(g);
			Date d=new Date();
			date=String.format("%tF",d);
			Font font,font3;
			font=new Font("黑体",Font.PLAIN,20);
			Graphics2D g2=(Graphics2D) g;
			g2.setFont(font);
			g2.drawString("天澜清洗设备组装单",200,35);
			font3=new Font("宋体",Font.PLAIN,10);
			g2.setFont(font3);
			g2.drawString(spname,20,65);
			//g2.drawString("单号:",130,65);
			g2.drawString("日期:",250,65);
			//g2.drawString(dh,160,65);
			g2.drawString(date,280,65);
			List<String> ls=new ArrayList<String>();
			ls.add("序号");ls.add("商品种类");ls.add("商品名称");ls.add("单位");ls.add("数量");ls.add("备注");
			int row=0;
			int y=0;
			int x=20;int s=0;
			int n[]=new int[]{0,4,6,12,3,4,8}; //总计37
			int t[]=new int[]{0,4,6,12,3,4};
			int count=0;
			for(int i=0;i<data.length+3;i++){
				y=row*18+90;
				if(i==1){
					for(int j=0;j<ls.size();j++){
						s=t[j]*14;
						x=x+s;
						g2.drawString(ls.get(j),x+5,y-4);
					}
				}
				x=20;s=0;
				if(i>1&&i<data.length+2){
					for(int j=0;j<data[i-2].length;j++){
						s=t[j]*14;
						x=x+s;
						g2.drawString(data[i-2][j],x+5,y-4);
						if(j==4){
							count=count+Integer.parseInt(data[i-2][4]);
						}
					}
				}
				x=20;s=0;
				if(i==data.length+2){
					for(int j=0;j<ls.size();j++){
						s=n[j]*14;
						x=x+s;
						if(j==0){
							g2.drawString("合计",x+5,y-4);
						}
						if(j==4){
							g2.drawString(Integer.toString(count),x+5,y-4);
						}
					}
				}
				g2.drawLine(20,y,538,y);//横线
				row++;
			}
			x=20;s=0;
			for(int i=0;i<7;i++){
				s=n[i]*14;
				x=x+s;
				g2.drawLine(x,90,x,y);//竖线
			}
			g2.drawString("开单人:"+user+"                                                            ",20,y+20);
		}
	}
}
