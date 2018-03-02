package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.CustomUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2/11/2018.
 */

@Getter
@Setter
@Builder
public class AccountDTO {

    private String number;
    private long amount;
    private Currency currency;
    private CustomUser customUser;

}
