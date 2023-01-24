package org.sid.banquemicro.web;

import org.sid.banquemicro.dto.BankAccountRequestDTO;
import org.sid.banquemicro.dto.BankAccountResponseDTO;
import org.sid.banquemicro.entities.BankAccount;
import org.sid.banquemicro.mappers.AccountMapper;
import org.sid.banquemicro.repositories.BankAccountRepository;
import org.sid.banquemicro.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/id")
    public BankAccount BankAccount(@PathVariable String id){
        return  bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account % not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO responseDTO){
        return accountService.addAccount(responseDTO);
    }
    @PutMapping("/bankAccounts/id")
    public  BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        if (bankAccount.getType() != null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);

    }
    @DeleteMapping("/bankAccounts/id")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }
}
