package controller;

import bo.BoFactory;
import bo.custom.ClientDetailsBo;
import dto.ClientDetailsDTO;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ChatDashBoardController extends Thread implements Initializable {
    public Pane OptionPane;
    public ImageView lblcamer;
    public Circle profilePhoto;
    public Label lblname;
    public Label lblstatus;
    public TextArea txtmessageBox;
    public ImageView lblcall;
    public Label lblpath;
    public Label lblfulname;
    public Label lblemail;
    public Label lblphonenumber;
    public Label lblgender;
    public ImageView profileimage;
    public ImageView profileimage1;
    public Pane viewemojipane;
    String username;
    public TextArea txtmessagesend;
    public TextFlow textemojipanel;

    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket socket;
    String password;
    ClientDetailsBo clientDetailsBo = BoFactory.getInstance().getBo(BoFactory.BoType.clientDetails);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OptionPane.setVisible(false);
        viewemojipane.setVisible(false);
        connectSocket();
        loadimoji();
    }
    private void loadimoji() {
        EmojiList emojiList = new EmojiList();

        for (int i = 0; i < emojiList.emoji.size(); i++) {
            Button mylabel = new Button(emojiList.getimoji(i));
            mylabel.setCursor(Cursor.cursor("hand"));
            mylabel.setStyle("-fx-background-color: transparent");
            textemojipanel.getChildren().add(mylabel);
            mylabel.setOnMouseClicked((e) -> {
                txtmessagesend.appendText(mylabel.getText());
            });
        }
    }
    public  void Details(String username,String password){
        this.username=username;
        this.password=password;
        this.lblname.setText(username);
        loaddetails();

    }

    private void loaddetails() {
        try {
            ClientDetailsDTO detail = clientDetailsBo.getAll(password,username);
            lblfulname.setText(detail.getFullname());
            lblemail.setText(detail.getEmail());
            lblphonenumber.setText(detail.getPhonenumber());
            lblgender.setText(detail.getGender());
            if(detail.getProfile().length()>5){
                lblpath.setText(detail.getProfile());
            }else {
                lblpath.setText("src\\assert\\icon\\user.png");
            }
            setProfile(lblpath.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setProfile(String path) {

        try {
            BufferedImage bufferedImage  = ImageIO.read(new File(path));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            profileimage.setImage(image);
            profilePhoto.setFill(new ImagePattern(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnMessageSendOnAction(MouseEvent mouseEvent) {
        String msg = txtmessagesend.getText();
        printWriter.println(this.username + ": " + msg);
        txtmessageBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtmessageBox.appendText("you: " + msg + "\n");
        txtmessagesend.clear();
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }


    }


    public void btnOptionOnAction(MouseEvent mouseEvent) {
        OptionPane.setVisible(true);
    }

    public void btnChooseOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        File filePath = fileChooser.showOpenDialog(stage);
        String path=filePath.getAbsolutePath();
        String replacepath=path.replace("\\","\\\\");
        lblpath.setText(replacepath);
        Updateprofile(replacepath);

        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            profileimage.setImage(image);
            profilePhoto.setFill(new ImagePattern(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void Updateprofile(String path) {
        try {
            clientDetailsBo.update(new ClientDetailsDTO(lblfulname.getText(), lblemail.getText(),
                    lblphonenumber.getText(), lblgender.getText(), path));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnBackOnAction(MouseEvent mouseEvent) {
        OptionPane.setVisible(false);
    }



    private void connectSocket() {
        try {
            socket=new Socket("localhost",8889);
            System.out.println("Socket is connected with server!");
           bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           printWriter=new PrintWriter(socket.getOutputStream(),true);
            this.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void run() {
        try {
     while(true){
         String msg= bufferedReader.readLine();
         String[] tokens = msg.split(" ");
         String cmd = tokens[0];
         StringBuilder fulmsg = new StringBuilder();
         for(int i = 0; i < tokens.length; i++) {
             fulmsg.append(tokens[i]);
         }
         if (cmd.equalsIgnoreCase( this.username+ ":")) {
             continue;
         } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
             break;
         }
             txtmessageBox.appendText(msg+"\n");
     }
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void btnViewEmojiOnAction(MouseEvent mouseEvent) {
        viewemojipane.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), viewemojipane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void btnCancelOnAction(MouseEvent mouseEvent) {
        viewemojipane.setVisible(false);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), viewemojipane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
