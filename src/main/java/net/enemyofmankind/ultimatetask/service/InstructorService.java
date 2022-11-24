package net.enemyofmankind.ultimatetask.service;

import net.enemyofmankind.ultimatetask.dto.InstructorRequestDTO;
import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstructorService
{
	public List<InstructorResponseDTO> findAll(Pageable pageable);

	public InstructorResponseDTO findById(Integer instructorId);

	public List<InstructorResponseDTO> findByFirstName(String firstName, Pageable pageable);

	public List<InstructorResponseDTO> findByLastName(String lastName, Pageable pageable);

	public List<InstructorResponseDTO> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) ;

	public List<StudentResponseDTO> findStudentsById(Integer instructorId, Pageable pageable);

	public void save(InstructorRequestDTO student);

	public void save(Integer instructorId, InstructorRequestDTO student);

	public void patchStudentById(Integer instructorId, Integer studentId);

	public void delete(Integer instructorId);

	public void deleteStudentById(Integer instructorId, Integer studentId);
}
