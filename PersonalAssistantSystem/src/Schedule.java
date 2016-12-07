import java.io.Serializable;
import java.sql.Date;

public class Schedule implements Serializable {
	private String month;
	private String day;
	private String content;

	public Schedule(String month, String day, String content) {
		this.month = month;
		this.day = day;
		this.content = content;
	}
}
