package br.com.bpsistemas.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey;

	@Value("${s3.region}")
	private String region;

	@Value("${proxy.host}")
	private String HTTP_HOST;

	@Value("${proxy.port}")
	private Integer HTTP_PORT;

	@Bean
	public AmazonS3 s3Client() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCred))
				.withClientConfiguration(getClientConfiguration(HTTP_HOST, HTTP_PORT)) // PROXY
				.build();
		return s3client;
	}

	private ClientConfiguration getClientConfiguration(String proxyHost, Integer proxyPort) {
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setProxyHost(proxyHost);
		clientConfiguration.setProxyPort(proxyPort);
		return clientConfiguration;
	}

}
