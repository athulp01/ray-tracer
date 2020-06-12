package raytracer;
/**
 * Sphere
 */
public class Sphere implements Surface{

    public final Color color;
    public final Vector3D center;
    public final double radius;

    public Sphere(final Color color, final Vector3D center, final double r) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    public Sphere(final Sphere s) {
        this.color = s.color;
        this.center = s.center;
        this.radius = s.radius;
    }

    public HitDetails hit(final Ray r, double tMin, double tMax) {
        final Vector3D distVec = Vector3D.sub(r.origin(), this.center);
        final double a = Vector3D.dot(r.direction(), r.direction());
        final double b = distVec.dot(r.direction());
        final double c = Vector3D.dot(distVec, distVec) - (this.radius * this.radius);
        final double discriminant = b * b -  a * c;
        HitDetails ans = new HitDetails();
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
        ans.t = Double.NaN;
        ans.normal = null;
        ans.point = null;
        return ans;

    }
    
}