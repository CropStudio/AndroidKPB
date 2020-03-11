package com.example.app4g.server.rmq;

import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendRmq {
    private static final String EXCHANGE_NAME = "logs";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] argv) throws Exception {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUri("amqp://iot_pertanian:iotpertanian@167.205.7.226:5672//iotpertanian");
        factory.setUsername("iot_pertanian");
        factory.setPassword("iotpertanian");
        factory.setVirtualHost("/iotpertanian");
        factory.setHost("167.205.7.226");
        factory.setPort(5672);


        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = argv.length < 1 ? "info: Hello World!" :
                    String.join(" ", argv);

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
