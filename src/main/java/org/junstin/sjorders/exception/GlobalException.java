package org.junstin.sjorders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    /* 나중에 오류별로 처리하기 */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> findIdException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorCode.IllegalArgument.toString() + " - "+ e.getMessage());
    }
}
