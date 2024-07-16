package net.fatcactis;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

public class RegistryUtil {
    public static int getIdFromBlock(Block block) {
        return Registries.BLOCK.getRawId(block);
    }
    public static Block getBlockFromId(int block) { return Registries.BLOCK.get(block); }
}
