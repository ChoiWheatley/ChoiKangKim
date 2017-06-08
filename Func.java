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

	JSONParser parser = new JSONParser();
	String path = new String();
	ComponentStruct nodeList = new ComponentStruct();

	// 파일 열기
	public void FileOpen(JFileChooser fileChooser) {
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				Object obj = parser.parse(new FileReader(file.toString()));
				String data = obj.toString();
				JSONParser parser = new JSONParser();
				JSONObject jobj = (JSONObject) parser.parse(data);
				JSONArray jarr = (JSONArray)jobj.get("Comp");

				for (int i = 0; i < jarr.size(); i++) {
					JSONObject tmp = (JSONObject)jarr.get(i);
					nodeList.get(i).startX = (int)tmp.get("startX");
					nodeList.get(i).startY = (int)tmp.get("startY");
					nodeList.get(i).xLength = (int)tmp.get("xLength");
					nodeList.get(i).yLength = (int)tmp.get("yLength");
					nodeList.get(i).compType = (int)tmp.get("compType");
					nodeList.get(i).name = (String)tmp.get("name");
					nodeList.get(i).compTextAttr = (String)tmp.get("compTextAttr");
					nodeList.get(i).compColor = (Color)tmp.get("compColor");
					nodeList.add(nodeList.get(i));
				}
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
	public void FileMake(JFileChooser fileChooser) {
		// 모든 componant값 삭제
	}

	// 저장
	public void SaveFile() {
		JSONArray jarr = new JSONArray();

		for (int i = 0; i < nodeList.getSize(); i++) {
			JSONObject jobj = new JSONObject();
			
			jobj.put("startPoint", nodeList.get(i).startX);
			jobj.put("lastPoint", nodeList.get(i).startY);
			jobj.put("xLength", nodeList.get(i).xLength);
			jobj.put("yLength", nodeList.get(i).yLength);
			jobj.put("compType", nodeList.get(i).compType);
			jobj.put("name", nodeList.get(i).name);
			jobj.put("compTextAttr", nodeList.get(i).compTextAttr);
			jobj.put("compColor", nodeList.get(i).compColor);
			
			jarr.add(jobj);
		}
		
		JSONObject Comp = new JSONObject();
		Comp.put("Comp", jarr);
		
		try (FileWriter filewriter = new FileWriter("c:\\jsonfile/NewFile.json")) {
			filewriter.write(jarr.toJSONString());
			filewriter.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 다른 이름으로 저장
	public void SaveName(JFileChooser fileChooser) {
		if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
		}
	}

	// java 파일 생성
	public void JavaMake() {
		// 이건 모르겠다...ㅠㅠ
	}
}
