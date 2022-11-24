package net.enemyofmankind.ultimatetask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class PersonResponseDTO
{
	private Integer id;
	private String firstName;
	private String lastName;
	private Short age;
	private String email;
}
