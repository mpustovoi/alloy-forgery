package wraith.alloyforgery.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.resource.conditions.conditions.TagsPopulatedResourceCondition;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import wraith.alloyforgery.data.builders.AlloyForgeryRecipeBuilder;

import java.util.concurrent.CompletableFuture;

import static wraith.alloyforgery.data.AlloyForgeryTags.Items.*;

public class AlloyForgeryRecipeProvider extends FabricRecipeProvider {

    public RecipeExporter exporter;

    public AlloyForgeryRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        this.exporter = exporter;

        createRawBlockRecipe("copper", Items.COPPER_BLOCK, ConventionalItemTags.STORAGE_BLOCKS_RAW_COPPER)
                .offerTo(exporter);

        createRawBlockRecipe("iron", Items.IRON_BLOCK,  ConventionalItemTags.STORAGE_BLOCKS_RAW_IRON)
                .offerTo(exporter);

        createRawBlockRecipe("gold", Items.GOLD_BLOCK, ConventionalItemTags.STORAGE_BLOCKS_RAW_GOLD)
                .offerTo(exporter);

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe,
                "zinc", STORAGE_BLOCKS_RAW_ZINC, STORAGE_BLOCKS_ZINC);

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createExtremeRawBlockRecipe, "tungsten", STORAGE_BLOCKS_RAW_TUNGSTEN, STORAGE_BLOCKS_TUNGSTEN,
                Identifier.of("techreborn:tungsten_block"),
                Identifier.of("indrev:tungsten_block"),
                Identifier.of("modern_industrialization:tungsten_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "titanium", STORAGE_BLOCKS_RAW_TITANIUM, STORAGE_BLOCKS_TITANIUM,
                Identifier.of("techreborn:titanium_block"),
                Identifier.of("modern_industrialization:titanium_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "tin", STORAGE_BLOCKS_RAW_TIN, STORAGE_BLOCKS_TIN,
                Identifier.of("mythicmetals:tin_block"),
                Identifier.of("techreborn:tin_block"),
                Identifier.of("indrev:tin_block"),
                Identifier.of("modern_industrialization:tin_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "silver", STORAGE_BLOCKS_RAW_SILVER, STORAGE_BLOCKS_SILVER,
                Identifier.of("mythicmetals:silver_block"),
                Identifier.of("techreborn:silver_block"),
                Identifier.of("indrev:silver_block"),
                Identifier.of("modern_industrialization:silver_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "platinum", STORAGE_BLOCKS_RAW_PLATINUM, STORAGE_BLOCKS_PLATINUM,
                Identifier.of("mythicmetals:platinum_block"),
                Identifier.of("modern_industrialization:platinum_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createExtremeRawBlockRecipe, "palladium", STORAGE_BLOCKS_RAW_PALLADIUM, STORAGE_BLOCKS_PALLADIUM,
                Identifier.of("mythicmetals:palladium_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "osmium", STORAGE_BLOCKS_RAW_OSMIUM, STORAGE_BLOCKS_OSMIUM,
                Identifier.of("mythicmetals:osmium_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "orichalcum", STORAGE_BLOCKS_RAW_ORICHALCUM, STORAGE_BLOCKS_ORICHALCUM,
                Identifier.of("mythicmetals:orichalcum_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "nickel", STORAGE_BLOCKS_RAW_NICKEL, STORAGE_BLOCKS_NICKEL,
                Identifier.of("techreborn:nickel_block"),
                Identifier.of("modern_industrialization:nickel_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "mythril", STORAGE_BLOCKS_RAW_MYTHRIL, STORAGE_BLOCKS_MYTHRIL,
                Identifier.of("mythicmetals:mythril_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "manganese", STORAGE_BLOCKS_RAW_MANGANESE, STORAGE_BLOCKS_MANGANESE,
                Identifier.of("mythicmetals:manganese_block"),
                Identifier.of("modern_industrialization:manganese_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "lead", STORAGE_BLOCKS_RAW_LEAD, STORAGE_BLOCKS_LEAD,
                Identifier.of("techreborn:lead_block"),
                Identifier.of("indrev:lead_block"),
                Identifier.of("modern_industrialization:lead_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "iridium", STORAGE_BLOCKS_RAW_IRIDIUM, STORAGE_BLOCKS_IRIDIUM,
                Identifier.of("techreborn:iridium_block"),
                Identifier.of("modern_industrialization:iridium_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createStandardRawBlockRecipe, "antimony", STORAGE_BLOCKS_RAW_ANTIMONY, STORAGE_BLOCKS_ANTIMONY,
                Identifier.of("modern_industrialization:antimony_block")
        );

        this.exportWithTagConditions(AlloyForgeryRecipeProvider::createAdvancedRawBlockRecipe, "adamantite", STORAGE_BLOCKS_RAW_ADAMANTITE, STORAGE_BLOCKS_ADAMANTITE,
                Identifier.of("mythicmetals:adamantite_block")
        );
    }

    //-------------------------------------------

    public static AlloyForgeryRecipeBuilder createOverriddenRawBlockRecipe(String materialType, TagKey<Item> output, TagKey<Item> input, int inputAmount, int outputAmount, int overrideIndex, int overrideAmount, int fuelPerTick) {
        return AlloyForgeryRecipeBuilder.create(output, outputAmount)
                .input(input, inputAmount)
                .criterion("has_raw_" + materialType + "_ore_block", conditionsFromTag(input))
                .overrideRange(overrideIndex, true, overrideAmount)
                .setFuelPerTick(fuelPerTick);
    }

    public static AlloyForgeryRecipeBuilder createStandardRawBlockRecipe(String materialType, TagKey<Item> output, TagKey<Item> input) {
        return createOverriddenRawBlockRecipe(materialType, output, input, 2, 3, 2, 4, 45);
    }

    public static AlloyForgeryRecipeBuilder createAdvancedRawBlockRecipe(String materialType, TagKey<Item> output, TagKey<Item> input) {
        return createOverriddenRawBlockRecipe(materialType, output, input, 2, 2, 3, 3, 90)
                .setMinimumForgeTier(2);
    }

    public static AlloyForgeryRecipeBuilder createExtremeRawBlockRecipe(String materialType, TagKey<Item> output, TagKey<Item> input) {
        return createOverriddenRawBlockRecipe(materialType, output, input, 2, 2, 3, 3, 135)
                .setMinimumForgeTier(2);
    }

    //-------------------------------------------

    public static AlloyForgeryRecipeBuilder createRawBlockRecipe(String materialType, Item output, TagKey<Item> input) {
        return AlloyForgeryRecipeBuilder.create(output, 3)
                .input(input, 2)
                .criterion("has_raw_" + materialType + "_ore_block", conditionsFromTag(input))
                .overrideRange(2, true, 4)
                .setFuelPerTick(45);
    }

    //-------------------------------------------

    public void exportWithTagConditions(AFRBuilderMethod builder, String materialType, TagKey<Item> input, TagKey<Item> output, Identifier... priorities) {
        builder.build(materialType, output, input)
                .addPriorityOutput(priorities)
                .offerTo(this.withConditions(this.exporter, new TagsPopulatedResourceCondition(output, input)));
    }

    public interface AFRBuilderMethod {
        AlloyForgeryRecipeBuilder build(String materialType, TagKey<Item> output, TagKey<Item> input);
    }

    //-------------------------------------------
}
