package ganymedes01.etfuturum.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.world.World;

import java.util.Random;

public class PostTreeGenerateEvent extends Event {
	public final World world;
	public final Random rand;
	public final int x, y, z;

	public PostTreeGenerateEvent(World world, Random rand, int x, int y, int z) {
		this.world = world;
		this.rand = rand;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
