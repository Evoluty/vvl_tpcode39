package barcode;

public class Code39 
{

	public Boolean isAllowedCharacter(char c)
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		if ( alphabet.contains((c+"")))
			return true;
		if ( Character.isDigit(c) )
			return true;
		if ( c == ' ' || c == '-' || c == '$' || c == '%' || c == '.' || c == '/' || c == '+' )
			return true;

		return false;
	}

	public String encodeCode39(String str)
	{
		String res = "*";

	    for (int i = 0; i < str.length(); i++)
	    {        
	    	char c = str.charAt(i);
	        if (isAllowedCharacter( c ))
	        { 
	        	res += Character.toUpperCase(c);
	        }
	    }

	    res += "*";
		return res;
	}

	public char modulo43(String str)
	{
		int res = 0;
		int i;
		for (i=1; i<str.length()-1; i++)
		{
			if (Character.isDigit((str.charAt(i))))
			{
				res += (str.charAt(i) - '0');
			}
			else if (Character.isLetter((str.charAt(i))))
			{
				res += (str.charAt(i) - 'A' + 10);
			}
			else
			{
				switch (str.charAt(i))
				{
					case '-':
						res += 36;
					break;

					case '.':
						res += 37;
					break;

					case ' ':
						res += 38;
					break;

					case '$':
						res += 39;
					break;

					case '/':
						res += 40;
					break;

					case '+':
						res += 41;
					break;

					case '%':
						res += 42;
					break;
				}
			}
		}

		String car = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
		return car.charAt(res%43);
	}
	

	public String addControl(String s)
	{
		return s + modulo43(s);
	}
	
	public String encodeAll(String strToEncode)
	{		
		return addControl(encodeCode39(strToEncode));
	}


}
