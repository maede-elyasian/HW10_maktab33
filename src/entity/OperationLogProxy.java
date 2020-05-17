package entity;

import dao.OperationLogDao;

import java.util.Arrays;
import java.util.List;

public class OperationLogProxy implements OperationLib {
    private OperationLogDao operationLogDao;
    private List<OperationLog> userOperations;
    private List<String> operations = Arrays.asList("purchase","delete","login","logOut","add");

    public OperationLogProxy(){
        operationLogDao = new OperationLogDao();
    }

    @Override
    public void productLog(String product, User user, String operation) {
        if (operations.contains(operation)) {
            operationLogDao.productLog(product, user, operation);
        }else {
            operationLogDao.productLog(product,user,"product log error");
        }
    }

    @Override
    public void login(User user, String operation) {
        if (operations.contains(operation)) {
            operationLogDao.login(user, operation);
        }else {
            operationLogDao.login(user,"login/logout error");
        }
    }

    @Override
    public void purchaseLog(User user, double totalPrice, String operation) {
        if (operations.contains(operation)){
            operationLogDao.purchaseLog(user,totalPrice,operation);
        }else {
            operationLogDao.purchaseLog(user,totalPrice,"purchase log error");
        }
    }

}
