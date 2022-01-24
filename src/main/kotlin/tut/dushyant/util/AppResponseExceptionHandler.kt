package tut.dushyant.util

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AppResponseExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ResponseStatusException::class])
    fun handleResponseException(ex: ResponseStatusException, request: WebRequest): ResponseEntity<MessageObj> {
        val msgObj = MessageObj(code = ex.rawStatusCode, message = ex.localizedMessage)
        return ResponseEntity.status(msgObj.code).body(msgObj)
    }

}