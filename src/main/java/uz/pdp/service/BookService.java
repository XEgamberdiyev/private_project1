package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.dao.BookDao;
import uz.pdp.dao.UserDao;
import uz.pdp.model.Book;
import uz.pdp.model.User;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static uz.pdp.constants.Constants.resourcePath;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;


    @Transactional
    public List<Book> getAllBooks(Integer page, int roleId) throws IOException {
        List<Book> allBooksFromDb = bookDao.getAllBookFromDb(page, roleId);

        for (Book book : allBooksFromDb) {

            BufferedImage image =  ImageIO.read(new File(resourcePath + "/" + book.getImageName()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image,"png",base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            book.setImagePath(b64);
        }

        return allBooksFromDb;
    }

    @Transactional
    public void saveBook(Book book, CommonsMultipartFile file, String[] authors) throws IOException {

        for (String authorId : authors) {
            User author = userDao.getUserById(authorId);
            book.getUsers().add(author);
        }

        String filename=file.getOriginalFilename();

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(resourcePath+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e){}

        book.setImageName(filename);

        bookDao.saveBookToDb(book);

    }

    @Transactional
    public Integer getTotalPages(int roleId) {
        return bookDao.getTotalPages(roleId);
    }

    @Transactional
    public Book getBookById(Integer bookId) {
        return bookDao.getBookById(bookId);
    }

    @Transactional
    public void deleteBookById(Integer bookId) {
        bookDao.deleteBookById(bookId);
    }

    @Transactional
    public List<Book> getAllBooks() throws IOException {
        List<Book> allBooksFromDb = bookDao.getAllBooksFromDb();

        for (Book book : allBooksFromDb) {

            BufferedImage image =  ImageIO.read(new File(resourcePath + "/" + book.getImageName()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image,"png",base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            book.setImagePath(b64);
        }

        return allBooksFromDb;
    }

    public void purchaseBook(int userId, Integer bookId) {
        bookDao.purchaseBook(userId, bookId);
    }
}
