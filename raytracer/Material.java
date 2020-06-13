package raytracer;

public interface Material {

    ScatteredRay scatter(Ray incidentRay, Surface.HitDetails details);
}
