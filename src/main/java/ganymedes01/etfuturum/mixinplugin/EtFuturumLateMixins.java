package ganymedes01.etfuturum.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import ganymedes01.etfuturum.Tags;
import ganymedes01.etfuturum.configuration.configs.ConfigMixins;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class EtFuturumLateMixins implements ILateMixinLoader {
	@Override
	public String getMixinConfig() {
		return "mixins." + Tags.MOD_ID + ".late.json";
	}

	@Override
	public List<String> getMixins(Set<String> loadedMods) {
		List<String> mixins = new ArrayList<>();

		if(ConfigMixins.enableSpectatorMode && loadedMods.contains("IronChest")) {
			mixins.add("spectator.MixinContainerIronChest");
		}

		return mixins;
	}
}
