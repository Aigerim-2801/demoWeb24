import data.PostgresDB;
import data.interfaces.IDB;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.MedicinesRepository;
import repositories.interfaces.IMedicinesRepository;

public class MyApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDB.class);
        bind(MedicinesRepository.class).to(IMedicinesRepository.class);
    }
}
}