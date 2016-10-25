package pl.edu.agh.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Student;
import pl.edu.agh.school.Term;

@RunWith(MockitoJUnitRunner.class)
public class StudentTest {

	private static final String STUDENT_SURNAME = "Kowalsky";
	private static final String STUDENT_NAME = "Adam";
	Student student;
	@Mock SchoolClass class1;
	@Mock Term term1;
	
	@Before
	public void setUp() throws Exception {
		student=new Student(STUDENT_NAME, STUDENT_SURNAME);
		List<Term> schedule=new ArrayList<Term>();
		schedule.add(term1);
		when(class1.getSchedule()).thenReturn(schedule);
	}

	@Test
	public void testSetSchoolClass() {
		student.setSchoolClass(class1);
		student.getSchedule();
	}

	@Test
	public void testGetName() {
		assertEquals(STUDENT_NAME,student.getName());
		assertEquals(STUDENT_SURNAME,student.getSurname());
	}

	@Test
	public void testGetSchedule() {
		assertNull(student.getSchedule());
		student.setSchoolClass(class1);
		Collection<Term> schedule=student.getSchedule();
		assertTrue(schedule.contains(term1));
	}

}
