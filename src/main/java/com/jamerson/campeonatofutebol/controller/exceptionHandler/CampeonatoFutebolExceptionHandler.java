package com.jamerson.campeonatofutebol.controller.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class CampeonatoFutebolExceptionHandler  extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public CampeonatoFutebolExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }


    /**
     * Captura excessão de parametro não indentificado
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String causa = ex.getCause().toString();

        return super.handleExceptionInternal (ex, new Erro(mensagemUsuario, causa), headers, status, request);

    }


    /**
     * Captura excessão de parametro inválido ou nulo
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return super.handleExceptionInternal(ex, erros, headers, status, request);
    }


    /**
     *  Trata erros sobre ids não encontrados no database enviados a um cadastro.
     *  	Ex: entidade que possui outra entidade como atributo
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String mensagem = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale() );
        String  causa = ex.toString();
        return super.handleExceptionInternal(ex, new Erro(mensagem, causa), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request );
    }


    /**
     * Trata excessão de recurso não encontrado
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String  causa = ex.toString();
        return handleExceptionInternal(ex, new Erro(mensagemUsuario, causa), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach( fieldError -> {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            erros.add( new Erro(mensagemUsuario ,fieldError.toString() ) );
        }
        );
        return erros;
    }


    private final class Erro {

        private final String mensagem;
        private final String causa;

        public Erro(String mensagem, String causa) {
            this.mensagem = mensagem;
            this.causa = causa;
        }

        public String getMensagem() {
            return mensagem;
        }

        public String getCausa() {
            return causa;
        }
    }
}
