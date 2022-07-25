package com.thirdparty.controller;

import com.thirdparty.data.GetTransactionRequest;
import com.thirdparty.data.GetTransactionResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping("create")
    public String create(@RequestHeader("partner-key") String partnerKey) {
        return "Save transaction to Dynamo DB and returns reference number";
    }

    @GetMapping()
    public GetTransactionResponse fetch(@RequestHeader("partner-key") String partnerKey, @Valid @RequestBody GetTransactionRequest request) {
        return null;
    }
}
