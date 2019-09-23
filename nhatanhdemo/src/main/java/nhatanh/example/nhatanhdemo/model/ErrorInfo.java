package nhatanh.example.nhatanhdemo.model;

public class ErrorInfo {
	private String path;
	private String key;
	private String msg;
	public ErrorInfo(String path, String key, String msg) {
		super();
		this.path = path;
		this.key = key;
		this.msg = msg;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
