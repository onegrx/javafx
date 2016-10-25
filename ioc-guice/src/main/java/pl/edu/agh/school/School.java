package pl.edu.agh.school;

import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class School {

	private ArrayList<Teacher> teachers;
	private ArrayList<SchoolClass> classes;

	private SerializablePersistenceManager manager;

	public School() {
		manager = new SerializablePersistenceManager();
		teachers = manager.loadTeachers();
		classes = manager.loadClasses();
	}

	public void addTeacher(Teacher teacher) {
		if (!teachers.contains(teacher)) {
			teachers.add(teacher);
			manager.saveTeachers(teachers);
			Logger.getInstance().log("Added " + teacher.toString());
		}
	}

	public Collection<Person> findPerson(String name, String surname) {
		Collection<Person> foundPersons = findPerson(name, surname,
				PersonType.Student);
		foundPersons.addAll(findPerson(name, surname, PersonType.Teacher));
		return foundPersons;
	}

	public Collection<Person> findPerson(String name, String surname,
			PersonType personType) {
		ArrayList<Person> foundTeachers = new ArrayList<Person>();
		if (personType == PersonType.Teacher) {
			for (Teacher teacher : teachers) {
				if (teacher.meetsSearchCriteria(name, surname)) {
					foundTeachers.add(teacher);
				}
			}
		} else if (personType == PersonType.Student) {
			for (SchoolClass schoolClass : classes) {
				for (Student student : schoolClass.getStudents()) {
					if (student.meetsSearchCriteria(name, surname)) {
						foundTeachers.add(student);
					}
				}
			}
		}
		return foundTeachers;
	}

	public void addClass(SchoolClass newClass) {
		if (!classes.contains(newClass)) {
			classes.add(newClass);
			manager.saveClasses(classes);
			Logger.getInstance().log("Added " + newClass.toString());
		}
	}

	public Collection<SchoolClass> findClass(String name, String profile) {
		ArrayList<SchoolClass> foundClasses = new ArrayList<SchoolClass>();
		for (SchoolClass schoolClass : classes) {
			if (schoolClass.meetSearchCriteria(name, profile)) {
				foundClasses.add(schoolClass);
			}
		}
		return foundClasses;
	}

	public int getTermCount(Person person) {
		return person.getSchedule().size();
	}

}
