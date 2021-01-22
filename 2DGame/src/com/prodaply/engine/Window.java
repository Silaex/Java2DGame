package com.prodaply.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.prodaply.engine.util.Vector2f;

public class Window {
	private BufferedImage image;
	private BufferStrategy bs;
	private JFrame frame;
	private JPanel panel;
	private Canvas canvas;
	private Graphics2D g;
	private GraphicsDevice gDevice;
	private float resolution;
	private GameController gc;
	private int[][] resolutions = {
			{ 640, 480 },
			{ 1024, 768 },
			{ 1600, 900 }
	};
	private int resolutionChoosen = -1;
	
	public Window(GameController gc) {
		this.gc = gc;
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		resolution = Toolkit.getDefaultToolkit().getScreenSize().getSize().width / (float)Toolkit.getDefaultToolkit().getScreenSize().getSize().height;
		
		Dimension gameDimension = new Dimension((int)(gc.getWidth() * gc.getScale().x), (int)(gc.getHeight() * gc.getScale().y));
		frame = new JFrame(gc.getName());
		canvas.setSize(gameDimension);
		
		System.out.println(Toolkit.getDefaultToolkit().getScreenResolution());
		
		this.panel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("nice");
			}
	    });
		
		// Listeners pour écouter les changements d'état de la fenêtre
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				Vector2f frameScaling = new Vector2f(frame.getContentPane().getWidth() / (float)gc.getWidth(), frame.getContentPane().getHeight() / (float)gc.getHeight());
				gc.scale = frameScaling;
				canvas.setSize((int)(gc.getWidth() * gc.getScale().x), (int)(gc.getHeight() * gc.getScale().y));
				//gc.getWidth() * (int)gc.getScale().y, gc.getWidth() * (int)gc.getScale().y
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
	}
	
	public void update() {
		if (gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			resolutionChoosen++;
			int index = resolutionChoosen % resolutions.length;
			frame.setSize(resolutions[index][0], resolutions[index][1]);
		}
		g = (Graphics2D)bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (int)(gc.getWidth() * this.gc.getScale().x), (int)(gc.getHeight() * this.gc.getScale().y), null);
		g.dispose();
		bs.show();
	}

	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
