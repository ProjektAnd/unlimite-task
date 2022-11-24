package net.enemyofmankind.ultimatetask.dao;

import net.enemyofmankind.ultimatetask.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> , PagingAndSortingRepository<Student, Integer>
{
	Page<Student> findAll(Pageable pageable);
	Page<Student> findByFirstName(String firstName, Pageable pageable);
	Page<Student> findByLastName(String lastName, Pageable pageable);
	Page<Student> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
}
