package raytracer;

public interface Surface {
    class HitDetails {
        double t;
        Vector3D point;
        Vector3D normal;
        Material material;
    }

    /**
     * This method is used to hit the surface with a ray.
     * @param r The ray
     * @param tMin minimum value
     * @param tMax maximum value
     * @return return @code HitDetails with valid points if surface got hit
     * by ray, otherwise return null and NaN values.
     */
    HitDetails hit(final Ray r, double tMin, double tMax);
}
