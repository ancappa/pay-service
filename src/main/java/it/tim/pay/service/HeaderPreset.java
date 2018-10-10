package it.tim.pay.service;

import java.security.Key;
import java.security.MessageDigest;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpHeaders;
import org.tomitribe.auth.signatures.Signature;
import org.tomitribe.auth.signatures.Signer;

import it.tim.pay.model.configuration.BuiltInConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderPreset {
	public HttpHeaders getHeaders4Get(String method, String uri, BuiltInConfiguration builtInConfiguration) {

		HttpHeaders httpHeaders = new HttpHeaders();
		try {

			final String keyString = builtInConfiguration.getSecret();
			final String merchantId = builtInConfiguration.getMerId();
			final String host = builtInConfiguration.getHost();

			TemporalAccessor tA = ZonedDateTime.now(ZoneOffset.UTC);
			if (!builtInConfiguration.getTimeOffset().equals("enabled")) {
				tA = ZonedDateTime.now(ZoneOffset.systemDefault());
			}
			final String date = new String(DateTimeFormatter.RFC_1123_DATE_TIME.format(tA));
			
			// SIGNATURE
			final Signature signature = new Signature(merchantId, "hmac-sha256", null, "host", "date", "(request-target)");
			final Key key = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
			final Signer signer = new Signer(key, signature);

			final Map<String, String> headers = new HashMap<>();
			headers.put("host", host);
			headers.put("date", date);
			headers.put("(request-target)", method+" "+uri);			
			
			Signature httpSignature = signer.sign(method, uri, headers);

			httpHeaders.add("host", host);
			httpHeaders.add("date", date);
			httpHeaders.add("authorization", httpSignature.toString());

		} catch (Exception e) {
			log.debug("Errore in fase di creazione della signature"+e.getMessage());
		}
		return httpHeaders;
	}		
	
	public HttpHeaders getHeaders4Post(String method, String uri, String payload, BuiltInConfiguration builtInConfiguration) {

		HttpHeaders httpHeaders = new HttpHeaders();
		try {

			final String keyString = builtInConfiguration.getSecret();
			final String merchantId = builtInConfiguration.getMerId();
			final String host = builtInConfiguration.getHost();

			TemporalAccessor tA = ZonedDateTime.now(ZoneOffset.UTC);
			if (!builtInConfiguration.getTimeOffset().equals("enabled")) {
				tA = ZonedDateTime.now(ZoneOffset.systemDefault());
			}
			final String date = new String(DateTimeFormatter.RFC_1123_DATE_TIME.format(tA));
			
			// DIGEST
			final byte[] digest = MessageDigest.getInstance("SHA-256").digest(payload.getBytes());
			final String digestHeader = "SHA-256=" + new String(Base64.getEncoder().encode(digest));

			// SIGNATURE
			final Signature signature = new Signature(merchantId, "hmac-sha256", null, "host", "date",
					"(request-target)", "digest");
			final Key key = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
			final Signer signer = new Signer(key, signature);

			final Map<String, String> headers = new HashMap<>();
			headers.put("host", host);
			headers.put("date", date);
			headers.put("(request-target)", method+" "+uri);
			headers.put("digest", digestHeader);		
			
			Signature httpSignature = signer.sign(method, uri, headers);

			httpHeaders.add("host", host);
			httpHeaders.add("date", date);
			httpHeaders.add("digest", digestHeader);
			httpHeaders.add("authorization", httpSignature.toString());

		} catch (Exception e) {
			log.debug("Errore in fase di creazione della signature"+e.getMessage());
		}
		return httpHeaders;
	}		
}