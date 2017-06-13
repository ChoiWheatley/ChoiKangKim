package testGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.*;

public class Func extends JFrame{
	private static final long serialVersionUID = 1L;

	String path = new String();

	public class Function extends JPanel{
		private static final long serialVersionUID = 1L;
		public ComponentStruct nodeList;

		// 파일 열기
		public void FileOpen(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				frame.nodeList = new ComponentStruct(); // nodeList 초기화
				try {
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(new FileReader(file.toString()));
					JSONObject temp = (JSONObject) obj;
					JSONArray jarr = (JSONArray)temp.get("Components"); // jarr에 json에서 저장된 components의 값 저장

					// json에서 읽어온 components값 현재 frame의 components에 넣기
					for (int i = 0; i < jarr.size(); i++) {
						JSONObject tmp = (JSONObject)jarr.get(i);

						// 왜 저장이 Long형으로 되는지는 모르겠음...ㅂㄷㅂㄷ
						Long tmpX = Long.parseLong((String) tmp.get("startX"));
						Long tmpY = Long.parseLong((String) tmp.get("startY"));
						Long tmpXL = Long.parseLong((String) tmp.get("xLength"));
						Long tmpTL = Long.parseLong((String) tmp.get("yLength"));
						Long tmpCT = Long.parseLong((String) tmp.get("compType"));
						Long tmpCR = Long.parseLong((String) tmp.get("compColorR"));
						Long tmpCG = Long.parseLong((String) tmp.get("compColorG"));
						Long tmpCB = Long.parseLong((String) tmp.get("compColorB"));

						CompNode tempnode = new CompNode();
						tempnode.startX = tmpX.intValue();
						tempnode.startY = tmpY.intValue();
						tempnode.xLength = tmpXL.intValue();
						tempnode.yLength = tmpTL.intValue();
						tempnode.compType = tmpCT.intValue();
						tempnode.name = (String) tmp.get("name");
						tempnode.compTextAttr = (String) tmp.get("CompTextAttr");
						tempnode.compColor = new Color(tmpCR.intValue(), tmpCG.intValue(), tmpCB.intValue());
						frame.nodeList.add(tempnode);
					}
					//클릭 한번 해야 repaint()가 호출되는데 이거 어떻게 고쳐...?
					repaint();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		// 새로 만들기
		public void FileMake(TestGUI frame, JFileChooser fileChooser) {
			// 현재 창을 닫고 새로운 창을 만들자
			frame.dispose();
			TestGUI frame2 = new TestGUI();
			frame2.main(null);
		}

		// 저장
		public void SaveFile(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				this.nodeList = frame.nodeList;
				JSONArray jarr = new JSONArray();
				JSONObject list = new JSONObject(); // list에 모든 components값 저장
				// 형식은 {"Components" : [{component1}], [{component2}],...}]}

				for (int i = 0; i < nodeList.getSize(); i++) {
					if (frame.nodeList.get(i).name !=null) {
						JSONObject jobj = new JSONObject();
						jobj.put("startX", Long.toString(nodeList.get(i).startX));
						jobj.put("startY", Long.toString(nodeList.get(i).startY));
						jobj.put("xLength", Long.toString(nodeList.get(i).xLength));
						jobj.put("yLength", Long.toString(nodeList.get(i).yLength));
						jobj.put("compType", Long.toString(nodeList.get(i).compType));
						jobj.put("name", nodeList.get(i).name);
						jobj.put("compTextAttr", nodeList.get(i).compTextAttr);
						// color 저장하려고 R, G, B로 나눴음
						jobj.put("compColorR", Long.toString(nodeList.get(i).compColor.getRed()));
						jobj.put("compColorG", Long.toString(nodeList.get(i).compColor.getGreen()));
						jobj.put("compColorB", Long.toString(nodeList.get(i).compColor.getBlue()));

						jarr.add(jobj);
					}
					else {
						JOptionPane.showMessageDialog(null, "There is some components named 'null'");
						return;
					}	
				}

				list.put("Components", jarr);
				//System.out.println(list); 잘 저장되는지 확인하기 위해서 넣은거얌

				try {
					// file에 쓰는 작업
					String path = fileChooser.getSelectedFile().getPath();
					FileWriter filewriter = new FileWriter(path); 
					filewriter.write(list.toJSONString());
					filewriter.flush();
					filewriter.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 다른 이름으로 저장(저장이랑 비슷함)
		public void SaveName(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				this.nodeList = frame.nodeList;
				JSONArray jarr = new JSONArray();
				JSONObject list = new JSONObject(); // list에 모든 components값 저장
				// 형식은 {"Components" : [{component1}], [{component2}],...}]}

				for (int i = 0; i < nodeList.getSize(); i++) {
					if (frame.nodeList.get(i).name !=null) {
						JSONObject jobj = new JSONObject();
						jobj.put("startX", Long.toString(nodeList.get(i).startX));
						jobj.put("startY", Long.toString(nodeList.get(i).startY));
						jobj.put("xLength", Long.toString(nodeList.get(i).xLength));
						jobj.put("yLength", Long.toString(nodeList.get(i).yLength));
						jobj.put("compType", Long.toString(nodeList.get(i).compType));
						jobj.put("name", nodeList.get(i).name);
						jobj.put("compTextAttr", nodeList.get(i).compTextAttr);
						// color 저장하려고 R, G, B로 나눴음
						jobj.put("compColorR", Long.toString(nodeList.get(i).compColor.getRed()));
						jobj.put("compColorG", Long.toString(nodeList.get(i).compColor.getGreen()));
						jobj.put("compColorB", Long.toString(nodeList.get(i).compColor.getBlue()));

						jarr.add(jobj);
					}
					else {
						JOptionPane.showMessageDialog(null, "There is some components named 'null'");
						return;
					}	
				}

				list.put("Components", jarr);
				//System.out.println(list); 잘 저장되는지 확인하기 위해서 넣은거얌

				try {
					// file에 쓰는 작업
					String path = fileChooser.getSelectedFile().getPath();
					FileWriter filewriter = new FileWriter(path); 
					filewriter.write(list.toJSONString());
					filewriter.flush();
					filewriter.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// java 파일 생성(워드패드로 확인해보면 형식 잘맞춘듯? 약간 뿌듯
		public void JavaMake(TestGUI frame, JFileChooser fileChooser) {
			try {
				File file = new File("MakeGUI.java");
				FileWriter fileWriter = new FileWriter(file); 
				fileWriter.write("import javax.swing.*;\n");
				fileWriter.write("import java.awt.*;\n");
				fileWriter.write("public class MakeGUI extends JFrame{\n");
				fileWriter.write("private static final long serialVersionUID = 1L;\n");
				fileWriter.write("public static void main (String[] args) {\n");
				fileWriter.write("MakeGUI gui = new MakeGUI();\n");
				fileWriter.write("\n");
				fileWriter.write("}\n");

				fileWriter.write("public MakeGUI() {\n");
				fileWriter.write("JFrame frame = new JFrame();\n");
				fileWriter.write("frame.setLayout(null);\n");

				for (int i = 0; i < frame.nodeList.getSize(); i++) {
					// component의 이름이 null이면 저장 안하고 오류메세지 띄움(try catch 문으로 하고싶었는데 못함)
					if (frame.nodeList.get(i).name !=null) {
						switch(frame.nodeList.get(i).compType) {
						case 1 : // button
							fileWriter.write("JButton " + frame.nodeList.get(i).name + " = new JButton(\"" + frame.nodeList.get(i).compTextAttr + "\");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setSize(" + frame.nodeList.get(i).xLength + "," + frame.nodeList.get(i).yLength + ");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setLocation(" + frame.nodeList.get(i).startX + "," + frame.nodeList.get(i).startY + ");\n");
							fileWriter.write("Color colorB = new Color("+ frame.nodeList.get(i).compColor.getRed() + ", "
									+ frame.nodeList.get(i).compColor.getGreen() + ", " + frame.nodeList.get(i).compColor.getBlue() + ");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setBackground(colorB);\n");
							fileWriter.flush();
							break;
						case 2 : // label
							fileWriter.write("JLabel " + frame.nodeList.get(i).name + " = new JLabel(\"" + frame.nodeList.get(i).compTextAttr + "\");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setSize(" + frame.nodeList.get(i).xLength + "," + frame.nodeList.get(i).yLength + ");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setLocation(" + frame.nodeList.get(i).startX + "," + frame.nodeList.get(i).startY + ");\n");
							fileWriter.flush();
							break;

						case 3 : // text Filed
							fileWriter.write("JTextField " + frame.nodeList.get(i).name + " = new JTextField(\"" + frame.nodeList.get(i).compTextAttr + "\");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setSize(" + frame.nodeList.get(i).xLength + "," + frame.nodeList.get(i).yLength + ");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setLocation(" + frame.nodeList.get(i).startX + "," + frame.nodeList.get(i).startY + ");\n");
							fileWriter.write("Color colorT = new Color("+ frame.nodeList.get(i).compColor.getRed() + ", "
									+ frame.nodeList.get(i).compColor.getGreen() + ", " + frame.nodeList.get(i).compColor.getBlue() + ");\n");
							fileWriter.write(frame.nodeList.get(i).name + ".setBackground(colorT);\n");
							fileWriter.flush();
							break;
						default : 
							break;
						}
					}
					// component의 이름이 null일 경우
					else {
						JOptionPane.showMessageDialog(null, "There is some components named 'null'");
						return;
					}
				}

				fileWriter.write("frame.setSize(980, 640);\n");
				fileWriter.write("frame.setDefaultCloseOperation(EXIT_ON_CLOSE);\n");
				fileWriter.write("frame.setVisible(true);\n");

				for (int i = 0; i < frame.nodeList.getSize(); i++)
					fileWriter.write("frame.add(" + frame.nodeList.get(i).name + ");\n");
				fileWriter.write("}\n");
				fileWriter.write("}\n");

				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
