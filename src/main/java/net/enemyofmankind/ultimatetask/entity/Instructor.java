package net.enemyofmankind.ultimatetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enemyofmankind.ultimatetask.dto.InstructorRequestDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "instructor")
public class Instructor extends Person {

	public Instructor(InstructorRequestDTO instructor) {
		setFirstName(instructor.getFirstName());
		setLastName(instructor.getLastName());
		setAge(instructor.getAge());
		setEmail(instructor.getEmail());
		this.subject = instructor.getSubject();
	}

	public Instructor(Integer id, InstructorRequestDTO instructor) {
		setId(id);
		setFirstName(instructor.getFirstName());
		setLastName(instructor.getLastName());
		setAge(instructor.getAge());
		setEmail(instructor.getEmail());
		this.subject = instructor.getSubject();
	}

	@Column (name = "subject" , nullable = false)
	private String subject;

	@ManyToMany (fetch= FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="instructor_student", joinColumns=@JoinColumn(name="instructor_id"), inverseJoinColumns=@JoinColumn(name="student_id"))
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student theStudent) {

		if (students == null) {
			students = new ArrayList<>();
		}

		students.add(theStudent);
	}

	public void removeStudent(Student theStudent) {

		if (students != null) {
			students.remove(theStudent);
		}
	}
}
