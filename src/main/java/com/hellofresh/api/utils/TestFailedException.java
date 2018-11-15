package com.hellofresh.api.utils;

/*
 * This class provides custom RuntimeException
*/
public class TestFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestFailedException(String s)
	{
		// Call constructor of parent Exception
		super(s);
	}

}
