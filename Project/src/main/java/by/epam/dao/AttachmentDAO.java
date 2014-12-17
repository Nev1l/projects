package by.epam.dao;

import by.epam.beans.Attachment;


public interface AttachmentDAO {
	void save(Attachment p);
	void update(Attachment p);
	void delete(int id);
	Attachment getAttachmentById(int id);
}
