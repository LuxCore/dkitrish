package ru.job4j.banking.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Just simple emulate of bank system.
 */
public class Bank {
	/**
	 * Accounts of user of bank.
	 */
	private Map<User, List<Account>> usersAccounts = new TreeMap<>();

	/**
	 * Gets users accounts.
	 *
	 * @return All users with all theirs accounts.
	 */
	public Map<User, List<Account>> getUsersAccounts() {
		return this.usersAccounts;
	}

	/**
	 * Adds a new user to bank system.
	 * <p>If no argument user in map then he will be added. For approval
	 * we use Map#containsKey() and return opposite result.
	 * <p>It adds only user and he has no accounts.
	 *
	 * @param user user of bank.
	 *
	 * @return Returns true if user was added successfully to bank system
	 *         otherwise false.
	 */
	public boolean addUser(User user) {
		boolean result = this.usersAccounts.containsKey(user);
		this.usersAccounts.putIfAbsent(user, new ArrayList<>());
		return !result;
	}

	/**
	 * Deletes a user from bank system with all his accounts.
	 * <p>If user is in map then it will removed from it, therefore we can
	 * return result using Map#containsKey(). If method throws exception
	 * it will be in line contains <tt>remove</tt> method.
	 * <p>It is sad. Bye bye!
	 *
	 * @param user user of bank.
	 *
	 * @return Returns true if user was deleted successfully from bank system
	 *         otherwise false.
	 */
	public List<Account> deleteUser(User user) {
		return this.usersAccounts.remove(user);
	}

	/**
	 * Adds an account to list of accounts of user.
	 * <p>Searching of user is performing by his passport data.
	 * <p>If same account already exists then returns false.
	 *
	 * @param passport Passport data of user.
	 * @param account New account of user must be added to the list.
	 *
	 * @return Returns true if user account was added otherwise false.
	 */
	public boolean addUserAccount(String passport, Account account) {
		boolean result = false;

		Optional<List<Account>> userAccounts = this.getUserAccounts(passport);
		if (userAccounts.isPresent() && !userAccounts.get().contains(account)) {
			result = userAccounts.get().add(account);
		}

		return result;
	}

	/**
	 * Deletes one user account if it exists.
	 *
	 * @param passport Passport data of user.
	 * @param account Account of user must be deleted from the list.
	 *
	 * @return Returns true if user account was deleted otherwise false.
	 */
	public Account deleteUserAccount(String passport, Account account) {
		Optional<List<Account>> userAccounts = this.getUserAccounts(passport);
		userAccounts.ifPresent(accounts -> accounts.remove(account));
		return account;
	}

	/**
	 * Gets all user accounts.
	 *
	 * @param passport Passport data of user.
	 * @return List of all user accounts.
	 */
	public Optional<List<Account>> getUserAccounts(String passport) {
		Optional<List<Account>> accounts = Optional.empty();
		Optional<Map.Entry<User, List<Account>>> userAccounts = this.usersAccounts.entrySet().stream()
				.filter(entry -> entry.getKey().getPassport().equals(passport))
				.findFirst();
		if (userAccounts.isPresent()) {
			accounts = Optional.of(userAccounts.get().getValue());
		}
		accounts.orElseThrow(() -> new NoSuchUserException(
				"Пользователь по паспорту " + passport + " не найден. \n"
				+ "Проверьте, пожалуйста, правильность введённого номер паспорта.")
		);

		return accounts;
	}

	/**
	 * Transfer of amount of money from user account to another account of the
	 * same user or from one user account to another account of other user.
	 *
	 * @param srcPassport
	 *         Passport data of user from the account of which money
	 *         will be tranferred.
	 * @param srcRequisites
	 *         Requisites of user account from which money will be transferred.
	 * @param destPassport
	 *         Passport data of user to the account of which money
	 *         will be tranferred.
	 * @param destRequisites
	 *         Requisites of user account to which money will be transferred.
	 * @param amount
	 *         Amount of money must be tranferred.
	 *
	 * @return Returns true if money were tranferred succesfully to another
	 * account.
	 */
	public boolean transferMoney(String srcPassport, String srcRequisites,
			String destPassport, String destRequisites, double amount) {

		if (amount <= 0.0) {
			return false;
		}

		if (srcRequisites.equals(destRequisites)) {
			return false;
		}

		Optional<User> srcUser = this.getUser(srcPassport);
		Optional<Account> srcAccount = Optional.empty();
		if (srcUser.isPresent()) {
			srcAccount = this.getUserAccount(srcUser.get(), srcRequisites);
		}
		if (srcAccount.isPresent() && amount > srcAccount.get().getValue()) {
			return false;
		}
		Optional<User> destUser = srcPassport.equals(destPassport)
				? srcUser : this.getUser(destPassport);
		Optional<Account> destAccount = Optional.empty();
		if (destUser.isPresent()) {
			destAccount = this.getUserAccount(destUser.get(), destRequisites);
		}

		boolean result = false;
		if (destAccount.isPresent() && srcAccount.isPresent()) {
			destAccount.get().setValue(destAccount.get().getValue() + amount);
			srcAccount.get().setValue(srcAccount.get().getValue() - amount);
			result = true;
		}
		return result;
	}

	/**
	 * Gets a user by passport.
	 *
	 * @param passport Passport data of user for searching him.
	 *
	 * @return user with required passport.
	 */
	public Optional<User> getUser(String passport) {
		Optional<Map.Entry<User, List<Account>>> accounts = this.usersAccounts.entrySet().stream()
				.filter(entry -> entry.getKey().getPassport().equals(passport))
				.findFirst();
		Optional<User> user = Optional.empty();
		if (accounts.isPresent()) {
			user = Optional.of(accounts.get().getKey());
		}
		user.orElseThrow(() -> new NoSuchUserException("Пользователь с номером паспорта "
				+ passport + " не найден!"));
		return user;
	}

	/**
	 * Gets account of user by account reqisites.
	 *
	 * @param user user of bank.
	 * @param requisites Requisites of account.
	 *
	 * @return Account of user.
	 */
	public Optional<Account> getUserAccount(User user, String requisites) {
		List<Account> accounts = this.usersAccounts.get(user);
		Optional<Account> result;
		result = accounts.stream()
				.filter(account -> account.getRequisites().equals(requisites))
				.findFirst();
		result.orElseThrow(() -> new NoSuchUserAccountException(
				"Счёт с реквизитами '" + requisites
				+ "' у пользователя " + user.getName()
				+ " не найден!")
		);

		return result;
	}
}
