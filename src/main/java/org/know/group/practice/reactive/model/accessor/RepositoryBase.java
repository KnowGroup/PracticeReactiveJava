package org.know.group.practice.reactive.model.accessor;

import com.google.common.collect.ImmutableList;
import org.immutables.mongo.concurrent.FluentFuture;
import org.immutables.mongo.repository.Repositories;
import org.immutables.mongo.repository.RepositorySetup;
import org.immutables.mongo.repository.internal.Constraints;

import java.util.function.Function;

abstract class RepositoryBase<T> extends Repositories.Repository<T> {

    RepositoryBase(RepositorySetup configuration, String collectionName, Class<T> type) {
        super(configuration, collectionName, type);
    }

    class RepositoryFinder extends Repositories.Finder<T, RepositoryFinder> {
        public RepositoryFinder(RepositoryBase repository, Constraints.ConstraintHost criteria) {
            super(repository);
            this.criteria = criteria;
        }
    }

    public RepositoryFinder finder() {
        return new RepositoryFinder(this, Constraints.nilConstraint());
    }

    public Function<Constraints.Constraint, RepositoryFinder> searchBy() {
        return (searchConstraint) -> new RepositoryFinder(this, searchConstraint);
    }

    public FluentFuture<Integer> insert(ImmutableList<T> docs) {
        return doInsert(docs);
    }
}
