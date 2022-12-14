package de.starvalcity.starvaleconomy.commands;

import de.starvalcity.starvaleconomy.Core;
import de.starvalcity.starvaleconomy.Corebase;
import de.starvalcity.starvaleconomy.handling.InterfaceHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PayDayCommand implements CommandExecutor {

    private static Corebase corebase = new Corebase();

    private Core plugin;

    public PayDayCommand(Core plugin) {
            this.plugin = plugin;
        }

        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // /payday
                if (args.length == 0) {

                    InterfaceHandler.openAdminPayDayGUI(player);

                    // /payday stop
                } else if (args[0].equals("stop")) {
                    corebase.getPayDayHandler().stopPayDay();

                    // /payday setInterval <Intervall>
                } else if (args[0].equals("setInterval")) {
                    try {
                        corebase.getPayDayHandler().setInterval(Integer.parseInt(args[1]));
                    } catch (NumberFormatException e) {
                        player.sendMessage("Bitte gib eine gültige Anzahl in Sekunden an!");
                    }

                    // /payday resume
                } else if (args[0].equals("resume")) {
                    corebase.getPayDayHandler().resumePayDay();
                } else {
                    player.sendMessage("Befehl ungültig!");
                }
                return true;
            }
            return false;
        }
}