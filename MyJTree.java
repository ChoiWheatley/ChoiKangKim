package testGUI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class MyJTree extends JTree {
	private static final long serialVersionUID = 1L;
	protected JTree tree;

	/*
	 * 어차피 트리안에 있는 컴포넌트들 바뀔 일이 없으니깐 생성시간에 한 번에 다 만들어버리자.
	 */
	public MyJTree() {
		DefaultMutableTreeNode Palete = new DefaultMutableTreeNode("Palete");

		DefaultMutableTreeNode userInterface = new DefaultMutableTreeNode("User Interface");
		DefaultMutableTreeNode button = new DefaultMutableTreeNode("Button");
		DefaultMutableTreeNode label = new DefaultMutableTreeNode("Label");
		DefaultMutableTreeNode textBox = new DefaultMutableTreeNode("Text Box");

		Palete.add(userInterface);

		userInterface.add(button);
		userInterface.add(label);
		userInterface.add(textBox);
		
		tree = new JTree(Palete);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
	}
	public JTree returnJTree(){
		return tree;
	}
}
