package raytracer;

import java.util.Vector;

public class World implements Surface {

    private Vector<Sphere> objects;

    public World() {
        this.objects = new Vector<>();
    }

    public void addObject(Sphere s) {
        this.objects.add(s);
    }

    public int objectCount() {
        return objects.size();
    }

    @Override
    public HitDetails hit(Ray r, double tMin, double tMax) {
        HitDetails details = new HitDetails();
        double closestHit = tMax;
        HitDetails tmp = null;
        for(Sphere s : objects) {
            tmp = s.hit(r, tMin, closestHit);
            if(tmp != null) {
                closestHit = tmp.t;
                details = tmp;
            }
        }
        if(closestHit == tMax) return null;
        return details;
    }
}
