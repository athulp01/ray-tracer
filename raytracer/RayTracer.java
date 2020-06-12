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
        final Sphere sphere1 = new Sphere(c, new Vector3D(0, 0, -1), 0.5);
        final Sphere sphere2 = new Sphere(new Color(0,1,0), new Vector3D(0, -100.5, -1), 100);
        World world = new World();
        world.addObject(sphere1);
        world.addObject(sphere2);
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                Color cur = new Color(0,0,0);
                for (int s=0; s<numOfSamples; s++) {
                    final double scaleX = (double) (x + Math.random()) / width;
                    final double scaleY = (double) (y + Math.random()) / height;
                    final Ray r = camera.getRay(scaleX, scaleY);
                    cur.add(new Color(Color.generateColor(r, world)));
                }
                cur.scale(1.0/numOfSamples);
                System.out.println(cur.toString());
            }
        }
    }
}