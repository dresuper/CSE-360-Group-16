package validation;

public record ValidationResult(boolean valid, String message) {
	// Successful result with no message
	public static ValidationResult ok() { return new ValidationResult(true, ""); }
	// Create a failed result with an error message
	public static ValidationResult error(String msg) { return new ValidationResult(false, msg); }

}
