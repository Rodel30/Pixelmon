package pixelmon.config;

import java.io.File;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEggInfo;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.Pixelmon;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.pokemon.*;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.spawning.SpawnRegistry;

public class PixelmonEntityList {
	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static EntityLiving createEntityByName(String par0Str, World par1World) {
		EntityLiving var2 = null;

		try {
			ClassType type = null;
			for (EnumPokemon pokemon : EnumPokemon.values())
				if (pokemon.name.equalsIgnoreCase(par0Str))
					type = pokemon.type;

			if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon) {
				var2 = new EntityPixelmon(par1World);
				((EntityPixelmon) var2).init(par0Str);
			} else {
				var2 = new EntityTrainer(par1World);
				((EntityTrainer) var2).init(par0Str);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	/**
	 * create a new instance of an entity from NBT store
	 */
	public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World) {
		EntityLiving var2 = null;

		try {
			Class<?> var3 = EntityPixelmon.class;

			if (var3 != null) {
				var2 = (EntityLiving) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
				((EntityPixelmon) var2).init(par0NBTTagCompound.getString("Name"));
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if (var2 != null) {
			var2.readFromNBT(par0NBTTagCompound);
		} else {
			System.out.println("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
		}

		return var2;
	}

	public static void registerEntities() {
		EntityRegistry.registerModEntity(EntityTrainer.class, "Trainer", PixelmonConfig.idTrainers, Pixelmon.instance, 100, 1, true);
		EntityRegistry.registerModEntity(EntityPixelmon.class, "Pixelmon", PixelmonConfig.idPixelmon, Pixelmon.instance, 100, 1, true);
	}

	public static void addSpawns() {
		System.out.println("[PIXELMON] Registering entity spawns");

		for (EnumPokemon pokemon : EnumPokemon.values()) {
			String name = pokemon.name;
			ClassType type = pokemon.type;
			int rarity = DatabaseStats.GetRarity(name);
			if (rarity > 0)
				SpawnRegistry.addSpawn(name, rarity, type);
		}

		for (EnumTrainers trainer : EnumTrainers.values()) {
			String name = trainer.toString();
			int rarity = DatabaseTrainers.getRarity(name);
			if (rarity > 0)
				SpawnRegistry.addSpawn(name, rarity, ClassType.Trainer);
		}

		// if (new File("resources/newsound/pixelmon/" +
		// name.toLowerCase() + ".ogg").exists())
		//
		// Minecraft.getMinecraft().installResource("newsound/pixelmon/"
		// + name.toLowerCase() + ".ogg", new
		// File("resources/newsound/pixelmon/" + name.toLowerCase() +
		// ".ogg"));

	}

	public enum ClassType {
		Trainer, Pixelmon, WaterPixelmon
	}
}
