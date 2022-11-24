package net.enemyofmankind.ultimatetask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class Person
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id" , nullable = false)
	private Integer id;

	@Column(name = "first_name" , nullable = false)
	private String firstName;

	@Column(name = "last_name" , nullable = false)
	private String lastName;

	@Column(name = "age" , nullable = false)
	private Short age;

	@Column(name = "email" , nullable = false)
	private String email;
}
