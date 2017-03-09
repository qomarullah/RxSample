package tsel.reactive.sample;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//http://tomstechnicalblog.blogspot.co.id/2015/11/rxjava-achieving-parallelization.html

public class Parallel {
	  public static void main( String[] args )
	    {
	        /*System.out.println( "Hello World!" );
	        Observable<Integer> vals = Observable.range(1,10);
	        vals.subscribe(val -> System.out.println(val));
	        */
	        
	     /*   Observable<Integer> vals = Observable.range(1,10);
	        vals.map(i -> intenseCalculation(i))
	        .subscribe(val -> System.out.println(val));
	       */ 
	      
    	 //no paralel
	        /*Observable<Integer> vals = Observable.range(1,10);
	        vals.subscribeOn(Schedulers.computation())
	                  .map(i -> intenseCalculation(i))
	                  .subscribe(val -> System.out.println("Subscriber received "
	                          + val + " on "
	                          + Thread.currentThread().getName()));
	        */
		  
		
		  /*Observable<Integer> vals = Observable.range(1,10);
		  vals.flatMap(val -> Observable.just(val)
		              .subscribeOn(Schedulers.computation())
		              .map(i -> intenseCalculation(i))
		  ).subscribe(val -> System.out.println(val));
	        
		  */
		  
		  Observable<Integer>vals = Observable.range(1,10);

		  vals.flatMap(val -> Observable.just(val)
		                 .subscribeOn(Schedulers.computation())
		                 .map(i -> intenseCalculation(i))
		       ).toList()
		       .subscribe(val -> System.out.println("Subscriber received "
		                  + val + " on "
		                  + Thread.currentThread().getName()));
		  
	        try {
				Thread.sleep(60_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	  
	  public static int intenseCalculation(int i) {
		    try {
		        System.out.println("Calculating " + i + 
		             " on " + Thread.currentThread().getName());
		        Thread.sleep(randInt(1000,5000));
		        return i;
		    } catch (InterruptedException e) {
		        throw new RuntimeException(e);
		    }
		}

	  public static int randInt(int min, int max) {

		    // NOTE: This will (intentionally) not run as written so that folks
		    // copy-pasting have to think about how to initialize their
		    // Random instance.  Initialization of the Random instance is outside
		    // the main scope of the question, but some decent options are to have
		    // a field that is initialized once and then re-used as needed or to
		    // use ThreadLocalRandom (if using at least Java 1.7).
		    Random rand = new Random();

		    // nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
		}
}
