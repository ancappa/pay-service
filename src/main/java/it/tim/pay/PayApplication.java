package it.tim.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.DefaultFeignLoggerFactory;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignLoggerFactory;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import it.tim.pay.common.headers.TimHeaders;
import it.tim.pay.util.FeignExtendedLogger;
import it.tim.pay.web.HeadersInterceptor;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"it.tim.pay"})
public class PayApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}



	private HeadersInterceptor headersInterceptor;

	@Autowired
    public PayApplication(HeadersInterceptor headersInterceptor) {
        this.headersInterceptor = headersInterceptor;
    }

	@Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public TimHeaders timHeaders(){
		return new TimHeaders();
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headersInterceptor)
                .addPathPatterns("/**/pay*/**/");
    }
    
    @Bean
    public FeignLoggerFactory feignLoggerFactory() {
    	return new DefaultFeignLoggerFactory(new FeignExtendedLogger());
    }

}