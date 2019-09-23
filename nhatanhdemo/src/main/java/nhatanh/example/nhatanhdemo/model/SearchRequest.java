package nhatanh.example.nhatanhdemo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SearchRequest  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sortTarget;
	private String sortType;
	private int displayCount;
	private int pageNumber;
	
	public SearchRequest(){
	}

	public SearchRequest(String sortTarget, String sortType, int displayCount,
			int pageNumber) {
		this.sortTarget = sortTarget;
		this.sortType = sortType;
		this.displayCount = displayCount;
		this.pageNumber = pageNumber;
	}

	public String getSortTarget() {
		return sortTarget;
	}

	public void setSortTarget(String sortTarget) {
		this.sortTarget = sortTarget;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getDisplayCount() {
		return displayCount;
	}

	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
