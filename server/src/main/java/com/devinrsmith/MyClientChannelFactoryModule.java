package com.devinrsmith;

import dagger.Module;
import dagger.Provides;
import io.deephaven.client.impl.BarrageSessionFactoryConfig;
import io.deephaven.server.session.ClientChannelFactoryModule;
import io.deephaven.server.session.ClientChannelFactoryModule.UserAgent;
import io.deephaven.server.session.SslConfigModule;
import java.util.List;

@Module(includes = {ClientChannelFactoryModule.class, SslConfigModule.class})
public class MyClientChannelFactoryModule {

  @Provides
  @UserAgent
  static String providesUserAgent() {
    return BarrageSessionFactoryConfig.userAgent(List.of("my-deephaven-server"));
  }
}
