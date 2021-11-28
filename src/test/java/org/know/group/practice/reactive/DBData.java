package org.know.group.practice.reactive;

import com.google.common.collect.ImmutableList;
import org.know.group.practice.reactive.model.*;

import java.time.Instant;
import java.util.UUID;

/**
 * Data used to setup Database
 */
public class DBData {

    public static ImmutableList<AccountDoc> accountDocs() {
        return ImmutableList.of(
                ImmutableAccountDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .account(ImmutableAccount.builder()
                                .accountNumber("AccountNumber1")
                                .accountType("Current")
                                .build()).build(),
                ImmutableAccountDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .account(ImmutableAccount.builder()
                                .accountNumber("AccountNumber2")
                                .accountType("Current")
                                .build()).build(),
                ImmutableAccountDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .account(ImmutableAccount.builder()
                                .accountNumber("AccountNumber3")
                                .accountType("Current")
                                .build()).build(),
                ImmutableAccountDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .account(ImmutableAccount.builder()
                                .accountNumber("AccountNumber4")
                                .accountType("Saving")
                                .build()).build(),
                ImmutableAccountDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .account(ImmutableAccount.builder()
                                .accountNumber("AccountNumber5")
                                .accountType("Saving")
                                .build()).build());
    }

    public static ImmutableList<TransactionDoc> transactionDocs() {
        return ImmutableList.of(ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber1")
                        .transaction(ImmutableTransaction.builder()
                                .amount(101.1)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber1")
                        .transaction(ImmutableTransaction.builder()
                                .amount(-101.2)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber1")
                        .transaction(ImmutableTransaction.builder()
                                .amount(-101.3)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber2")
                        .transaction(ImmutableTransaction.builder()
                                .amount(102.0)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber3")
                        .transaction(ImmutableTransaction.builder()
                                .amount(-103.0)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber4")
                        .transaction(ImmutableTransaction.builder()
                                .amount(104.1)
                                .executionTime(Instant.now())
                                .build()).build(),
                ImmutableTransactionDoc.builder()
                        .id(UUID.randomUUID().toString())
                        .accountNumber("AccountNumber4")
                        .transaction(ImmutableTransaction.builder()
                                .amount(104.2)
                                .executionTime(Instant.now())
                                .build()).build());
    }
}
