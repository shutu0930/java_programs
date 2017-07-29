package Task1_Static_Factory;
import static Task1_Static_Factory.Expression.*;

public class ExpressionDemo {
    public static void main(String [] args){
        Expression e = multi(lit(2),add(lit(3),lit(3)));
        System.out.println(e.show().substring(1,e.show().length()-1));


        System.out.println("The first expression has size " + e.size);
        System.out.println("The first expression has height " + e.height);
        System.out.println("The first expression has operator " + e.operators);

        Expression e1 = multi(lit(2),add(lit(3),lit(2)));
        System.out.println(e1.simplify());
        
        
        



    }
}
