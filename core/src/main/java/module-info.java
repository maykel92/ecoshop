module es.echoshop.api.core {
    requires dagger;
    requires org.jooq;
    requires jakarta.inject;
    requires com.zaxxer.hikari;

    exports config;
    exports repository;
    exports dto;

}