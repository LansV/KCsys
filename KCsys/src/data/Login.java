package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
     public Login(){
       Dao d=new Dao();
       Connection con=d.getcon();
  	   setLayout(null);
	   setBounds(100,100,300,200);
	   Container c=getContentPane();
	   setTitle("用户登录");
	   JLabel JL_user=new JLabel("用户：");
	   JL_user.setBounds(40,20,40,20);
	   JLabel JL_pass=new JLabel("密码：");
	   JL_pass.setBounds(40,55,40,20);
	   JTextField JT_user=new JTextField();
	   JT_user.setBounds(85,20,120,20);
	   JPasswordField JT_pass=new JPasswordField();
	   JT_pass.setBounds(85,55,120,20);
	   JButton JB_login=new JButton("登陆");
	   JB_login.setBounds(40,120,60,25);
	   JB_login.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user=JT_user.getText().trim();
			String pass=new String(JT_pass.getPassword()).trim();
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
						if(user.equals(xuser)&&pass.equals(xpass)){
							dispose();
							new MF();
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
				JOptionPane.showMessageDialog(null, "数据库链接故障！请与管理员联系！18026753608");
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
	   JB_exit.setBounds(200,120,60,25);
	   c.add(JB_exit);
	   c.add(JB_login);
	   c.add(JT_pass);
	   c.add(JT_user);
	   c.add(JL_pass);
	   c.add(JL_user);
	   setVisible(true);
     }
     public static void main(String[] args){
    	 new Login();
     }

}