package com.ab.helpers;

import java.util.regex.Pattern;

public class EmailValidator {

	public static boolean isValid(String email)
	{
	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	if (email == null)
	return false;
	return pattern.matcher(email).matches();
	}
}
