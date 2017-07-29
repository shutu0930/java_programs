package Task1_Static_Factory;

public class Deduct extends Expression{
    public Deduct(Expression e1, Expression e2) {
        this.left = e1;
        this.right = e2;
        this.height = Math.max(e1.height,e2.height) + 1;
        this.operators = e1.operators + e2.operators + 1;
        this.size = e1.size + e2.size + 1;
    }
    public String show() {
        return "(" + left.show() + "-" + right.show() + ")";
    }

    @Override
    public String simplify() {
        if (left.simplify().matches("[0-9]{1,}")&&right.simplify().matches("[0-9]{1,}"))
            return Integer.toString(Integer.parseInt(left.simplify()) - Integer.parseInt(right.simplify()));
        else
            return "(" + left.simplify() + "-" + right.simplify() + ")";
    }
}
