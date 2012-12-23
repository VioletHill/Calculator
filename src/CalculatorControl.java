import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

class CalculatorControl extends Panel
{
	protected JTextField expression;
	protected boolean isFirst;
	protected int lastChar;
	//lastChar 表示上一位是什么0 0  	1~9 1	+-*/ 2	（ 3		）4   
	CalculatorControl()
	{
		expression=new JTextField("0");
	}

	public void getLastChar()
	{
		if (expression.getText().length()==1) isFirst=true;
		else isFirst=false;
		
		char ch=expression.getText().charAt(expression.getText().length()-1);
		if (ch=='0') lastChar=0;
		else if (ch>'0' && ch<='9') lastChar=1;
		else if (ch=='+' || ch=='-' || ch=='*' || ch=='/') lastChar=2;
		else if (ch=='(') lastChar=3;
		else if (ch==')') lastChar=4;
		else //error信息 计算结果出现问题
		{
			clear();
			lastChar=0;
			isFirst=true;
		}
	}
	
	public void clear()
	{
		expression.setText("0");
	}
	
	public void deleteLastChar()
	{
		if (expression.getText().length()==1)
			expression.setText("0");
		else expression.setText(expression.getText().substring(0, expression.getText().length()-1));
	}
	
	ActionListener numberListener = new ActionListener() 
	{
		//数字可以接在 数字 符号 左括号后面
		//不能接在又括号后面
		public void actionPerformed(ActionEvent e) 
		{	
			JButton button=(JButton)e.getSource();
			getLastChar();
			if (lastChar==4) return ;
			if (lastChar==0 && isFirst)
			{
				if (button.getText()=="0") return ;
				else
				{
					expression.setText(button.getText());
				}
			}
			else expression.setText(expression.getText()+button.getText());
		}				
	};
	
	ActionListener signListener=new ActionListener()
	{
		//符号可以接在数字 左括号 右括号后面
		//不可以接在符号后面  和左括号后面
		public void actionPerformed(ActionEvent e)
		{
			JButton button=(JButton)e.getSource();
			getLastChar();
			if (lastChar==3) return ;
			if (lastChar==2)
			{
				deleteLastChar();
				expression.setText(expression.getText()+button.getText());
			}
			else
			{
				expression.setText(expression.getText()+button.getText());
			}
		}
	};
	
	ActionListener backspaceListener=new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if (expression.getText().charAt(expression.getText().length()-1)=='!') clear();
			else 	deleteLastChar();
		}	
	};
	
	ActionListener leftBracketListener=new ActionListener()
	{
		//左括号不可以接在数字 右括号后面
		public void actionPerformed(ActionEvent e)
		{
			getLastChar();
			if (lastChar==0 && isFirst)
			{
				expression.setText("(");
				return ;
			}
			if (lastChar==0 || lastChar==1) return ;
			expression.setText(expression.getText()+'(');
		}
	};
	
	ActionListener rightBracketListener=new ActionListener()
	{
		//右括号可以接在数字，左括号，
		public void actionPerformed(ActionEvent e)
		{
			getLastChar();
			if (lastChar==2) return ;
			expression.setText(expression.getText()+')');
		}
	};
	
	ActionListener equalListener=new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			getLastChar();
			if (lastChar!=4 && lastChar!=0 && lastChar!=1) expression.setText("表达式错误！");
			else
			{
				CalculatorModel calcluatorModel=new CalculatorModel(expression.getText());
				expression.setText(calcluatorModel.calculatorExpression());
			}
		}
	};
	
	ActionListener clearListener=new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			clear();
		}
	};
}
