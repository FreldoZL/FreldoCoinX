package backEnd.controller;

import backEnd.model.Balance;
import backEnd.service.SmartContractService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/smart_contract")
@Api(description = "Services for smart contracts", name = "Smart contract controller")
public class SmartContractController {

    @Autowired
    private SmartContractService smartContractService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to get ether balance in ETH.")
    public @ResponseBody Float getBalance() {
        return smartContractService.getBalance();
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to get contract address")
    public @ResponseBody String getContractAddress() throws IOException {
        return smartContractService.getContractAddress();
    }

    @RequestMapping(value = "/tokens", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to get purchased tokens amount")
    public @ResponseBody Float getTokensAmount() throws IOException {
        return smartContractService.getTokensAmount();
    }

    @RequestMapping(value = "/eth_price", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to get Ether price in USD")
    public @ResponseBody Float getEthPriceInUSD() throws IOException {
        return smartContractService.getEthPriceInUSD();
    }

    @Scheduled(cron = "0 */60 * * * ?")
    @RequestMapping(value = "/update_balance_eth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to update smart contract balance")
    public void updateBalanceEth() {
        smartContractService.updateBalanceEth();
    }

    @Scheduled(cron = "0 */60 * * * ?")
    @RequestMapping(value = "/update_balance_usd", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to update smart contract balance")
    public void updateBalanceUsd() {
        smartContractService.updateBalanceUsd();
    }

    @RequestMapping(value = "/balance_eth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to get Ether balance")
    public @ResponseBody Balance getEthBalance() throws IOException {
        return smartContractService.getEthBalance();
    }
}
