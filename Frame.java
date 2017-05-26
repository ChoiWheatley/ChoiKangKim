import java.awt.BorderLayout;
import java.awt.Image;

//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
import javax.swing.*;


public class Frame extends JFrame {
	
	private static final long serialVersionUID =- 711163588504124217L;
	
    public Frame() {

        
        setTitle("Simple GUI Builder"); 
        setSize(1500, 1000);
        
        MenuBar();
        ToolBar();
        getContentPane().add(new AttributePane().getContent()); // Attribute Pane
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    //class ���������
    private void MenuBar() {

    	JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("열기");
		JMenuItem fileOpen = new JMenuItem("열기");
		JMenuItem fileMake = new JMenuItem("새로 만들기");

		JMenu menuSave = new JMenu("저장");
		JMenuItem saveFile = new JMenuItem("저장");
		JMenuItem saveName = new JMenuItem("다른 이름으로 저장");

		JMenu JavaMake = new JMenu("java 파일 생성");
		
		JMenu exitBuilder = new JMenu("닫기");
		
		menuFile.add(fileOpen);
		menuFile.add(fileMake);

		menuSave.add(saveFile);
		menuSave.add(saveName);

		menuBar.add(menuFile);
		menuBar.add(menuSave);
		menuBar.add(JavaMake);
		menuBar.add(Box.createHorizontalGlue()); // �ݱ⸦ �� ����������
		menuBar.add(exitBuilder);
    }
    
    // class ���������
    private void ToolBar() {
    	
        JToolBar toolbar = new JToolBar();

        ImageIcon originIcon1 = new ImageIcon("src/new.png");
        
        //�̹��� ũ�⸦ ���߱� ����
        Image originImg1 = originIcon1.getImage();
        Image changedImg1 = originImg1.getScaledInstance(10, 10, 10);
        ImageIcon icon1 = new ImageIcon(changedImg1);
        JButton newButton = new JButton(icon1);
        toolbar.add(newButton);
        
        ImageIcon originIcon2 = new ImageIcon("src/open.png");
        Image originImg2 = originIcon2.getImage();
        Image changedImg2 = originImg2.getScaledInstance(10, 10, 10);
        ImageIcon icon2 = new ImageIcon(changedImg2);
        JButton openButton = new JButton(icon2);
        toolbar.add(openButton);

        ImageIcon originIcon3 = new ImageIcon("src/save.png");
        Image originImg3 = originIcon3.getImage();
        Image changedImg3 = originImg3.getScaledInstance(10, 10, 10);
        ImageIcon icon3 = new ImageIcon(changedImg3);
        JButton saveButton = new JButton(icon3);
        toolbar.add(saveButton);
        
        ImageIcon originIcon4 = new ImageIcon("src/savenew.png");
        Image originImg4 = originIcon4.getImage();
        Image changedImg4 = originImg4.getScaledInstance(10, 10, 10);
        ImageIcon icon4 = new ImageIcon(changedImg4);
        JButton savenewButton = new JButton(icon4);
        toolbar.add(savenewButton);
        
        ImageIcon originIcon5 = new ImageIcon("src/java.png");
        Image originImg5 = originIcon5.getImage();
        Image changedImg5 = originImg5.getScaledInstance(10, 10, 10);
        ImageIcon icon5 = new ImageIcon(changedImg5);
        JButton javaButton = new JButton(icon5);
        toolbar.add(javaButton);
        
        ImageIcon originIcon6 = new ImageIcon("src/exit.png");
        Image originImg6 = originIcon6.getImage();
        Image changedImg6 = originImg6.getScaledInstance(10, 10, 10);
        ImageIcon icon6 = new ImageIcon(changedImg6);
        JButton exitButton = new JButton(icon6);
        toolbar.add(exitButton);
        
        //toolbar.addSeparator(); �� �ִ� �Լ����� �ϴµ� ���� �� �𸣰���

        add(toolbar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
    	
    	new Frame();
       /* EventQueue.invokeLater(() -> {
            ToolbarEX ex = new ToolbarEX();
            ex.setVisible(true);
        });
        */
    }
}