package account;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ManageAccountSubject {
	static private JFrame MFrame; 
	static private JTree MTree;
	ManageAccountSubjectData masd=new ManageAccountSubjectData();
	public JTree MyTree(){
		DefaultMutableTreeNode accountClassNode = new DefaultMutableTreeNode("��");
		List<String> classlist=masd.getAccountClass();
		int classlength=classlist.size();
		for(int i=0;i<classlength;i++){
			//�����
			String classid=classlist.get(i).split(":")[0];
			DefaultMutableTreeNode classnode=new DefaultMutableTreeNode(classlist.get(i));
			List<String> firstlist=masd.getFirstSubject(classid);
			int firstlength=firstlist.size();
			if(firstlength!=0){
				//���һ����Ŀ
				for(String first:firstlist){
					String firstid=first.split(":")[0];
					DefaultMutableTreeNode firstnode=new DefaultMutableTreeNode(first);
					List<String> secondlist=masd.getSecondSubject(firstid);
					int secondlength=secondlist.size();
					if(secondlength!=0){
						//��Ӷ�����Ŀ
						for(String second:secondlist){
							String secondid=second.split(":")[0];
							DefaultMutableTreeNode secondnode=new DefaultMutableTreeNode(second);
							List<String> thirdlist=masd.getThirdSubject(secondid);
							int thirdlength=thirdlist.size();
							if(thirdlength!=0){
								//���������Ŀ
								for(String third:thirdlist){
									String thirdid=third.split(":")[0];
									DefaultMutableTreeNode thirdnode=new DefaultMutableTreeNode(third);
									List<String> fourthlist=masd.getFourthSubject(thirdid);
									int fourthlength=fourthlist.size();
									if(fourthlength!=0){
										//����ļ���Ŀ
										for(String fourth:fourthlist){
											String fourthid=fourth.split(":")[0];
											DefaultMutableTreeNode fourthnode=new DefaultMutableTreeNode(fourth);
											List<String> fifthlist=masd.getFifthSubject(fourthid);
											int fifthlength=fifthlist.size();
											if(fifthlength!=0){
												//����弶��Ŀ
												for(String fifth:fifthlist){
													DefaultMutableTreeNode fifthnode=new DefaultMutableTreeNode(fifth);
													fourthnode.add(fifthnode);
												}
											}
											thirdnode.add(fourthnode);
										}
									}
									secondnode.add(thirdnode);
								}
							}
							firstnode.add(secondnode);
						}
					}
					classnode.add(firstnode);
				}
			}
			accountClassNode.add(classnode);
		}
		DefaultTreeModel dtm=new DefaultTreeModel(accountClassNode);
		JTree mytree=new JTree(dtm);
		return mytree;
	};
	public ManageAccountSubject() {
		MFrame=new JFrame();
		MFrame.setBounds(100,100,500,700);
		MFrame.setTitle("��Ŀ����");
		Container MFrameC=MFrame.getContentPane();
		MFrameC.setLayout(null);
		MFrame.setResizable(false);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsp=new JScrollPane();
		final JPopupMenu rm=new JPopupMenu();
		JMenuItem addas=new JMenuItem("����");
		JMenuItem deleteas=new JMenuItem("ɾ��");
		rm.add(addas);
		rm.add("�޸�");
		rm.add(deleteas);
		//MTree=new JTree();
		MTree=MyTree();
		MTree.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				if(e.getButton()==3){
					TreePath path = MTree.getPathForLocation(e.getX(),e.getY());
			        MTree.setSelectionPath(path);
					rm.show(MTree, e.getX(), e.getY());
				}
			}
		});
		//--------------------------------------------------------
		//-----------------------------------------------------------
		JFrame inputFrame=new JFrame();
		inputFrame.setResizable(false);
		inputFrame.setBounds(500,200,300,120);
		Container inputFC=inputFrame.getContentPane();
		inputFC.setLayout(null);
		JLabel subidL=new JLabel("��Ŀ���:");
		subidL.setBounds(10,10,60,20);
		inputFC.add(subidL);
		JTextField subidT=new JTextField();
		subidT.setBounds(70, 10, 210, 20);
		inputFC.add(subidT);
		JLabel subnameL=new JLabel("��Ŀ����:");
		subnameL.setBounds(10,50,60,20);
		inputFC.add(subnameL);
		JTextField subnameT=new JTextField();
		subnameT.setBounds(70, 50, 210, 20);
		inputFC.add(subnameT);
		JButton addsubB=new JButton();
		addsubB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String subid=subidT.getText().trim();
				String subname=subnameT.getText().trim();
				if(subid.length()!=0&&subname.length()!=0){
					String regEx="\\D";
					Pattern p=Pattern.compile(regEx);
					if(!p.matcher(subid).find()){
						TreePath[] selectpath=MTree.getSelectionPaths();
						TreePath t=MTree.getSelectionPath();
						DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) MTree
								 .getLastSelectedPathComponent();
						if(t==null){
							System.out.println("null");
							return;
						}
						String temp=new TreePath(treeNode).toString();
						temp=temp.replace("[", "");
						temp=temp.replace("]", "");
						String[] tempz=temp.split(":");
						String parentsubid=tempz[0].trim();
						int psidl=parentsubid.length();
						if(subid.length()>psidl){
							String frontid=subid.substring(0, psidl).trim();
							if(parentsubid.equals(frontid)){
								int subgrade=t.getPathCount()-1;
								System.out.println("������ӿ�Ŀ�ȼ���"+subgrade);
								List<String> existSubId=masd.getExistSubId(subgrade, frontid);
								for(String s:existSubId){
									if(subid.equals(s)){
										JOptionPane.showMessageDialog(inputFrame, "��Ŀ����ظ�");
										return;
									}
								}
								String s=selectpath[0].toString().replace("[", "");
								s=s.replace("]", "");
								s=s.replace(":", ",");
								s=s.replace(" ", "");
								s=s.substring(2, s.length());
								s=s+","+subid+","+subname;
								String[] st=s.split(",");
								masd.addSubject(st);
								DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(subid+":"+subname);
								treeNode.add(newNode);
								DefaultTreeModel model=(DefaultTreeModel) MTree.getModel();
								TreeNode[] nodes = model.getPathToRoot(newNode);    
								TreePath path = new TreePath(nodes);    
								MTree.scrollPathToVisible(path);  
								MTree.setSelectionPath(path);
								MTree.updateUI();
								subidT.setText("");
								subnameT.setText("");
								MFrame.setEnabled(true);
								inputFrame.dispose();
							}else{
								JOptionPane.showMessageDialog(inputFrame, "��Ŀ��ŷǷ���ǰ�˿�Ŀ�������\nӦΪ"+parentsubid+"***");
							}
							System.out.println("�ϼ���Ŀ��ţ�"+parentsubid+'\n'+"������ӿ�Ŀ��ȡ��ţ�"+frontid);
						}else{
							JOptionPane.showMessageDialog(inputFrame, "��Ŀ��ŷǷ�����������ڸ���Ŀ");
						}
					}else{
						JOptionPane.showMessageDialog(inputFrame, "��Ŀ��ű���Ϊ������");
					}
				}else{
					JOptionPane.showMessageDialog(inputFrame, "��ȷ��ֵ��Ϊ��");
				}
			}
			
		});
		subidT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar()=='\n'){
					addsubB.doClick();
				}
			}
		});
		subnameT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar()=='\n'){
					addsubB.doClick();
				}
			}
		});
		inputFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inputFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO �Զ����ɵķ���
				subidT.setText("");
				subnameT.setText("");
				MFrame.setEnabled(true);
				inputFrame.dispose();
			}
		});
		//------------------------------------------
		//-----------------------------------------
		addas.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TreePath t=MTree.getSelectionPath();
				if(t==null){
					System.out.println("null");
					return;
				}
				int subgrade=t.getPathCount()-1;
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) t.getLastPathComponent();
				if(subgrade>1){
					if(subgrade<6){
						System.out.println("�������ӿ�Ŀ�ȼ���"+subgrade);
						String temp=new TreePath(treeNode).toString();
						temp=temp.replace("[", "");
						temp=temp.replace("]", "");
						String[] tempz=temp.split(":");
						String frontsubid=tempz[0];
						System.out.println("�������ӿ�Ŀ����Ŀ��"+frontsubid);
						subidT.setText(masd.getSubId(subgrade, frontsubid));
						MFrame.setEnabled(false);
						inputFrame.setTitle("��д��Ŀ");
						inputFrame.setVisible(true);
						subnameT.requestFocus();
					}else{
						JOptionPane.showMessageDialog(inputFrame, "��ĩ����Ŀ");
					}
				}else{
					
				}
			}
		});
		deleteas.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				TreePath t=MTree.getSelectionPath();
				if(t==null){
					System.out.println("null");
					return;
				}
				int subgrade=t.getPathCount()-2;
				System.out.println("����ɾ����Ŀ�ȼ���"+subgrade);
				if(subgrade>1){
					DefaultMutableTreeNode dnode=(DefaultMutableTreeNode) t.getLastPathComponent();
					String temp=new TreePath(dnode).toString();
					DefaultTreeModel model=(DefaultTreeModel) MTree.getModel();
					model.removeNodeFromParent(dnode);
					temp=temp.replace("[", "");
					temp=temp.replace("]", "");
					String[] tempz=temp.split(":");
					String deleteid=tempz[0].trim();
					System.out.println("ɾ����Ŀ��ţ�"+deleteid);
					masd.deleteSubject(subgrade, deleteid);
				}else{
					JOptionPane.showMessageDialog(inputFrame, "����Ŀ��ֹ�޸�");
				}
			}
		});
		jsp.setViewportView(MTree);
		jsp.setBounds(10,10,470,650);
		MFrameC.add(jsp);
		MFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new ManageAccountSubject();
	}
}
