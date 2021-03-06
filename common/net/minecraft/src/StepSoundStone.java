package net.minecraft.src;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

final class StepSoundStone extends StepSound
{
    StepSoundStone(String par1Str, float par2, float par3)
    {
        super(par1Str, par2, par3);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound()
    {
        return "random.glass";
    }
}
