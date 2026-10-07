package com.portfolio.cms.exception;
import com.portfolio.cms.dto.ApiError; import jakarta.servlet.http.HttpServletRequest; import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.security.access.AccessDeniedException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.NoSuchElementException;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ApiError> validation(MethodArgumentNotValidException e,HttpServletRequest r){String msg=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid request");return error(400,"Validation error",msg,r);}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<ApiError> bad(IllegalArgumentException e,HttpServletRequest r){return error(400,"Bad request",e.getMessage(),r);}
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<ApiError> notFound(NoSuchElementException e,HttpServletRequest r){return error(404,"Not found",e.getMessage(),r);}
 @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<ApiError> conflict(DataIntegrityViolationException e,HttpServletRequest r){return error(409,"Conflict","The record conflicts with existing data.",r);}
 @ExceptionHandler(AccessDeniedException.class) ResponseEntity<ApiError> denied(AccessDeniedException e,HttpServletRequest r){return error(403,"Forbidden","You do not have permission to perform this action.",r);}
 @ExceptionHandler(Exception.class) ResponseEntity<ApiError> other(Exception e,HttpServletRequest r){return error(500,"Internal server error","Something went wrong on the server.",r);}
 private ResponseEntity<ApiError> error(int s,String err,String msg,HttpServletRequest r){return ResponseEntity.status(s).body(new ApiError(Instant.now(),s,err,msg,r.getRequestURI()));}
}
