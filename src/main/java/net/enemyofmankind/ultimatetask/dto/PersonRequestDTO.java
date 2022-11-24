package net.enemyofmankind.ultimatetask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class PersonRequestDTO
{
	@Size (min = 3, max = 60, message = "The length of first name must be between 3 and 60 characters")
	private String firstName;

	@Size (min = 3, max = 60, message = "The length of last name must be between 3 and 60 characters")
	private String lastName;

	@Min (value = 19, message = "Pearson should be at least 19 years old")
	private Short age;

	@Size(min = 5 , max = 100)
	@Email (regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,16}", flags = Pattern.Flag.CASE_INSENSITIVE , message = "Email domain address is invalid")
	private String email;
}
