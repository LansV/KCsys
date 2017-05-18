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
		DefaultMutableTreeNode accountClassNode = new DefaultMutableTreeNode("类");
		List<String> classlist=masd.getAccountClass();
		int classlength=classlist.size();
		for(int i=0;i<classlength;i++){
			//添加类
			String classid=classlist.get(i).split(":")[0];
			DefaultMutableTreeNode classnode=new DefaultMutableTreeNode(classlist.get(i)+"   -----   类");
			List<String> firstlist=masd.getFirstSubject(classid);
			int firstlength=firstlist.size();
			if(firstlength!=0){
				//添加一级科目
				for(String first:firstlist){
					String firstid=first.split(":")[0];
					DefaultMutableTreeNode firstnode=new DefaultMutableTreeNode(first+"   -----   一级科目");
					List<String> secondlist=masd.getSecondSubject(firstid);
					int secondlength=secondlist.size();
					if(secondlength!=0){
						//添加二级科目
						for(String second:secondlist){
							String secondid=second.split(":")[0];
							DefaultMutableTreeNode secondnode=new DefaultMutableTreeNode(second+"   -----   二级科目");
							List<String> thirdlist=masd.getThirdSubject(secondid);
							int thirdlength=thirdlist.size();
							if(thirdlength!=0){
								//添加三级科目
								for(String third:thirdlist){
									String thirdid=third.split(":")[0];
									DefaultMutableTreeNode thirdnode=new DefaultMutableTreeNode(third+"   -----   三级科目");
									List<String> fourthlist=masd.getFourthSubject(thirdid);
									int fourthlength=fourthlist.size();
									if(fourthlength!=0){
										//添加四级科目
										for(String fourth:fourthlist){
											String fourthid=fourth.split(":")[0];
											DefaultMutableTreeNode fourthnode=new DefaultMutableTreeNode(fourth+"    -----   四级科目");
											List<String> fifthlist=masd.getFifthSubject(fourthid);
											int fifthlength=fifthlist.size();
											if(fifthlength!=0){
												for(String fifth:fifthlist){
													DefaultMutableTreeNode fifthnode=new DefaultMutableTreeNode(fifth+"    -----   五级科目");
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
		MFrame.setTitle("科目管理");
		Container MFrameC=MFrame.getContentPane();
		MFrameC.setLayout(null);
		MFrame.setResizable(false);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsp=new JScrollPane();
		final JPopupMenu rm=new JPopupMenu();
		rm.add("增加");
		rm.add("修改");
		rm.add("删除");
		MTree=accountTree();
		MTree.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
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
