package chap11;

public class Pizza {
	 private PizzaStatus status;
	 public enum PizzaStatus {
		 ORDERED (5){
			 public boolean isOrdered() {
				 return true;
	            }
	        },
		 READY (2){
	        public boolean isReady() {
	             return true;
	        }
	     },
		 DELIVERED (0){
	    	 public boolean isDelivered() {
	    		 return true;
	         }
	     };
	        private int timeToDelivery;
	        //枚举类PizzaStatus 构造方法
	        PizzaStatus (int timeToDelivery) {
	            this.timeToDelivery = timeToDelivery;
	        }
	        public boolean isOrdered() {return false;} 
	        public boolean isReady() {return false;} 
	        public boolean isDelivered(){return false;} 
	        //获得派送时间
	        public int getTimeToDelivery() {
	            return timeToDelivery;
	        }        
	    }
	 public PizzaStatus getStatus() {
		 return status;
	 }

	 public void setStatus(PizzaStatus status) {
		 this.status = status;
	 }
	 public boolean isDeliverable() {
		 return this.status.isReady();
	 }
	 public void printTimeToDeliver() {
		 System.out.println("Time to delivery is " + this.getStatus().getTimeToDelivery());
	 }   
}
