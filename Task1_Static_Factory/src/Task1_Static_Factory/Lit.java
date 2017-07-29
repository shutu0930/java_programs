package Task1_Static_Factory;

public class Lit extends Expression{
    public Lit(int input) {
        this.left = null;
        this.right = null;
        this.num = input;
        this.height = 0;
        this.operators = 0;
        this.size = 1;
    }
    public String show() {
        return this.num.toString();
    }

    public String simplify() {
        return this.num.toString();
    }
}
