package nhatanh.example.nhatanhdemo.model;

public class SuccessInfo {
	private String path;
	private String msg;
	private Long id;
	public SuccessInfo(String path, String msg, Long id) {
		super();
		this.path = path;
		this.msg = msg;
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
