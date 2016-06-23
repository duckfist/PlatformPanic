package pp.utilities;

public class FloatRect {
    public float x;
    public float y;
    public float width;
    public float height;

    public float getRight() {
        return this.x + this.width;
    }

    public float getDown() {
        return this.y + this.height;
    }

    public FloatRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public FloatRect(Float2 f2_pos, float width, float height) {
        this(f2_pos.x, f2_pos.y, width, height);
    }

    public FloatRect intersection(FloatRect other_rect) {
        float tx1 = this.x;
        float ty1 = this.y;
        float rx1 = other_rect.x;
        float ry1 = other_rect.y;
        float tx2 = tx1;
        tx2 += this.width;
        float ty2 = ty1;
        ty2 += this.height;
        float rx2 = rx1;
        rx2 += other_rect.width;
        float ry2 = ry1;
        ry2 += other_rect.height;
        if (tx1 < rx1) {
            tx1 = rx1;
        }
        if (ty1 < ry1) {
            ty1 = ry1;
        }
        if (tx2 > rx2) {
            tx2 = rx2;
        }
        if (ty2 > ry2) {
            ty2 = ry2;
        }
        return new FloatRect(tx1, ty1, tx2 -= tx1, ty2 -= ty1);
    }

    public boolean intersects(FloatRect r) {
        float tw = this.width;
        float th = this.height;
        float rw = r.width;
        float rh = r.height;
        if (rw <= 0.0f || rh <= 0.0f || tw <= 0.0f || th <= 0.0f) {
            return false;
        }
        float tx = this.x;
        float ty = this.y;
        float rx = r.x;
        float ry = r.y;
        rh += ry;
        tw += tx;
        th += ty;
        return !((rw += rx) >= rx && rw <= tx || rh >= ry && rh <= ty || tw >= tx && tw <= rx || th >= ty && th <= ry);
    }

    public boolean isEmpty() {
        return this.width <= 0.0f || this.height <= 0.0f;
    }

    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void translate(Float2 dxdy) {
        this.translate(dxdy.x, dxdy.y);
    }
}

