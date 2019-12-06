package by.epam.project.logic;

import java.util.List;

import by.epam.project.exception.LogicException;

public interface Logic {
    void action(List<String> parameters) throws LogicException;
}
