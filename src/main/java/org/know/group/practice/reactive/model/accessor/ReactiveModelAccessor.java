package org.know.group.practice.reactive.model.accessor;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.know.group.practice.reactive.model.AccountDoc;
import org.know.group.practice.reactive.model.TransactionDoc;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.know.group.practice.reactive.model.accessor.AccountDocRepository.byAccountNumber;

public class ReactiveModelAccessor {

    private final Repositories repositories = new Repositories();

    public Single<Optional<AccountDoc>> findLatestAccount(String accountNumber) {
        return repositories.getAccountDocRepository()
                .flatMap(accountDocRepository -> Single.fromFuture(accountDocRepository.searchBy().apply(byAccountNumber(accountNumber)).fetchFirst()));
    }

    public Completable setupDB(ImmutableList<AccountDoc> accountDocs, ImmutableList<TransactionDoc> transactionDocs) {
        return repositories.getAccountDocRepository()
                .flatMapCompletable(accountDocRepository -> Single.fromFuture(accountDocRepository.insert(accountDocs))
                        .flatMapCompletable(insertedRecords ->
                                Single.fromFuture(accountDocRepository.finder().fetchAll())
                                        .flatMapCompletable(allDocs -> {
                                            Logger.getAnonymousLogger().log(Level.INFO, String.format("Total number of Accounts : %d", allDocs.size()));
                                            return Completable.complete();
                                        })))
                .andThen(Completable.defer(() -> repositories.getTransactionDocRepository()
                        .flatMapCompletable(transactionDocRepository -> Single.fromFuture(transactionDocRepository.insert(transactionDocs))
                                .flatMapCompletable(insertedRecords ->
                                        Single.fromFuture(transactionDocRepository.finder().fetchAll())
                                                .flatMapCompletable(allDocs -> {
                                                    Logger.getAnonymousLogger().log(Level.INFO, String.format("Total number of Transactions : %d", allDocs.size()));
                                                    return Completable.complete();
                                                })))));
    }
}
