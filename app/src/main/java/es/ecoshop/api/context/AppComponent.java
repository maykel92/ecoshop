package es.ecoshop.api.context;

import config.DatasourceModule;
import dagger.Component;
import es.ecoshop.api.config.JavalinModule;
import io.javalin.Javalin;
import jakarta.inject.Singleton;

@Singleton
@Component(modules = {JavalinModule.class, DatasourceModule.class})
public interface AppComponent {
    Javalin app();
}
