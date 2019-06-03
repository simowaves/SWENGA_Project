package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.DocumentModel;
 
@Repository
@Transactional
public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {
}