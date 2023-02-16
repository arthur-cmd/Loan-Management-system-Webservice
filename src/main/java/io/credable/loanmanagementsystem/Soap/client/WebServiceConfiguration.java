package io.credable.loanmanagementsystem.Soap.client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;

@Configuration
public class WebServiceConfiguration {


    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
        security.setSecurementActions("UsernameToken");
        security.setSecurementUsername("admin");
        security.setSecurementPassword("pwd123");
        security.setSecurementPasswordType("PasswordText");
        security.setSecurementMustUnderstand(true);
        return security;
    }


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.credable.loanmanagementsystem.customerclasses");
        return marshaller;
    }
    @Bean public  SoapClient soapClient (Jaxb2Marshaller marshaller){
        SoapClient client= new SoapClient();
        client.setDefaultUri("https://kycapitest.credable.io/service/customer");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptor= new ClientInterceptor[]{securityInterceptor()};
        client.setInterceptors(interceptor);
        return client;

    }
}
