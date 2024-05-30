package io.github.andregois.cad.service;

import java.io.IOException;

import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
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
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cepRequest;
	}
}
