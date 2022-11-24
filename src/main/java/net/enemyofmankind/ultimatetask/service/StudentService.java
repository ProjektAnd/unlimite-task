package net.enemyofmankind.ultimatetask.service;

import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentRequestDTO;
import net.enemyofmankind.ultimatetask.dto.StudentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService
{
	public List<StudentResponseDTO> findAll(Pageable pageable);

	public StudentResponseDTO findById(Integer studentId);

	public List<StudentResponseDTO> findByFirstName(String firstName, Pageable pageable);

	public List<StudentResponseDTO> findByLastName(String lastName, Pageable pageable);

	public List<StudentResponseDTO> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) ;

	public List<InstructorResponseDTO> findInstructorsById(Integer studentId, Pageable pageable);

	public void save(StudentRequestDTO student);

	public void save(Integer studentId, StudentRequestDTO student);

	public void patchInstructorById(Integer studentId, Integer instructorId);

	public void delete(Integer studentId);

	public void deleteInstructorById(Integer studentId, Integer instructorId);
}
