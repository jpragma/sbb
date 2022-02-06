package com.jpragma.sbb.rest

import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalRestControllerExceptionHandler {
    data class ErrorInfo(val url:String, val message: String)

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(OptimisticLockingFailureException::class)
    @ResponseBody
    fun handlerOptimisticLockFailure(req: HttpServletRequest, ex: OptimisticLockingFailureException): ErrorInfo {
        return ErrorInfo(req.requestURI, ex.localizedMessage)
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException::class)
    @ResponseBody
    fun handlerDuplicateKey(req: HttpServletRequest, ex: DuplicateKeyException): ErrorInfo {
        return ErrorInfo(req.requestURI, ex.localizedMessage)
    }

}