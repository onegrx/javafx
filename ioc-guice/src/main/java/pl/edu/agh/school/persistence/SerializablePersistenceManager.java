package pl.edu.agh.school.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;

public final class SerializablePersistenceManager {

	private static Logger log = Logger.getLogger(SerializablePersistenceManager.class.getName());

	private String teachersStorageFileName;

	private String classStorageFileName;

	public SerializablePersistenceManager() {
		teachersStorageFileName = "teachers.dat";
		classStorageFileName = "classes.dat";
	}

	public void saveTeachers(ArrayList<Teacher> teachers) {
		if (teachers == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
			oos.writeObject(teachers);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while saving the teachers data", e);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Teacher> loadTeachers() {
		ArrayList<Teacher> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {

			res = (ArrayList<Teacher>) ios.readObject();
		} catch (FileNotFoundException e) {
			res = new ArrayList<Teacher>();
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while loading the teachers data", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}

	public void saveClasses(ArrayList<SchoolClass> classes) {
		if (classes == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {

			oos.writeObject(classes);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while saving the classes data", e);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SchoolClass> loadClasses() {
		ArrayList<SchoolClass> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
			res = (ArrayList<SchoolClass>) ios.readObject();
		} catch (FileNotFoundException e) {
			res = new ArrayList<SchoolClass>();
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while loading the classes data", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}
}
