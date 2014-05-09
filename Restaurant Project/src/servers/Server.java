package servers;

public class Server {
	public static void main(String[] args){
		int minutes = 10;
		// listening for the connection from the Kitchen
		ListenKitchen ip = new ListenKitchen();
		new Thread(ip).start();
		
		ListenOrders listenO = new ListenOrders();
		new Thread(listenO).start();
	    //DB STUFF  
	    SQLiteJDBC database = new SQLiteJDBC();
	    ListenToManager manager = new ListenToManager();
	    new Thread(manager).start();
	    
		try{
			Thread.sleep(minutes * 60 * 1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Stopping server.");
	}
	

}
