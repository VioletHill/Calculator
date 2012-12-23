import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


class CalculatorViewPanel extends CalculatorControl
{
	public static final int numberCount=10; 
	
	public static final int Hgap=10;
	public static final int Vgap=10;
	
	public static final int buttonSizeWide=50;
	public static final int buttonSizeHigh=50;
	public static final Dimension buttonSize=new Dimension(buttonSizeWide,buttonSizeHigh);
	JButton []num=new JButton [numberCount];
	JButton []sign=new JButton[4];
	JButton equal=new JButton("=");
	JButton leftBracket=new JButton("(");
	JButton	rightBracket=new JButton(")");
	JButton backspace=new JButton("<=");
	JButton clear=new JButton("CE");
	
	
	CalculatorViewPanel()
	{
		//布局
		FlowLayout flowLayout=new FlowLayout();
		flowLayout.setHgap(Hgap);
		flowLayout.setVgap(Vgap);
		setLayout(flowLayout);
		
		//数字键
		for (int i=0; i<numberCount; i++)
		{
				num[i]=new JButton(""+i);
				num[i].setPreferredSize(buttonSize);
				num[i].addActionListener(numberListener);
		}
		num[0].setPreferredSize(new Dimension(buttonSizeWide*2+Vgap,buttonSizeHigh));
		
		//符号键
		sign[0]=new JButton("+");
		sign[1]=new JButton("-");
		sign[2]=new JButton("*");
		sign[3]=new JButton("/");
		
		for (int i=0; i<4; i++)
		{
			sign[i].setPreferredSize(buttonSize);
			sign[i].addActionListener(signListener);
		}
		
		//括号
		leftBracket.setPreferredSize(buttonSize);
		leftBracket.addActionListener(leftBracketListener);
		rightBracket.setPreferredSize(buttonSize);
		rightBracket.addActionListener(rightBracketListener);
		

		//CE
		clear.setPreferredSize(buttonSize);
		clear.addActionListener(clearListener);
		//退格
		backspace.setPreferredSize(buttonSize);
		backspace.addActionListener(backspaceListener);
		//文本显示内容
		expression.setPreferredSize(new Dimension(buttonSizeWide*4+3*Vgap,buttonSizeHigh));
		expression.setHorizontalAlignment(JTextField.RIGHT);
		expression.setEditable(false);
		//等号
		equal.setPreferredSize(buttonSize);
		equal.addActionListener(equalListener);
		
		add(expression);
		add(clear);			add(leftBracket);	add(rightBracket);	add(backspace);
		add(num[1]);		add(num[2]);		add(num[3]);		add(sign[0]);
		add(num[4]);		add(num[5]);		add(num[6]);		add(sign[1]);
		add(num[7]);		add(num[8]);		add(num[9]);		add(sign[2]);
		add(num[0]);							add(equal);			add(sign[3]);
	}
	
}



class CalculatorView extends JFrame
{
	CalculatorViewPanel numberPanel=new CalculatorViewPanel();
	
	CalculatorView()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("计算器");
		//setLayout(layout);
	//	add(a,BorderLayout.NORTH);
	//	add(b,BorderLayout.SOUTH);
		add(numberPanel);//,BorderLayout.CENTER);
	//	add(numberPanel,BorderLayout.NORTH);
		//flowLayout.
		
	
		setSize(CalculatorViewPanel.buttonSizeWide*4+CalculatorViewPanel.Hgap*5+10,
				CalculatorViewPanel.buttonSizeHigh*6+CalculatorViewPanel.Hgap*10+10);
		this.setResizable(false);
		setVisible(true);
	}
}