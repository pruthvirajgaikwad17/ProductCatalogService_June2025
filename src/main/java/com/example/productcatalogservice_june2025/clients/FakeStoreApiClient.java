package com.example.productcatalogservice_june2025.clients;

import com.example.productcatalogservice_june2025.dtos.FakeStoreProductDto;
import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto replaceFakeStoreProduct(FakeStoreProductDto fakeStoreProductDtoInput, Long id ) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDtoInput,
                FakeStoreProductDto.class,
                id
        );
        FakeStoreProductDto fakeStoreProductDtoOutput = fakeStoreProductDtoResponseEntity.getBody();
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoOutput;
        }

        return null;
    }

    public FakeStoreProductDto createFakeStoreProduct(FakeStoreProductDto fakeStoreProductDtoInput) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                fakeStoreProductDtoInput,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto fakeStoreProductDtoOutput = fakeStoreProductDtoResponseEntity.getBody();
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoOutput;
        }
        return null;
    }

    public FakeStoreProductDto getFakeStoreProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                id);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDto;
        }
        return null;
    }

    public List<FakeStoreProductDto> getFakeStoreProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<FakeStoreProductDto> fakeStoreProductDto = Arrays.asList(fakeStoreProductDtoResponseEntity.getBody());
        if(validateResponse(fakeStoreProductDtoResponseEntity)) {
            return fakeStoreProductDto;
        }
        return null;
    }

    private <T> boolean validateResponse(ResponseEntity<T> response) {
        return response.getBody() != null &&
                (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED);
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
}
