import java.util.ArrayList;

public class User {
	User(String fn, String ln){
		Panel.userList.add(this);
		this.firstName = fn;
		this.lastName = ln;
	}
	String firstName;
	String lastName;
	
	ArrayList<Amount> expenses = new ArrayList<>();
	ArrayList<Amount> income = new ArrayList<>();

	public void newIncome() {
		
		//income.add(new Amount(name, amount, discip));
	}
	
	public void removeIncome(Panel panel) {
		
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
