import java.util.ArrayList;

public class User {
	User(String fn, String ln){
		Panel1.userList.add(this);
		this.firstName = fn;
		this.lastName = ln;
	}
	
	int incomePerWeek = 0;
	int outcomePerWeek = 0;
	String firstName;
	String lastName;
	
	ArrayList<Amount> expenses = new ArrayList<>();
	ArrayList<Amount> income = new ArrayList<>();

	public void newIncome(int amount, String name, String discip) {
		
		income.add(new Amount(name, amount, discip));
		System.out.println(income.get(0).name);
	}
	
	public void removeIncome(String name) {
		
	}
	
	public void clearIncome() {
		
	}
	
	public void viewAllIncome() {
		for(Amount income: income) {
			System.out.println(income.name + ": " +income.amount);
		}
	}
	
	public void newExpenses(String name, double amount, String discip) {
		expenses.add(new Amount(name, amount, discip));
	}
	
	public void viewAllExpenses() {
		double totalOutcome= 0;
		for(Amount expenses: expenses) {
			System.out.println(expenses.name + ": " +expenses.amount);
			totalOutcome += expenses.amount;
		}
		System.out.println("Total: " + totalOutcome);
	}
	
	public void changeName(String fn, String ln) {
		this.firstName = fn;
		this.lastName = ln;
	}
	
}
