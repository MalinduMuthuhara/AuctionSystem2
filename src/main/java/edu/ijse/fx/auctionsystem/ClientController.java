package edu.ijse.fx.auctionsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {

    @FXML
    private Button bidTxt;

    @FXML
    private TextField clientBidTxt;
    @FXML
    private TextArea clientReceiveMessage;
    @FXML
    private TextField clientSendMessage;

    @FXML
    private Button emojiBtn;
    @FXML
    private Button sendBtn;

    private Socket remoteSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String message = "";

    @FXML
    public void initialize(){
        new Thread(()->{
            try{
                remoteSocket  = new Socket("127.0.0.1",6000);
                dataInputStream = new DataInputStream(remoteSocket.getInputStream());
                dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

                while(true){
                    message = dataInputStream.readUTF();
                    clientReceiveMessage.appendText("Right Now We Are Bidding For :::::" + message + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    @FXML
    void navigateEmojiBtn(ActionEvent event) {
        String emoji = "☠\uFE0F" ;
        try{
            dataOutputStream.writeUTF(emoji);
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void navigateSendBid(ActionEvent event) {
        try{
            dataOutputStream.writeUTF(clientBidTxt.getText());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void navigateSendBtn(ActionEvent event) {
        try{
            dataOutputStream.writeUTF(clientSendMessage.getText());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
