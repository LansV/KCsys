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

public class TJCHF {
	TJCHFdata d=new TJCHFdata();
	public JTable mt(String qx,String qdate,String edate,String sp){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);      
		String[] cn={"单号","识别号","商品","单位","数量","成本","售价","折扣","成本总计","销售总计","日期","属性"};
		JTable mt=new JTable();
		String[][] arr=d.getdate(qx, qdate, edate, sp);
		DefaultTableModel xdm=new DefaultTableModel(arr,cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		mt.setModel(xdm);
		mt.setDefaultRenderer(Object.class, tcr);
		mt.setRowHeight(20);
    	TableColumn mtc=mt.getColumnModel().getColumn(2);   //设置列宽    
    	mtc.setPreferredWidth(160);   
    	mtc.setMinWidth(160);
    	mtc.setMaxWidth(160);
    	TableColumn mtc2=mt.getColumnModel().getColumn(10);   //设置列宽    
    	mtc2.setPreferredWidth(80);   
    	mtc2.setMinWidth(80);
    	mtc2.setMaxWidth(80);
		return mt;
	}
	public TJCHF(){
		JFrame mf=new JFrame("出货统计");
		mf.setResizable(false);
		Container mc=mf.getContentPane();
		mc.setLayout(null);
		mf.setBounds(100,100,935,550);
		mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JScrollPane mjsp=new JScrollPane();
		mjsp.setBounds(10,10,900,450);
		JTable mt=mt("","","","");
		mjsp.setViewportView(mt);
		Double jehj=0.0;
		Double cbhj=0.0;
		int slhj=0;
		for(int i=0;i<mt.getRowCount();i++){
			String scb=mt.getValueAt(i,8).toString();
			String s=mt.getValueAt(i,9).toString();
			String s1=mt.getValueAt(i,4).toString();
			int sl=Integer.parseInt(s1);
			Double je=Double.parseDouble(s);
			Double cb=Double.parseDouble(scb);
			cbhj=cb+cbhj;
			slhj=sl+slhj;
			jehj=je+jehj;
		}
		JTextField tqx=new JTextField();
		tqx.setBounds(20,465,40,25);
		JTextField tdate=new JTextField();
		tdate.setBounds(120,465,80,25);
		JTextField tedate=new JTextField();
		tedate.setBounds(210,465,80,25);
		JTextField tsp=new JTextField();
		tsp.setBounds(320,465,80,25);
		JLabel showslhj=new JLabel();
		showslhj.setText(Integer.toString(slhj));
		showslhj.setBounds(575,465,100,25);
		JLabel showcbhj=new JLabel();
		showcbhj.setText(String.format("%.3f", cbhj));
		showcbhj.setBounds(642,465,100,25);
		JLabel showjehj=new JLabel();
		showjehj.setText(String.format("%.3f", jehj));
		showjehj.setBounds(708,465,100,25);
		JPanel mp=new JPanel();
		mp.setLayout(null);
		mp.setBounds(0, 0, 1000, 500);
		JButton jcx=new JButton("查询");
		jcx.setBounds(420,465,60,25);
		jcx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTable mt=mt(tqx.getText().trim(),tdate.getText().trim(),tedate.getText().trim(),tsp.getText().trim());
				mjsp.setViewportView(mt);
				int slhj=0;
				Double jehj=0.0;
				Double cbhj=0.0;
				for(int i=0;i<mt.getRowCount();i++){
					String scb=mt.getValueAt(i,8).toString();
					String s=mt.getValueAt(i,9).toString();
					String s1=mt.getValueAt(i,4).toString();
					int sl=Integer.parseInt(s1);
					Double je=Double.parseDouble(s);
					Double cb=Double.parseDouble(scb);
					cbhj=cb+cbhj;
					slhj=sl+slhj;
					jehj=je+jehj;
				}
				showslhj.setText(Integer.toString(slhj));
				showjehj.setText(String.format("%.3f", jehj));
				showcbhj.setText(String.format("%.3f", cbhj));
			}
			
		});
		tqx.addKeyListener(new KeyAdapter(){
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
		mp.add(tqx);
		mp.add(tedate);
		mp.add(tsp);
		mp.add(showjehj);
		mp.add(showslhj);
		mp.add(showcbhj);
		mp.add(mjsp);
		mc.add(mp);
		mf.setVisible(true);
	}
	public static void main(String[] args){
		new TJCHF();
	}
}
