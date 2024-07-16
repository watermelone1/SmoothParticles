package net.fatcactis.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.fatcactis.MathUtil;
import net.fatcactis.SmoothParticles;
import net.fatcactis.midnightlib.SmoothParticlesConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(Block.class)
public class ParticleSpawning {
    Resource emptyResource = new Resource(null, null);
    BlockState bs;
    BlockPos bp;
    World w;

    @Inject(at = @At("HEAD"), method = "onPlaced")
    protected void spawnParticles(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
//        List<Resource> resourceList =  MinecraftClient.getInstance().getResourceManager().getAllResources(Identifier.of(SmoothParticles.ID, "particles\\"+state.getBlock().getName()));
//        SmoothParticles.LOGGER.info(String.valueOf(resourceList.get(0)));
        bs = state;
        bp = pos;
        w = world;
        if (SmoothParticlesConfig.breakParticles) {
            AtomicInteger counter = new AtomicInteger();

            if (!state.isAir()) {
                VoxelShape voxelShape = state.getOutlineShape(world, pos);
                voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
                    double d = Math.min(1.0, maxX - minX);
                    double e = Math.min(1.0, maxY - minY);
                    double f = Math.min(1.0, maxZ - minZ);
                    int i = Math.max(2, MathUtil.intCeil(d / (0.25)));
                    int j = Math.max(2, MathUtil.intCeil(e / (0.25)));
                    int k = Math.max(2, MathUtil.intCeil(f / (0.25)));

                    for(int l = 0; l < i; ++l) {
                        for(int m = 0; m < j; ++m) {
                            for(int n = 0; n < k; ++n) {
                                double g = ((double)l + 0.5) / (double)i;
                                double h = ((double)m + 0.5) / (double)j;
                                double o = ((double)n + 0.5) / (double)k;
                                double p = g * d + minX;
                                double q = h * e + minY;
                                double r = o * f + minZ;
                                counter.getAndIncrement();
                                if (counter.get() % (7 / ((int) SmoothParticlesConfig.particleMultiplier)) == 1) {
                                    MinecraftClient.getInstance().particleManager.addParticle(new BlockDustParticle(MinecraftClient.getInstance().world, (double)pos.getX() + p, (double)pos.getY() + q, (double)pos.getZ() + r, g - 0.5, h - 0.5, o - 0.5, state, pos));
                                }
                            }
                        }
                    }

                });
            }
        }
        else {
            try {
                spawnBlockParticles(state.getBlock(), (int) (10 * SmoothParticlesConfig.particleMultiplier));
            } catch (Exception e) {
            }
        }

    }

    private void spawnBlockParticles(Block block, int num) {
        if(bs.getBlock() == block) {
            for(int i = 0; i < 360; i++) {
                if (i % 360/num == 0) {
                    w.addParticle(getParticleFromBlock(block),
                            bp.getX() + Math.random(), bp.getY() + Math.random(), bp.getZ() + Math.random(),
                            (Math.random() - 0.5) * 0.25d, (Math.random() - 0.5) * 0.25d, (Math.random() - 0.5) * 0.25d);
                }
            }
        }
    }

    private ParticleEffect getParticleFromBlock(Block b) {
        return SmoothParticles.PARTICLE_TYPES.get(Registries.BLOCK.getRawId(b));
    }
}