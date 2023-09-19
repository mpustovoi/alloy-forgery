package wraith.alloyforgery.recipe.handlers;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import wraith.alloyforgery.forges.ForgeDefinition;
import wraith.alloyforgery.recipe.AlloyForgeRecipe;

import java.util.*;
import java.util.function.Consumer;

public class BlastFurnaceRecipeHandler extends ForgeRecipeHandler<BlastingRecipe> {

    private final SingleSlotInventory emulatedInv = new SingleSlotInventory();

    @Override
    public Optional<BlastingRecipe> gatherRecipe(RecipeContext context) {
        Optional<BlastingRecipe> possibleRecipe = Optional.empty();

        for (int i = 0; i < 10; i++) {
            possibleRecipe = context.world().getRecipeManager()
                    .getFirstMatch(RecipeType.BLASTING, emulatedInv.changeIndex(i, context.inventory()), context.world())
                    .filter(BlastFurnaceRecipeHandler::notBlacklisted);

            if (possibleRecipe.isPresent()) break;
        }

        return possibleRecipe;
    }

    @Override
    public ItemStack recipeOutput(RecipeContext context, BlastingRecipe recipe) {
        var stack = recipe.getOutput(context.world().getRegistryManager()).copy();

        //if(context.forgeDefinition().forgeTier() >= 3) stack.increment(1);

        return stack;
    }

    @Override
    public float getFuelRequirement(RecipeContext context) {
        return getFuelPerTick(this.lastRecipe.get()) * context.forgeDefinition().speedMultiplier();
    }

    public static float getFuelPerTick(BlastingRecipe recipe){
        return ((recipe.getCookTime() / (float) ForgeDefinition.BASE_MAX_SMELT_TIME) * 10);
    }

    @Override
    public void craftRecipe(RecipeContext context, Consumer<DefaultedList<ItemStack>> remainderConsumer) {
        var inputStack = emulatedInv.getStack(0);

        inputStack.decrement(recipeOutput(context, this.lastRecipe.get()).getCount());

        if (inputStack.isEmpty()) emulatedInv.setStack(0, inputStack);

        super.craftRecipe(context, remainderConsumer);
    }

    private static class SingleSlotInventory implements Inventory {

        public int index = 0;
        public Inventory wrappedInventory = new SimpleInventory();

        public SingleSlotInventory(){}

        public SingleSlotInventory changeIndex(int index, Inventory wrappedInventory){
            this.index = index;
            this.wrappedInventory = wrappedInventory;

            return this;
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return this.wrappedInventory.getStack(index).isEmpty();
        }

        @Override
        public ItemStack getStack(int slot) {
            return this.wrappedInventory.getStack(index);
        }

        @Override
        public ItemStack removeStack(int slot, int amount) {
            return this.wrappedInventory.removeStack(index, amount);
        }

        @Override
        public ItemStack removeStack(int slot) {
            return this.wrappedInventory.removeStack(index);
        }

        @Override
        public void setStack(int slot, ItemStack stack) {
            this.wrappedInventory.setStack(index, stack);
        }

        @Override
        public void markDirty() {
            this.wrappedInventory.markDirty();
        }

        @Override
        public boolean canPlayerUse(PlayerEntity player) {
            return this.wrappedInventory.canPlayerUse(player);
        }

        @Override
        public void clear() {}
    }

    private static final Set<Identifier> BLACKLIST_BLASTING_RECIPES = new HashSet<>();

    public static void initEvents(){
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            onDatapackload(server.getRecipeManager());
        });

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            onDatapackload(server.getRecipeManager());
        });
    }

    public static void onDatapackload(RecipeManager manager){
        BLACKLIST_BLASTING_RECIPES.clear();

        List<Recipe<?>> alloyForgeryRecipes = manager.values().stream()
                .filter(recipe -> recipe.getType() == AlloyForgeRecipe.Type.INSTANCE)
                .toList();

        for (Recipe<?> recipe : manager.values()) {
            if(recipe.getType() == RecipeType.BLASTING && !isUniqueRecipe(alloyForgeryRecipes, recipe)){
                BLACKLIST_BLASTING_RECIPES.add(recipe.getId());
            }
        }
    }

    public static boolean isUniqueRecipe(List<Recipe<?>> alloyForgeryRecipes, Recipe<?> blastFurnaceRecipe){
        List<Ingredient> ingredients = blastFurnaceRecipe.getIngredients();

        for (Ingredient ingredient : ingredients) {
            ItemStack[] stacks = ingredient.getMatchingStacks();

            List<Recipe<?>> matchedRecipes = alloyForgeryRecipes.stream()
                    .filter(recipe1 -> {
                        for (Ingredient recipe1Ingredient : recipe1.getIngredients()) {
                            for (ItemStack stack : stacks) {
                                if(recipe1Ingredient.test(stack)){
                                    return true;
                                }
                            }
                        }

                        return false;
                    }).toList();

            if(!matchedRecipes.isEmpty()) return false;
        }

        return true;
    }

    public static boolean notBlacklisted(Recipe<?> recipe){
        return !BLACKLIST_BLASTING_RECIPES.contains(recipe.getId());
    }
}