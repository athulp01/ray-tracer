package raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class RayTracer {

    public static void main(final String[] args) throws Exception{
        final Camera camera = new Camera();
        final int numOfSamples = 100;
        FileWriter fileWriter = new FileWriter("./img.ppm");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        final int width = 800;
        final int height = 400;
        System.out.println("P3\n");
        System.out.println(width + " " + height);
//        writer.newLine();
        System.out.println("255");
//        writer.newLine();
        final Color c = new Color(1, 0, 1);
        final Sphere sphere1 = new Sphere(c, new Vector3D(0, 0, -1), 0.5, new Matte(new Vector3D(0.8, 0.3, 0.3)));
        final Sphere sphere2 = new Sphere(c, new Vector3D(0, -100.5, -1), 100, new Matte(new Vector3D(0.8, 0.8, 0.0)));
        final Sphere sphere3 = new Sphere(c, new Vector3D(1, 0, -1), 0.5, new Glossy(new Vector3D(0.8, 0.6, 0.2)));
        final Sphere sphere4 = new Sphere(c, new Vector3D(-1, 0, -1), 0.5, new Glossy(new Vector3D(0.8, 0.8, 0.8)));
        World world = new World();
        world.addObject(sphere1);
        world.addObject(sphere2);
        world.addObject(sphere3);
        world.addObject(sphere4);
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
                cur.x = Math.sqrt(cur.x);
                cur.y = Math.sqrt(cur.y);
                cur.z = Math.sqrt(cur.z);
                System.out.println(cur.toString());
            }
        }
    }
}