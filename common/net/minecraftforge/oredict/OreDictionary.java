package net.minecraftforge.oredict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;

public class OreDictionary
{
    private static int maxID = 0;
    private static HashMap<String, Integer> oreIDs = new HashMap<String, Integer>();
    private static HashMap<Integer, ArrayList<ItemStack>> oreStacks = new HashMap<Integer, ArrayList<ItemStack>>();
    
    /**
     * Gets the integer ID for the specified ore name. 
     * If the name does not have a ID it assigns it a new one.
     * 
     * @param name The unique name for this ore 'oreIron', 'ingotIron', etc..
     * @return A number representing the ID for this ore type
     */
    public static int getOreID(String name)
    {
        Integer val = oreIDs.get(name);
        if (val == null)
        {
            val = maxID++;
            oreIDs.put(name, val);
            oreStacks.put(val, new ArrayList<ItemStack>());
        }
        return val;
    }
    
    /**
     * Reverse of getOreID, will not create new entries.
     * 
     * @param id The ID to translate to a string
     * @return The String name, or "Unknown" if not found.
     */
    public static String getOreName(int id)
    {
        for (Map.Entry<String, Integer> entry : oreIDs.entrySet())
        {
            if (id == entry.getValue())
            {
                return entry.getKey();
            }
        }
        return "Unknown";
    }
    
    /**
     * Retrieves the ArrayList of items that are registered to this ore type.
     * Creates the list as empty if it did not exist.
     *  
     * @param id The ore ID, see getOreID
     * @return An arrayList containing ItemStacks registered for this ore
     */
    public static ArrayList<ItemStack> getOres(String name)
    {
        return getOres(getOreID(name));
    }
    
    /**
     * Retrieves a list of all unique ore names that are already registered.
     * 
     * @return All unique ore names that are currently registered.
     */
    public static String[] getOreNames()
    {
        return oreIDs.keySet().toArray(new String[0]);
    }
    
    /**
     * Retrieves the ArrayList of items that are registered to this ore type.
     * Creates the list as empty if it did not exist.
     *  
     * @param id The ore ID, see getOreID
     * @return An arrayList containing ItemStacks registered for this ore
     */
    public static ArrayList<ItemStack> getOres(Integer id)
    {
        ArrayList<ItemStack> val = oreStacks.get(id);
        if (val == null)
        {
            val = new ArrayList<ItemStack>();
            oreStacks.put(id, val);
        }
        return val;
    }
    
    //Convenience functions that make for cleaner code mod side. They all drill down to registerOre(String, int, ItemStack)
    public static void registerOre(String name, Item      ore){ registerOre(name, new ItemStack(ore));  }
    public static void registerOre(String name, Block     ore){ registerOre(name, new ItemStack(ore));  }
    public static void registerOre(String name, ItemStack ore){ registerOre(name, getOreID(name), ore); }
    public static void registerOre(int    id,   Item      ore){ registerOre(id,   new ItemStack(ore));  }
    public static void registerOre(int    id,   Block     ore){ registerOre(id,   new ItemStack(ore));  }
    public static void registerOre(int    id,   ItemStack ore){ registerOre(getOreName(id), id, ore);   }
    
    /**
     * Registers a ore item into the dictionary.
     * Raises the registerOre function in all registered handlers.
     * 
     * @param name The name of the ore
     * @param id The ID of the ore
     * @param ore The ore's ItemStack
     */
    private static void registerOre(String name, int id, ItemStack ore)
    {
        ArrayList<ItemStack> ores = getOres(id);
        ore = ore.copy();
        ores.add(ore);
        MinecraftForge.EVENT_BUS.post(new OreRegisterEvent(name, ore));
    }
    
    public static class OreRegisterEvent extends Event
    {
        public final String Name;
        public final ItemStack Ore;
        
        public OreRegisterEvent(String name, ItemStack ore)
        {
            this.Name = name;
            this.Ore = ore;
        }
    }
}
