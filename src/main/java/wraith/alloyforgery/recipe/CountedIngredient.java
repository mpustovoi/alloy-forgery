package wraith.alloyforgery.recipe;


import io.wispforest.endec.Endec;
import io.wispforest.endec.format.edm.*;
import net.minecraft.recipe.Ingredient;
import wraith.alloyforgery.utils.EndecUtils;

import java.util.Map;

public record CountedIngredient(Ingredient ingredient, int count) {
    public static Endec<CountedIngredient> ENDEC = EdmEndec.INSTANCE.xmapWithContext((ctx, element) -> {
        var object = element.<Map<String, EdmElement<?>>>cast();

        return new CountedIngredient(
                EndecUtils.INGREDIENT.decode(ctx, EdmDeserializer.of(element)),
                object.containsKey("count") ? object.get("count").<Number>cast().intValue() : 1
        );
    }, (ctx, countedIngredient) -> {
        var element = (EdmElement<Map<String, EdmElement<?>>>) EndecUtils.INGREDIENT.encodeFully(ctx, EdmSerializer::of, countedIngredient.ingredient());

        var count = countedIngredient.count();

        if (count > 1) element.value().put("count", EdmElement.wrapInt(count));

        return element;
    });
}
