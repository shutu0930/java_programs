package Task1_Static_Factory;

import java.util.HashMap;

public abstract class Expression {
    public Expression left, right;
    public Integer num;
    public Integer height;
    public Integer operators;
    public Integer size;
    public HashMap<String, Integer> map;
    public String variable;

    public abstract String show();
    public abstract String simplify();


    public static Expression add(Expression r1, Expression r2){
        return new Add(r1,r2);
    }
    public static Expression multi(Expression r1, Expression r2){
        return new Multi(r1,r2);
    }
    public static Expression deduct(Expression r1, Expression r2){
        return new Deduct(r1,r2);
    }
    public static Expression divide(Expression r1, Expression r2){
        return new Divide(r1,r2);
    }
    public static Expression lit(int input){
        return new Lit(input);
    }
    public static Expression variable(String variable){ return new Variable(variable); }
    public static Expression evaluate(HashMap<String, Integer> map,String variable){ return new Evaluate(map,variable);}




}
