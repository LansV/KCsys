package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
public class MF {
	public MF(int userid,String user){
		JFrame mf=new JFrame("主菜单");
		mf.setResizable(false);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				new KCx(user);
			}
		});
		kc.setBounds(160,10,60,30);
		mfc.add(kc);
		JButton xs=new JButton("销售");
		xs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new XSF(user);
			}
		});
		xs.setBounds(20,50,60,30);
		mfc.add(xs);
		JButton ys=new JButton("应收");
		ys.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YS(user);
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
				new Assemble(user);
			}
		});
		zz.setBounds(90,90,60,30);
		mfc.add(zz);
		JButton nbc=new JButton("内部");
		nbc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NBC();
			}
		});
		nbc.setBounds(160,90,60,30);
		mfc.add(nbc);
		JButton wx=new JButton("维修");
		wx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RepairList(user);
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
//		JButton assemble=new JButton("组装");
//		assemble.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				new Assemble(user);
//			}
//		});
//		assemble.setBounds(160,170,60,30);
//		mfc.add(assemble);
		mf.setVisible(true);
	}
}
