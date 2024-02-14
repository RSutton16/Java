import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    ArrayList<Amount> income = new ArrayList<>();
    ArrayList<Amount> outcome = new ArrayList<>();

    

    public void addNewIncome(double amount, String name, String description){
        income.add(new Amount(amount, name, description));
    }

    public void addNewOutcome(double amount, String name, String description){
        outcome.add(new Amount(amount, name, description));
    }

    public void getIncome(){
        for (Amount amount : income) {
            System.out.println(amount.getName() + " " + amount.getAmount());
        }
    }

    public void getOutcome(){
        for (Amount amount : income) {
            System.out.println(amount.getName() + " " + amount.getAmount());
        }
    }

    public double getIncomeAmount(){
        double incomeAmount = 0;
        for (Amount amount : income) {
            incomeAmount += amount.getAmount();
        }
        return incomeAmount;
    }

    public double getOutcomeAmount(){
        double outcomeAmount = 0;
        for (Amount amount : outcome) {
            outcomeAmount += amount.getAmount();
        }
        return outcomeAmount;
    }



}
