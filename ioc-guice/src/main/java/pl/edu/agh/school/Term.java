package pl.edu.agh.school;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Term implements Serializable {

	private static final long serialVersionUID = -5531282222177760431L;

	private DayOfWeek _dayOfWeek;
	private Date _startTime;
	private int _durationInMinutes;
	private Subject _subject;

	public Term(DayOfWeek dayOfWeek, Date hour, int durationInMinutes) {
		_dayOfWeek = dayOfWeek;
		_startTime = hour;
		_durationInMinutes = durationInMinutes;
	}

	public DayOfWeek getDayOfWeek() {
		return _dayOfWeek;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public long getDuration() {
		return _durationInMinutes;
	}

	public void setSubject(Subject subject) {
		_subject = subject;
	}

	public Subject getSubject() {
		return _subject;
	}

	@Override
	public String toString() {
		DateFormat timeFormat = new SimpleDateFormat("hh:mm");
		return "term of " + _subject + " on " + _dayOfWeek + " at "
				+ timeFormat.format(_startTime) + ", " + _durationInMinutes
				+ " min";
	}

}
