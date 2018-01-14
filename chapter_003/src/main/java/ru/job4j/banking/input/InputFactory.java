package ru.job4j.banking.input;

// import java.util.List;

/**
 * Simple Factory of inputs.
 */
public class InputFactory {
	/**
	 * Creates desired console inputs.
	 * <p>It is wrapper factory over decorator.
	 *
	 * @param inputType Type of input.
	 *
	 * @return Desired console input.
	 */
	public static Input createConsole(InputType inputType) {
		Input input = null;

		if (inputType == InputType.VALID) {
			input = new ValidMenuNumberInput(new ConsoleInput());
		}

		return input;
	}

	/**
	 * Creates desired stub inputs.
	 * <p>It is wrapper factory over decorator.
	 *
	 * @param inputType Type of input.
	 * @param stubAnswers List of answers for automatic tests
	 *
	 * @return Desired stub input.
	 */
	public static Input createStub(InputType inputType, String[] stubAnswers) {
		Input input = null;

		if (inputType == InputType.VALID) {
			input = new ValidMenuNumberInput(new StubInput(stubAnswers));
		}

		return input;
	}
}
