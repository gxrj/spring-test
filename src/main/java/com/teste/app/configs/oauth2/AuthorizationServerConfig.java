package com.teste.app.configs.oauth2;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

@Configuration( proxyBeanMethods = false ) 
public class AuthorizationServerConfig {

    @Value( "${oauth2.client-id}" )
    private String clientId;

    @Value( "${oauth2.client-secret}" )
    private String clientSecret;

    @Value( "${oauth2.authorization-server-address}" )
    private String authorizationServerAddress;

  /**
     * Returns a SecurityFilterChain instance customized with 
     * OAuth2AuthorizationServer configurations and other security settings.
     * @param HttpSecurity instance
     * @return SecurityFilterChain instance
     * @exception Exception
     */
    @Bean
    @Order( Ordered.HIGHEST_PRECEDENCE )
    public SecurityFilterChain createSecurityFilterChainBean( HttpSecurity http) throws Exception{
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity( http );
        return http.build();
    }

    /** Define informações sobre o cliente, o mecanismo de salvamento dessas informações
     * e o redirecionamento de rotas caso não autenticado 
     * */
    @Bean
    public RegisteredClientRepository registeredClientRepository(){

        RegisteredClient client = RegisteredClient.withId( UUID.randomUUID().toString() )
        .clientId( clientId )
        .clientSecret( clientSecret )
        .clientAuthenticationMethod( ClientAuthenticationMethod.CLIENT_SECRET_BASIC )
        .authorizationGrantType( AuthorizationGrantType.AUTHORIZATION_CODE )
        .redirectUri( "http://localhost:8080/cadastro" )
        .redirectUri( "http://localhost:8080/autenticacao" )
        .scope( "read" )
        .scope( "write" )
        .build();

        return new InMemoryRegisteredClientRepository( client );
    }

    /**Especifica a uri do authorization server */
    @Bean 
    public ProviderSettings providerSettings(){
        return ProviderSettings.builder().issuer( authorizationServerAddress ).build();
    }

    /**Algorítmo responsável por fabricar o jwt
     * utilizando criptografia RSA
    */
    @Bean
    public JWKSource<SecurityContext> jwkSource(){
        RSAKey rsaKey = generateRsaKey();
        JWKSet jwkSet = new JWKSet( rsaKey );
        return ( jwkSelector, securtiyContext ) -> jwkSelector.select( jwkSet );
    }

    /** Função geradora de pares 
     * com chave Rsa pública e Rsa privada 
    */
    private static RSAKey generateRsaKey(){
        KeyPair kPair;

        try{
            KeyPairGenerator kPairGenerator = KeyPairGenerator. getInstance( "RSA" );
            kPairGenerator.initialize( 2048 );
            kPair = kPairGenerator.generateKeyPair();
        }
        catch( Exception e ){ throw new IllegalStateException( e ); }
        
        RSAPublicKey pubKey = ( RSAPublicKey ) kPair.getPublic();
        RSAPrivateKey privKey = ( RSAPrivateKey ) kPair.getPrivate();

        return new RSAKey.Builder( pubKey )
                        .privateKey( privKey )
                        .keyID( UUID.randomUUID().toString() )
                        .build();
    }
}
