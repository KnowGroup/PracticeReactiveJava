package org.know.group.practice.reactive.model.accessor;

import org.immutables.mongo.repository.RepositorySetup;
import org.immutables.mongo.repository.internal.Constraints;
import org.know.group.practice.reactive.model.AccountDoc;

import static org.know.group.practice.reactive.model.AccountDoc.ACCOUNT_DOC;

class AccountDocRepository extends RepositoryBase<AccountDoc> {

    AccountDocRepository(RepositorySetup configuration) {
        super(configuration, ACCOUNT_DOC, AccountDoc.class);
    }

    public static Constraints.Constraint byAccountNumber(final String accountNumber) {
        return Constraints.nilConstraint().equal("account.accountNumber", false, accountNumber);
    }
}
