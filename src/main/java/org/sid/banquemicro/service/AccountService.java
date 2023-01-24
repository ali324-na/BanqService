package org.sid.banquemicro.service;

import org.sid.banquemicro.dto.BankAccountRequestDTO;
import org.sid.banquemicro.dto.BankAccountResponseDTO;
import org.sid.banquemicro.entities.BankAccount;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccounDTO);
}
