package net.enemyofmankind.ultimatetask.dao;

import net.enemyofmankind.ultimatetask.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>, PagingAndSortingRepository<Instructor, Integer>
{
	Page<Instructor> findAll(Pageable pageable);
	Page<Instructor> findByFirstName(String firstName, Pageable pageable);
	Page<Instructor> findByLastName(String lastName, Pageable pageable);
	Page<Instructor> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
}