package org.sid.banquemicro.service;

import org.sid.banquemicro.dto.BankAccountRequestDTO;
import org.sid.banquemicro.dto.BankAccountResponseDTO;
import org.sid.banquemicro.entities.BankAccount;
import org.sid.banquemicro.mappers.AccountMapper;
import org.sid.banquemicro.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccounDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccounDTO.getBalance())
                .type(bankAccounDTO.getType())
                .currency(bankAccounDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);

        return bankAccountResponseDTO;
    }
}
