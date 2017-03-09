package tsel.reactive.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

public class TestHttp {

	Util u;
	String url1="http://localhost/sample/sleep.php?sleep=2";
	String url2="http://localhost/sample/sleep.php?sleep=5";
	String url3="http://localhost/sample/sleep.php?sleep=3";
	
	
	public TestHttp(){
		this.u= new Util();
	}
	
	
	public String getUrl (){
		
		Observable<Todo> todoObservable = Observable.create(emitter -> {
			 try {
	                List<Todo> todos = getTodos();
	                for (Todo todo : todos) {
	                       emitter.onNext(todo);
	                }
	                emitter.onComplete();
	        } catch (Exception e) {
	                emitter.onError(e);
	        }
		});
		
		Disposable disposable = todoObservable.subscribe(t -> System.out.print("===>"+t.getTitle()));
		disposable.dispose();
		System.out.println("aaaaaaaaaaaaaaa");
		
		return "ok";
	}
	
	public List<Todo> getTodos() {

        List<Todo> todos = new ArrayList();

        for(int i=0;i<10;i++){
        	
        	
        	Todo e=new Todo();
        	String url=url1;
        	if(i%2==0)url=url1;
        	
        	String resp;
			try {
				resp = u.sendGet(url);
				e.setId(i);
	        	e.setTitle(resp);
	        	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        	todos.add(e);
        	
        }
        
        return todos;
	}

}
