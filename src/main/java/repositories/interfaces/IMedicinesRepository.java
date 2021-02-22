package repositories.interfaces;
import entities.Medicines;

import java.util.List;
public interface IMedicinesRepository {

    boolean createUser(Medicines medicines);
    Medicines getMedicinesById(int id);
    List<Medicines> getAllMediciness();
}
