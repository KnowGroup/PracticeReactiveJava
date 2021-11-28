/*
 * Follow https://github.com/KnowGroup
 */
package org.know.group.practice.reactive.model;

import org.immutables.gson.Gson;
import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

/**
 * @author KnowGroup
 */
@Gson.TypeAdapters
@Mongo.Repository(collection = TransactionDoc.TRANSACTION_DOC)
@Value.Immutable
public interface TransactionDoc {

    String TRANSACTION_DOC = "transactionDoc";

    @Mongo.Id
    String id();

    String accountNumber();

    Transaction transaction();
}
