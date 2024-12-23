package salek664.lucky_charm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.advancement.criterion.LuckLootContainerCriterion;
import salek664.lucky_charm.item.LuckyCharmItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LuckyCharmAdvancementProvider extends FabricAdvancementProvider {
    public LuckyCharmAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }
    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry a = Advancement.Builder.create()
                .display(
                        LuckyCharmItems.FOURLEAF_CLOVER,
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.four_leaf.title"))
                        ),
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.four_leaf.description"))
                        ),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("clover", InventoryChangedCriterion.Conditions.items(LuckyCharmItems.FOURLEAF_CLOVER))
                .build(consumer, "adventure/four_leaf");
        AdvancementEntry b = Advancement.Builder.create()
                .display(
                        Blocks.CHEST,
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.get_lucky.title"))
                        ),
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.get_lucky.description"))
                        ),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .parent(a)
                .criterion("lucky_loot", LuckLootContainerCriterion.Conditions.create(3))
                .build(consumer, "adventure/get_lucky");
        Advancement.Builder.create()
                .display(
                        LuckyCharmItems.FORTUNE_GEM,
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.extreme_luck.title"))
                        ),
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.extreme_luck.description"))
                        ),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(b)
                .criterion("extreme_luck", LuckLootContainerCriterion.Conditions.create(6))
                .build(consumer, "adventure/extreme_luck");
    }
}
