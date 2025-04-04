package hw.diploma.configserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigServer
@Slf4j
public class ConfigServerApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
