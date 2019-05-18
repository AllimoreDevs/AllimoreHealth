package taurasi.marc.allimorehealth.allimorehealth.ScalingHealth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import taurasi.marc.allimorehealth.allimorehealth.Allimorehealth;

import java.util.logging.Level;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Get Out if the command is not in use
        if( !(command.getName().equalsIgnoreCase("SetMaxHealth")) ) return false;

        int newMaxHp = ReadMaxHp(args, sender);
        if(newMaxHp == 0){return false;}


        // Check if a player name was provided
        if(args.length < 2){
            return SetMaxHpOnSender(sender, newMaxHp);
        }else{
            return SetMaxHpOnProvidedPlayer(sender, args, newMaxHp);
        }
    }

    private int ReadMaxHp(String[] args, CommandSender sender){
        if(args.length < 1){
            LogError(sender,"You have to specify an amount of hp to set the max to!");
            return 0;
        }
        try{
            return Integer.parseInt(args[0]);
        }catch (NumberFormatException e) {
            LogError(sender, "Incorrect Command Usage! Hp amount must be provided as a number without decimals!");
            return 0;
        }
    }

    private boolean SetMaxHpOnSender(CommandSender sender, int newMaxHp){
        if(sender instanceof Player){
            Player player = (Player)sender;
            Allimorehealth.INSTANCE.GetScalingHealthManager().SetPlayerHealth(player, newMaxHp);
            sender.sendMessage(player.getDisplayName() + " Max Hp set to: " + newMaxHp);
            return true;

        }else{
            LogError(sender,"You have to a specify a player to modify when running this command from the console!");
            return false;
        }
    }

    private boolean SetMaxHpOnProvidedPlayer(CommandSender sender, String[] args, int newMaxHp){
        // Player name was provided
        Player player = Allimorehealth.INSTANCE.getServer().getPlayer(args[1]);
        if(player == null){
            LogError(sender,"Could not find player!");
            return false;
        }

        Allimorehealth.INSTANCE.GetScalingHealthManager().SetPlayerHealth(player, newMaxHp);
        sender.sendMessage(player.getDisplayName() + " Max Hp set to: " + newMaxHp);
        return true;
    }

    private void LogError(CommandSender sender, String message){
        Allimorehealth.INSTANCE.getLogger().log(Level.INFO, message);
        sender.sendMessage(message);
    }
}
