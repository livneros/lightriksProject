package entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utils.Constants.*;

public class WeightFunctionTest {

    private static final int DEFAULT_EPSILON = 1;
    private double powerFactor;
    private double epsilon;
    private WeightFunction weightFunction;

    @Test
    public void solvePerCoordinate_basicParams() {
        powerFactor = 2;
        epsilon = DEFAULT_EPSILON;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        Coordinate u = mock(Coordinate.class);
        Coordinate v = mock(Coordinate.class);
        double distance = 0.0;
        when(u.euclideanDistance(v)).thenReturn(distance);
        double solveSol = weightFunction.solvePerCoordinate(u, v);
        Assert.assertEquals(1, solveSol, DEFAULT_DELTA);
    }

    @Test
    public void solvePerCoordinate_almostZeroEpsilon() {
        powerFactor = 2;
        epsilon = MINIMAL_EPSILON_ALLOWED;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        Coordinate u = mock(Coordinate.class);
        Coordinate v = mock(Coordinate.class);
        double distance = 0.0;
        when(u.euclideanDistance(v)).thenReturn(distance);
        double solveSol = weightFunction.solvePerCoordinate(u, v);
        assertTrue(solveSol < Double.MAX_VALUE);
    }

    @Test
    public void solvePerCoordinate_negativePowerFactor() {
        powerFactor = -1;
        epsilon = DEFAULT_EPSILON;
        weightFunction = WeightFunction.getInstance(powerFactor, epsilon);
        Coordinate u = mock(Coordinate.class);
        Coordinate v = mock(Coordinate.class);
        double distance = 2.0;
        when(u.euclideanDistance(v)).thenReturn(distance);
        double solveSol = weightFunction.solvePerCoordinate(u, v);
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
        assertEquals(originalWeightFunction.getPowerFactor(), secondWeightFunction.getPowerFactor(), ZERO_DELTA);
        assertEquals(originalWeightFunction.getEpsilon(), secondWeightFunction.getEpsilon(), ZERO_DELTA);
    }

    @Test
    public void solve_singleBoundary() throws Exception {
        double boundaryValue = 5.0;
        double euclideanDis = 2.0;
        Coordinate u = mock(Coordinate.class);
        Coordinate boundary = mock(Coordinate.class);
        Map<Coordinate, Double> boundaries = mock(Map.class);
        when(boundaries.keySet()).thenReturn(new HashSet<>(Collections.singleton(boundary)));
        when(boundaries.get(boundary)).thenReturn(boundaryValue);
        WeightFunction weightFunction = mock(WeightFunction.class);
        when(weightFunction.solvePerCoordinate(u, boundary)).thenReturn(euclideanDis);
        when(weightFunction.solve(u, boundaries)).thenCallRealMethod();
        double res = weightFunction.solve(u, boundaries);
        assertEquals(boundaryValue, res, ZERO_DELTA);
    }
    @Test
    public void solve_doubleBoundary() throws Exception {
        double firstBoundaryValue = 5.0;
        double secondBoundaryValue = 3.0;
        double euclideanDis = 2.0;
        Coordinate u = mock(Coordinate.class);
        Coordinate firstBoundary = mock(Coordinate.class);
        Coordinate secondBoundary = mock(Coordinate.class);
        Map<Coordinate, Double> boundaries = mock(Map.class);
        when(boundaries.keySet()).thenReturn(new HashSet<>(Arrays.asList(firstBoundary, secondBoundary)));
        when(boundaries.get(firstBoundary)).thenReturn(firstBoundaryValue);
        when(boundaries.get(secondBoundary)).thenReturn(secondBoundaryValue);
        WeightFunction weightFunction = mock(WeightFunction.class);
        when(weightFunction.solvePerCoordinate(u, firstBoundary)).thenReturn(euclideanDis);
        when(weightFunction.solvePerCoordinate(u, secondBoundary)).thenReturn(euclideanDis);
        when(weightFunction.solve(u, boundaries)).thenCallRealMethod();
        double res = weightFunction.solve(u, boundaries);
        assertEquals(4.0, res, ZERO_DELTA);
    }
}