package Stack;

class Stacks {
	private int top = 0; 
	private String[] st = null; 

	public Stacks(int i) {
		this.top = 0;
		this.st = new String[i + 1]; 
	}

	public void Push(String str) throws OverFlowException 
	{
		if (top + 1 >= st.length) {
			throw new OverFlowException();
		} else {
			top++;
			st[top] = str;
			System.out.println("Stack�� + top  : " + st[top]);
		}
	}

	public String Pop() throws UnderFlowException 
	{
		String temp = "";
		if (top == 0) {
			throw new UnderFlowException();
		} else {
			temp = st[top];
			st[top] = null;
			top--;
		}
		return temp;
	}

	public void Check() 
	{
		System.out.println("< �꾩옱 Stack���곹깭 >");
		for (int i = top; i > 0; i--) {
			System.out.println(st[i]);
		}
		System.out.println("---------------------");
	}
}