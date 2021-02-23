package controller;

import animatefx.animation.FadeIn;
import bo.BoFactory;
import bo.custom.ClientDetailsBo;
import bo.custom.SuperBo;
import dto.ClientDetailsDTO;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {


    public AnchorPane root;
    public Pane loginpane;
    public Button btnsingUp;
    public TextField txtusername;
    public PasswordField txtpassword;
    public Label lblloginwarning;
    public Pane singuppane;
    public Label lblwarning;
    public Label lblconform1;
    public Label lblconform2;
    public TextField txtfullname;
    public TextField txtregusername;
    public TextField txtemail;
    public TextField txtphonenumber;
    public PasswordField txtregpassword;
    public RadioButton radiomale;
    public RadioButton radiofemale;
    public String gender;
    ClientDetailsBo clientDetailsBo = BoFactory.getInstance().getBo(BoFactory.BoType.clientDetails);
    public  void initialize(){
        loginpane.setVisible(true);
        singuppane.setVisible(false);
        lblconform1.setVisible(false);
        lblconform2.setVisible(false);
        lblwarning.setVisible(false);
        lblloginwarning.setVisible(false);
    }

    public void btnloginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtusername.getText().length()>0&&txtpassword.getText().length()>0){
            if(CheckLogin()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChatDashBoard.fxml"));
                Parent root =loader.load();
                ChatDashBoardController chatDashBoardController= loader.getController();
                chatDashBoardController.Details(txtusername.getText(),txtpassword.getText());
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.getIcons().add(new Image("/assert/icon/whatsapp.png"));
                stage.show();
                Stage stage1=(Stage) txtusername.getScene().getWindow();
                stage1.close();
            }
        }else {
           lblloginwarning.setVisible(true);
        }

    }

    private boolean CheckValidation() {
        if(Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$").matcher(txtfullname.getText()).matches()){
            if(Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$").matcher(txtregusername.getText()).matches()){
                if(Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$").matcher(txtregpassword.getText()).matches()){
                    if(Pattern.compile("^([A-z]|[0-9]){1,}[@]{1}[A-z]{1,}.[A-z]{1,}$").matcher(txtemail.getText()).matches()){
                        if(Pattern.compile("^[0-9]{10}$").matcher(txtphonenumber.getText()).matches()){
                            if(radiofemale.isSelected()||radiomale.isSelected()){
                                    if(radiomale.isSelected()){
                                       gender="Male";
                                        }else {
                                        gender="Female";
                                         }
                                lblwarning.setVisible(false);
                                      return true;
                            }else {
                                lblwarning.setText("select your gender!");
                            }
                        }else {
                            lblwarning.setText("invalid invalid phone number!");
                        }
                    }else {
                        lblwarning.setText("invalid email address");
                    }
                }else {
                    lblwarning.setText("password is not strong!");
                }
            }else {
                lblwarning.setText("invalid user name");
            }
        }else {
            lblwarning.setText("invalid Full Name");
        }
        lblwarning.setVisible(true);
        return false;
    }

    public void btnBackOnAction(MouseEvent mouseEvent) {
        loginpane.setVisible(true);
        singuppane.setVisible(false);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), loginpane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    private  boolean CheckLogin(){
        try {
            String    searchUsername = clientDetailsBo.search(txtpassword.getText(), txtusername.getText());
        if(searchUsername!=null){
                 return true;
            }else {
            lblloginwarning.setVisible(true);
        }
        } catch (Exception ex) {
            lblloginwarning.setVisible(true);
        }
        return false;
    }

    public void btnloginHereOnAction(ActionEvent actionEvent) {
        loginpane.setVisible(true);
        singuppane.setVisible(false);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), loginpane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void btnCreateAnAccountOnAction(ActionEvent actionEvent) {
        boolean added=false;
        if(CheckValidation()){
            try {
                added = clientDetailsBo.add(new ClientDetailsDTO(txtfullname.getText(), txtregusername.getText(),
                        txtregpassword.getText(), txtemail.getText(), txtphonenumber.getText(), gender, "no"));
            } catch (SQLException ex) {
               switch (ex.getErrorCode()){
                   case 1062:{
                       lblwarning.setText("this phone number already exists!");
                       lblwarning.setVisible(true);
                   }
               }
            }
        }
        if(added){
            lblconform1.setVisible(true);
            lblconform2.setVisible(true);
        }
    }

    public void btnSingUpOnAction(ActionEvent actionEvent) {
        loginpane.setVisible(false);
        singuppane.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), singuppane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
