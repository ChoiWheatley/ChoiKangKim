package testGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.filechooser.*;

class CreateMenuBar extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	TestGUI frame = new TestGUI();
	JFileChooser fileChooser = new JFileChooser();
	JTextField path = new JTextField();
	JLabel label = new JLabel("");

	JMenuBar menuBar = new JMenuBar();
	JMenu menuFile = new JMenu("열기");
	JMenuItem fileOpen = new JMenuItem("열기");
	JMenuItem fileMake = new JMenuItem("새로 만들기");

	JMenu menuSave = new JMenu("저장");
	JMenuItem saveFile = new JMenuItem("저장");
	JMenuItem saveName = new JMenuItem("다른 이름으로 저장");

	JMenuItem JavaMake = new JMenuItem("java 파일 생성");
	JMenuItem exitBuilder = new JMenuItem("닫기");

	public CreateMenuBar(TestGUI frame) {
		this.frame = frame;
		
		fileOpen.addActionListener(this);
		fileMake.addActionListener(this);
		fileOpen.addActionListener(this);
		saveFile.addActionListener(this);
		saveName.addActionListener(this);
		JavaMake.addActionListener(this);
		exitBuilder.addActionListener(this);

		menuFile.add(fileOpen);
		menuFile.add(fileMake);
		menuSave.add(saveFile);
		menuSave.add(saveName);

		menuBar.add(menuFile);
		menuBar.add(menuSave);
		menuBar.add(JavaMake);
		menuBar.add(exitBuilder);
		
		frame.setJMenuBar(menuBar);
		setVisible(true);
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fileOpen){
			// FileOpne();
			// if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
			// json으로 저장된 파일 불러오기
			//}
		}
		else if(e.getSource() == fileMake){
			// 기존의 componant 값 삭제
		}
		
		else if(e.getSource() == saveFile){
			//if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			//이 창은 안뜨도록?	
			// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
			// json으로 저장
			//}
		}
		if(e.getSource() == saveName){
			//if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인	
			//}
		}
		else if(e.getSource() == JavaMake){
			// javafile 어떻게 만들어여...?
		}
		else if(e.getSource() == exitBuilder){
			System.exit(1);
			// 메소드로
		}
	}
	
}
