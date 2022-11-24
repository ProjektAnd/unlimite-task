package net.enemyofmankind.ultimatetask.exception;

public class RecordNotFoundException extends RuntimeException
{
	public RecordNotFoundException(String msg) {
		super(msg);
	}

	public RecordNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
}
