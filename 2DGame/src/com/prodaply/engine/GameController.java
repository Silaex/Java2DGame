package com.prodaply.engine;

import java.awt.Color;
import java.awt.Toolkit;

import com.prodaply.engine.util.Vector2f;

public class GameController implements Runnable {
	
	protected final String NAME = "The game that has no name";
	protected static Vector2f scale = new Vector2f(4, 4);
	protected static float resolution = Toolkit.getDefaultToolkit().getScreenSize().getSize().width / (float)Toolkit.getDefaultToolkit().getScreenSize().getSize().height;
	protected static final int HEIGHT = 128;
	protected static final int WIDTH = (int)(HEIGHT * resolution);
	
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGameController aGameController;
	private Thread thread;
	
	private boolean running = false;
	protected static final float FRAME_RATE = 60.0f;
	
	
	public GameController (AbstractGameController agc) {
		this.aGameController = agc;
	}
	
	public void start() {
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		float elapsedTime = 0;
		double nsPerTick = 1000000000.0 / FRAME_RATE;
		int frames = 0;
		int ticks = 0;
		int fps = 0;
		float deltaTime = 1 / FRAME_RATE;
		
		while (running) {
			long now = System.nanoTime();
			elapsedTime += (now - lastTime) / nsPerTick;
			lastTime = now;
			frames++;
			
			while (elapsedTime >= deltaTime) {
				// "Physics"
				aGameController.fixedUpdate(this, deltaTime);
				ticks++;
				elapsedTime -= 1;
			}
			
			// Game Loop
			renderer.alphaProcessing();
			// Initialize abstract methods
			aGameController.update(this, renderer);
			renderer.drawText("fps: " + fps, 0, HEIGHT - 16, Color.white.getRGB());
			window.update();
			input.update();
			// Clear frame
			renderer.clear();
			
			
			try {
				Thread.sleep((int)(((1 - elapsedTime) * 500) / FRAME_RATE));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// When 1 second is passed
			if (System.currentTimeMillis() - lastTimer > 1000) {
//				System.out.println(ticks + " ticks, " + fps + " fps");
				lastTimer += 1000;
				fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public Input getInput() {
		return input;
	}

	public String getName() {
		return NAME;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static Vector2f getScale() {
		return scale;
	}
	
	public Window getWindow() {
		return window;
	}
}
