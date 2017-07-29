package myPackage;
import static myPackage.LitExpression.*;
import static myPackage.VarExpression.*;
import static myPackage.AddExpression.*;
import static myPackage.DivExpression.*;
import static myPackage.MultExpression.*;
import static myPackage.SubsExpression.*;

public class ExpressionTrees {


	
	public static void main(String[] args) {
		Subs subs=new Subs();

		
		System.out.println("Test expression:");
		Expression e2= mult(lit(2),add(lit(3),lit(5)));
		System.out.println("Exp1:"+e2.show()+"="+e2.evaluate(subs));
		System.out.println("Size:"+e2.size());
		System.out.println("height:"+e2.height());
		System.out.println("operators:"+e2.operators());
		System.out.println();

		
		Expression e3= add(add(lit(3),lit(2)),lit(7));
		System.out.println("Exp2:"+e3.show()+"="+e3.evaluate(subs));
		System.out.println("Size:"+e3.size());
		System.out.println("height:"+e3.height());
		System.out.println("operators:"+e3.operators());
		System.out.println();

		
		Expression e4= divide(lit(60),subs(lit(7),lit(1)));
		System.out.println("Exp3:"+e4.show()+"="+e4.evaluate(subs));
		System.out.println("Size:"+e4.size());
		System.out.println("height:"+e4.height());
		System.out.println("operators:"+e4.operators());
		System.out.println();

		
//		Expression e5= mult(sub(add(lit(3),lit(2)),lit(1)),div(lit(4),lit(2)));
//		System.out.println(e5.show()+"="+e5.evaluate(subs));
//		System.out.println("Size:"+e5.size());
//		System.out.println("height:"+e5.height());
//		System.out.println("operators:"+e5.operators());
//		System.out.println();
		
		
		
		System.out.println("Test methods:");
//		Expression e= new AddExpression(new MultExpression(new LitExpression(3),new LitExpression(4)), new LitExpression(5));
//		System.out.println(e.show()+"="+e.evaluate(subs));
//		
		//subs.put("x", 0);
//		Expression e2= new AddExpression(new MultExpression(new LitExpression(3),new LitExpression(4)), new MultExpression(new LitExpression(5), new VarExpression("x")));
//		System.out.println(e2.show()+"="+e2.evaluate(subs));
		Expression e0= mult(lit(2),add(lit(3),lit(5)));
		System.out.println(e0.show()+"="+e0.simplify(subs));
		
		System.out.println("Size: " + e0.size());
		System.out.println("height:" + e0.height());
		System.out.println("operator:" + e0.operators());
		System.out.println();
		
		System.out.println("Test variable expressions:");
		Expression e11= add(var("x"), mult(lit(3),lit(2)));
		System.out.println(e11.show()+"="+e11.simplify(subs));
		
		
		Expression e12= add(lit(2),add(lit(5),var("x")));
		System.out.println(e12.show()+"="+e12.simplify(subs));
		
		
		Expression e13= add(add(lit(2),lit(5)),var("x"));
		System.out.println(e13.show()+"="+e13.simplify(subs));
		
		
		
//		System.out.println(e13.show()+"="+e13.evaluate(subs));
//		Expression e1= add(mult(lit(3),lit(4)), lit(5));
//		System.out.println(e1.show()+"="+e1.evaluate(subs));
//		System.out.println("Size:"+e1.size());
//		System.out.println("height:"+e1.height());
//		System.out.println("operators:"+e1.operators());
//		System.out.println(e1.show()+"="+e1.simplify(subs));
//		System.out.println();		
		
		
		
	}

}
