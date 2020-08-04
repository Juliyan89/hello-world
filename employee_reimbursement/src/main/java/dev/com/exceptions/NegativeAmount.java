package dev.com.exceptions;

public class NegativeAmount extends Exception {

	public NegativeAmount() {
		super("Cannot have a negative balance on expense!");
	}
}
