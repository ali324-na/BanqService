package org.sid.banquemicro.mappers;

import org.sid.banquemicro.dto.BankAccountResponseDTO;
import org.sid.banquemicro.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return  bankAccountResponseDTO;
    }
}
