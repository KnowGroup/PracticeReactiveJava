package org.know.group.practice.reactive.model.accessor;

import org.immutables.mongo.repository.RepositorySetup;
import org.know.group.practice.reactive.model.TransactionDoc;

import static org.know.group.practice.reactive.model.TransactionDoc.TRANSACTION_DOC;

class TransactionDocRepository extends RepositoryBase<TransactionDoc> {

    TransactionDocRepository(RepositorySetup configuration) {
        super(configuration, TRANSACTION_DOC, TransactionDoc.class);
    }
}
