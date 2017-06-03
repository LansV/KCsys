package data;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import security.CheckDate;
import tool.PrintRegister;

public class ProductRegister {
	public ProductRegister(String user){
		MainFrame(user).setVisible(true);
	}
	public static void main(String[] args){
		new ProductRegister("test");
	}
	public JFrame MainFrame(String user){
	   	
		ProductRegisterData d=new ProductRegisterData();
		JFrame PR_Frame=new JFrame("产品登记");
		PR_Frame.setResizable(false);
		PR_Frame.setBounds(500,100,300,480);
		PR_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container PR_Frame_C=PR_Frame.getContentPane();
		PR_Frame_C.setLayout(null);
		JLabel choseSp_L=new JLabel("选择商品");
		choseSp_L.setBounds(165,10,80,24);
		PR_Frame_C.add(choseSp_L);
		JLabel sp_no_L=new JLabel();
		sp_no_L.setBounds(20,40,60,24);
		PR_Frame_C.add(sp_no_L);
		JComboBox<String> choseSp_JC=new JComboBox<String>();
		choseSp_JC.setBounds(100,40,180,24);
		String[][] spName=d.getSpName();
		for(int i=0;i<spName.length;i++){
			choseSp_JC.addItem(spName[i][1]);
		}
		choseSp_JC.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				int i=choseSp_JC.getSelectedIndex();
				sp_no_L.setText(spName[i][0]);
			}
		});
		PR_Frame_C.add(choseSp_JC);
		JLabel pumpNo_L=new JLabel("泵头编号");
		pumpNo_L.setBounds(115,80,80,24);
		PR_Frame_C.add(pumpNo_L);
		JTextField pumpNo_T=new JTextField();
		pumpNo_T.setBounds(50,110,200,24);
		PR_Frame_C.add(pumpNo_T);
		JLabel motorNo_L=new JLabel("电机编号");
		motorNo_L.setBounds(115,150,80,24);
		PR_Frame_C.add(motorNo_L);
		JTextField motorNo_T=new JTextField();
		motorNo_T.setBounds(50,180,200,24);
		PR_Frame_C.add(motorNo_T);
		JLabel assembler_L=new JLabel("装配");
		assembler_L.setBounds(130,220,80,24);
		PR_Frame_C.add(assembler_L);
		JTextField assembler_T=new JTextField();
		assembler_T.setBounds(50,250,200,24);
		PR_Frame_C.add(assembler_T);
		JLabel qc_L=new JLabel("质检");
		qc_L.setBounds(130,290,80,24);
		PR_Frame_C.add(qc_L);
		JTextField qc_T=new JTextField();
		qc_T.setBounds(50,320,200,24);
		PR_Frame_C.add(qc_T);
		JLabel productNo=new JLabel("",JLabel.CENTER);
		productNo.setBounds(50,360,200,24);
		productNo.setText(d.getSN());
		PR_Frame_C.add(productNo);
		JButton submitB=new JButton("提交");
		submitB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckDate.ReturnCheckDateResult(submitB);
				// TODO Auto-generated method stub
				String sbh=sp_no_L.getText().trim();
				String name=choseSp_JC.getSelectedItem().toString();
				String pumpNo=pumpNo_T.getText().trim();
				String motorNo=motorNo_T.getText().trim();
				String assemble=assembler_T.getText().trim();
				String qc=qc_T.getText().trim();
				String tlNo=productNo.getText();
				if(sbh.length()!=0&&name.length()!=0&&pumpNo.length()!=0&&motorNo.length()!=0&&assemble.length()!=0
						&&qc.length()!=0&&tlNo.length()!=0){
					d.insertProductRegister(sbh, name, pumpNo, motorNo, tlNo, assemble, qc, user);
					choseSp_JC.setSelectedIndex(0);
					pumpNo_T.setText("");
					motorNo_T.setText("");
					assembler_T.setText("");
					qc_T.setText("");
					productNo.setText(d.getSN());
					PrintRegister.setRegisterInfo(name, pumpNo, motorNo, tlNo);
					new PrintRegister();
				}else{
					JOptionPane.showMessageDialog(PR_Frame, "请补全信息");
				}
			}
		});
		submitB.setBounds(120,400,60,24);
		PR_Frame_C.add(submitB);
		return PR_Frame;
	}
}
