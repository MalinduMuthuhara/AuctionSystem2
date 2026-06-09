package edu.ijse.fx.auctionsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private Button emojiBtn;

    @FXML
    private TextField highestPriceTxt;

    @FXML
    private TextField itemNameTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button sendBtn;

    @FXML
    private TextArea serverReceiveMessage;

    @FXML
    private TextField serverSendMessage;

    private ServerSocket serverSocket;
    private Socket localSocket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String message = "";

    public void initialize(){
        new Thread(()->{
            try{
                serverSocket = new ServerSocket(6000);
                serverReceiveMessage.appendText("Waiting For Client.....\n");
                localSocket = serverSocket.accept();
                serverReceiveMessage.appendText("Client Connected\n");

                dataInputStream = new DataInputStream(localSocket.getInputStream());
                dataOutputStream = new DataOutputStream(localSocket.getOutputStream());

                dataOutputStream.writeUTF("[Item : VintageWatch | Starting Price : LKR 5,000]");
                dataOutputStream.flush();

                while (true){
                    message = dataInputStream.readUTF();
                    serverReceiveMessage.appendText("Client Bid : " + message + "\n");
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    @FXML
    void navigateEmojiBtn(ActionEvent event){
        String emoji = "\uD83D\uDE02";
        try {
            dataOutputStream.writeUTF(emoji);
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void navigateServerSend(ActionEvent event) {

        try{
            dataOutputStream.writeUTF(itemNameTxt.getText());
            dataOutputStream.writeUTF(priceTxt.getText());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
