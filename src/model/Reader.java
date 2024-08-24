/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 *
 * @author Thang
 */
public class Reader extends Person {
    private String cardID; 

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    private static final String EXCHANGE_NAME = "notifications";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public Reader() throws Exception {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    }

    public Reader(String cardID, String name, String dob, String address, String gender, 
                  String phoneNumber, String email, String idNumber, String password) {
        super(name, dob, address, gender, phoneNumber, email, idNumber, password);
        this.cardID = cardID;

        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBookRequest() throws Exception {
        String message = "sendBookRequest";
        channel.basicPublish(EXCHANGE_NAME, "sendBookRequest", null, message.getBytes("UTF-8"));
    }

    public void cancelBookRequest() throws Exception {
        String message = "cancelBookRequest";
        channel.basicPublish(EXCHANGE_NAME, "cancelBookRequest", null, message.getBytes("UTF-8"));
    }

    public void extendReaderCardRequest() throws Exception {
        String message = "extendReaderCard";
        channel.basicPublish(EXCHANGE_NAME, "extendReaderCard", null, message.getBytes("UTF-8"));
    }

    public void makeAReaderCardRequest() throws Exception {
        String message = "makeAReaderCardRequest";
        channel.basicPublish(EXCHANGE_NAME, "makeAReaderCardRequest", null, message.getBytes("UTF-8"));
    }
}
