package misc;
import java.util.ArrayList;

public class ErrorHandler {
	private ArrayList<Error> errors = new ArrayList<Error>();
	
	public void addError(Error error) {
		this.errors.add(error);
	}
	
	public boolean removeError(Error error) {
		return this.errors.remove(error);
	}
	
	public void draw() {}
}
