package pl.edu.agh.iisg.to.javafx.cw4.model.generator;

import java.math.BigDecimal;
import java.time.LocalDate;

import pl.edu.agh.iisg.to.javafx.cw4.model.Account;
import pl.edu.agh.iisg.to.javafx.cw4.model.Category;
import pl.edu.agh.iisg.to.javafx.cw4.model.Transaction;

public class DataGenerator {

	private static Category income = new Category("Income");
	private static Category groceries = new Category("Groceries");
	private static Category fuel = new Category("Fuel");
	
	public final static Account generateAccountData() {
		Account account = new Account("Personal account");
		
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 24), "My company", income, BigDecimal.valueOf(1000L)));
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 25), "Greenrocesr's", groceries, BigDecimal.valueOf(973L)));
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 26), "Petrol station", fuel,BigDecimal.valueOf(727L)));
		
		return account;
	}
	
	public final static Transaction generateTransaction() {
		return new Transaction(LocalDate.of(2015, 8, 24), "My company 1", income,  BigDecimal.valueOf(1000L));
	}
}
