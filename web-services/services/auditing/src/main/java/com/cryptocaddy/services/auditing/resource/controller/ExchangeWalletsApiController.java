package com.cryptocaddy.services.auditing.resource.controller;

import com.cryptocaddy.services.auditing.resource.api.AbstractRestHandler;
import com.cryptocaddy.services.auditing.resource.api.ExchangeWalletsApi;
import com.cryptocaddy.services.auditing.resource.model.AuditReport;
import com.cryptocaddy.services.auditing.resource.model.attributes.Exchange;
import com.cryptocaddy.services.auditing.resource.service.ExchangeWalletsService;
import com.cryptocaddy.services.auditing.resource.validation.ExchangeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jon Waggoner
 * Date: 1/18/2018
 */
@RestController
public class ExchangeWalletsApiController extends AbstractRestHandler implements ExchangeWalletsApi {
    private ExchangeWalletsService exchangeWalletsService;

    @Autowired
    public ExchangeWalletsApiController(ExchangeWalletsService exchangeWalletsService) {
        this.exchangeWalletsService = exchangeWalletsService;
    }

    @Override
    public ResponseEntity<AuditReport> getExchangeWallets(Exchange exchange) {

        ExchangeValidator exchangeValidator = new ExchangeValidator();
        if (!exchangeValidator.test(exchange)) {
            return new ResponseEntity<>(new AuditReport(), HttpStatus.BAD_REQUEST);
        }

        AuditReport result = exchangeWalletsService.getExchangeWallets(exchange);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
