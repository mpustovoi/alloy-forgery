package wraith.alloyforgery.utils;

import io.wispforest.endec.Endec;
import io.wispforest.owo.serialization.CodecUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.recipe.Ingredient;

public class EndecUtils {
    public static Endec<Ingredient> INGREDIENT = CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC);
    public static Endec<FluidVariant> FLUID_VARIANT = CodecUtils.toEndec(FluidVariant.CODEC).catchErrors((ctx, deserializer, e) -> FluidVariant.blank());
}
