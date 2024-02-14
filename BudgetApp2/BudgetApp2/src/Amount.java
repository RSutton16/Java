
public class Amount {

    private double amount;
    private String name;
    private String description;
  
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Amount(double amount, String name, String description) {
        this.amount = amount;
        this.name = name;
        this.description = description;
    }

}
