package net.enemyofmankind.ultimatetask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enemyofmankind.ultimatetask.entity.Instructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorResponseDTO extends PersonResponseDTO
{
	private String subject;

	public InstructorResponseDTO(Instructor instructor) {
		setId(instructor.getId());
		setFirstName(instructor.getFirstName());
		setLastName(instructor.getLastName());
		setAge(instructor.getAge());
		setEmail(instructor.getEmail());
		this.subject = instructor.getSubject();
	}
}
