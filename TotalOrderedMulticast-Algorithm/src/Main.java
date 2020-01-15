
public class Main {

	public static void main(String[] args) {
		TotalOrderedMulticast totalOrder = new TotalOrderedMulticast();
		totalOrder.init();

		try {
			if(TotalOrderedMulticast.connectionThread != null){
				TotalOrderedMulticast.connectionThread.join();
			}
			if(TotalOrderedMulticast.orderThread != null){
				TotalOrderedMulticast.orderThread.join();
			}
			if(TotalOrderedMulticast.ackThread != null){
				TotalOrderedMulticast.ackThread.join();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Process "+ TotalOrderedMulticast.process_id+" ended!");

	}

}
