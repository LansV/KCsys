package order;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int qx;
	static String Version="V1.170212";
	String UserName;
	boolean EqualsVersion=false;
     public Login(){
    	 Dao d=new Dao();
         Connection con=d.getcon();
         String EqualStr = null;
    	 try{
    		Statement sql = con.createStatement();
			ResultSet res = sql.executeQuery("select*from VersionControl");
			while(res.next()){
				EqualStr=res.getString("CVersion").trim();
			}
    	 }catch(Exception e){
    		 JOptionPane.showMessageDialog(null,"获取版本错误！\n请联系天澜公司获取技术支持");
    		 System.exit(0);
    	 }
    	 if(Version.equals(EqualStr)==true){
    		 InetAddress ia = null;
    		 String PcName=null;
    		 String PcMac=null;
	    		try {
					ia = InetAddress.getLocalHost();
					String n=ia.toString();
					String[] s=n.split("/");
					PcName=s[0];
					System.out.println(PcName);
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					PcName="";
				}
	    		try{
	    			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
	    			//System.out.println("mac数组长度："+mac.length);
	    			StringBuffer sb = new StringBuffer("");
	    			for(int i=0; i<mac.length; i++) {
	    				if(i!=0) {
	    					sb.append("-");
	    				}
	    				//字节转换为整数
	    				int temp = mac[i]&0xff;
	    				String str = Integer.toHexString(temp);
	    				if(str.length()==1) {
	    					sb.append("0"+str);
	    				}else {
	    					sb.append(str);
	    				}
	    			}
	    			PcMac=sb.toString().toUpperCase();
	    			System.out.println(PcMac);
	    		}catch(SocketException e){
	    			PcMac="";
	    		}
	    	   URL url = this.getClass().getResource("/order/Image/TLogo.png");
	    	   Image img = Toolkit.getDefaultToolkit().getImage(url);
	    	   this.setIconImage(img);
    		   setResizable(false);
    	  	   setLayout(null);
    		   setBounds(100,100,300,200);
    		   Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    		   setBounds(p.x - 300 / 2, p.y - 200 / 2, 300, 200);
    		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		   Container c=getContentPane();
    		   setTitle("用户登录");
    		   JLabel JL_user=new JLabel("账号：");
    		   JL_user.setBounds(50,20,40,20);
    		   JLabel JL_pass=new JLabel("密码：");
    		   JL_pass.setBounds(50,65,40,20);
    		   JTextField JT_user=new JTextField();
    		   JT_user.setBounds(95,20,140,24);
    		   JPasswordField JT_pass=new JPasswordField();
    		   JT_pass.setBounds(95,65,140,24);
    		   JButton JB_login=new JButton("登陆");
    		   JB_login.setBounds(50,120,60,25);
    		   JB_login.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				String user=JT_user.getText().trim();
    				String pass=new String(JT_pass.getPassword());
    				try{
    					if(user.length()==0 || pass.length()==0){
    						if(user.length()==0&&pass.length()==0){
    							JOptionPane.showMessageDialog(null, "用户名与密码不能为空");
    							JT_user.requestFocus();
    						}else{
    							if(user.length()==0){
    								JOptionPane.showMessageDialog(null, "用户名不能为空");
    								JT_user.requestFocus();
    							}else{
    								if(pass.length()==0){
    									JOptionPane.showMessageDialog(null, "密码不能为空");
    									JT_pass.requestFocus();
    								}
    							}
    						}
    					}else{
    						Statement sql = con.createStatement();
    						ResultSet res = sql.executeQuery("select*from UserB where username='"+user+"'");
    						if(res.next()){
    							String xuser=res.getString("username").trim();
    							String xpass=res.getString("pass").trim();
    							@SuppressWarnings("unused")
								int  id=res.getInt("id");
    							if(user.equals(xuser)&&pass.equals(xpass)){
    								dispose();
    								new MainFrame();
    							}else{
    								JOptionPane.showMessageDialog(null, "用户名或密码错误！");
    								JT_pass.setText("");
    								JT_pass.requestFocus();
    							}		
    						}else{
    							JOptionPane.showMessageDialog(null, "用户不存在！");
    							JT_user.setText("");
    							JT_pass.setText("");
    							JT_user.requestFocus();
    						}	
    					};
    				}catch(Exception e1){
    					JOptionPane.showMessageDialog(null, "数据库链接故障！请与管理员联系");
    				}
    			}
    			   
    		   });
    		   JT_pass.addKeyListener(new KeyAdapter(){
    			   public void keyTyped(KeyEvent e){
    				   if(e.getKeyChar()=='\n')
    					   JB_login.doClick();
    			   }
    		   });
    		   JButton JB_exit=new JButton("退出");
    		   JB_exit.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				System.exit(0);
    			}
    		   });
    		   JB_exit.setBounds(180,120,60,25);
    		   c.add(JB_exit);
    		   c.add(JB_login);
    		   c.add(JT_pass);
    		   c.add(JT_user);
    		   c.add(JL_pass);
    		   c.add(JL_user);
    		   setVisible(true);
    	 }else{
    		JOptionPane.showMessageDialog(null,"\t当前 [ "+Version+ " ] 版本过低！\n请联系天澜公司\n获取最新版本后重试"); 
    		System.exit(0);
    	 }
     }
     public static void main(String[] args){
    	 new Login();
     }

}