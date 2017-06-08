package testGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.xml.internal.ws.api.Component;

import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.JToolBar;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

public class TestGUI extends JFrame {
	private ComponentStruct nodeList = null;
	private DefaultMutableTreeNode whatNode = null;
	private Object nodeInfo = null;
	private boolean addComponentMode = false;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField startXValue;
	private JTextField startYValue;
	private JTextField xLengthValue;
	private JTextField yLengthValue;
	private JTextField nameValue;
	private JTextField rValue;
	private JTextField gValue;
	private JTextField bValue;
	private JTextField compTextAttrValue;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
					CreateMenuBar menubar = new CreateMenuBar(frame);
					CreateToolBar toolbar = new CreateToolBar(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public TestGUI() {
		nodeList = new ComponentStruct();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("GUI Builder");
		setBounds(100, 100, 980, 640);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(5);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setContinuousLayout(true);
		splitPane_1.setResizeWeight(0.386);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);

		/*
		 * Component Pane
		 * 
		 * JTreeMouse.java 파일 사용안함. 원래는 별도의 JTreeMouse 클래스를 가져와서
		 * addMouseListener 의 인자로 할 생각이었으나, 예상외로 마우스 커서 바뀐 게 JTree 컴포넌트 안에서만
		 * 유지되고 그 밖(Editor Pane)으로 가니까 자동으로 default 커서로 바뀌더라. 그래서 그냥 익명 클래스로 작성해
		 * 보기로 했다.
		 * 
		 * Editor Pane은 원래 TextArea 라서 자동으로 텍스트를 위한 커서로 바뀐 거고 나머지 패널에는 잘 바뀐다.
		 * 어쨌든 성공!
		 * 
		 * 이제는 TextArea를 다른 걸로 바꿔야지.
		 * 
		 * Component Pane
		 */
		JTree tree = (new MyJTree().returnJTree());
		tree.setBorder(BorderFactory.createTitledBorder("Components"));
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				// Double Clicked
				if (arg0.getClickCount() == 2) {
					whatNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					nodeInfo = whatNode.getUserObject();
					// String 캐스트 해서 쓰숑
					System.out.println((String) nodeInfo);
					if ((String) nodeInfo != "User Interface" && (String) nodeInfo != "Palete") {
						setCursor(Cursor.CROSSHAIR_CURSOR);
						addComponentMode = true;
					}
				}
			}
		});
		tree.addKeyListener(new KeyAdapter() {
			/*
			 * esc가 눌리면(아 물론 addComponentMode 가 true일 때여야지) 커서가 다시 일반 커서로 올아오게
			 */
			public void keyPressed(KeyEvent e) {
				if (addComponentMode && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.out.println("exit component mode");
					addComponentMode = false;
					setCursor(Cursor.DEFAULT_CURSOR);
				}
			}
		});
		splitPane_1.setLeftComponent(tree);

		JPanel AttributePane = new JPanel();
		splitPane_1.setRightComponent(AttributePane);
		AttributePane.setLayout(new GridLayout(8, 2));

		JPanel startXPane = new JPanel();
		startXPane.setBackground(new Color(224, 255, 255));
		AttributePane.add(startXPane);
		startXPane.setLayout(new GridLayout(1, 2));

		JLabel startX = new JLabel("startX");
		startXPane.add(startX);

		JPanel textStartX = new JPanel();
		startXPane.add(textStartX);
		textStartX.setBackground(Color.WHITE);
		textStartX.setForeground(Color.WHITE);
		textStartX.setLayout(new GridLayout(1, 2));

		startXValue = new JTextField();
		textStartX.add(startXValue);
		startXValue.setText("x");
		startXValue.setColumns(10);

		JPanel startYPane = new JPanel();
		startYPane.setBackground(new Color(127, 255, 212));
		AttributePane.add(startYPane);
		startYPane.setLayout(new GridLayout(1, 2));

		JLabel startY = new JLabel("startY");
		startYPane.add(startY);

		JPanel textStartY = new JPanel();
		textStartY.setForeground(Color.WHITE);
		textStartY.setBackground(Color.WHITE);
		startYPane.add(textStartY);
		textStartY.setLayout(new GridLayout(1, 2));

		startYValue = new JTextField();
		startYValue.setText("x");
		startYValue.setColumns(10);
		textStartY.add(startYValue);

		JPanel xLengthPane = new JPanel();
		xLengthPane.setBackground(new Color(224, 255, 255));
		AttributePane.add(xLengthPane);
		xLengthPane.setLayout(new GridLayout(1, 2));

		JLabel xLength = new JLabel("xLength");
		xLengthPane.add(xLength);

		xLengthValue = new JTextField();
		xLengthPane.add(xLengthValue);
		xLengthValue.setColumns(1);

		JPanel yLengthPane = new JPanel();
		yLengthPane.setBackground(new Color(127, 255, 212));
		AttributePane.add(yLengthPane);
		yLengthPane.setLayout(new GridLayout(1, 2));

		JLabel yLength = new JLabel("yLength");
		yLengthPane.add(yLength);

		yLengthValue = new JTextField();
		yLengthPane.add(yLengthValue);
		yLengthValue.setText("x");
		yLengthValue.setColumns(10);

		JPanel compTypePane = new JPanel();
		compTypePane.setBackground(new Color(224, 255, 255));
		AttributePane.add(compTypePane);
		compTypePane.setLayout(new GridLayout(1, 2));

		JLabel compType = new JLabel("compType");
		compType.setBackground(new Color(127, 255, 212));
		compTypePane.add(compType);

		JComboBox compTypeValue = new JComboBox();
		compTypeValue.setBackground(new Color(248, 248, 255));
		compTypeValue.setModel(new DefaultComboBoxModel(
				new String[] { "COMP_BUTTON", "COMP_CHECK_BOX", "COMP_LABEL", "COMP_TEXT_BOX", "COMP_COMBO_BOX" }));
		compTypePane.add(compTypeValue);

		JPanel namePane = new JPanel();
		namePane.setBackground(new Color(127, 255, 212));
		AttributePane.add(namePane);
		namePane.setLayout(new GridLayout(1, 2));

		JLabel name = new JLabel("name");
		namePane.add(name);

		nameValue = new JTextField();
		nameValue.setText("x");
		nameValue.setColumns(10);
		namePane.add(nameValue);

		JPanel compColorPane = new JPanel();
		compColorPane.setBackground(new Color(224, 255, 255));
		AttributePane.add(compColorPane);
		compColorPane.setLayout(new GridLayout(1, 2));

		JLabel compColor = new JLabel("compColor");
		compColorPane.add(compColor);

		rValue = new JTextField();
		rValue.setText("r");
		rValue.setColumns(10);
		compColorPane.add(rValue);

		gValue = new JTextField();
		gValue.setText("g");
		compColorPane.add(gValue);
		gValue.setColumns(10);

		bValue = new JTextField();
		bValue.setText("b");
		compColorPane.add(bValue);
		bValue.setColumns(10);

		JPanel compTextAttributePane = new JPanel();
		compTextAttributePane.setBackground(new Color(127, 255, 212));
		AttributePane.add(compTextAttributePane);
		compTextAttributePane.setLayout(new GridLayout(1, 2));

		JLabel compTextAttr = new JLabel("compTextAttr");
		compTextAttributePane.add(compTextAttr);

		compTextAttrValue = new JTextField();
		compTextAttributePane.add(compTextAttrValue);
		compTextAttrValue.setColumns(10);

		/*
		 * 에디터 패인은 addComponentMode가 true일 때에만 마우스 드래그를 허용, 드래그시 rectangular 가
		 * 생성 되어 가시적으로 만들어질 컴포넌트의 크기에 대한 정보를 제공한다. 그리고, 드래그를 놓았을 때 1. 드래그 시작위치
		 * 2. rectangular 크기 3. 무슨 컴포넌트인지 를 새로 만들어진 rectangular에다가 전달한다.
		 */
		EditPane editPane = new EditPane();
		editPane.setBorder(BorderFactory.createTitledBorder("Edit Pane"));
		splitPane.setRightComponent(editPane);

		editPane.setLayout(null);
		JScrollBar scrollBar = new JScrollBar();
		tree.add(scrollBar, BorderLayout.EAST);

	} // End of main function

	@SuppressWarnings("deprecation")
	public void setCursor(int offset) {
		super.setCursor(offset);
	}

	/*
	 * 내부 클래스 EditPane는 EditPane 안에서 addComponentMode가 활성화 되어있다면 드래그를 통해 새로운
	 * 컴포넌트를 끌어서(또는 그냥 클릭해서) 가시적으로 새로운 컴포넌트를 생성했다는 표현을 제공한다.
	 */
	private class EditPane extends JPanel {
		private static final long serialVersionUID = 1L;
		// 그냥 클릭시 생성될 기본 컴포넌트의 크기
		private final static int DEFAULT_X = 70;
		private final static int DEFAULT_Y = 50;
		Point startPoint, lastPoint;
		boolean highlight = false;

		public EditPane() {
			MouseHandler handle = new MouseHandler();
			this.addMouseListener(handle);
			this.addMouseMotionListener(handle);
		}

		/*
		 * 이제 Vector를 버리고 내가 만든 nodeList를 활용을 해보자. 사실 이전에는 걍 시작포인트랑 끝포인트만 필요했지만
		 * 지금은 색까지 넣어야 하느라 아예 내용을 통째로 담고있는 링크드 리스트 로 옮겨야 한다.
		 */
		public void paintComponent(Graphics g) {
			// 부모의 paintComponent를 호출 (in JPanel)
			super.paintComponent(g);

			if (nodeList.getSize() != 0) {
				for (int i = 0; i < nodeList.getSize(); i++) {
					CompNode cmp = nodeList.get(i);
					int startX = cmp.startX;
					int startY = cmp.startY;
					g.setColor(cmp.compColor);
					g.fillRect(startX, startY, cmp.xLength, cmp.yLength);
				}
			}
			// drawRect로 드래그시 사각형이 그려지는 효과
			if (addComponentMode) {
				g.setColor(Color.BLACK);
				g.drawRect(startPoint.x, startPoint.y, lastPoint.x - startPoint.x, lastPoint.y - startPoint.y);
			}
			//highlight 효과를 컴포넌트에게 전달하는 부분
			if (highlight){
				CompNode topNode = nodeList.get(nodeList.getSize()-1);
				g.setColor(Color.RED);
				g.drawRect(topNode.startX-2, topNode.startY-2, topNode.xLength+3, topNode.yLength+3);
			}
		}

		// 드래그 초기설정
		private class MouseHandler implements MouseListener, MouseMotionListener {
			CompNode newNode;
			Rectangle tempBox = null;
			int tempStartX, tempStartY;
			int tempLengthX, tempLengthY;
			int top = -1;
			int offX, offY;

			public void mousePressed(MouseEvent e) {
				if (addComponentMode) {
					startPoint = e.getPoint();
				} else {
					/*
					 * 클릭했을 때 겹치는 컴포넌트 중에서 가장 위에 올라와 있는 놈을 호출한다 제일 먼저 각 노드들의
					 * startPoint와 lastPoint를 가져와 rectangle을 하나 만든 다음,
					 * contains(int x, int y, int width, int height) 메소드 를 사용하여
					 * 클릭여부를 확인한다.
					 */
					if (nodeList.getSize() != 0) {
						top = -1;
						for (int i = 0; i < nodeList.getSize(); i++) {
							CompNode cmp = nodeList.get(i);
							tempStartX = cmp.startX;
							tempStartY = cmp.startY;
							tempLengthX = cmp.xLength;
							tempLengthY = cmp.yLength;

							tempBox = new Rectangle(tempStartX, tempStartY, tempLengthX, tempLengthY);
							if (tempBox.contains(e.getX(), e.getY())) {
								top = i;
							}

						} // end of for-loop for checking the biggest
							// node which contains mouse axis

						if (top >= 0) {
							System.out.println("You have selected " + nodeList.get(top).compType);
							CompNode topNode = nodeList.get(top);
							offX = e.getX() - topNode.startX;
							offY = e.getY() - topNode.startY;
							/*
							 * 그리고, 드래그된 컴포넌트는 노드의 제일 앞으로 오게 설정하고 싶다.
							 */
							nodeList.moveToLast(top);
							top = nodeList.getSize() - 1;
							highlight = true;
							/*
							 * 앞으로 나온 컴포넌트 가 활성화되었다는 것을 보여주기 위해 상자를 하나 더 그려주자.
							 * 어차피 이놈은 하나만 그리면 된다.
							 * 
							 */
						} // end of if, when top is bigger than zero.
					} // end of if, when nodeList's size is not zero
					repaint();
				} // end of else, when addComponentMode is false
			} // end of mousePressed()

			public void mouseDragged(MouseEvent e) {
				if (addComponentMode) {
					lastPoint = e.getPoint();
				} else {
					/*
					 * 만약에 top(현재 클릭한 컴포넌트 중 가장 높이 있는놈) 가 0이상, 즉 마우스가 클릭이 된 상태에서
					 * 잡힌 컴포넌트가 있다면 드래그시 따라 움직이도록 만들어야 한다.
					 */
					if (top >= 0) {
						CompNode topNode = nodeList.get(top);
						topNode.startX = e.getX() - offX;
						topNode.startY = e.getY() - offY;
					}
				}
				repaint();
			}

			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent e) {
				Random colorRandom = new Random();
				Color randColor = new Color(colorRandom.nextInt(255), colorRandom.nextInt(255),
						colorRandom.nextInt(255));
				if (addComponentMode) {
					lastPoint = e.getPoint();
					// 단순 클릭일 경우, default size를 더해주어 컴포넌트를 만든다.
					if (startPoint.x == lastPoint.x && startPoint.y == lastPoint.y) {
						startPoint = e.getPoint();
						lastPoint.setLocation(startPoint.getX() + DEFAULT_X, startPoint.getY() + DEFAULT_Y);
					} else {
						lastPoint = e.getPoint();
					}
					TestGUI.super.setCursor(Cursor.DEFAULT_CURSOR);
					repaint();

					/*
					 * 현 컴포넌트를 얻어오고(저기 위에 nodeInfo 라는 놈에게 심어놓았다.)
					 * ComponentStruct 링크드 리스트에다가 쑤셔넣는다.
					 */
					newNode = new CompNode();
					newNode.startX = startPoint.x;
					newNode.startY = startPoint.y;
					newNode.xLength = lastPoint.x - startPoint.x;
					newNode.yLength = lastPoint.y - startPoint.y;
					newNode.name = null;
					newNode.compTextAttr = null;
					newNode.compColor = randColor;

					switch ((String) nodeInfo) {
					case ("Button"):
						System.out.println("hi Button");
						newNode.compType = ComponentStruct.COMP_BUTTON;
						break;
					case ("Check Box"):
						System.out.println("hi Check Box");
						newNode.compType = ComponentStruct.COMP_CHECK_BOX;
						break;
					case ("Label"):
						System.out.println("hi Label");
						newNode.compType = ComponentStruct.COMP_LABEL;
						break;
					case ("Text Box"):
						System.out.println("hi Text Box");
						newNode.compType = ComponentStruct.COMP_TEXT_BOX;
						break;
					case ("Combo Box"):
						System.out.println("hi Combo Box");
						newNode.compType = ComponentStruct.COMP_COMBO_BOX;
						break;

					} // end of switch

					// Linked List에다가 집어넣기
					nodeList.add(newNode);
					System.out.println("size : " + nodeList.getSize());

				} // end of addComponentMode checking

				// 이제 다시 addComponentMode를 원래대로 돌려놓고 마우스를 default 모양으로 바꿔야지
				addComponentMode = false;

				// top 를 -1로 다시 내려놓아야
				top = -1;

				// startPoint, lastPoint 초기화
				startPoint = null;
				lastPoint = null;
				
				//highlight 초기화
				highlight = false;

			} // end of mouseReleased method

			public void mouseMoved(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		} // end of inner class MouseHandler

	} // end of Class EditPane
} // end of public class TestGUI
