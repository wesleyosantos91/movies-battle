package br.com.letscode.api.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message, String messageDeveloper) {
        erros.add(new FieldMessage(fieldName, message, messageDeveloper));
    }
}
