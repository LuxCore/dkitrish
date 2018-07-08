package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Игра пинг-понг в JavaFX интерфейсе.
 */
public class PingPong extends Application {
	@Override
	public void start(Stage primaryStage) {
		int initWidth = 200;
		int initHeight = 200;
		Random rnd = new Random();
		int xRange = rnd.nextInt(initWidth);
		int yRange = rnd.nextInt(initHeight);
		Group root = new Group();
		Rectangle rect = new Rectangle(xRange, yRange, 10, 10);
		root.getChildren().add(rect);
		Thread t = new Thread(new FigureMovement(rect));
		t.start();
		primaryStage.setScene(new Scene(root, initWidth, initHeight));
		primaryStage.setTitle("Ping-pong");
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(event -> t.interrupt());
		primaryStage.show();
	}

	/**
	 * Точка запуска приложения.
	 *
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
