package tsel.reactive.sample;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ScatterGather {

	private final static Logger logger = Logger.getLogger(App.class.getName());

	 public void testSequentialScatterGather() throws Exception {
		 long s1=System.currentTimeMillis();
			
		 List<String> list =
    	   IntStream.range(0, 5)
    	     .boxed()
    	     .map(this::generateTask)
    	     .collect(Collectors.toList());

    	 logger.info(list.toString());
    	 logger.info("RT:"+(System.currentTimeMillis()-s1)+"");
				
    	}

    	public void testObservable() throws Exception {
    		long s1=System.currentTimeMillis();
			
    		ExecutorService executors = Executors.newCachedThreadPool();
        	List<Observable<String>> obs = IntStream.range(0, 5)
    		            .boxed()
    		            .map(i -> generateTask(i, executors)).collect(Collectors.toList());

    		Observable<List<String>> merged = Observable.merge(obs).toList();
    		merged.subscribe(l -> logger.info(l.toString()));
    		
    		logger.info("RT:"+(System.currentTimeMillis()-s1)+"");
			
       	}

	private String generateTask(int i) {
		Util.delay(1000);
		return i + "-" + "test";
	}
       	public Observable<String> generateTask(int i, ExecutorService executorService) {
    	    return Observable
    	            .<String>create(s -> {
    	                Util.delay(1000);
    	                if ( i == 2) {
    	                    throw new RuntimeException("Run, it is a 5!");
    	                }
    	                s.onNext( i + "-test");
    	                s.onComplete();
    	            }).onErrorReturn(e -> e.getMessage()).subscribeOn(Schedulers.from(executorService));
    	}
}
