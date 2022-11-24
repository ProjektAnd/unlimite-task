package net.enemyofmankind.ultimatetask.rest;

import net.enemyofmankind.ultimatetask.dto.InstructorRequestDTO;
import net.enemyofmankind.ultimatetask.dto.InstructorResponseDTO;
import net.enemyofmankind.ultimatetask.dto.StudentResponseDTO;
import net.enemyofmankind.ultimatetask.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("api/instructor")
public class InstructorRestController
{
	private final InstructorService instructorService;

	@Autowired
	public InstructorRestController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}

	@GetMapping ()
	public ResponseEntity<List<InstructorResponseDTO>> get(Pageable pageable) {

		List<InstructorResponseDTO> instructors = instructorService.findAll(pageable);

		return ResponseEntity.ok(instructors);
	}

	@GetMapping("/{instructorId}")
	public ResponseEntity<InstructorResponseDTO> getById(@PathVariable ("instructorId") Integer studentId) {

		InstructorResponseDTO theInstructor = instructorService.findById(studentId);

		return ResponseEntity.ok(theInstructor);
	}

	@GetMapping(params = { "firstName" })
	public ResponseEntity<List<InstructorResponseDTO>> getByFirstName(@RequestParam ("firstName") String firstName, Pageable pageable) {

		List<InstructorResponseDTO> instructors = instructorService.findByFirstName(firstName, pageable);

		return ResponseEntity.ok(instructors);
	}

	@GetMapping(params = { "lastName" })
	public ResponseEntity<List<InstructorResponseDTO>> getByLastName(@RequestParam("lastName") String lastName, Pageable pageable) {

		List<InstructorResponseDTO> instructors = instructorService.findByLastName(lastName , pageable);

		return ResponseEntity.ok(instructors);
	}

	@GetMapping(params = { "firstName", "lastName" })
	public ResponseEntity<List<InstructorResponseDTO>> getByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Pageable pageable) {

		List<InstructorResponseDTO> instructors = instructorService.findByFirstNameAndLastName(firstName, lastName, pageable);

		return ResponseEntity.ok(instructors);
	}

	@GetMapping("/{instructorId}/student")
	public ResponseEntity<List<StudentResponseDTO>> getStudents(@PathVariable("instructorId") Integer instructorId, Pageable pageable) {

		List<StudentResponseDTO> instructors = instructorService.findStudentsById(instructorId, pageable);

		return ResponseEntity.ok(instructors);
	}

	@PostMapping ()
	public ResponseEntity<Void> add(@RequestBody @Valid InstructorRequestDTO instructor) {

		instructorService.save(instructor);

		return ResponseEntity.ok().build();
	}

	@PutMapping ("/{instructorId}")
	public ResponseEntity<Void> update(@PathVariable("instructorId") Integer instructorId, @RequestBody @Valid InstructorRequestDTO instructor) {

		instructorService.save(instructorId, instructor);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{instructorId}/addStudent/{studentId}")
	public ResponseEntity<Void> addStudentById(@PathVariable("instructorId") Integer instructorId, @PathVariable("studentId") Integer studentId) {

		instructorService.patchStudentById(instructorId, studentId);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> delete(@PathVariable("studentId") Integer instructorId) {

		instructorService.delete(instructorId);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping ("/{instructorId}/deleteStudent/{studentId}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable("instructorId") Integer instructorId, @PathVariable("studentId") Integer studentId) {

		instructorService.deleteStudentById(instructorId, studentId);

		return ResponseEntity.ok().build();
	}
}
