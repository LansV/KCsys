package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

import security.Lock;
public class MF {
	public MF(int userid,String user){
		JFrame mf=new JFrame("主菜单");
		mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				Lock.deleteAllLock(mf, new File("lock/"));
				System.exit(0);
			}
		});
		mf.setResizable(false);
		
		mf.setBounds(100,100,250,500);
		Container mfc=mf.getContentPane();
		mfc.setLayout(null);
		JButton zl=new JButton("种类");
		zl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new wZL();
			}
		});
		zl.setBounds(20,10,60,30);
		mfc.add(zl);
		JButton sp=new JButton("商品");
		sp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new wCP();
			}
		});
		sp.setBounds(90,10,60,30);
		mfc.add(sp);
		JButton kc=new JButton("库存");
		kc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf,"lock/KCx.txt")){
					new KCx(user);
				}
			}
		});
		kc.setBounds(160,10,60,30);
		mfc.add(kc);
		JButton xs=new JButton("销售");
		xs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf,"lock/XSF.txt")){
					new XSF(user);
				}
			}
		});
		xs.setBounds(20,50,60,30);
		mfc.add(xs);
		JButton ys=new JButton("应收");
		ys.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf, "lock/YS.txt")){
					new YS(user);
				}
				
			}
		});
		ys.setBounds(90,50,60,30);
		mfc.add(ys);
		JButton yf=new JButton("应付");
		yf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YF();
			}
		});
		yf.setBounds(160,50,60,30);
		mfc.add(yf);
		JButton dh=new JButton("需定");
		dh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DH();
			}
		});
		dh.setBounds(20,90,60,30);
		mfc.add(dh);
		JButton zz=new JButton("组装");
		zz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf,"lock/Assemble.txt")){
					new Assemble(user);
				}
				
			}
		});
		zz.setBounds(90,90,60,30);
		mfc.add(zz);
		JButton nbc=new JButton("内部");
		nbc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf, "lock/NBC.txt")){
					new NBC(user);
				}
				
			}
		});
		nbc.setBounds(160,90,60,30);
		mfc.add(nbc);
		JButton wx=new JButton("维修");
		wx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf, "lock/RepairList.txt")){
					new RepairList(user);
				}
			}
		});
		wx.setBounds(160,130,60,30);
		mfc.add(wx);
		JButton gysgl=new JButton("供应");
		gysgl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GYSmanage();
			}
		});
		gysgl.setBounds(90,130,60,30);
		mfc.add(gysgl);
		JButton tjjhf=new JButton("进统");
		tjjhf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TJJHF();
			}
		});
		tjjhf.setBounds(20,130,60,30);
		mfc.add(tjjhf);
		JButton tjchf=new JButton("出统");
		tjchf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TJCHF();
			}
		});
		tjchf.setBounds(20,170,60,30);
		mfc.add(tjchf);
		JButton qSAR=new JButton("查询");
		qSAR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new QuerySaleAndRepair(user);
			}
		});
		qSAR.setBounds(90,170,60,30);
		mfc.add(qSAR);
		JButton queryAllOrder=new JButton("订单");
		queryAllOrder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new QueryAllOrder(user);
			}
		});
		queryAllOrder.setBounds(160,170,60,30);
		mfc.add(queryAllOrder);
		JButton customerinfo=new JButton("客户");
		customerinfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleLock(mf,"lock/CustomerInfo.txt")==true){
					new CustomerInfo(user);
				}
			}
		});
		customerinfo.setBounds(20,210,60,30);
		mfc.add(customerinfo);
		mf.setVisible(true);
	}
}
