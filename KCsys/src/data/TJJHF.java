package data;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TJJHF {
	public JTable mt(String gys,String qdate,String edate,String sp){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //������Ⱦ��
	    tcr.setHorizontalAlignment(JLabel.CENTER);                      //ȫ�־���
		TJJHFdate d=new TJJHFdate();
		JTable mt=new JTable();
		String[] xcn={"��Ӧ�̱��","��Ӧ��","����","���","����","����","��λ","����","�ܼ�","����"};
		String[][] arr=d.getdate(gys,qdate,edate,sp);
		DefaultTableModel xdm=new DefaultTableModel(arr,xcn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //���ز��ɱ༭
			}
		};
		mt.setModel(xdm);
		mt.setDefaultRenderer(Object.class, tcr);
		mt.setRowHeight(20);
    	TableColumn mtc=mt.getColumnModel().getColumn(1);   //�����п�    
    	mtc.setPreferredWidth(160);   
    	mtc.setMinWidth(160);
    	mtc.setMaxWidth(160);
    	TableColumn mtc2=mt.getColumnModel().getColumn(4);   //�����п�    
    	mtc2.setPreferredWidth(160);   
    	mtc2.setMinWidth(160);
    	mtc2.setMaxWidth(160);
		return mt;
	}
	public TJJHF(){
		JFrame mf=new JFrame("����ͳ��");
		Container mc=mf.getContentPane();
		mc.setLayout(null);
		mf.setBounds(100,100,935,550);
		mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JScrollPane mjsp=new JScrollPane();
		mjsp.setBounds(10,10,900,450);
		JTable mt=mt("","","","");
		mjsp.setViewportView(mt);
		Double jehj=0.0;
		int slhj=0;
		for(int i=0;i<mt.getRowCount();i++){
			String s=mt.getValueAt(i,8).toString();
			String s1=mt.getValueAt(i,5).toString();
			int sl=Integer.parseInt(s1);
			Double je=Double.parseDouble(s);
			slhj=sl+slhj;
			jehj=je+jehj;
		}
		JTextField tgys=new JTextField();
		tgys.setBounds(20,465,40,25);
		JTextField tdate=new JTextField();
		tdate.setBounds(120,465,80,25);
		JTextField tedate=new JTextField();
		tedate.setBounds(210,465,80,25);
		JTextField tsp=new JTextField();
		tsp.setBounds(320,465,80,25);
		JLabel showslhj=new JLabel();
		showslhj.setText(Integer.toString(slhj));
		showslhj.setBounds(565,465,100,25);
		JLabel showjehj=new JLabel();
		showjehj.setText(String.format("%.3f", jehj));
		showjehj.setBounds(760,465,100,25);
		JPanel mp=new JPanel();
		mp.setLayout(null);
		mp.setBounds(0, 0, 1000, 500);
		JButton jcx=new JButton("��ѯ");
		jcx.setBounds(420,465,60,25);
		jcx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTable mt=mt(tgys.getText(),tdate.getText(),tedate.getText(),tsp.getText());
				mjsp.setViewportView(mt);
				int slhj=0;
				Double jehj=0.0;
				for(int i=0;i<mt.getRowCount();i++){
					String s=mt.getValueAt(i,8).toString();
					String s1=mt.getValueAt(i,5).toString();
					int sl=Integer.parseInt(s1);
					Double je=Double.parseDouble(s);
					slhj=sl+slhj;
					jehj=je+jehj;
				}
				showslhj.setText(Integer.toString(slhj));
				showjehj.setText(String.format("%.3f", jehj));
			}
			
		});
		tgys.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					jcx.doClick();
				}
			}
		});
		mp.add(jcx);
		mp.add(tsp);
		mp.add(tdate);
		mp.add(tgys);
		mp.add(tedate);
		mp.add(tsp);
		mp.add(showjehj);
		mp.add(showslhj);
		mp.add(mjsp);
		mc.add(mp);
		mf.setVisible(true);
	}
	public static void main(String[] args){
		new TJJHF();
	}
}
