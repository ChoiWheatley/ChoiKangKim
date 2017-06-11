package testGUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/*
 * 짜피 공통된 부분의 Attribute만 제공할 나쁜 프로그래머니까 컴포넌트별로 다른 정보를 제공하는건 건너뛰자. (만약 없는
 * 놈(compText)의 경우는 그냥 null로 띄우지 뭐 희희)
 * 
 * 컴포넌트 이름 + 좌표(시작좌표, 끝좌표), x축 길이, y축 길이, 컴포넌트타입, 컴포넌트 텍스트( 띄워진 컴포넌트상에 표시될 이름)
 */
public class ComponentStruct {
	// Constants
	public final static int COMP_BUTTON = 1;
	public final static int COMP_CHECK_BOX = 2;
	public final static int COMP_LABEL = 3;
	public final static int COMP_TEXT_BOX = 4;
	public final static int COMP_COMBO_BOX = 5;

	private CompNode head;
	private CompNode tail;
	private int size;

	private static final CompNode dummyCompNode = null;

	// constructor
	public ComponentStruct() {
		head = null;
		tail = null;
		size = 0;
	}

	/*
	 * 넣을 때 순서는 상관없다.
	 */
	public void add(CompNode newCompNode) {
		if (size == 0) {
			head = newCompNode;
			tail = newCompNode;
			head.link = tail;
			tail.link = dummyCompNode;
		} else {
			tail.link = newCompNode;
			tail = tail.link;
			tail.link = dummyCompNode;
		}
		size++;
	} // end of add

	/*
	 * 제거할 때에는 뺄 노드의 인덱를 명시, 인자로 들어온 노드가 있는지 확인한 뒤에 있으면 제거하고 true를 리턴, 없으면 그대로
	 * false 리턴.
	 */
	public boolean sub(int index) {
		if (size == 0) {
			System.out.println("Empty");
			return false;
		}
		if (index > this.size) {
			System.out.println("Index Out Of Boundary");
			return false;
		}

		if (index != 0) {
			CompNode prevCompNode = this.get(index - 1);
			CompNode tempCompNode = this.get(index);

			prevCompNode.link = tempCompNode.link;
			tempCompNode = null; // 이제 참조할 수 있는 수단이 없으므로 JVM이 알아서 처리해주겠지?

		} else {
			// index가 0이라서 head를 다시 지정해줘야한다.
			CompNode headNode = this.head;
			this.head = headNode.link;
			headNode = null;
		}
		size--;

		return true;

	} // end of sub

	public boolean isEmpty() {
		return (size == 0);
	} // end of isEmpty()

	public int getSize() {
		return size;
	} // end of getSize()

	public CompNode get(int index) {
		CompNode tempNode = head;
		if (index >= size) {
			System.out.println("out of boundary");
			return null;
		}
		for (int i = 0; i < index; i++) {
			tempNode = tempNode.link;
		}
		return tempNode;
	} // end of get()

	/*
	 * moveToLast 메소드는 컴포넌트를 맨 위로 올리고 싶을 때 그 인덱스의 번호를 인자로 함수를 호출하면 알아서 가장 마지막
	 * 노드로 이동시켜준다.
	 */
	// 1. 인덱스-1의 노드의 link를 인덱스+1로 연결한다.
	// 2. 인덱스(였던)의 노드를 tail의 link로 연결한다.
	// 3. 2의 노드의 link를 dummyNode로 연결한다.
	public void moveToLast(int index) {
		// 인덱스가 tail이면...
		if (index == this.size - 1) {
			return;
		}
		if (index == 0) {
			CompNode headNode = this.head;
			this.head = headNode.link;
			this.tail.link = headNode;
			this.tail = this.tail.link;
			this.tail.link = dummyCompNode;
			return;
		}
		CompNode prevNode = this.get(index - 1);
		CompNode tempNode = prevNode.link;
		prevNode.link = tempNode.link;
		this.tail.link = tempNode;
		this.tail = this.tail.link;
		this.tail.link = dummyCompNode;
	}

} // end of constructor ComponentStruct()

// Each components are stored in
// this class.
class CompNode {
	int startX;
	int startY;
	int xLength;
	int yLength;
	int compType;
	String name;
	String compTextAttr;
	Color compColor;

	public CompNode link;

} // end of CompNode class

// end of ComponentStruct