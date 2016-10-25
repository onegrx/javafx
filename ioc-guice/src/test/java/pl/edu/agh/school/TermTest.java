package pl.edu.agh.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.edu.agh.school.DayOfWeek;
import pl.edu.agh.school.Subject;
import pl.edu.agh.school.Term;

@RunWith(MockitoJUnitRunner.class)
public class TermTest {
	
	private static final int DURATION = 45;
	Term term;
	DayOfWeek day=DayOfWeek.SATURDAY;
	Date date;
	@Mock Subject subject;
	
	@Before
	public void setUp() throws Exception {
		date=new Date();
		term=new Term(day,date,DURATION);
	}

	@Test
	public void testTermData() {
		assertEquals(day,term.getDayOfWeek());
		assertEquals(DURATION,term.getDuration());
		assertEquals(date.getTime(),term.getStartTime().getTime());
	}

	@Test
	public void testGetSubject() {
		assertNull(term.getSubject());
		term.setSubject(subject);
		assertEquals(subject,term.getSubject());
	}

}
