package by.epam.dao;

import by.epam.beans.Position;


public interface PositionDAO {
	void save(Position p);
	void update(Position p);
	void delete(int id);
	//Position getPositionByEmployeeId(int id);
}
