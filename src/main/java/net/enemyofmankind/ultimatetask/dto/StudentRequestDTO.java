package net.enemyofmankind.ultimatetask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enemyofmankind.ultimatetask.entity.Student;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDTO extends PersonRequestDTO
{
	@Size (min = 3, max = 120, message = "The length of field of study must be between 3 and 120 characters")
	private String fieldOfStudy;

	public StudentRequestDTO(Student student) {
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setAge(student.getAge());
		setEmail(student.getEmail());
		this.fieldOfStudy = student.getFieldOfStudy();
	}
}
