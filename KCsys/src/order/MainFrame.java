package order;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame {
	public MainFrame(int id,String user){
		//----------------------------------创建主面板---------------------------------------------
		JFrame MainFrame=new JFrame();
		MainFrame.setAlwaysOnTop(true);
		MainFrame.setResizable(false);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = null;
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    MainFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		MainFrame.setTitle("系统功能");
		MainFrame.setBounds(100,100,300,140);
		Container MainFrame_Contant=MainFrame.getContentPane();
		MainFrame_Contant.setBackground(new Color(196,228,210));
		MainFrame_Contant.setLayout(null);
		//---------------------------------------------打开客户资料----------------------------------
		JButton MainFrame_CustomerB=new JButton();
		try{
			Icon Contact_Icon=new ImageIcon("order/Image/contact.png");
			MainFrame_CustomerB.setIcon(Contact_Icon);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取应用图标错误");
		}
		MainFrame_CustomerB.setContentAreaFilled(false);
		MainFrame_CustomerB.setBounds(7,15,80,80);
		MainFrame_Contant.add(MainFrame_CustomerB);
		//-----------------------------------------打开订单------------------------------------------
		JButton MainFrame_AddOrderB=new JButton();
		try{
			Icon AddOrder_Icon=new ImageIcon("order/Image/add.png");
			MainFrame_AddOrderB.setIcon(AddOrder_Icon);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取应用图标错误");
		}
		MainFrame_AddOrderB.setContentAreaFilled(false);
		MainFrame_AddOrderB.setBounds(107,15,80,80);
		MainFrame_AddOrderB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new XSF();
			}
		});
		MainFrame_Contant.add(MainFrame_AddOrderB);
		//------------------------------------打开查询--------------------------------------------
		JButton MainFrame_QueryOrderB=new JButton();
		try{
			Icon QueryOrderB_Icon=new ImageIcon("order/Image/Query.png");
			MainFrame_QueryOrderB.setIcon(QueryOrderB_Icon);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取应用图标错误");
		}
		MainFrame_QueryOrderB.setContentAreaFilled(false);
		MainFrame_QueryOrderB.setBounds(207,15,80,80);
		MainFrame_QueryOrderB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		MainFrame_Contant.add(MainFrame_QueryOrderB);
		//----------------------------------------------------------------------------------------
		MainFrame.setVisible(true);
	}
	public static void main(String[] args){
		new MainFrame(1,"test");
	}
}
