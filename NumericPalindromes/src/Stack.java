import java.util.ArrayList;
import java.util.List;

/*
 * This class is the Stack required to be in part of the solution for the project
 */
public class Stack
{
	private List<Integer> stack;
	
	public Stack()
	{
		stack = new ArrayList<Integer>();
	}
	
	public void push(int x)
	{
		stack.add(x);
	}
	
	public int pop() throws IndexOutOfBoundsException
	{
		if(stack.isEmpty())
		{
			throw new IndexOutOfBoundsException("The stack is empty, push a value before popping");
		}
			int y = stack.size()-1;
			int x = stack.get(y);
			stack.remove(y);
			return (x);
	}

}
