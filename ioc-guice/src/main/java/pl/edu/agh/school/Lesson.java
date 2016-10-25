package pl.edu.agh.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Lesson implements Serializable {

	private static final long serialVersionUID = -1645504644575582072L;

	private Subject _subject;
	private Calendar _date;

	private ArrayList<Student> _presentStudents = new ArrayList<Student>();

	public Lesson(Subject subject) {
		_subject = subject;
		_date = Calendar.getInstance();
	}

	public Subject getSubject() {
		return _subject;
	}

	public Calendar getDate() {
		return _date;
	}

	public void registerPresence(Student student) {
		if (!_presentStudents.contains(student)) {
			_presentStudents.add(student);
		}
	}

	public boolean isPresent(Student student) {
		return _presentStudents.contains(student);
	}

}
