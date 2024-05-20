package bankGPT;

class Account {
    private String name;
    private String accountNumber;
    private String balance;

    public Account(String name, String accountNumber, String balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + ":" + accountNumber + " : Rs " + balance;
    }
}
