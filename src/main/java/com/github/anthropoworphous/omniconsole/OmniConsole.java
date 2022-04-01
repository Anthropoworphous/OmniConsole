package com.github.anthropoworphous.omniconsole;

import com.github.anthropoworphous.cmdlib.processor.CMDRegister;
import com.github.anthropoworphous.omniconsole.cmd.LogStuffCMD;
import com.github.anthropoworphous.omniconsole.cmd.ReloadConfigCMD;
import com.github.anthropoworphous.omniconsole.cmd.SocketCMD;
import org.bukkit.plugin.java.JavaPlugin;

public final class OmniConsole extends JavaPlugin {
    private static JavaPlugin plugin = null;

    public static JavaPlugin getPlugin() {
        return plugin;
    }
    public static int getPort() { return plugin.getConfig().getInt("Port"); }

    @Override
    public void onEnable() {
        //set plugin instance
        plugin = this;

        //config stuff
        saveDefaultConfig();
        reloadConfig();

        //register cmd
        CMDRegister.registerCMD(new LogStuffCMD(), this);
        CMDRegister.registerCMD(new ReloadConfigCMD(), this);
        CMDRegister.registerCMD(new SocketCMD.StartCMD(), this);
        CMDRegister.registerCMD(new SocketCMD.StopCMD(), this);
    }
}
