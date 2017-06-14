package test;


import java.awt.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
public class Test extends JFrame {
	private static final long serialVersionUID=20110125L;
	public Test() {
		Container container=this.getContentPane();
		
		this.setBounds(200, 200, 200, 500);
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("类");
		DefaultMutableTreeNode nodeFirst=new DefaultMutableTreeNode("一级科目");
		root.add(nodeFirst);
		DefaultMutableTreeNode nodeSecond=new DefaultMutableTreeNode("一级科目");
		root.add(nodeSecond);
		DefaultMutableTreeNode nodeFirstA=new DefaultMutableTreeNode("二级科目");
		nodeFirstA.add(new DefaultMutableTreeNode("三级科目"));
		nodeFirst.add(nodeFirstA);
		DefaultMutableTreeNode nodeFirstB=new DefaultMutableTreeNode("二级科目");
		nodeFirst.add(nodeFirstB);
		JTree tree=new JTree(root);
		
		container.add(tree);
		this.setVisible(true);
		tree.expandPath(new TreePath(nodeFirstA.getPath()));
		System.out.println(new TreePath(nodeFirstA.getPath()));
		//Enumeration<?> enumeration=nodeFirst.preorderEnumeration();
		/*while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode)enumeration.nextElement();
			if (!node.isLeaf()) {
				TreePath path=new TreePath(node.getPath());
				tree.expandPath(path);
			}
		}*/
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Test();
		String s="2017-6-5";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr=s;  
		try {
			java.util.Date date=sdf.parse(dstr);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 

	}
}

