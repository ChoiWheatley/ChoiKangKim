
import javax.swing.*;
import java.awt.*;

public class AttributePane {
	public JPanel getContent() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		Rectangle pan = new Rectangle(5, 5, 180, 35);
		
		JLabel label = new JLabel("���� x, y ��ǥ");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		JTextField txtfield = new JTextField(pan.x  + ", " + pan.y); // ���ø� ���� ���� ��
		txtfield.setBounds(185, 5, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("�ʺ�, ����");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField(pan.width  + ", " + pan.height);
		txtfield.setBounds(185, 40, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("������Ʈ�� �ؽ�Ʈ �Ӽ���");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField();
		txtfield.setBounds(185, 75, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("������Ʈ Ÿ��");
		label.setBounds(pan);
		label.setBorder(BorderFactory.createEtchedBorder());
		panel.add(label);
		pan.y += 35;
		txtfield = new JTextField();
		txtfield.setBounds(185, 110, 100, 35);
		panel.add(txtfield);
		
		label = new JLabel("������Ʈ ������");
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
