package raytracer;

public class Camera {

    private final Vector3D lowerLeft;
    private final Vector3D horizontal;
    private final Vector3D vertical;
    private final Vector3D origin;

    public Camera() {
        origin = new Vector3D(0, 0, 0);
        lowerLeft = new Vector3D(-2.0, -1.0, -1.0);
        horizontal = new Vector3D(4.0, 0.0, 0.0);
        vertical = new Vector3D(0.0, 2.0, 0.0);
    }

    public Ray getRay(double scaleX, double scaleY) {
        final Vector3D scaledX = Vector3D.scale(horizontal, scaleX);
        final Vector3D scaledY = Vector3D.scale(vertical, scaleY);
        final Vector3D sum = Vector3D.add(scaledX, scaledY);
        return new Ray(origin, Vector3D.add(lowerLeft, sum));
    }
}
