import java.util.Stack;

public class CalculatorModel
{
	Stack<Long> num=new Stack<Long>();
	Stack<Character> sign=new Stack<Character>();
	String expression;
	
	CalculatorModel(String str)
	{
		expression=str;
	}
	private boolean checkBracket()
	{
		int count=0;
		for (int i=0; i<expression.length(); i++)
		{
			char ch=expression.charAt(i);
			if (ch=='(') count++;
			if (ch==')') count--;
			if (count<0) return false;
		}
		if (count!=0) return false;
		else return true;
	}
	
	private int getPriority(char ch)
	{
		switch (ch)
		{
		case '(':
			return 1;
		case '*':
			return 3;
		case '/':
			return 3;
		case '+':
			return 2;
		case '-':
			return 2;
		default:
			return 0;
		}
	}
	
	private Long result(Long a, Long b,char sign)
	{
		switch (sign)
		{
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		case '/':
			return a/b;
		default:
			return null;
		}
	}
	
	public String calculatorExpression()
	{
		expression='('+expression+')';
		if (!checkBracket())	return "括号不匹配!";
		
		int lastsign=-1;
		char signTop;
		Long a,b;
		for (int i=0; i<expression.length(); i++)
		{
			char ch=expression.charAt(i);
			if (ch>='0' && ch<='9')		continue;
			
			if (lastsign+1!=i) num.push(Long.valueOf(expression.substring(lastsign+1, i)));
			lastsign=i;
			
			int priority=getPriority(ch);
			if (ch==')')
			{
				while (sign.get(sign.size()-1)!='(' )
				{
					b=num.pop();
					a=num.pop();
					signTop=sign.pop();
					if (signTop=='/' && b==0) return "除数不能为0!";
					num.push(result(a,b,signTop));
				}
				sign.pop();
				
			}
			else if (ch=='(') sign.push(ch);
			else 
			{
				while (getPriority(sign.get(sign.size()-1))>=priority)
				{
					b=num.pop();
					a=num.pop();
					signTop=sign.pop();
					if (signTop=='/' && b==0) return "除数不能为0";
					num.push(result(a,b,signTop));
				}
				sign.push(ch);
			}
			
		}
		return String.valueOf(num.pop());
	}
}
