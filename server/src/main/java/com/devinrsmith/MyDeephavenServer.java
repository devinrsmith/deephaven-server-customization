package com.devinrsmith;

import io.deephaven.configuration.Configuration;
import io.deephaven.server.runner.MainHelper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyDeephavenServer {

    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException, TimeoutException {
        final Configuration configuration = MainHelper.init(args, MyDeephavenServer.class);
        new MyComponentFactory()
                .build(configuration)
                .getServer()
                .run()
                .join();
    }
}
