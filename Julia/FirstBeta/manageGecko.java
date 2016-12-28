package FirstBeta;

public class manageGecko {
	
	private static final String processname = "geckodriver.exe";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\me\\work\\official\\Julia\\gecko_driver";
	public static boolean killGecko(){
		try{
		Runtime.getRuntime().exec(new String("taskkill /F /IM " + processname));
		
		return true;
		}catch(Exception e){
			Logging.slog("Error killing Gecko");
			return false;
		}
	}
}
