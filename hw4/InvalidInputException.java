package cs445.hw4;

public class InvalidInputException extends RuntimeException{
	public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}