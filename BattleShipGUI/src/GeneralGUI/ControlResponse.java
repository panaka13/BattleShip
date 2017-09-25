package GeneralGUI;

public class ControlResponse {
	private static boolean responsive = true;
	
	public static void turnOn() {
		responsive = true;
	}
	
	public static void turnOff() {
		responsive = false;
	}
	
	public static boolean isResponsive() {
		return responsive;
	}
}
