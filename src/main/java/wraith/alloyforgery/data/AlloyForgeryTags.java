package wraith.alloyforgery.data;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import wraith.alloyforgery.AlloyForgery;

public class AlloyForgeryTags {

    private static final String COMMON_NAMESPACE = TagUtil.C_TAG_NAMESPACE;

    public static class Items {

        //Zinc
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_ZINC = registerCommon("storage_blocks/raw_zinc");
        public static final TagKey<Item> STORAGE_BLOCKS_ZINC = registerCommon("storage_blocks/zinc");

        //tungsten
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_TUNGSTEN = registerCommon("storage_blocks/raw_tungsten");
        public static final TagKey<Item> STORAGE_BLOCKS_TUNGSTEN = registerCommon("storage_blocks/tungsten");

        //titanium
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_TITANIUM = registerCommon("storage_blocks/raw_titanium");
        public static final TagKey<Item> STORAGE_BLOCKS_TITANIUM = registerCommon("storage_blocks/titanium");

        //tin
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_TIN = registerCommon("storage_blocks/raw_tin");
        public static final TagKey<Item> STORAGE_BLOCKS_TIN = registerCommon("storage_blocks/tin");

        //silver
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SILVER = registerCommon("storage_blocks/raw_silver");
        public static final TagKey<Item> STORAGE_BLOCKS_SILVER = registerCommon("storage_blocks/silver");

        //platinum
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_PLATINUM = registerCommon("storage_blocks/raw_platinum");
        public static final TagKey<Item> STORAGE_BLOCKS_PLATINUM = registerCommon("storage_blocks/platinum");

        //palladium
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_PALLADIUM = registerCommon("storage_blocks/raw_palladium");
        public static final TagKey<Item> STORAGE_BLOCKS_PALLADIUM = registerCommon("storage_blocks/palladium");

        //osmium
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_OSMIUM = registerCommon("storage_blocks/raw_osmium");
        public static final TagKey<Item> STORAGE_BLOCKS_OSMIUM = registerCommon("storage_blocks/osmium");

        //orichalcum
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_ORICHALCUM = registerCommon("storage_blocks/raw_orichalcum");
        public static final TagKey<Item> STORAGE_BLOCKS_ORICHALCUM = registerCommon("storage_blocks/orichalcum");

        //nickel
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_NICKEL = registerCommon("storage_blocks/raw_nickel");
        public static final TagKey<Item> STORAGE_BLOCKS_NICKEL = registerCommon("storage_blocks/nickel");

        //mythril
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_MYTHRIL = registerCommon("storage_blocks/raw_mythril");
        public static final TagKey<Item> STORAGE_BLOCKS_MYTHRIL = registerCommon("storage_blocks/mythril");

        //mythril
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_MANGANESE = registerCommon("storage_blocks/raw_manganese");
        public static final TagKey<Item> STORAGE_BLOCKS_MANGANESE = registerCommon("storage_blocks/manganese");

        //lead
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_LEAD = registerCommon("storage_blocks/raw_lead");
        public static final TagKey<Item> STORAGE_BLOCKS_LEAD = registerCommon("storage_blocks/lead");

        //iridium
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_IRIDIUM = registerCommon("storage_blocks/raw_iridium");
        public static final TagKey<Item> STORAGE_BLOCKS_IRIDIUM = registerCommon("storage_blocks/iridium");

        //antimony
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_ANTIMONY = registerCommon("storage_blocks/raw_antimony");
        public static final TagKey<Item> STORAGE_BLOCKS_ANTIMONY = registerCommon("storage_blocks/antimony");

        //adamantite
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_ADAMANTITE = registerCommon("storage_blocks/raw_adamantite");
        public static final TagKey<Item> STORAGE_BLOCKS_ADAMANTITE = registerCommon("storage_blocks/adamantite");


        private static TagKey<Item> registerCommon(String path) {
            return register(common(path));
        }

        private static TagKey<Item> registerAlloyForgery(String path) {
            return register(alloyForgery(path));
        }

        private static TagKey<Item> register(Identifier id) {
            return TagKey.of(RegistryKeys.ITEM, id);
        }
    }

    private static Identifier common(String path) {
        return Identifier.of(COMMON_NAMESPACE, path);
    }

    private static Identifier alloyForgery(String path) {
        return AlloyForgery.id(path);
    }
}
