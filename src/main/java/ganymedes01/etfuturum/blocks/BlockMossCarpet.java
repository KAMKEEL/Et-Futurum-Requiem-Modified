package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.client.sound.ModSounds;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMossCarpet extends BaseBlock {

	public BlockMossCarpet() {
		super(Material.carpet);
		setHardness(0.1F);
		setResistance(0.1F);
		setBlockTextureName("moss_block");
		setBlockName(Utils.getUnlocalisedName("moss_carpet"));
		setHarvestLevel("hoe", 0);
		setBlockSound(ModSounds.soundMossCarpet);
		setCreativeTab(EtFuturum.creativeTabBlocks);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.validateBlockBounds(0);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}


	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	public void setBlockBoundsForItemRender()
	{
		this.validateBlockBounds(0);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		byte b0 = 0;
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)((float)p_149668_3_ + (float)b0 * f), (double)p_149668_4_ + this.maxZ);
	}

	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_) && this.canBlockStay(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		this.validateLocation(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
	}

	private boolean validateLocation(World p_150090_1_, int p_150090_2_, int p_150090_3_, int p_150090_4_)
	{
		if (!this.canBlockStay(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_))
		{
			this.dropBlockAsItem(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_, p_150090_1_.getBlockMetadata(p_150090_2_, p_150090_3_, p_150090_4_), 0);
			p_150090_1_.setBlockToAir(p_150090_2_, p_150090_3_, p_150090_4_);
			return false;
		}
		else
		{
			return true;
		}
	}

	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	{
		return !p_149718_1_.isAirBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return p_149646_5_ == 1 ? true : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.validateBlockBounds(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	protected void validateBlockBounds(int input)
	{
		byte b0 = 0;
		float f = (float)(1 * (1 + b0)) / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}
}
