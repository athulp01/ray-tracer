package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import raytracer.Color;
import raytracer.Ray;
import raytracer.Sphere;
import raytracer.Vector3D;

public class TestVector3D {

    Vector3D v1 = new Vector3D(1.0, 1.0, 1.0);
    Vector3D v2 = new Vector3D(1.0, 1.0, 0.0);

    @Test
    public void testSum() {
        Vector3D t = Vector3D.add(v1, v2);
        assertEquals(new Vector3D(2.0,2.0,1.0), t);
    }

    @Test
    public void testSub() {
        Vector3D t = Vector3D.sub(v1, v2);
        assertEquals(new Vector3D(0,0,1.0), t);
    }

    @Test
    public void testScale() {
        Vector3D t = Vector3D.scale(v1, 2);
        assertEquals(new Vector3D(2.0,2.0,2.0), t);
    }

    @Test
    public void testLength() {
        assertEquals(Math.sqrt(2), v2.length(), 0.000001);
    }

    @Test
    public void testDot() {
        assertEquals(2.0, v1.dot(v2), 1e-4);
    }

    @Test
    public void testUnitVector() {
        double len = Math.sqrt(3);
        Vector3D unit = new Vector3D(1/len, 1/len, 1/len);
        assertEquals(unit, Vector3D.unitVector(v1));
    }
    

}