module es.echoshop.api.core {
    requires dagger;
    requires org.jooq;
    requires jakarta.inject;
    requires com.zaxxer.hikari;
    requires es.echoshop.api.commons;
    requires java.compiler;

    exports config;
    exports repository;
    exports dto;
    exports es.ecoshop.api.genereted.jooq;
    exports es.ecoshop.api.genereted.jooq.tables;
    exports es.ecoshop.api.genereted.jooq.tables.records;




}