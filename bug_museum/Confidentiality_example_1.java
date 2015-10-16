// This example illustrates the problem of confidentiality.
public class Confidentiality_example_1 {
	// The execution time is directly proportional to the number of '1's in e, which reveals sensitive information to attackers.
	int squareMult(int x, boolean[] e, int n, int N) {
		int y = 1;
		for(int i = n - 1; i >= 0; --i) {
			y = y * y;
			y = y % N;
			if(e[i] == true) {
				y = y * x;
				y = y % N;
			}
		}
		return y;
	}
}
