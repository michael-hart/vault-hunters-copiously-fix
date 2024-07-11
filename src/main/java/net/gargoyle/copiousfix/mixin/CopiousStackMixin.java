package net.gargoyle.copiousfix.mixin;

import iskallia.vault.block.VaultOreBlock;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        for (ItemStack stack : returned) {
            ItemStack replacement = new ItemStack(stack.getItem(), stack.getCount());
            if (stack.hasTag()) {
                replacement.setTag(stack.getTag());
            }
            replacementStack.add(replacement);
        }
        cir.setReturnValue(replacementStack);
    }
}