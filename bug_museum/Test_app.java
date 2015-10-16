public class Test_app {
	static int fun1() {
		int ret = 0;
		while (ret <= 100) {
			System.out.println("1st loop");
			for (int i = 0; i < 200; i++) {
				System.out.println("2nd loop");
				ret += i;
				for (int j = 0; j < 300; j++) {
					fun2(i);
				}
			}
		}
		return ret;
	}

	static int fun2(int n) {
		for (int i = 0; i < 400; i++) {
			for (int j = 0; j < 500; j++) {
				int a = i + j;
			}
		}
		return n / 2;
	}

	static void fun3(int n) {
		for (int i = 0; i < 600; i++) {
			int b = i;
		}
		fun2(n);
	}

	static void fun4() {
		int i = 100;
		int j = 0;
		while (i > 0) {
			i--;
			j++;
		}
	}

	static void fun5() {
		int x = 5;
		int y = 10;
		int z = 7;
		int w = 8;
		if(x > y) {
			z = x + y;
			y = 4;
			w = x - y;
			w = x / 100;
		}
		z = x - 3;
	}

	public static void main(String[] args) {
		fun1();
		// fun2(4);
		// fun3(4);
	}
}
