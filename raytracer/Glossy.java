package raytracer;

public class Glossy implements Material {

    private final Vector3D reflectance;

    public Glossy(Vector3D reflectance) {
        this.reflectance = reflectance;
    }

    private Vector3D reflect(Vector3D incident, Vector3D normal) {
        return Vector3D.sub(incident,
                Vector3D.scale(normal, 2*Vector3D.dot(incident, normal)));
    }

    @Override
    public ScatteredRay scatter(Ray incidentRay, Surface.HitDetails details) {
        Vector3D reflected = reflect(Vector3D.unitVector(incidentRay.direction()),
                details.normal);
        if(Vector3D.dot(reflected, details.normal) > 0) {
            return new ScatteredRay(new Ray(details.point, reflected), this.reflectance);
        } else return null;
    }
}
