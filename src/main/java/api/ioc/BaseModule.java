package api.ioc;

import api.client.AuthClient;
import api.client.IAuthClient;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

public class BaseModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(IAuthClient.class).to(AuthClient.class).in(Singleton.class);
    }
}
