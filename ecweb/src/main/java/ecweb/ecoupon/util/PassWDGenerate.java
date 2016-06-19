package ecweb.ecoupon.util;

import java.util.Random;

//create 6 digital random number, which used as password
public class PassWDGenerate {
public static String passwdGenerate(){
	String passwd=null;
	Random randomGenerator = new Random();
	Integer randomInt = randomGenerator.nextInt(1000000);
	passwd=randomInt.toString();
	if(passwd.length()<6){
		int i = 6-passwd.length();
		for(int j=0;j<i;j++) passwd="0"+passwd;
	}
	return passwd;
}
}
