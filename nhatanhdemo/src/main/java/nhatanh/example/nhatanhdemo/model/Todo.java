package nhatanh.example.nhatanhdemo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Todo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workName;
	private Date startingDate;
	private Date endingDate;
	private String status;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	public Todo(){
		id=0;
	}

	public Todo(String workName, Date startingDate, Date endingDate,
			String status, long id) {
		this.workName = workName;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = status;
		this.id = id;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}




