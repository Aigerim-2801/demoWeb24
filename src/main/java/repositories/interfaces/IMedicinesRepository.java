package repositories.interfaces;
import entities.Medicines;

import java.util.List;
public interface IMedicinesRepository {

    boolean createMedicines(Medicines medicines);
    Medicines getMedicinesById(int id);
    List<Medicines> searchMedicinesByName(String name);
}
