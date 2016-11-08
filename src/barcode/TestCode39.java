package barcode;

import junit.framework.*;

public class TestCode39 extends TestCase 
{
	
	Code39 c = new Code39();
	
	public void testEncodeCode39() 
	{
		assertEquals("failure - strings are not equal", "*BONJOUR*", 		c.encodeCode39("bonjour"));
		assertEquals("failure - strings are not equal", "*BONJOUR*", 		c.encodeCode39("*bon*jour*"));
		assertEquals("failure - strings are not equal", "*B ON JOU R*", 	c.encodeCode39("b on jou r"));
		assertEquals("failure - strings are not equal", "*/J+-O*", 			c.encodeCode39("/`j+-o*"));
		assertEquals("failure - strings are not equal", "*%%-BON%J OUR/+*", c.encodeCode39("**%%&é'(-è_çà)bon%j our*/+'"));
	}
	
	public void testModulo43()
	{
		assertEquals("failure - strings are not equal", 'E', c.modulo43("*TEST*"));
		assertEquals("failure - strings are not equal", 'O', c.modulo43("*BON JOUR*"));
		assertEquals("failure - strings are not equal", 'G', c.modulo43("*JFKDSML$FDSKL*"));
		assertEquals("failure - strings are not equal", 'D', c.modulo43("*P%EI*"));
		assertEquals("failure - strings are not equal", 'B', c.modulo43("*K+F/FL.IJ*"));
	}

	public void testAddControl()
	{
		assertEquals("failure - strings are not equal", "*TEST*E", 			c.addControl("*TEST*"));
		assertEquals("failure - strings are not equal", "*BON JOUR*O", 		c.addControl("*BON JOUR*"));
		assertEquals("failure - strings are not equal", "*JFKDSML$FDSKL*G", c.addControl("*JFKDSML$FDSKL*"));
		assertEquals("failure - strings are not equal", "*P%EI*D", 			c.addControl("*P%EI*"));
		assertEquals("failure - strings are not equal", "*K+F/FL.IJ*B", 	c.addControl("*K+F/FL.IJ*"));
	}
	
	public void testEncodeAll()
	{
		assertEquals("failure - strings are not equal", "*FJKDSLMFSERIA*Z", c.encodeAll("fjkdslmfse'àr'i')aé*"));
		assertEquals("failure - strings are not equal", "*FDEFGT565-$$J*-", c.encodeAll("fdefgt565'(-)à$$ADFj"));
		assertEquals("failure - strings are not equal", "*FDS86J$TA* ", 	c.encodeAll("fds8'(6èŷj$t')aé*)"));
		assertEquals("failure - strings are not equal", "*FJKDR$8A*M", 		c.encodeAll("fjkdr&)$8)aé*"));
		assertEquals("failure - strings are not equal", "*DUIOKL45668F*+", 	c.encodeAll("d'çà)_çuiokl45668f*"));
	}
}
