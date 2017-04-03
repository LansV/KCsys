package tool;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class YSXXtest {
	public YSXXtest(){
		String[][] xxarr={{"","","","","","","","","",""}};
		JLabel showhj=new JLabel("10000.00");
		JFrame xxf=new JFrame("销售单据");
		xxf.setResizable(false);
		Container xxfc=xxf.getContentPane();
		//=====================================================table===========================
		JTable xxtable=new JTable();
		xxtable.getTableHeader().setResizingAllowed(false);
		String[] mcn={"序号","商品型号","商品名称","单位","折扣","单价","数量","金额","备注"};
		DefaultTableModel xxmdm=new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		xxtable.setModel(xxmdm);
		JPanel mp=new JPanel();
		JScrollPane msp=new JScrollPane();
		msp.setViewportView(xxtable);
		xxtable.setRowHeight(20);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    xxtable.setDefaultRenderer(Object.class, tcr);
	    xxmdm.setDataVector(xxarr,mcn);
    	TableColumn cktablecxh=xxtable.getColumnModel().getColumn(0);   //设置列宽    
    	cktablecxh.setPreferredWidth(40);   
    	cktablecxh.setMinWidth(40);
    	cktablecxh.setMaxWidth(40);
    	TableColumn cktableczl=xxtable.getColumnModel().getColumn(1);   //设置列宽    
    	cktableczl.setPreferredWidth(120);   
    	cktableczl.setMinWidth(120);
    	cktableczl.setMaxWidth(120);
    	TableColumn cktableccp=xxtable.getColumnModel().getColumn(2);   //设置列宽    
    	cktableccp.setPreferredWidth(180);   
    	cktableccp.setMinWidth(180);
    	cktableccp.setMaxWidth(180);
    	TableColumn cktablecdw=xxtable.getColumnModel().getColumn(3);   //设置列宽    
    	cktablecdw.setPreferredWidth(40);   
    	cktablecdw.setMinWidth(40);
    	cktablecdw.setMaxWidth(40);
    	//===========================================================table================
		JComboBox<String> jc=new JComboBox<String>();
		jc.addItem("快递代收");
		jc.addItem("现金");
		jc.addItem("银行");
		jc.addItem("其他");
		jc.setEnabled(false);
		jc.setBounds(600,60,80,25);
		JPanel jtp=new JPanel();
		jtp.setLayout(null);
		jtp.add(jc);
		JLabel mcl=new JLabel("客户:");
		mcl.setBounds(10,20,40,25);
		JTextField mct=new JTextField();
		mct.setEditable(false);
		mct.setBounds(50,20,180,25);
		jtp.add(mct);
		jtp.add(mcl);
		JLabel lxrl=new JLabel("联系人:");
		lxrl.setBounds(320,20,60,25);
		JTextField lxrt=new JTextField();
		lxrt.setEditable(false);
		lxrt.setBounds(370,20,70,25);
		jtp.add(lxrt);
		jtp.add(lxrl);
		JLabel lxrtell=new JLabel("TEL:");
		lxrtell.setBounds(550,20,60,25);
		JTextField lxrtelt=new JTextField();
		lxrtelt.setEditable(false);
		lxrtelt.setBounds(580,20,90,25);
		jtp.add(lxrtell);
		jtp.add(lxrtelt);
		JLabel addrl=new JLabel("地址:");
		addrl.setBounds(10,60,40,25);
		jtp.add(addrl);
		JTextField addrt=new JTextField();
		addrt.setEditable(false);
		addrt.setBounds(50,60,300,25);
		jtp.add(addrt);
		JLabel ml=new JLabel("1");
		ml.setBounds(20,560,80,25);
		jtp.setBounds(0,0,750,90);
		msp.setBounds(0,0,700,450);
		mp.setLayout(null);
		JButton print_b=new JButton("打印");
		print_b.setBounds(330,560,60,25);
		xxfc.add(print_b);
		mp.add(msp);
		mp.setBounds(18,100,750,450);
		showhj.setBounds(600,560,60,25);
		xxfc.setLayout(null);
		xxfc.add(jtp);
		xxfc.add(ml);
		xxfc.add(mp);
		xxfc.add(showhj);
		xxf.setBounds(20,50,750,630);
		xxf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		xxf.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		xxf.setVisible(true);
	}
	public static void main(String[] args){
		new YSXXtest();
	}
}
