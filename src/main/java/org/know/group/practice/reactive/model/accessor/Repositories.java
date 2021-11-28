package org.know.group.practice.reactive.model.accessor;

import com.github.fakemongo.Fongo;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mongodb.client.MongoDatabase;
import io.reactivex.Single;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ServiceLoader;
import java.util.concurrent.Executors;

import static org.immutables.mongo.repository.RepositorySetup.builder;

@SuppressWarnings("SpellCheckingInspection")
class Repositories {

    private static final String DATABASE_NAME = "fongoDB";
    private static final String FONGO_NAME = "Fake Mongo DB";

    private static final MongoDatabase FONGO_DB = new Fongo(FONGO_NAME).getDatabase(DATABASE_NAME);
    private static final ListeningExecutorService SINGLE_THREAD_EXECUTOR = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

    private final AccountDocRepository accountDocRepository;
    private final TransactionDocRepository transactionDocRepository;

    Repositories() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, getInstantTypeAdapter());
        ServiceLoader.load(TypeAdapterFactory.class).forEach(gsonBuilder::registerTypeAdapterFactory);

        this.accountDocRepository = new AccountDocRepository(builder()
                .database(FONGO_DB)
                .gson(gsonBuilder.create())
                .executor(SINGLE_THREAD_EXECUTOR).build());

        this.transactionDocRepository = new TransactionDocRepository(builder()
                .database(FONGO_DB)
                .gson(gsonBuilder.create())
                .executor(SINGLE_THREAD_EXECUTOR).build());
    }

    private TypeAdapter<Instant> getInstantTypeAdapter() {
        return new TypeAdapter<Instant>() {
            @Override
            public void write(JsonWriter out, Instant value) throws IOException {
                out.value(new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendInstant(3)
                        .toFormatter()
                        .format(value));
            }

            @Override
            public Instant read(JsonReader in) throws IOException {
                return DateTimeFormatter.ISO_INSTANT.parse(in.nextString(), Instant::from);
            }
        };
    }

    public Single<AccountDocRepository> getAccountDocRepository() {
        return Single.just(accountDocRepository);
    }

    public Single<TransactionDocRepository> getTransactionDocRepository() {
        return Single.just(transactionDocRepository);
    }
}
