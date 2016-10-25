package pl.edu.agh.school;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.edu.agh.school.Mark;
import pl.edu.agh.school.MarkValue;
import pl.edu.agh.school.Student;

@RunWith(MockitoJUnitRunner.class)
public class MarkTest {

	
	Mark mark;
	// cannot mock final classes
	MarkValue value=MarkValue.A;
	@Mock Student student;
	
	@Before
	public void setUp() throws Exception {
		mark=new Mark(value,student);
	}

	@Test
	public void testMark() {
		assertEquals(student,mark.getStudent());
		assertEquals(value,mark.getValue());
	}
	
}
