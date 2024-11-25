package ganymedes01.etfuturum.mixins.early.spectator;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import ganymedes01.etfuturum.spectator.SpectatorMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerChest.class)
public abstract class MixinContainerChest extends Container {
	@WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/IInventory;openInventory()V"))
	private void ignoreOpenInventoryInSpectator(IInventory chestInv, Operation<Void> original,
												@Local(ordinal = 0, argsOnly = true) IInventory playerInv) {
		if (!(playerInv instanceof InventoryPlayer) || !SpectatorMode.isSpectator(((InventoryPlayer) playerInv).player)) {
			original.call(chestInv);
		}
	}

	@Inject(method = "onContainerClosed", at = @At(value = "HEAD"), cancellable = true)
	private void ignoreCloseInventoryInSpectator(EntityPlayer p_75134_1_, CallbackInfo ci) {
		if (SpectatorMode.isSpectator(p_75134_1_)) {
			ci.cancel();
		}
	}
}
