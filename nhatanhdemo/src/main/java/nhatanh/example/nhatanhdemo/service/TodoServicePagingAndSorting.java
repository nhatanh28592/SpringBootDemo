package nhatanh.example.nhatanhdemo.service;

import nhatanh.example.nhatanhdemo.model.Todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoServicePagingAndSorting extends PagingAndSortingRepository<Todo, Long> {
    Page<Todo> findAll(Pageable pageable);
}
