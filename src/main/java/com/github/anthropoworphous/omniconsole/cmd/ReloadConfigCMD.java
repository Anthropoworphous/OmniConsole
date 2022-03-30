package com.github.anthropoworphous.omniconsole.cmd;

import com.github.anthropoworphous.cmdlib.arg.analyst.ArgsAnalyst;
import com.github.anthropoworphous.cmdlib.arg.route.IRoute;
import com.github.anthropoworphous.cmdlib.cmd.implementation.CMD;
import com.github.anthropoworphous.omniconsole.OmniConsole;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadConfigCMD extends CMD {
    @Override
    public Boolean execute(CommandSender commandSender, ArgsAnalyst argsAnalyst) {
        OmniConsole.getPlugin().reloadConfig();
        return true;
    }

    @Override
    public @Nullable List<IRoute> cmdRoutes() {
        return null;
    }

    @Override
    public @NotNull String cmdName() {
        return "reload-config";
    }
}
