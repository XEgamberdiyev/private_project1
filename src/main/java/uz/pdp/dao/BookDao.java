package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Book;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.constants.Constants.bookNumberPerPage;

@Component
public class BookDao {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;

    public List<Book> getAllBookFromDb(Integer page, int roleId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<Book> resultList = (List<Book>) query.getResultList();

        List<Book> bookList = new ArrayList<>();
        if (roleId == 1) {
            for (int i = (page - 1) * bookNumberPerPage; i < resultList.size() && i < (page * bookNumberPerPage); i++) {
                if (resultList.get(i).isActive()){
                    bookList.add(resultList.get(i));
                }
            }
        } else {
            for (int i = (page - 1) * bookNumberPerPage; i < resultList.size() && i < (page * bookNumberPerPage); i++) {
                bookList.add(resultList.get(i));
            }
        }


        return bookList;
    }

    public void saveBookToDb(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (book.getId() == null) {
            currentSession.save(book);
        } else {

            String hql = "update books set name=:name, description=:description, imageName =:imageName, isActive =: isActive, price =: price where id=:id";

            Query query = currentSession.createQuery(hql);
            query.setParameter("name",book.getName());
            query.setParameter("description",book.getDescription());
            query.setParameter("imageName",book.getImageName());
            query.setParameter("isActive",book.isActive());
            query.setParameter("price",book.getPrice());
            query.setParameter("id",book.getId());

            query.executeUpdate();
        }
    }

    public Integer getTotalPages(int roleId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<Book> resultList = (List<Book>) query.getResultList();

        int size;
        if (roleId == 1){
            int count = 0;
            for (Book book : resultList) {
                if (book.isActive()){
                    count++;
                }
            }
            size = count;
        } else {
            size = resultList.size();
        }


        if (size % bookNumberPerPage == 0) {
            size = size / bookNumberPerPage;
        } else {
            size = size / bookNumberPerPage + 1;
        }

        return size;
    }

    public Book getBookById(Integer bookId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Book book = currentSession.get(Book.class, bookId);
        return book;
    }

    public void deleteBookById(Integer bookId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from books where id=" + bookId);
        query.executeUpdate();
    }

    public List<Book> getAllBooksFromDb() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void purchaseBook(int userId, Integer bookId) {
        String query = "Select * from person_purchase_book("+userId+", "+bookId+")";

        Boolean isPurchased = template.queryForObject(query, (rs, rowNum) -> rs.getBoolean(1));

    }
}
