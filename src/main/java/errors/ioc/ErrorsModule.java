package errors.ioc;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import errors.exceptions.handlers.APIErrorHandler;
import errors.interfaces.IErrorHandler;

public class ErrorsModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(IErrorHandler.class).annotatedWith(Names.named(Type.API.name())).to(APIErrorHandler.class).in(Singleton.class);
    }
}
