package wraith.alloyforgery.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Map;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    @Accessor("recipesByType")
    Multimap<RecipeType<?>, RecipeEntry<?>> af$getRecipes();

    @Accessor("recipesByType")
    void af$setRecipes(Multimap<RecipeType<?>, RecipeEntry<?>> recipesByType);

    @Accessor("recipesById")
    Map<Identifier, RecipeEntry<?>> af$getRecipesById();

    @Accessor("recipesById")
    void af$setRecipesById(Map<Identifier, RecipeEntry<?>> recipesById);
}
