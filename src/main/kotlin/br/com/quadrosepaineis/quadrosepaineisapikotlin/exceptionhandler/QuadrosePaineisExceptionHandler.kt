package br.com.quadrosepaineis.quadrosepaineisapikotlin.exceptionhandler

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class QuadrosePaineisExceptionHandler : ResponseEntityExceptionHandler() {

    @Autowired
    lateinit var messageSource: MessageSource

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException, request: WebRequest): ResponseEntity<Any> {
        val rootCause = ExceptionUtils.getRootCauseMessage(ex)
        var msgUser = "Tipo inválido"
        if (rootCause.contains("SQLIntegrityConstraintViolationException"))
            msgUser = """Já existe um registro com o valor ${StringUtils.substringBetween(rootCause, "'")}"""
        val listErrors = listOf(ErrorMessage(rootCause, msgUser))

        return handleExceptionInternal(ex, listErrors, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

}

data class ErrorMessage(var msgDev: String, var msgUser: String)
