package Task1_Static_Factory;

import java.util.HashMap;

public class Evaluate extends Expression{
    public Evaluate(HashMap<String, Integer> map, String variable) {
        this.left = null;
        this.right = null;
        this.num = map.get(variable);
    }
    public String show() {
        return this.num.toString();
    }

    @Override
    public String simplify() {
        return this.num.toString();
    }
}
