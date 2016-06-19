package ecweb;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import ecweb.ecoupon.controller.RegisterController;
import ecweb.ecoupon.util.ShortMessage;

public class Checkingtime {
	ShortMessage sm;
	int mintues;

	@Test
	public void mytest() {
		sm=new ShortMessage();
	      sm.setMessage("testing Checktime");
	      Calendar cal = Calendar.getInstance();
	      System.out.println(cal.toString());
	      cal.add(Calendar.MINUTE, -2);
	      sm.setCreatedTime(cal.getTime());
	      System.out.println(cal.toString());
	      mintues=3;
	      
		RegisterController rc= new RegisterController();
		
		assertTrue(rc.CheckingTime(sm, 3));
	}

}
