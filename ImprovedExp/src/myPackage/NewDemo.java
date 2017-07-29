package myPackage;

import static myPackage.Sum.sum;
import static myPackage.Minus.minus;
import static myPackage.LitExp.litt;
import static myPackage.VarExp.var;
import static myPackage.Times.times;
import static myPackage.Divide.divide;



public class NewDemo {
	public static void main(String[] args) {
	
	Subs subs=new Subs();
	//System.out.println("Test expression:");
	Expression e2= sum(litt(2),minus(litt(5),litt(1)));
	
	System.out.println("Exp1:"+e2.show()+"="+e2.evaluate(subs));
	System.out.println("Size:"+e2.size());
	System.out.println("height:"+e2.height());
	System.out.println("operators:"+e2.operators());
	System.out.println();
	
	Expression e3= sum(sum(litt(2),litt(5)),var("x"));
	
	System.out.println("Exp1:"+e3.show()+"="+e3.simplify(subs));
	System.out.println("Size:"+e3.size());
	System.out.println("height:"+e3.height());
	System.out.println("operators:"+e3.operators());
	System.out.println();
	
	Expression e4= sum(times(litt(4),litt(6)),minus(litt(7),litt(1)));
	
	System.out.println("Exp1:"+e4.show()+"="+e4.evaluate(subs));
	System.out.println("Size:"+e4.size());
	System.out.println("height:"+e4.height());
	System.out.println("operators:"+e4.operators());
	System.out.println();
	
	
	
	
	
	
	
	}

}
