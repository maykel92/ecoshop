module es.echoshop.api.commons {
    requires jakarta.validation;

    exports validator;
    exports exceptions;
    exports dto.response.error;
}