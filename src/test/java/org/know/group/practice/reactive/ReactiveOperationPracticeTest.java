package org.know.group.practice.reactive;

import com.google.common.base.Optional;
import org.junit.BeforeClass;
import org.junit.Test;
import org.know.group.practice.reactive.model.ImmutableAccount;

import static org.know.group.practice.reactive.DBData.accountDocs;
import static org.know.group.practice.reactive.DBData.transactionDocs;

public class ReactiveOperationPracticeTest {
    private static ReactiveOperationPractice reactiveOperationPractice;

    /**
     * Example 1
     *
     * @throws InterruptedException
     */
    @Test
    public void shouldBeAbleFetchAccountByAccountNumber() throws InterruptedException {
        reactiveOperationPractice.fetchAccount("AccountNumber1")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue(Optional::isPresent)
                .assertValue(accountOptional -> accountOptional.equals(Optional.of(ImmutableAccount.builder().accountNumber("AccountNumber1").accountType("Current").build())));
    }

    /**
     * Practice 1
     *
     * @throws InterruptedException
     */
    @Test
    public void shouldBeAbleFetchAllAccountsByAccountType() throws InterruptedException {
        reactiveOperationPractice.fetchAccounts("Current")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue(accounts -> accounts.stream().anyMatch(account -> "AccountNumber1".equals(account.accountNumber())))
                .assertValue(accounts -> accounts.stream().anyMatch(account -> "AccountNumber2".equals(account.accountNumber())))
                .assertValue(accounts -> accounts.stream().anyMatch(account -> "AccountNumber3".equals(account.accountNumber())));
    }

    /**
     * Practice 2
     *
     * @throws InterruptedException
     */
    @Test
    public void shouldBeAbleFetchAllTransactionsForAccountByAccountNumber() throws InterruptedException {
        reactiveOperationPractice.fetchAllTransactionsForAccount("AccountNumber3")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue(transactions -> transactions.size() == 1)
                .assertValue(transactions -> transactions.iterator().next().amount() == -103.0);
    }

    /**
     * Practice 3
     *
     * @throws InterruptedException
     */
    @Test
    public void shouldBeAbleFetchAllNegativeTransactionsForAccountsByAccountType() throws InterruptedException {
        /*
        TODO Implement Code
        reactiveOperationPractice.fetchAllNegativeTransactionsForAccount("Current")
         */
        throw new RuntimeException("To be implemented");
    }

    /**
     * Practice 4
     *
     * @throws InterruptedException
     */
    @Test
    public void shouldBeAblePersistNewTransactionForAccount() throws InterruptedException {
        reactiveOperationPractice.pushNewTransactionsForAccount("AccountNumber5", 105.1)
                .test()
                .await()
                .assertNoErrors()
                .assertComplete();

        reactiveOperationPractice.fetchAllTransactionsForAccount("AccountNumber5")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue(transactions -> transactions.size() == 1)
                .assertValue(transactions -> transactions.iterator().next().amount() == 105.1);
    }

    @BeforeClass
    public static void setup() throws InterruptedException {
        reactiveOperationPractice = new ReactiveOperationPractice();
        reactiveOperationPractice.setupDB(accountDocs(), transactionDocs()).test().await().assertNoErrors().assertComplete();
    }
}