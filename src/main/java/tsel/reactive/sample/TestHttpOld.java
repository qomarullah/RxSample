package tsel.reactive.sample;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TestHttpOld {

	Util u;
	public TestHttpOld(){
		this.u= new Util();
	}
	
	
	public String getUrl (){
		String resp=null;
		
		String url1="http://localhost/sample/sleep.php?sleep=5";
		String url2="http://localhost/sample/sleep.php?sleep=1";
		String url3="http://localhost/sample/sleep.php?sleep=3";
		
		
		Observable<String> a1 = null;
		try {
			a1 = Observable.just(u.sendGet(url1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Observable<String> a2 = null;
		try {
			a2 = Observable.just(u.sendGet(url2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Observable<String> a3 = null;
		try {
			a3 = Observable.just(u.sendGet(url3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*a1.subscribe(t1 -> System.out.print(t1));
		a2.subscribe(t2 -> System.out.print(t2));
		a3.subscribe(t3 -> System.out.print(t3));
*/
		//merge a1,a2,a3
		Observable<String> merge = Observable.merge(a1,a2,a3);
		merge.subscribe(t -> System.out.print(t));
		
		//looping subscribe
		
				
		return "ok";
	}

}
