package pl.edu.agh.school;

import java.io.Serializable;

public class Mark implements Serializable {

	private static final long serialVersionUID = -8070625969786768937L;

	private MarkValue _value;
	private Person _student;

	public Mark(MarkValue value, Person student) {
		_value = value;
		_student = student;
	}

	public MarkValue getValue() {
		return _value;
	}

	public Person getStudent() {
		return _student;
	}
}
