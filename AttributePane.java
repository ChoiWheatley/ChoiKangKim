
import javax.swing.*;
import java.awt.*;

public class AttributePane {
	public JPanel getContent() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		Rectangle pan = new Rectangle(5, 5, 180, 35);
		
		JLabel label = new JLabel("시작 x, y 좌표");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		JTextField txtfield = new JTextField(pan.x  + ", " + pan.y); // 예시를 위해 넣은 값
		txtfield.setBounds(185, 5, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("너비, 높이");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField(pan.width  + ", " + pan.height);
		txtfield.setBounds(185, 40, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("컴포넌트의 텍스트 속성값");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField();
		txtfield.setBounds(185, 75, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("컴포넌트 타입");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField();
		txtfield.setBounds(185, 110, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("컴포넌트 변수명");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField();
		txtfield.setBounds(185, 145, 100, 35);
		panel.add(txtfield);
		
		return panel;
	}
	
}
