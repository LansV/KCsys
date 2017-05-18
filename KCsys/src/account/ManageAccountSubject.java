package account;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class ManageAccountSubject {
	static private JFrame MFrame; 
	static private JTree MTree;
	ManageAccountSubjectData masd=new ManageAccountSubjectData();
	public JTree accountTree(){
		DefaultMutableTreeNode accountClassNode = new DefaultMutableTreeNode("��");
		List<String> classlist=masd.getAccountClass();
		int classlength=classlist.size();
		for(int i=0;i<classlength;i++){
			//�����
			String classid=classlist.get(i).split(":")[0];
			DefaultMutableTreeNode classnode=new DefaultMutableTreeNode(classlist.get(i)+"   -----   ��");
			List<String> firstlist=masd.getFirstSubject(classid);
			int firstlength=firstlist.size();
			if(firstlength!=0){
				//���һ����Ŀ
				for(String first:firstlist){
					String firstid=first.split(":")[0];
					DefaultMutableTreeNode firstnode=new DefaultMutableTreeNode(first+"   -----   һ����Ŀ");
					List<String> secondlist=masd.getSecondSubject(firstid);
					int secondlength=secondlist.size();
					if(secondlength!=0){
						//��Ӷ�����Ŀ
						for(String second:secondlist){
							String secondid=second.split(":")[0];
							DefaultMutableTreeNode secondnode=new DefaultMutableTreeNode(second+"   -----   ������Ŀ");
							List<String> thirdlist=masd.getThirdSubject(secondid);
							int thirdlength=thirdlist.size();
							if(thirdlength!=0){
								//���������Ŀ
								for(String third:thirdlist){
									String thirdid=third.split(":")[0];
									DefaultMutableTreeNode thirdnode=new DefaultMutableTreeNode(third+"   -----   ������Ŀ");
									List<String> fourthlist=masd.getFourthSubject(thirdid);
									int fourthlength=fourthlist.size();
									if(fourthlength!=0){
										//����ļ���Ŀ
										for(String fourth:fourthlist){
											String fourthid=fourth.split(":")[0];
											DefaultMutableTreeNode fourthnode=new DefaultMutableTreeNode(fourth+"    -----   �ļ���Ŀ");
											List<String> fifthlist=masd.getFifthSubject(fourthid);
											int fifthlength=fifthlist.size();
											if(fifthlength!=0){
												for(String fifth:fifthlist){
													DefaultMutableTreeNode fifthnode=new DefaultMutableTreeNode(fifth+"    -----   �弶��Ŀ");
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
		JTree mtree=new JTree(accountClassNode);
		return mtree;
	}
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
		rm.add("����");
		rm.add("�޸�");
		rm.add("ɾ��");
		MTree=accountTree();
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
		jsp.setViewportView(MTree);
		jsp.setBounds(10,10,470,650);
		MFrameC.add(jsp);
		MFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new ManageAccountSubject();
	}
}
