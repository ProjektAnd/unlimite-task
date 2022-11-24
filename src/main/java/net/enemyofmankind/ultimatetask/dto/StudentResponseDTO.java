package net.enemyofmankind.ultimatetask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import net.enemyofmankind.ultimatetask.entity.Student;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDTO extends PersonResponseDTO
{
	private String fieldOfStudy;

	public StudentResponseDTO(Student student) {
		setId(student.getId());
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setAge(student.getAge());
		setEmail(student.getEmail());
		this.fieldOfStudy = student.getFieldOfStudy();
	}
}
