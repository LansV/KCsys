package data;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Assemble {
	AssembleData d=new AssembleData();
	public Assemble(String user){
		JFrame Assemble_MFrame=new JFrame("组装面板");
		Assemble_MFrame.setResizable(false);
		Assemble_MFrame.setAlwaysOnTop(true);
		Assemble_MFrame.setBounds(100,100,400,705);
		Assemble_MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container Assemble_MFrame_Content=Assemble_MFrame.getContentPane();
		Assemble_MFrame_Content.setLayout(null);
		JTextField Assemble_MFrame_JT=new JTextField();
		Assemble_MFrame_JT.setBounds(10, 10, 150, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_JT);
		JButton Assemble_MFrame_QueryB=new JButton("查询");
		Assemble_MFrame_QueryB.setBounds(220, 10, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_QueryB);
		JButton Assemble_MFrame_AddB=new JButton("添加");
		Assemble_MFrame_AddB.setBounds(320, 645, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_AddB);
		//------------------------------------------MFrame_Table--------------------------------------
		JTable Assemble_MFrame_Table=new JTable();
		String[] Assemble_MFrame_TableColumn={"识别号","品名"};
		DefaultTableModel Assemble_MFrame_TableModel=new DefaultTableModel(d.zm(Assemble_MFrame_JT.getText().trim()),Assemble_MFrame_TableColumn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		Assemble_MFrame_Table.setModel(Assemble_MFrame_TableModel);
		JScrollPane Assemble_MFrame_JS=new JScrollPane();
		Assemble_MFrame_JS.setViewportView(Assemble_MFrame_Table);
		Assemble_MFrame_JS.setBounds(10,40,375,600);
		Assemble_MFrame_Content.add(Assemble_MFrame_JS);
		//--------------------------------------------------------------------------------------
		Assemble_MFrame.setVisible(true);
		//---------------------------------------------------------------------------------------
		JFrame mframe=new JFrame("组装");
		Container mc=mframe.getContentPane();
		mframe.setBounds(100,20,500,650);
		mframe.setResizable(false);
		mc.setLayout(null);
		JPanel mpane=new JPanel();
		mpane.setLayout(null);
		JScrollPane mjsp=new JScrollPane();
		JTable mtable=new JTable(){
			private static final long serialVersionUID = 1L;
			public void setValueAt(Object aValue, int rowIndex, int columnIndex){
				if(columnIndex==5){
	                try{
	                	String st=(String) aValue;
						int num =Integer.parseInt(st);
						//System.out.println(kccount.get(rowIndex));
						if(num>kccount.get(rowIndex)){
							JOptionPane.showMessageDialog(null,"超出库存");
							return;
						}
						if(num==0){
							JOptionPane.showMessageDialog(null,"不能为0");
							return;
						}
	                }catch(Exception ex){
	                    JOptionPane.showMessageDialog(null, "只能输入数字!");
	                    return;
	                }
				}  
			      super.setValueAt(aValue,rowIndex,columnIndex);
			}
		};
		mtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						mtable.clearSelection();
					}
				}
			}
		});
		mtable.getTableHeader().setReorderingAllowed(false);
		String[] mcn={"序号","商品型号","商品名称","单位","单价","数量","金额"};
		DefaultTableModel mdm=new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				if(colunm==5){
					return true;
				}
				return false;
			}
		};
		mdm.setColumnIdentifiers(mcn);
		mtable.setRowHeight(20);
		mtable.setModel(mdm);
		mtable.setDefaultRenderer(Object.class, tcr);
    	TableColumn mtablecl3=mtable.getColumnModel().getColumn(1);   //设置列宽    
    	mtablecl3.setPreferredWidth(80);   
    	mtablecl3.setMinWidth(80);
    	mtablecl3.setMaxWidth(80);
    	TableColumn mtablecl=mtable.getColumnModel().getColumn(2);   //设置列宽    
    	mtablecl.setPreferredWidth(130);   
    	mtablecl.setMinWidth(130);
    	mtablecl.setMaxWidth(130);
    	TableColumn mtablecl2=mtable.getColumnModel().getColumn(6);   //设置列宽    
    	mtablecl2.setPreferredWidth(60);   
    	mtablecl2.setMinWidth(60);
    	mtablecl2.setMaxWidth(60);
		mjsp.setViewportView(mtable);
		mjsp.setBounds(7,0,470,580);
		mpane.add(mjsp);
		JButton zzbutton=new JButton("组装");
		zzbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mtable.getRowCount()<2){
					JOptionPane.showMessageDialog(null,"至少添加两种商品进行组装");
				}else{
					mframe.setEnabled(false);
					zm.setVisible(true);
				}
			}
		});
		zzbutton.setBounds(200,585,60,25);
		JButton lszz=new JButton("组装记录");
		lszz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lsdm.setDataVector(gd.zm(),lscn);
				mframe.setEnabled(false);
				lsf.setVisible(true);
			}
		});
		lszz.setBounds(10,585,90,25);
		JLabel zjl=new JLabel();
		zjl.setBounds(420,585,90,25);
		mpane.add(zjl);
		mpane.add(lszz);
		mpane.add(zzbutton);
		mpane.setBounds(0,0,500,630);
		mc.add(mpane);
		mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mframe.setVisible(true);
		Object[] row=new Object[mcn.length];
		row[0]="";
		row[1]="";
		row[2]="";
		row[3]="";
		row[4]="";
		row[5]="";
		row[6]="";
	}
	public static void main(String[] args){
		new Assemble("test");
	}
}
