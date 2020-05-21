package model;

public class Customer {
    private String customerName;
    private String customerPassword;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerAddress;

    public Customer() {

    }

    public Customer(String customerName, String customerPassword) {
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public Customer(String name, String password, String phoneNumber, String email, String address) {
        this.customerName = name;
        this.customerPassword = password;
        this.customerPhoneNumber = phoneNumber;
        this.customerEmail = email;
        this.customerAddress = address;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
