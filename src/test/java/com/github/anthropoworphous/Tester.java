package com.github.anthropoworphous;

import express.Express;
import io.javalin.websocket.WsContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tester {
    @Test
    public void test() throws InterruptedException {
        Express app = new Express();
        String ip = "localhost";
        int port = 420;
        List<WsContext> clients = new ArrayList<>();

        app.ws("/ws", (ws -> {
            ws.onConnect(client -> {
                clients.add(client);
                client.send("Connected to " + ip);
            });
            ws.onClose(client -> {
                clients.remove(client);
                client.send("Disconnected from " + ip);
            });
        }));

        app.listen(ip, port);

        while (clients.size() < 1) {
            TimeUnit.SECONDS.sleep(1);
        }

        clients.forEach(c -> c.send("test"));
    }
}
