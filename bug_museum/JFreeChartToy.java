import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.io.IOException;

public class JFreeChartToy {
	final int N = 100;
	// int N;

	List<Shape> objectArray = new ArrayList<Shape>();
	Shape[] objectArray2;
	int objectCount = 0;

	public JFreeChartToy(int num) {
		// N = num;
		objectArray2 = new Shape[N];
		/*for(int i = 0; i < N; i++) {
		  objectArray.add(new CandleStick(i));
		  objectArray2[i].position = i;
		  }*/
	}


	class Shape {
		int position;

		Shape(int n) {
			position = n;
		}

		double drawItem() {
			return position;
		}
	}

	class CandleStick extends Shape {
		CandleStick(int n) {
			super(n);
		}

		@Override
			double drawItem() {
				// CANDLE_STICK represents the category of CandleStick
				double ret1 = 0;
				int count = 0;
				for(ListIterator<Shape> i = objectArray.listIterator(); i.hasNext();) {
					ret1 += i.next().position;
					count++;
				}
				ret1 /= count;

				double ret2 = 0;
				for(int i = 0; i < objectCount; i++) {
					ret2 += objectArray2[i].position;
				}
				ret2 /= count;

				return ret1;
			}
	}

	class Circle extends Shape{
		Circle(int n) {
			super(n);
		}

		@Override
			double drawItem() {
				return position;
			}
	}

	void render(int numOfShape) throws IOException {
		for(int i = 0; i < numOfShape; i++) {
			System.out.println("Please choose a shape: (1: CandleStick, 2: Circle)");
			int n = System.in.read();
			Shape shape;
			if(n == 1) {
				shape = new CandleStick(i);
			} else if (n == 2) {
				shape = new Circle(i);
			} else {
				System.out.println("Wrong input!");
				break;
			}
			shape.drawItem();
			objectArray.add(shape);
			objectArray2[i] = shape;
			objectCount++;
		}
	}
}
