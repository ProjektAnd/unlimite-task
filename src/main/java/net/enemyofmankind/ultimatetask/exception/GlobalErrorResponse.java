package net.enemyofmankind.ultimatetask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse
{
	private int status;
	private String message;
	private long timeStamp;
	private String contextPath;

	public GlobalErrorResponse(HttpStatus httpStatus, Exception exception, WebRequest webRequest){
		status = httpStatus.value();
		message = exception.getMessage();
		timeStamp = System.currentTimeMillis();
		contextPath = webRequest.getDescription(false);
	}
}

