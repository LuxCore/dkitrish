package ru.job4j.banking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserAccountException;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.User;

/**
 * Tests Bank methods.
 */
public class BankTest {
	/**
	 * Checking for expected exception.
	 */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Tests addition of user to list of users and theirs accounts.
	 */
	@Test
	public void test1UserAdditionToUsersAccounts() {
		Bank bank = new Bank();
		User user = new User("Batman", "BM555777");
		boolean actual = bank.addUser(user);

		assertThat(true, is(actual));
	}

	/**
	 * Tests addition of user to list of users and theirs accounts.
	 */
	@Test
	public void test2UserAdditionToUsersAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		User user2 = new User("Batman", "BM555777");
		boolean actual = bank.addUser(user2);

		assertThat(false, is(actual));
	}

	/**
	 * Tests deletion of user from list of users and theirs accounts.
	 */
	@Test
	public void test1UserDeletionFromUsersAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		User user2 = new User("Xander Cage", "XC-XXX");
		bank.addUser(user2);

		List<Account> actual = bank.deleteUser(user1);

		assertThat(new ArrayList<>(), is(actual));
	}

	/**
	 * Tests deletion of user from list of users and theirs accounts.
	 * It is nothing to delete if no argument user in map.
	 */
	@Test
	public void test2UserDeletionFromUsersAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		User user2 = new User("Xander Cage", "XC-XXX");
		bank.addUser(user2);

		List<Account> actual = bank.deleteUser(user2);
		List<Account> expected = new LinkedList<>();

		assertThat(expected, is(actual));
	}

	/**
	 * Test of addition of account to users list of accounts.
	 */
	@Test
	public void test1AccountAdditionToUserAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		Account account = new Account("Batman requisites", 69_666.69);
		boolean actual = bank.addUserAccount("BM555777", account);

		assertThat(true, is(actual));
	}

	/**
	 * Test of deletion of account from users list of accounts.
	 */
	@Test
	public void test1AccountDeletionFromUserAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		Account account1 = new Account("Batman requisites 2", 69_666.69);
		bank.addUserAccount("BM555777", account1);

		Account account2 = new Account("Batman requisites 2", 77_777.69);
		bank.addUserAccount("BM555777", account2);

		Account actual = bank.deleteUserAccount("BM555777", account1);
		Account expected = new Account("Batman requisites 2", 69_666.69);

		assertThat(expected, is(actual));
	}

	/**
	 * Test of deletion of account from users list of accounts.
	 */
	@Test
	public void test2AccountDeletionFromUserAccounts() {
		Bank bank = new Bank();
		User user1 = new User("Batman", "BM555777");
		bank.addUser(user1);

		Account account1 = new Account("Batman requisites 1", 11_111.11);
		bank.addUserAccount("BM555777", account1);

		Account account2 = new Account("Batman requisites 2", 11_222.22);
		bank.addUserAccount("BM555777", account2);

		Account account3 = new Account("Batman requisites 3", 33_333.33);
		bank.addUserAccount("BM555777", account3);

		Account account4 = new Account("Batman requisites 4", 44_444.44);
		bank.addUserAccount("BM555777", account4);

		List<Account> actual = new ArrayList<>(); //bank.getUserAccounts("BM555777");
		actual.add(bank.deleteUserAccount("BM555777", account1));
		actual.add(bank.deleteUserAccount("BM555777", account3));

		List<Account> expected = new ArrayList<>();
		expected.add(new Account("Batman requisites 1", 11_111.11));
		expected.add(new Account("Batman requisites 3", 33_333.33));

		assertThat(expected, is(actual));
	}

	/**
	 * Test of deletion of account from users list of accounts.
	 * <p>After deletion of last account list of account must not be equals to
	 * null, it must be empty.
	 */
	@Test
	public void test3AccountDeletionFromUserAccounts() {
		Bank bank = new Bank();
		String batmanPassport = "BM555777";
		User user1 = new User("Batman", batmanPassport);
		bank.addUser(user1);

		Account account1 = new Account("Batman requisites of last account", 69_666.69);
		bank.addUserAccount(batmanPassport, account1);

		bank.deleteUserAccount(batmanPassport, account1);
		List<Account> actual = bank.getUserAccounts(batmanPassport);
		List<Account> expected = new ArrayList<>();

		assertThat(expected, is(actual));
	}

	/**
	 * Test of getting of all user accounts.
	 */
	@Test
	public void testGetAllUserAccounts() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account account1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, account1);

		Account account2 = new Account("LUX reqisites 2", 6_900_000.69);
		bank.addUserAccount(cgPassport, account2);

		Account account3 = new Account("LUX reqisites 3", 3_200_000.69);
		bank.addUserAccount(cgPassport, account3);

		Account account4 = new Account("LUX reqisites 4", 4_400_000.69);
		bank.addUserAccount(cgPassport, account4);

		List<Account> expected = new LinkedList<>();
		expected.add(account1);
		expected.add(account2);
		expected.add(account3);
		expected.add(account4);
		List<Account> actual = null;
		try {
			actual = bank.getUserAccounts("666");
		} catch (NoSuchUserException e) {
			actual = bank.getUserAccounts(cgPassport);
		}

		assertThat(expected, is(actual));
	}

	/**
	 * Test of transfer of money.
	 */
	@Test
	public void test1TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		Account cgAccount2 = new Account("LUX reqisites 2", 6_900_000.69);
		bank.addUserAccount(cgPassport, cgAccount2);

		Account cgAccount3 = new Account("LUX reqisites 3", 3_200_000.69);
		bank.addUserAccount(cgPassport, cgAccount3);

		Account cgAccount4 = new Account("LUX reqisites 4", 4_400_000.69);
		bank.addUserAccount(cgPassport, cgAccount4);

		final String asPassport = "AS696969";
		User user2 = new User("Anastasia Steele", asPassport);
		bank.addUser(user2);

		Account asAccount1 = new Account("DELUX reqisites 1", 69.69);
		bank.addUserAccount(asPassport, asAccount1);

		Account asAccount2 = new Account("DELUX reqisites 2", 0.69);
		bank.addUserAccount(asPassport, asAccount2);

		boolean actual = bank.transferMoney(cgPassport, "LUX reqisites 2",
				asPassport, "DELUX reqisites 1", 5_000_000.0);

		assertThat(true, is(actual));
	}

	/**
	 * Test of transfer of money.
	 */
	@Test
	public void test2TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		Account cgAccount2 = new Account("LUX reqisites 2", 6_900_000.69);
		bank.addUserAccount(cgPassport, cgAccount2);

		Account cgAccount3 = new Account("LUX reqisites 3", 3_200_000.69);
		bank.addUserAccount(cgPassport, cgAccount3);

		Account cgAccount4 = new Account("LUX reqisites 4", 4_400_000.69);
		bank.addUserAccount(cgPassport, cgAccount4);

		final String asPassport = "AS696969";
		User user2 = new User("Anastasia Steele", asPassport);
		bank.addUser(user2);

		Account asAccount1 = new Account("DELUX reqisites 1", 69.69);
		bank.addUserAccount(asPassport, asAccount1);

		Account asAccount2 = new Account("DELUX reqisites 2", 0.69);
		bank.addUserAccount(asPassport, asAccount2);

		bank.transferMoney(cgPassport, "LUX reqisites 2",
				asPassport, "DELUX reqisites 1", 5_000_000.0);

		double expected = 1_900_000.69;
		double actual = cgAccount2.getValue();

		assertEquals(expected, actual, 0.001);
	}

	/**
	 * Test of transfer of money from one account to another of the same user.
	 * Проверять будем конечную сумму на счету получателя.
	 */
	@Test
	public void test3TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		Account cgAccount2 = new Account("LUX reqisites 2", 6_900_000.69);
		bank.addUserAccount(cgPassport, cgAccount2);

		bank.transferMoney(cgPassport, "LUX reqisites 2",
				cgPassport, "LUX reqisites 1", 500_000.69);

		double expected = 2_000_001.38;
		double actual = cgAccount1.getValue();

		assertEquals(expected, actual, 0.001);
	}

	/**
	 * Test of transfer of money from one account to another of the same user.
	 * Проверять будем оставшуюся сумму на счету отправителя.
	 */
	@Test
	public void test4TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		Account cgAccount2 = new Account("LUX reqisites 2", 6_900_000.69);
		bank.addUserAccount(cgPassport, cgAccount2);

		bank.transferMoney(cgPassport, "LUX reqisites 2",
				cgPassport, "LUX reqisites 1", 500_000.69);

		double expected = 6_400_000.0;
		double actual = cgAccount2.getValue();

		assertEquals(expected, actual, 0.001);
	}

	/**
	 * Test of transfer of money.
	 * Перешлём нулевую суммую.
	 */
	@Test
	public void test5TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		final String asPassport = "AS696969";
		User user2 = new User("Anastasia Steele", asPassport);
		bank.addUser(user2);

		Account asAccount1 = new Account("DELUX reqisites 1", 69.69);
		bank.addUserAccount(asPassport, asAccount1);

		boolean actual = bank.transferMoney(cgPassport, "LUX reqisites 1",
				asPassport, "DELUX reqisites 1", 0.0);
		boolean expected = false;

		assertThat(expected, is(actual));
	}

	/**
	 * Test of transfer of money.
	 * Перешлём суммую большую, чем есть на счету.
	 */
	@Test
	public void test6TransferMoney() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		Account cgAccount1 = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount1);

		final String asPassport = "AS696969";
		User user2 = new User("Anastasia Steele", asPassport);
		bank.addUser(user2);

		Account asAccount1 = new Account("DELUX reqisites 1", 69.69);
		bank.addUserAccount(asPassport, asAccount1);

		boolean actual = bank.transferMoney(cgPassport, "LUX reqisites 1",
				asPassport, "DELUX reqisites 1", 2_000_000.0);
		boolean expected = false;

		assertThat(expected, is(actual));
	}

	/**
	 * Находим пользователя по паспорту.
	 */
	@Test
	public void test1GetUser() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);

		User actual = bank.getUser("CG696969");
		User expected = new User("Christian Grey", cgPassport);

		assertThat(expected, is(actual));
	}

	/**
	 * Проверим поиск пользователя на наличие исключений.
	 *
	 * @throws NoSuchUserException when no user found.
	 */
	@Test
	public void test2GetUser() throws NoSuchUserException {
		String exceptionPassport = "CGXXX";
		expectedException.expect(NoSuchUserException.class);
		expectedException.expectMessage("Пользователь с номером паспорта "
				+ exceptionPassport + " не найден!");

		Bank bank = new Bank();
		final String cgPassport = "CG696969";

		User user1 = new User("Christian Grey", cgPassport);
		bank.addUser(user1);
		bank.getUser(exceptionPassport);
	}

	/**
	 * Проверяем поиск счёта пользователя.
	 */
	@Test
	public void test1GetUserAccount() {
		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user = new User("Christian Grey", cgPassport);
		bank.addUser(user);

		Account cgAccount = new Account("LUX reqisites 1", 1_500_000.69);
		bank.addUserAccount(cgPassport, cgAccount);

		Account actual = bank.getUserAccount(user, "LUX reqisites 1");
		Account expected = new Account("LUX reqisites 1");

		assertThat(expected, is(actual));
	}

	/**
	 * Проверяем поиск счёта пользователя.
	 *
	 * @throws NoSuchUserAccountException when no user account found.
	 */
	@Test
	public void test2GetUserAccount() throws NoSuchUserAccountException {
		expectedException.expect(NoSuchUserAccountException.class);
		expectedException.expectMessage("Счёт с реквизитами 'LUX reqisites 2' "
				+ "у пользователя Christian Grey не найден!");

		Bank bank = new Bank();
		final String cgPassport = "CG696969";
		User user = new User("Christian Grey", cgPassport);
		bank.addUser(user);

		Account cgAccount = new Account("LUX reqisites 1");
		bank.addUserAccount(cgPassport, cgAccount);

		bank.getUserAccount(user, "LUX reqisites 2");
	}
}
