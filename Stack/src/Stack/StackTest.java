/*
 		2012-12-04   繹먲옙�ワ옙占� */
package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StackTest {
	public static void main(String[] args) throws IOException // main
	{
		boolean roop = true;
		Scanner s = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Stack 占쎈슣�わ옙占쏙옙�뽰삂 占썩뫖�뀐옙占�");
		System.out.print("占싼딆뒠占쎌꼷��Stack占쏙옙占싼덈┛�쒙옙占쎈굝�곤옙占썰틠�깃쉭占쏙옙: ");
		Stacks st = new Stacks(s.nextInt());

		while (roop) {
			try {
				System.out.println();
				System.out.println("#################################");
				System.out.println("1 占쎌쥚源�옙占폩ush占쎌꼹六�");
				System.out.println("2 占쎌쥚源�옙占폩op占쎌꼹六�");
				System.out.println("3 占쎌쥚源�옙占쏙옙袁⑹삺 Stack占쎄낱源�癰귣떯由�");
				System.out.println("4 占쎌쥚源�옙占폮tack 占쎈슣�わ옙占썽넫�낆┷");
				System.out.print("占쎌쥚源�: ");

				int choice = s.nextInt();
				switch (choice) {
				case 1:
					System.out.print("Push占쏙옙�얜챷�꾬옙占쏙옙�낆젾 : ");
					String input = br.readLine();
					st.Push(input);
					break;
				case 2:
					System.out.println("Pop占쏙옙�얜챷�꾬옙占� " + (String) st.Pop());
					break;
				case 3:
					st.Check();
					break;
				case 4:
					roop = false;
					break;
				default:
					System.out.println("占쎌꼶�쏉옙占썼린�딆깈�쒙옙占쎈굝�곤옙�뤿�占쎈벉�뀐옙占�");
				}
			} catch (OverFlowException e) {
				System.out.println(e.getMessage());
			} catch (UnderFlowException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
