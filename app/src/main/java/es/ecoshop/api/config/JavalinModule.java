package es.ecoshop.api.config;

import dagger.Module;
import dagger.Provides;
import dto.AppConfig;
import dto.response.error.ErrorApi;
import dto.response.error.TypeError;
import exceptions.TypeHeaderException;
import io.javalin.Javalin;
import io.javalin.http.ContentType;
import io.javalin.http.Header;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Locale;

@Module
public class JavalinModule {

    @Provides
    @Singleton
    public Javalin provideJavalinApp(AppConfig appConfig) {
        var app = Javalin.create(config -> {
            config.useVirtualThreads = appConfig.virtualThread();
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(it -> it.allowHost("http://localhost")));
        });

        app.exception(ValidationException.class, (e, ctx) -> {
            List<String> mensagess = e.getErrors().values().stream()
                    .flatMap(List::stream)
                    .map(ValidationError::getMessage)
                    .toList();

            var error = ErrorApi.builder()
                    .typeError(TypeError.VALIDATION_EXCEPTION)
                    .messages(mensagess)
                    .patch(ctx.path())
                    .status(HttpStatus.BAD_REQUEST.getCode());

            ctx.status(HttpStatus.BAD_REQUEST)
             .json(error);
        });
        app.exception(ConstraintViolationException.class, (e, ctx) -> {
            List<String> mensajes = e.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            var error = ErrorApi.builder()
                    .typeError(TypeError.CONSTRAINT_VIOLATION_EXCEPTION)
                    .message("varios errores")
                    .messages(mensajes)
                    .patch(ctx.path())
                    .status(HttpStatus.BAD_REQUEST.getCode())
                    .build();

            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(error);
        });

        app.beforeMatched((ctx) -> {
            String headerAccept = ctx.header(Header.ACCEPT);

            if (headerAccept == null)
                throw new TypeHeaderException("Se esperaba un objeto en formato JSON (cabecera Accept ausente)");

            String acceptLower = headerAccept.toLowerCase(Locale.ROOT);
            String jsonMime = ContentType.APPLICATION_JSON.getMimeType().toLowerCase(Locale.ROOT);

            if (!(acceptLower.contains(jsonMime)
                    || acceptLower.contains("application/*")
                    || acceptLower.contains("*/*"))) {
                throw new TypeHeaderException("Se esperaba un objeto en formato JSON");
            }

            ctx.contentType(ContentType.APPLICATION_JSON);
        });

        app.start(appConfig.serverPort());

        return app;
    }
}
