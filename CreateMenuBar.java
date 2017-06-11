package testGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.filechooser.*;

import com.sun.org.apache.xpath.internal.functions.Function2Args;

import testGUI.Func.Function;

class CreateMenuBar extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	TestGUI frame;
	JFileChooser fileChooser = new JFileChooser();
	
	JMenuBar menuBar = new JMenuBar();
	JMenuItem fileOpen = new JMenuItem("열기");
	JMenuItem fileMake = new JMenuItem("새로 만들기");
	JMenuItem saveFile = new JMenuItem("저장");
	JMenuItem saveName = new JMenuItem("다른 이름으로 저장");
	JMenuItem javaMake = new JMenuItem("java 파일 생성");
	JMenuItem exitBuilder = new JMenuItem("닫기");

	public CreateMenuBar(TestGUI frame) {
		this.frame = frame;
		
		fileOpen.setMaximumSize(fileOpen.getPreferredSize());
		fileMake.setMaximumSize(fileMake.getPreferredSize());
		saveFile.setMaximumSize(saveFile.getPreferredSize());
		saveName.setMaximumSize(saveName.getPreferredSize());
		javaMake.setMaximumSize(javaMake.getPreferredSize());
		exitBuilder.setMaximumSize(exitBuilder.getPreferredSize());
		
		fileOpen.addActionListener(this);
		fileMake.addActionListener(this);
		saveFile.addActionListener(this);
		saveName.addActionListener(this);
		javaMake.addActionListener(this);
		exitBuilder.addActionListener(this);

		menuBar.add(fileOpen);
		menuBar.add(fileMake);
		menuBar.add(saveFile);
		menuBar.add(saveName);
		menuBar.add(javaMake);
		menuBar.add(exitBuilder);
		
		frame.setJMenuBar(menuBar);
		setVisible(true);
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fileOpen){
			Func func = new Func();
			Func.Function func2 = func.new Function();
			func2.FileOpen(frame, fileChooser);			
		}
		else if(e.getSource() == fileMake){
			Func func = new Func();
			Func.Function func2 = func.new Function();
			func2.FileMake(frame, fileChooser);
		}
		
		else if(e.getSource() == saveFile){
			Func func = new Func();
			Func.Function func2 = func.new Function();
			func2.SaveFile(frame, fileChooser);			
		}
		else if(e.getSource() == saveName){
			Func func = new Func();
			Func.Function func2 = func.new Function();
			func2.SaveName(frame, fileChooser);			
		}
		else if(e.getSource() == javaMake){
			Func func = new Func();
			Func.Function func2 = func.new Function();
			//func2.JavaMake(frame, fileChooser);			
		}
		else if(e.getSource() == exitBuilder){
			System.exit(1);
		}
	}
}
