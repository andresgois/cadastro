package io.github.andregois.cad.service;

import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.github.andregois.cad.mapper.CepMapper;
import io.github.andregois.cad.model.Cep;

@Stateless
public class ViaCepService {

	public static Cep consultaCep(String cep) {
		Cep cepRequest = null;
		try {
			HttpClient  httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");
			HttpResponse response = httpClient.execute(httpGet);

			String responseBody = EntityUtils.toString(response.getEntity());
			cepRequest = CepMapper.mapFromJson(responseBody, Cep.class);
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		return cepRequest;
	}
}
