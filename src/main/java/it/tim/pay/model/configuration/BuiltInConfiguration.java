package it.tim.pay.model.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "builtin")
@Data
@Component
public class BuiltInConfiguration {

    private String merId;
    private String secret;
    private String secretCF;
    private String isCFEncrypted;
    private String timeOffset;
    private String host;
	private String keystorePath;
	private String keystoreAlias;
	private String keystorePass;
    private String errorURL;
    private String callbackURL;
    private String notifyURL;
    private String validpaysystem;
    
    public BuiltInConfiguration() {
	}

	public BuiltInConfiguration(String merId, String secret, String timeOffset, String host, String keystorePath,
			String keystoreAlias, String keystorePass, String errorURL, String callbackURL, String notifyURL, String validpaysystem, String secretCF, String isCFEncrypted) {
		super();
		this.merId = merId;
		this.secret = secret;
		this.timeOffset = timeOffset;
		this.host = host;
		this.keystorePath = keystorePath;
		this.keystoreAlias = keystoreAlias;
		this.keystorePass = keystorePass;
		this.errorURL = errorURL;
		this.callbackURL = callbackURL;
		this.notifyURL = notifyURL;
		this.validpaysystem = validpaysystem;
		this.isCFEncrypted = isCFEncrypted;
		this.secretCF = secretCF;
	}
}
