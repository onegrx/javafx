package pl.edu.agh.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import pl.edu.agh.logger.Logger;

public class Subject implements Serializable {

	private static final long serialVersionUID = 5342955138128716653L;

	private String _name;

	private ArrayList<Term> _terms = new ArrayList<Term>();
	private ArrayList<Mark> _marks = new ArrayList<Mark>();
	private ArrayList<Mark> _semesterMarks = new ArrayList<Mark>();
	private ArrayList<Lesson> _lessons = new ArrayList<Lesson>();

	private Teacher _teacher;

	public Subject(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void addTerm(Term newTerm) {
		if (!_terms.contains(newTerm)) {
			_terms.add(newTerm);
			newTerm.setSubject(this);
			Logger.getInstance().log(
					"Added " + newTerm.toString() + " to " + toString());
		}
	}

	public Collection<Term> getSchedule() {
		return _terms;
	}

	public void addMark(Mark mark) {
		_marks.add(mark);
	}

	public void addSemesterMark(Mark mark) {
		_semesterMarks.add(mark);
	}

	public void addLesson(Lesson lesson) {
		_lessons.add(lesson);
	}

	public void setTeacher(Teacher teacher) {
		_teacher = teacher;
		_teacher.addSubject(this);
	}

	public Teacher getTeacher() {
		return _teacher;
	}

	public Collection<Mark> getMarks() {
		return Collections.unmodifiableCollection(_marks);
	}

	public Collection<Mark> getSemesterMarks() {
		return Collections.unmodifiableCollection(_semesterMarks);
	}

	public Collection<Lesson> getLessons() {
		return Collections.unmodifiableCollection(_lessons);
	}

	@Override
	public String toString() {
		return "subject " + _name;
	}

}
