package servers;

import java.net.InetAddress;

public class GlobalV {
	public static int listeningPortKitchenCon = 6000;
	public static int listeningPortKitchenAlerts = 8020;
	public static int sendingPortOrders = 8010;
	public static InetAddress KitchenIp =null;
	
	public static int listeningPortManager = 6030;
	
	public static int listeningPortAndroidOrders = 6050;
	public static int sendingPortAlertsToAndroid = 7060;
	
	public static InetAddress AndroidIP = null;
	public static InetAddress ManagerIP = null;

}
