package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class paneltest extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;
	static BufferedImage bi;
	public paneltest(){
		try {
			//img=ImageIO.read(new File("e://pic//25611.jpg"));
			bi = ImageIO.read(new File("e://pic//25611.jpg"));   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public void paint(Graphics g){     
		 //调用父类的paint方法是画背景     
		 super.paint(g);   
		 g.drawImage(bi,0,0,null); 
	}
	 public static int getheight(){
		int height=bi.getHeight();
		return height;
	 }
	 public static int getwidth(){
		 int width=bi.getWidth();
		 return width;
	 }
	 /*
	  public static void main(String[] args)  {     
		  JFrame f = new JFrame();     
		  Container cp = f.getContentPane();     
		  cp.add(new paneltest());     
		  f.setSize(getwidth(),getheight());    
		  f.setVisible(true);  
		 }
		  */
}

