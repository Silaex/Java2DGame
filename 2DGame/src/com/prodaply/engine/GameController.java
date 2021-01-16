package com.prodaply.engine;

import java.awt.Color;

public class GameController implements Runnable {
	
	protected final String NAME = "2D Game";
	protected final int HEIGHT = 16*16;
	protected final int WIDTH = 16*16;
	protected final int SCALE = 3;
	
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGameController aGameController;
	private Thread thread;
	
	private boolean running = false;
	private float FRAME_RATE = 60.0f;
	
	
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
				aGameController.update(this, deltaTime);
				ticks++;
				elapsedTime -= 1;
			}
			
			// Game Loop
			// Clear frame
			// Initialize abstract methods
			renderer.drawText("fps: " + fps, 0, HEIGHT - 16, Color.white.getRGB());
			aGameController.render(this, renderer);
			window.update();
			input.update();
			renderer.clear();
			
			try {
				Thread.sleep((int)(((1 - elapsedTime) * 500) / FRAME_RATE));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// When 1 second is passed
			if (System.currentTimeMillis() - lastTimer > 1000) {
				System.out.println(ticks + " ticks, " + fps + " fps");
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
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getScale() {
		return SCALE;
	}
	
	public Window getWindow() {
		return window;
	}
}