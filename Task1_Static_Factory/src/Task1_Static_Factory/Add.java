package Task1_Static_Factory;

import java.security.PublicKey;

public class Add extends Expression{
    public Add(Expression e1, Expression e2) {
        this.left = e1;
        this.right = e2;
        this.height = Math.max(e1.height,e2.height) + 1;
        this.operators = e1.operators + e2.operators + 1;
        this.size = e1.size + e2.size + 1;
    }
    public String show() {
        return "(" + left.show() + "+" + right.show() + ")";
    }

    public String simplify() {
        String le = left.simplify();
        String ri = right.simplify();

        if (le.matches("\\d*")&&ri.matches("\\d*")){

            return Integer.toString(Integer.parseInt(le) + Integer.parseInt(ri));
        }


        else
            return "(" + le + "+" + ri + ")";
    }
}
