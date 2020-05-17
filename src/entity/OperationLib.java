package entity;

public interface OperationLib {
    void productLog(String product, User user, String operation);

    void login(User user, String operation);

    void purchaseLog(User user, double totalPrice, String operation);
}
