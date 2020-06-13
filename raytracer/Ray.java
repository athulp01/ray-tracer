package raytracer;

public class Ray {

    private final Vector3D origin, direction;

    public Ray(final Vector3D origin, final Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(Ray r) {
        this.origin = r.origin;
        this.direction = r.direction;
    }

    public Vector3D origin() {return this.origin;}
    public Vector3D direction() {return this.direction;}

    public Vector3D pointAt(double t) {
        return Vector3D.add(origin, Vector3D.scale(direction, t));
    }

    @Override
    public String toString() {
        return String.format("origin = %s, dir = %s", origin.toString(), direction.toString());
    }
}