package net.fatcactis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.client.particle.DustPlumeParticle;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.Registries;

import java.util.Arrays;

public class SmoothParticlesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        int i = 0;
        for (Block block : Registries.BLOCK) {
//            int start = 352; //350 u
//            int end = 354;
//            register(Math.clamp(i, start, end));
//            SmoothParticles.LOGGER.info(RegistryUtil.getBlockFromId(Math.clamp(i, start, end)).toString() + " " + Math.clamp(i, start, end));
            register(i);
            i++;
        }
        //brick slab
        //stone brick slab
        //mud brick slab
        //nether brick sla
        //quartz slab
        //red sandstone slab
    }
    void register(int index) {
        SmoothParticles.LOGGER.info("client register index" + index);
        SmoothParticles.LOGGER.info(SmoothParticles.PARTICLE_TYPES.get(index).toString());
        ParticleFactoryRegistry.PendingParticleFactory factory = GravityBlock.Factory::new;
        if (RegistryUtil.getBlockFromId(index).toString().contains("leaves")) {
            factory = GravityBlock.Factory::new;
        }
        ParticleFactoryRegistry.getInstance().register(SmoothParticles.PARTICLE_TYPES.get(index), factory);
    }
}
