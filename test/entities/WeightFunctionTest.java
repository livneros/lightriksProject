package entities;

import entities.implementations.CoordinateImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static utils.Constants.*;

import static org.junit.Assert.*;

public class WeightFunctionTest {

    private static final int DEFAULT_EPSILON = 1;
    private double powerFactor;
    private double epsilon;
    private WeightFunction weightFunction;
    private CoordinateImpl coordinateImpl;

    @Test
    public void solve_basicParams() {
        powerFactor = 2;
        epsilon = DEFAULT_EPSILON;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        coordinateImpl = Mockito.mock(CoordinateImpl.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        Coordinate v = Mockito.mock(Coordinate.class);
        double distance = 0.0;
        Mockito.when(coordinateImpl.euclideanDistance(u, v)).thenReturn(distance);
        double solveSol = weightFunction.solve(u, v, coordinateImpl);
        Assert.assertEquals(1, solveSol, DEFAULT_DELTA);
    }

    @Test
    public void solve_almostZeroEpsilon() {
        powerFactor = 2;
        epsilon = MINIMAL_EPSILON_ALLOWED;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        coordinateImpl = Mockito.mock(CoordinateImpl.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        Coordinate v = Mockito.mock(Coordinate.class);
        double distance = 0.0;
        Mockito.when(coordinateImpl.euclideanDistance(u, v)).thenReturn(distance);
        double solveSol = weightFunction.solve(u, v, coordinateImpl);
        assertTrue(solveSol < Double.MAX_VALUE);
    }

    @Test
    public void solve_negativePowerFactor() {
        powerFactor = -1;
        epsilon = DEFAULT_EPSILON;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        coordinateImpl = Mockito.mock(CoordinateImpl.class);
        Coordinate u = Mockito.mock(Coordinate.class);
        Coordinate v = Mockito.mock(Coordinate.class);
        double distance = 2.0;
        Mockito.when(coordinateImpl.euclideanDistance(u, v)).thenReturn(distance);
        double solveSol = weightFunction.solve(u, v, coordinateImpl);
        Assert.assertEquals(0.66667, solveSol, DEFAULT_DELTA);
    }

    @Test
    public void singleton_test() {
        powerFactor = 1;
        epsilon = DEFAULT_EPSILON;
        WeightFunction originalWeightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        double secondPowerFactor = 1;
        double secondEpsilon = 5;
        WeightFunction secondWeightFunction = WeightFunction.getInstance(secondPowerFactor, secondEpsilon);
        assertEquals(powerFactor, secondWeightFunction.getPowerFactor(), ZERO_DELTA);
        assertEquals(epsilon, secondWeightFunction.getEpsilon(), ZERO_DELTA);
    }
}