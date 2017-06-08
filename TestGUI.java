package swing;

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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JLayeredPane;
import javax.swing.JScrollBar;
public class TestGUI extends JFrame {
	private boolean addComponentMode = false;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JSplitPane splitPane_2 = new JSplitPane();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public TestGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("GUI Builder");
		setBounds(100, 100, 980, 640);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
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
			public void mouseClicked(MouseEvent arg0) {
				// Double Clicked
				if (arg0.getClickCount() == 2) {
					System.out.println("Double Clicked");
					setCursor(Cursor.CROSSHAIR_CURSOR);
					addComponentMode = true;
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

		/*
		 * 에디터 패인은 addComponentMode가 true일 때에만 마우스 드래그를 허용, 드래그시 rectangular 가
		 * 생성 되어 가시적으로 만들어질 컴포넌트의 크기에 대한 정보를 제공한다. 그리고, 드래그를 놓았을 때 1. 드래그 시작위치
		 * 2. rectangular 크기 3. 무슨 컴포넌트인지 를 새로 만들어진 rectangular에다가 전달한다.
		 */
		EditPane editPane = new EditPane();
		editPane.setBorder(BorderFactory.createTitledBorder("Edit Pane"));
		splitPane_2.setLeftComponent(editPane);

		editPane.setLayout(null);
		JScrollBar scrollBar = new JScrollBar();
		tree.add(scrollBar, BorderLayout.EAST);
		splitPane_2.setResizeWeight(0.7);
		splitPane.setRightComponent(splitPane_2);

		JList list = new JList();
		splitPane_2.setRightComponent(list);

	} // End of main function

	public void setCursor(int offset) {
		super.setCursor(offset);
	}

	/*
	 * 내부 클래스 EditPane는 EditPane 안에서 addComponentMode가 활성화 되어있다면 드래그를 통해 새로운
	 * 컴포넌트를 끌어서(또는 그냥 클릭해서) 가시적으로 새로운 컴포넌트를 생성했다는 표현을 제공한다.
	 */
	private class EditPane extends JPanel {
		// 그냥 클릭시 생성될 기본 컴포넌트의 크기
		private final static int DEFAULT_X = 30;
		private final static int DEFAULT_Y = 50;
		Graphics objectComponent;
		Point startPoint, lastPoint;
		
		// box 정보 
		Rectangle box = new Rectangle();
		int offX, offY;
		boolean isDragged;

		public EditPane() {
			MouseHandler handle = new MouseHandler();
			this.addMouseListener(handle);
			this.addMouseMotionListener(handle);
		}

		/*
		 * Vector<Point> 는 한 오브젝트만 넣을 것이 아니기 때문에 미리 저장해 두고 계속해서 컴포넌트들을 받아와 그릴 수
		 * 있게 만들었다.
		 */
		Vector<Point> startPointList = new Vector<Point>();
		Vector<Point> lastPointList = new Vector<Point>();

		public void paintComponent(Graphics g) {
			// 부모의 paintComponent를 호출 (in JPanel)
			super.paintComponent(g);
			g.setColor(Color.BLUE);

			if (startPointList.size() >1) {
				for (int i = 0; i < lastPointList.size(); i++) {
					Point start = startPointList.get(i);
					Point last = lastPointList.get(i);
					g.drawRect(start.x, start.y, last.x - start.x, last.y - start.y);
					//g.drawRect(box.x,box.y,box.width,box.height);
				}
			}
			if (startPoint != null)
if(addComponentMode == true)g.drawRect(startPoint.x, startPoint.y, lastPoint.x - startPoint.x, lastPoint.y - startPoint.y);
else	g.drawRect(box.x,box.y,box.width,box.height);
		}

		// 드래그 초기설정
		private class MouseHandler implements MouseListener, MouseMotionListener {
			
			
             public void mousePressed(MouseEvent e) {
				if (addComponentMode == true) {
					System.out.println("pressed");
					startPoint = e.getPoint();
					box.x = startPoint.x;
					
					box.y = startPoint.y;
					startPointList.add(startPoint);
				}
				else if(box.contains(new Point(e.getX(),e.getY()))){
					//#1 마우스 버튼 누름
					//사각형내 마우스 클릭 상대 좌표를 구함
					//현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
				System.out.println(" 내부박스클릭 ");
					offX = e.getX() - box.x;
					offY = e.getY() - box.y;
				
					//드래그 시작을 표시
					isDragged = true;

				}
			}

			public void mouseDragged(MouseEvent e) {
				if (addComponentMode == true) {
					lastPoint = e.getPoint();
					repaint();
				}
				if(isDragged){
					
					box.x = e.getX() - offX;
					box.y = e.getY() - offY;

				}
				repaint();
			
			}

			public void mouseReleased(MouseEvent e) {
				if (addComponentMode == true) {
					lastPoint = e.getPoint();
				    box.width = lastPoint.x - startPoint.x;
					box.height = lastPoint.y - startPoint.y;
					// 단순 클릭일 경우, default size를 더해주어 컴포넌트를 만든다.
					if (startPoint.x == lastPoint.x && startPoint.y == lastPoint.y) {
						startPoint = e.getPoint();
						lastPoint.setLocation(startPoint.getX() + DEFAULT_X, startPoint.getY() + DEFAULT_Y);
					} else {
						lastPoint = e.getPoint();
					}
					lastPointList.add(lastPoint);
					TestGUI.super.setCursor(Cursor.DEFAULT_CURSOR);
					repaint();
				} // end of addComponentMode checking

				// 이제 다시 addComponentMode를 원래대로 돌려놓고 마우스를 default 모양으로 바꿔야지
				addComponentMode = false;

			} // end of mouseReleased

			public void mouseMoved(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		}

	}
}
