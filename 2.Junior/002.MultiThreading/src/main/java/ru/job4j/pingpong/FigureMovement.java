package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Алгоритм движения фигуры.
 */
public class FigureMovement implements Runnable {
	/**
	 * Фигура, которая будет двигаться по заданному алгоритму в методе
	 * {@code run}.
	 */
	private Rectangle rect;

	/**
	 * Конструктор, принимающий фигуру.
	 *
	 * @param rect Фигура, для которой задано движение.
	 */
	public FigureMovement(Rectangle rect) {
		this.rect = rect;
	}

	@Override
	public void run() {
		int stepX = 1;
		int stepY = 1;
		while (!Thread.interrupted()) {
			if (rect.getX() >= 200 || rect.getX() <= 0) {
				stepX *= -1;
			}
			if (rect.getY() >= 200 || rect.getY() <= 0) {
				stepY *= -1;
			}
			rect.setX(rect.getX() + stepX);
			rect.setY(rect.getY() + stepY);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
