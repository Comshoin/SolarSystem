/**
 * 
 */
package system;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


/**
 * @author Comshoin
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable {
	//Dimensions
	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int WIDTH = 640;
	public static int HEIGHT = 640;
	public static double SCALE;

	//Properties
	public static final String TITLE = "Solar System";
	public static final String VERSION = "1.0.0";

	//Image
	private BufferedImage image;
	private Graphics2D g2d;

	//Thread
	private Thread thread;
	private boolean running;
	private int TargetFPS = 60;	
	private int FPS;
	private long targetTime = 1000 / TargetFPS;


	/**
	 * 
	 */
	public MainPanel() {
		//Calculate scaling
		if(Math.abs(screenWidth / screenHeight - 16.0f / 9.0f) / (16.0f / 9.0f) < 0.0005f){
			SCALE = ((int) (screenWidth / 1360 / 0.25)) * 0.25;
		} else if(screenWidth / screenHeight - (16.0f / 9.0f) / (16.0f / 9.0f) > 0.0005f){
			SCALE = ((int) (screenHeight / 1360 / 0.25)) * 0.25;
		} else if(screenWidth / screenHeight - (16.0f / 9.0f) / (16.0f / 9.0f) < -0.0005f){
			SCALE = ((int) (screenWidth / 765 / 0.25)) * 0.25;
		}
		//Scales the width and height
		WIDTH *= SCALE;
		HEIGHT *= SCALE;
		//Sets JPanel size
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.requestFocus();
	}

	/**
	 * Initializes double buffering and grid
	 */
	public void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		running = true;
	}

	/**
	 * 
	 */
	public void update(){
		
	}

	/**
	 * This method renders to the double buffered image
	 */
	public void render(){		
		
	}

	/**
	 * Method to render the double buffered frame
	 */
	public void renderToScreen(){
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	@Override
	public void run() {
		long start;
		long elapsed;
		long delay;

		init();

		//Game loop
		while(running){
			start = System.nanoTime();

			update();
			render();
			renderToScreen();

			elapsed = System.nanoTime() - start;
			delay = targetTime - elapsed / 1000000;
			FPS = (int) (1000000000 / elapsed);

			if(delay < 0){
				delay = 0;
			}

			try{
				Thread.sleep(delay);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//Initializes thread and listeners
	@Override
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			//this.addKeyListener(this);
			//this.addMouseListener(this);
			//this.addMouseMotionListener(this);
			//this.addMouseWheelListener(this);
			thread.start();
		}
	}
}
