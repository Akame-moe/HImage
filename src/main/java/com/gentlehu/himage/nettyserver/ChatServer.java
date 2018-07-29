package com.gentlehu.himage.nettyserver;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

/**
 * Created by gentle-hu on 2018/7/25 5:36.
 * Email:me@gentlehu.com
 */
public class ChatServer {

    public static void main(String[] args) {

        try {
            Configuration config = new Configuration();
            config.setHostname("localhost");
            config.setPort(8888);

            final SocketIOServer server = new SocketIOServer(config);

            server.addEventListener("chat_event", ChatMessage.class, (socketIOClient, chatMessage, ackRequest) -> server.getBroadcastOperations().sendEvent("chat_event",chatMessage));
            server.addConnectListener(new ConnectListener() {
                @Override
                public void onConnect(SocketIOClient client) {
//                    nettyserver.getBroadcastOperations().sendEvent("system_event",ChatMessage.make(0,"system",client.getRemoteAddress().toString()+" join the room."));
                    System.out.println("------->connect");
                }
            });
            server.addDisconnectListener(new DisconnectListener() {
                @Override
                public void onDisconnect(SocketIOClient client) {
//                    nettyserver.getBroadcastOperations().sendEvent("system_event",ChatMessage.make(0,"system",client.getRemoteAddress().toString()+" left the room."));
                    System.out.println("----> disconnect");
                }
            });
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessage("sdfdf");
            server.start();
            System.out.println("nettyserver started.");
            Thread.sleep(600_000);
            server.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
