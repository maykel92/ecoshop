module es.echoshop.api.commons {
    requires jakarta.validation;
    requires java.desktop;

    exports validator;
    exports exceptions;
    exports dto.response.error;
    exports pagination;
}