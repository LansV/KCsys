package data;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class showpic extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    BufferedImage bi=null;
		Statement sql=null;
	   	ResultSet res=null;
	   	String add=null;
	   	Dao d=new Dao();
	   	Connection con = d.getcon();
	public showpic(String cp){
			try {
				try {
					sql=con.createStatement();
					res=sql.executeQuery("select CPx_picadd from CPx where CPx_name = '"+cp+"'");
						while(res.next()){
							if(res.getString("CPx_picadd")==null){
								//System.out.println(res.getString("CPx_picadd"));
							}else{
								//System.out.println(res.getString("CPx_picadd").trim()+"020");
								add=res.getString("CPx_picadd").trim();
								//System.out.println(add);
							}
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "获取图片地址出错");
				}
				if(add!=null){
					bi = ImageIO.read(new File(add));
				}else{
					JOptionPane.showMessageDialog(null,"该产品未添加图片");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog(null,"该产品未添加图片");
			} 
	}
	public void paint(Graphics g){
		if(add!=null){
			super.paint(g);
			g.drawImage(bi,0, 0, null);
		}
	}
	public int geth(){
		int h=0;
		if(add!=null){
			h=bi.getHeight();	
		}
		return h;
	}
	public  int getw(){
		int w=0;
		if(add!=null){
		 w=bi.getWidth();
		}
		return w;
	}
}
