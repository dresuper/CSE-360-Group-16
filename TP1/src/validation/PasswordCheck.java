package validation;

public final class PasswordCheck {
	private PasswordCheck() {}
	
	// Runs FSM and return pass or fail with message
	public static ValidationResult validate(String pass) {
		if (pass == null || pass.isEmpty()) {
			return ValidationResult.error("Password is needed.");
		}
		if (pass.length() > 32) {
			return ValidationResult.error("Password is too long! (max 32 characters)");
		}
		
		String error = evaluatePassword(pass);
		
		if (error.isEmpty()) {
			return ValidationResult.ok();
		}
		return  ValidationResult.error(error);
	}
	
	//FSM logic from testbed and adapted
	public static String evaluatePassword(String input) {
		if(input.length() <= 0) {
			return "*** Error *** The password is empty!";
		}
		if(input.length() > 32) {
			return "*** Error *** The password is too long! (max 32 characters)"; // FIX
		}
		
		// The following are the attributes associated with each of the requirements
		boolean foundUpperCase = false;				// Reset the Boolean flag
		boolean foundLowerCase = false;				// Reset the Boolean flag
		boolean foundNumericDigit = false;			// Reset the Boolean flag
		boolean foundSpecialChar = false;			// Reset the Boolean flag
		boolean foundLongEnough = false;			// Reset the Boolean flag
		

			// The cascading if statement sequentially tries the current character against all of
			// the valid transitions, each associated with one of the requirements
			for (int i = 0; i < input.length(); i++) {
				char currentChar = input.charAt(i);
				
				if (currentChar >= 'A' && currentChar <= 'Z') {
					foundUpperCase = true;
				} else if (currentChar >= 'a' && currentChar <= 'z') {
					foundLowerCase = true;
				} else if (currentChar >= '0' && currentChar <= '9') {
					foundNumericDigit = true;
				} else if ("~`!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/".indexOf(currentChar) >= 0) {
					foundSpecialChar = true;
				} else {
					return "*** Error *** An invalid character has been found!";
				}
				
				// At least 8 characters
				if (i >= 7) { 
					foundLongEnough = true;
				}
			
		}
		
		// Construct a String with a list of the requirement elements that were found.
		String errMessage = "";
		if (!foundUpperCase)
			errMessage += "Upper case; ";
		
		if (!foundLowerCase)
			errMessage += "Lower case; ";
		
		if (!foundNumericDigit)
			errMessage += "Numeric digits; ";
			
		if (!foundSpecialChar)
			errMessage += "Special character; ";
			
		if (!foundLongEnough)
			errMessage += "Long Enough; ";
		
		if (errMessage == "")
			return "";

		// If it gets here, there something was not found, so return an appropriate message
		return errMessage + "conditions were not satisfied";
	}

}
