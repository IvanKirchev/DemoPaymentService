package com.example.demopayment.projection;

import java.util.UUID;

public record TransactionPartiesOnly (UUID senderId, UUID receiverId){}
