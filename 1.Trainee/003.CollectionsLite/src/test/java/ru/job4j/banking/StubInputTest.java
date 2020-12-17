package ru.job4j.banking;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;
import ru.job4j.banking.input.InputFactory;
import ru.job4j.banking.input.InputType;
import ru.job4j.banking.menu.MainMenu;
import ru.job4j.banking.menu.MenuUser;
import ru.job4j.banking.menu.MenuFactory;
import ru.job4j.banking.start.StartUI;

/**
 * Tests bank application with virtual user by stub answers.
 * =============================================================================
 * If menu order will be changed than all tests are falls.
 * =============================================================================
 */
public class StubInputTest {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(StubInputTest.class);

	/**
	 * Addition of user to bank system.
	 */
	@Test
	public void testRegisterNewUserInBankSystem() {
		LOG.debug("BEGIN --- REGISTRATION OF NEW USER IN BANK SYSTEM ---");
		String userName1 = "Unknown User";
		String userPassport1 = "UU000000";

		String userName2 = "John Doe";
		String userPassport2 = "JDXXXYYY";

		// Duplicate of second user.
		String userName3 = "John Doe";
		String userPassport3 = "JDXXXYYY";

		String[] answers = {
				"1", userName1, userPassport1,
				"1", userName2, userPassport2,
				"1", userName3, userPassport3, "7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		Set<User> actual = bank.getUsersAccounts().keySet();
		Set<User> expected = new TreeSet<>();
		expected.add(new User(userName2, userPassport2));
		expected.add(new User(userName1, userPassport1));

		assertThat(expected, is(actual));
		LOG.debug("END --- REGISTRATION OF NEW USER IN BANK SYSTEM ---");
	}

	/**
	 * Deletion of user from bank system.
	 */
	@Test
	public void testDeletionOfRegisteredUserInBankSystem() {
		LOG.debug("BEGIN --- DELETION OF REGISTERED USER IN BANK SYSTEM ---");
		String userName1 = "Unknown User";
		String userPassport1 = "UU000000";

		String userName2 = "John Doe";
		String userPassport2 = "JDXXXYYY";

		String[] answers = {
				"1", userName1, userPassport1,
				"1", userName2, userPassport2,
				"2", userPassport1, "7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		Set<User> actual = bank.getUsersAccounts().keySet();
		Set<User> expected = new TreeSet<>();
		expected.add(new User(userName2, userPassport2));

		assertThat(expected, is(actual));
		LOG.debug("END --- DELETION OF REGISTERED USER IN BANK SYSTEM ---");
	}

	/**
	 * Creation of new account for registered user in bank system.
	 */
	@Test
	public void testCreationOfAccountForRegisteredUserInBankSystem() {
		LOG.debug("BEGIN --- CREATION OF AN ACCOUNT FOR REGISTERED USER IN BANK SYSTEM ---");
		String userName1 = "Unknown User";
		String userPassport1 = "UU000000";
		String userReqisites1 = "REQ-123456789";
		String userReqisites2 = "RQST-789456123";

		String[] answers = {
				"1", userName1, userPassport1,
				"3", userPassport1, userReqisites1, "1234567.89",
				"3", userPassport1, userReqisites2, "9876543.21", "7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		Optional<List<Account>> actual = bank.getUserAccounts(userPassport1);
		List<Account> expected = new ArrayList<>();
		expected.add(new Account(userReqisites1));
		expected.add(new Account(userReqisites2));

		assertThat(expected, is(actual.get()));
		LOG.debug("END --- CREATION OF AN ACCOUNT FOR REGISTERED USER IN BANK SYSTEM ---");
	}

	/**
	 * Deletion of registered account for registered user in bank system.
	 */
	@Test
	public void testDeletionOfAccountForRegisteredUserInBankSystem() {
		LOG.debug("BEGIN --- DELETION OF AN ACCOUNT FOR REGISTERED USER IN BANK SYSTEM ---");
		String userName1 = "Unknown User";
		String userPassport1 = "UU000000";
		String userReqisites1 = "REQ-123456789";
		String userReqisites2 = "RQST-789456123";

		String[] answers = {
				"1", userName1, userPassport1,
				"3", userPassport1, userReqisites1, "1234567.89",
				"3", userPassport1, userReqisites2, "9876543.21",
				"4", userPassport1, userReqisites1, "7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		Optional<List<Account>> actual = bank.getUserAccounts(userPassport1);
		List<Account> expected = new ArrayList<>();
		expected.add(new Account(userReqisites2));

		assertThat(expected, is(actual.get()));
		LOG.debug("END --- DELETION OF AN ACCOUNT FOR REGISTERED USER IN BANK SYSTEM ---");
	}

	/**
	 * Show all accounts of registered user in bank system.
	 */
	@Test
	public void testShowAllAccountsOfRegisteredUserInBankSystem() {
		LOG.debug("BEGIN --- SHOW ALL ACCOUNTS OF REGISTERED USER IN BANK SYSTEM ---");
		String userName1 = "Unknown User";
		String userPassport1 = "UU000000";
		String userReqisites1 = "REQ-123456789";
		String userReqisites2 = "RQST-789456123";
		String userReqisites3 = "RQST-159753";

		String[] answers = {
				"1", userName1, userPassport1,
				"3", userPassport1, userReqisites1, "1234567.89",
				"3", userPassport1, userReqisites2, "9876543.21",
				"3", userPassport1, userReqisites3, "0.00",
				"5", userPassport1, "7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		Optional<List<Account>> actual = bank.getUserAccounts(userPassport1);
		List<Account> expected = new ArrayList<>();
		expected.add(new Account(userReqisites1, 1234567.89));
		expected.add(new Account(userReqisites2, 9876543.21));
		expected.add(new Account(userReqisites3, 0.00));

		assertThat(expected, is(actual.get()));
		LOG.debug("END --- SHOW ALL ACCOUNTS OF REGISTERED USER IN BANK SYSTEM ---");
	}

	/**
	 * Transfer money from one account to another.
	 */
	@Test
	public void testTransferMoney() {
		LOG.debug("BEGIN --- TRANSFER MONEY FROM ONE ACCOUNT TO ANOTHER ---");
		String user1Name = "Unknown User";
		String user1Passport = "UU000000";
		String user1Requisites1 = "REQ_USR1-1";
		String user1Requisites2 = "REQ_USR1-2";
		String user2Name = "John Doe";
		String user2Passport = "JD??????";
		String user2Requisites1 = "REQ_USR2-1";

		String[] answers = {
				"1", user1Name, user1Passport,
				"3", user1Passport, user1Requisites1, "100000.25",
				"3", user1Passport, user1Requisites2, "80000.50",
				"1", user2Name, user2Passport,
				"3", user2Passport, user2Requisites1, "40000.75",
				"6", user2Passport, user2Requisites1, user1Passport, user1Requisites2, "20000.75",
				"6", user1Passport, user1Requisites1, user1Passport, user1Requisites2, "70000.25",
				"7"};

		Bank bank = new Bank();
		Input input = InputFactory.createStub(InputType.VALID, answers);
		MainMenu menu = MenuFactory.create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);

		double actual = bank.getUserAccount(
				new User(user1Name, user1Passport),
				user1Requisites2).get().getValue();
		double expected = 170_001.50;

		assertThat(expected, is(actual));
		LOG.debug("END --- TRANSFER MONEY FROM ONE ACCOUNT TO ANOTHER ---");
	}
}
