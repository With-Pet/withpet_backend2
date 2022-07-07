package com.withpet.backend.dto.post;

import lombok.Data;

import java.util.ArrayList;

@Data
public class KaKaoAddressResult {
    private ArrayList<AddressDocuments> documents;
}
