package dao;

import entity.OperationLib;
import entity.OperationLog;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Time.*;

public class OperationLogDao implements OperationLib {
    private static Connection connection = MyConnection.getConnection();
    private static final String insert = "insert into operation_log values(?,?,?,?)";
    private static PreparedStatement preparedStatement;

    @Override
    public void productLog(String product, User user, String operation) {
        String operation2 = operation + " " + product;
        setPreparedStatement(user, operation2);
    }

    @Override
    public void login(User user, String operation) {
        setPreparedStatement(user, operation);
    }

    @Override
    public void purchaseLog(User user, double totalPrice, String operation) {
        String operation2 = operation + " " + String.valueOf(totalPrice);
        setPreparedStatement(user, operation2);
    }

    public List<OperationLog> getAllOperations(int id) {
        String select = "select * from operation_log where user_id=?";
        List<OperationLog> operationLogs = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                operationLogs.add(getAllOperations(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operationLogs;
    }

    private static Time getTime() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter ob = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeformat = localTime.format(ob);
        return valueOf(timeformat);
    }

    private static Date getDate() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }

    private static OperationLog getAllOperations(ResultSet resultSet) {
        OperationLog operationLog = new OperationLog();
        UserDao userDao = new UserDao();
        try {
            int userId = resultSet.getInt("user_id");
            operationLog.setAuthority(userDao.getUserById(userId));
            operationLog.setOperation(resultSet.getString("operation"));
            operationLog.setTime(resultSet.getTime("time").toLocalTime());
            operationLog.setDate(resultSet.getDate("date").toLocalDate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operationLog;
    }

    private void setPreparedStatement(User user, String operation) {
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setDate(1, getDate());
            preparedStatement.setTime(2, getTime());
            preparedStatement.setString(3, operation);
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

