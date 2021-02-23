package dao;

import bd.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public  static <T>T execute(String sql,Object...param) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i <param.length; i++) {
            preparedStatement.setObject((1+i),param[i]);
        }
        if(sql.startsWith("Select")){
            return (T)preparedStatement.executeQuery();
        }
        return ((T)(Boolean)(preparedStatement.executeUpdate()>0));
    }
}
