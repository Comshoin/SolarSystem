/**
 * 
 */
package system;

import javax.swing.JFrame;

/**
 * @author Comshoin
 * @author Aaron
 * @author Ryan
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame(MainPanel.TITLE + " v" + MainPanel.VERSION);
		frame.setContentPane(new MainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
