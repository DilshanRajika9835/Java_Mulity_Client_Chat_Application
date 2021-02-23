package dao.custom;
import dto.ClientDetailsDTO;
import entity.ClientDetails;

import java.sql.SQLException;

public interface ClientDetailsDao {
    public  boolean add(ClientDetails clientDetails) throws SQLException;
    public  boolean update( ClientDetails clientDetails) throws SQLException;
    public  String search(String password,String username) throws SQLException;
    public  ClientDetails getAll(String password, String username) throws SQLException;
}
