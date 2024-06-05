package wraith.alloyforgery;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import wraith.alloyforgery.block.ForgeControllerBlock;
import wraith.alloyforgery.forges.ForgeDefinition;
import java.util.List;

public class ForgeControllerItem extends BlockItem {

    public ForgeControllerItem(ForgeControllerBlock block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.alloy_forgery.forge_tier", getForgeDefinition().forgeTier()).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.alloy_forgery.fuel_capacity", getForgeDefinition().fuelCapacity()).formatted(Formatting.GRAY));
    }

    public ForgeDefinition getForgeDefinition() {
        return ((ForgeControllerBlock) getBlock()).forgeDefinition;
    }
}
