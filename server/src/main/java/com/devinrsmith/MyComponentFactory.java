package com.devinrsmith;

import com.devinrsmith.MyComponentFactory.MyComponent;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.deephaven.appmode.ApplicationState;
import io.deephaven.configuration.Configuration;
import io.deephaven.server.auth.AllowAllAuthorizationProvider;
import io.deephaven.server.auth.AuthorizationProvider;
import io.deephaven.server.jetty.JettyConfig;
import io.deephaven.server.jetty.JettyServerComponent;
import io.deephaven.server.jetty.JettyServerModule;
import io.deephaven.server.runner.CommunityDefaultsModule;
import io.deephaven.server.runner.ComponentFactoryBase;
import java.io.PrintStream;
import javax.inject.Singleton;

public class MyComponentFactory extends ComponentFactoryBase<MyComponent> {

  @Override
  public MyComponent build(Configuration configuration, PrintStream out, PrintStream err) {
    final JettyConfig jettyConfig = JettyConfig.buildFromConfig(configuration).build();
    return DaggerMyComponentFactory_MyComponent.builder()
        .withOut(out)
        .withErr(err)
        .withJettyConfig(jettyConfig)
        .build();
  }

  @Singleton
  @Component(modules = MyModule.class)
  public interface MyComponent extends JettyServerComponent {

    @Component.Builder
    interface Builder extends JettyServerComponent.Builder<Builder, MyComponent> {}
  }

  @Module(
      includes = {
        JettyServerModule.class,
        CommunityDefaultsModule.class,
      })
  public interface MyModule {

    @Binds
    AuthorizationProvider bindsAuthorizationProvider(
        AllowAllAuthorizationProvider allowAllAuthorizationProvider);

    @Binds
    @IntoSet
    ApplicationState.Factory bindsStartupTimeApp(MyStartupTimeApplication startupTimeApplication);
  }
}
