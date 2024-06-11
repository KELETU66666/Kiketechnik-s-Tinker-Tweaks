package liketechnik.tinkertweaks.level;

import liketechnik.tinkertweaks.LiketechniksTinkerTweaks;
import liketechnik.tinkertweaks.Tooltips;
import liketechnik.tinkertweaks.util.HarvestLevels;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import static slimeknights.tconstruct.library.utils.Tags.HARVESTLEVEL;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class ToolHarvestLevelNBT extends ModifierNBT {

    public static final String TAG_BOOST_EXP = "head_exp";
    public static final String TAG_IS_BOOSTED = "harvest_level_modified";

    public int bxp;
    public boolean isBoosted;

    public ToolHarvestLevelNBT(NBTTagCompound tag) {
        super(tag);
    }

    @Override
    public void read(NBTTagCompound tag) {
        super.read(tag);
        bxp = tag.getInteger(TAG_BOOST_EXP);
        isBoosted = tag.getBoolean(TAG_IS_BOOSTED);
    }

    @Override
    public void write(NBTTagCompound tag) {
        super.write(tag);
       //int hlvl = tag.getInteger(HARVESTLEVEL);
       ////if(!Config.pickaxeBoostRequired)
       ////    return;
       //if (hlvl == 0/* || !(tool instanceof Pickaxe || tool instanceof Hammer)*/)
       //    return;

        tag.setLong(TAG_BOOST_EXP, bxp);
        tag.setBoolean(TAG_IS_BOOSTED, isBoosted);
    }


    public static void levelUpMiningLevel(NBTTagCompound stack, EntityPlayer player) {
        // fancy message
        if (player != null) {
            if (!player.world.isRemote) {
                player.sendStatusMessage(new TextComponentString(Tooltips.getInfoString(I18n.format("message.levelup.miningboost", stack), TextFormatting.DARK_AQUA, String.format("+%d %s", 1, I18n.format("message.levelup.mininglevel")), TextFormatting.GOLD)), false);
            }
        }

        // increase harvest level by 1
        ToolNBT tnbt = TagUtil.getToolStats(stack);
        tnbt.harvestLevel = tnbt.harvestLevel + 1;
        TagUtil.setToolTag(stack, tnbt.get());
    }
}