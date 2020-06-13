package raytracer;

import java.lang.Math;

public class Vector3D {

    public double x, y, z;
    
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector3D copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
    }

    @Override
    public String toString() {
        return "("+this.x+","+this.y+","+this.z+")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) 
            return false;
        if(!Vector3D.class.isAssignableFrom(obj.getClass()))
            return false;
        final Vector3D that = (Vector3D)obj;
        return !(Math.abs(this.x - that.x) > 1e-4) && !(Math.abs(this.y - that.y) > 1e-4) && !(Math.abs(this.z - that.z) > 1e-4);
    }

    public double dot(Vector3D that) {
        return this.x*that.x + this.y*that.y + this.z*that.z;
    }

    public double distance(Vector3D that) {
        return Math.sqrt(((this.x - that.x) * (this.x - that.x)) +
        ((this.y - that.y) * (this.y - that.y)) + 
        ((this.z - that.z) * (this.z - that.z)));
    }

    public double length() {
        return Math.sqrt((this.x*this.x) +
        (this.y*this.y) + (this.z*this.z));
    }

    public static Vector3D cross(Vector3D v1, Vector3D v2) {
       return new Vector3D((v1.y*v2.z - v1.z*v2.y), (-(v1.x*v2.z - v1.z*v2.x)), (v1.x*v2.y - v1.y*v2.x));
    }

    public void add(Vector3D that) {
        this.x += that.x;
        this.y += that.y;
        this.z += that.z;
    }

    public void sub(Vector3D that) {
        this.x -= that.x;
        this.y -= that.y;
        this.z -= that.z;
    }

    public void mul(Vector3D that) {
        this.x *= that.x;
        this.y *= that.y;
        this.z *= that.z;
    }

    public void scale(double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
    }

    public static Vector3D add(Vector3D first, Vector3D second) {
        Vector3D res = new Vector3D(first);
        res.add(second);
        return res;
    }

    public static Vector3D sub(Vector3D first, Vector3D second) {
        Vector3D res = new Vector3D(first);
        res.sub(second);
        return res;
    }
    
    public static Vector3D scale(Vector3D v, double factor) {
        Vector3D res = new Vector3D(v);
        res.scale(factor);
        return res;
    }

    public static Vector3D mul(Vector3D first, Vector3D second) {
        Vector3D res = new Vector3D(first);
        res.mul(second);
        return res;
    }

    public static double dot(Vector3D first, Vector3D second) {
        return first.dot(second);
    }

    public static Vector3D unitVector(Vector3D v) {
        return Vector3D.scale(v,1/v.length());
    }


}
