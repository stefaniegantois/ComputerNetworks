package HTTPClient;
public class HTTPVersion {
	
	private int major;
	private int minor;
	
	public HTTPVersion (int major, int minor) {
		this.major = major;
		this.minor = minor;
	}
	
	public HTTPVersion (String version) {
		setMajor(Character.getNumericValue(version.charAt((version.indexOf(".")-1))));
		setMinor(Character.getNumericValue(version.charAt((version.indexOf(".")+1))));
		System.out.println("major " + major + " minor " + minor);
	}
	
	public void setMajor(int major) {
		this.major=major;
	}
	
	public int getMajor() {
		return major;
	}
	
	public void setMinor(int minor) {
		this.minor=minor;
	}
	
	public int getMinor() {
		return minor;
	}
	
	@Override
	public String toString() {
		return "HTTP/" + getMajor() + "." + getMinor();
	}
	
}