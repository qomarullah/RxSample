package tsel.reactive.sample;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */

import java.util.logging.Logger;

//assumes the current class is called MyLogger

public class App 

{
	private final static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ScatterGather test=new ScatterGather();
        
        try {
        	//no reactive
			test.testSequentialScatterGather();
			//reactive
			test.testObservable();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
   
}
