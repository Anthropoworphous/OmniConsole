package com.github.anthropoworphous.omniconsole.cmd;

import com.github.anthropoworphous.cmdlib.arg.analyst.ArgsAnalyst;
import com.github.anthropoworphous.cmdlib.arg.route.IRoute;
import com.github.anthropoworphous.cmdlib.arg.route.implementation.Route;
import com.github.anthropoworphous.cmdlib.arg.type.lonetypes.implementation.types.StringVar;
import com.github.anthropoworphous.cmdlib.cmd.implementation.CMD;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogStuffCMD extends CMD {
    @Override
    public Boolean execute(CommandSender commandSender, ArgsAnalyst argsAnalyst) {
        Bukkit.getLogger().info(argsAnalyst.get(0).toString());
        return true;
    }

    @Override
    public @Nullable List<IRoute> cmdRoutes() {
        return List.of(new Route(new StringVar()));
    }

    @Override
    public @NotNull String cmdName() {
        return "log";
    }
}
