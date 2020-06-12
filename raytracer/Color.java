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

    static public Vector3D generateColor(final Ray r, final Surface surface) {
        Surface.HitDetails detail = surface.hit(r, 0, Double.MAX_VALUE);
        if(detail.point != null) {
            detail.normal.add(new Vector3D(1,1,1));
            detail.normal.scale(0.5);
            return detail.normal;
        }
        final Vector3D unit = unitVector(r.direction());
        final double t = 0.5 * (unit.y + 1.0);
        return (add(scale(new Vector3D(1.0, 1.0, 1.0), 1.0 - t),
                scale(new Vector3D(0.5, 0.7, 1.0), t)));
    }
}