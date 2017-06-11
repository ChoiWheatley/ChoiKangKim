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
				//String path = fileChooser.getSelectedFile().getPath();
				File file = fileChooser.getSelectedFile();
				frame.nodeList = new ComponentStruct();
				//this.nodeList = frame.nodeList;
				try {
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(new FileReader(file.toString()));
					JSONObject temp = (JSONObject) obj;
					JSONArray jarr = (JSONArray)temp.get("Components");

					for (int i = 0; i < jarr.size(); i++) {
						JSONObject tmp = (JSONObject)jarr.get(i);
						Long tmpX = Long.parseLong((String) tmp.get("startX"));
						Long tmpY = Long.parseLong((String) tmp.get("startY"));
						Long tmpXL = Long.parseLong((String) tmp.get("xLength"));
						Long tmpTL = Long.parseLong((String) tmp.get("yLength"));
						Long tmpCT = Long.parseLong((String) tmp.get("compType"));
						
						CompNode tempnode = new CompNode();
						tempnode.startX = tmpX.intValue();
						tempnode.startY = tmpY.intValue();
						tempnode.xLength = tmpXL.intValue();
						tempnode.yLength = tmpTL.intValue();
						tempnode.compType = tmpCT.intValue();
						tempnode.name = (String) tmp.get("name");
						tempnode.compTextAttr = (String) tmp.get("CompTextAttr");
						//this.nodeList.get(i).compColor = (Color)tmp.get("compColor");
						frame.nodeList.add(tempnode);
					}
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
			frame.dispose();
			TestGUI frame2 = new TestGUI();
			frame2.main(null);
		}

		// 저장
		public void SaveFile(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				//File file = fileChooser.getSelectedFile();
				this.nodeList = frame.nodeList;
				JSONArray jarr = new JSONArray();
				JSONObject list = new JSONObject();

				for (int i = 0; i < nodeList.getSize(); i++) {
					JSONObject jobj = new JSONObject();
					jobj.put("startX", Long.toString(nodeList.get(i).startX));
					jobj.put("startY", Long.toString(nodeList.get(i).startY));
					jobj.put("xLength", Long.toString(nodeList.get(i).xLength));
					jobj.put("yLength", Long.toString(nodeList.get(i).yLength));
					jobj.put("compType", Long.toString(nodeList.get(i).compType));
					jobj.put("name", nodeList.get(i).name);
					jobj.put("compTextAttr", nodeList.get(i).compTextAttr);
					//jobj.put("compColor", nodeList.get(i).compColor);

					jarr.add(jobj);
				}

				list.put("Components", jarr);
				System.out.println(list);

				try {
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

		// 다른 이름으로 저장
		public void SaveName(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				//File file = fileChooser.getSelectedFile();
				this.nodeList = frame.nodeList;
				JSONArray jarr = new JSONArray();
				JSONObject list = new JSONObject();

				for (int i = 0; i < nodeList.getSize(); i++) {
					JSONObject jobj = new JSONObject();
					jobj.put("startX", Long.toString(nodeList.get(i).startX));
					jobj.put("startY", Long.toString(nodeList.get(i).startY));
					jobj.put("xLength", Long.toString(nodeList.get(i).xLength));
					jobj.put("yLength", Long.toString(nodeList.get(i).yLength));
					jobj.put("compType", Long.toString(nodeList.get(i).compType));
					jobj.put("name", nodeList.get(i).name);
					jobj.put("compTextAttr", nodeList.get(i).compTextAttr);
					//jobj.put("compColor", nodeList.get(i).compColor);

					jarr.add(jobj);
				}

				list.put("Components", jarr);
				System.out.println(list);

				try {
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
		
		/*
		// java 파일 생성
		public void JavaMake(TestGUI frame, JFileChooser fileChooser) {
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				this.nodeList = frame.nodeList
				try {
					FileWriter fileWriter = new FileWriter(file);
					for (int i = 0; i < nodeList.getSize(); i++) {
						switch(nodeList.get(i).compType) {
						case 1 : // button
							fileWriter.write("JButton tmp = new JButton(nodeList.get(i).compTextAttr);"\n);
							fileWriter.write("tmp.setSize(nodeList.get(i).xLength, nodeList.get(i).yLength);"\n);
							fileWriter.write("tmp.setLocation(nodeList.get(i).startX, nodeList.get(i).startY);"\n);
							// 변수 이름 지정
							// 색 지정
							fileWriter.flush();
						case 2 : // check box
							fileWriter.write("JCheckBox tmp = new JCheckBox(nodeList.get(i).compTextAttr);"\n);
							fileWriter.write("tmp.setSize(nodeList.get(i).xLength, nodeList.get(i).yLength);"\n);
							fileWriter.write("tmp.setLocation(nodeList.get(i).startX, nodeList.get(i).startY);"\n);
							
						case 3 : // label
							fileWriter.write("JLabel tmp = new JLabel(nodeList.get(i).compTextAttr);"\n);
							fileWriter.write("tmp.setSize(nodeList.get(i).xLength, nodeList.get(i).yLength);"\n);
							fileWriter.write("tmp.setLocation(nodeList.get(i).startX, nodeList.get(i).startY);"\n);
							
						case 4 : // text box
							fileWriter.write("JTextField tmp = new JTextFiled(nodeList.get(i).compTextAttr);"\n);
							fileWriter.write("tmp.setSize(nodeList.get(i).xLength, nodeList.get(i).yLength);"\n);
							fileWriter.write("tmp.setLocation(nodeList.get(i).startX, nodeList.get(i).startY);"\n);
							
						case 5 : // combo box
							fileWriter.write("JComboBox tmp = new JButton(nodeList.get(i).compTextAttr);"\n);
							fileWriter.write("tmp.setSize(nodeList.get(i).xLength, nodeList.get(i).yLength);"\n);
							fileWriter.write("tmp.setLocation(nodeList.get(i).startX, nodeList.get(i).startY);"\n);
							
						}
					}
				}
				catch (IOException e){
					
				}

			}
*/		
	}
}
