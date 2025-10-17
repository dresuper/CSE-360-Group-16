package guiUserLogin;

import java.util.Optional;

import database.Database;
import entityClasses.User;
import javafx.stage.Stage;

import validation.UsernameCheck;
import validation.PasswordCheck;
import validation.ValidationResult;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;


public class ControllerUserLogin {
	
	/*-********************************************************************************************

	The User Interface Actions for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	*/


	// Reference for the in-memory database so this package has access
	private static Database theDatabase = applicationMain.FoundationsMain.database;

	private static Stage theStage;	
	
	/**********
	 * <p> Method: public doLogin() </p>
	 * 
	 * <p> Description: This method is called when the user has clicked on the Login button. This
	 * method checks the username and password to see if they are valid.  If so, it then logs that
	 * user in my determining which role to use.
	 * 
	 * The method reaches batch to the view page and to fetch the information needed rather than
	 * passing that information as parameters.
	 * 
	 */	
	protected static void doLogin(Stage ts) {
		theStage = ts;
		String username = ViewUserLogin.text_Username.getText();
		String password = ViewUserLogin.text_Password.getText();
    	boolean loginResult = false;
    	
    	ValidationResult name = UsernameCheck.validate(username);
		if (!name.valid()) {
			new Alert (Alert.AlertType.ERROR, name.message()).showAndWait();
			return;
		}
		
		ValidationResult pass = PasswordCheck.validate(password);
		if (!pass.valid()) {
			new Alert (Alert.AlertType.ERROR, pass.message()).showAndWait();
			return;
		}
    	
		// Fetch the user and verify the username
     	if (theDatabase.getUserAccountDetails(username) == false) {
     		// Don't provide too much information.  Don't say the username is invalid or the
     		// password is invalid.  Just say the pair is invalid.
    		ViewUserLogin.alertUsernamePasswordError.setContentText(
    				"Incorrect username/password. Try again!");
    		ViewUserLogin.alertUsernamePasswordError.showAndWait();
    		return;
    	}
		System.out.println("*** Username is valid");
		
		// Check to see that the login password matches the account password
    	String actualPassword = theDatabase.getCurrentPassword();
    	
    	if (password.compareTo(actualPassword) != 0) {
    		ViewUserLogin.alertUsernamePasswordError.setContentText(
    				"Incorrect username/password. Try again!");
    		ViewUserLogin.alertUsernamePasswordError.showAndWait();
    		return;
    	}
		System.out.println("*** Password is valid for this user");
		
		// Establish this user's details
    	User user = new User(username, password, theDatabase.getCurrentFirstName(), 
    			theDatabase.getCurrentMiddleName(), theDatabase.getCurrentLastName(), 
    			theDatabase.getCurrentPreferredFirstName(), theDatabase.getCurrentEmailAddress(), 
    			theDatabase.getCurrentAdminRole(), 
    			theDatabase.getCurrentNewRole1(), theDatabase.getCurrentNewRole2());
    	
    	// See which home page dispatch to use
		int numberOfRoles = theDatabase.getNumberOfRoles(user);		
		System.out.println("*** The number of roles: "+ numberOfRoles);
		if (numberOfRoles == 1) {
			// Single Account Home Page - The user has no choice here
			
			// Admin role
			if (user.getAdminRole()) {
				loginResult = theDatabase.loginAdmin(user);
				if (loginResult) {
					guiAdminHome.ViewAdminHome.displayAdminHome(theStage, user);
				}
			} else if (user.getNewRole1()) {
				loginResult = theDatabase.loginRole1(user);
				if (loginResult) {
					guiRole1.ViewRole1Home.displayRole1Home(theStage, user);
				}
			} else if (user.getNewRole2()) {
				loginResult = theDatabase.loginRole2(user);
				if (loginResult) {
					guiRole2.ViewRole2Home.displayRole2Home(theStage, user);
				}
				// Other roles
			} else {
				System.out.println("***** UserLogin goToUserHome request has an invalid role");
			}
		} else if (numberOfRoles > 1) {
			// Multiple Account Home Page - The user chooses which role to play
			System.out.println("*** Going to displayMultipleRoleDispatch");
			guiMultipleRoleDispatch.ViewMultipleRoleDispatch.
				displayMultipleRoleDispatch(theStage, user);
		}
	}
	
	
	public static void onForgotPassword() {
	
		
		TextInputDialog forgot = new TextInputDialog();
		forgot.setTitle("Forgot Password");
		forgot.setHeaderText("Enter your account email");
		forgot.setContentText("Email: ");
		Optional<String> r = forgot.showAndWait();
		if (r.isEmpty()) return;
		

		String emailAddress = r.get().trim();
		if (emailAddress.isEmpty()){new Alert(AlertType.ERROR, "Enter a valid email.").show(); return;
		
		}

		TextInputDialog d1 = new TextInputDialog();
		d1.setTitle("Reset Password");
		d1.setHeaderText("Enter new password");
		d1.setContentText("New password:");
		Optional<String> pwR1 = d1.showAndWait();
		if (pwR1.isEmpty()) return;
		String pw1 = pwR1.get();
		
		TextInputDialog d2 = new TextInputDialog();
		d2.setTitle("Reset Password");
		d2.setHeaderText("Confirm new password");
		d2.setContentText("Confirm:");
		Optional<String> pwR2 = d2.showAndWait();
		if (pwR2.isEmpty()) return;
		String pw2 = pwR2.get();
		
		ValidationResult pass = PasswordCheck.validate(pw1);
		if (!pass.valid()) {
			new Alert (Alert.AlertType.ERROR, pass.message()).showAndWait();
			return;
		}
		
		if (!pw1.equals(pw2)) {
			new Alert(AlertType.ERROR, "Passwords need to match").show();
			return;
		}
		
		boolean update = theDatabase.resetPassword(emailAddress, pw1);
		new Alert(update ? AlertType.INFORMATION : AlertType.INFORMATION, "If account with this email exist. Proceed to login.").show(); }
		
	
	
	
		
	/**********
	 * <p> Method: setup() </p>
	 * 
	 * <p> Description: This method is called to reset the page and then populate it with new
	 * content.</p>
	 * 
	 */
	protected static void doSetupAccount(Stage theStage, String invitationCode) {
		guiNewAccount.ViewNewAccount.displayNewAccount(theStage, invitationCode);
	}

	
	/**********
	 * <p> Method: public performQuit() </p>
	 * 
	 * <p> Description: This method is called when the user has clicked on the Quit button.  Doing
	 * this terminates the execution of the application.  All important data must be stored in the
	 * database, so there is no cleanup required.  (This is important so we can minimize the impact
	 * of crashed.)
	 * 
	 */	
	protected static void performQuit() {
		System.out.println("Perform Quit");
		System.exit(0);
	}	

}
