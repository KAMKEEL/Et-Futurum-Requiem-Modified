package ganymedes01.etfuturum.mixins.early.spectator.client;

import ganymedes01.etfuturum.spectator.SpectatorMode;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {
	@Inject(method = "isInvisibleToPlayer", at = @At("HEAD"), cancellable = true)
	public void isInvisibleToPlayer(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
		if (SpectatorMode.isSpectator(player)) {
			cir.setReturnValue(false);
		}
	}
}
