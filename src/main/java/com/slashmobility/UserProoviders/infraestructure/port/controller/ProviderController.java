package com.slashmobility.UserProoviders.infraestructure.port.controller;

import com.slashmobility.UserProoviders.application.command.CmdCreateProvider;
import com.slashmobility.UserProoviders.application.command.CmdUpdateProvider;
import com.slashmobility.UserProoviders.application.query.QueryProvider;
import com.slashmobility.UserProoviders.application.query.QueryProviders;
import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.infraestructure.mapper.ProviderMapper;
import com.slashmobility.UserProoviders.infraestructure.type.ProviderUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private CmdCreateProvider cmdCreateProvider;

    @Autowired
    private QueryProviders queryProviders;

    @Autowired
    private QueryProvider queryProvider;

    @Autowired
    private CmdUpdateProvider cmdUpdateProvider;

    @GetMapping("/{providerId}")
    public ResponseEntity<Provider> getProvider(@PathVariable("providerId") String providerId) {
        return this.queryProvider.handle(providerId)
                .map(provider -> new ResponseEntity<>(provider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/")
    public ResponseEntity<List<Provider>> getProviders() {
        return this.queryProviders.handle()
                .map(providers -> new ResponseEntity<>(providers, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/")
    public ResponseEntity createProvider(@RequestBody Provider provider) {

        return new ResponseEntity<>(this.cmdCreateProvider.handle(provider), HttpStatus.OK);

    }

    @PutMapping("/{providerId}")
    public ResponseEntity updateProvider(@PathVariable String providerId,
                                        @RequestBody ProviderUpdateRequest providerUpdateRequest) {
        Provider provider = this.providerMapper.toDomainOfUpdateRequest(providerUpdateRequest);
        provider.setName(providerId);
        return new ResponseEntity<>(this.cmdUpdateProvider.handle(provider) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);

    }

}
