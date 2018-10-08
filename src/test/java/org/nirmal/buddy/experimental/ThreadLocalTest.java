package org.nirmal.buddy.experimental;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author
 */
public class ThreadLocalTest {

	public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd/MM/yyyy");
		};
	};
	public static void main(String args[]) throws IOException {
		new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		threadLocal.get();
		long t = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			//threadLocal.get().format(d);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.format(d);
		}
		System.out.println("Time: " + (System.currentTimeMillis() - t));
	}

}




