package com.enigma.api.inventory.models.transaction;

import com.enigma.api.inventory.models.PageSearch;
import com.google.api.client.util.DateTime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TransactionSearch extends PageSearch {
    private Integer id;
    private DateTime date;
}
