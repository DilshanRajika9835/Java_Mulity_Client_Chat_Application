package bo;

import bo.custom.SuperBo;
import bo.custom.impl.ClientDetailsBoImpl;

public class BoFactory {
    private  static  BoFactory boFactory;
    private  BoFactory(){

    }
    public  static  BoFactory getInstance(){
        return boFactory==null?(boFactory=new BoFactory()):boFactory;
    }
    public  enum BoType {
        clientDetails
    }
    public <T extends SuperBo> T getBo(BoType type){
        switch (type){
            case clientDetails:return(T) new ClientDetailsBoImpl();
            default:return null;
        }
    }
}
