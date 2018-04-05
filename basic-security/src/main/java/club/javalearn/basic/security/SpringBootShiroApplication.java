package club.javalearn.basic.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@ServletComponentScan
@SpringBootApplication
public class SpringBootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroApplication.class,args);
    }
}
