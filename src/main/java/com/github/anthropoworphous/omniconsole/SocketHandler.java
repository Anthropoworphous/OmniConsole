package com.github.anthropoworphous.omniconsole;

import com.github.anthropoworphous.cmdlib.arg.analyst.ArgsAnalyst;
import com.github.anthropoworphous.cmdlib.arg.route.IRoute;
import com.github.anthropoworphous.cmdlib.cmd.implementation.CMD;
import express.Express;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SocketHandler {
    public static void start(String ip, int port) {
        //load web stuff with a different class loader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(OmniConsole.class.getClassLoader());
        var app = new Express();
        app.listen(ip, port);

        app.ws("/ws", (ws -> {
            ws.onConnect(client -> {
                OmniConsole.getClients().add(client);
                client.send("Connected to " + ip);
            });
            ws.onClose(client -> {
                OmniConsole.getClients().remove(client);
                client.send("Disconnected from " + ip);
            });
        }));

        Bukkit.getLogger().info("Opened web socket at: " + ip + " with port: " + port);

        //reset class loader
        Thread.currentThread().setContextClassLoader(classLoader);
    }

    public static class StartCMD extends CMD {
        @Override
        public Boolean execute(CommandSender commandSender, ArgsAnalyst argsAnalyst) {
            int port = OmniConsole.getPlugin().getConfig().getInt("PORT");
            if (port == 0) { port = 42069; }
            start(Optional.ofNullable(
                    OmniConsole.getPlugin()
                            .getConfig()
                            .getString("IP"))
                            .orElse(
                                    OmniConsole.getPlugin()
                                            .getServer()
                                            .getIp()),
                    port
            );
            return true;
        }

        @Override
        public @Nullable List<IRoute> cmdRoutes() {
            return null;
        }

        @Override
        public @NotNull String cmdName() {
            return "host-websocket";
        }
    }
}
