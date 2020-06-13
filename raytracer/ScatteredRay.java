package raytracer;

public class ScatteredRay extends Ray {

    final public Vector3D attenuation;

    public ScatteredRay(Ray r, Vector3D attenuation) {
        super(r);
        this.attenuation = attenuation;
    }
}
