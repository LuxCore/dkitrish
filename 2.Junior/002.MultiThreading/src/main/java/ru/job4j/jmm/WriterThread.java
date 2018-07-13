package ru.job4j.jmm;

/**
 * Пишущий или вносящий изменения поток.
 */
public class WriterThread extends Thread {
	/**
	 * Объект, который является общим для всех потоков.
	 */
	private SharedObject sharedObject;

	/**
	 * Конструкотр, принимающий совместный объект.
	 *
	 * @param sharedObject Общий объект.
	 */
	public WriterThread(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
		this.setName("Writer" + this.getName());
	}

	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sharedObject.incSharedInt();
		sharedObject.changeFlag();
		System.out.println(this.getName() + ": shared obj = "
				+ sharedObject.getSharedInt()
				+ " and flag = " + sharedObject.isFlagSet());
	}
}
