package mhj.expmm.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.common.entities.EntityFluxRift;

/**
 * @Author: ManualHuaJi
 */
public class CommandEXPMM extends CommandBase {
    @Override
    public String getName() {
        return "expmm";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 1;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        float flux = AuraHelper.getFlux(sender.getEntityWorld(), sender.getPosition());
        float size = new EntityFluxRift(sender.getEntityWorld()).getRiftSize();
        sender.sendMessage(new TextComponentString(String.valueOf(flux)));

    }
}
