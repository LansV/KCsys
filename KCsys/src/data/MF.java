package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
public class MF {
	public MF(int userid,String user){
		JFrame mf=new JFrame("���˵�");
		mf.setResizable(false);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setBounds(100,100,250,500);
		Container mfc=mf.getContentPane();
		mfc.setLayout(null);
		JButton zl=new JButton("����");
		zl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new wZL();
			}
		});
		zl.setBounds(20,10,60,30);
		mfc.add(zl);
		JButton sp=new JButton("��Ʒ");
		sp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new wCP();
			}
		});
		sp.setBounds(90,10,60,30);
		mfc.add(sp);
		JButton kc=new JButton("���");
		kc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new KCx(user);
			}
		});
		kc.setBounds(160,10,60,30);
		mfc.add(kc);
		JButton xs=new JButton("����");
		xs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new XSF(user);
			}
		});
		xs.setBounds(20,50,60,30);
		mfc.add(xs);
		JButton ys=new JButton("Ӧ��");
		ys.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YS(user);
			}
		});
		ys.setBounds(90,50,60,30);
		mfc.add(ys);
		JButton yf=new JButton("Ӧ��");
		yf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YF();
			}
		});
		yf.setBounds(160,50,60,30);
		mfc.add(yf);
		JButton dh=new JButton("�趨");
		dh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DH();
			}
		});
		dh.setBounds(20,90,60,30);
		mfc.add(dh);
		JButton zz=new JButton("��װ");
		zz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Assemble(user);
			}
		});
		zz.setBounds(90,90,60,30);
		mfc.add(zz);
		JButton nbc=new JButton("�ڲ�");
		nbc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NBC();
			}
		});
		nbc.setBounds(160,90,60,30);
		mfc.add(nbc);
		JButton wx=new JButton("ά��");
		wx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RepairList(user);
			}
		});
		wx.setBounds(160,130,60,30);
		mfc.add(wx);
		JButton gysgl=new JButton("��Ӧ");
		gysgl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GYSmanage();
			}
		});
		gysgl.setBounds(90,130,60,30);
		mfc.add(gysgl);
		JButton tjjhf=new JButton("��ͳ");
		tjjhf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TJJHF();
			}
		});
		tjjhf.setBounds(20,130,60,30);
		mfc.add(tjjhf);
		JButton tjchf=new JButton("��ͳ");
		tjchf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TJCHF();
			}
		});
		tjchf.setBounds(20,170,60,30);
		mfc.add(tjchf);
		JButton qSAR=new JButton("��ѯ");
		qSAR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new QuerySaleAndRepair(user);
			}
		});
		qSAR.setBounds(90,170,60,30);
		mfc.add(qSAR);
//		JButton assemble=new JButton("��װ");
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
