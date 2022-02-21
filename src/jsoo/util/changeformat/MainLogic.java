package jsoo.util.changeformat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * File format changer
 * @author jsoo 
 * 2022.02.21
 */
public class MainLogic extends JFrame implements ActionListener{
	
	JButton b1,b2;
	JPanel p;
	public MainLogic() {
		setSize(300,200);
		setTitle("인코딩변환");
		
		p = new JPanel();
		b1 = new JButton("UTF-8 To MS949");
		b2 = new JButton("MS949 To UTF-8");
		
		b1.addActionListener(this);//이벤트메소드호출
		b2.addActionListener(this);//이벤트메소드호출
		
		p.add(b1);
		p.add(b2);
		add(p);
		setVisible(true);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			FileOpener fo = new FileOpener();
			fo.fileOpen(0);

		}else if(e.getSource()==b2) {
			FileOpener fo = new FileOpener();
			fo.fileOpen(1);
		}
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainLogic();
	}

}
