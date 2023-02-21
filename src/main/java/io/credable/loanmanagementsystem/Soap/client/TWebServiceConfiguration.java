//package io.credable.loanmanagementsystem.Soap.client;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//import org.springframework.ws.client.support.interceptor.ClientInterceptor;
//import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
//
//@Configuration
//public class TWebServiceConfiguration {
//
//    @Bean
//    public Wss4jSecurityInterceptor securityInterceptor() {
//        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
//        security.setSecurementActions("UsernameToken");
//        security.setSecurementUsername("admin");
//        security.setSecurementPassword("pwd123");
//        security.setSecurementPasswordType("PasswordText");
//        security.setSecurementMustUnderstand(true);
//        return security;
//    }
//
//    @Bean
//    public Jaxb2Marshaller Tmarshaller() {
//        Jaxb2Marshaller Tmarshaller = new Jaxb2Marshaller();
//        Tmarshaller.setContextPath("io.credable.loanmanagementsystem.transactionclasses");
//        return Tmarshaller;
//    }
//
//    @Bean
//    public TransactionClient TsoapClient(Jaxb2Marshaller Tmarshaller) {
//        TransactionClient Tclient = new TransactionClient();
//        Tclient.setDefaultUri("https://kycapitest.credable.io/service/customer");
//        Tclient.setMarshaller(Tmarshaller);
//        Tclient.setUnmarshaller(Tmarshaller);
//        ClientInterceptor[] Tinterceptor = new ClientInterceptor[]{securityInterceptor(),  new SoapInterceptor("")};
//        Tclient.setInterceptors(Tinterceptor);
//        return Tclient;
//
//    }
//}
