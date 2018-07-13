package ru.job4j.jmm;

/**
 * Читающий поток.
 */
public class ReaderThread extends Thread {
	/**
	 * Объект, который является общим для всех потоков.
	 */
	private SharedObject sharedObject;

	/**
	 * Конструкотр, принимающий совместный объект.
	 *
	 * @param sharedObject Общий объект.
	 */
	public ReaderThread(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
		this.setName("Reader" + this.getName());
	}

	@Override
	public void run() {
		while (!sharedObject.isFlagSet()) {
			System.out.println(this.getName()
					+ ": shared obj = " + sharedObject.getSharedInt()
					+ "; flag = " + sharedObject.isFlagSet());
		}
	}
}
