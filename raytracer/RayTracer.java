package raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class RayTracer extends Thread{

    final static int width = 800;
    final static int height = 400;
    final static int numOfSamples = 100;
    final static Vector3D lookFrom = new Vector3D(0, 0, 2);
    final static Vector3D lookAt = new Vector3D(0,0,-1);
    final static  Vector3D up = new Vector3D(0,1,0);
    final static double angle = 60;
    final static double aspectRatio = (float)width/height;

    private static World generateWorld() {
        final Color c = new Color(1, 0, 1);
        final Sphere sphere1 = new Sphere(new Vector3D(0, 0, -1), 0.5, new Glossy(new Vector3D(0.8, 0.3, 0.3)));
        final Sphere sphere2 = new Sphere(new Vector3D(0, -100.5, -1), 100, new Matte(new Vector3D(0.5, 0.7, 0.8)));
        final Sphere sphere3 = new Sphere(new Vector3D(0.8, 0, 0), 0.5, new Matte(new Vector3D(0.1, 1.0, 0.2)));
        final Sphere sphere4 = new Sphere(new Vector3D(-1, 0, 0), 0.5, new Matte(new Vector3D(1.0, 0.0, 0.0)));
        World world = new World();
        world.addObject(sphere1);
        world.addObject(sphere2);
        world.addObject(sphere3);
        world.addObject(sphere4);
        return world;
    }



    public static void main(final String[] args) throws Exception{
        final Camera camera = new Camera(lookFrom, lookAt, up, angle, aspectRatio);
        final World world = generateWorld();
        FileWriter fileWriter = new FileWriter("./img.ppm");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("P3\n");
        writer.write(width + " " + height);
        writer.newLine();
        writer.write("255\n");
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                Color cur = new Color(0,0,0);
                // Anti-Aliasing by averaging
                for (int s=0; s<numOfSamples; s++) {
                    final double scaleX = (double) (x + Math.random()) / width;
                    final double scaleY = (double) (y + Math.random()) / height;
                    final Ray r = camera.getRay(scaleX, scaleY);
                    cur.add(new Color(Color.generateColor(r, world, 0)));
                }
                cur.scale(1.0/numOfSamples);
                // Gamma correction (gamma = 2)
                cur.gammaCorrect();
                writer.write(cur.toString());
                writer.newLine();
            }
        }
        writer.close();
    }
}