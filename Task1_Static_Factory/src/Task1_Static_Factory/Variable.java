package Task1_Static_Factory;

public class Variable extends Expression{
    public Variable(String variable) {
        this.left = null;
        this.right = null;
        this.variable = variable;
        this.height = 0;
        this.operators = 0;
        this.size = 1;
    }
    public String show() {
        return this.variable;
    }

    public String simplify() {
        return this.variable;
    }
}
