package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ClientDetailsDao;
import entity.ClientDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDetailsDaoimpl implements ClientDetailsDao {
    @Override
    public boolean add(ClientDetails client) throws SQLException {
        return CrudUtil.execute("Insert into ClientDetail values(?,?,?,?,?,?,?)" ,client.getFullname(),client.getUsername(),client.getPassword(),client.getEmail(),client.getPhonenumber(),client.getGender(),client.getProfile());

    }

    @Override
    public boolean update(ClientDetails client) throws SQLException {
     return   CrudUtil.execute("Update ClientDetail set FullName=?,Email=?,Gender=?,Profile=?" +
               " where PhoneNumber=?",client.getFullname(),client.getEmail(),
               client.getGender(),client.getProfile(),client.getPhonenumber());
    }

    @Override
    public String search(String password, String username) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("Select UserName from ClientDetail where PassWord=?", password);
        if(resultSet.next()){
            return resultSet.getString("username");
        }
return null;
    }

    @Override
    public ClientDetails getAll(String password, String username ) throws SQLException {
        ResultSet rst = CrudUtil.execute("Select * from ClientDetail where PassWord=?&& UserName=?", password,username);
        while(rst.next()){
        return new ClientDetails(rst.getString(1),rst.getString(2),rst.getString(3),
                rst.getString(4),rst.getString(5),
                rst.getString(6),rst.getString(7));
        }
        return null;
    }
}
