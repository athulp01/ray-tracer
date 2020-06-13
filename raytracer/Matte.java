package raytracer;

public class Matte implements Material{

    final Vector3D reflectance;

    public Matte(Vector3D reflectance) {
        this.reflectance = reflectance;
    }

    @Override
    public ScatteredRay scatter(Ray incidentRay, Surface.HitDetails details) {
        Vector3D target = Vector3D.add(details.normal, Sphere.RandomPointInUnitSphere());
        return new ScatteredRay(new Ray(details.point, target), this.reflectance);
    }
}
