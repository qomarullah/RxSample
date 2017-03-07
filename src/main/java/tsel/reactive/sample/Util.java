package tsel.reactive.sample;

public class Util {

	public static void delay(int s){
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
