package nhatanh.example.nhatanhdemo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nhatanh.example.nhatanhdemo.model.Todo;

@Repository
public interface TodoService extends JpaRepository<Todo, Long>{
	@Query(value = "SELECT * FROM todo WHERE work_name = ?1 ", nativeQuery = true)
    Todo findByWorkName(String workName);
}
