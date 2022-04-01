package com.github.anthropoworphous.omniconsole.jettystuff;

import org.bukkit.Bukkit;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@WebSocket
public class SocketHandler {
    private static Set<RemoteEndpoint> clientList = new HashSet<>();
    
    public static void sendMsg(String msg) {
        clientList.forEach(client -> {
            try {
                client.sendString(msg);
            } catch (IOException e) { e.printStackTrace(); }
        });
    }









    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        clientList.remove(session.getRemote());
        Bukkit.getLogger().info("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        Bukkit.getLogger().info("Error: " + t.getMessage());
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        session.setIdleTimeout(-1); //never timeout
        try {
            Bukkit.getLogger().info("Connect: " + session.getRemoteAddress().getAddress());
            session.getRemote().sendString("Connect: " + session.getRemoteAddress().getAddress());

            String isSecure = session.isSecure() ? "secure." : "insecure!";
            Bukkit.getLogger().info("The connection is " + isSecure);
            session.getRemote().sendString("The connection is " + isSecure);

            clientList.add(session.getRemote());
            session.getRemote().sendString("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Bukkit.getLogger().info("Message: " + message);
    }
}
