package net.enemyofmankind.ultimatetask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enemyofmankind.ultimatetask.entity.Instructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorRequestDTO extends PersonRequestDTO
{
	@Size (min = 3, max = 120, message = "The length of subject must be between 3 and 120 characters")
	private String subject;

	public InstructorRequestDTO(Instructor instructor) {
		setFirstName(instructor.getFirstName());
		setLastName(instructor.getLastName());
		setAge(instructor.getAge());
		setEmail(instructor.getEmail());
		this.subject = instructor.getSubject();
	}
}
