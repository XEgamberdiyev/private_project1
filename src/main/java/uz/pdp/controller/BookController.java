package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.model.Book;
import uz.pdp.model.User;
import uz.pdp.service.BookService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;


    @GetMapping(path = "/{page}")
    public String getCoursesForAdmin(@PathVariable Integer page, Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        int roleId = (int)session.getAttribute("roleId");
        List<Book> allBooks = bookService.getAllBooks(page, roleId);
        Integer pages = bookService.getTotalPages(roleId);


        model.addAttribute("bookList", allBooks);
        model.addAttribute("pages", pages);
        return "/views/book/view-books";
    }

    @GetMapping(path = "/addBookForm")
    public String addBookForm(Model model) {
        List<User> authorList = userService.getAuthor();

        model.addAttribute("authorList", authorList);
        return "/book/addBookForm";
    }

    @PostMapping(path = "/addBook")
    public String addBook(HttpServletRequest request, @RequestParam(name = "file") CommonsMultipartFile file) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String[] authors = request.getParameterValues("authors");
        String active = request.getParameter("active");

        boolean isActive = true;
        if (active == null){
            isActive = false;
        }

        if (id == null){
            Book book = new Book();
            book.setName(name);
            book.setDescription(description);
            book.setPrice(Double.valueOf(price));
            book.setActive(isActive);
            bookService.saveBook(book, file, authors);
        } else {
            Book bookById = bookService.getBookById(Integer.parseInt(id));
            bookById.setName(name);
            bookById.setDescription(description);
            bookById.setPrice(Double.valueOf(price));
            bookById.setActive(isActive);
            bookService.saveBook(bookById, file, authors);
        }

        return "redirect:/books/1";
    }

    @GetMapping(path = "/editForm/{bookId}")
    public String editCourseForm(@PathVariable Integer bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        List<User> authorList = userService.getAuthor();

        model.addAttribute("authorList", authorList);
        return "/book/editBookForm";
    }


    @GetMapping(path = "/delete/{bookId}")
    public String deleteBook(@PathVariable Integer bookId, Model model) {
        bookService.deleteBookById(bookId);
        return "redirect:/books/1";
    }

    @GetMapping(path = "/info/{bookId}")
    public String getBookInfo(@PathVariable Integer bookId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastCourseId", bookId);
        Book bookById = bookService.getBookById(bookId);
        List<User> users = bookById.getUsers();
        List<User> authors = new ArrayList<>();
        for (User user : users) {
            if (user.getRoleId() == 2){
                authors.add(user);
            }
        }

        model.addAttribute("book", bookById);
        model.addAttribute("authors", authors);
        return "/book/bookInfo";
    }
}
