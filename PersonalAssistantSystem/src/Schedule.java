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
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		String description;
		description = month + "/" + day;
		description += ": " + content + "\n";
		return description;
	}	
}