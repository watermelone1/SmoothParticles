package net.fatcactis.midnightlib;

import eu.midnightdust.lib.config.MidnightConfig;

public class SmoothParticlesConfig extends MidnightConfig {
    @Entry(name = "Use Block Breaking Particles.\n" +
            "\n§4Warning: This is still in development and turning this off may cause issues" +
            "\nPlease report issues to https://github.com/watermelone1/Smooth-Particles/issues") public static boolean breakParticles = true;
    @Comment() public static Comment spacer1;
    @Comment() public static Comment spacer2;
    @Comment() public static Comment spacer3;
    @Entry(isSlider = true, min = 0f, max = 5f, precision = 1000) public static float particleMultiplier = 1f;
}
