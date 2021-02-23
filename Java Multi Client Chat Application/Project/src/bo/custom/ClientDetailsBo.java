package bo.custom;

import dto.ClientDetailsDTO;

import java.sql.SQLException;

public interface ClientDetailsBo extends SuperBo {
    public  boolean add(ClientDetailsDTO clientDetailsDTO) throws SQLException;
    public  boolean update(ClientDetailsDTO clientDetailsDTO) throws SQLException;
    public  String search(String password,String username) throws SQLException;
    public  ClientDetailsDTO getAll(String password, String username) throws SQLException;
}
