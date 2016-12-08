package data;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class YF {
	getData d=new getData();
	wData w=new wData();
	Double ys;
	int wzx;
	int wzy;
	public YF(){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //������Ⱦ��
	    tcr.setHorizontalAlignment(JLabel.CENTER);                      //ȫ�־���

		//--------------------------------------��д���--------------------------------------------------------------
		JFrame xf=new JFrame("����");
		xf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container xfc=xf.getContentPane();
		xfc.setLayout(null);
		xf.setBounds(800,380,200,100);
		JTextField xt=new JTextField();
		xt.setBounds(10,20,160,25);
		xfc.add(xt);
		xf.setResizable(false);
		//---------------------------------------�������----------------------------------------------------
		JFrame ff=new JFrame("����");
		ff.setResizable(false);
		ff.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container ffc=ff.getContentPane();
		ffc.setLayout(null);
		ff.setBounds(350,180,650,350);
		JTable table=new JTable();
		table.getTableHeader().setReorderingAllowed(false);
	    table.setDefaultRenderer(Object.class, tcr);
		String[] xcn={"����","���","����","����","����","�ܼ�","�Ѹ�","Ӧ��","������"};
		DefaultTableModel xdm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //���ز��ɱ༭
			}
		};
		table.setModel(xdm);
		//------------------------------------------������-------------------------------------------------
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub	
				if(e.getButton()==1&&e.getClickCount()==2){
					ff.setEnabled(false);
					xf.setVisible(true);
				}
			}
		});
		//-----------------------------------------------------------------------------------------------
		JScrollPane xjsp=new JScrollPane();
		xjsp.setViewportView(table);
		xjsp.setBounds(10,5,625,310);
		ffc.add(xjsp);
		//-----------------------------------------------------�����---------------------------------------------
		JFrame f=new JFrame("Ӧ��");
		f.setResizable(false);
		Container fc=f.getContentPane();
		fc.setLayout(null);
		f.setBounds(1000,50,400,650);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String[][] arr=d.yf();
		String[] cxcn={"���","��Ӧ������","Ӧ��","��������"};
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
		jtab.setRowHeight(20);
		TableColumn cj=jtab.getColumnModel().getColumn(1);   //�����п�    
    	cj.setPreferredWidth(140);   
    	cj.setMinWidth(140);
    	cj.setMaxWidth(40);
		jtab.setDefaultRenderer(Object.class, tcr);
		jtab.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2&&e.getButton()==1){
					f.setEnabled(false);
					int r=jtab.getSelectedRow();
					String s=jtab.getValueAt(r,0).toString().trim();
					String[][] xarr=d.xyf(s);
					xdm.setDataVector(xarr, xcn);
					ff.setVisible(true);
				}
			}
		});
		//-------------------------------------------------���������-------------------------------
		xt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					if(xt.getText().length()==0){
						JOptionPane.showMessageDialog(null,"����Ϊ��");
					}else{
						try{
							String sxt=xt.getText().trim();
							Double fkje=Double.parseDouble(sxt);
							if(fkje<=0){
								JOptionPane.showMessageDialog(null,"����С������");
							}else{
								int tr=table.getSelectedRow();
								String dh=table.getValueAt(tr,0).toString();
								Double ysje=Double.parseDouble(table.getValueAt(tr,7).toString());
								Double sqje=Double.parseDouble(table.getValueAt(tr,6).toString());
								Double zjje=Double.parseDouble(table.getValueAt(tr,5).toString());
								if(fkje<ysje){
									w.updateyf(dh,fkje+sqje,0);
								}else if(fkje>ysje){
									w.updateyf(dh,zjje,1);
									Double syje=fkje-ysje;
									if(table.getRowCount()>1){
										for(int i=0;i<table.getRowCount();i++){
											if(i==tr){
												
											}else{
												String sdh=table.getValueAt(i,0).toString();
												Double sysje=Double.parseDouble(table.getValueAt(i,7).toString());
												Double ssqje=Double.parseDouble(table.getValueAt(i,6).toString());
												if(syje-sysje>0){
													w.updateyf(sdh,sysje+ssqje,1);
													syje=syje-sysje;
												}else if(syje-sysje<0){
													w.updateyf(sdh,syje+ssqje,0);
													break;
												}else{
													w.updateyf(sdh,sysje+ssqje,1);
													break;
												}
											}
										}
									}
									if(syje>0){
										JOptionPane.showMessageDialog(null,"����Ӧ��"+String.format("%.3f", syje)+",���Զ�����");
									}
								}
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"���ݷǷ�");
						}
					}
					int trc=jtab.getSelectedRow();
					String kh=jtab.getValueAt(trc,0).toString().trim();
					String[][] xarr=d.xyf(kh);
					xdm.setDataVector(xarr, xcn);
					xt.setText("");
					ff.setEnabled(true);
					xf.dispose();
				}
			}
		});
		//-------------------------------------------------�����رռ���------------------------------------------
		ff.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				String[][] arr2=d.yf();
				dm.setDataVector(arr2,cxcn);
				TableColumn cj=jtab.getColumnModel().getColumn(1);   //�����п�    
		    	cj.setPreferredWidth(140);   
		    	cj.setMinWidth(140);
		    	cj.setMaxWidth(40);
				f.setEnabled(true);
				ff.dispose();
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
		jsp.setBounds(0,0,375,600);
		JPanel jp=new JPanel();
		jp.setBounds(8,10,375,600);
		jp.setLayout(null);
		jp.add(jsp);
		fc.add(jp);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new YF();
	}
}
