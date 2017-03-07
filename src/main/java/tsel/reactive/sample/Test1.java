package tsel.reactive.sample;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//https://www.infoq.com/articles/rxjava2-by-example
			
		Observable<String> howdy = Observable.just("Howdy!");
		howdy.subscribe(System.out::println);
		
		//overload
		Observable.just("Hello", "World").subscribe(System.out::println);
		//list
		List<String> words = Arrays.asList(
				 "the",
				 "quick",
				 "brown",
				 "fox",
				 "jumped",
				 "over",
				 "the",
				 "lazy",
				 "dogs"
				);

				Observable.just(words)
				          .subscribe(System.out::println);
				
				//emit per item
				Observable.fromIterable(words)
		          .subscribe(System.out::println);
				
				//range and zip
				Observable.range(1, 5).subscribe(System.out::println);
				
				//zip combines the elements of the source stream with the elements of a supplied stream, using a pairwise “zip
				Observable.fromIterable(words)
				 .zipWith(Observable.range(1, Integer.MAX_VALUE), 
				     (string, count)->String.format("%2d. %s", count, string))
				 .subscribe(System.out::println);
				
				//take emmission from observable then flattens the emissions from all of those into a single Observable.
				Observable.fromIterable(words)
				 .flatMap(word -> Observable.fromArray(word.split("")))
				 .zipWith(Observable.range(1, Integer.MAX_VALUE),
				   (string, count) -> String.format("%2d. %s", count, string))
				 .subscribe(System.out::println);
				
				//disctinct letter
				Observable.fromIterable(words)
				 .flatMap(word -> Observable.fromArray(word.split("")))
				 .distinct()
				 .zipWith(Observable.range(1, Integer.MAX_VALUE),
				   (string, count) -> String.format("%2d. %s", count, string))
				 .subscribe(System.out::println);
				
				//sort
				Observable.fromIterable(words)
				 .flatMap(word -> Observable.fromArray(word.split("")))
				 .distinct()
				 .sorted()
				 .zipWith(Observable.range(1, Integer.MAX_VALUE),
				   (string, count) -> String.format("%2d. %s", count, string))
				 .subscribe(System.out::println);
				
				
				
				///////Tick Tock
				Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);//wekeend
				Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);//weekdays
				
				
				Observable<Long> clock = Observable.merge(
					       slow.filter(tick-> isSlowTickTime()),
					       fast.filter(tick-> !isSlowTickTime())
					);
				
				clock.subscribe(tick-> System.out.println(new Date()));
				
				
				//not exit
				try {
					Thread.sleep(60_000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private static long start = System.currentTimeMillis();
	
	public static Boolean isSlowTickTime() {
	   return (System.currentTimeMillis() - start) % 30_000 >= 15_000;
	}
	

}
