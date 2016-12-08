package test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class imgtest extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public imgtest(){
		 setTitle("ImageViewer");
         label = new JLabel();
         add(label);
         String pic="e://pic//25611.jpg";
         label.setIcon(new ImageIcon(pic));
	}
	public static void main(String[] args){
		JFrame jf=new imgtest();
		jf.setSize(1000, 700);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
