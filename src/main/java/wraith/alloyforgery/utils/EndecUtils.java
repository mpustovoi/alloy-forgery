package wraith.alloyforgery.utils;

import io.wispforest.owo.serialization.Endec;
import io.wispforest.owo.serialization.format.json.JsonDeserializer;
import io.wispforest.owo.serialization.format.json.JsonEndec;
import io.wispforest.owo.serialization.format.json.JsonSerializer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Pair;

public class EndecUtils {

    public static Endec<ItemStack> ITEM_STACK = Endec.ofCodec(ItemStack.CODEC);
    public static Endec<Ingredient> INGREDIENT = Endec.ofCodec(Ingredient.ALLOW_EMPTY_CODEC);
    public static Endec<FluidVariant> FLUID_VARIANT = Endec.ofCodec(FluidVariant.CODEC).catchErrors((ctx, deserializer, e) -> FluidVariant.blank());
}
