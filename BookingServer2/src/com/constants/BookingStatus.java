package com.constants;

public enum BookingStatus {
   PENDING(1),
   APPROVED(2),
   CANCELLED(3),
   REJECTED(4),
   DELETED(5),
   CLOSED(6);
   
   private int value;
   
   private BookingStatus(int value) {
      this.value = value;
   }
   
   public int getValue() {
      return value;
   }
}
	

