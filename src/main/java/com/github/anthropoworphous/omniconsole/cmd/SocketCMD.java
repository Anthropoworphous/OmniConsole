package com.github.anthropoworphous.omniconsole.cmd;

import com.github.anthropoworphous.cmdlib.arg.analyst.ArgsAnalyst;
import com.github.anthropoworphous.cmdlib.arg.route.IRoute;
import com.github.anthropoworphous.cmdlib.cmd.implementation.CMD;
import com.github.anthropoworphous.omniconsole.OmniConsole;
import com.github.anthropoworphous.omniconsole.jettystuff.SocketServer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.eclipse.jetty.server.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SocketCMD {
    private static Thread serverThread = null;

    public static class StartCMD extends CMD {
        @Override
        public Boolean execute(CommandSender commandSender, ArgsAnalyst argsAnalyst) {
            Server server = new SocketServer().createServer(OmniConsole.getPort());
            serverThread = new Thread(() -> {
                try {
                    server.start();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
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

    public static class StopCMD extends CMD {
        @Override
        public Boolean execute(CommandSender commandSender, ArgsAnalyst argsAnalyst) {
            Optional.ofNullable(serverThread).ifPresentOrElse(
                    thread -> {
                        if (thread.isAlive()) {
                            thread.interrupt();
                            Bukkit.getLogger().info("Server thread interrupted");
                        } else {
                            Bukkit.getLogger().info("Server thread is inactive");
                        }
                    }, () -> Bukkit.getLogger().info("Server thread is inactive")
            );
            return true;
        }

        @Override
        public @Nullable List<IRoute> cmdRoutes() {
            return null;
        }

        @Override
        public @NotNull String cmdName() {
            return "stop-websocket";
        }
    }
}
