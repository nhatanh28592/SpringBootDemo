package nhatanh.example.nhatanhdemo.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nhatanh.example.nhatanhdemo.model.ErrorInfo;
import nhatanh.example.nhatanhdemo.model.SearchRequest;
import nhatanh.example.nhatanhdemo.model.SuccessInfo;
import nhatanh.example.nhatanhdemo.model.Todo;
import nhatanh.example.nhatanhdemo.service.TodoService;
import nhatanh.example.nhatanhdemo.service.TodoServicePagingAndSorting;

@RestController
@RequestMapping("/api")
public class RestApiController {
	public static Logger logger = LoggerFactory.getLogger(RestApiController.class);
	public static List<String> statusList = Arrays.asList("planning", "doing", "complete");
	
	@Autowired
	TodoService todoService;
	@Autowired
	TodoServicePagingAndSorting todoServicePagingAndSorting;
	
	@RequestMapping(value = "/todo/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAllTodo(){
		List<Todo> listTodo= todoService.findAll();
		if(listTodo.isEmpty()) {
			return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Todo>>(listTodo, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/todo/create", method = RequestMethod.POST)
	public Object createTodo(@Valid @RequestBody Todo todo) {
		List<ErrorInfo> errors = checkTodo(todo, "api/todo/create");
		Todo todoDb = todoService.findByWorkName(todo.getWorkName());
		if(todoDb != null && todoDb.getId() != 0){
			errors.add(new ErrorInfo("api/todo/create", "workName", "Work name exist!"));
		}
		if (errors.size() > 0) {
			return ResponseEntity.ok(errors);
		}
		Todo todoInsert =  todoService.save(todo);
		return ResponseEntity.ok(todoInsert);
	}
	
	@RequestMapping(value = "/todo/update", method = RequestMethod.POST)
	public Object updateTodo(@Valid @RequestBody Todo todoForm) {
		String path = "api/todo/update";
		List<ErrorInfo> errors = checkTodo(todoForm, path);
		
		//Check workname exist
		Todo todoDb = todoService.findByWorkName(todoForm.getWorkName());
		if(todoDb != null && todoDb.getId() != todoForm.getId()){
			errors.add(new ErrorInfo(path, "workName", "Work name exist!"));
		}
		//Check id exist
		Todo todo = todoService.getOne(todoForm.getId());
		if(todo == null) {
	       errors.add(new ErrorInfo(path, "Id", "Id not exist!")); 
	    }
		if (errors.size() > 0) {
			return ResponseEntity.ok(errors);
		}
		
	    todo.setWorkName(todoForm.getWorkName());
	    todo.setStartingDate(todoForm.getStartingDate());
	    todo.setEndingDate(todoForm.getEndingDate());
	    todo.setStatus(todoForm.getStatus());

	    Todo updatedTodo= todoService.save(todo);
	    return ResponseEntity.ok(updatedTodo);
	}
	
	@RequestMapping(value = "/todo/delete/{id}", method = RequestMethod.DELETE)
	public Object deleteTodo(@PathVariable(value = "id") Long id) {
		Todo todo = todoService.getOne(id);
	    if(todo == null) {
	        return ResponseEntity.ok(new ErrorInfo("/todo/update", "Id", "Id not exist!")); 
	    }

	    todoService.delete(todo);
	    return ResponseEntity.ok(new SuccessInfo("/todo/update", "Delete complete", todo.getId())); 
	}
	
	@RequestMapping(value = "/todo/search", method = RequestMethod.POST)
	public Object search(@Valid @RequestBody SearchRequest request) {
		int displayCount = request.getDisplayCount();
		int pageNumber = request.getPageNumber();
		String sortTarget = request.getSortTarget();
		// Set paging and sorting
		Sort sort = new Sort(Direction.ASC, sortTarget);
		if (request.getSortType().toLowerCase().equals("desc")) {
			sort = new Sort(Direction.DESC, sortTarget);
		}
		Pageable pageable = PageRequest.of(pageNumber, displayCount, sort);
		
		Page<Todo> page = todoServicePagingAndSorting.findAll(pageable);
		return ResponseEntity.ok(page);
	}

	private static List<ErrorInfo> checkTodo(Todo todo, String path){
		List<ErrorInfo> errors = new ArrayList<ErrorInfo>();
		
		if (!statusList.contains(todo.getStatus().toLowerCase())) {
			errors.add(new ErrorInfo(path, "status", "Status not correct format!"));
		}
		if (todo.getStartingDate().after(todo.getEndingDate())) {
			errors.add(new ErrorInfo(path, "endingDate", "endingDate < startingDate !"));
		}
		return errors;
	}
}
