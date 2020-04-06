package ufogame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import view.GameFrameWork;
import view.IGameObject;
import view.IKeyboardListener;
import view.ITickableListener;
import view.Message;

public class Game implements ITickableListener, IKeyboardListener {

	// Idea: we want to have multiple instances of an ufo and of a projectile
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Ufo> ufos = new ArrayList<>();
//	private Ufo [] ufos = new Ufo[10];
//	private Projectile [] projectiles = new Projectile[30];
	private Ship ship;
	private int screenWidth = 900;
	private int screenHeight = 700;
	private GameFrameWork frameWork = new GameFrameWork();
	private int score = 0;
	Message score_message = new Message("Score " + score, 780, 25, 24, new Color(255, 255, 255));

	/**
	 * Initiates everything for the game. Multiple ufos and a ship are created.
	 */
	public void init() {
		frameWork.setSize(screenWidth, screenHeight);
		frameWork.setBackground(new Background("01Vorlesung\\assets\\space14.jpg"));
		
		ship = new Ship(screenWidth / 2, 4 * screenHeight / 5, screenWidth / 9, screenWidth / 9,
				"01Vorlesung\\assets\\ship23.png");
		frameWork.addGameObject(ship);

		Ufo ufo = new Ufo(-20, screenHeight / 5, screenWidth / 10, screenWidth / 19, 1,
				"01Vorlesung\\assets\\ufo20.png");
		ufos.add(ufo);
		frameWork.addGameObject(ufo);

		for (int i = 1; i < 10; i++) {
			ufos.add(new Ufo(ufos.get(i - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(i));
		}
		
		
		frameWork.addMessage(score_message);

		frameWork.addTick(this);
		frameWork.addIKeyInput(this);

	}

	public void shoot() {
		// create a projectile
		Projectile projectile = new Projectile(ship.getX() + ship.getWidth()/4, 
				ship.getY() - ship.getWidth() / 2, ship.getWidth() / 2, ship.getWidth() / 2, 3,
				"01Vorlesung\\assets\\projectile06.png");
		projectiles.add(projectile);

		frameWork.addGameObject(projectile);

//		for(int i = 0; i < 1000; i++) {
//			projectiles.add(projectile);
//		}
//		
//		for(Projectile p : projectiles) {
//			System.out.println(p.getImagePath());
//		}
		// Variante Array
		// projectiles[0] = projectile;

		// projectiles.get(0).getWidth();
		// Variante Array
		// projectiles[0].getWidth();

		// projectiles.size();
		// Variante Array
		// projectiles.lenght
	}
	
	@Override
	public void tick(long elapsedTime) {
		for (Ufo ufo : ufos) {
			ufo.move();
		}
		if (ufos.get(0).getX() > screenWidth) {
			frameWork.removeGameObject(ufos.get(0));
			ufos.remove(0);
			ufos.add(new Ufo(ufos.get(ufos.size() - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(ufos.size() - 1));
		}
		
		for(Projectile p: projectiles) {
            p.move();
        }
		
		
		// Projektile die über den Rand gehen = deleted
		if(projectiles.size() > 0) {
			if(projectiles.get(0).getY() < -10) {
				frameWork.removeGameObject(projectiles.get(0));
				projectiles.remove(0);
			}
		}
		
		
		
		// Collision + Score
		if(checkCollided()) {
			frameWork.removeMessage(score_message);
			score += 1;
			score_message = new Message("Score " + score, 780, 25, 24, new Color(255, 255, 255));
			frameWork.addMessage(score_message);
		}
		
		
		//TODO check size of list
		if(projectiles.size() < 0) {
			frameWork.removeGameObject(projectiles.get(0));
		}
		
	}

	@Override
	public int[] getKeys() {
		int [] keys = {KeyEvent.VK_SPACE};
		return keys;
	}

	@Override
	public void keyDown(int key) {
		shoot();
	}
	
	
	// Collision AABB
	//	box1.x + box1.w >= box2.x
	//	box2.x + box2.w >= box1.x
	//	box1.y + box1.h >= box2.y
	//	box2.y + box2.h >= box1.y
	public boolean checkCollided() {
		for(int p = 0; p < projectiles.size(); p++) {
			for(int u = 0; u < ufos.size(); u++) {
				if(projectiles.get(p).getX() + projectiles.get(p).getWidth() >= ufos.get(u).getX()
						&& ufos.get(u).getX() + ufos.get(u).getWidth() >= projectiles.get(p).getX()
						&& projectiles.get(p).getY() + projectiles.get(p).getHeight() >= ufos.get(u).getY()
						&& ufos.get(u).getY() + ufos.get(u).getHeight() >= projectiles.get(p).getY()) {
					
					frameWork.removeGameObject(projectiles.get(p));
					frameWork.removeGameObject(ufos.get(u));
					projectiles.remove(p);
					ufos.remove(u);
					return true;
				}
			}
		}
		return false;
	}
	
}
