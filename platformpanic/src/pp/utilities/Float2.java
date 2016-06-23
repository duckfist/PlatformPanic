package pp.utilities;

public class Float2 {
    public float x;
    public float y;

    public Float2() {
        this(0.0f, 0.0f);
    }

    public Float2(Float2 point) {
        this(point.x, point.y);
    }

    public Float2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setLoc(Float2 point) {
        this.setLoc(point.x, point.y);
    }

    public void setLoc(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float delta_x, float delta_y) {
        this.setLoc(this.x + delta_x, this.y + delta_y);
    }

    public void translate(Float2 delta) {
        this.setLoc(this.x + delta.x, this.y + delta.y);
    }

    public void scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public float magnitude() {
        if (this.x == 0.0f && this.y == 0.0f) {
            return 0.0f;
        }
        Double result = new Double(Math.sqrt(this.x * this.x + this.y * this.y));
        return result.floatValue();
    }

    public static float dotProduct(Float2 vector1, Float2 vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }

    public static float angleBetween(Float2 vector1, Float2 vector2) {
        float denominator = vector1.magnitude() * vector2.magnitude();
        if (denominator == 0.0f) {
            return 0.0f;
        }
        float numerator = Float2.dotProduct(vector1, vector2);
        float theta = (float)Math.acos(numerator / denominator);
        return theta;
    }

    public Float2 getCopy() {
        return new Float2(this);
    }

    public String toString() {
        return new String(String.format("[x=%1.3f,y=%1.3f]", Float.valueOf(this.x), Float.valueOf(this.y)));
    }
}

