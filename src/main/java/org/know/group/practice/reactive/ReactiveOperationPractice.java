/*
 * Follow https://github.com/KnowGroup
 */

package org.know.group.practice.reactive;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.know.group.practice.reactive.model.Account;
import org.know.group.practice.reactive.model.AccountDoc;
import org.know.group.practice.reactive.model.Transaction;
import org.know.group.practice.reactive.model.TransactionDoc;
import org.know.group.practice.reactive.model.accessor.ReactiveModelAccessor;

import java.util.List;
import java.util.Map;

/**
 * @author KnowGroup
 */
public class ReactiveOperationPractice {

    private final ReactiveModelAccessor accessor;

    public ReactiveOperationPractice() {
        accessor = new ReactiveModelAccessor();
    }

    /**
     * Example 1
     * Single<Optional<T1>> -> Single<Optional<T2>>
     *
     * @param accountNumber
     * @return
     */
    public Single<Optional<Account>> fetchAccount(final String accountNumber) {
        return accessor.findLatestAccount(accountNumber)
                .map(accountDocOptional -> accountDocOptional.transform(AccountDoc::account));
    }

    /**
     * Practice 1
     * Single<List<Optional<T1>>> -> Single<List<T1>>
     *
     * @param accountType
     * @return
     */
    public Single<List<Account>> fetchAccounts(final String accountType) {
        //TODO Implement Code
        return null;
    }

    /**
     * Practice 2
     * Single<Optional<T1>> -> Single<List<T2>>
     *
     * @param accountNumber
     * @return
     */
    public Single<List<Transaction>> fetchAllTransactionsForAccount(final String accountNumber) {
        //TODO Implement Code
        return null;
    }

    /**
     * Practice 3
     * Single<List<Optional<T1>>> + Single<List<Optional<T2>>> -> Single<Map<T3,List<T4>>>
     *
     * @param accountType
     * @return
     */
    public Single<Map<Account, List<Transaction>>> fetchAllNegativeTransactionsForAccount(final String accountType) {
        //TODO Implement Code
        return null;
    }

    /**
     * Practice 4
     * Completable
     *
     * @param accountNumber
     * @return
     */
    public Completable pushNewTransactionsForAccount(final String accountNumber, final Double amount) {
        //TODO Implement Code
        return null;
    }

    public Completable setupDB(ImmutableList<AccountDoc> accountDocs, ImmutableList<TransactionDoc> transactionDocs) {
        return accessor.setupDB(accountDocs, transactionDocs);
    }
}
