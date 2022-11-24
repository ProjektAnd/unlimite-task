package net.enemyofmankind.ultimatetask.service;

import net.enemyofmankind.ultimatetask.dao.InstructorRepository;
import net.enemyofmankind.ultimatetask.dao.StudentRepository;
import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentRequestDTO;
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
public class StudentServiceImpl implements StudentService
{
	private final StudentRepository studentRepository;
	private final InstructorRepository instructorRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, InstructorRepository instructorRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.instructorRepository = instructorRepository;
		this.modelMapper = modelMapper;
	}

	public List<StudentResponseDTO> findAll(Pageable pageable) {
		List<Student> students = studentRepository.findAll(pageable).getContent();
		if (students.isEmpty()) {
			throw new RecordNotFoundException("Students not found");
		}
		return getStudentResponseDto(students);
	}

	public StudentResponseDTO findById(Integer studentId) {
		Student theStudent = getStudentById(studentId);

		return getStudentResponseDto(theStudent);
	}

	public List<StudentResponseDTO> findByFirstName(String firstName, Pageable pageable) {
		List<Student> students = studentRepository.findByFirstName(firstName, pageable).getContent();
		if (students.isEmpty()) {
			throw new RecordNotFoundException("Student not found: firstName= " + firstName);
		}
		return getStudentResponseDto(students);
	}

	public List<StudentResponseDTO> findByLastName(String lastName, Pageable pageable) {
		List<Student> students = studentRepository.findByLastName(lastName, pageable).getContent();
		if (students.isEmpty()) {
			throw new RecordNotFoundException("Student not found: lastName= " + lastName);
		}
		return getStudentResponseDto(students);
	}

	public List<StudentResponseDTO> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) {
		List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName, pageable).getContent();
		if (students.isEmpty()) {
			throw new RecordNotFoundException("Student not found: firstName= " + firstName + " lastName= " + lastName);
		}
		return getStudentResponseDto(students);
	}

	public List<InstructorResponseDTO> findInstructorsById(Integer studentId, Pageable pageable) {
		Student theStudent = getStudentById(studentId);

		List<InstructorResponseDTO> instructors = theStudent.getInstructors().stream().map(InstructorResponseDTO::new).collect(Collectors.toList());

		// Peggination of nested List
		int start = (int)pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), instructors.size());
		Page<InstructorResponseDTO> peggedInstructors = new PageImpl<>(instructors.subList(start, end), pageable, instructors.size());

		return peggedInstructors.getContent();
	}


	public void save(StudentRequestDTO student) {
		Student theStudent = new Student(student);

		studentRepository.save(theStudent);
	}

	public void save(Integer studentId, StudentRequestDTO student) {
		// PUT (GenerationType.AUTO) Record must exist
		Student theStudent = getStudentById(studentId);

		theStudent = new Student(studentId, student);

		studentRepository.save(theStudent);
	}

	public void patchInstructorById(Integer studentId, Integer instructorId) {
		Student theStudent = getStudentById(studentId);
		Instructor theInstructor = getInstructorById(instructorId);

		if(theStudent.getInstructors().contains(theInstructor)) {
			throw new RecordAlreadyExistsException("The Instructor is already assigned to the Student");
		}

		theStudent.addInstructor(theInstructor);

		studentRepository.save(theStudent);
	}

	public void delete(Integer studentId) {
		Student theStudent = getStudentById(studentId);

		studentRepository.delete(theStudent);
	}

	public void deleteInstructorById(Integer studentId, Integer instructorId) {
		Student theStudent = getStudentById(studentId);
		Instructor theInstructor = getInstructorById(instructorId);

		if(!theStudent.getInstructors().contains(theInstructor)) {
			throw new RecordNotFoundException("The Instructor does not belong to the Student");
		}

		theStudent.removeInstructor(theInstructor);

		studentRepository.save(theStudent);
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

	private StudentResponseDTO getStudentResponseDto(Student theStudent) {
		return modelMapper.map(theStudent, StudentResponseDTO.class);
	}

	private List<StudentResponseDTO> getStudentResponseDto(List<Student> students) {
		return students.stream().map(this::getStudentResponseDto).collect(Collectors.toList());
	}
}
