package net.fatcactis;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fatcactis.midnightlib.SmoothParticlesConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class SmoothParticles implements ModInitializer {
	public static final String ID = "smoothparticles";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final ArrayList<SimpleParticleType> PARTICLE_TYPES = new ArrayList<>();

	@Override
	public void onInitialize() {
		MidnightConfig.init(ID, SmoothParticlesConfig.class);
		registerParticles();
	}

	void register(Block block) {
		LOGGER.info(String.valueOf(Registries.BLOCK.getRawId(block)) + block.getName().toString());
		PARTICLE_TYPES.add(FabricParticleTypes.simple());
		String blockID = Registries.BLOCK.getEntry(block).getIdAsString();
		String splitID = splitIdentifier(blockID);
		LOGGER.info(Identifier.of(ID, splitID).toString());
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(ID, splitID), PARTICLE_TYPES.getLast());
	}

	void registerParticles() {
		for (Block block : Registries.BLOCK) {
			register(block);
		}
		LOGGER.info(String.valueOf(PARTICLE_TYPES.size()));
	}

	public static String splitIdentifier(String input) {
		int index = input.indexOf(":");
		if (index != -1) {
			String result = input.replaceAll(".*" + ":", "");
			return result;
		} else {
			return input;
		}
	}



}