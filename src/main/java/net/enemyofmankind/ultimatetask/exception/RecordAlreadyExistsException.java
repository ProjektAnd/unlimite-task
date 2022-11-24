package net.enemyofmankind.ultimatetask.exception;

public class RecordAlreadyExistsException extends RuntimeException
{
	public RecordAlreadyExistsException(String msg) {
		super(msg);
	}

	public RecordAlreadyExistsException(String msg, Exception e) {
		super(msg, e);
	}
}
