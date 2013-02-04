package Factorial;

import java.util.Scanner;

public class FactorialTest {
	public static void main(String[] args) {
		boolean roof = true;
		Scanner s = new Scanner(System.in);
		Factorials fac = new Factorials();
		System.out.println("Factorial 占쎈슣�わ옙占쏙옙�뽰삂 占썩뫖�뀐옙占�");
		while (roof) {
			try {
				System.out.println("1 占쎌쥚源�옙占폝actorial �닌뗫릭疫뀐옙");
				System.out.println("2 占쎌쥚源�옙占쏙옙��뮞占쏙옙�ル굝利�");
				System.out.print("占쎌쥚源�: ");
				String choice = s.next();
				if (choice.matches("\\d")) {					
					switch (Integer.parseInt(choice)) {
					case 1:
						System.out.print("n!占쏙옙n占쏙옙占쎈굝�곤옙�곻폒占쎈챷��: ");
						String input = s.next();
						if (input.matches("\\d+")) {
							System.out.println(input + "! ="+ fac.factorial(Long.parseLong(input)));
						} else {
							System.out.println("占썬꺃�꾤몴占쏙옙�낆젾占쎈똻竊쒙옙紐꾩뒄.");
						}
						break;
					case 2:
						roof = false;
						break;
					default:
						System.out.println("1占쎈�援�2�쒙옙占쎈굝�곤옙占썰틠�깃쉭占쏙옙");
					}
					
				}else{
					System.out.println("占썬꺃�꾤몴占쏙옙�낆젾占쏙옙雅뚯눘苑�옙占�");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
