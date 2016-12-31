package alex9932.foxengine;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Console extends JDialog {
	private JTextArea textArea;
	private JScrollPane scrollpane;

	public Console() {
		super(new Frame(), "Console");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		scrollpane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollpane.setViewportView(textArea);
		this.add(scrollpane);
		this.setVisible(true);
	}
	
	public void append(String str) {
		textArea.append(str);
	}
	
	public void close() {
		this.dispose();
	}
}