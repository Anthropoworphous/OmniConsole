package com.github.anthropoworphous.omniconsole.jettystuff;

import org.bukkit.Bukkit;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class SocketServer {
    public Server createServer(int port) {
        Server server = new Server(port);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(SocketHandler.class);
            }
        };
        server.setHandler(wsHandler);

        Bukkit.getLogger().info("server at: " + server.getURI().getHost() + server.getURI().getPath());

        return server;
    }


}
