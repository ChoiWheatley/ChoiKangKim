package testGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CreateToolBar extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	TestGUI frame = new TestGUI();
	JFileChooser fileChooser = new JFileChooser();
	JToolBar toolbar = new JToolBar();
	
	ImageIcon openicon = new ImageIcon("testGUI/icon/open.jpg");
	JButton openButton = new JButton(openicon);
	ImageIcon newicon = new ImageIcon("testGUI/icon/new.jpg");
	JButton newButton = new JButton(newicon);
	ImageIcon saveicon = new ImageIcon("testGUI/icon/save.jpg");
	JButton saveButton = new JButton(saveicon);
	ImageIcon savenewicon = new ImageIcon("testGUI/icon/savenew.jpg");
	JButton savenewButton = new JButton(savenewicon);
	ImageIcon javaicon = new ImageIcon("testGUI/icon/java.jpg");
	JButton javaButton = new JButton(javaicon);
	ImageIcon exiticon = new ImageIcon("testGUI/icon/exit.jpg");
	JButton exitButton = new JButton(exiticon);
	
	public CreateToolBar(TestGUI frame) {
		this.frame = frame;
		Container container = frame.getContentPane();
		
		add(openButton);
		add(newButton);
		add(saveButton);
		add(savenewButton);
		add(javaButton);
		add(exitButton);
		
		openButton.setBorderPainted(false);
		newButton.setBorderPainted(false);
		saveButton.setBorderPainted(false);
		savenewButton.setBorderPainted(false);
		javaButton.setBorderPainted(false);
		exitButton.setBorderPainted(false);
		
		
		openButton.addActionListener(this);
		newButton.addActionListener(this);
		saveButton.addActionListener(this);
		savenewButton.addActionListener(this);
		javaButton.addActionListener(this);
		exitButton.addActionListener(this);

		
		toolbar.add(openButton);
		toolbar.add(newButton);
		toolbar.add(saveButton);
		toolbar.add(savenewButton);
		toolbar.add(javaButton);
		toolbar.add(exitButton);
		
		container.add(toolbar,  BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openButton){
			Func func = new Func();
			func.FileOpen(fileChooser);
		}
		else if(e.getSource() == newButton){
			// Func func = new Func();
			// func.FileMake();
		}
		
		else if(e.getSource() == saveButton){
			Func func = new Func();
			func.SaveFile();
		}
		if(e.getSource() == savenewButton){
			Func func = new Func();
			func.SaveName(fileChooser);	
		}
		else if(e.getSource() == javaButton){
			// Func func = new Func();
			// func.JavaMake();
		}
		else if(e.getSource() == exitButton){
			System.exit(1);
		}
	}

}
