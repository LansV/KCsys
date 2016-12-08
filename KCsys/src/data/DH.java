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

public class DH {
	wData w=new wData();
	getData gd=new getData();
	public DH(){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    //--------------------------------------------数量----------------------------------------------------------
		JFrame dhf=new JFrame("数量");
		dhf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container dhc=dhf.getContentPane();
		dhc.setLayout(null);
		dhf.setBounds(800,380,200,80);
		JLabel dhl=new JLabel("数量:");
		dhl.setBounds(10,10,60,25);
		JTextField slt=new JTextField();
		slt.setBounds(45,10,140,25);
		//JLabel thsl=new JLabel("数量:");
		//thsl.setBounds(10,50,60,25);
		//JTextField thtsl=new JTextField();
		//thtsl.setBounds(45,50,140,25);
		dhc.add(dhl);
		//thc.add(thsl);
		dhc.add(slt);
		dhf.setResizable(false);
	    //------------------------------------------主面板----------------------------------------------------------
		JFrame mframe=new JFrame("订货");
		mframe.setResizable(false);
		mframe.setBounds(350,100,350,500);
		mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container mc=mframe.getContentPane();
		mc.setLayout(null);
		JTable mtable=new JTable();
		String[] mcn={"商品名称","状态","日期","数量"};
		String[][] arr=gd.getdhsp();
		DefaultTableModel mdm=new DefaultTableModel(arr,mcn){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		mtable.setModel(mdm);
		mtable.setDefaultRenderer(Object.class,tcr);
		mtable.setRowHeight(20);
    	TableColumn mtablecl=mtable.getColumnModel().getColumn(0);   //设置列宽    
    	mtablecl.setPreferredWidth(140);   
    	mtablecl.setMinWidth(140);
    	mtablecl.setMaxWidth(140);
    	TableColumn mtablecl1=mtable.getColumnModel().getColumn(1);   //设置列宽    
    	mtablecl1.setPreferredWidth(50);   
    	mtablecl1.setMinWidth(50);
    	mtablecl1.setMaxWidth(50);
    	TableColumn mtablecl2=mtable.getColumnModel().getColumn(2);   //设置列宽    
    	mtablecl2.setPreferredWidth(80);   
    	mtablecl2.setMinWidth(80);
    	mtablecl2.setMaxWidth(80);
		JPopupMenu pm=new JPopupMenu();
		JMenuItem dh=new JMenuItem("订货");
		JMenuItem sh=new JMenuItem("收货");
		dh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mframe.setEnabled(false);
				dhf.setVisible(true);
			}
		});
		sh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r=mtable.getSelectedRow();
				String sp=mtable.getValueAt(r,0).toString().trim();
				//int sl=Integer.parseInt(mtable.getValueAt(r,3).toString().trim());
				w.sh(sp);
				//w.wkcin(sl,sp,"","进货");
				String[][] arr2=gd.getdhsp();
				mdm.setDataVector(arr2,mcn);
		    	TableColumn mtablecl=mtable.getColumnModel().getColumn(0);   //设置列宽    
		    	mtablecl.setPreferredWidth(140);   
		    	mtablecl.setMinWidth(140);
		    	mtablecl.setMaxWidth(140);
		    	TableColumn mtablecl1=mtable.getColumnModel().getColumn(1);   //设置列宽    
		    	mtablecl1.setPreferredWidth(50);   
		    	mtablecl1.setMinWidth(50);
		    	mtablecl1.setMaxWidth(50);
		    	TableColumn mtablecl2=mtable.getColumnModel().getColumn(2);   //设置列宽    
		    	mtablecl2.setPreferredWidth(80);   
		    	mtablecl2.setMinWidth(80);
		    	mtablecl2.setMaxWidth(80);
			}
		});
		pm.add(dh);pm.add(sh);
    	mtable.addMouseListener(new MouseAdapter(){
    		public void mousePressed(MouseEvent e){
    			if(e.getButton()==3){
    				int r=mtable.rowAtPoint(e.getPoint());
    				if(mtable.getRowSelectionAllowed()==true){
    					mtable.setRowSelectionInterval(r,r);
    					if(mtable.getValueAt(r,1).toString().equals("需订")){
    						dh.setEnabled(true);
    						sh.setEnabled(false);
    						pm.show(mtable,e.getX(),e.getY());
    					}else{
    						dh.setEnabled(false);
    						sh.setEnabled(true);
    						pm.show(mtable,e.getX(),e.getY());
    					}
    				}
    			}
    		}
    	});
		JPanel mp=new JPanel();
		mp.setBounds(0, 0,350,500);
		mp.setLayout(null);
		JScrollPane msp=new JScrollPane();
		msp.setViewportView(mtable);
		msp.setBounds(7,5,330,457);
		mp.add(msp);
		mc.add(mp);
		mframe.setVisible(true);
		//----------------------------------slt监听----------------------------------------------------
		slt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					if(slt.getText().trim().length()!=0){
						try{
							String ssl=slt.getText().trim();
							int sl=Integer.parseInt(ssl);
							if(sl==0){
								JOptionPane.showMessageDialog(null,"订货数量不能为0");
							}else{
								int r=mtable.getSelectedRow();
								String sp=mtable.getValueAt(r,0).toString();
								w.updatedh(sp,sl);
								w.wdh(sp,sl);
								slt.setText("");
								mframe.setEnabled(true);
								dhf.dispose();
								String[][] arr2=gd.getdhsp();
								mdm.setDataVector(arr2,mcn);
						    	TableColumn mtablecl=mtable.getColumnModel().getColumn(0);   //设置列宽    
						    	mtablecl.setPreferredWidth(140);   
						    	mtablecl.setMinWidth(140);
						    	mtablecl.setMaxWidth(140);
						    	TableColumn mtablecl1=mtable.getColumnModel().getColumn(1);   //设置列宽    
						    	mtablecl1.setPreferredWidth(50);   
						    	mtablecl1.setMinWidth(50);
						    	mtablecl1.setMaxWidth(50);
						    	TableColumn mtablecl2=mtable.getColumnModel().getColumn(2);   //设置列宽    
						    	mtablecl2.setPreferredWidth(80);   
						    	mtablecl2.setMinWidth(80);
						    	mtablecl2.setMaxWidth(80);
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"输入非法");
						}
					}else{
						JOptionPane.showMessageDialog(null,"输入为空");
					}
				}
			}
		});
		//---------------------------------dhf监听----------------------------------------------------
		dhf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				mframe.setEnabled(true);
			}
		});
	}
	public static void main(String[] args){
		new DH();
	}
}
