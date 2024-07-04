package net.gargoyle.copiousfix.mixin;

import com.mojang.logging.LogUtils;
import iskallia.vault.block.VaultOreBlock;

import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(VaultOreBlock.class)
public class CopiousStackMixin {
    @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
    public void onGetDrops(CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> returned = cir.getReturnValue();
        if (returned.size() < 2) {
            // No copiously proc; leave it as it is
            return;
        }

        List<ItemStack> replacementStack = new ArrayList<>();
//        final Logger LOGGER = LogUtils.getLogger();
        for (ItemStack stack : returned) {
//            Can we print the name of the player?
//            LOGGER.info("Duplicating item stack: {}", stack);
            replacementStack.add(new ItemStack(stack.getItem(), stack.getCount()));
        }
        cir.setReturnValue(replacementStack);
    }
}