import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.taskmaster.config.DataConfig;
import com.taskmaster.service.TaskService;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
		TaskService ts = context.getBean(TaskService.class);
		//System.out.println(ts.getAllTasksInDay("2015-12-16"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date;
		/*try {
			date = format.parse("2015-14-00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			System.out.println(calendar.get(Calendar.MONTH)+1);
			//System.out.println(calendar.get(Calendar.MONTH));
			//System.out.println(ts.getDaysInMonthWithTask(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+ 1) + "%"));
			System.out.println(ts.getDaysInMonthWithTask("2016-1%"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(ts.getAllTasksInDay("2015-12-31%"));
		
		
		
	}
}
