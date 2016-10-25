package pl.edu.agh.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Teacher extends Person implements ITeacher, Serializable {

	private static final long serialVersionUID = -5057727100439049074L;

	private ArrayList<Subject> _subjects = new ArrayList<Subject>();

	public Teacher(String name, String surname) {

		super(name, surname);
		personType = PersonType.Teacher;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void addSubject(Subject newSubject) {
		if (!_subjects.contains(newSubject)) {
			_subjects.add(newSubject);
		}
	}

	public Collection<Term> getSchedule() {
		ArrayList<Term> schedule = new ArrayList<Term>();
		for (Subject subject : _subjects) {
			schedule.addAll(subject.getSchedule());
		}
		return schedule;
	}

}
