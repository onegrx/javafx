package pl.edu.agh.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LessonTest {

	Lesson lesson;
	@Mock Subject subject;
	@Mock Student student;
	
	@Before
	public void setUp() throws Exception {
		lesson = new Lesson(subject);
	}

	@Test
	public void testGetSubject() {
		assertEquals(subject,lesson.getSubject());
	}

	@Test
	public void testGetDate() {
		assertNotNull(lesson.getDate());
		// check if date is not in the future
		assertFalse(lesson.getDate().before(Calendar.getInstance()));
	}

	@Test
	public void testRegisterPresence() {
		lesson.registerPresence(student);
		assertTrue(lesson.isPresent(student));
		
	}

}
