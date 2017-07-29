package myPackage;
import java.util.Stack;
public class ParseDemo {
	
	static String e1="(2+3)";
	static String e2="( 6+10  ) * 2";
	
	public static boolean hasError(String exp){
        int len=exp.length()-1;
        Stack<Character> sc = new Stack<Character>();
        if(exp.charAt(len)=='+' ||exp.charAt(len)=='-' ||exp.charAt(len)=='*' || exp.charAt(len)=='/')
            return true;
//        if(exp.contains("(")&&!exp.contains(")")){
//        	return true;
//        }
//        if(!exp.contains("(")&&exp.contains(")")){
//        	return true;
//        }
        
        
        for (int i = 0; i < exp.length(); i++)
        {
            Character ch = exp.charAt(i);
            if (ch=='(')// 如果是左括号，放入栈中
            {
                sc.push(ch);
            } else if (ch==')') // 如果是右括号
            {
                if (sc.empty()) // 栈为空，栈头没有字符与右括号匹配
                {
                    return true;
                }
                // 栈不为空，栈头字符与右括号匹配
                if (sc.peek() == '(')
                {
                    sc.pop();
                } else //网上许多列子没有这里的else代码块，导致({}[]]])会被判断为true
                { // 栈不为空，栈头字符不与右括号匹配
                    return true;
                }
            }

        }

        return sc.empty() ? false : true;
        

        //return false;
    }
	
	public static void main(String[] args) {
		
		boolean haserror=hasError(e1);
		System.out.println(haserror);
//		Tokenizer tz = new MyTokenizer(e1);
//		Expression pe= Expression.parseExp(tz);
//		Subs subs=new Subs();
//		subs.put("x", 1);
//		System.out.println(pe.show()+"="+pe.evaluate(subs));
		
	}

}
