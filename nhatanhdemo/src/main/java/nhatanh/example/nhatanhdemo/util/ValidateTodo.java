package nhatanh.example.nhatanhdemo.util;

import java.util.Arrays;
import java.util.List;

import nhatanh.example.nhatanhdemo.model.ErrorInfo;
import nhatanh.example.nhatanhdemo.model.Todo;

public class ValidateTodo {
	public static List<String> statusList = Arrays.asList("planning", "doing", "complete");

	public static ErrorInfo valitate(Todo toto, String path) {
		if (!statusList.contains(toto.getStatus().toLowerCase())) {
			return new ErrorInfo(path, "status", "Status not correct format!");
		}
		if (!statusList.contains(toto.getStatus().toLowerCase())) {
			return new ErrorInfo(path, "status", "Status not correct format!");
		}
		return null;
		
	}
}
