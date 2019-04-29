package com.geeboo.dyna.server;

import com.geeboo.auth.client.EnableAceAuthClient;
import com.geeboo.cache.EnableAceCache;
import com.geeboo.common.eureka.EnableEurekaStatusChange;
import com.geeboo.common.qiniu.EnableQiniu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({"com.geeboo.auth.client.facade", "com.geeboo.*.*.client.facade"})
@EnableScheduling
@EnableAceAuthClient
@ServletComponentScan("com.geeboo.system.server.configuration.druid")
@EnableAceCache
@EnableTransactionManagement
@EnableQiniu
@EnableEurekaStatusChange
public class DynaServerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(DynaServerBootstrap.class, args);
        System.out.print("/**\n"
               + " * #                                                   #\n"
               + " * #                       _oo0oo_                     #\n"
               + " * #                      o8888888o                    #\n"
               + " * #                      88\" . \"88                    #\n"
               + " * #                      (| -_- |)                    #\n"
               + " * #                      0\\  =  /0                    #\n"
               + " * #                    ___/`---'\\___                  #\n"
               + " * #                  .' \\\\|     |# '.                 #\n"
               + " * #                 / \\\\|||  :  |||# \\                #\n"
               + " * #                / _||||| -:- |||||- \\              #\n"
               + " * #               |   | \\\\\\  -  #/ |   |              #\n"
               + " * #               | \\_|  ''\\---/''  |_/ |             #\n"
               + " * #               \\  .-\\__  '-'  ___/-. /             #\n"
               + " * #             ___'. .'  /--.--\\  `. .'___           #\n"
               + " * #          .\"\" '<  `.___\\_<|>_/___.' >' \"\".         #\n"
               + " * #         | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |       #\n"
               + " * #         \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /       #\n"
               + " * #     =====`-.____`.___ \\_____/___.-`___.-'=====    #\n"
               + " * #                       `=---='                     #\n"
               + " * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #\n"
               + " * #                                                   #\n"
               + " * #               佛祖保佑         永无BUG             #\n"
               + " * #                                                   #\n"
               + " */");
    }
}
