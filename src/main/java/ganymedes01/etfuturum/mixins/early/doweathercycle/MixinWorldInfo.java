package ganymedes01.etfuturum.mixins.early.doweathercycle;

import ganymedes01.etfuturum.gamerule.DoWeatherCycle;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldInfo.class)
public class MixinWorldInfo {

	@Inject(method = {"setRainTime", "setRaining", "setThunderTime", "setThundering"}, at = @At("HEAD"), cancellable = true)
	private void cancelWeatherChange(CallbackInfo ci) {
		if (DoWeatherCycle.INSTANCE.canCancelWeatherChange(((WorldInfo) (Object) this).getGameRulesInstance())) {
			ci.cancel();
		}
	}

}
