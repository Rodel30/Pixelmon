package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityArcanine extends EntityPixelmon
{
	
	public EntityArcanine(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Arcanine");
	}
	
	public void evolve() 
	{

	}

}
