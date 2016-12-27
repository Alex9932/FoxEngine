package alex9932.foxengine;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ErrorMessage extends JDialog {
	private JPanel ROOT = new JPanel();
	protected Image img;

	{
		try {
			img = ImageIO.read(new File("./res/textures/logo.png"));
		} catch (IOException e) {
		}
	}
	
	public ErrorMessage() {
		super(new Frame(), "Fox engine");
		this.setSize(350, 180);
		getContentPane().add(ROOT);
		ROOT.setLayout(null);
		
		JPanel icon = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, 32, 32, null);
			}
		};
		icon.setBounds(15, 15, 32, 32);
		ROOT.add(icon);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 117, 25);
		ROOT.add(btnNewButton);
		
		this.setVisible(true);
	}
}