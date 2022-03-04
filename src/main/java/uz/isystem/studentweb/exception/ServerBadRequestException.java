package uz.isystem.studentweb.exception;

public class ServerBadRequestException extends RuntimeException {
   public ServerBadRequestException(String message){
       super(message);
   }
}
