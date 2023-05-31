package net.merchantpug.toomanyorigins.badge;

import com.google.auto.service.AutoService;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.origins.badge.Badge;
import net.merchantpug.toomanyorigins.badge.factory.IDefinedKeybindBadgeFactory;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Function;

public record DefinedKeybindBadge(SerializableData.Instance data) implements IDefinedKeybindBadge<PowerType<?>> {

    @Override
    public String getText() {
        return data.getString("text");
    }

    @Override
    public String getKeyName() {
        return ((Active.Key)data.get("key")).key;
    }

    @Override
    public <K> K getKey() {
        return data.get("key");
    }

    @Override
    public ResourceLocation spriteId() {
        return data.getId("sprite");
    }

    @Override
    public List<ClientTooltipComponent> getTooltipComponents(PowerType<?> powerType, int i, float v, Font font) {
        return IDefinedKeybindBadge.super.generateTooltipComponents(powerType, i, v, font);
    }

    @AutoService(IDefinedKeybindBadgeFactory.class)
    public static class Factory implements IDefinedKeybindBadgeFactory {

        public Factory() {

        }

        @Override
        public Function<SerializableData.Instance, Badge> getFactoryCreator() {
            return DefinedKeybindBadge::new;
        }
    }
}
