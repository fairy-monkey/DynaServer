package com.geeboo.dyna.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String appName1;

    @Value("${eureka.instance.instance-id}")
    private String appName2;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(""
                + " ┏┓   ┏┓\n"
                + "┏┛┻━━━┛┻┓\n"
                + "┃       ┃\n"
                + "┃   ━   ┃\n"
                + "┃ ┳┛ ┗┳ ┃\n"
                + "┃       ┃\n"
                + "┃   ┻   ┃\n"
                + "┃       ┃\n"
                + "┗━┓   ┏━┛\n"
                + "  ┃   ┃\n"
                + "  ┃   ┃\n"
                + "  ┃   ┗━━━┓\n"
                + "  ┃       ┣┓\n"
                + "  ┃       ┏┛\n"
                + "  ┗┓┓┏━┳┓┏┛\n"
                + "代码神兽 草泥马 一切bug退散");
        System.out.println("注册服务地址: DiscoveryClient_" + appName1.toUpperCase() + "/" + appName2);
    }
}
