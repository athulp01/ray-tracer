package raytracer;

/**
 * Sphere
 */
public class Sphere implements Surface{

    public final Vector3D center;
    public final double radius;
    public final Material material;

    public Sphere(final Vector3D center, final double r, Material m) {
        this.center = center;
        this.radius = r;
        this.material = m;
    }

    public Sphere(final Sphere s) {
        this.center = s.center;
        this.radius = s.radius;
        this.material = s.material;
    }

    public HitDetails hit(final Ray r, double tMin, double tMax) {
        final Vector3D distVec = Vector3D.sub(r.origin(), this.center);
        final double a = Vector3D.dot(r.direction(), r.direction());
        final double b = distVec.dot(r.direction());
        final double c = Vector3D.dot(distVec, distVec) - (this.radius * this.radius);
        final double discriminant = b * b -  a * c;
        HitDetails ans = new HitDetails();
        ans.material = this.material;
        if(discriminant > 0) {
            double t = (-b - Math.sqrt(discriminant)) / a;
            if(t < tMax && t > tMin) {
                ans.t = t;
                ans.point = r.pointAt(t);
                ans.normal = Vector3D.scale(Vector3D.sub(ans.point, this.center), 1.0/this.radius);
                return ans;
            }
            t = (-b + Math.sqrt(discriminant)) / a;
            if(t < tMax && t > tMin) {
                ans.t = t;
                ans.point = r.pointAt(t);
                ans.normal = Vector3D.scale(Vector3D.sub(ans.point, this.center), 1.0/this.radius);
                return ans;
            }
        }
        return null;
    }

    static public Vector3D RandomPointInUnitSphere() {
        Vector3D ans;
        do {
            ans = Vector3D.sub(Vector3D.scale(new Vector3D(Math.random(), Math.random(), Math.random()),
                    2), new Vector3D(1,1,1));
        }while(Math.pow(ans.length(), 2) >= 1.0);
        return ans;
    }
    
}