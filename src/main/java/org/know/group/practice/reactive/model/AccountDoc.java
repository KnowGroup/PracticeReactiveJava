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
@Mongo.Repository(collection = AccountDoc.ACCOUNT_DOC)
@Value.Immutable
public interface AccountDoc {

    String ACCOUNT_DOC = "accountDoc";

    @Mongo.Id
    String id();

    Account account();
}
