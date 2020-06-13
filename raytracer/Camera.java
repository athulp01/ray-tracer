package raytracer;

public class Camera {

    private final Vector3D lowerLeft;
    private final Vector3D horizontal;
    private final Vector3D vertical;
    private final Vector3D origin;

    public Camera(Vector3D lookFrom, Vector3D lookAt, Vector3D up, double viewAngle, double aspectRatio) {
        Vector3D u,v,w;
        double theta = viewAngle*Math.PI/180;
        double halfHeight= Math.tan(theta/2);
        double halfWidth = aspectRatio * halfHeight;
        w = Vector3D.unitVector(Vector3D.sub(lookFrom, lookAt));
        u = Vector3D.unitVector(Vector3D.cross(up, w));
        v = Vector3D.cross(w,u);
        origin = lookFrom;
        lowerLeft = Vector3D.add(origin, Vector3D.add(
                Vector3D.scale(u, -halfWidth), Vector3D.sub(
                        Vector3D.scale(v, -halfHeight), w
                )
        ));
        horizontal = Vector3D.scale(u, 2*halfWidth);
        vertical = Vector3D.scale(v, 2*halfHeight);
    }

    public Ray getRay(double scaleX, double scaleY) {
        final Vector3D scaledX = Vector3D.scale(horizontal, scaleX);
        final Vector3D scaledY = Vector3D.scale(vertical, scaleY);
        Vector3D sum = Vector3D.add(scaledX, scaledY);
        sum.sub(origin);
        return new Ray(origin, Vector3D.add(lowerLeft, sum));
    }
}
