package tool;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PrintRegister {
	static String model;
	static String pumpno;
	static String motorno;
	static String sn;
	public static void setRegisterInfo(String model,String pumpno,String motorno,String sn){
		PrintRegister.model=model;
		PrintRegister.pumpno=pumpno;
		PrintRegister.motorno=motorno;
		PrintRegister.sn=sn;
	}
	public PrintRegister(){
		JFrame jf=new JFrame("打印预览");
		//jf.setAlwaysOnTop(true);
		jf.setResizable(false);
		jf.setSize(220,200);
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
										Font font,font2;
										font=new Font("黑体",Font.BOLD,15);
										font2=new Font("黑体",Font.PLAIN,13);
										g2.setFont(font);
										g2.drawString("Model:", 20, 30);
										g2.setFont(font2);
										g2.drawString(model, 75, 30);
										g2.setFont(font);
										g2.drawString("PumpNo:", 20, 50);
										g2.setFont(font2);
										g2.drawString(pumpno, 83, 50);
										g2.setFont(font);
										g2.drawString("MotorNo:", 20, 70);
										g2.setFont(font2);
										g2.drawString(motorno, 93, 70);
										g2.setFont(font);
										g2.drawString("SN:", 20, 90);
										g2.setFont(font2);
										g2.drawString(sn,48, 90);
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
		//new PrintRegister();
	}
	class Draw extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g){
			super.paint(g);
			Font font,font2;
			font=new Font("黑体",Font.BOLD,15);
			font2=new Font("黑体",Font.PLAIN,13);
			Graphics2D g2=(Graphics2D) g;
			g2.setFont(font);
			g2.drawString("Model:", 20, 30);
			g2.setFont(font2);
			g2.drawString(model, 75, 30);
			g2.setFont(font);
			g2.drawString("PumpNo:", 20, 50);
			g2.setFont(font2);
			g2.drawString(pumpno, 83, 50);
			g2.setFont(font);
			g2.drawString("MotorNo:", 20, 70);
			g2.setFont(font2);
			g2.drawString(motorno, 93, 70);
			g2.setFont(font);
			g2.drawString("SN:", 20, 90);
			g2.setFont(font2);
			g2.drawString(sn,48, 90);
			
		}
	}
}
