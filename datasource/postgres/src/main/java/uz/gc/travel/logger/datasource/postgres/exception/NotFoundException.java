package uz.gc.travel.logger.datasource.postgres.exception;

/**
 * @author a.ergashev
 * Date: 5/31/2023
 * Time: 3:55 PM
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message, Throwable exception){
        super(message, exception);
    }
}
