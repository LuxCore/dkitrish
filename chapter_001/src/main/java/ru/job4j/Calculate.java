package ru.job4j;

/**
 *	Класс для вычислений.
 *	@author Denis.Kitrish
 *	@since 20.09.2017
 *	@version 1.0
 */
public class Calculate {

	/**
	 *	Вывод строки в консоль.
	 *
	 *	@param args Параметры из командной строки.
	 */
	public static void main(String[] args) {
		System.out.println("I`m vary happy to live my life :)");
	}

	/**
	 *	Method echo.
	 *
	 *	@param name Sombody's name.
	 *	@return Three times "echo" plus sombody's name.
	 */
	public String echo(String name) {
		return "Echo, echo, echo: " + name;
	}
}