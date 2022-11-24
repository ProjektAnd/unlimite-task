package net.enemyofmankind.ultimatetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enemyofmankind.ultimatetask.dto.StudentRequestDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "student")
public class Student extends Person {

	public Student(StudentRequestDTO student) {
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setAge(student.getAge());
		setEmail(student.getEmail());
		this.fieldOfStudy = student.getFieldOfStudy();
	}

	public Student(Integer id, StudentRequestDTO student) {
		setId(id);
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setAge(student.getAge());
		setEmail(student.getEmail());
		this.fieldOfStudy = student.getFieldOfStudy();
	}

	@Column(name = "field_of_study" , nullable = false)
	private String fieldOfStudy;

	@ManyToMany (fetch= FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="instructor_student", joinColumns=@JoinColumn(name="student_id"), inverseJoinColumns=@JoinColumn(name="instructor_id"))
	private List<Instructor> instructors;

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void addInstructor(Instructor theInstructor) {

		if (instructors == null) {
			instructors = new ArrayList<>();
		}

		instructors.add(theInstructor);
	}

	public void removeInstructor(Instructor theInstructor) {

		if (instructors != null) {
			instructors.remove(theInstructor);
		}
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}
}
