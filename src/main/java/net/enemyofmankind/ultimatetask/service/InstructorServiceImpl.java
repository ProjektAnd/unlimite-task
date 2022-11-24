package net.enemyofmankind.ultimatetask.service;

import net.enemyofmankind.ultimatetask.dao.InstructorRepository;
import net.enemyofmankind.ultimatetask.dao.StudentRepository;
import net.enemyofmankind.ultimatetask.dto.InstructorRequestDTO;
import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentResponseDTO;
import net.enemyofmankind.ultimatetask.entity.Instructor;
import net.enemyofmankind.ultimatetask.entity.Student;
import net.enemyofmankind.ultimatetask.exception.RecordAlreadyExistsException;
import net.enemyofmankind.ultimatetask.exception.RecordNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService
{
	private final StudentRepository studentRepository;
	private final InstructorRepository instructorRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public InstructorServiceImpl(StudentRepository studentRepository, InstructorRepository instructorRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.instructorRepository = instructorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<InstructorResponseDTO> findAll(Pageable pageable) {
		List<Instructor> instructors = instructorRepository.findAll(pageable).getContent();
		if (instructors.isEmpty()) {
			throw new RecordNotFoundException("Instructors not found");
		}
		return getInstructorResponseDto(instructors);
	}

	@Override
	public InstructorResponseDTO findById(Integer instructorId) {
		Instructor theInstructor = getInstructorById(instructorId);

		return getInstructorResponseDto(theInstructor);
	}

	@Override
	public List<InstructorResponseDTO> findByFirstName(String firstName, Pageable pageable) {
		List<Instructor> instructors = instructorRepository.findByFirstName(firstName, pageable).getContent();
		if (instructors.isEmpty()) {
			throw new RecordNotFoundException("Instructor not found: firstName= " + firstName);
		}
		return getInstructorResponseDto(instructors);
	}

	@Override
	public List<InstructorResponseDTO> findByLastName(String lastName, Pageable pageable) {
		List<Instructor> instructors = instructorRepository.findByLastName(lastName, pageable).getContent();
		if (instructors.isEmpty()) {
			throw new RecordNotFoundException("Instructor not found: lastName= " + lastName);
		}
		return getInstructorResponseDto(instructors);
	}

	@Override
	public List<InstructorResponseDTO> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) {
		List<Instructor> instructors = instructorRepository.findByFirstNameAndLastName(firstName, lastName, pageable).getContent();
		if (instructors.isEmpty()) {
			throw new RecordNotFoundException("Instructor not found: firstName= " + firstName + " lastName= " + lastName);
		}
		return getInstructorResponseDto(instructors);
	}

	@Override
	public List<StudentResponseDTO> findStudentsById(Integer instructorId, Pageable pageable) {
		Instructor theInstructor = getInstructorById(instructorId);

		List<StudentResponseDTO> instructors = theInstructor.getStudents().stream().map(StudentResponseDTO::new).collect(Collectors.toList());

		// Peggination of nested List
		int start = (int)pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), instructors.size());
		Page<StudentResponseDTO> peggedInstructors = new PageImpl<>(instructors.subList(start, end), pageable, instructors.size());

		return peggedInstructors.getContent();
	}

	@Override
	public void save(InstructorRequestDTO instructor) {
		Instructor theInstructor = new Instructor(instructor);

		instructorRepository.save(theInstructor);
	}

	@Override
	public void save(Integer instructorId, InstructorRequestDTO instructor) {
		// PUT (GenerationType.AUTO) Record must exist
		Instructor theInstructor = getInstructorById(instructorId);

		theInstructor = new Instructor(instructorId, instructor);

		instructorRepository.save(theInstructor);
	}

	@Override
	public void patchStudentById(Integer instructorId, Integer studentId) {
		Instructor theInstructor = getInstructorById(instructorId);
		Student theStudent = getStudentById(studentId);

		if(theInstructor.getStudents().contains(theStudent)) {
			throw new RecordAlreadyExistsException("The Student is already assigned to the Instructor");
		}

		theInstructor.addStudent(theStudent);

		instructorRepository.save(theInstructor);
	}

	@Override
	public void delete(Integer instructorId) {
		Instructor theInstructor = getInstructorById(instructorId);

		instructorRepository.delete(theInstructor);
	}

	@Override
	public void deleteStudentById(Integer instructorId, Integer studentId) {
		Instructor theInstructor = getInstructorById(instructorId);
		Student theStudent = getStudentById(studentId);

		if(!theInstructor.getStudents().contains(theStudent)) {
			throw new RecordNotFoundException("The Student does not belong to the Instructor");
		}

		theInstructor.removeStudent(theStudent);

		instructorRepository.save(theInstructor);
	}

	private Student getStudentById(Integer studentId) {
		Optional<Student> student = studentRepository.findById(studentId);

		if (!student.isPresent()) {
			throw new RecordNotFoundException("Student not found: id=" + studentId);
		}

		return student.get();
	}

	private Instructor getInstructorById(Integer instructorId) {
		Optional<Instructor> instructor = instructorRepository.findById(instructorId);
		if (!instructor.isPresent()) {
			throw new RecordNotFoundException("Instructor not found: id=" + instructorId);
		}
		return instructor.get();
	}

	private InstructorResponseDTO getInstructorResponseDto(Instructor theInstructor) {
		return modelMapper.map(theInstructor, InstructorResponseDTO.class);
	}

	private List<InstructorResponseDTO> getInstructorResponseDto(List<Instructor> students) {
		return students.stream().map(this::getInstructorResponseDto).collect(Collectors.toList());
	}
}
