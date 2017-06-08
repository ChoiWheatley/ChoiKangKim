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
	
	// 파일 열기
	public void FileOpen(JFileChooser fileChooser) {
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				// 배열이 아닌 경우 읽어오기
				Object obj = parser.parse(new FileReader(file.toString()));
				JSONObject jobj = (JSONObject) obj;
				// ex) String name = (String) jobj.get("name");

				// 배열인 경우 읽어오기
				JSONArray magList = (JSONArray) jobj.get("list");
				Iterator<String>iterator = magList.iterator();
				while (iterator.hasNext()) {
					// 확실하지 않음 우리 배열 쓰는거 있남?
					// ex) name = iterator.next();
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
		//if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
		//File file = fileChooser.getSelectedFile();
		// 배열이 아닌경우 저장
		JSONObject jobj = new JSONObject();
		// ex) jobj.put("name", "button1S");

		//배열인 경우 저장
		JSONArray list = new JSONArray();
		// ex) list.add("name?");
		// ex) jobj.put("name", name);

		try (FileWriter filewriter = new FileWriter("d:\\jsonfile/NewFile.json")) {
			filewriter.write(jobj.toJSONString());
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
			// 배열이 아닌경우 저장
			JSONObject jobj = new JSONObject();
			// ex) obj.put("name", "button1S");

			//배열인 경우 저장
			JSONArray list = new JSONArray();
			// ex) list.add("name?");
			// ex) jobj.put("name", name);

			try (FileWriter filewriter = new FileWriter(file.toString())) {
				filewriter.write(jobj.toJSONString());
				filewriter.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// java 파일 생성
	public void JavaMake() {
		// 이건 모르겠다...ㅠㅠ
	}
}
