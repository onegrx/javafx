package pl.edu.agh.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.logger.Logger;

public class SchoolClass implements Serializable {

	private static final long serialVersionUID = -1458264557391305041L;

	private String _name;
	private String _profile;

	private ArrayList<Student> _students = new ArrayList<Student>();
	private ArrayList<Subject> _subjects = new ArrayList<Subject>();

	public SchoolClass(String name, String profile) {
		_name = name;
		_profile = profile;
	}

	public String getName() {
		return _name;
	}

	public String getProfile() {
		return _profile;
	}

	@Override
	public String toString() {
		return "class " + _name + ", profile " + _profile;
	}

	public void addSubject(Subject subject) {
		if (!_subjects.contains(subject)) {
			_subjects.add(subject);
			Logger.getInstance().log(
					"Added " + subject.toString() + " to " + this.toString());
		}
	}

	public Collection<Subject> getSubjects() {
		return _subjects;
	}

	public void addStudent(Student student) {
		if (!_students.contains(student)) {
			_students.add(student);
			student.setSchoolClass(this);
			Logger.getInstance().log(
					"Added " + student.toString() + " to class "
							+ this.toString());
		}
	}

	public Collection<Student> getStudents() {
		return _students;
	}

	public Collection<Term> getSchedule() {
		Collection<Term> terms = new ArrayList<Term>();
		for (Subject subject : _subjects) {
			terms.addAll(subject.getSchedule());
		}
		return terms;
	}

	public boolean meetSearchCriteria(String name, String profile) {
		return _name.equals(name) && _profile.equals(profile);
	}

}
