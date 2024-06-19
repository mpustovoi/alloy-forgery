package wraith.alloyforgery.recipe;


import io.wispforest.endec.Endec;
import io.wispforest.endec.format.edm.*;
import io.wispforest.endec.impl.KeyedEndec;
import net.minecraft.recipe.Ingredient;
import wraith.alloyforgery.utils.EndecUtils;

public record CountedIngredient(Ingredient ingredient, int count) {
    public static final KeyedEndec<Integer> COUNT_KEY = Endec.INT.keyed("count", 1);

    public static Endec<CountedIngredient> ENDEC = EdmEndec.MAP.xmapWithContext((ctx, element) -> {
        return new CountedIngredient(
                EndecUtils.INGREDIENT.decode(ctx, EdmDeserializer.of(element)),
                element.get(COUNT_KEY)
        );
    }, (ctx, countedIngredient) -> {
        var element = EndecUtils.INGREDIENT.encodeFully(ctx, EdmSerializer::of, countedIngredient.ingredient()).asMap();

        var count = countedIngredient.count();

        if (count > 1) element.put(COUNT_KEY, count);

        return element;
    });
}
