package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class YS {
	getData d=new getData();
	wData w=new wData();
	Double ys;
	int tabler;
	int wzx;
	int wzy;
	public YS(){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //������Ⱦ��
	    tcr.setHorizontalAlignment(JLabel.CENTER);                      //ȫ�־���
	    //=====================================��ϸ���================================================
	    
	    //--------------------------------------�˻�����-------------------------------------------------------------
		JFrame thf=new JFrame("�˻�");
		thf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container thc=thf.getContentPane();
		thc.setLayout(null);
		thf.setBounds(800,380,200,120);
		JLabel thyz=new JLabel("ԭ��:");
		thyz.setBounds(10,10,60,25);
		JTextField thtyz=new JTextField();
		thtyz.setBounds(45,10,140,25);
		JLabel thsl=new JLabel("����:");
		thsl.setBounds(10,50,60,25);
		JTextField thtsl=new JTextField();
		thtsl.setBounds(45,50,140,25);
		thtsl.setEnabled(false);
		thc.add(thyz);
		thc.add(thsl);
		thc.add(thtyz);
		thc.add(thtsl);
		thf.setResizable(false);
	    //-----------------------------------��ϸ�����--------------------------------------------------------------
		JPopupMenu thpm=new JPopupMenu();
		JMenuItem th=new JMenuItem("�˻�");
		thpm.add(th);
	    JFrame xxdf=new JFrame();
		xxdf.setTitle("��ϸ�б�");
		xxdf.setResizable(false);
		Container xxdc=xxdf.getContentPane();
		xxdf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		xxdc.setLayout(null);
		JTable xxdtable=new JTable();
		xxdtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=xxdtable.rowAtPoint(e.getPoint());
					if(xxdtable.getRowSelectionAllowed()==true){
						xxdtable.setRowSelectionInterval(r,r);
						thpm.show(xxdtable,e.getX(),e.getY());
					}
				}
			}
		});
		String[] xxdmc={"���","��Ʒ�ͺ�","��Ʒ����","��λ","�ۿ�","����","����","���","��ע"};
		DefaultTableModel xxddm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //���ز��ɱ༭
			}
		};
		th.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thtsl.setEnabled(true);
				xxdf.setEnabled(false);
				thf.setVisible(true);
			}
		});
		xxdtable.getTableHeader().setReorderingAllowed(false);
		xxdtable.setModel(xxddm);
	    xxdtable.setDefaultRenderer(Object.class, tcr);
		JScrollPane xxdjsp=new JScrollPane();
		xxdjsp.setViewportView(xxdtable);
		xxdjsp.setBounds(5,5,620,320);
		xxdc.add(xxdjsp);
		//--------------------------------------��д���--------------------------------------------------------------
		JFrame xf=new JFrame("�տ�");
		xf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container xfc=xf.getContentPane();
		xfc.setLayout(null);
		xf.setBounds(800,380,200,100);
		JTextField xt=new JTextField();
		xt.setBounds(10,20,160,25);
		xfc.add(xt);
		xf.setResizable(false);
		//--------------------------------------------��д���˼�¼----------------------------------------------------
		JFrame hzf=new JFrame("����");
		hzf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container hzc=hzf.getContentPane();
		hzc.setLayout(null);
		hzf.setBounds(800,380,200,100);
		JTextField hzt=new JTextField();
		hzt.setBounds(10,20,160,25);
		hzc.add(hzt);
		hzf.setResizable(false);
		//------------------------------------------------------------------------------------------------------------
		JFrame ff=new JFrame("����");
		ff.setResizable(false);
		ff.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container ffc=ff.getContentPane();
		ffc.setLayout(null);
		ff.setBounds(350,180,650,350);
		JTable table=new JTable();
		table.getTableHeader().setReorderingAllowed(false);
	    table.setDefaultRenderer(Object.class, tcr);
		String[] xcn={"����","����","�ܼ�","����","Ӧ��","������"};
		DefaultTableModel xdm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //���ز��ɱ༭
			}
		};
		table.setModel(xdm);
		//-------------------------------------�˻�ԭ���������-----------------------------------------------------
		thtyz.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(thtsl.isEnabled()){
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"ԭ��Ϊ��");
						}else{
							String sth=thtsl.getText().trim();
							if(sth.length()==0){
								JOptionPane.showMessageDialog(null,"����Ϊ��");
							}else{
								try{
									int thsl=Integer.parseInt(sth);
									int r=xxdtable.getSelectedRow();
									int tr=table.getSelectedRow();
									int yysl=Integer.parseInt(xxdtable.getValueAt(r,6).toString().trim());
									if(thsl<=yysl){
										String yy=thtyz.getText().trim(); //�˻�ԭ��
										String dh=table.getValueAt(tr,0).toString().trim();  //��ȡ����
										String kh=table.getValueAt(tr,1).toString().trim();    //��ȡ�ͻ�����
										Double yszj=Double.parseDouble(table.getValueAt(tr,2).toString().trim());//��ȡӦ��
										int bh=Integer.parseInt(xxdtable.getValueAt(r,0).toString().trim());
										String xh=xxdtable.getValueAt(r,1).toString().trim();
										String sp=xxdtable.getValueAt(r,2).toString().trim();   //��ȡ��Ʒ����
										String szk = null;
										Double dj=Double.parseDouble(xxdtable.getValueAt(r,5).toString().trim());//��ȡ��Ʒ����
										Double yzj=Double.parseDouble(xxdtable.getValueAt(r,7).toString().trim());//��ȡԭ��
										Double dje = null;
										try{
											szk=xxdtable.getValueAt(r,4).toString().trim();   //��ȡ�ۿ�
											Double zk=Double.parseDouble(szk.trim());
											dje=Double.parseDouble(String.format("%.2f",zk/10*thsl*dj));   //�õ��˻��ܼ�
										}catch(Exception e2){
											dje=Double.parseDouble(String.format("%.2f",thsl*dj));     //�õ��˻��ܼ�
										}
										//w.gth(dh,kh,sp,thsl,yysl,dj,je,dj,yy);
										Double ysje=Double.parseDouble(String.format("%.2f",yszj-dje));
										Double xsje=Double.parseDouble(String.format("%.2f",yzj-dje));
										w.gth(dh,bh,kh,sp,thsl,yysl,dj,dje,ysje,xsje,yy);
										w.wkcin(xh,sp,thsl,kh+"�˻�");
										System.out.println(yzj);
										System.out.println(dje);
										System.out.println(ysje);
										System.out.println(xsje);
										xxdf.setEnabled(true);
										thtsl.setEnabled(false);
										thtsl.setText("");
										thtyz.setText("");
										thf.dispose();
									}else{
										JOptionPane.showMessageDialog(null,"������������");
									}
								}catch(Exception e1){
									JOptionPane.showMessageDialog(null,"����Ƿ�");
								}
							}
						}
					}else{
						if(thtyz.getText().trim().length()==0){
							JOptionPane.showMessageDialog(null,"ԭ��Ϊ��");
						}else{
							int r=table.getSelectedRow();
							String yy=thtyz.getText().trim();
							String dh=table.getValueAt(r,0).toString().trim();
							String kh=table.getValueAt(r,1).toString().trim();
							Double je=Double.parseDouble(table.getValueAt(r,2).toString().trim());
							w.dth(dh,kh,je,yy);
							int xrc=xxdtable.getRowCount();
							for(int i=0;i<xrc;i++){
								String xh=xxdtable.getValueAt(i,1).toString().trim();
								String sp=xxdtable.getValueAt(i,2).toString().trim();
								String str=xxdtable.getValueAt(i,6).toString().trim();
								int sl=Integer.parseInt(str);
								w.wkcin(xh,sp,sl,kh+"�˻�");
							}
							String[][] xarr=d.xys(kh);
							xdm.setDataVector(xarr,xcn);
							ff.setEnabled(true);
							thtyz.setText("");
							thf.dispose();
						}
					}
				}
			}
			});
		//-------------------------------------�˻������������-----------------------------------------------------
		thtsl.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(thtyz.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,"ԭ��Ϊ��");
					}else{
						String sth=thtsl.getText().trim();
						if(sth.length()==0){
							JOptionPane.showMessageDialog(null,"����Ϊ��");
						}else{
							try{
								int ths=Integer.parseInt(sth);
								System.out.println(ths);
								xxdf.setEnabled(true);
								thtsl.setEnabled(false);
								thtsl.setText("");
								thtyz.setText("");
								thf.dispose();
							}catch(Exception e1){
								JOptionPane.showMessageDialog(null,"����Ƿ�");
							}
						}
					}
				}
			}
		});
		//--------------------------------------�˻����ڼ���------------------------------------------------------
		thf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(xxdf.isVisible()){
					xxdf.setEnabled(true);
				}else{
					ff.setEnabled(true);
				}
				thtsl.setEnabled(false);
				thtsl.setText("");
				thtyz.setText("");
				thf.dispose();
			}
		});
		//--------------------------------------------�Ҽ��˵�--------------------------------------------------------
		JPopupMenu pm=new JPopupMenu();
		JMenuItem mit=new JMenuItem("��ϸ�б�");
		JMenuItem hz=new JMenuItem("��ӻ���");
		JMenuItem qth=new JMenuItem("�˻�");
		pm.add(mit);
		pm.add(hz);
		pm.add(qth);
		//------------------------------------------������-------------------------------------------------
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub	
				if(e.getButton()==3){
					int r=table.rowAtPoint(e.getPoint());
					if(table.getRowSelectionAllowed()==true){
						table.setRowSelectionInterval(r,r);
						pm.show(table,e.getX(),e.getY());
						wzx=e.getX();
						wzy=e.getY();
					}
				}
				if(e.getButton()==1&&e.getClickCount()==2){
					ff.setEnabled(false);
					int r=table.getSelectedRow();
					String s=table.getValueAt(r,4).toString().trim();
					try{
						ys=Double.parseDouble(s);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"���ݴ���");
					}
					tabler=table.getRowCount();
					xf.setVisible(true);
				}
			}
		});
		//----------------------------------------------------------------------------------------------
		qth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=table.getSelectedRow();
				String st=table.getValueAt(r,0).toString().trim();
				if(st.substring(0,1).equals("X")){
					xxddm.setDataVector(d.wxd(st),xxdmc);
				}else{
					xxddm.setDataVector(d.xsd(st),xxdmc);
				}
				thf.setVisible(true);
				ff.setEnabled(false);
			}	
		});
		//----------------------------------------------------------------------------------------------
		mit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=table.getSelectedRow();
				String st=table.getValueAt(r,0).toString().trim();
				if(st.substring(0,1).equals("X")){
					xxddm.setDataVector(d.wxd(st),xxdmc);
				}else{
					xxddm.setDataVector(d.xsd(st),xxdmc);
				}
		    	TableColumn xxdlk=xxdtable.getColumnModel().getColumn(2);   //�����п�    
		    	xxdlk.setPreferredWidth(180);   
		    	xxdlk.setMinWidth(180);
		    	xxdlk.setMaxWidth(180);
		    	ff.setEnabled(false);
		    	xxdf.setBounds(200+wzx,250+wzy,650,350);
				xxdf.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------------
		xxdf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				xxdf.dispose();
			}
		});
		//--------------------------------�������д�뻵��---------------------------------------------
		hzt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()=='\n'){
					int r=table.getSelectedRow();
					String dh=table.getValueAt(r,0).toString().trim();
					String kh=table.getValueAt(r,1).toString().trim();
					String st=table.getValueAt(r,4).toString().trim();
					Double je=Double.parseDouble(st);
					String bz=hzt.getText().trim();
					w.whz(dh,kh,je,bz);
					String[][] xarr=d.xys(kh);
					xdm.setDataVector(xarr, xcn);
					hzt.setText("");
					ff.setEnabled(true);
					hzf.dispose();
				}
			}
		});
		//-----------------------------------------------------------------------------------------------
		hz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(false);
				hzf.setVisible(true);
			}
		});
		//-----------------------------------------------------------------------------------------------
		JScrollPane xjsp=new JScrollPane();
		xjsp.setViewportView(table);
		xjsp.setBounds(10,5,625,310);
		ffc.add(xjsp);
		//---------------------------------------------------Ӧ�������-------------------------------------------
		JFrame f=new JFrame("Ӧ��");
		f.setResizable(false);
		Container fc=f.getContentPane();
		fc.setLayout(null);
		f.setBounds(1000,50,320,650);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String[][] arr=d.ys();
		String[] cxcn={"�ͻ�����","Ӧ��","����"};
		JScrollPane jsp=new JScrollPane();
		JTable jtab=new JTable();
		jtab.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel dm=new DefaultTableModel(arr,cxcn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //���ز��ɱ༭
			}
		};
		jtab.setModel(dm);
		TableColumn cj=jtab.getColumnModel().getColumn(0);   //�����п�    
    	cj.setPreferredWidth(140);   
    	cj.setMinWidth(140);
    	cj.setMaxWidth(40);
		jtab.setDefaultRenderer(Object.class, tcr);
		jtab.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				// TODO Auto-generated method stub
				if(e.getClickCount()==2&&e.getButton()==1){
					f.setEnabled(false);
					int r=jtab.getSelectedRow();
					String s=jtab.getValueAt(r,0).toString().trim();
					String[][] xarr=d.xys(s);
					xdm.setDataVector(xarr, xcn);
					ff.setVisible(true);
				}
			}
		});
		xt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					int sr=table.getSelectedRow();
					String s=xt.getText().trim();
					if(s.length()==0){
						JOptionPane.showMessageDialog(null,"���Ϊ��ֵ");
					}else{
						try{
							Double sk=Double.parseDouble(s);
							if(ys-sk<0){
								String dh=table.getValueAt(sr,0).toString().trim();    //��ȡ����
								String ssq=table.getValueAt(sr,3).toString().trim();  //��ȡ���ڽ��
								Double sq1=Double.parseDouble(ssq);
								Double zj=ys+sq1;
								w.updateys(dh,zj,0);
								table.setValueAt(String.format("%.2f",ys+sq1),sr,3);
								table.setValueAt(0,sr,4);
								sk=Double.parseDouble(String.format("%.2f",sk-ys));
								if(tabler>1){ //���Ϊ�൥��
									JOptionPane.showMessageDialog(null,"���������,���Զ��������½��");
									for(int i=0;i<tabler;i++){
										if(sr==i){
											//����ѡ����
										}else{
											String gdh=table.getValueAt(i,0).toString().trim();    //��ȡ����
											String st=table.getValueAt(i,4).toString().trim();  //��ȡӦ�ս��
											String st2=table.getValueAt(i,3).toString().trim();  //��ȡ���ڽ��
											Double zys=Double.parseDouble(st);
											Double sq=Double.parseDouble(st2);
											if(sk-zys>0||sk-zys==0){        //�ж��Ƿ���ʣ��
												sk=sk-zys;
												Double gzj=zys+sq;
												table.setValueAt(String.format("%.2f",zys+sq),i,3);
												table.setValueAt(0,i,4);
												w.updateys(gdh,gzj,0);
												if(sk>0&&i==tabler-1){      //���һ���������ʣ�� JP��ʾ
													JOptionPane.showMessageDialog(null,"������Ӧ��"+String.format("%.2f",sk)+"Ԫ");
												}
											}else{  
												table.setValueAt(String.format("%.2f",sk+sq),i,3);  
												table.setValueAt(String.format("%.2f",zys-sk),i,4);
												Double gzj=sk+sq;
												w.updateys(gdh,gzj,1);
												break;                     //����ѭ��
											}
										}
									}
								}else{
									if(sk>0){
										JOptionPane.showMessageDialog(null,"������Ӧ��"+String.format("%.2f",sk)+"Ԫ");
									}
								}
								ff.setEnabled(true);
								xf.dispose();
							}else if(ys-sk>0||ys-sk==0){
								String dh=table.getValueAt(sr,0).toString().trim();
								String ssq=table.getValueAt(sr,3).toString().trim();  //��ȡ���ڽ��
								Double sq1=Double.parseDouble(ssq);
								table.setValueAt(sk+sq1,sr,3);
								table.setValueAt(String.format("%.2f",ys-sk),sr,4);
								Double gzj=sk+sq1;
								if(ys-sk==0){
									w.updateys(dh,gzj,0);
								}else{
									w.updateys(dh,gzj,1);
								}
								ff.setEnabled(true);
								xf.dispose();
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"�Ƿ�����");
						}
						String kh=table.getValueAt(sr,1).toString().trim();
						String[][] xarr=d.xys(kh);
						xdm.setDataVector(xarr, xcn);
						xt.setText("");
					}
				}
			}
		});
		//--------------------------------------------------------------------------------------------------------
		ff.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				String[][] arr2=d.ys();
				dm.setDataVector(arr2,cxcn);
				TableColumn cj=jtab.getColumnModel().getColumn(0);   //�����п�    
		    	cj.setPreferredWidth(140);   
		    	cj.setMinWidth(140);
		    	cj.setMaxWidth(40);
				f.setEnabled(true);
				ff.dispose();
			}
		});
		//---------------------------------------------------------------------------------------------------------
		hzf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				hzf.dispose();
			}
		});
		//--------------------------------------------------------------------------------------------------------
		xf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				ff.setEnabled(true);
				xf.dispose();
			}
		});
		//-------------------------------------------------------------------------------------------------------
		jsp.setViewportView(jtab);
		jsp.setBounds(0,0,295,600);
		JPanel jp=new JPanel();
		jp.setBounds(8,10,295,600);
		jp.setLayout(null);
		jp.add(jsp);
		fc.add(jp);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new YS();
	}
}
