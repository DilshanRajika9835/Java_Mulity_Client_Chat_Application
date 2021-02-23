package dao.custom;;
import dao.custom.impl.ClientDetailsDaoimpl;

public class DaoFactory {
    private  static DaoFactory daoFactory;
    private  DaoFactory(){

    }
    public  static  DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }
    public  enum DaoType {
        clientDetails
    }
    public <T> T getdao(DaoType type){
        switch (type){
            case clientDetails:return (T) new ClientDetailsDaoimpl();
            default:return null;
        }
    }
}
