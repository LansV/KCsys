package test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PrintWx {
	//595��842
	static String dh;
	static String lxr;
	static String tel;
	static String date;
	static String khm;
	static String add;
	static String js;
	static List<Object> kh=new ArrayList<Object>();
	static List<Object> sp=new ArrayList<Object>();
	static List<Object> hj=new ArrayList<Object>();
	public static void setkhls(List<Object> kh){
		PrintWx.kh=kh;
		dh=kh.get(0).toString().trim();
		khm=kh.get(1).toString().trim();
		lxr=kh.get(2).toString().trim();
		tel=kh.get(3).toString().trim();
		add=kh.get(4).toString().trim();
		js=kh.get(5).toString().trim();
		Date d=new Date();
		date=String.format("%tF",d);
	}
	public static void setsp(List<Object> sp){
		PrintWx.sp=sp;
	}
	public static void sethj(List<Object> hj){
		PrintWx.hj=hj;
	}
	public  PrintWx(){
		JFrame jf=new JFrame();
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
										Font font,font2,font3;
										font=new Font("����",Font.PLAIN,20);
										g2.setFont(font);
										g2.setFont(font);
										g2.drawString("������ϴ�豸ά�޵�",190,35);
										font2=new Font("����",Font.PLAIN,10);
										g2.setFont(font2);
										g2.drawString("��ַ:�������������̹�߷�����1��   �绰:0750-3327669  Fax:0750-3226508",100,50);
										font3=new Font("����",Font.PLAIN,10);
										g2.setFont(font3);
										g2.drawString("����:",20,65);g2.drawString("����:",240,65);g2.drawString("����:",420,65);
										g2.drawString("�ͻ�:",20,85);g2.drawString("��ϵ��:",240,85);g2.drawString("��ϵ�绰:",420,85);
										g2.drawString("��ַ:",20,105);
										g2.drawString(dh,50,65);g2.drawString(date,270,65);g2.drawString(js,445,65);
										g2.drawString(khm,50,85);g2.drawString(lxr,280,85);g2.drawString(tel,470,85);
										g2.drawString(add,50,105);
										List<String> ls=new ArrayList<String>();
										ls.add("���");ls.add("��Ʒ����");ls.add("��Ʒ����");ls.add("��λ");
										ls.add("�ۿ�");ls.add("����");ls.add("����");ls.add("���");ls.add("��ע");
										int n[]=new int[]{0,2,4,10,3,3,4,3,4,4};
										int s=0;
										int x=20;
										int y=115;
										int row=0;
										int count=0;
										List<Object> lsx=new ArrayList<Object>();
										lsx=sp;
										System.out.println(lsx.size());
										for(int i=0;i<lsx.size()/9+3;i++){    //������
											y=115+row*18;
											if(i==1){
												for(int j=0;j<ls.size();j++){
													s=n[j]*14;
													x=x+s;
													g2.drawString(ls.get(j),x+3,y-4); //д���ͷ����
												}
											}
											s=0;
											x=20;
											if(i>1&&i<lsx.size()/9+2){
												for(int j=0;j<9;j++){
													s=n[j]*14;
													x=x+s;
													System.out.println(j+"    "+x);
													g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-4);    // д�뵱������
												}
												count++;
											}
											if(i==lsx.size()/9+2){
												g2.drawString(hj.get(0).toString().trim(),51,y-4);
												g2.drawString(hj.get(1).toString().trim(),387,y-4);
												g2.drawString(hj.get(2).toString().trim(),429,y-4);
											}
											g2.drawLine(20,y,538,y);//����
											row++;
										}
										count=0;
										row=0;
										s=0;
										x=20;
										for(int i=0;i<ls.size()+1;i++){  //������
											s=n[i]*14;
											x=x+s;
											if(i<2||i>5){
												g2.drawLine(x,115,x,y);//����
											}else{
												g2.drawLine(x,115,x,y-18);//����
											}
											if(i==0){
												g2.drawString("�ϼ�",x+3,y-4);
											}
										}
										System.out.println(y);
										g2.drawString("������:                                   ������:                          �ջ���:",20,y+20);
										g2.drawString("��ɫ:�����         ��ɫ:������      ��ɫ:�տ���         ��ɫ:�ջ���",20,y+40);
										return Printable.PAGE_EXISTS;
									}
								}
							});
						}
						job.setJobName("��ӡͼ��");
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
		new PrintWx();
	}
	class Draw extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g){
			super.paint(g);
			Font font,font2,font3;
			font=new Font("����",Font.PLAIN,20);
			Graphics2D g2=(Graphics2D) g;
			g2.setFont(font);
			g2.drawString("������ϴ�豸ά�޵�",170,35);
			font2=new Font("����",Font.PLAIN,10);
			g2.setFont(font2);
			g2.drawString("��ַ:�������������̹�߷�����1��   �绰:0750-3327669  Fax:0750-3226508",100,50);
			font3=new Font("����",Font.PLAIN,10);
			g2.setFont(font3);
			g2.drawString("����:",20,65);g2.drawString("����:",240,65);g2.drawString("����:",420,65);
			g2.drawString("�ͻ�:",20,85);g2.drawString("��ϵ��:",240,85);g2.drawString("��ϵ�绰:",420,85);
			g2.drawString("��ַ:",20,105);
			g2.drawString(dh,50,65);g2.drawString(date,270,65);g2.drawString(js,445,65);
			g2.drawString(khm,50,85);g2.drawString(lxr,280,85);g2.drawString(tel,470,85);
			g2.drawString(add,50,105);
			List<String> ls=new ArrayList<String>();
			ls.add("���");ls.add("��Ʒ����");ls.add("��Ʒ����");ls.add("��λ");ls.add("�ۿ�");ls.add("����");ls.add("����");ls.add("���");ls.add("��ע");
			int n[]=new int[]{0,2,4,10,3,3,4,3,4,4};
			int s=0;
			int x=20;
			int y=115;
			int row=0;
			int count=0;
			List<Object> lsx=new ArrayList<Object>();
			lsx=sp;
			System.out.println(lsx.size());
			for(int i=0;i<lsx.size()/9+3;i++){    //������
				y=115+row*18;
				if(i==1){
					for(int j=0;j<ls.size();j++){
						s=n[j]*14;
						x=x+s;
						g2.drawString(ls.get(j),x+3,y-4); //д���ͷ����
					}
				}
				s=0;
				x=20;
				if(i>1&&i<lsx.size()/9+2){
					for(int j=0;j<9;j++){
						s=n[j]*14;
						x=x+s;
						System.out.println(j+"    "+x);
						g2.drawString(lsx.get(j+count*9).toString().trim(),x+3,y-4);    // д�뵱������
					}
					count++;
				}
				if(i==lsx.size()/9+2){
					g2.drawString(hj.get(0).toString().trim(),51,y-4);
					g2.drawString(hj.get(1).toString().trim(),387,y-4);
					g2.drawString(hj.get(2).toString().trim(),429,y-4);
				}
				g2.drawLine(20,y,538,y);//����
				row++;
			}
			count=0;
			row=0;
			s=0;
			x=20;
			for(int i=0;i<ls.size()+1;i++){  //������
				s=n[i]*14;
				x=x+s;
				if(i<2||i>5){
					g2.drawLine(x,115,x,y);//����
				}else{
					g2.drawLine(x,115,x,y-18);//����
				}
				if(i==0){
					g2.drawString("�ϼ�",x+3,y-4);
				}
			}
			System.out.println(y);
			g2.drawString("������:                                   ������:                          �ջ���:",20,y+20);
			g2.drawString("��ɫ:�����         ��ɫ:������      ��ɫ:�տ���         ��ɫ:�ջ���",20,y+40);
		}
	}
}