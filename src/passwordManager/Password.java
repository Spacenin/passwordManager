package passwordManager;

public class Password {
	private String passwordCred;
	private String usernameCred;
	private String url;
	private String passName;
	
	public Password(String passCred, String userCred, String url, String passName) {
		passwordCred = passCred;
		usernameCred = userCred;
		this.url = url;
		this.passName = passName;
	}
	
	public Password() {
		this("N/A", "N/A", "N/A", "N/A");
	}
	
	public String getPassword() {
		return(passwordCred);
	}
	
	public String getUsername() {
		return(usernameCred);
	}
	
	public String getUrl() {
		return(url);
	}
	
	public String getName() {
		return(passName);
	}
}
