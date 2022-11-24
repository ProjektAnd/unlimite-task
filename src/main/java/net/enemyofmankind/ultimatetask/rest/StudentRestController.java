package net.enemyofmankind.ultimatetask.rest;

import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentRequestDTO;
import net.enemyofmankind.ultimatetask.dto.StudentResponseDTO;
import net.enemyofmankind.ultimatetask.entity.Student;
import net.enemyofmankind.ultimatetask.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("api/student")
public class StudentRestController
{

	private final StudentService studentService;

	@Autowired
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping ()
	public ResponseEntity<List<StudentResponseDTO>> get(Pageable pageable) {

		List<StudentResponseDTO> students = studentService.findAll(pageable);

		return ResponseEntity.ok(students);
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<StudentResponseDTO> getById(@PathVariable("studentId") Integer studentId) {

		StudentResponseDTO theStudent = studentService.findById(studentId);

		return ResponseEntity.ok(theStudent);
	}

	@GetMapping(params = { "firstName" })
	public ResponseEntity<List<StudentResponseDTO>> getByFirstName(@RequestParam("firstName") String firstName, Pageable pageable) {

		List<StudentResponseDTO> students = studentService.findByFirstName(firstName, pageable);

		return ResponseEntity.ok(students);
	}

	@GetMapping(params = { "lastName" })
	public ResponseEntity<List<StudentResponseDTO>> getByLastName(@RequestParam("lastName") String lastName, Pageable pageable) {

		List<StudentResponseDTO> students = studentService.findByLastName(lastName , pageable);

		return ResponseEntity.ok(students);
	}

	@GetMapping(params = { "firstName", "lastName" })
	public ResponseEntity<List<StudentResponseDTO>> getByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Pageable pageable) {

		List<StudentResponseDTO> students = studentService.findByFirstNameAndLastName(firstName, lastName, pageable);

		return ResponseEntity.ok(students);
	}

	@GetMapping("/{studentId}/instructor")
	public ResponseEntity<List<InstructorResponseDTO>> getInstructors(@PathVariable("studentId") Integer studentId, Pageable pageable) {

		List<InstructorResponseDTO> instructors = studentService.findInstructorsById(studentId, pageable);

		return ResponseEntity.ok(instructors);
	}

	@PostMapping ()
	public ResponseEntity<Object> add(@RequestBody @Valid StudentRequestDTO student) {

		studentService.save(student);

		return ResponseEntity.ok().build();
	}

	@PutMapping ("/{studentId}")
	public ResponseEntity<Object> update(@PathVariable("studentId") Integer studentId, @RequestBody @Valid StudentRequestDTO student) {

		studentService.save(studentId, student);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{studentId}/addInstructor/{instructorID}")
	public ResponseEntity<Object> updateInstructorsById(@PathVariable("studentId") Integer studentId, @PathVariable("instructorID") Integer instructorId) {

		studentService.patchInstructorById(studentId, instructorId);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> delete(@PathVariable("studentId") Integer studentId) {

		studentService.delete(studentId);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping ("/{studentId}/deleteInstructor/{instructorId}")
	public ResponseEntity<Student> deleteInstructorsById(@PathVariable("studentId") Integer studentId, @PathVariable("instructorId") Integer instructorId) {

		studentService.deleteInstructorById(studentId, instructorId);

		return ResponseEntity.ok().build();
	}
}