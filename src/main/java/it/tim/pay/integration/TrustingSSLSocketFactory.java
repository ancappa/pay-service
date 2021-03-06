package it.tim.pay.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.ssl.SSLContexts;

import lombok.extern.slf4j.Slf4j;

/**
 * Used for ssl tests to simplify setup.
 */
@Slf4j
// @Component
public final class TrustingSSLSocketFactory extends SSLSocketFactory implements X509TrustManager, X509KeyManager {

	private static final Map<String, SSLSocketFactory> sslSocketFactories = new LinkedHashMap<String, SSLSocketFactory>();

	private final static String[] ENABLED_CIPHER_SUITES = { "TLS_RSA_WITH_AES_256_CBC_SHA" };
	private SSLSocketFactory delegate = null;
	private final String serverAlias;
	private final PrivateKey privateKey;
	private final X509Certificate[] certificateChain;

	private TrustingSSLSocketFactory(String serverAlias, String gpwd, File keyStoreFile) {

		SSLContext sc = null;
		try {

			sc = SSLContext.getInstance("SSLv3");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.serverAlias = serverAlias;
		if (serverAlias.isEmpty()) {
			this.privateKey = null;
			this.certificateChain = null;
		} else {
			try {

				log.info("1");

				String keyStoreFileName;
			
				keyStoreFileName = keyStoreFile.getAbsolutePath();
				log.info("keyStoreFileName from resource: " + keyStoreFileName);
			
				System.setProperty("javax.net.ssl.trustStore", keyStoreFileName);
				System.setProperty("jdk.tls.client.protocols", "SSLv3,SSLv2Hello,TLSv1.2");
				System.setProperty("https.protocols", "SSLv3,SSLv2Hello,TLSv1.2");
				KeyStore keyStore = loadKeyStore(new FileInputStream(keyStoreFile),gpwd.toCharArray());
				this.privateKey = (PrivateKey) keyStore.getKey(serverAlias, gpwd.toCharArray());

				log.info("privateKey " + privateKey);

				Certificate[] rawChain = keyStore.getCertificateChain(serverAlias);
				this.certificateChain = Arrays.copyOf(rawChain, rawChain.length, X509Certificate[].class);

				log.info("certificateChain " + certificateChain);

				TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(keyStore);

				sc.init(new KeyManager[] { this }, tmf.getTrustManagers(), new SecureRandom());
				sc = SSLContexts.custom().loadTrustMaterial(keyStoreFile).build();

				this.delegate = sc.getSocketFactory();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public synchronized static SSLSocketFactory get(String serverAlias, String pwd, File keyStoreFile) {
		if (!sslSocketFactories.containsKey(serverAlias)) {
			sslSocketFactories.put(serverAlias, new TrustingSSLSocketFactory(serverAlias, pwd, keyStoreFile));
		}
		return sslSocketFactories.get(serverAlias);
	}

	static Socket setEnabledCipherSuites(Socket socket) {
		SSLSocket.class.cast(socket).setEnabledCipherSuites(ENABLED_CIPHER_SUITES);
		return socket;
	}

	private static KeyStore loadKeyStore(InputStream inputStream, char[] KEYSTORE_PASSWORD) throws IOException {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");

			keyStore.load(inputStream, KEYSTORE_PASSWORD);
			return keyStore;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}

		}
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return ENABLED_CIPHER_SUITES;
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return ENABLED_CIPHER_SUITES;
	}

	@Override
	public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		return setEnabledCipherSuites(delegate.createSocket(s, host, port, autoClose));
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return setEnabledCipherSuites(delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		return setEnabledCipherSuites(delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
		return setEnabledCipherSuites(delegate.createSocket(host, port, localHost, localPort));
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		return setEnabledCipherSuites(delegate.createSocket(address, port, localAddress, localPort));
	}

	  public X509Certificate[] getAcceptedIssuers() {
			// return null for future use
			return certificateChain;
		}
	  

	public void checkClientTrusted(X509Certificate[] certs, String authType) {
		// Do nothing because future use
	}

	public void checkServerTrusted(X509Certificate[] certs, String authType) {
		// Do nothing because future use
	}

	@Override
	public String[] getClientAliases(String keyType, Principal[] issuers) {
		// return null for future use
		return null;
	}

	@Override
	public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
		// return null for future use
		return null;
	}

	@Override
	public String[] getServerAliases(String keyType, Principal[] issuers) {
		// return null for future use
		return null;
	}

	@Override
	public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
		return serverAlias;
	}

	@Override
	public X509Certificate[] getCertificateChain(String alias) {
		return certificateChain;
	}

	@Override
	public PrivateKey getPrivateKey(String alias) {
		return privateKey;
	}

}
