package model.dao;


import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerRT<T> extends Consumer<T> {

    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            //todo throw new DAOException(e);
            throw new RuntimeException(e);
        }
    }

    void acceptThrows(T elem) throws Exception;

}
