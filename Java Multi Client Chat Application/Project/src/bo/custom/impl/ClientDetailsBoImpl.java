package bo.custom.impl;

import bo.custom.ClientDetailsBo;
import dao.custom.ClientDetailsDao;
import dao.custom.DaoFactory;
import dto.ClientDetailsDTO;
import entity.ClientDetails;

import java.sql.SQLException;

public class ClientDetailsBoImpl  implements ClientDetailsBo {
    ClientDetailsDao clientDetailsDao= DaoFactory.getInstance().getdao(DaoFactory.DaoType.clientDetails);
    @Override
    public boolean add(ClientDetailsDTO dto) throws SQLException {
     return   clientDetailsDao.add(new ClientDetails(dto.getFullname(),dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getPhonenumber(),dto.getGender(),dto.getProfile()));
    }

    @Override
    public boolean update(ClientDetailsDTO dto) throws SQLException {
      return   clientDetailsDao.update(new ClientDetails(dto.getFullname(),dto.getEmail(),dto.getPhonenumber(),dto.getGender(),dto.getProfile()));
    }

    @Override
    public String search(String password, String username) throws SQLException {
        return clientDetailsDao.search(password,username);
    }

    @Override
    public ClientDetailsDTO getAll(String password, String username) throws SQLException {
        ClientDetails client = clientDetailsDao.getAll(password, username);
        return   new ClientDetailsDTO(client.getFullname(),client.getUsername(),client.getPassword(),client.getEmail(),client.getPhonenumber(),client.getGender(),client.getProfile());
    }
}
