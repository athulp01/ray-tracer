package raytracer;

public class Color extends Vector3D{

    public Color(double red, double blue, double green) {
        super(red, blue, green);
    }

    public Color(Vector3D vec) {
        super(vec);
    }

    @Override
    public String toString() {
        return (int)(this.x*255.99) + " " + 
        (int)(this.y*255.99) + " " + (int)(this.z*255.99);
    }

    static public Vector3D generateColor(final Ray r, final Surface surface, int depth) {
        Surface.HitDetails detail = surface.hit(r, 0.001, Double.MAX_VALUE);
        ScatteredRay scattered;
        if(detail != null) {
            if (depth < 50 && (scattered = detail.material.scatter(r, detail)) != null) {
                return Vector3D.mul(generateColor(scattered, surface, depth+1), scattered.attenuation);
            } else {
                return new Vector3D(0,0,0);
            }
        }
        final Vector3D unit = unitVector(r.direction());
        final double t = 0.5 * (unit.y + 1.0);
        return (add(scale(new Vector3D(1.0, 1.0, 1.0), 1.0 - t),
                scale(new Vector3D(0.5, 0.7, 1.0), t)));
    }
}