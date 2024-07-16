package net.fatcactis;

import java.util.Random;

public class MathUtil<T>{
    public T getRandom(T... ts) {
        return ts[(int) Math.floor(Math.random() * ts.length)];

    }
    public T getRandomFromSeed(long seed, T... ts) {
        Random random = new Random();
        random.setSeed(seed);
        return ts[(int) Math.floor(random.nextDouble() * ts.length)];
    }
    public T getRandomList(T... ts) {
        return ts[(int) Math.floor(Math.random() * ts.length)];


    }
    public static double getRandomDoubleFromSeed(long seed, double... doubles) {
        Random random = new Random();
        random.setSeed(seed);
        return doubles[(int) Math.floor(random.nextDouble() * doubles.length)];
    }
    public static double flipAroundNumber(double num, double point) {
        if(point == num) {
            return num;
        }
        double difference = point - num;
        return point + difference;
    }

    public static int intCeil (double num) {
        return (int) Math.ceil(num);
    }
}
